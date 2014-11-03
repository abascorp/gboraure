/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
	public class Bvt002 extends Bd implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		private LazyDataModel<Bvt002> lazyModel;  
		
		
		
		
	    /**
		 * @return the lazyModel
		 */
		public LazyDataModel<Bvt002> getLazyModel() {
			return lazyModel;
		}



	public Bvt002() throws ClassNotFoundException, SQLException, NamingException{
		lazyModel  = new LazyDataModel<Bvt002>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7217573531435419432L;
			
            @Override
			public List<Bvt002> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
	}
	
	private String coduser = "";
	private String desuser = "";
	private String cluser = "";
	private String cluser1 = "";
	private String b_codrol = "";
	private String desrol = "";
	private String cluserold = "";
	private int columns;
	private String[][] arr;
	private Object filterValue = "";
	private List<Bvt002> list = new ArrayList<Bvt002>();
	private int validarOperacion = 0;
	
	
	
	     /**
	 * @return the cluser1
	 */
	public String getCluser1() {
		return cluser1;
	}

	/**
	 * @param cluser1 the cluser1 to set
	 */
	public void setCluser1(String cluser1) {
		this.cluser1 = cluser1;
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
	 * @return the desuser
	 */
	public String getDesuser() {
		return desuser;
	}
	
	/**
	 * @param desuser the desuser to set
	 */
	public void setDesuser(String desuser) {
		this.desuser = desuser;
	}
	
	/**
	 * @return the cluser
	 */
	public String getCluser() {
		return cluser;
	}
	
	/**
	 * @param cluser the cluser to set
	 */
	public void setCluser(String cluser) {
		this.cluser = cluser;
	}
	
	/**
	 * @return the b_codrol
	 */
	public String getb_codrol() {
		return b_codrol;
	}
	
	/**
	 * @param b_codrol the b_codrol to set
	 */
	public void setb_codrol(String b_codrol) {
		this.b_codrol = b_codrol;
	}
	
	
	
	/**
	 * @return the cluserold
	 */
	public String getCluserold() {
		return cluserold;
	}
	
	/**
	 * @param cluserold the cluserold to set
	 */
	public void setCluserold(String cluserold) {
		this.cluserold = cluserold;
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
     * Inserta Usuarios.
     * <p>
     * Parámetros del Método: String coduser, String desuser, String clave, String b_codrol.
     **/
    public void insert() throws  NamingException {
    	//Valida que los campos no sean nulos
    		String[] veccodrol = b_codrol.split("\\ - ", -1);
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
            con = ds.getConnection();
            
            String query = "INSERT INTO Bvt002 VALUES (?,?,?,?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, coduser.toUpperCase());
            pstmt.setString(2, desuser.toUpperCase());
            pstmt.setString(3, cluser.toUpperCase());
            pstmt.setString(4, veccodrol[0].toUpperCase());
            pstmt.setString(5, login);
            pstmt.setString(6, login);

            ////System.out.println(query);
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
	
	        	String query = "DELETE from Bvt002 WHERE coduser in (" + param + ")";
	        		        	
	            pstmt = con.prepareStatement(query);
	            ////System.out.println(query);
	
	            try {
	                //Avisando
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
    
  

       /**
     * Actualiza Usuarios
     * <p> Parámetros del Método: String coduser, String desuser, String cluser, String p_codrol.
     **/
    public void update() throws  NamingException {
    
    		String[] veccodrol = b_codrol.split("\\ - ", -1);
        String[] st = new String[2];
        st[0] = " ,CLUSER = '" + cluser.toUpperCase() + "'";
        try {

        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();		
     		
            String query = "UPDATE Bvt002";
             query += " SET DESUSER = ?";
               if(!cluser.equals(""))
                   query += st[0];
             query += " , B_CODROL = ?";
             query += " WHERE CODUSER = ?";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, desuser.toUpperCase());
            pstmt.setString(2, veccodrol[0].toUpperCase());
            pstmt.setString(3, coduser.toUpperCase());

            try {
                //Avisando
            	pstmt.executeUpdate();
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
                }
                desuser = "";
           		cluser = "";
           		b_codrol = " - ";
           		cluserold = "";
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
     * Actualiza Usuarios
     * <p> Parámetros del Método: String coduser, String desuser, String cluser, String p_codrol.
     **/
    public void updatea() throws  NamingException {
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();	
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //        getUrl(), getUsuario(), getClave());
            String query = "UPDATE Bvt002";
             query += " SET CLUSER = ?";
             query += " WHERE CODUSER = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, cluser.toUpperCase());
            pstmt.setString(2, login.toUpperCase());
            try {
            	if(!cluser.equals(cluser1)){
            		 msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,  getMessage("bvt002Cl1Msj"), "");
            	} else {
                //Avisando
                pstmt.executeUpdate();
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("bvt002up"), "");
            	}
            } catch (SQLException e) {
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
 		
 		
 		String query = "";

  		switch ( productName ) {
        case "Oracle":
        	   query += "  select * from ";
        	   query += " ( select query.*, rownum as rn from";
        	   query += " (SELECT trim(A.CODUSER) , trim(A.DESUSER), trim(A.CLUSER), trim(A.B_CODROL), trim(B.DESROL)";
        	   query += " FROM Bvt002 A, BVT003 B " ;
        	   query += " WHERE A.B_CODROL=B.CODROL" ;
        	   query += " AND A.CODUSER||A.DESUSER like '%" + ((String) filterValue).toUpperCase() + "%'";
	  		   query += " order by " + sortField + ") query";
	           query += " ) where rownum <= " + pageSize ;
	           query += " and rn > (" + first + ")";
             break;
        case "PostgreSQL":
			   query += " SELECT trim(A.CODUSER) , trim(A.DESUSER), trim(A.CLUSER), trim(A.B_CODROL), trim(B.DESROL)";
			   query += " FROM Bvt002 A, BVT003 B" ;
			   query += " WHERE A.B_CODROL=B.CODROL " ;
			   query += " and A.CODUSER||A.DESUSER like '%" + ((String) filterValue).toUpperCase() + "%'";
			   query += " order by " + sortField ;
			   query += " LIMIT " + pageSize;
			   query += " OFFSET " + first;
             break;
        case "Microsoft SQL Server":
			   query += "SELECT * FROM (SELECT ";
			   query += "			   ROW_NUMBER() OVER (ORDER BY A.CODUSER ASC) AS ROW_NUM, ";
			   query += "			   A.CODUSER , A.DESUSER, A.CLUSER, A.B_CODROL, B.DESROL ";
			   query += "			   FROM Bvt002 A, BVT003 B ";
			   query += "			   WHERE A.B_CODROL=B.CODROL) TOT ";
			   query += "WHERE  ";
			   query += "TOT.CODUSER + TOT.DESUSER LIKE '%" + ((String) filterValue).toUpperCase() + "%')) ";
			   query += "AND TOT.ROW_NUM <= " + pageSize;
			   query += "AND TOT.ROW_NUM > " + first; 
			   query += "ORDER BY " + sortField;        		
        }

        
        pstmt = con.prepareStatement(query);
        //System.out.println(query);
  		
        r =  pstmt.executeQuery();
        
        while (r.next()){
        Bvt002 select = new Bvt002();
     	select.setCoduser(r.getString(1));
     	select.setDesuser(r.getString(2));
        select.setCluser(r.getString(3));
        select.setb_codrol(r.getString(4) + " - " + r.getString(5));
        select.setDesrol(r.getString(5));
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
        	 query = "SELECT count_bvt002('" + ((String) filterValue).toUpperCase() + "') from dual";
             break;
        case "PostgreSQL":
        	 query = "SELECT count_bvt002('" + ((String) filterValue).toUpperCase() +  "')";
             break;
        case "Microsoft SQL Server":
       	 query = "SELECT DBO.count_bvt002('" + ((String) filterValue).toUpperCase() +  "')";
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
     * Leer datos de Usuarios
     *<p> Parámetros del Método: String coduser, String desuser.
      * * Fila desde y hasta para paginación, orden de la consulta.
     * @throws NamingException 
     * @throws IOException 
     **/

    @SuppressWarnings("null")
	public void  selectBvt002a(String usuario, String orden, String pool) throws NamingException  {

        //Pool de conecciones JNDI. Cambio de metodología de conexión a bd. Julio 2010
        Context initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup(JNDI);
        try {
            Statement stmt = null;
            ResultSet rs;
            Connection con = ds.getConnection();
            
            
            String query = "SELECT CODUSER , DESUSER" +
           " FROM Bvt002" +
           " WHERE CODUSER = '" + usuario.toUpperCase() + "'" +
           " ORDER BY " + orden ;
            //System.out.println(query);
            try{
            rs = stmt.executeQuery(query);
            rows = 1;
 		    rs.last();
 		    rows = rs.getRow();
            //System.out.println(rows);

            ResultSetMetaData rsmd = rs.getMetaData();
        	columns = rsmd.getColumnCount();
 		    //System.out.println(columns);
        	arr = new String[rows][columns];

            int i = 0;
 		    rs.beforeFirst();
            while (rs.next()){
                for (int j = 0; j < columns; j++)
 				arr [i][j] = rs.getString(j+1);
 				i++;
               }
                    } catch (SQLException e) {
                    e.printStackTrace();
                }
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     /**
      * Leer Datos de Usuarios
      *<p> Parametros del Metodo: String coduser, String desuser.
      * String pool
     * @throws IOException 
      **/
     public void selectLogin(String user, String pool) throws NamingException {

         //Pool de conecciones JNDI. Cambio de metodologia de conexion a Bd. Julio 2010
         Context initContext = new InitialContext();
         DataSource ds = (DataSource) initContext.lookup(JNDI);
         try {
             Statement stmt;
             ResultSet rs;
             Connection con = ds.getConnection();
           //Reconoce la base de datos de conección para ejecutar el query correspondiente a cada uno
      		DatabaseMetaData databaseMetaData = con.getMetaData();
      		productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección
             //Class.forName(getDriver());
             //con = DriverManager.getConnection(
             //        getUrl(), getUsuario(), getClave());
             stmt = con.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             String query = "";
             
             System.out.println( productName );
             
             switch ( productName ) {
             case "Oracle":
            	 query = "SELECT trim(coduser), trim(cluser), trim(B_CODROL), trim(desuser)";
                 query += " FROM BVT002";
                 query += " where coduser = '" + user.toUpperCase() + "'";
                  break;
             case "PostgreSQL":
            	 query = "SELECT trim(coduser), trim(cluser), trim(B_CODROL), trim(desuser)";
                 query += " FROM BVT002";
                 query += " where coduser = '" + user.toUpperCase() + "'";
                  break;
             case "Microsoft SQL Server":
            	 query = "SELECT coduser, cluser, B_CODROL, desuser";
                 query += " FROM BVT002";
                 query += " where coduser = '" + user.toUpperCase() + "'";
     	         break;            		   
       		}
       		
             
             System.out.println(query);
             try {
                 rs = stmt.executeQuery(query);
                 rows = 1;
                 rs.last();
                 rows = rs.getRow();
                 //System.out.println(rows);

                 ResultSetMetaData rsmd = rs.getMetaData();
                 columns = rsmd.getColumnCount();
                 //System.out.println(columns);
                 arr = new String[rows][columns];

                 int i = 0;
                 rs.beforeFirst();
                 while (rs.next()) {
                     for (int j = 0; j < columns; j++) {
                         arr[i][j] = rs.getString(j + 1);
                     }
                     i++;
                 }
             } catch (SQLException e) {
             }
             stmt.close();
             con.close();

         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     /**
      * @return Retorna el arreglo
      **/
     public String[][] getArray(){
     	return arr;
     }

     /**
      * @return Retorna número de filas
      **/
     public int getRows(){
     	return rows;
     }
     /**
      * @return Retorna número de columnas
      **/
     public int getColumns(){
     	return columns;
     }

   	private void limpiarValores() {
 		// TODO Auto-generated method stub
   		coduser = "";
   		desuser = "";
   		cluser = "";
   		b_codrol = " - ";
   		cluserold = "";
 	}


}
