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
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

	/**
	 *
	 * @author Andres
	 */
	@ManagedBean
	@ViewScoped
	public class Bvt003a extends Bd implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
	private LazyDataModel<Bvt003a> lazyModel;  
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Bvt003a> getLazyModel() {
		return lazyModel;
	}	
	
	@PostConstruct
	public void init() {
		lazyModel  = new LazyDataModel<Bvt003a>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7217573531435419432L;
			
            @Override
			public List<Bvt003a> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
            
            
            //Arregla bug de primefaces
            @Override
            /**
			 * Arregla el Issue 1544 de primefaces lazy loading porge generaba un error
			 * de divisor equal a cero, es solamente un override
			 */
            public void setRowIndex(int rowIndex) {
                /*
                 * The following is in ancestor (LazyDataModel):
                 * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
                 */
                if (rowIndex == -1 || getPageSize() == 0) {
                    super.setRowIndex(-1);
                }
                else
                    super.setRowIndex(rowIndex % getPageSize());
            }
            
		};
		//
		listrol = new HashMap<String,String>();
		selectBvt003();

	}
	

	
	private String codrol = "";
	private String coduser = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("bcoduser"); //Usuario logeado
	private Object filterValue = "";
	private List<Bvt003a> list = new ArrayList<Bvt003a>();
	private Map<String,String> listrol = new HashMap<String, String>();   //Lista de compañías disponibles para acceso a seguridad 
	private List<String> selectedBvt003;   //Listado de roles seleccionadas
	private Map<String, String> sorted;
	private int validarOperacion = 0;
	private int rows;
	private String exito = "exito";
	private String desrol = "";
	private String instancia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("instancia"); //Usuario logeado

	
	
	

	/**
	 * @return the desrol
	 */
	public String getDesrol() {
		return desrol;
	}

	/**
	 * @param desrol the desrol to set
	 */
	public void setDesrol(String desrol) {
		this.desrol = desrol;
	}

	/**
	 * @return the selectedBvt003
	 */
	public List<String> getSelectedBvt003() {
		return selectedBvt003;
	}

	/**
	 * @param selectedBvt003 the selectedBvt003 to set
	 */
	public void setSelectedBvt003(List<String> selectedBvt003) {
		this.selectedBvt003 = selectedBvt003;
	}

	/**
	 * @return the sorted
	 */
	public Map<String, String> getSorted() {
		return sorted;
	}

	/**
	 * @param sorted the sorted to set
	 */
	public void setSorted(Map<String, String> sorted) {
		this.sorted = sorted;
	}

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

		/**
	 * @return the codrol
	 */
	public String getCodrol() {
		return codrol;
	}
	
	/**
	 * @param codrol the codrol to set
	 */
	public void setCodrol(String codrol) {
		this.codrol = codrol;
	}
	
	
	

	/**
	 * @return the coduser
	 */
	public String getCoduser() {
		return coduser;
	}

	/**
	 * @param coduser the coduser to set
	 */
	public void setCoduser(String coduser) {
		this.coduser = coduser;
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
	PntGenerica consulta = new PntGenerica();
	boolean vGacc; //Validador de opciones del menú
	private String login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); //Usuario logeado
	FacesMessage msj =  null;
	
	
	//Coneccion a base de datos
	//Pool de conecciones JNDI
	//Coneccion a base de datos
	//Pool de conecciones JNDIFARM
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;

	/**
     * Inserta roles adicionales.
     * <p>
     * <b>Parametros del Metodo:<b> String codrol, String desrol unidos como un solo string.<br>
     * String pool, String login.<br><br>
     * <b>Ejemplo:</b>insertBvt003("01|EJEMPLO","jdbc/opennomina","admin").
     **/
    private void insert(String prol) throws  NamingException {
    	//Valida que los campos no sean nulos
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();		
     		
            String query = "INSERT INTO Bvt003a VALUES (?,?,?,'" + getFecha() + "',?,'" + getFecha() + "',?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, prol.toUpperCase());
            pstmt.setString(2, coduser.split(" - ")[0].toUpperCase());
            pstmt.setString(3, login);
            pstmt.setString(4, login);
            pstmt.setInt(5, Integer.parseInt(instancia));
            ////System.out.println(query);
            try {
                //Avisando
                pstmt.executeUpdate();           
             } catch (SQLException e)  {
            	 exito = "error";
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            	FacesContext.getCurrentInstance().addMessage(null, msj);
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
     		
     		String query = "DELETE from Bvt003a WHERE coduser||codrol in (" + param + ") and instancia = '" + instancia + "'";
            pstmt = con.prepareStatement(query);
            //System.out.println(query);
            //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
            
            try {
                pstmt.executeUpdate();
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
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
    
    
    
  
    public void guardar() throws NamingException, SQLException, ClassNotFoundException{   	
    	if (selectedBvt003.size()<=0){
    		msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("acccat2IngUsr"), "");
    		FacesContext.getCurrentInstance().addMessage(null, msj);
    	} else {  	
    	   for (int i = 0; i< selectedBvt003.size(); i++){
    		  //System.out.println("Selected :" + selectedBvt002.get(i));
    		insert(selectedBvt003.get(i));           
    	   }
   		limpiarValores();   
        if(exito=="exito"){
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
        	FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    	}
    }
    
    /**
     * Leer Datos de paises
     * @throws NamingException 
     * @throws IOException 
     **/ 	
  	public void select(int first, int pageSize, String sortField, Object filterValue) throws SQLException, ClassNotFoundException, NamingException {
  		Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);
 		con = ds.getConnection();
 		//Reconoce la base de datos de conección para ejecutar el query correspondiente a cada uno
 		DatabaseMetaData databaseMetaData = con.getMetaData();
 		productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección
 		//System.out.println("coduser :" + coduser);
 		if(coduser==null){
 			coduser = " - ";
 		}
 		String[] vecuser = coduser.split("\\ - ", -1);
 		
 		String query = "";

  		switch ( productName ) {
        case "Oracle":
        	   query += "  select * from ";
        	   query += " ( select query.*, rownum as rn from";
        	   query += " (SELECT trim(coduser), trim(b.desrol), trim(a.codrol)";
        	   query += " FROM BVT003a a, bvt003 b";
        	   query += " where a.codrol = b.codrol";
        	   query += " and a.coduser||b.desrol like '%" + ((String) filterValue).toUpperCase() + "%'";
        	   query += " and a.coduser like '" + vecuser[0].toUpperCase() + "%'";
        	   query += " AND a.instancia = '" + instancia + "'";
	  		   query += " order by " + sortField + ") query";
	           query += " ) where rownum <= " + pageSize ;
	           query += " and rn > (" + first + ")";
             break;
        case "PostgreSQL":
	           query += " SELECT trim(coduser), trim(b.desrol), trim(a.codrol)";
	      	   query += " FROM BVT003a a, bvt003 b";
	      	   query += " where a.codrol = b.codrol";
	      	   query += " and a.coduser||b.desrol like '%" + ((String) filterValue).toUpperCase() + "%'";
        	   query += " and a.coduser like '" + vecuser[0].toUpperCase() + "%'";
        	   query += " AND a.instancia = '" + instancia + "'";
	  		   query += " order by " + sortField ;
	           query += " LIMIT " + pageSize;
	           query += " OFFSET " + first;
             break;
        case "Microsoft SQL Server":
               query += " SELECT * ";
               query += " FROM (SELECT ";
               query += "       ROW_NUMBER() OVER (ORDER BY A.CODROL ASC) AS ROW_NUM, ";         
     	       query += "       A.CODROL, ";
     	       query += "	    A.coduser ";
     	       query += " 		FROM BVT003 A";
     	       query += " 		WHERE A.CODROL + DESROL LIKE '%" + ((String) filterValue).toUpperCase() + "%') TOT";
     	       query += "       AND a.instancia = '" + instancia + "'";
	  	       query += " WHERE ";
	  	       query += " TOT.ROW_NUM <= " + pageSize;
	           query += " AND TOT.ROW_NUM > " + first;
	           query += " ORDER BY " + sortField ;
          break;
  		}

        
        pstmt = con.prepareStatement(query);
        //System.out.println(query);
  		
        r =  pstmt.executeQuery();
        
        
        while (r.next()){
     	Bvt003a select = new Bvt003a();
     	select.setDesrol(r.getString(2));
        select.setCoduser(r.getString(1));
        select.setCodrol(r.getString(3));
        	//Agrega la lista
        	list.add(select);
        }
        //Cierra las conecciones
        coduser = null;
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
 		
 		if(coduser==null){
 			coduser = " - ";
 		}
 		String[] vecuser = coduser.split("\\ - ", -1);
  		
  		switch ( productName ) {
        case "Oracle":
        	 query = "SELECT count_bvt003a('" + ((String) filterValue).toUpperCase() + "','" + vecuser[0] + "','" + instancia + "') from dual";
             break;
        case "PostgreSQL":
        	 query = "SELECT count_bvt003a('" + ((String) filterValue).toUpperCase() + "','" + vecuser[0] + "','" + instancia + "')";
             break;
        case "Microsoft SQL Server":
       	 query = "SELECT DBO.count_bvt003a('" + ((String) filterValue).toUpperCase() + "','" + vecuser[0] + "','" + instancia + "')";
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
        coduser = null;
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
    	codrol = "";
    	coduser = "";
    	validarOperacion = 0;
	}
  	
  	
  	/**
     * Leer Datos de nominas para asignar a menucheck
     * @throws NamingException 
	 * @throws SQLException 
     * @throws IOException 
     **/ 	
  	private void selectBvt003()  {
  		try {
  		Context initContext = new InitialContext();     
    	DataSource ds = (DataSource) initContext.lookup(JNDI);
        con = ds.getConnection();
 
    	//Reconoce la base de datos de conección para ejecutar el query correspondiente a cada uno
  		DatabaseMetaData databaseMetaData = con.getMetaData();
  		productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección
  		
  		String query = "";
  		
  		if(coduser==null){
 			coduser = " - ";
 		}
 		String[] vecuser = coduser.split("\\ - ", -1);

  		switch ( productName ) {
  		case "Oracle":
        	query = "Select trim(codrol), codrol||' - '||desrol";
            query += " from bvt003";
            query += " where codrol not in (select b_codrol from bvt002 where coduser = '" + vecuser[0].toUpperCase() + "')";
            query += " AND instancia = '" + instancia + "'";
            query += " order by codrol";
	        break;
  		case "PostgreSQL":
  			query = "Select trim(codrol), codrol||' - '||desrol";
            query += " from bvt003";
            query += " where codrol not in (select b_codrol from bvt002 where coduser = '" + vecuser[0].toUpperCase() + "')";
            query += " AND instancia = '" + instancia + "'";
            query += " order by codrol";
	        break;
  		case "Microsoft SQL Server":
  			query = "Select codrol, codrol||' - '||desrol";
            query += " from bvt003";
            query += " where codrol not in (select b_codrol from bvt002 where coduser ? '" + vecuser[0].toUpperCase() + "')";
            query += " AND instancia = '" + instancia + "'";
            query += " order by codrol";
	        break;
	        }        


        //System.out.println(query);

        
        pstmt = con.prepareStatement(query);
        ////System.out.println(query);
  		
        r =  pstmt.executeQuery();
        
  		
        
        while (r.next()){
        	String codrol = new String(r.getString(1));
        	String desrol = new String(r.getString(2));
        	
            listrol.put(desrol, codrol);
            sorted = sortByValues(listrol);
            
        }
        //Cierra las conecciones
        coduser = null;
        pstmt.close();
        con.close();
  		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
  	}

  	public void reset() {
  		coduser = "";    
    }
  	


}
