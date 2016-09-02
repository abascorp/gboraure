/*
 *  Copyright (C) 2011 - 2016  DVCONSULTORES

    Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */

package org.openbizview.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.openbizview.util.PntGenerica;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

	/**
	 *
	 * @author Andres
	 */
	@ManagedBean
	@ViewScoped
	public class Bvt001A extends Bd implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		private LazyDataModel<Bvt001A> lazyModel;  
		
		
		/**
		 * @return the lazyModel
		 */
		public LazyDataModel<Bvt001A> getLazyModel() {
			return lazyModel;
		}	
	
		@PostConstruct
		public void init() {
			if (instancia == null){instancia = "99999";}
			//Si no tiene acceso al módulo no puede ingresar
			if (new SeguridadMenuBean().opcmnu("R01")=="false") {
				RequestContext.getCurrentInstance().execute("PF('idleDialogNP').show()");
			}
			
			lazyModel  = new LazyDataModel<Bvt001A>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 7217573531435419432L;
				
	            @Override
				public List<Bvt001A> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
	            	//Filtro
	            	if (filters != null) {
	               	 for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
	               		 String filterProperty = it.next(); // table column name = field name
	                     filterValue = filters.get(filterProperty);
	               	 }
	                }
	            	try { 
	            		//Consulta
						select(first, pageSize,sortField, filterValue);
						//Counter
						counter(filterValue);
						//Contador lazy
						lazyModel.setRowCount(rows);  //Necesario para crear la paginación
					} catch (SQLException | NamingException | ClassNotFoundException e) {	
						e.printStackTrace();
					}             
					return list;  
	            } 
	            
	            
	            
	            
			};
		}
		
	
	private String codgrup = "";
	private String desgrup = "";
	private List<Bvt001A> list = new ArrayList<Bvt001A>();
	private Object filterValue = "";
	private int validarOperacion = 0;
	private String instancia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("instancia"); //Usuario logeado

	
	
	/**
	 * @return the codgrup
	 */
	public String getcodgrup() {
		return codgrup;
	}
	/**
	 * @param codgrup the codgrup to set
	 */
	public void setcodgrup(String codgrup) {
		this.codgrup = codgrup;
	}
	/**
	 * @return the desgrup
	 */
	public String getdesgrup() {
		return desgrup;
	}
	/**
	 * @param desgrup the desgrup to set
	 */
	public void setdesgrup(String desgrup) {
		this.desgrup = desgrup;
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
	 * @return the list
	 */
	public List<Bvt001A> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Bvt001A> list) {
		this.list = list;
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
     * <b>Parametros del Metodo:<b> String codgrup1, String desgrup1 unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void insert() throws  NamingException {   	
       		
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
            con = ds.getConnection();
            DatabaseMetaData databaseMetaData = con.getMetaData();
     		productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección
            
            String query = "INSERT INTO Bvt001A VALUES (?,?,?," + getFecha(productName) + ",?," + getFecha(productName) + ",?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, codgrup.toUpperCase());
            pstmt.setString(2, desgrup.toUpperCase());
            pstmt.setString(3, login);
            pstmt.setString(4, login);
            pstmt.setInt(5, Integer.parseInt(instancia));
            //System.out.println(query);
            try {
                //Avisando
            	pstmt.executeUpdate();
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
                limpiarValores();

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
	
	        	String query = "DELETE from Bvt001A WHERE codgrup in (" + param + ") and instancia = '" + instancia + "'";
	        		        	
	            pstmt = con.prepareStatement(query);
	            ////System.out.println(query);
	
	            try {
	                //Avisando
	                pstmt.executeUpdate();
	                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
	                list.clear();	
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
     * <b>Parametros del Metodo:<b> String codgrup1, String desgrup1 unidos como un solo string.<br>
     * String pool, String login.<br><br>
     **/
    public void update() throws  NamingException {
         try {
        	 Context initContext = new InitialContext();     
      		DataSource ds = (DataSource) initContext.lookup(JNDI);

      		con = ds.getConnection();
      		DatabaseMetaData databaseMetaData = con.getMetaData();
     		productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección
      		
            String query = "UPDATE Bvt001A";
             query += " SET desgrup = ?, usract = ?, fecact=" + getFecha(productName) + "";
             query += " WHERE codgrup = ? and instancia = '" + instancia + "'";
           // //System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, desgrup.toUpperCase());
            pstmt.setString(2, login);
            pstmt.setString(3, codgrup.toUpperCase());
            // Antes de ejecutar valida si existe el registro en la base de Datos.
            try {
                //Avisando
                pstmt.executeUpdate();
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
                }
                desgrup = "";
            	validarOperacion = 0;
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
 	public void select(int first, int pageSize, String sortField, Object filterValue) throws SQLException, ClassNotFoundException, NamingException  {
 		
 		Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);
 		con = ds.getConnection();	
 		//Reconoce la base de datos de conección para ejecutar el query correspondiente a cada uno
 		DatabaseMetaData databaseMetaData = con.getMetaData();
 		productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección
 		//System.out.println(productName);
 		String query = "";

  		switch ( productName ) {
        case "Oracle":
        	   query += "  select * from ";
        	   query += " ( select query.*, rownum as rn from";
        	   query += " (SELECT trim(codgrup), trim(desgrup)";
        	   query += " FROM BVT001A";
               query += " WHERE CODGRUP||desgrup like '%" + ((String) filterValue).toUpperCase() + "%'";
               query += " AND   codgrup like '" + codgrup + "%'";
               query += " AND   instancia = '" + instancia + "'";
	  		   query += " order by " + sortField + ") query";
	           query += " ) where rownum <= " + pageSize ;
	           query += " and rn > (" + first + ")";
        break;
        case "PostgreSQL":
        	   query += " SELECT trim(codgrup), trim(desgrup)";
               query += " FROM BVT001A";
               query += " WHERE CODGRUP||desgrup like '%" + ((String) filterValue).toUpperCase() + "%'";
               query += " AND   codgrup like '" + codgrup + "%'";
               query += " AND   instancia = '" + instancia + "'";
               query += " order by " + sortField ;
	           query += " LIMIT " + pageSize;
	           query += " OFFSET " + first;
             break;
        case "Microsoft SQL Server":
	           query += " SELECT ltrim(rtrim(codgrup)), ltrim(rtrim(desgrup))";
	           query += " FROM BVT001A";
	           query += " WHERE CODGRUP+desgrup like '%" + ((String) filterValue).toUpperCase() + "%'";
	           query += " AND   codgrup like '" + codgrup + "%'";
	           query += " AND   instancia = '" + instancia + "'";
               query += " order by " + sortField ;
	           query += " OFFSET " + first + " ROWS";
	           query += " FETCH NEXT " + pageSize + " ROWS ONLY";
        break;
        }
 		
 		       
       
       pstmt = con.prepareStatement(query);
       //System.out.println(query);
 		
       r =  pstmt.executeQuery();
       
       while (r.next()){
    	Bvt001A select = new Bvt001A();
    	select.setcodgrup(r.getString(1));
    	select.setdesgrup(r.getString(2));
       	
       	//Agrega la lista
       	list.add(select);
       }
       //Cierra las conecciones
       pstmt.close();
       con.close();

 	}
 	
 	/**
     * Leer registros en la tabla
     * @throws NamingException 
     * @throws IOException 
     **/ 	
  	public void counter(Object filterValue ) throws SQLException, NamingException {
     try {	
    	Context initContext = new InitialContext();     
   		DataSource ds = (DataSource) initContext.lookup(JNDI);
   		con = ds.getConnection();
   	   //Reconoce la base de datos de conección para ejecutar el query correspondiente a cada uno
 		DatabaseMetaData databaseMetaData = con.getMetaData();
 		productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección
   	      		
 		String query = "";
  		
  		switch ( productName ) {
        case "Oracle":
        	 query = "SELECT count_bvt001a('" + ((String) filterValue).toUpperCase() + "','" + instancia + "') from dual";
             break;
        case "PostgreSQL":
        	 query = "SELECT count_bvt001a('" + ((String) filterValue).toUpperCase() + "','" + instancia + "')";
             break;
        case "Microsoft SQL Server":
       	 query = "SELECT DBO.count_bvt001a('" + ((String) filterValue).toUpperCase() + "','" + instancia + "')";
            break;
  		}

        
        pstmt = con.prepareStatement(query);
        //System.out.println(query);

         r =  pstmt.executeQuery();
        
        
        while (r.next()){
        	rows = r.getInt(1);
        }
     } catch (SQLException e){
         e.printStackTrace();    
     }
        //Cierra las conecciones
        pstmt.close();
        con.close();
        r.close();

  	}
  	
   
   /**
  	 * @return the rows
  	 */
  	public int getRows() {
  		return rows;
  	}

  	private void limpiarValores() {
		// TODO Auto-generated method stub
  		codgrup = "";
  		desgrup = "";
  		validarOperacion = 0;
	}



}
