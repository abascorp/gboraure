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
import java.util.Date;
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
public class Gbora05 extends Bd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Gbora05> lazyModel;  
	
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Gbora05> getLazyModel() {
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
	lazyModel  = new LazyDataModel<Gbora05>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 7217573531435419432L;
		
        @Override
		public List<Gbora05> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
	private Date fecreg = new Date();
	private String canmil = "";
	private Object filterValue = "";
	private List<Gbora05> list = new ArrayList<Gbora05>();
	private List<Gbora05> list1 = new ArrayList<Gbora05>();
	private int validarOperacion = 0;
	private String zcodgra = "";
	private String zcodsec = "";
	private String zfecreg = "";
	private String zcanmil = "";
	private String zdesgra = "";
	private String zgranja = "";
	private String zdessec = "";
	private String zsector = "";
	private String zdelete = "";
	//AUTOCOMPLETES//
	PntGenericaAuto consulta = new PntGenericaAuto();
	String[][] tabla;
	
	public Date getFecreg() {
		return fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public String getCanmil() {
		return canmil;
	}

	public void setCanmil(String canmil) {
		this.canmil = canmil;
	}

	public String getZfecreg() {
		return zfecreg;
	}

	public void setZfecreg(String zfecreg) {
		this.zfecreg = zfecreg;
	}

	public String getZcanmil() {
		return zcanmil;
	}

	public void setZcanmil(String zcanmil) {
		this.zcanmil = zcanmil;
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
 * Inserta GBORA05.
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

		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
                                    
        String query = "INSERT INTO GBORA05 VALUES (1,?,'" + sdfecha2.format(fecreg) + "',?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
        pstmt = con.prepareStatement(query);
        //pstmt.setString(1,vecgra[0].toUpperCase());
        pstmt.setString(1,vecsec[0].toUpperCase());
        pstmt.setFloat(2, Float.parseFloat(canmil));
        pstmt.setString(3, login);
        pstmt.setString(4, login);            
        
        //System.out.println(query);

        try {
        	//System.out.println("entre al try");	
            //Avisando
        	pstmt.executeUpdate();
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
            limpiarValores();                
        } catch (SQLException e)  {
            
            if (e.getMessage().trim().equals("ORA-00001: unique constraint (OPENBIZVIEW.GBORA05_PK) violated")){
            	
            	//System.out.println("se cumple la condicion y muestro el msg");
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora05pkv"), "");
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
 * Elimina GBORA05
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

        	String query = "DELETE FROM GBORA05 WHERE CODGRA||CODSEC||TO_CHAR(FECREG, 'DD/MM/YYYY') IN (" + param + ")";
        		        	
            pstmt = con.prepareStatement(query);
            //System.out.println(query);

            try {
                //Avisando
                pstmt.executeUpdate();
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
                limpiarValores();	
            } catch (SQLException e) {

                if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA05_FK1) violated - child record found")){
                	
                	//System.out.println("se cumple la condicion y muestro el msg");
                	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora02fkv"), "");
                }
                
                else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA04_FK1) violated - child record found")){
                	
                	//System.out.println("se cumple la condicion y muestro el msg");
                	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora02fkv"), "");
                }
                
                else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA03_FK1) violated - child record found")){
                	
                	//System.out.println("se cumple la condicion y muestro el msg");
                	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora02fkv"), "");
                }
                
                else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA06L_FK1) violated - child record found")){
                	
                	//System.out.println("se cumple la condicion y muestro el msg");
                	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora02fkv"), "");
                }
                
                else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA06R_FK1) violated - child record found")){
                	
                	//System.out.println("se cumple la condicion y muestro el msg");
                	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora02fkv"), "");
                }
                else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA07_FK1) violated - child record found")){
                	
                	//System.out.println("se cumple la condicion y muestro el msg");
                	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora02fkv"), "");
                }
                else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA08_FK1) violated - child record found")){
                	
                	//System.out.println("se cumple la condicion y muestro el msg");
                	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora02fkv"), "");
                }
                else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA010_FK1) violated - child record found")){
                	
                	//System.out.println("se cumple la condicion y muestro el msg");
                	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora02fkv"), "");
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
	}
	FacesContext.getCurrentInstance().addMessage(null, msj); 
}

/**
 * Actualiza GBORA05
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

		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
  		 		
        String query = "UPDATE GBORA05";
         query += " SET CANMIL = ?, ";
         query += " USRACT = ?,";
         query += " FECACT = '" + getFecha() + "'";
         query += " WHERE CODGRA = 1 ";
         query += " AND CODSEC = ? ";
         query += " AND TO_CHAR(FECREG, 'DD/MM/YYYY') = '" + sdfecha2.format(fecreg) + "'";
         
         //System.out.println(query);
         
        pstmt = con.prepareStatement(query);

        pstmt.setFloat(1, Float.parseFloat(canmil));
        pstmt.setString(2, login);
        //pstmt.setString(3,vecgra[0].toUpperCase());
        pstmt.setString(3,vecsec[0].toUpperCase());
        
        // Antes de ejecutar valida si existe el registro en la base de Datos.
        try {
            //Avisando
            pstmt.executeUpdate();
            if(pstmt.getUpdateCount()==0){
            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
            } else {
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
            }
            fecreg = new Date();
            canmil = "";
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
 * Leer Datos de GBORA05
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

		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
		
		//Consulta paginada
     String query = "SELECT * FROM"; 
	    query += "(select query.*, rownum as rn from";
		query += "(SELECT A.CODGRA, A.CODSEC, TO_CHAR(A.FECREG, 'DD/MM/YYYY') AS FECREG, A.CANMIL, B.DESSEC, C.DESGRA ";
	    query += " FROM GBORA05 A, GBORA02 B, GBORA01 C";
	    query += " WHERE A.CODSEC = B.CODSEC";
	    query += " AND A.CODGRA = C.CODGRA";
	    query += " AND A.CANMIL||B.DESSEC||C.DESGRA||TO_CHAR(A.FECREG, 'DD/MM/YYYY') LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
	    //query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
	    query += " AND A.CODSEC  like '" + vecsec[0].toUpperCase() + "%'";
	    query += ")query )" ;
	    query += " WHERE rownum <=?";
	    query += " AND rn > (?)";
	    query += " ORDER BY CODGRA, CODSEC";

    pstmt = con.prepareStatement(query);
    pstmt.setInt(1,pageSize);
    pstmt.setInt(2,first);
    //System.out.println(query);
    //System.out.println(pageSize);
    //System.out.println(first);
		
    r =  pstmt.executeQuery();
    
    while (r.next()){
 	Gbora05 select = new Gbora05();
 	select.setZcodgra(r.getString(1));
 	select.setZcodsec(r.getString(2));
 	select.setZfecreg(r.getString(3));
 	select.setZcanmil(r.getString(4));
 	select.setZdessec(r.getString(5));
 	select.setZsector(r.getString(2) + " - " + r.getString(5));
 	select.setZdesgra(r.getString(6));
 	select.setZgranja(r.getString(1) + " - " + r.getString(6));
	select.setZdelete(r.getString(1) + "" + r.getString(2) + "" + r.getString(3));
   	
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

			//String[] vecgra = codgra.split("\\ - ", -1);
			String[] vecsec = codsec.split("\\ - ", -1);	

			//Consulta no paginada
		     String query = "SELECT A.CODGRA, A.CODSEC, TO_CHAR(A.FECREG, 'DD/MM/YYYY') AS FECREG, A.CANMIL, B.DESSEC, C.DESGRA ";
				    query += " FROM GBORA05 A, GBORA02 B, GBORA01 C";
				    query += " WHERE A.CODSEC = B.CODSEC";
				    query += " AND A.CODGRA = C.CODGRA";
				    query += " AND A.CANMIL||B.DESSEC||C.DESGRA||TO_CHAR(A.FECREG, 'DD/MM/YYYY') LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
				    //query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
				    query += " AND A.CODSEC  like '" + vecsec[0].toUpperCase() + "%'";
		     	    query += " ORDER BY CODGRA, CODSEC";

	    pstmt = con.prepareStatement(query);
	    //System.out.println(query);
			
	    r =  pstmt.executeQuery();
	    
	    while (r.next()){
	 	Gbora05 select = new Gbora05();
	 	select.setZcodgra(r.getString(1));
	 	select.setZcodsec(r.getString(2));
	 	select.setZfecreg(r.getString(3));
	 	select.setZcanmil(r.getString(4));
	 	select.setZdessec(r.getString(5));
	 	select.setZsector(r.getString(2) + " - " + r.getString(5));
	 	select.setZdesgra(r.getString(6));
	 	select.setZgranja(r.getString(1) + " - " + r.getString(6));
	   	
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
  		canmil = "";
  		//fecreg = new Date();
  		validarOperacion = 0;
	}
  	
    public void reset() {
    	// TODO Auto-generated method stub
    	codgra = null;   
    	codsec = null;
    }   
}
