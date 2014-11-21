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
import org.openbizview.getset.Cias;
import org.openbizview.util.PntGenerica;

	/**
	 *
	 * @author Andres
	 */
	@ManagedBean
	@ViewScoped
	public class Bvt010 extends Bd {
	
		@PostConstruct
		public void init() {
	        try {
				select();
			} catch (ClassNotFoundException | SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	private String codcia = "";
	private String descia = "";
	private String exito = "exito";
	
	
	
	/**
	 * @return the exito
	 */
	public String getExito() {
		return exito;
	}
	/**
	 * @param exito the exito to set
	 */
	public void setExito(String exito) {
		this.exito = exito;
	}


	private List<Cias> list = new ArrayList<Cias>();
	private List<Cias> filtro;
	private int rows = 0;

	
	/**
	 * @return the codcia
	 */
	public String getcodcia() {
		return codcia;
	}
	/**
	 * @param codcia the codcia to set
	 */
	public void setcodcia(String codcia) {
		this.codcia = codcia;
	}
	/**
	 * @return the descia
	 */
	public String getdescia() {
		return descia;
	}
	/**
	 * @param descia the descia to set
	 */
	public void setdescia(String descia) {
		this.descia = descia;
	}


	
	/**
	 * @return the list
	 */
	public List<Cias> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Cias> list) {
		this.list = list;
	}
	/**
	 * @return the filtro
	 */
	public List<Cias> getFiltro() {
		return filtro;
	}
	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<Cias> filtro) {
		this.filtro = filtro;
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
    public void insert() throws  NamingException {   	
    	//Valida que los campos no sean nulos
  		
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();		
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "INSERT INTO bvt010 VALUES (?,?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, codcia.toUpperCase());
            pstmt.setString(2, descia.toUpperCase());
            pstmt.setString(3, login);
            pstmt.setString(4, login);
            try {
                //Avisando
                pstmt.executeUpdate();
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
                codcia = "";
                list.clear();
                select();
            } catch (SQLException e)  {
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            	 FacesContext.getCurrentInstance().addMessage(null, msj);
            	 exito = "error";
            }
            
            pstmt.close();
            con.close();
        } catch (Exception e) {
        }
       
    }
    
    
    /**
     * Borra Paises
     * <p>
     * Parametros del metodo: String codpai. Pool de conecciones
     **/
    public void delete() {  
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
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //        getUrl(), getUser(), getPass());
        	String query = "DELETE bvt010 WHERE codcia in (" + param + ")";
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
            //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
            
            try {
                pstmt.executeUpdate();
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
                list.clear();
                codcia = "";
                select();
                limpiarValores();	
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
            String query = "UPDATE bvt010";
             query += " SET descia = ?, usract = ?, fecact='" + getFecha() + "'";
             query += " WHERE codcia = ?";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, descia.toUpperCase());
            pstmt.setString(2, login);
            pstmt.setString(3, codcia.toUpperCase());
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
                descia = "";
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
   
    
    public void guardar() throws NamingException, SQLException{   	
    	if(validarOperacion==0){
    		insert();
    	} else {
    		update();
    	}
    	if(exito=="exito"){
    		msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
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
	               String query = "  SELECT codcia, descia";
	               query += " FROM bvt010";
	               query += " WHERE codcia  like '" + codcia.toUpperCase() + "%'" ;
	               query += " ORDER BY 1";
	       
	       pstmt = con.prepareStatement(query);
	       ////System.out.println(query);
	 		
	       r =  pstmt.executeQuery();
	       
	       
	       while (r.next()){
	    	Cias select = new Cias();
	    	select.setVcodcia(r.getString(1));
	    	select.setVdescia(r.getString(2));
	       	
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
  	
    private void limpiarValores() {
		// TODO Auto-generated method stub
    	codcia = "";
    	descia = "";
    	validarOperacion = 0;
	}




}
