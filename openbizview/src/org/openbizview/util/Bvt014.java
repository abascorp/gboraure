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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.openbizview.getset.Asociardim;


/**
 *
 * @author Andres
 */
@ManagedBean
@SessionScoped
public class Bvt014 extends Bd {
	
	public Bvt014() throws ClassNotFoundException, SQLException, NamingException{
		 		
       select();
	}
	
	@PostConstruct
	//Load the table before the html table is rended on the page
    public void initialize()
    {
      list.clear();
      try {
		select();
	} catch (ClassNotFoundException | SQLException | NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

	private String bcoddim = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dim");
	private String asodim = "";
	private String desasodim = "";
	private String bcodgto = "";
	private int validarOperacion = 0;
	private List<Asociardim> list = new ArrayList<Asociardim>();
	private List<Asociardim> filtro; 
   
	
	/**
	 * @return the bcoddim
	 */
	public String getBcoddim() {
		return bcoddim;
	}

	/**
	 * @param bcoddim the bcoddim to set
	 */
	public void setBcoddim(String bcoddim) {
		this.bcoddim = bcoddim;
	}

	/**
	 * @return the asodim
	 */
	public String getAsodim() {
		return asodim;
	}

	/**
	 * @param asodim the asodim to set
	 */
	public void setAsodim(String asodim) {
		this.asodim = asodim;
	}

	/**
	 * @return the desasodim
	 */
	public String getdesasodim() {
		return desasodim;
	}

	/**
	 * @param desasodim the desasodim to set
	 */
	public void setdesasodim(String desasodim) {
		this.desasodim = desasodim;
	}

	/**
	 * @return the bcodgto
	 */
	public String getBcodgto() {
		return bcodgto;
	}

	/**
	 * @param bcodgto the bcodgto to set
	 */
	public void setBcodgto(String bcodgto) {
		this.bcodgto = bcodgto;
	}
	
	

	
	/**
	 * @return the list
	 */
	public List<Asociardim> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Asociardim> list) {
		this.list = list;
	}

	/**
	 * @return the filtro
	 */
	public List<Asociardim> getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<Asociardim> filtro) {
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
     * Inserta categoria3.
     * <p>
     * <b>Parametros del Metodo:<b> String b_codcat1, String b_codcat2 ,String codcat3, String descat3 unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void insert() throws  NamingException {

    		String[] veccoddim = bcoddim.split("\\ - ", -1);
    		String[] veccodgto = bcodgto.split("\\ - ", -1);
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
            con = ds.getConnection();
            
            String query = "INSERT INTO Bvt014 VALUES (?,?,?,?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, veccoddim[0].toUpperCase());
            pstmt.setString(2, asodim.toUpperCase());
            pstmt.setString(3, desasodim.toUpperCase());
            pstmt.setString(4, veccodgto[0].toUpperCase());
            pstmt.setString(5, login);
            pstmt.setString(6, login);
            //
            //System.out.println(veccoddim[0].toUpperCase());
            //System.out.println(asodim.toUpperCase());
            //System.out.println(desasodim.toUpperCase());
            //System.out.println(veccodgto[0].toUpperCase());
            //System.out.println(login);
            //System.out.println(login);
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
	
	        	String query = "DELETE Bvt014 WHERE b_coddim||asodim in (" + param + ")";
	        		        	
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
     * Actualiza categoria3
     * <b>Parametros del Metodo:<b> String b_codcat1, String b_codcat2, String codcat3,  String descat3 unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void update() throws  NamingException {
    		String[] veccoddim = bcoddim.split("\\ - ", -1);
    		String[] veccodgto = bcodgto.split("\\ - ", -1);
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();	
     		
            String query;
            if(bcodgto.equals(" - ")){
           	 query = "UPDATE Bvt014";
                query += " SET desasodim = ?, usract = ?, fecact='" + getFecha() + "'";
                query += " WHERE b_coddim = ? and asodim = ?";	
            }  else {
             query = "UPDATE Bvt014";
             query += " SET desasodim = ?, b_codgto = '" + veccodgto[0].toUpperCase() + "' , usract = ?, fecact='" + getFecha() + "'";
             query += " WHERE b_coddim = ? and asodim = ?";
            }
            ////System.out.println("GASTO : " + veccodgto[0].toUpperCase());
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, desasodim.toUpperCase());
            pstmt.setString(2, login);
            pstmt.setString(3, veccoddim[0].toUpperCase());
            pstmt.setString(4, asodim.toUpperCase());
            try {
                //Avisando
                pstmt.executeUpdate();
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
                }
          		desasodim = "";
          		bcodgto = " - ";
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
    * Leer Datos de categoria2
    * @throws NamingException 
 * @throws IOException 
    **/ 	
 	public void select() throws SQLException, ClassNotFoundException, NamingException {
 		Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);

 		con = ds.getConnection();	
 		
 		if(bcoddim==null){
			bcoddim = " - ";
 		}
 		if(bcoddim==""){
 			bcoddim = " - ";
 		}
 		bcodgto = " - ";
 		String[] veccoddim = bcoddim.split("\\ - ", -1);
 		
 		
 		//Consulta paginada
 		String query = " SELECT A.asodim, A.desasodim, A.B_codgto, decode(B.desgto,null,'N/A',B.desgto), a.b_coddim" ;
               query +=  " FROM bvt014 A, bvt013 B";
               query += " WHERE A.B_codgto=B.codgto(+)";
               query += " and A.asodim  like '" + asodim.toUpperCase() + "%'" ;
               query += " and A.B_CODDIM  like '" + veccoddim[0].toUpperCase() + "%'" ;
               query += " ORDER BY A.asodim" ;
       
       pstmt = con.prepareStatement(query);
       ////System.out.println(query);
 		
       r =  pstmt.executeQuery();

       
       while (r.next()){
    	Asociardim select = new Asociardim();
    	select.setvasodim(r.getString(1));
       	select.setvdesvasodim(r.getString(2));
       	select.setVbcodgto(r.getString(3));
       	select.setVbcodgtodesgto(r.getString(4));
       	select.setVbcoddim(r.getString(5));
       	//Agrega la lista
       	list.add(select);
       }
       //Cierra las conecciones
       pstmt.close();
       con.close();
       bcoddim = "";
       bcodgto = "";
 		
 	}
 	
   /**
  	 * @return the rows
  	 */
  	public int getRows() {
  		return rows;
  	}

  	private void limpiarValores() {
		// TODO Auto-generated method stub
  		asodim = "";
  		desasodim = "";
  		bcodgto = " - ";
  		validarOperacion = 0;
	}
  	



}
