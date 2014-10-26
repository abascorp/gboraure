/*
 *  Copyright (C) 2011  ANDRES DOMINGUEZ

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
    bajo los terminos de la Licencia Pública General GNU publicada 
    por la Fundacion para el Software Libre, ya sea la version 3 
    de la Licencia, o (a su eleccion) cualquier version posterior.

    Este programa se distribuye con la esperanza de que sea útil, pero 
    SIN GARANTiA ALGUNA; ni siquiera la garantia implicita 
    MERCANTIL o de APTITUD PARA UN PROPoSITO DETERMINADO. 
    Consulte los detalles de la Licencia Pública General GNU para obtener 
    una informacion mas detallada. 

    Deberia haber recibido una copia de la Licencia Pública General GNU 
    junto a este programa. 
    En caso contrario, consulte <http://www.gnu.org/licenses/>.
 */

package org.openbizview.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.openbizview.getset.Calculo;

/**
 *
 * @author Andres
 */
@ManagedBean
@SessionScoped
public class Bvt016 extends Bd {

	public Bvt016() throws ClassNotFoundException, SQLException, NamingException{
	  select();
	}

	private String bcodcia = "";
	private String banocal = "";
	private String bnumper = "";
	private String bcodesc = "";
	private String bstatus = "0";
	private List<Calculo> list = new ArrayList<Calculo>();
	private List<Calculo> filtro;
	
	

	 /**
	 * @return the list
	 */
	public List<Calculo> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Calculo> list) {
		this.list = list;
	}

	/**
	 * @return the filtro
	 */
	public List<Calculo> getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<Calculo> filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return the bcodcia
	 */
	public String getBcodcia() {
		return bcodcia;
	}
	
	/**
	 * @param bcodcia the bcodcia to set
	 */
	public void setBcodcia(String bcodcia) {
		this.bcodcia = bcodcia;
	}
	
	/**
	 * @return the banocal
	 */
	public String getBanocal() {
		return banocal;
	}
	
	/**
	 * @param banocal the banocal to set
	 */
	public void setBanocal(String banocal) {
		this.banocal = banocal;
	}
	
	/**
	 * @return the bnumper
	 */
	public String getBnumper() {
		return bnumper;
	}
	
	/**
	 * @param bnumper the bnumper to set
	 */
	public void setBnumper(String bnumper) {
		this.bnumper = bnumper;
	}
	
	/**
	 * @return the bcodesc
	 */
	public String getBcodesc() {
		return bcodesc;
	}
	
	/**
	 * @param bcodesc the bcodesc to set
	 */
	public void setBcodesc(String bcodesc) {
		this.bcodesc = bcodesc;
	}
	
	/**
	 * @return the bstatus
	 */
	public String getBstatus() {
		return bstatus;
	}
	
	/**
	 * @param bstatus the bstatus to set
	 */
	public void setBstatus(String bstatus) {
		this.bstatus = bstatus;
	}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
FacesMessage msj = null;
PntGenerica consulta = new PntGenerica();
boolean vGacc; //Validador de opciones del menú
private int rows; //Registros de tabla
//private String login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); //Usuario logeado
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//Coneccion a base de datos
//Pool de conecciones JNDI
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;

	
    
    /**
     * Borra Paises
     * <p>
     * Parametros del metodo: String codpai. Pool de conecciones
     **/
    public void delete() throws  NamingException {  
    	try {
    		Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //        getUrl(), getUser(), getPass());
        	String query = "DELETE  BVM003";
            query += " WHERE b_codcia = ? and b_anocal = ? and b_numper = ? and b_codesc = ?";
           ////System.out.println(query);
           pstmt = con.prepareStatement(query);
           pstmt.setString(1, bcodcia.toUpperCase());
           pstmt.setString(2, banocal);
           pstmt.setString(3, bnumper.toUpperCase());
           pstmt.setString(4, bcodesc.toUpperCase());    
            ////System.out.println(query);
            //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
            
            try {
                //Avisando
                pstmt.executeUpdate();
                depura();
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("bvt016Dep5"), "");  
                FacesContext.getCurrentInstance().addMessage(null, msj);

            } catch (SQLException e) {
                e.printStackTrace();
                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            }

            pstmt.close();
            con.close();      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    


    /**
     * Actualiza roles
     * <b>Parametros del Metodo:<b> String codrol, String desrol unidos como un solo string.<br>
     * String pool, String login.<br><br>
     * <b>Ejemplo:</b>updateBvt003("01|EJEMPLO","jdbc/opennomina","admin").
     **/
    public void update() throws  NamingException {
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
            PreparedStatement pstmt = null;
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "UPDATE BVT016";
             query += " SET ESTATUS = ?";
             query += " WHERE b_codcia = ? and b_anocal = ? and b_numper = ? and b_codesc = ?";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bstatus.toUpperCase());
            pstmt.setString(2, bcodcia.toUpperCase());
            pstmt.setString(3, banocal);
            pstmt.setString(4, bnumper.toUpperCase());
            pstmt.setString(5, bcodesc.toUpperCase());            
            
            try {
	            //Avisando
	            pstmt.executeUpdate();
	            if(pstmt.getUpdateCount()==0){
	            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
	            } else {
	            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("bvt016Apro5"), "");
	            }
	            list.clear();
	            select();
	            
	        } catch (SQLException e) {
	        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
	        }
	        pstmt.close();
	        con.close();
	    } catch (Exception e) {
	    }
	    FacesContext.getCurrentInstance().addMessage(null, msj);
	}
    
    private void depura() throws  NamingException {
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
            PreparedStatement pstmt = null;
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "UPDATE BVT016";
             query += " SET ESTATUS = 0";
             query += " WHERE b_codcia = ? and b_anocal = ? and b_numper = ? and b_codesc = ?";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bcodcia.toUpperCase());
            pstmt.setString(2, banocal);
            pstmt.setString(3, bnumper.toUpperCase());
            pstmt.setString(4, bcodesc.toUpperCase());            

            try {
                //Avisando
                pstmt.executeUpdate();
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
            } catch (SQLException e) {
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
            }
                
            pstmt.close();
            con.close();
        } catch (Exception e) {
        }
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
    
    /**
    private void updateEstatus() throws  NamingException {
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
            PreparedStatement pstmt = null;
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "UPDATE BVM003";
             query += " SET ESTATUS = ?";
             query += " WHERE b_codcia = ? and b_anocal = ? and b_numper = ? and b_codesc = ?";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bstatus.toUpperCase());
            pstmt.setString(2, bcodcia.toUpperCase());
            pstmt.setString(3, banocal);
            pstmt.setString(4, bnumper.toUpperCase());
            pstmt.setString(5, bcodesc.toUpperCase());            
            
            try {
                //Avisando
                pstmt.executeUpdate();
            } catch (SQLException e) {
            }
                
            pstmt.close();
            con.close();
        } catch (Exception e) {
        }
    }
    **/
    
    /**
     * Ejecuta procedimiento almacenado en la base de datos
     **/
    private void execProcedure(String param) throws  NamingException {
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
            CallableStatement proc = null;
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //        getUrl(), getUsuario(), getClave());

            //Verifica si la secuencia esta en el tope definido en la tabla y la resetea
            //consulta.selectPntGenerica("select SECBVM003.nextval from dual", getJNDI());

            //String[][] result = consulta.getArray();
            //double nextval = Double.parseDouble(result[0][0]);
            //double maxval = 999999999;

            proc = con.prepareCall("{CALL BICALPRE001_PRESU('" + param + "')}");

            try {
                //if(nextval>=maxval){
                //resetSeq();//LLama al procedimiento que resetea la secuencia
                //}
                //Avisando
                proc.execute(); //ejecuta 
               } catch (SQLException e) {
                e.printStackTrace();
                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            }
            //
            proc.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * Rutina que ejecuta el blanqueo de secuencia. Se hace estática porque es solo de uso interno de la clase
	 
	
	 private void resetSeq() throws  NamingException {
	    try {
	    	Context initContext = new InitialContext();     
	 		DataSource ds = (DataSource) initContext.lookup(JNDI);
	
	 		con = ds.getConnection();
	        CallableStatement proc = null;
	        //Class.forName(getDriver());
	        //con = DriverManager.getConnection(
	        //        getUrl(), getUsuario(), getClave());
	
	        proc = con.prepareCall("{CALL RESET_SEQ('SECBVM003')}");
	        proc.execute();
	        //
	        proc.close();
	        con.close();
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	**/
 
	 /**
	  * Borra Paises
	  * <p>
	  * Parametros del metodo: String codpai. Pool de conecciones
	  **/
	 public void procedure() throws  NamingException {  
	 	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	 	String[] param = request.getParameterValues("toProces");
	 	if(param!=null){
	 	for(int i=0;i<param.length;i++){
	 		execProcedure(param[i]);
	 		////System.out.println("PARAM:" + param[i]);
	 		  }
	 	 msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("bvt016Ok"), "");  
         FacesContext.getCurrentInstance().addMessage(null, msj);
	 	}
	 }
    
    /**
     * Leer Datos de paises
     * @throws NamingException 
     * @throws IOException 
     **/ 	
  	public void select() throws SQLException, ClassNotFoundException, NamingException {
  		Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);

 		con = ds.getConnection();
 			
  		
  		//Consulta paginada
  		String query = " SELECT B_CODCIA, B_ANOCAL, B_NUMPER, B_CODESC, DECODE(ESTATUS,'0', 'ABIERTO', 'APROBADO') ";
  		       query += " FROM BVT016";
  		       query += " WHERE ESTATUS = '" + bstatus + "'";
  		       query += " ORDER BY 1";
        
        pstmt = con.prepareStatement(query);
        ////System.out.println(query);
  		
        ResultSet r =  pstmt.executeQuery();
        
        while (r.next()){
        Calculo select = new Calculo();
        select.setVbcodcia(r.getString(1));
        select.setVbanocal(r.getString(2));
        select.setVbnumper(r.getString(3));
        select.setVbcodesc(r.getString(4));
        select.setVbstatus(r.getString(5));
        select.setVprocesar(r.getString(1) + "','" + r.getString(2) + "','" + r.getString(3) + "','" + r.getString(4) + "','" + r.getString(2)+r.getString(3));
        	//Agrega la lista
        	list.add(select);
        }
        //Cierra las conecciones
        pstmt.close();
        con.close();

  	}
  	
  	
  	public void buscar() throws ClassNotFoundException, SQLException, NamingException{
  		list.clear();
  		select();
  	}
  	
       /**
  	 * @return the rows
  	 */
  	public int getRows() {
  		return rows;
  	}





}
