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
		createBarModel();
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
	private List<ChartSeries> listchart = new ArrayList<ChartSeries>();
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

	public String getCodgra() {
		return codgra;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
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
    	//System.out.println("list: " + list.size());
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
  	
    private BarChartModel initBarModel() throws SQLException, ClassNotFoundException, NamingException {
    	
		//System.out.println("entre al metodo SELECT");	
		Context initContext = new InitialContext();     
		DataSource ds = (DataSource) initContext.lookup(JNDI);
		con = ds.getConnection();	
		
		//Consulta no paginada
	     String query = "SELECT A.CODSEC, A.CANMIL, TO_CHAR(A.FECREG, 'DD/MM/YYYY') AS FECREG, B.DESSEC ";
	     	    query += " FROM GBORA05 A, GBORA02 B";
	     	    query += " WHERE A.CODSEC = B.CODSEC";
	     	    query += " AND TO_CHAR(A.FECREG, 'DD/MM/YYYY') BETWEEN '23/01/2017' AND '29/01/2017'";
	     	    query += " ORDER BY A.CODSEC, A.FECREG";

    pstmt = con.prepareStatement(query);
    //System.out.println(query);
		
    r =  pstmt.executeQuery();
 
    while (r.next()){
    ChartSeries boys = new ChartSeries();
    boys.setLabel("Cantidad de Mililitros");
 	boys.set(r.getString(4),r.getFloat(2));
 	
	//Agrega la lista
	listchart.add(boys);
	//System.out.println("listchart: " + listchart.size());

    BarChartModel model = new BarChartModel();
   
    model.addSeries(boys);
         
    return model;
        
    }
	    //Cierra las conecciones
	    pstmt.close();
	    con.close();
	    
		return barModel;		
    }
          
    private void createBarModel() throws ClassNotFoundException, SQLException, NamingException {
        barModel = initBarModel();
         
        barModel.setTitle("Grafica de lluvia de la semana");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Sector");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Mililitros");
        yAxis.setMin(0);
        yAxis.setMax(25);
    }    
}
