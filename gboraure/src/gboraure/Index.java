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
import javax.sql.DataSource;

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
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
/**
 *
 * @author Mauricio
 */
@ManagedBean
@ViewScoped
public class Index extends Bd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Index> lazyModel;  
	
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Index> getLazyModel() {
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
	lazyModel  = new LazyDataModel<Index>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 7217573531435419432L;
		
        @Override
		public List<Index> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
	try {
		createBarModels();
	} catch (ClassNotFoundException | SQLException | NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	/**
	 * 
	 */	
	private String codgra = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codgra"); //Usuario logeado
	private String codsec = "";
	private String dessec = "";
	private Object filterValue = "";
	private List<Index> list = new ArrayList<Index>();
	private List<Index> list1 = new ArrayList<Index>();
	private int validarOperacion = 0;
	private String zcodgra = "";
	private String zcodsec = "";
	private String zdessec = "";
	private String zdesgra = "";
	private String zgranja = "";
	private String zdelete = "";
	//AUTOCOMPLETES//
	PntGenericaAuto consulta = new PntGenericaAuto();
	String[][] tabla;
	//GRAFICOS//
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;

	public String getCodgra() {
		return codgra;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
		this.horizontalBarModel = horizontalBarModel;
	}

	public void setCodgra(String codgra) {
		this.codgra = codgra;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codgra", codgra);
		//System.out.println("GRANJA: " + codgra);
	}

	public String getCodsec() {
		return codsec;
	}

	public void setCodsec(String codsec) {
		this.codsec = codsec;
	}

	public String getDessec() {
		return dessec;
	}

	public void setDessec(String dessec) {
		this.dessec = dessec;
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
 * Leer Datos de GBORA02
 * @throws NamingException 
* @throws IOException 
 **/ 	
	public void select(int first, int pageSize, Object filterValue) throws SQLException, ClassNotFoundException, NamingException {
  				
		//System.out.println("entre al metodo SELECT");	
		Context initContext = new InitialContext();     
		DataSource ds = (DataSource) initContext.lookup(JNDI);
		con = ds.getConnection();		
		
        if (codgra == null) {
			codgra = "";
		}
		if (codgra == "") {
			codgra= "";
		}

		String[] vecgra = codgra.split("\\ - ", -1);
		
		//Consulta paginada
     String query = "SELECT * FROM"; 
	    query += "(select query.*, rownum as rn from";
		query += "(SELECT A.CODGRA, A.CODSEC, A.DESSEC, B.DESGRA ";
	    query += " FROM GBORA02 A, GBORA01 B";
	    query += " WHERE A.CODGRA = B.CODGRA";
	    query += " AND  A.CODSEC||B.DESGRA||A.DESSEC LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
	    query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
	    query += " AND A.CODSEC  like '" + codsec.toUpperCase() + "%'";
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
 	Index select = new Index();
 	select.setZcodgra(r.getString(1));
 	select.setZcodsec(r.getString(2));
 	select.setZdessec(r.getString(3));
 	select.setZdesgra(r.getString(4));
 	select.setZgranja(r.getString(1) + " - " + r.getString(4));
	select.setZdelete(r.getString(1) + "" + r.getString(2));
   	
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
			
	        if (codgra == null) {
				codgra = "";
			}
			if (codgra == "") {
				codgra= "";
			}

			String[] vecgra = codgra.split("\\ - ", -1);			

			//Consulta no paginada
		     String query = "SELECT A.CODGRA, A.CODSEC, A.DESSEC, B.DESGRA ";
		     	    query += " FROM GBORA02 A, GBORA01 B";
		     	    query += " WHERE A.CODGRA = B.CODGRA";
		     	    query += " AND  A.CODSEC||B.DESGRA||A.DESSEC LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
		     	    query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
		     	    query += " AND A.CODSEC  like '" + codsec.toUpperCase() + "%'";
		     	    query += " ORDER BY CODGRA, CODSEC";

	    pstmt = con.prepareStatement(query);
	    //System.out.println(query);
			
	    r =  pstmt.executeQuery();
	    
	    while (r.next()){
	 	Index select = new Index();
	 	select.setZcodgra(r.getString(1));
	 	select.setZcodsec(r.getString(2));
	 	select.setZdessec(r.getString(3));
	 	select.setZdesgra(r.getString(4));
	 	select.setZgranja(r.getString(1) + " - " + r.getString(4));
	   	
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
  	
    private BarChartModel initBarModel() throws SQLException, ClassNotFoundException, NamingException{
    	
		//System.out.println("entre al metodo SELECT");	
		Context initContext = new InitialContext();     
		DataSource ds = (DataSource) initContext.lookup(JNDI);
		con = ds.getConnection();	
		
		 String query = "SELECT  DESGRA,"; 
		 query += " CODSEC,"; 
		 query += " MES,"; 
		 query += " DECODE(PIEDRAS_NEGRAS,NULL,0,PIEDRAS_NEGRAS) AS PIEDRAS_NEGRAS,"; 
		 query += " DECODE(CHARCO,NULL,0,CHARCO) AS CHARCO,"; 
		 query += " DECODE(TAMARINDO,NULL,0,TAMARINDO) AS TAMARINDO,"; 
		 query += " DECODE(CAIMANA_G,NULL,0,CAIMANA_G) AS CAIMANA_G,"; 
		 query += " DECODE(CAIMANA_C,NULL,0,CAIMANA_C) AS CAIMANA_C,"; 
		 query += " DECODE(BECERRA,NULL,0,BECERRA) AS BECERRA,"; 
		 query += " DECODE(PALO_A_PIQUE,NULL,0,PALO_A_PIQUE) AS PALO_A_PIQUE,"; 
		 query += " DECODE(LA_BANDERA,NULL,0,LA_BANDERA) AS LA_BANDERA,"; 
		 query += " DECODE(PARAISO,NULL,0,PARAISO) AS PARAISO,"; 
		 query += " DECODE(LA_UVA,NULL,0,LA_UVA) AS LA_UVA,"; 
		 query += " DECODE(VIGILANCIA,NULL,0,VIGILANCIA) AS VIGILANCIA"; 
		 query += " FROM (SELECT B.DESGRA, "; 
		 query += " A.CODSEC,"; 
		 query += " C.DESSEC, "; 
		 query += " SUM(A.CANMIL) AS CANMIL, "; 
		 query += " CASE "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '01%' THEN 'ENE' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '02%' THEN 'FEB' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '03%' THEN 'MAR' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '04%' THEN 'ABR' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '05%' THEN 'MAY' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '06%' THEN 'JUN' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '07%' THEN 'JUL' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '08%' THEN 'AGO' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '09%' THEN 'SEP' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '10%' THEN 'OCT' ";  
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '11%' THEN 'NOV' "; 
		 query += "  WHEN SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) LIKE '12%' THEN 'DIC' "; 
		 query += " END AS MES "; 
		 query += " FROM GBORA05 A LEFT OUTER JOIN GBORA01 B ON A.CODGRA = B.CODGRA "; 
		 query += "                LEFT OUTER JOIN GBORA02 C ON A.CODSEC = C.CODSEC "; 
		 query += " WHERE A.CODGRA LIKE '%' "; 
		 query += " AND A.CODSEC LIKE '%' "; 
		 query += " AND SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),7,4) LIKE TO_CHAR(SYSDATE, 'YYYY') "; 
		 query += " GROUP BY B.DESGRA, A.CODSEC, C.DESSEC, SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2) "; 
		 query += " ORDER BY A.CODSEC, C.DESSEC, SUBSTR(TO_CHAR(A.FECREG, 'DD/MM/YYYY'),4,2)) PIVOT (SUM(CANMIL) FOR DESSEC IN ('PIEDRAS NEGRAS' PIEDRAS_NEGRAS, 'CHARCO' CHARCO, 'TAMARINDO' TAMARINDO, 'CAIMANA G' CAIMANA_G, 'CAIMANA C' CAIMANA_C, 'BECERRA' BECERRA, 'PALO A PIQUE' PALO_A_PIQUE, 'LA BANDERA' LA_BANDERA, 'PARAISO' PARAISO, 'LA UVA' LA_UVA, 'VIGILANCIA' VIGILANCIA)) "; 
		 query += " ORDER BY MES, CODSEC "; 

    pstmt = con.prepareStatement(query);
    //System.out.println(query);
		
    r =  pstmt.executeQuery();
    
    while (r.next()){
 	Index select = new Index();
 	select.setZcodgra(r.getString(1));
 	select.setZcodsec(r.getString(2));
 	select.setZdessec(r.getString(3));
 	select.setZdesgra(r.getString(4));
   	
    	//Agrega la lista
    	list1.add(select);

    }
    //Cierra las conecciones
    pstmt.close();
    con.close();
    	
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
     
    private void createBarModels() throws ClassNotFoundException, SQLException, NamingException {
        createBarModel();
    }
     
    private void createBarModel() throws ClassNotFoundException, SQLException, NamingException {
        barModel = initBarModel();
         
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }    
}
