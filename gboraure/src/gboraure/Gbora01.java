/*
 *  Copyright (C) 2017  MAURICIO ALBANESE

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
    bajo los terminos de la Licencia P√∫blica General GNU publicada 
    por la Fundacion para el Software Libre, ya sea la version 3 
    de la Licencia, o (a su eleccion) cualquier version posterior.

    Este programa se distribuye con la esperanza de que sea √∫til, pero 
    SIN GARANTiA ALGUNA; ni siquiera la garantia implicita 
    MERCANTIL o de APTITUD PARA UN PROPoSITO DETERMINADO. 
    Consulte los detalles de la Licencia P√∫blica General GNU para obtener 
    una informacion mas detallada. 

    Deberia haber recibido una copia de la Licencia P√∫blica General GNU 
    junto a este programa. 
    En caso contrario, consulte <http://www.gnu.org/licenses/>.
 */

package gboraure;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import gboraure.LoginBean;

import gboraure.PntGenerica;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
/**
 *
 * @author Mauricio
 */
@ManagedBean
@ViewScoped
public class Gbora01 extends Bd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Gbora01> lazyModel;  
	
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Gbora01> getLazyModel() {
		return lazyModel;
	}	

@PostConstruct
public void init() {
	
	if(login==null){
		//RequestContext.getCurrentInstance().execute("$('#myModalTimeout').modal()");	
	try {
		new LoginBean().logout();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	} 
	
	//System.out.println("entre al metodo INIT");
	lazyModel  = new LazyDataModel<Gbora01>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 7217573531435419432L;
		
        @Override
		public List<Gbora01> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
        	//Filtro
        	if (filters != null) {
           	 for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
           		 String filterProperty = it.next(); // table column name = field name
                 filterValue = filters.get(filterProperty);
           	 }
            }
        	try { 
        		//Consulta
				select(first, pageSize+first, filterValue);
				//Counter
				counter(filterValue);
				//Contador lazy
				rows = list1.size();
				lazyModel.setRowCount(rows);  //Necesario para crear la paginaci√≥n
		    	//System.out.println("lista: " + list1.size());
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

	/**
	 * 
	 */
	private String codgra = "";
	private String desgra = "";
	private String numrif = "";
	private String numtel = "";
	private String direc1 = "";
	private String direc2 = "";
	private String paigra = "";
	private String edogra = "";
	private Object filterValue = "";
	private List<Gbora01> list = new ArrayList<Gbora01>();
	private List<Gbora01> list1 = new ArrayList<Gbora01>();
	private int validarOperacion = 0;
	private String zcodgra = "";
	private String zdesgra = "";
	private String znumrif = "";
	private String znumtel = "";
	private String zdirec1 = "";
	private String zdirec2 = "";
	private String zpaigra = "";
	private String zedogra = "";
	private String zdelete = "";
	//AUTOCOMPLETES//
	PntGenericaAuto consulta = new PntGenericaAuto();
	String[][] tabla;

	public String getCodgra() {
		return codgra;
	}

	public void setCodgra(String codgra) {
		this.codgra = codgra;
	}

	public String getDesgra() {
		return desgra;
	}

	public void setDesgra(String desgra) {
		this.desgra = desgra;
	}

	public String getNumrif() {
		return numrif;
	}

	public void setNumrif(String numrif) {
		this.numrif = numrif;
	}

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	public String getDirec1() {
		return direc1;
	}

	public void setDirec1(String direc1) {
		this.direc1 = direc1;
	}

	public String getDirec2() {
		return direc2;
	}

	public void setDirec2(String direc2) {
		this.direc2 = direc2;
	}

	public String getPaigra() {
		return paigra;
	}

	public void setPaigra(String paigra) {
		this.paigra = paigra;
	}

	public String getEdogra() {
		return edogra;
	}

	public void setEdogra(String edogra) {
		this.edogra = edogra;
	}

	public String getZcodgra() {
		return zcodgra;
	}

	public void setZcodgra(String zcodgra) {
		this.zcodgra = zcodgra;
	}

	public String getZdesgra() {
		return zdesgra;
	}

	public void setZdesgra(String zdesgra) {
		this.zdesgra = zdesgra;
	}

	public String getZnumrif() {
		return znumrif;
	}

	public void setZnumrif(String znumrif) {
		this.znumrif = znumrif;
	}

	public String getZnumtel() {
		return znumtel;
	}

	public void setZnumtel(String znumtel) {
		this.znumtel = znumtel;
	}

	public String getZdirec1() {
		return zdirec1;
	}

	public void setZdirec1(String zdirec1) {
		this.zdirec1 = zdirec1;
	}

	public String getZdirec2() {
		return zdirec2;
	}

	public void setZdirec2(String zdirec2) {
		this.zdirec2 = zdirec2;
	}

	public String getZpaigra() {
		return zpaigra;
	}

	public void setZpaigra(String zpaigra) {
		this.zpaigra = zpaigra;
	}

	public String getZedogra() {
		return zedogra;
	}

	public void setZedogra(String zedogra) {
		this.zedogra = zedogra;
	}

	public String getZdelete() {
		return zdelete;
	}

	public void setZdelete(String zdelete) {
		this.zdelete = zdelete;
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
	
	//Formateador de la fecha sdfecha
    //java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat("dd/MM/yyyy", locale);
    //String fecha = sdfecha.format(fecact); //Fecha formateada para insertar en tablas


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
	FacesMessage msj = null;
	PntGenerica consulta1 = new PntGenerica();
	boolean vGacc; //Validador de opciones del men√∫
	private int rows; //Registros de tabla Sybase
	//private int rows1; //Registros de tabla oracle
	private String login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); //Usuario logeado
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//Coneccion a base de datos
	//Pool de conecciones JNDI
		Connection con;
		PreparedStatement pstmt = null;
		ResultSet r;


/**
 * Inserta GBORA01.
 * <p>
 * <b>Parametros del Metodo:<b> String codcat1, String descat1 unidos como un solo string.<br>
 * String pool, String login.<br><br>
 **/
public void insert() throws  NamingException {   	
	//System.out.println("entre al metodo INSERT");	
    try {
    	Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);
        con = ds.getConnection();
                                    
        String query = "INSERT INTO GBORA01 VALUES (?,?,?,?,?,?,?,?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1,codgra.toUpperCase());
        pstmt.setString(2,desgra.toUpperCase());
        pstmt.setString(3,numrif.toUpperCase());
        pstmt.setString(4,numtel.toUpperCase());
        pstmt.setString(5,direc1.toUpperCase());
        pstmt.setString(6,direc2.toUpperCase());
        pstmt.setString(7,paigra);
        pstmt.setString(8,edogra);
        pstmt.setString(9, login);
        pstmt.setString(10, login);            
        
        //System.out.println(query);

        try {
        	//System.out.println("entre al try");	
            //Avisando
        	pstmt.executeUpdate();
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
            limpiarValores();                
         } catch (SQLException e)  {
        	 //System.out.println("entre al catch");	
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
 * Elimina GBORA01
 * <b>Parametros del Metodo:<b> String codcat1, String descat1 unidos como un solo string.<br>
 * String pool, String login.<br><br>
 **/
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

        	String query = "DELETE FROM GBORA01 WHERE CODGRA IN (" + param + ")";
        		        	
            pstmt = con.prepareStatement(query);
            //System.out.println(query);

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
 * Actualiza GBORA01
 * <b>Parametros del Metodo:<b> String codcat1, String descat1 unidos como un solo string.<br>
 * String pool, String login.<br><br>
 **/
public void update() throws  NamingException {
	//System.out.println("entre al metodo UPDATE");

     try { 	 
    	Context initContext = new InitialContext();     
  		DataSource ds = (DataSource) initContext.lookup(JNDI);	
  		con = ds.getConnection();
  		 		
        String query = "UPDATE GBORA01";
         query += " SET DESGRA = ?, ";
         query += " NUMRIF = ?,";
         query += " NUMTEL = ?,";
         query += " DIREC1 = ?,";
         query += " DIREC2 = ?,";
         query += " PAIGRA = ?,";
         query += " EDOGRA = ?,";
         query += " USRACT = ?,";
         query += " FECACT = '" + getFecha() + "'";
         query += " WHERE CODGRA = ? ";
         
         //System.out.println(query);
         
        pstmt = con.prepareStatement(query);

        pstmt.setString(1,desgra.toUpperCase());
        pstmt.setString(2,numrif.toUpperCase());
        pstmt.setString(3,numtel.toUpperCase());
        pstmt.setString(4,direc1.toUpperCase());
        pstmt.setString(5,direc2.toUpperCase());
        pstmt.setString(6,paigra);
        pstmt.setString(7,edogra);
        pstmt.setString(8, login);
        pstmt.setString(9,codgra.toUpperCase());
        
        // Antes de ejecutar valida si existe el registro en la base de Datos.
        try {
            //Avisando
            pstmt.executeUpdate();
            if(pstmt.getUpdateCount()==0){
            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
            } else {
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
            }
            desgra = "";
            numrif = "";
            numtel = "";
            direc1 = "";
            direc2 = "";
            paigra = "";
            edogra = "";
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
			//System.out.println("me fui al insert");
			insert();
		} else {
			//System.out.println("me fui al update");
			update();
		}
	}


/**
 * Leer Datos de GBORA01
 * @throws NamingException 
* @throws IOException 
 **/ 	
	public void select(int first, int pageSize, Object filterValue) throws SQLException, ClassNotFoundException, NamingException {
  				
		//System.out.println("entre al metodo SELECT");	
		Context initContext = new InitialContext();     
		DataSource ds = (DataSource) initContext.lookup(JNDI);
		con = ds.getConnection();		
		
		//Consulta paginada
     String query = "SELECT * FROM"; 
	    query += "(select query.*, rownum as rn from";
		query += "(SELECT A.CODGRA, A.DESGRA, A.NUMRIF, A.NUMTEL, A.DIREC1, A.DIREC2, A.PAIGRA, A.EDOGRA ";
	    query += " FROM GBORA01 A";
	    query += " WHERE  A.CODGRA||A.DESGRA||A.NUMRIF||A.PAIGRA||translate(A.EDOGRA, '·ÈÌÛ˙¡…Õ”⁄', 'aeiouAEIOU') LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
	    query += " AND A.CODGRA  like '" + codgra.toUpperCase() + "%'";
	    query += ")query )" ;
	    query += " WHERE rownum <=?";
	    query += " AND rn > (?)";
	    query += " ORDER BY CODGRA";

    pstmt = con.prepareStatement(query);
    pstmt.setInt(1,pageSize);
    pstmt.setInt(2,first);
    //System.out.println(query);
    //System.out.println(pageSize);
    //System.out.println(first);
		
    r =  pstmt.executeQuery();
    
    while (r.next()){
 	Gbora01 select = new Gbora01();
 	select.setZcodgra(r.getString(1));
 	select.setZdesgra(r.getString(2));
 	select.setZnumrif(r.getString(3));
 	select.setZnumtel(r.getString(4));
 	select.setZdirec1(r.getString(5));
 	select.setZdirec2(r.getString(6));
 	select.setZpaigra(r.getString(7));
 	select.setZedogra(r.getString(8));
 	select.setZdelete(r.getString(1));
   	
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

	public void counter(Object filterValue) throws SQLException, ClassNotFoundException, NamingException {
	  				
			//System.out.println("entre al metodo SELECT");	
			Context initContext = new InitialContext();     
			DataSource ds = (DataSource) initContext.lookup(JNDI);
			con = ds.getConnection();		
			

			//Consulta no paginada
		     String query = "SELECT A.CODGRA, A.DESGRA, A.NUMRIF, A.NUMTEL, A.DIREC1, A.DIREC2, A.PAIGRA, A.EDOGRA ";
			    query += " FROM GBORA01 A";
			    query += " WHERE  A.CODGRA||A.DESGRA||A.NUMRIF||A.PAIGRA||translate(A.EDOGRA, '·ÈÌÛ˙¡…Õ”⁄', 'aeiouAEIOU') LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
			    query += " AND A.CODGRA  like '" + codgra.toUpperCase() + "%'";
			    query += " ORDER BY CODGRA";

	    pstmt = con.prepareStatement(query);
	    //System.out.println(query);
			
	    r =  pstmt.executeQuery();
	    
	    while (r.next()){
	 	Gbora01 select = new Gbora01();
	 	select.setZcodgra(r.getString(1));
	 	select.setZdesgra(r.getString(2));
	 	select.setZnumrif(r.getString(3));
	 	select.setZnumtel(r.getString(4));
	 	select.setZdirec1(r.getString(5));
	 	select.setZdirec2(r.getString(6));
	 	select.setZpaigra(r.getString(7));
	 	select.setZedogra(r.getString(8));
	   	
	    	//Agrega la lista
	    	list1.add(select);

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
  		codgra = "";
  		desgra = "";
  		numrif = "";
  		numtel = "";
  		direc1 = "";
  		direc2 = "";
  		paigra = "";
  		edogra = "";
  		validarOperacion = 0;
	}
}
