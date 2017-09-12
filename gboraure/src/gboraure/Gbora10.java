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
public class Gbora10 extends Bd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Gbora10> lazyModel;  
	
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Gbora10> getLazyModel() {
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
	lazyModel  = new LazyDataModel<Gbora10>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 7217573531435419432L;
		
        @Override
		public List<Gbora10> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
	private String codpoz = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codpoz"); //Usuario logeado
	private String estatus = "";
	private String qls = "";
	private Date fecafo = new Date();
	private String observ = "";
	private String codtip = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codtip"); //Usuario logeado
	private Object filterValue = "";
	private List<Gbora10> list = new ArrayList<Gbora10>();
	private List<Gbora10> list1 = new ArrayList<Gbora10>();
	private int validarOperacion = 0;
	private String zcodgra = "";
	private String zcodsec = "";
	private String zcodpoz = "";
	private String zestatus = "";
	private String zqls = "";
	private String zfecafo = "";
	private String zobserv = "";
	private String zdespoz = "";
	private String zpozo = "";
	private String zdesgra = "";
	private String zgranja = "";
	private String zdessec = "";
	private String zsector = "";
	private String zdelete = "";
	//AUTOCOMPLETES//
	PntGenericaAuto consulta = new PntGenericaAuto();
	String[][] tabla;
	

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getQls() {
		return qls;
	}

	public void setQls(String qls) {
		this.qls = qls;
	}

	public Date getFecafo() {
		return fecafo;
	}

	public void setFecafo(Date fecafo) {
		this.fecafo = fecafo;
	}

	public String getObserv() {
		return observ;
	}

	public void setObserv(String observ) {
		this.observ = observ;
	}

	public String getZestatus() {
		return zestatus;
	}

	public void setZestatus(String zestatus) {
		this.zestatus = zestatus;
	}

	public String getZqls() {
		return zqls;
	}

	public void setZqls(String zqls) {
		this.zqls = zqls;
	}

	public String getZfecafo() {
		return zfecafo;
	}

	public void setZfecafo(String zfecafo) {
		this.zfecafo = zfecafo;
	}

	public String getZobserv() {
		return zobserv;
	}

	public void setZobserv(String zobserv) {
		this.zobserv = zobserv;
	}

	public String getZpozo() {
		return zpozo;
	}

	public void setZpozo(String zpozo) {
		this.zpozo = zpozo;
	}

	public String getCodpoz() {
		return codpoz;
	}

	public void setCodpoz(String codpoz) {
		this.codpoz = codpoz;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codpoz", codpoz);
	}

	public String getCodtip() {
		return codtip;
	}

	public void setCodtip(String codtip) {
		this.codtip = codtip;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codtip", codtip);
	}

	public String getZcodpoz() {
		return zcodpoz;
	}

	public void setZcodpoz(String zcodpoz) {
		this.zcodpoz = zcodpoz;
	}

	public String getZdespoz() {
		return zdespoz;
	}

	public void setZdespoz(String zdespoz) {
		this.zdespoz = zdespoz;
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
 * Inserta GBORA10.
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
        if (codpoz == null) {
			codpoz = "";
		}
		if (codpoz == "") {
			codpoz = "";
		}
		
		if (qls == null) {
			qls = "0";
		}
		if (qls == "") {
			qls = "0";
		}
		
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] vecpoz = codpoz.split("\\ - ", -1);
                                    
        String query = "INSERT INTO GBORA10 VALUES (1,?,?,?,?,'" + sdfecha2.format(fecafo) + "',?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
        pstmt = con.prepareStatement(query);
        //pstmt.setString(1,vecgra[0].toUpperCase());
        pstmt.setString(1,vecsec[0].toUpperCase());
        pstmt.setString(2,vecpoz[0].toUpperCase());
        pstmt.setString(3,estatus.toUpperCase());
        pstmt.setFloat(4, Float.parseFloat(qls));
        pstmt.setString(5,observ.toUpperCase());
        pstmt.setString(6, login);
        pstmt.setString(7, login);            
        
        //System.out.println(query);

        try {
        	//System.out.println("entre al try");	
            //Avisando
        	pstmt.executeUpdate();
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
            limpiarValores();                
        } catch (SQLException e)  {
            
            if (e.getMessage().trim().equals("ORA-00001: unique constraint (OPENBIZVIEW.GBORA10_PK) violated")){
            	
            	//System.out.println("se cumple la condicion y muestro el msg");
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora10pkv"), "");
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
 * Elimina GBORA10
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

        	String query = "DELETE FROM GBORA10 WHERE CODGRA||CODSEC||CODPOZ||TO_CHAR(FECAFO, 'DD/MM/YYYY') IN (" + param + ")";
        		        	
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
                 	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora08fkv"), "");
                 }
                 
                 else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA04_FK1) violated - child record found")){
                 	
                 	//System.out.println("se cumple la condicion y muestro el msg");
                 	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora08fkv"), "");
                 }
                 
                 else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA03_FK1) violated - child record found")){
                 	
                 	//System.out.println("se cumple la condicion y muestro el msg");
                 	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora08fkv"), "");
                 }
                 
                 else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA06L_FK1) violated - child record found")){
                 	
                 	//System.out.println("se cumple la condicion y muestro el msg");
                 	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora08fkv"), "");
                 }
                 
                 else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA06R_FK1) violated - child record found")){
                 	
                 	//System.out.println("se cumple la condicion y muestro el msg");
                 	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora08fkv"), "");
                 }
                 else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA07_FK1) violated - child record found")){
                 	
                 	//System.out.println("se cumple la condicion y muestro el msg");
                 	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora08fkv"), "");
                 }
                 else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA08_FK1) violated - child record found")){
                 	
                 	//System.out.println("se cumple la condicion y muestro el msg");
                 	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora08fkv"), "");
                 }
                 else if (e.getMessage().trim().equals("ORA-02292: integrity constraint (OPENBIZVIEW.GBORA010_FK1) violated - child record found")){
                 	
                 	//System.out.println("se cumple la condicion y muestro el msg");
                 	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora08fkv"), "");
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
 * Actualiza GBORA10
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
        if (codpoz == null) {
			codpoz = "";
		}
		if (codpoz == "") {
			codpoz = "";
		}
		if (qls == null) {
			qls = "0";
		}
		if (qls == "") {
			qls = "0";
		}
		
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] vecpoz = codpoz.split("\\ - ", -1);
  		 		
        String query = "UPDATE GBORA10";
         query += " SET ESTATUS = ?, ";
         query += " QLS = ?, ";
         query += " OBSERV = ?, ";
         query += " USRACT = ?,";
         query += " FECACT = '" + getFecha() + "'";
         query += " WHERE CODGRA = 1 ";
         query += " AND CODSEC = ? ";
         query += " AND CODPOZ = ? ";
         query += " AND FECAFO = '" + sdfecha2.format(fecafo) + "'";
         
         //System.out.println(query);
         //System.out.println(despoz);
         //System.out.println(vectip[0]);
         //System.out.println(vecsec[0]);
         //System.out.println(codpoz);

        pstmt = con.prepareStatement(query);


        pstmt.setString(1,estatus.toUpperCase());
        pstmt.setFloat(2, Float.parseFloat(qls));
        pstmt.setString(3,observ.toUpperCase());
        pstmt.setString(4, login);
        pstmt.setString(5,vecsec[0].toUpperCase());
        pstmt.setString(6,vecpoz[0].toUpperCase());
        
        // Antes de ejecutar valida si existe el registro en la base de Datos.
        try {
            //Avisando
            pstmt.executeUpdate();
            if(pstmt.getUpdateCount()==0){
            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
            } else {
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
            }
            estatus = "";
            qls = "";
            observ = "";
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
 * Leer Datos de GBORA10
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
        if (codpoz == null) {
			codpoz = "";
		}
		if (codpoz == "") {
			codpoz = "";
		}
		
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] vecpoz = codpoz.split("\\ - ", -1);
		
		//Consulta paginada
     String query = "SELECT * FROM"; 
	    query += "(select query.*, rownum as rn from";
		query += "(SELECT A.CODGRA, A.CODSEC, A.CODPOZ, A.ESTATUS, A.QLS, TO_CHAR(A.FECAFO, 'DD/MM/YYYY') AS FECAFO, OBSERV, B.DESSEC, C.DESGRA, D.DESPOZ ";
	    query += " FROM GBORA10 A, GBORA02 B, GBORA01 C, GBORA08 D";
	    query += " WHERE A.CODSEC = B.CODSEC";
	    query += " AND A.CODGRA = C.CODGRA";
	    query += " AND A.CODPOZ = D.CODPOZ";
	    query += " AND A.CODSEC = D.CODSEC";
	    query += " AND D.DESPOZ||B.DESSEC||C.DESGRA||A.ESTATUS||TO_CHAR(A.FECAFO, 'DD/MM/YYYY') LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
	    //query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
	    query += " AND A.CODSEC  like '" + vecsec[0].toUpperCase() + "%'";
	    query += " AND A.CODPOZ  like '" + vecpoz[0].toUpperCase() + "%'";
	    query += ")query )" ;
	    query += " WHERE rownum <=?";
	    query += " AND rn > (?)";
	    query += " ORDER BY CODGRA, CODSEC, CODPOZ";

    pstmt = con.prepareStatement(query);
    pstmt.setInt(1,pageSize);
    pstmt.setInt(2,first);
    //System.out.println(query);
    //System.out.println(pageSize);
    //System.out.println(first);
		
    r =  pstmt.executeQuery();
    
    while (r.next()){
 	Gbora10 select = new Gbora10();
 	select.setZcodgra(r.getString(1));
 	select.setZcodsec(r.getString(2));
 	select.setZcodpoz(r.getString(3));
 	select.setZestatus(r.getString(4));
 	select.setZqls(r.getString(5));
 	select.setZfecafo(r.getString(6));
 	select.setZobserv(r.getString(7));
 	select.setZdessec(r.getString(8));
 	select.setZdesgra(r.getString(9));
 	select.setZdespoz(r.getString(10));
 	select.setZgranja(r.getString(1) + " - " + r.getString(9));
 	select.setZsector(r.getString(2) + " - " + r.getString(8));
 	select.setZpozo(r.getString(3) + " - " + r.getString(10));
	select.setZdelete(r.getString(1) + "" + r.getString(2) + "" + r.getString(3) + "" + r.getString(6));
   	
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
	        if (codpoz == null) {
				codpoz = "";
			}
			if (codpoz == "") {
				codpoz = "";
			}
			
			String[] vecsec = codsec.split("\\ - ", -1);
			String[] vecpoz = codpoz.split("\\ - ", -1);

			//Consulta no paginada
		     String query = "SELECT A.CODGRA, A.CODSEC, A.CODPOZ, A.ESTATUS, A.QLS, TO_CHAR(A.FECAFO, 'DD/MM/YYYY') AS FECAFO, OBSERV, B.DESSEC, C.DESGRA, D.DESPOZ ";
				    query += " FROM GBORA10 A, GBORA02 B, GBORA01 C, GBORA08 D";
				    query += " WHERE A.CODSEC = B.CODSEC";
				    query += " AND A.CODGRA = C.CODGRA";
				    query += " AND A.CODPOZ = D.CODPOZ";
				    query += " AND A.CODSEC = D.CODSEC";
				    query += " AND D.DESPOZ||B.DESSEC||C.DESGRA||A.ESTATUS||TO_CHAR(A.FECAFO, 'DD/MM/YYYY') LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
				    //query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
				    query += " AND A.CODSEC  like '" + vecsec[0].toUpperCase() + "%'";
				    query += " AND A.CODPOZ  like '" + vecpoz[0].toUpperCase() + "%'";
				    query += " ORDER BY CODGRA, CODSEC, CODPOZ";

			    pstmt = con.prepareStatement(query);
			    //System.out.println(query);
					
			    r =  pstmt.executeQuery();
			    
			    while (r.next()){
			 	Gbora10 select = new Gbora10();
			 	select.setZcodgra(r.getString(1));
			 	select.setZcodsec(r.getString(2));
			 	select.setZcodpoz(r.getString(3));
			 	select.setZestatus(r.getString(4));
			 	select.setZqls(r.getString(5));
			 	select.setZfecafo(r.getString(6));
			 	select.setZobserv(r.getString(7));
			 	select.setZdesgra(r.getString(8));
			 	select.setZdessec(r.getString(9));
			 	select.setZdespoz(r.getString(10));
			 	select.setZgranja(r.getString(1) + " - " + r.getString(8));
			 	select.setZsector(r.getString(2) + " - " + r.getString(9));
			 	select.setZpozo(r.getString(3) + " - " + r.getString(10));


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
  		codpoz = "";
  		estatus = "";
  		qls = "";
  		fecafo = new Date();
  		observ = "";
  		validarOperacion = 0;
	}
  	
    public void reset() {
    	// TODO Auto-generated method stub
    	codgra = null;   
    	codsec = null;
    	codpoz = null;
    }   
    
	/**
	 * Lista de Tipos de sector en la tabla de pozos.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
		
		 public List<String> completeSectorpozos(String query) throws NamingException,IOException { 
  	
		 List<String> results = new ArrayList<String>();
		 
		 String vquery = "SELECT A.CODSEC|| ' - ' ||B.DESSEC AS SECTOR";
		        vquery += " FROM GBORA08 A, GBORA02 B";  
		        vquery += " WHERE A.CODSEC = B.CODSEC";  
		        vquery += " GROUP BY A.CODSEC, B.DESSEC";
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
		 
			/**
			 * Lista de Tipos de pozos.
			 * 
			 * @throws NamingException
			 * @return List String
			 * @throws IOException
			 **/
				
				 public List<String> completePozo(String query) throws NamingException,IOException { 
		  	
				 if (codsec == null) {
					 codsec = "";
				 }	
					 
				 String[] vecsec = codsec.split("\\ - ", -1);
					 
				 List<String> results = new ArrayList<String>();
				 
				 String vquery = "SELECT A.CODPOZ|| ' - ' ||A.DESPOZ AS POZO";
				        vquery += " FROM GBORA08 A";  
				        vquery += " WHERE A.CODSEC = '" + vecsec[0].toUpperCase() + "'";
				        vquery += " GROUP BY A.CODPOZ, A.DESPOZ";
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
