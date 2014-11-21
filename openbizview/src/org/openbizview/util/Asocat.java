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
import java.util.Date;
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
import org.openbizview.getset.AsociarCat;


	/**
	 *
	 * @author Andres
	 */
	@ManagedBean
	@ViewScoped
	public class Asocat extends Bd {
	
	public void init() {
	   try {
		select();
	} catch (ClassNotFoundException | SQLException | NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	private String bcodcat4 = "";
	private String codproc = "";
	private String uorg = "";
	private Date feccam = null;
	private List<AsociarCat> list = new ArrayList<AsociarCat>();
	private List<AsociarCat> filtro;
	
	     /**
	 * @return the bcodcat4
	 */
	public String getBcodcat4() {
		return bcodcat4;
	}
	
	/**
	 * @param bcodcat4 the bcodcat4 to set
	 */
	public void setBcodcat4(String bcodcat4) {
		this.bcodcat4 = bcodcat4;
	}
	
	/**
	 * @return the codproc
	 */
	public String getCodproc() {
		return codproc;
	}
	
	/**
	 * @param codproc the codproc to set
	 */
	public void setCodproc(String codproc) {
		this.codproc = codproc;
	}
	
	/**
	 * @return the uorg
	 */
	public String getUorg() {
		return uorg;
	}
	
	/**
	 * @param uorg the uorg to set
	 */
	public void setUorg(String uorg) {
		this.uorg = uorg;
	}
	
	
	
	/**
	 * @return the feccam
	 */
	public Date getFeccam() {
		return feccam;
	}
	
	/**
	 * @param feccam the feccam to set
	 */
	public void setFeccam(Date feccam) {
		this.feccam = feccam;
	}
	
	
	
	

/**
	 * @return the list
	 */
	public List<AsociarCat> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<AsociarCat> list) {
		this.list = list;
	}

	/**
	 * @return the filtro
	 */
	public List<AsociarCat> getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<AsociarCat> filtro) {
		this.filtro = filtro;
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
     * Inserta acocat.
     * <p>
     * <b>Parametros del Metodo:<b> String codcat4, String codproc, String uorg unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void insert() throws  NamingException {
    	//Valida que los campos no sean nulos
    	
    		String[] veccodcat4 = bcodcat4.split("\\ - ", -1);
    		String[] vcodproc = codproc.split("\\ - ", -1);
    		String[] vuorg = uorg.split("\\ - ", -1);
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
            con = ds.getConnection();
            
            String query = "INSERT INTO asocat VALUES (?,?,?,'" + getFecha() + "',?,'" + getFecha() + "',?,'" + sdfecha.format(feccam).toString() + "')";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, veccodcat4[0].toUpperCase());
            pstmt.setString(2, vcodproc[0].toUpperCase());
            pstmt.setString(3, login);
            pstmt.setString(4, login);
            pstmt.setString(5, vuorg[1].toUpperCase());
            ////System.out.println(query);
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
	
	        	String query = "DELETE asocat WHERE uorg||b_codcat4||codproc in (" + param + ")";
	        		        	
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
     * Leer Datos de categoria2
     * @throws NamingException 
     * @throws IOException 
     **/ 	
  	public void select() throws SQLException, ClassNotFoundException, NamingException {
  		
  		Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);

 		con = ds.getConnection();		
  		
  		
  		//Consulta paginada
  		String query = " SELECT a.uorg, a.b_codcat4, b.descat4, a.codproc, to_char(a.feccam,'dd/mm/yyyy') ";
               query += " FROM asocat a, bvtcat4 b";
               query += " WHERE a.b_codcat4=b.codcat4 ";
               query += " ORDER BY a.b_codcat4, a.uorg" ;
        
        pstmt = con.prepareStatement(query);
        ////System.out.println(query);
  		
        r =  pstmt.executeQuery();
        
        
        while (r.next()){
        	AsociarCat select = new AsociarCat();
     	    select.setVuorg(r.getString(1));
     	    select.setVcodcat(r.getString(2));
     	    select.setVcodcatdescat(r.getString(3));
     	    select.setVcodproc(r.getString(4));
        	select.setFeccam(r.getString(5));
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
		bcodcat4 = "";
		codproc = "";
		uorg = "";
		feccam = null;
	}



}
