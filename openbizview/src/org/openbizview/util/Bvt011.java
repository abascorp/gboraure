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
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
import org.openbizview.getset.Periodos;
import org.openbizview.util.PntGenerica;

/**
 *
 * @author Andres
 */
@ManagedBean
@ViewScoped
public class Bvt011 extends Bd {

	public Bvt011() throws ClassNotFoundException, SQLException, NamingException{
         select();
	}
	
	private String bcodciaorigen = "";
	private String bcodciadestino = "";  
	private String anocal = "";
	private String numper = "";
	private Date fecini;
    private Date fecfin;
    private String descrip = "";
    private String percopia = " - ";  //Localidad para copiar
    private List<Periodos> list = new ArrayList<Periodos>();
    private List<Periodos> filtro;
    private int rows = 0;
	


	/**
	 * @return the anocal
	 */
	public String getAnocal() {
		return anocal;
	}

	/**
	 * @param anocal the anocal to set
	 */
	public void setAnocal(String anocal) {
		this.anocal = anocal;
	}

	/**
	 * @return the numper
	 */
	public String getNumper() {
		return numper;
	}

	/**
	 * @param numper the numper to set
	 */
	public void setNumper(String numper) {
		this.numper = numper;
	}


	/**
	 * @return the descrip
	 */
	public String getDescrip() {
		return descrip;
	}

	/**
	 * @param descrip the descrip to set
	 */
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	

/**
	 * @return the fecini
	 */
	public Date getFecini() {
		return fecini;
	}

	/**
	 * @param fecini the fecini to set
	 */
	public void setFecini(Date fecini) {
		this.fecini = fecini;
	}

	/**
	 * @return the fecfin
	 */
	public Date getFecfin() {
		return fecfin;
	}

	/**
	 * @param fecfin the fecfin to set
	 */
	public void setFecfin(Date fecfin) {
		this.fecfin = fecfin;
	}

/**
	 * @return the bcodciadestino
	 */
	public String getBcodciadestino() {
		return bcodciadestino;
	}

	/**
	 * @param bcodciadestino the bcodciadestino to set
	 */
	public void setBcodciadestino(String bcodciadestino) {
		this.bcodciadestino = bcodciadestino;
	}
	
	

/**
	 * @return the percopia
	 */
	public String getPercopia() {
		return percopia;
	}

	/**
	 * @param percopia the percopia to set
	 */
	public void setPercopia(String percopia) {
		this.percopia = percopia;
	}

    
    	/**
	 * @return the list
	 */
	public List<Periodos> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Periodos> list) {
		this.list = list;
	}

	/**
	 * @return the filtro
	 */
	public List<Periodos> getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<Periodos> filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return the bcodciaorigen
	 */
	public String getBcodciaorigen() {
		return bcodciaorigen;
	}
	
	/**
	 * @param bcodciaorigen the bcodciaorigen to set
	 */
	public void setBcodciaorigen(String bcodciaorigen) {
		this.bcodciaorigen = bcodciaorigen;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
FacesMessage msj = null;
PntGenerica consulta = new PntGenerica();
boolean vGacc; //Validador de opciones del menú
private String login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); //Usuario logeado

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



//Coneccion a base de datos
	//Pool de conecciones JNDI
	//Coneccion a base de datos
	//Pool de conecciones JNDIFARM
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;
	
	private int validarOperacion=0;
	
	
	public int getValidarOperacion() {
		return validarOperacion;
	}
	
	
	public void setValidarOperacion(int validarOperacion) {
		this.validarOperacion = validarOperacion;
	}



     /**
     * Inserta categoria1.
     * <p>
     * <b>Parametros del Metodo:<b> String codcia1, String descia1 unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    private void insert() throws  NamingException {   
    	
    		 BigDecimal[] conver = new BigDecimal[2];
    	     conver[0] = new BigDecimal(anocal);
    	     conver[1] = new BigDecimal(numper);

        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();		
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "INSERT INTO bvt011 VALUES (" + conver[0] + "," + conver[1] + ",'"           
            		+  sdfecha.format(fecini) + "','" +  sdfecha.format(fecfin) + "',?,?,'" 
            		+ getFecha() + "',?,'" + getFecha() + "')";
            ////System.out.println(query);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, descrip.toUpperCase());
            pstmt.setString(2, login);
            pstmt.setString(3, login);
            ////System.out.println(query);
            try {
                //Avisando
                pstmt.executeUpdate();
                limpiarValores();
                list.clear();
                select();
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO,getMessage("msnInsert"), "");        
             } catch (SQLException e)  {
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            }
            pstmt.close();
            con.close();
            
        } catch (Exception e) {
        }
    	
        FacesContext.getCurrentInstance().addMessage(null, msj);
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
     		
        	String query = "DELETE bvt011 WHERE anocal||numper in (" + param + ")";
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
            //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
            
            try {
                pstmt.executeUpdate();
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
    
    
    /**
     * Actualiza categoria1
     * <b>Parametros del Metodo:<b> String codcia1, String descia1 unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void update() throws  NamingException {
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();		
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "UPDATE bvt011";
             query += " SET fecini = '" + sdfecha.format(fecini) + "', fecfin = '" + sdfecha.format(fecfin) 
            		 + "', descrip = ?, usract = ?, fecact='" + getFecha() + "'";
             query += " WHERE  anocal = ? and numper = ?";
            //System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, descrip.toUpperCase());
            pstmt.setString(2, login.toUpperCase());
            pstmt.setString(3, anocal);
            pstmt.setString(4, numper);
            // Antes de ejecutar valida si existe el registro en la base de Datos.
            // Antes de ejecutar valida si existe el registro en la base de Datos.
            try {
                //Avisando
                pstmt.executeUpdate();
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
                }
                validarOperacion = 0;
            	bcodciaorigen = "";
            	bcodciadestino = "";  
            	fecini = null;
                fecfin = null;
                descrip = "";
                percopia = " - ";  //Localidad para copiar
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
 		
 		
 		//Consulta paginada
               String query = " SELECT anocal, numper, to_char(fecini,'dd/mm/yyyy'), to_char(fecfin,'dd/mm/yyyy'), descrip";
               query += " FROM bvt011";
               query += " where anocal  like trim('" + anocal +  "%')";
               query += " and numper  like trim('" + numper +  "%')";
               query += " ORDER BY 1 desc";
       
       pstmt = con.prepareStatement(query);
       ////System.out.println(query);
 		
       r =  pstmt.executeQuery();
      	
       
       while (r.next()){
    	Periodos select = new Periodos();
    	select.setVanocal(r.getString(1));
    	select.setVnumper(r.getString(2));
       	select.setVfecini(r.getString(3));
       	select.setVfecfin(r.getString(4));
       	select.setVdescrip(r.getString(5));
       	//Agrega la lista
       	list.add(select);
       	rows = list.size();
       }
       //Cierra las conecciones
       pstmt.close();
       con.close();

 	}
   
   /**
  	 * @return the rows
  	 */
  	public int getRows() {
  		return rows;
  	}
  	
  	
  	public void buscar() throws ClassNotFoundException, SQLException, NamingException{
  		list.clear();
  		select();
  	}
  	
  	
  	/**Este metodo copia sucursales de una compañia a otra
  	 * <p>
  	 * Parametros:
  	 * Origen: p_codcia(Origen), codsuc (origen) si deja en blanco trae todas.
  	 * <p>
  	 * Destino: p_codcia (Destino).
  	 * <p>
  	 * Si el campo de vecValores[1] (Sucursal) realiza un select para calcular
  	 * la diferencia de los registros que existen en una tabla y que no existen
  	 * en otra y solo copiar lo que no este. De lo contrario tomaria todos los
  	 * registros. Y si existieran daria un error de constraint.
  	 * */

  	public void copiar() throws  NamingException {

  	 String campos = "b_codcia, anocal, numper, fecini, fecfin, descrip, USRCRE, FECCRE, USRACT, FECACT";
  	 
  	 String[] veccodcia = bcodciaorigen.split("\\ - ", -1);
  	 String[] veccodciadestino = bcodciadestino.split("\\ - ", -1);
  	 String[] vecpercopia = percopia.split("\\ - ", -1);
  	 
  	        try {
  	        	Context initContext = new InitialContext();     
  	     		DataSource ds = (DataSource) initContext.lookup(JNDI);

  	     		con = ds.getConnection();		
  	            //Class.forName(getDriver());
  	            //con = DriverManager.getConnection(
  	            //        getUrl(), getUsuario(), getClave());
  	            String query = "INSERT INTO bvt011 (" + campos + ")";
  	            query += " SELECT '" + veccodciadestino[0] + "' ,anocal, numper, fecini, fecfin, descrip" ;
  	            query += " , 'BI', '" + getFecha() + "' , 'BI', '" + getFecha() + "'";
  	            query += " FROM bvt011";
  	            query += " WHERE b_codcia= TRIM('" + veccodcia[0] + "')";
  	            if (vecpercopia[0].equals("")){
  	            query += " AND anocal||numper NOT IN (SELECT anocal||numper FROM bvt011 WHERE b_codcia='" +  veccodciadestino[0] + "')";
  	            } else {
  	            query += " AND anocal||numper NOT IN (SELECT anocal||numper FROM bvt011 WHERE b_codcia='" +  veccodciadestino[0] + "') AND anocal||numper= TRIM('" + vecpercopia[0] + "')";
  	            }
  	            pstmt = con.prepareStatement(query);
  	               try {
  	                ////System.out.println(query);
  	                //Avisando
  	                pstmt.executeUpdate();
  	                if (pstmt.getUpdateCount() == 0) {
  	                   msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("nocopias"), "");
  	                    percopia = " - ";
  	                } else  if (pstmt.getUpdateCount() <= 1) {
  	                  msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("copia"), "");
  	                    percopia = " - ";
  	                }
  	              limpiarValores();
  	              list.clear();
                  select();
  	            } catch (SQLException e)  {
  	                 e.printStackTrace();
  	               msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
  	            }
  	            pstmt.close();
  	            con.close();

  	        } catch (Exception e) {
  	            e.printStackTrace();
  	        }
  		 FacesContext.getCurrentInstance().addMessage(null, msj);
  	}


    private void limpiarValores() {
    	bcodciaorigen = "";
    	bcodciadestino = "";  
    	anocal = "";
    	numper = "";
    	fecini = null;
        fecfin = null;
        descrip = "";
        percopia = " - ";  //Localidad para copiar
    	validarOperacion = 0;
	}



}
