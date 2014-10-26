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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.openbizview.getset.Plan;
import org.openbizview.util.PntGenerica;

/**
 *
 * @author Andres
 */
@ManagedBean
@ViewScoped
public class Bvt008 extends Bd {

	public Bvt008() throws ClassNotFoundException, SQLException, NamingException{
         select();
	}
	

	private String codplan = "";
	private String desplan = "";
	private String pcodgrup = "";
	private int validarOperacion = 0;
	private List<Plan> list = new ArrayList<Plan>();
	private List<Plan> filtro;
	
	/**
	 * @return the codplan
	 */
	public String getcodplan() {
		return codplan;
	}
	/**
	 * @param codplan the codplan to set
	 */
	public void setcodplan(String codplan) {
		this.codplan = codplan;
	}
	/**
	 * @return the desplan
	 */
	public String getdesplan() {
		return desplan;
	}
	/**
	 * @param desplan the desplan to set
	 */
	public void setdesplan(String desplan) {
		this.desplan = desplan;
	}
	
	
	/**
	 * @return the pcodgrup
	 */
	public String getPcodgrup() {
		return pcodgrup;
	}
	/**
	 * @param pcodgrup the pcodgrup to set
	 */
	public void setPcodgrup(String pcodgrup) {
		this.pcodgrup = pcodgrup;
	}
	
	
    /**
	 * @return the list
	 */
	public List<Plan> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Plan> list) {
		this.list = list;
	}
	/**
	 * @return the filtro
	 */
	public List<Plan> getFiltro() {
		return filtro;
	}
	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<Plan> filtro) {
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
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
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
		Connection con;
		PreparedStatement pstmt = null;
		ResultSet r;




     /**
     * Inserta categoria1.
     * <p>
     * <b>Parametros del Metodo:<b> String codplan, String desplan unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void insert() throws  NamingException {   	
    	//Valida que los campos no sean nulos
    		String[] vecbcodgrup = pcodgrup.split("\\ - ", -1);
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
            con = ds.getConnection();
            
            String query = "INSERT INTO bvt008 VALUES (?,?,?,'" + getFecha() + "',?,'" + getFecha() + "',?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, codplan.toUpperCase());
            pstmt.setString(2, desplan.toUpperCase());
            pstmt.setString(3, login);
            pstmt.setString(4, login);
            pstmt.setString(5, vecbcodgrup[0]);
            try {
                //Avisando
            	pstmt.executeUpdate();
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
        	e.printStackTrace();
        }	
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
    
    
    public void delete() throws NamingException  {  
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
	
	        	String query = "DELETE bvt008 WHERE codplan in (" + param + ")";
	        		        	
	            pstmt = con.prepareStatement(query);
	            ////System.out.println(query);
	
	            try {
	                //Avisando
	                pstmt.executeUpdate();
	                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
	                list.clear();
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
     * <b>Parametros del Metodo:<b> String codplan, String desplan unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void update() throws  NamingException {
    
    		String[] vecbcodgrup = pcodgrup.split("\\ - ", -1);
    		
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();		
     		
            String query = "UPDATE bvt008";
             query += " SET desplan = ?, usract = ?, fecact='" + getFecha() + "', p_codgrup = ?";
             query += " WHERE codplan = ?";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, desplan.toUpperCase());
            pstmt.setString(2, login);
            pstmt.setString(3, vecbcodgrup[0]);
            pstmt.setString(4, codplan.toUpperCase());
            try {
                //Avisando
                pstmt.executeUpdate();
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
                }
                desplan = "";
            	pcodgrup = " - ";
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
    
    
    public void guardar() throws NamingException, SQLException{   	
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
 		
 		pcodgrup = " - ";
 		
 		//Consulta paginada
 		String query = "  SELECT a.codplan, a.desplan, a.p_codgrup, b.desgrup";
               query += " FROM bvt008 a, bvt017 b";
               query += " WHERE a.p_codgrup=b.codgrup (+)";
               query += " and a.codplan like '" + codplan.toUpperCase() + "%'";
               query += " ORDER BY a.codplan" ;
       
       pstmt = con.prepareStatement(query);
       ////System.out.println(query);
 		
       r =  pstmt.executeQuery();
       
       while (r.next()){
    	Plan select = new Plan();
    	select.setVcodplan(r.getString(1));
    	select.setVdesplan(r.getString(2));
       	select.setVcodgrupdesgrup(r.getString(3) + " - " + r.getString(4));
       	select.setVdesgrup(r.getString(4));
       	//Agrega la lista
       	list.add(select);
       	rows = list.size();
       }
       //Cierra las conecciones
       pstmt.close();
       con.close();
       pcodgrup = "";
 	}
   
   /**
  	 * @return the rows
  	 */
  	public int getRows() {
  		return rows;
  	}
  	
  	private void limpiarValores() {
		// TODO Auto-generated method stub
  		codplan = "";
  		desplan = "";
  		pcodgrup = " - ";
  		validarOperacion = 0;
	}



}
