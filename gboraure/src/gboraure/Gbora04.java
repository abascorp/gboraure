/*
 *  Copyright (C) 2017  MAURICIO ALBANESE

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

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
/**
 *
 * @author Mauricio
 */
@ManagedBean
@ViewScoped
public class Gbora04 extends Bd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Gbora04> lazyModel;  
	
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Gbora04> getLazyModel() {
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
	lazyModel  = new LazyDataModel<Gbora04>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 7217573531435419432L;
		
        @Override
		public List<Gbora04> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
				lazyModel.setRowCount(rows);  //Necesario para crear la paginación
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
	private String codgra = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codgra"); //Usuario logeado
	private String codsec = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codsec"); //Usuario logeado
	private String codlot = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codlot"); //Usuario logeado
	private String codtab = "";
	private String destab = "";
	private Object filterValue = "";
	private List<Gbora04> list = new ArrayList<Gbora04>();
	private List<Gbora04> list1 = new ArrayList<Gbora04>();
	private int validarOperacion = 0;
	private String zcodgra = "";
	private String zcodsec = "";
	private String zcodlot = "";
	private String zcodtab = "";
	private String zdestab = "";
	private String zdesgra = "";
	private String zgranja = "";
	private String zdessec = "";
	private String zsector = "";
	private String zdeslot = "";
	private String zlote = "";
	private String zdelete = "";
	//AUTOCOMPLETES//
	PntGenericaAuto consulta = new PntGenericaAuto();
	String[][] tabla;
		
	public String getCodlot() {
		return codlot;
	}

	public void setCodlot(String codlot) {
		this.codlot = codlot;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codlot", codlot);
	}

	public String getCodtab() {
		return codtab;
	}

	public void setCodtab(String codtab) {
		this.codtab = codtab;
	}

	public String getDestab() {
		return destab;
	}

	public void setDestab(String destab) {
		this.destab = destab;
	}

	public String getZcodtab() {
		return zcodtab;
	}

	public void setZcodtab(String zcodtab) {
		this.zcodtab = zcodtab;
	}

	public String getZdestab() {
		return zdestab;
	}

	public void setZdestab(String zdestab) {
		this.zdestab = zdestab;
	}

	public String getZlote() {
		return zlote;
	}

	public void setZlote(String zlote) {
		this.zlote = zlote;
	}

	public String getZcodlot() {
		return zcodlot;
	}

	public void setZcodlot(String zcodlot) {
		this.zcodlot = zcodlot;
	}

	public String getZdeslot() {
		return zdeslot;
	}

	public void setZdeslot(String zdeslot) {
		this.zdeslot = zdeslot;
	}

	public String getZsector() {
		return zsector;
	}

	public void setZsector(String zsector) {
		this.zsector = zsector;
	}

	public String getCodgra() {
		return codgra;
	}

	public void setCodgra(String codgra) {
		this.codgra = codgra;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codgra", codgra);
	}

	public String getCodsec() {
		return codsec;
	}

	public void setCodsec(String codsec) {
		this.codsec = codsec;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codsec", codsec);
	}

	public String getZcodgra() {
		return zcodgra;
	}

	public void setZcodgra(String zcodgra) {
		this.zcodgra = zcodgra;
	}

	public String getZcodsec() {
		return zcodsec;
	}

	public void setZcodsec(String zcodsec) {
		this.zcodsec = zcodsec;
	}

	public String getZdessec() {
		return zdessec;
	}

	public void setZdessec(String zdessec) {
		this.zdessec = zdessec;
	}

	public String getZdesgra() {
		return zdesgra;
	}

	public void setZdesgra(String zdesgra) {
		this.zdesgra = zdesgra;
	}

	public String getZgranja() {
		return zgranja;
	}

	public void setZgranja(String zgranja) {
		this.zgranja = zgranja;
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
	boolean vGacc; //Validador de opciones del menú
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
 * Inserta GBORA04.
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
        
        //if (codgra == null) {
			//codgra = "";
		//}
		//if (codgra == "") {
			//codgra= "";
		//}
        if (codsec == null) {
			codsec = "";
		}
		if (codsec == "") {
			codsec = "";
		}
        if (codlot == null) {
			codlot = "";
		}
		if (codlot == "") {
			codlot = "";
		}

		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] veclot = codlot.split("\\ - ", -1);
                                    
        String query = "INSERT INTO GBORA04 VALUES (1,?,?,?,?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
        pstmt = con.prepareStatement(query);
        //pstmt.setString(1,vecgra[0].toUpperCase());
        pstmt.setString(1,vecsec[0].toUpperCase());
        pstmt.setString(2,veclot[0].toUpperCase());
        pstmt.setString(3,codtab.toUpperCase());
        pstmt.setString(4,destab.toUpperCase());
        pstmt.setString(5, login);
        pstmt.setString(6, login);            
        
        //System.out.println(query);

        try {
        	//System.out.println("entre al try");	
            //Avisando
        	pstmt.executeUpdate();
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
            limpiarValores();                
        } catch (SQLException e)  {
            
            if (e.getMessage().trim().equals("ORA-00001: unique constraint (OPENBIZVIEW.GBORA04_PK) violated ")){
            	
            	//System.out.println("se cumple la condicion y muestro el msg");
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora04pkv"), "");
            }
                    
            else {            	
            	//System.out.println("no se cumple la condicion y muestro otro msg");
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            }
        }	
        
        pstmt.close();
        con.close();
    } catch (Exception e) {
    	e.printStackTrace();
    }	
    FacesContext.getCurrentInstance().addMessage(null, msj);
}

/**
 * Elimina GBORA04
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

        	String query = "DELETE FROM GBORA04 WHERE CODGRA||CODSEC||CODLOT||CODTAB IN (" + param + ")";
        		        	
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
 * Actualiza GBORA04
 * <b>Parametros del Metodo:<b> String codcat1, String descat1 unidos como un solo string.<br>
 * String pool, String login.<br><br>
 **/
public void update() throws  NamingException {
	//System.out.println("entre al metodo UPDATE");

     try { 	 
    	Context initContext = new InitialContext();     
  		DataSource ds = (DataSource) initContext.lookup(JNDI);	
  		con = ds.getConnection();
  		
        //if (codgra == null) {
			//codgra = "";
		//}
		//if (codgra == "") {
			//codgra= "";
		//}
        if (codsec == null) {
			codsec = "";
		}
		if (codsec == "") {
			codsec = "";
		}
        if (codlot == null) {
			codlot = "";
		}
		if (codlot == "") {
			codlot = "";
		}

		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] veclot = codlot.split("\\ - ", -1);
  		 		
        String query = "UPDATE GBORA04";
         query += " SET DESTAB = ?, ";
         query += " USRACT = ?,";
         query += " FECACT = '" + getFecha() + "'";
         query += " WHERE CODGRA = 1 ";
         query += " AND CODSEC = ? ";
         query += " AND CODLOT = ? ";
         query += " AND CODTAB = ? ";
         
         //System.out.println(query);
         
        pstmt = con.prepareStatement(query);

        pstmt.setString(1,destab.toUpperCase());
        pstmt.setString(2, login);
        //pstmt.setString(3,vecgra[0].toUpperCase());
        pstmt.setString(3,vecsec[0].toUpperCase());
        pstmt.setString(4,veclot[0].toUpperCase());
        pstmt.setString(5,codtab.toUpperCase());
        
        // Antes de ejecutar valida si existe el registro en la base de Datos.
        try {
            //Avisando
            pstmt.executeUpdate();
            if(pstmt.getUpdateCount()==0){
            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
            } else {
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
            }
            destab = "";
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
 * Leer Datos de GBORA04
 * @throws NamingException 
* @throws IOException 
 **/ 	
	public void select(int first, int pageSize, Object filterValue) throws SQLException, ClassNotFoundException, NamingException {
  				
		//System.out.println("entre al metodo SELECT");	
		Context initContext = new InitialContext();     
		DataSource ds = (DataSource) initContext.lookup(JNDI);
		con = ds.getConnection();		
		
        //if (codgra == null) {
			//codgra = "";
		//}
		//if (codgra == "") {
			//codgra= "";
		//}
        if (codsec == null) {
			codsec = "";
		}
		if (codsec == "") {
			codsec = "";
		}
        if (codlot == null) {
			codlot = "";
		}
		if (codlot == "") {
			codlot = "";
		}

		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] veclot = codlot.split("\\ - ", -1);
		
		//Consulta paginada
     String query = "SELECT * FROM"; 
	    query += "(select query.*, rownum as rn from";
		query += "(SELECT A.CODGRA, A.CODSEC, A.CODLOT, A.CODTAB, A.DESTAB, B.DESLOT, C.DESSEC, D.DESGRA ";
	    query += " FROM GBORA04 A, GBORA03 B, GBORA02 C, GBORA01 D";
	    query += " WHERE A.CODLOT = B.CODLOT";
	    query += " AND A.CODSEC = B.CODSEC";
	    query += " AND A.CODSEC = C.CODSEC";
	    query += " AND A.CODGRA = D.CODGRA";
	    query += " AND A.CODTAB||C.DESSEC||D.DESGRA||B.DESLOT||A.DESTAB LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
	    //query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
	    query += " AND A.CODSEC  like '" + vecsec[0].toUpperCase() + "%'";
	    query += " AND A.CODLOT  like '" + veclot[0].toUpperCase() + "%'";
	    query += ")query )" ;
	    query += " WHERE rownum <=?";
	    query += " AND rn > (?)";
	    query += " ORDER BY CODGRA, CODSEC, CODLOT, CODTAB";

    pstmt = con.prepareStatement(query);
    pstmt.setInt(1,pageSize);
    pstmt.setInt(2,first);
    //System.out.println(query);
    //System.out.println(pageSize);
    //System.out.println(first);
		
    r =  pstmt.executeQuery();
    
    while (r.next()){
 	Gbora04 select = new Gbora04();
 	select.setZcodgra(r.getString(1));
 	select.setZcodsec(r.getString(2));
 	select.setZcodlot(r.getString(3));
 	select.setZcodtab(r.getString(4));
 	select.setZdestab(r.getString(5));
 	select.setZdeslot(r.getString(6));
 	select.setZlote(r.getString(3) + " - " + r.getString(6));
 	select.setZdessec(r.getString(7));
 	select.setZsector(r.getString(2) + " - " + r.getString(7));
 	select.setZdesgra(r.getString(8));
 	select.setZgranja(r.getString(1) + " - " + r.getString(8));
	select.setZdelete(r.getString(1) + "" + r.getString(2) + "" + r.getString(3) + "" + r.getString(4));
   	
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
			
	        //if (codgra == null) {
				//codgra = "";
			//}
			//if (codgra == "") {
				//codgra= "";
			//}
	        if (codsec == null) {
				codsec = "";
			}
			if (codsec == "") {
				codsec = "";
			}
	        if (codlot == null) {
				codlot = "";
			}
			if (codlot == "") {
				codlot = "";
			}

			//String[] vecgra = codgra.split("\\ - ", -1);
			String[] vecsec = codsec.split("\\ - ", -1);
			String[] veclot = codlot.split("\\ - ", -1);

			//Consulta no paginada
		     String query = "SELECT A.CODGRA, A.CODSEC, A.CODLOT, A.CODTAB, A.DESTAB, B.DESLOT, C.DESSEC, D.DESGRA ";
				    query += " FROM GBORA04 A, GBORA03 B, GBORA02 C, GBORA01 D";
				    query += " WHERE A.CODLOT = B.CODLOT";
				    query += " AND A.CODSEC = B.CODSEC";
				    query += " AND A.CODSEC = C.CODSEC";
				    query += " AND A.CODGRA = D.CODGRA";
				    query += " AND A.CODTAB||C.DESSEC||D.DESGRA||B.DESLOT||A.DESTAB LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
				    //query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
				    query += " AND A.CODSEC  like '" + vecsec[0].toUpperCase() + "%'";
				    query += " AND A.CODLOT  like '" + veclot[0].toUpperCase() + "%'";
		     	    query += " ORDER BY CODGRA, CODSEC, CODLOT, CODTAB";

	    pstmt = con.prepareStatement(query);
	    //System.out.println(query);
			
	    r =  pstmt.executeQuery();
	    
	    while (r.next()){
	 	Gbora04 select = new Gbora04();
	 	select.setZcodgra(r.getString(1));
	 	select.setZcodsec(r.getString(2));
	 	select.setZcodlot(r.getString(3));
	 	select.setZcodtab(r.getString(4));
	 	select.setZdestab(r.getString(5));
	 	select.setZdeslot(r.getString(6));
	 	select.setZlote(r.getString(3) + " - " + r.getString(6));
	 	select.setZdessec(r.getString(7));
	 	select.setZsector(r.getString(2) + " - " + r.getString(7));
	 	select.setZdesgra(r.getString(8));
	 	select.setZgranja(r.getString(1) + " - " + r.getString(8));
	   	
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
  		codtab = "";
  		destab = "";
  		validarOperacion = 0;
	}
  	
    public void reset() {
    	// TODO Auto-generated method stub  	
    	codgra = null;   
    	codsec = null;
    	codlot = null;
    }   
       
	/**
	 * Lista de Sectores.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
		
		 public List<String> completeLote(String query) throws NamingException,IOException { 
  	
		 //if (codgra == null) {
			// codgra = "";
		 //}	
		 if (codsec == null) {
			 codsec = "";
		 }	
		 
		 //String[] vecgra = codgra.split("\\ - ", -1);
		 String[] vecsec = codsec.split("\\ - ", -1);
		 //System.out.println("codgra: " + vecgra[0]);	 
				 		 
		 List<String> results = new ArrayList<String>();
		 
		 String vquery = "SELECT A.CODLOT|| ' - ' ||A.DESLOT AS LOTE";
		        vquery += " FROM GBORA03 A";  
		        //vquery += " WHERE A.CODGRA = '" + vecgra[0].toUpperCase() + "'";
		        vquery += " WHERE A.CODSEC = '" + vecsec[0].toUpperCase() + "'";
		        vquery += " GROUP BY A.CODLOT, A.DESLOT";
		        vquery += " ORDER BY 1";
				 
				//System.out.println(vquery);
		
				consulta.selectPntGenericaAuto(vquery,JNDI);
		
				rows = consulta.getRows();
		
				tabla = consulta.getArray();
		
				for (int i = 0; i < rows; i++) {
					results.add(tabla[i][0]);
				}
				return results;
			}	
}
