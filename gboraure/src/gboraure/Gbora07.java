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
public class Gbora07 extends Bd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Gbora07> lazyModel;  
	
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Gbora07> getLazyModel() {
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
	lazyModel  = new LazyDataModel<Gbora07>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 7217573531435419432L;
		
        @Override
		public List<Gbora07> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
	private String codtab = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codtab"); //Usuario logeado
	private Date fecnot = new Date();
	private String boleto = "";
	private String remesa = "";
	private String cosechadora = "";
	private String acarreo = "";
	private String cana = "";
	private String rendimiento = "";
	private String ttp = "";
	private Date fecrea = new Date();
	private String placa = "";
	private String chofer = "";
	private Object filterValue = "";
	private List<Gbora07> list = new ArrayList<Gbora07>();
	private List<Gbora07> list1 = new ArrayList<Gbora07>();
	private int validarOperacion = 0;
	private String zcodgra = "";
	private String zcodsec = "";
	private String zcodtab = "";
	private String zfecnot = "";
	private String zboleto = "";
	private String zremesa = "";
	private String zcosechadora = "";
	private String zacarreo = "";
	private String zcana = "";
	private String zrendimiento = "";
	private String zttp = "";
	private String zfecrea = "";
	private String zplaca = "";
	private String zchofer = "";
	private String zdessec = "";
	private String zsector = "";
	private String zdestab = "";
	private String ztablon = "";
	private String zdesgra = "";
	private String zgranja = "";
	private String zdelete = "";
	//AUTOCOMPLETES//
	PntGenericaAuto consulta = new PntGenericaAuto();
	String[][] tabla;

	public String getCodtab() {
		return codtab;
	}

	public void setCodtab(String codtab) {
		this.codtab = codtab;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codtab", codtab);
	}

	public Date getFecnot() {
		return fecnot;
	}

	public void setFecnot(Date fecnot) {
		this.fecnot = fecnot;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}

	public String getRemesa() {
		return remesa;
	}

	public void setRemesa(String remesa) {
		this.remesa = remesa;
	}

	public String getCosechadora() {
		return cosechadora;
	}

	public void setCosechadora(String cosechadora) {
		this.cosechadora = cosechadora;
	}

	public String getAcarreo() {
		return acarreo;
	}

	public void setAcarreo(String acarreo) {
		this.acarreo = acarreo;
	}

	public String getCana() {
		return cana;
	}

	public void setCana(String cana) {
		this.cana = cana;
	}

	public String getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(String rendimiento) {
		this.rendimiento = rendimiento;
	}

	public String getTtp() {
		return ttp;
	}

	public void setTtp(String ttp) {
		this.ttp = ttp;
	}

	public Date getFecrea() {
		return fecrea;
	}

	public void setFecrea(Date fecrea) {
		this.fecrea = fecrea;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChofer() {
		return chofer;
	}

	public void setChofer(String chofer) {
		this.chofer = chofer;
	}

	public String getZcodtab() {
		return zcodtab;
	}

	public void setZcodtab(String zcodtab) {
		this.zcodtab = zcodtab;
	}

	public String getZfecnot() {
		return zfecnot;
	}

	public void setZfecnot(String zfecnot) {
		this.zfecnot = zfecnot;
	}

	public String getZboleto() {
		return zboleto;
	}

	public void setZboleto(String zboleto) {
		this.zboleto = zboleto;
	}

	public String getZremesa() {
		return zremesa;
	}

	public void setZremesa(String zremesa) {
		this.zremesa = zremesa;
	}

	public String getZcosechadora() {
		return zcosechadora;
	}

	public void setZcosechadora(String zcosechadora) {
		this.zcosechadora = zcosechadora;
	}

	public String getZacarreo() {
		return zacarreo;
	}

	public void setZacarreo(String zacarreo) {
		this.zacarreo = zacarreo;
	}

	public String getZcana() {
		return zcana;
	}

	public void setZcana(String zcana) {
		this.zcana = zcana;
	}

	public String getZrendimiento() {
		return zrendimiento;
	}

	public void setZrendimiento(String zrendimiento) {
		this.zrendimiento = zrendimiento;
	}

	public String getZttp() {
		return zttp;
	}

	public void setZttp(String zttp) {
		this.zttp = zttp;
	}

	public String getZfecrea() {
		return zfecrea;
	}

	public void setZfecrea(String zfecrea) {
		this.zfecrea = zfecrea;
	}

	public String getZplaca() {
		return zplaca;
	}

	public void setZplaca(String zplaca) {
		this.zplaca = zplaca;
	}

	public String getZchofer() {
		return zchofer;
	}

	public void setZchofer(String zchofer) {
		this.zchofer = zchofer;
	}

	public String getZdestab() {
		return zdestab;
	}

	public void setZdestab(String zdestab) {
		this.zdestab = zdestab;
	}

	public String getZtablon() {
		return ztablon;
	}

	public void setZtablon(String ztablon) {
		this.ztablon = ztablon;
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
 * Inserta GBORA07.
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
        if (codtab == null) {
			codtab = "";
		}
		if (codtab == "") {
			codtab = "";
		}

		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] vectab = codtab.split("\\ - ", -1);
                                    
        String query = "INSERT INTO GBORA07 VALUES (1,'" + sdfecha2.format(fecnot) + "',?,?,?,?,?,?,?,?,?,'" + sdfecha2.format(fecrea) + "',?,?,?,'" + getFecha() + "',?,'" + getFecha() + "')";
        pstmt = con.prepareStatement(query);
        //pstmt.setString(1,vecgra[0].toUpperCase());
        pstmt.setString(1,vecsec[0].toUpperCase());
        pstmt.setString(2,vectab[0].toUpperCase());
        pstmt.setString(3,boleto.toUpperCase());
        pstmt.setString(4,remesa.toUpperCase());
        pstmt.setString(5,cosechadora.toUpperCase());
        pstmt.setString(6,acarreo.toUpperCase());
        pstmt.setFloat(7, Float.parseFloat(cana));
        pstmt.setFloat(8, Float.parseFloat(rendimiento));
        pstmt.setFloat(9, Float.parseFloat(ttp));
        pstmt.setString(10,placa.toUpperCase());
        pstmt.setString(11,chofer.toUpperCase());
        pstmt.setString(12, login);
        pstmt.setString(13, login);            
        
        //System.out.println(query);

        try {
        	//System.out.println("entre al try");	
            //Avisando
        	pstmt.executeUpdate();
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
            limpiarValores();                
        } catch (SQLException e)  {
            
            if (e.getMessage().trim().equals("ORA-00001: unique constraint (OPENBIZVIEW.GBORA07_PK) violated")){
            	
            	//System.out.println("se cumple la condicion y muestro el msg");
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("gbora07pkv"), "");
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
 * Elimina GBORA07
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

        	String query = "DELETE FROM GBORA07 WHERE CODGRA||CODSEC||CODTAB||TO_CHAR(FECNOT, 'DD/MM/YYYY') IN (" + param + ")";
        		        	
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
 * Actualiza GBORA07
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
		if (codtab == null) {
			codtab = "";
		}
		if (codtab == "") {
			codtab = "";
		}

		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] vectab = codtab.split("\\ - ", -1);
  		 		
        String query = "UPDATE GBORA07";
         query += " SET BOLETO = ?, ";
         query += " REMESA = ?, ";
         query += " COSECHADORA = ?, ";
         query += " ACARREO = ?, ";
         query += " CANA = ?, ";
         query += " RENDIMIENTO = ?, ";
         query += " TTP = ?, ";
         query += " FECREA = '" + sdfecha2.format(fecrea) + "',";
         query += " PLACA = ?, ";
         query += " CHOFER = ?, ";
         query += " USRACT = ?,";
         query += " FECACT = '" + getFecha() + "'";
         query += " WHERE CODGRA = 1 ";
         query += " AND CODSEC = ? ";
         query += " AND CODTAB = ? ";
         query += " AND TO_CHAR(FECNOT, 'DD/MM/YYYY') = '" + sdfecha2.format(fecnot) + "'";
         
         //System.out.println(query);
         
        pstmt = con.prepareStatement(query);

        pstmt.setString(1,boleto.toUpperCase());
        pstmt.setString(2,remesa.toUpperCase());
        pstmt.setString(3,cosechadora.toUpperCase());
        pstmt.setString(4,acarreo.toUpperCase());
        pstmt.setFloat(5, Float.parseFloat(cana));
        pstmt.setFloat(6, Float.parseFloat(rendimiento));
        pstmt.setFloat(7, Float.parseFloat(ttp));
        pstmt.setString(8,placa.toUpperCase());
        pstmt.setString(9,chofer.toUpperCase());
        pstmt.setString(10, login);
        pstmt.setString(11,vecsec[0].toUpperCase());
        pstmt.setString(12,vectab[0].toUpperCase());
        
        // Antes de ejecutar valida si existe el registro en la base de Datos.
        try {
            //Avisando
            pstmt.executeUpdate();
            if(pstmt.getUpdateCount()==0){
            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
            } else {
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
            }
            boleto = "";
            remesa = "";
            cosechadora = "";
            acarreo = "";
            cana = "";
            rendimiento = "";
            ttp = "";
            fecrea = new Date();
            placa = "";
            chofer = "";
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
 * Leer Datos de GBORA07
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
        if (codtab == null) {
			codtab = "";
		}
		if (codtab == "") {
			codtab = "";
		}
		//String[] vecgra = codgra.split("\\ - ", -1);
		String[] vecsec = codsec.split("\\ - ", -1);
		String[] vectab = codtab.split("\\ - ", -1);
		
		//Consulta paginada
     String query = "SELECT * FROM"; 
	    query += "(select query.*, rownum as rn from";
		query += "(SELECT A.CODGRA, TO_CHAR(A.FECNOT, 'DD/MM/YYYY') AS FECNOT, A.CODSEC, A.CODTAB, A.BOLETO, A.REMESA, A.COSECHADORA, A.ACARREO, A.CANA, A.RENDIMIENTO, A.TTP, TO_CHAR(A.FECREA, 'DD/MM/YYYY') AS FECREA, A.PLACA, A.CHOFER, C.DESGRA, B.DESSEC, D.DESTAB ";
	    query += " FROM GBORA07 A, GBORA02 B, GBORA01 C, GBORA04 D";
	    query += " WHERE A.CODSEC = B.CODSEC";
	    query += " AND A.CODGRA = C.CODGRA";
	    query += " AND A.CODTAB = D.CODTAB";
	    query += " AND A.BOLETO||A.REMESA||A.CANA||B.DESSEC||D.DESTAB||TO_CHAR(A.FECREA, 'DD/MM/YYYY') LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
	    //query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
	    query += " AND A.CODSEC  like '" + vecsec[0].toUpperCase() + "%'";
	    query += " AND A.CODTAB  like '" + vectab[0].toUpperCase() + "%'";
	    query += ")query )" ;
	    query += " WHERE rownum <=?";
	    query += " AND rn > (?)";
	    query += " ORDER BY CODGRA, CODSEC, CODTAB";

    pstmt = con.prepareStatement(query);
    pstmt.setInt(1,pageSize);
    pstmt.setInt(2,first);
    //System.out.println(query);
    //System.out.println(pageSize);
    //System.out.println(first);
		
    r =  pstmt.executeQuery();
    
    while (r.next()){
 	Gbora07 select = new Gbora07();
 	select.setZcodgra(r.getString(1));
 	select.setZfecnot(r.getString(2));
 	select.setZcodsec(r.getString(3));
 	select.setZcodtab(r.getString(4));
 	select.setZboleto(r.getString(5));
 	select.setZremesa(r.getString(6));
 	select.setZcosechadora(r.getString(7));
 	select.setZacarreo(r.getString(8));
 	select.setZcana(r.getString(9));
 	select.setZrendimiento(r.getString(10));
 	select.setZttp(r.getString(11));
 	select.setZfecrea(r.getString(12));
 	select.setZplaca(r.getString(13));
 	select.setZchofer(r.getString(14));
 	select.setZdesgra(r.getString(15));
 	select.setZdessec(r.getString(16));
 	select.setZdestab(r.getString(17));
 	select.setZgranja(r.getString(1) + " - " + r.getString(15));
 	select.setZsector(r.getString(3) + " - " + r.getString(16));
 	select.setZtablon(r.getString(4) + " - " + r.getString(17));
	select.setZdelete(r.getString(1) + "" + r.getString(3) + "" + r.getString(4) + "" + r.getString(2));
   	
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
	        if (codtab == null) {
				codtab = "";
			}
			if (codtab == "") {
				codtab = "";
			}
			//String[] vecgra = codgra.split("\\ - ", -1);
			String[] vecsec = codsec.split("\\ - ", -1);
			String[] vectab = codtab.split("\\ - ", -1);

			//Consulta no paginada
		     String query = "SELECT A.CODGRA, TO_CHAR(A.FECNOT, 'DD/MM/YYYY') AS FECNOT, A.CODSEC, A.CODTAB, A.BOLETO, A.REMESA, A.COSECHADORA, A.ACARREO, A.CANA, A.RENDIMIENTO, A.TTP, TO_CHAR(A.FECREA, 'DD/MM/YYYY') AS FECREA, A.PLACA, A.CHOFER, C.DESGRA, B.DESSEC, D.DESTAB ";
			    query += " FROM GBORA07 A, GBORA02 B, GBORA01 C, GBORA04 D";
			    query += " WHERE A.CODSEC = B.CODSEC";
			    query += " AND A.CODGRA = C.CODGRA";
			    query += " AND A.CODTAB = D.CODTAB";
			    query += " AND A.BOLETO||A.REMESA||A.CANA||B.DESSEC||D.DESTAB||TO_CHAR(A.FECREA, 'DD/MM/YYYY') LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
			    //query += " AND A.CODGRA  like '" + vecgra[0].toUpperCase() + "%'";
			    query += " AND A.CODSEC  like '" + vecsec[0].toUpperCase() + "%'";
			    query += " AND A.CODTAB  like '" + vectab[0].toUpperCase() + "%'";
		     	query += " ORDER BY CODGRA, CODSEC, CODTAB";

	    pstmt = con.prepareStatement(query);
	    //System.out.println(query);
			
	    r =  pstmt.executeQuery();
	    
	    while (r.next()){
	 	Gbora07 select = new Gbora07();
	 	select.setZcodgra(r.getString(1));
	 	select.setZfecnot(r.getString(2));
	 	select.setZcodsec(r.getString(3));
	 	select.setZcodtab(r.getString(4));
	 	select.setZboleto(r.getString(5));
	 	select.setZremesa(r.getString(6));
	 	select.setZcosechadora(r.getString(7));
	 	select.setZacarreo(r.getString(8));
	 	select.setZcana(r.getString(9));
	 	select.setZrendimiento(r.getString(10));
	 	select.setZttp(r.getString(11));
	 	select.setZfecrea(r.getString(12));
	 	select.setZplaca(r.getString(13));
	 	select.setZchofer(r.getString(14));
	 	select.setZdesgra(r.getString(15));
	 	select.setZdessec(r.getString(16));
	 	select.setZdestab(r.getString(17));
	 	select.setZgranja(r.getString(1) + " - " + r.getString(15));
	 	select.setZsector(r.getString(3) + " - " + r.getString(16));
	 	select.setZtablon(r.getString(4) + " - " + r.getString(17));
		select.setZdelete(r.getString(1) + "" + r.getString(3) + "" + r.getString(4) + "" + r.getString(2));
	   		   	
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
        boleto = "";
        remesa = "";
        cosechadora = "";
        acarreo = "";
        cana = "";
        rendimiento = "";
        ttp = "";
        fecrea = new Date();
        placa = "";
        chofer = "";
        validarOperacion = 0;
	}
  	
    public void reset() {
    	// TODO Auto-generated method stub
    	//codgra = null;   
    	codsec = null;
    	codtab = null;
    }   
    
	/**
	 * Lista de Sectores.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
		
		 public List<String> completeTablon(String query) throws NamingException,IOException { 
  	
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
		 
		 String vquery = "SELECT A.CODTAB|| ' - ' ||A.DESTAB AS TABLON";
		        vquery += " FROM GBORA04 A";  
		        //vquery += " WHERE A.CODGRA = '" + vecgra[0].toUpperCase() + "'";
		        vquery += " WHERE A.CODSEC = '" + vecsec[0].toUpperCase() + "'";
		        vquery += " GROUP BY A.CODTAB, A.DESTAB";
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
