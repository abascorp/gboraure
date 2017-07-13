/*
 *  Copyright (C) 2016  MAURICIO ALBANESE

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
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;

import gboraure.StringMD;

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
public class Usuarios extends Bd implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Usuarios> lazyModel;  
	
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Usuarios> getLazyModel() {
		return lazyModel;
	}	

@PostConstruct
public void init() {
	//System.out.println("entre al metodo INIT");
	
	lazyModel  = new LazyDataModel<Usuarios>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 7217573531435419432L;
		
        @Override
		public List<Usuarios> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
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
	private String coduser = "";
	private String desuser = "";
	private String cluser = "";
	private String randomKey;
	private String mail = "";
	private String id = "";
	private Object filterValue = "";
	private List<Usuarios> list = new ArrayList<Usuarios>();
	private List<Usuarios> list1 = new ArrayList<Usuarios>();
	private int validarOperacion = 0;
	StringMD md = new StringMD();
	private String zcoduser = "";
	private String zdesuser = "";
	private String zcluser = "";
	private String zmail = "";
	private String zdelete = "";
	private String zid = "";
    private int columns;
    private String[][] arr;

    public String getCluser() {
		return cluser;
	}

	public void setCluser(String cluser) {
		this.cluser = cluser;
	}

	public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public String[][] getArray() {
        return this.arr;
    }

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getZmail() {
		return zmail;
	}

	public void setZmail(String zmail) {
		this.zmail = zmail;
	}

	public String getCoduser() {
		return coduser;
	}

	public void setCoduser(String coduser) {
		this.coduser = coduser;
	}

	public String getDesuser() {
		return desuser;
	}

	public void setDesuser(String desuser) {
		this.desuser = desuser;
	}

	public String getZcoduser() {
		return zcoduser;
	}

	public void setZcoduser(String zcoduser) {
		this.zcoduser = zcoduser;
	}

	public String getZdesuser() {
		return zdesuser;
	}

	public void setZdesuser(String zdesuser) {
		this.zdesuser = zdesuser;
	}

	public String getZcluser() {
		return zcluser;
	}

	public void setZcluser(String zcluser) {
		this.zcluser = zcluser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZid() {
		return zid;
	}

	public void setZid(String zid) {
		this.zid = zid;
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
 * Inserta USUARIOS.
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
        
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        
        if(cluser.matches(pattern)) {
        	       
        String query = "INSERT INTO PUROLOMO.DBO.USUARIOS VALUES (?,?,?,?,?,CONVERT(date,'" + getFecha() + "', 103),?,CONVERT(date,'" + getFecha() + "', 103))";
        pstmt = con.prepareStatement(query);;
        pstmt.setString(1, coduser.toUpperCase());
        pstmt.setString(2, desuser.toUpperCase());
        pstmt.setString(3, md.getStringMessageDigest(cluser, StringMD.SHA256));
        pstmt.setString(4, mail.toUpperCase());
        pstmt.setString(5, login);
        pstmt.setString(6, login);            
        
        //System.out.println(query);
        //System.out.println("Coduser: " + coduser);
        //System.out.println("desuser: " + desuser);
        //System.out.println("cluser: " + cluser);
        //System.out.println("Mail: " + mail);
     
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
        } // fin del ciclo if patter
        else {
        	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("html57"), "");
        } // fin del ciclo else
        
    } catch (Exception e) {
    	e.printStackTrace();
    }	
    FacesContext.getCurrentInstance().addMessage(null, msj);
}

/**
 * Elimina USUARIOS
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

        	String query = "DELETE from PUROLOMO.DBO.USUARIOS WHERE ID in (" + param + ")";
        		        	
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
 * Actualiza USUARIOS
 * <b>Parametros del Metodo:<b> String codcat1, String descat1 unidos como un solo string.<br>
 * String pool, String login.<br><br>
 **/
public void update() throws  NamingException {
	//System.out.println("entre al metodo UPDATE");
     try { 	 
    	 Context initContext = new InitialContext();     
  		DataSource ds = (DataSource) initContext.lookup(JNDI);
  		
  		con = ds.getConnection();		

  		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        
        if(cluser.matches(pattern)) {
  		
        String query = "UPDATE PUROLOMO.DBO.USUARIOS";
         query += " SET DESUSER = ?, ";
         query += " cluser = ?, ";
         query += " MAIL = ?, ";
         query += " USRACT = ?,";
         query += " FECACT = CONVERT(date,'" + getFecha() + "', 103)";
         query += " WHERE CODUSER = ? ";

        pstmt = con.prepareStatement(query);
        pstmt.setString(1, desuser.toUpperCase());
        pstmt.setString(2, md.getStringMessageDigest(cluser, StringMD.SHA256));
        pstmt.setString(3, mail.toLowerCase());
        pstmt.setString(4, login);  
        pstmt.setString(5, coduser.toUpperCase());

        //System.out.println(query);
        //System.out.println("Coduser: " + coduser);
        //System.out.println("desuser: " + desuser);
        //System.out.println("cluser: " + cluser);
        
        // Antes de ejecutar valida si existe el registro en la base de Datos.
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
      		mail = "";
      		validarOperacion = 0;
            //limpiarValores();
        } catch (SQLException e) {
        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
        }
        pstmt.close();
        con.close();
        
        } // fin del ciclo if patter
        else {
        	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, getMessage("html57"), "");
        } // fin del ciclo else
        
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
 * Leer Datos de USUARIOS
 * @throws NamingException 
* @throws IOException 
 **/ 	
	public void select(int first, int pageSize, Object filterValue) throws SQLException, ClassNotFoundException, NamingException {
  		
		//System.out.println("entre al metodo SELECT");	
		Context initContext = new InitialContext();     
		DataSource ds = (DataSource) initContext.lookup(JNDI);
		con = ds.getConnection();		

		//Consulta paginada
	     String query = "SELECT sq1.* FROM"; 
		    query += "(select query.*, ROW_NUMBER() OVER(ORDER BY ID ASC) AS rn FROM";
			query += "(SELECT A.CODUSER, A.DESUSER, A.cluser, A.MAIL, A.ID ";
		    query += " FROM PUROLOMO.DBO.USUARIOS A ";
		    query += " WHERE A.CODUSER+A.DESUSER+A.MAIL LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
		    query += " AND A.CODUSER  like '" + coduser.toUpperCase() + "%'";
		    query += ")query ) sq1" ;
		    query += " WHERE sq1.rn <=?";
		    query += " AND sq1.rn > (?)";
		    query += " ORDER BY ID";
		
    pstmt = con.prepareStatement(query);
    pstmt.setInt(1,pageSize);
    pstmt.setInt(2,first);
    //System.out.println(query);
    //System.out.println(pageSize);
    //System.out.println(first);
		
    r =  pstmt.executeQuery();
    
    while (r.next()){
 	Usuarios select = new Usuarios();
 	select.setZcoduser(r.getString(1));
 	select.setZdesuser(r.getString(2));
 	select.setZcluser(r.getString(3));
 	select.setZmail(r.getString(4));
 	select.setZdelete(r.getString(5));
 	select.setZid(r.getString(5));
   	
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

		//Consulta paginada
     String query = "SELECT CODUSER, DESUSER, cluser, MAIL, ID ";
	    query += " FROM PUROLOMO.DBO.USUARIOS ";
	    query += " WHERE CODUSER+DESUSER+MAIL LIKE '%" + ((String) filterValue).toUpperCase() + "%'";
	    query += " AND CODUSER  like '" + coduser.toUpperCase() + "%'";
	    query += " ORDER BY ID";

    pstmt = con.prepareStatement(query);
    //System.out.println(query);
		
    r =  pstmt.executeQuery();
    
    while (r.next()){
 	Usuarios select = new Usuarios();
 	select.setZcoduser(r.getString(1));
 	select.setZdesuser(r.getString(2));
 	select.setZcluser(r.getString(3));
 	select.setZmail(r.getString(4));
 	select.setZdelete(r.getString(5));
 	select.setZid(r.getString(5));
   	
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
  		coduser = "";
  		desuser = "";
  		cluser = "";
  		id = "";
  		validarOperacion = 0;
	}
  	
    public void selectLogin(String user, String pool) throws NamingException {
    	//System.out.println("Entre al metodo select login");
        InitialContext initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup(JNDI);
        //System.out.println(JNDI);
        try {
        	//System.out.println("Entre al try");
            Connection con = ds.getConnection();
            DatabaseMetaData databaseMetaData = con.getMetaData();
            this.productName = databaseMetaData.getDatabaseProductName();
            Statement stmt = con.createStatement(1004, 1007);
            String query = "";
            //String string = this.productName;

                    query = "SELECT coduser, cluser, desuser, mail";
                    query = String.valueOf(query) + " FROM bvt002";
                    query = String.valueOf(query) + " where coduser = '" + user.toUpperCase() + "'";
                    //System.out.println(query);

            try {
            	//System.out.println("entre al try 2");
                ResultSet rs = stmt.executeQuery(query);
                this.rows = 1;
                rs.last();
                this.rows = rs.getRow();
                ResultSetMetaData rsmd = rs.getMetaData();
                this.columns = rsmd.getColumnCount();
                this.arr = new String[this.rows][this.columns];
                int i = 0;
                rs.beforeFirst();
                while (rs.next()) {
                    int j = 0;
                    while (j < this.columns) {
                        this.arr[i][j] = rs.getString(j + 1);
                        //System.out.println(rs.getString(j + 1));
                        ++j;
                    }
                    ++i;
                }
            } catch (SQLException rsmd) {
                // empty catch block
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
        	//System.out.println("entre al catch");
        }
    }	
    
   	//Recuperación de contraseñas
   	/**
     * Recuperación de contraseña por parte del usuario
     * @throws NamingException 
   	 * @throws ClassNotFoundException 
     * @throws IOException 
     **/ 
  	public void reqChgpass() throws NamingException, SQLException, ClassNotFoundException{
  			System.out.println("Entre al request chgpass");
  			try {
  	        	Context initContext = new InitialContext();     
  	        	DataSource ds = (DataSource) initContext.lookup(JNDI);
  	        	con = ds.getConnection();
  	        
  	        randomKey = "BIZ"+md.randomKey();
  	        	
  			String query = "UPDATE bvt002";
  			       query+= " set cluser = '" + md.getStringMessageDigest(randomKey.toUpperCase(), StringMD.SHA256) + "'";
  			       query+= " where coduser = '" +  coduser.toUpperCase() + "'";
  			pstmt = con.prepareStatement(query); 
  			System.out.println(query);
  			Usuarios seg = new Usuarios(); // Crea objeto para el login
  			int rows = 0;
  			// LLama al método que retorna el usuario y la contraseña
  			seg.selectLogin(coduser.toUpperCase(), JNDI);
  			rows = seg.getRows();
  			String[][] vltabla = seg.getArray();
  			System.out.println("Mail: " + vltabla[0][3].toLowerCase());
        	System.out.println("RandomKey: " + randomKey);
            //Valida que exista el usuario
  			if (rows == 0) {
  				msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("noreg"), "");

  			} else {
  			pstmt.executeUpdate();

            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("chgpass7"), "");
            	ChangePassNotification2(vltabla[0][3].toLowerCase(), randomKey);
            	
            cluser= "";
  			}
  			} catch (SQLException e) {
                e.printStackTrace();
                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL,  e.getMessage(), "");
            }
  			
  			pstmt.close();
            con.close();
              
  		FacesContext.getCurrentInstance().addMessage(null, msj); 
  	}

   	/**
     * Envía notificación a usuario al cambiar la contraseña
    **/
    private void ChangePassNotification2(String mail, String clave) {
    	System.out.println("Llame a la notification2");
    	try {
    		Context initContext = new InitialContext();     
        	Session session = (Session) initContext.lookup(JNDIMAIL);
        	System.out.println(JNDIMAIL);
    			// Crear el mensaje a enviar
    			MimeMessage mm = new MimeMessage(session);
    			// Establecer las direcciones a las que será enviado
    			// el mensaje (test2@gmail.com y test3@gmail.com en copia
    			// oculta)
    			// mm.setFrom(new
    			// InternetAddress("opennomina@dvconsultores.com"));
    			mm.addRecipient(Message.RecipientType.TO,
    					new InternetAddress(mail));
    			System.out.println("Direccion de envio establecida");
    			// Establecer el contenido del mensaje
    			mm.setSubject(getMessage("mailUserUserChgPass"));
    			mm.setText(getMessage("mailNewUserMsj2"));
    			System.out.println(getMessage("mailNewUserMsj2"));
    			// use this is if you want to send html based message
                mm.setContent("esto es una prueba", "text/html; charset=utf-8");
                //System.out.println(getMessage("mailNewUserMsj6") + " " + coduser.toUpperCase() + " / " + clave + "<br/><br/> " + getMessage("mailNewUserMsj2"));
    			// Enviar el correo electrónico
                	
    			Transport.send(mm);
    			System.out.println("pase el transport");
    			//System.out.println("Correo enviado exitosamente");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	System.out.println("Fin");
     }
   	
  	
}
