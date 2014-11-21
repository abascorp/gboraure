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
import java.text.ParseException;
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
import org.openbizview.getset.Calendario;

import java.util.Date;

	/**
	 *
	 * @author Andres
	 */
	@ManagedBean(name="trgip001")
	@ViewScoped
	public class T_rgip001 extends Bd {
	
		private String anopro = "";
		private String mespro = "";
		private String anocon = "";
		private String mescon = "";
		private Date mesini = null;
	    private Date mesfin = null;
	    private String semcon = "";
	    private List<Calendario> list = new ArrayList<Calendario>();
	    private List<Calendario> filtro;
	    private int validarOperacion = 0;
	
	@PostConstruct
	public void init(){
	  try {
		select();
	} catch (ClassNotFoundException | SQLException | NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	
	/**
	 * @return the anopro
	 */
	public String getAnopro() {
		return anopro;
	}
	
	
	/**
	 * @param anopro the anopro to set
	 */
	public void setAnopro(String anopro) {
		this.anopro = anopro;
	}
	
	
	/**
	 * @return the mespro
	 */
	public String getMespro() {
		return mespro;
	}
	
	
	/**
	 * @param mespro the mespro to set
	 */
	public void setMespro(String mespro) {
		this.mespro = mespro;
	}
	
	
	/**
	 * @return the anocon
	 */
	public String getAnocon() {
		return anocon;
	}
		
	
	/**
	 * @param anocon the anocon to set
	 */
	public void setAnocon(String anocon) {
		this.anocon = anocon;
	}
	
	
	/**
	 * @return the mescon
	 */
	public String getMescon() {
		return mescon;
	}
		
	
	/**
	 * @param mescon the mescon to set
	 */
	public void setMescon(String mescon) {
		this.mescon = mescon;
	}
		
	
	/**
	 * @return the mesini
	 */
	public Date getMesini() {
		return mesini;
	}
		
	
	/**
	 * @param mesini the mesini to set
	 */
	public void setMesini(Date mesini) {
		this.mesini = mesini;
	}
		
	
	/**
	 * @return the mesfin
	 */
	public Date getMesfin() {
		return mesfin;
	}
		
	
	/**
	 * @param mesfin the mesfin to set
	 */
	public void setMesfin(Date mesfin) {
		this.mesfin = mesfin;
	}
		
	
	/**
	 * @return the semcon
	 */
	public String getSemcon() {
		return semcon;
	}
		
	
	/**
	 * @param semcon the semcon to set
	 */
	public void setSemcon(String semcon) {
		this.semcon = semcon;
	}
	
	/**
	 * @return the list
	 */
	public List<Calendario> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Calendario> list) {
		this.list = list;
	}


	/**
	 * @return the filtro
	 */
	public List<Calendario> getFiltro() {
		return filtro;
	}


	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<Calendario> filtro) {
		this.filtro = filtro;
	}


	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//Coneccion a base de datos
	//Pool de conecciones JNDI
		Connection con;
		PreparedStatement pstmt = null;
		ResultSet r;


     /**
     * Inserta calendario.
     * <p>
     * @throws ParseException 
     **/
    public void insert() throws  NamingException, ParseException {
    	//Valida que los campos no sean nulos
         BigDecimal[] conver = new BigDecimal[5];
        conver[0] = new BigDecimal(anopro);
        conver[1] = new BigDecimal(mespro);
        conver[2] = new BigDecimal(anocon);
        conver[3] = new BigDecimal(mescon);
        conver[4] = new BigDecimal(semcon);

        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
            con = ds.getConnection();
            
            String query = "INSERT INTO rgipadmin.t_rgip001@infocent_calendario VALUES (" + conver[0] + ","+ conver[1] 
            		+ "," + conver[2] + "," + conver[3] + ",'" + sdfecha.format(mesini) + "','" + sdfecha.format(mesfin) + "'," +  conver[4] + ")";
            pstmt = con.prepareStatement(query);
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
	
	        	String query = "DELETE rgipadmin.t_rgip001@infocent_calendario WHERE anopro||mespro||anocon||mescon||semcon in (" + param + ")";
	        		        	
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
     * Actualiza calendario
     **/
    public void update() throws  NamingException {
    	//Valida que los campos no sean nulos
    	
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();	
     		
            String query = "UPDATE rgipadmin.t_rgip001@infocent_calendario";
             query += " SET FECINI= '" + sdfecha.format(mesini) + "', FECFIN = '" + sdfecha.format(mesfin) + "'";
             query += " WHERE ANOPRO = ? AND MESPRO = ? AND ANOCON = ? AND MESCON = ? AND SEMCON = ?";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, anopro);
            pstmt.setString(2, mespro);
            pstmt.setString(3, anocon);
            pstmt.setString(4, mescon);
            pstmt.setString(5, semcon);
            try {
                //Avisando
                pstmt.executeUpdate();
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
                }
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
    
     public void guardar() throws NamingException, SQLException, ParseException{   	
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
  		String query = " SELECT ANOPRO, MESPRO, ANOCON, MESCON, SEMCON, TO_CHAR(FECINI, 'DD/MM/YYYY'), TO_CHAR(FECFIN, 'DD/MM/YYYY')";
  		       query += " FROM rgipadmin.t_rgip001@infocent_calendario";
  		       query += " ORDER BY ANOPRO, MESPRO" ;
        
        pstmt = con.prepareStatement(query);
        //System.out.println(query);
  		
        r =  pstmt.executeQuery();
        
        while (r.next()){
       	Calendario select = new Calendario();
     	select.setVlanopro(r.getString(1));
     	select.setVlmespro(r.getString(2));
        select.setVlanocon(r.getString(3));
        select.setVlmescon(r.getString(4));
        select.setVlsemcon(r.getString(5));
        select.setVlmesini(r.getString(6));
        select.setVlmesfin(r.getString(7));
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
   		anopro = "";
		mespro = "";
		anocon = "";
		mescon = "";
		mesini = null;
	    mesfin = null;
	    semcon = "";
  		validarOperacion = 0;
	}
   
}
