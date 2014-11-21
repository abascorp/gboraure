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

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.openbizview.getset.Escenarios;
import org.openbizview.util.PntGenerica;

/**
 *
 * @author Andres
 */
@ManagedBean
@ViewScoped

public class Bvt012 extends Bd {

	@PostConstruct
	public void init() {
       try {
		select();
	} catch (ClassNotFoundException | SQLException | NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	private String anoper = "";
	private String codesc = "";
	private String desesc = "";
	private List<Escenarios> list = new ArrayList<Escenarios>();
	private List<Escenarios> filtro;
	private int validarOperacion=0;
	

	
	/**
	 * @return the codesc
	 */
	public String getcodesc() {
		return codesc;
	}
	/**
	 * @param codesc the codesc to set
	 */
	public void setcodesc(String codesc) {
		this.codesc = codesc;
	}
	/**
	 * @return the desesc
	 */
	public String getdesesc() {
		return desesc;
	}
	/**
	 * @param desesc the desesc to set
	 */
	public void setdesesc(String desesc) {
		this.desesc = desesc;
	}
	

	/**
	 * @return the anoper
	 */
	public String getAnoper() {
		return anoper;
	}
	/**
	 * @param anoper the anoper to set
	 */
	public void setAnoper(String anoper) {
		this.anoper = anoper;
	}

	
    /**
	 * @return the list
	 */
	public List<Escenarios> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Escenarios> list) {
		this.list = list;
	}
	/**
	 * @return the filtro
	 */
	public List<Escenarios> getFiltro() {
		return filtro;
	}
	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<Escenarios> filtro) {
		this.filtro = filtro;
	}
	/**
	 * @return the validarOperacion
	 */
	public int getValidarOperacion() {
		return validarOperacion;
	}
	/**
	 * @param validarOperacion the validarOperacion to set
	 */
	public void setValidarOperacion(int validarOperacion) {
		this.validarOperacion = validarOperacion;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
FacesMessage msj = null;
PntGenerica consulta = new PntGenerica();
boolean vGacc; //Validador de opciones del menú
private int rows; //Registros de tabla
private String login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); //Usuario logeado
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//Coneccion a base de datos
	//Pool de conecciones JNDI
	//Coneccion a base de datos
	//Pool de conecciones JNDIFARM
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;



     /**
     * Inserta categoria1.
     * <p>
     * <b>Parametros del Metodo:<b> String codesc, String desesc unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    private void insert() throws  NamingException {   	
    	//Valida que los campos no sean nulos
    		String[] vecanoper = anoper.split("\\ - ", -1);
    		String anio = vecanoper[0].substring(0, 4);
    		String per;
            if(vecanoper[0].length()==6){
            	per = vecanoper[0].substring(4, 6);
            } else {
            	per = vecanoper[0].substring(4, 5);
            }
    		
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "INSERT INTO bvt012 VALUES (?,?,?,?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, anio);
            pstmt.setString(2, per);
            pstmt.setString(3, codesc.toUpperCase());
            pstmt.setString(4, desesc.toUpperCase());
            pstmt.setString(5, login);
            pstmt.setString(6, login);
            ////System.out.println("LONGITUD: " + vecanoper[0].length());
            ////System.out.println("AÑO: " + anio);
            ////System.out.println("PERIODO: " + per);
            try {
                //Avisando
            	pstmt.executeUpdate();
            	//insertBvt016(codcia.toUpperCase(), anio, per, codesc.toUpperCase(), "0");
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");   
                limpiarValores();
                list.clear();
                select();
             } catch (SQLException e)  {
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");          	
            }
            
            pstmt.close();
            con.close();
        } catch (Exception e) {
        }
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
    
    
    
    @SuppressWarnings("unused")
	private void insertBvt016(String codcia, String anocal, String numper, String codesc, String estatus) throws  NamingException {   	
         try {
        	 Context initContext = new InitialContext();     
      		DataSource ds = (DataSource) initContext.lookup(JNDI);

      		con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "INSERT INTO BVT016 VALUES (?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, codcia);
            pstmt.setString(2, anocal);
            pstmt.setString(3, numper);
            pstmt.setString(4, codesc);
            pstmt.setString(5, estatus);
            try {
                //Avisando
                pstmt.executeUpdate();
            } catch (SQLException e)  {
                  e.printStackTrace();
                  
            }
        } catch (Exception e) {
        }
    }
    
    
    /**
     * Borra Paises
     * <p>
     * Parametros del metodo: String codpai. Pool de conecciones
     **/
    public void delete() throws  NamingException {  
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	String[] chkbox = request.getParameterValues("toDelete");
    	
    	if (chkbox==null){
    		msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("del"), "");
    	} else {
	        try {
    		Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
     		
     		String param = "'" + StringUtils.join(chkbox, "','") + "'";
     		
        	String query = "DELETE bvt012 WHERE b_anocal||b_numper||codesc in (" + param + ")";
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
            //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
            
            try {
            	pstmt.executeUpdate();
            	deleteBvt016(param);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
                limpiarValores();
                list.clear();
                select();
                	
            } catch (SQLException e) {
                e.printStackTrace();
                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            }

            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
			}
			FacesContext.getCurrentInstance().addMessage(null, msj); 
		}
    
    private void deleteBvt016(String param) throws  NamingException {  
    	try {
    		Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //        getUrl(), getUser(), getPass());
        	String query = "DELETE BVT016 WHERE b_codcia||b_anocal||b_numper||b_codesc in (" + param + ")";
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
            //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
            
            try {
                //Avisando
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
               e.printStackTrace();
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    }
    
  
    /**
     * Actualiza categoria1
     * <b>Parametros del Metodo:<b> String codesc, String desesc unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void update() throws  NamingException {
    	String[] vecanoper = anoper.split("\\ - ", -1);
		String anio = vecanoper[0].substring(0, 4);
		String per;
        if(vecanoper[0].length()==6){
        	per = vecanoper[0].substring(4, 6);
        } else {
        	per = vecanoper[0].substring(4, 5);
        }

        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "UPDATE bvt012";
             query += " SET desesc = ?, usract = ?, fecact='" + getFecha() + "'";
             query += " WHERE codesc = ?  and b_anocal = ? and b_numper = ?";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, desesc.toUpperCase());
            pstmt.setString(2, login);
            pstmt.setString(3, codesc.toUpperCase());
            pstmt.setString(4, anio);
            pstmt.setString(5, per);
            ////System.out.println("LONGITUD: " + vecanoper[0].length());
            ////System.out.println("AÑO: " + anio);
            ////System.out.println("PERIODO: " + per);
            // Antes de ejecutar valida si existe el registro en la base de Datos.
            try {
                //Avisando
                pstmt.executeUpdate();
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
                }
            	desesc = "";
            	validarOperacion = 0;
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
   
    
    public void guardar() throws NamingException, SQLException, ClassNotFoundException{   	
    	if(validarOperacion==0){
    		insert();
    	} else {
    		update();
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
 		
		String[] vecanoper = anoper.split("\\ - ", -1);
 		
 		
 		//Consulta paginada
               String query = " SELECT codesc, desesc, b_anocal||b_numper, b_anocal||' - '||b_numper";
               query += " FROM bvt012";
               query += " where codesc LIKE trim('" + codesc.toUpperCase() +  "%') ";
               query += " AND   b_anocal||b_numper like '" + vecanoper[0] + "%'";
               query += " ORDER BY 1" ;

       
       pstmt = con.prepareStatement(query);
       ////System.out.println(query);
 		
       r =  pstmt.executeQuery();
       
       while (r.next()){
    	Escenarios select = new Escenarios();
    	select.setVcodesc(r.getString(1));
    	select.setVdesesc(r.getString(2));
       	select.setVbcodciaanocalnumper(r.getString(3));
       	select.setVbcodciaanocalnumper1(r.getString(4));
       	//Agrega la lista
       	list.add(select);
       	rows = list.size();
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

    private void limpiarValores() {
    	anoper = "";
    	codesc = "";
    	desesc = "";
    	validarOperacion=0;
	}



}
