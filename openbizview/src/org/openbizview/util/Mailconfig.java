package org.openbizview.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.openbizview.getset.Mail;
import org.openbizview.util.PntGenerica;


@ManagedBean
@SessionScoped
public class Mailconfig  extends Bd {
	
	public Mailconfig() throws SQLException, NamingException, IOException{
		select();
	}

	private String usuario = "";
	private String clave = "";
	private String starttlsenable = "";
	private String auth = "";
	private String host = "";
	private String puerto = "";
	private int validarOperacion = 0;//Param guardar para validar si guarda o actualiza
	private List<Mail> list = new ArrayList<Mail>();//LIstar paises
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the starttlsenable
	 */
	public String getStarttlsenable() {
		return starttlsenable;
	}
	/**
	 * @param starttlsenable the starttlsenable to set
	 */
	public void setStarttlsenable(String starttlsenable) {
		this.starttlsenable = starttlsenable;
	}
	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}
	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the puerto
	 */
	public String getPuerto() {
		return puerto;
	}
	/**
	 * @param puerto the puerto to set
	 */
	public void setPuerto(String puerto) {
		this.puerto = puerto;
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
	 * @return the list
	 */
	public List<Mail> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Mail> list) {
		this.list = list;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
FacesMessage msj = null; 
PntGenerica consulta = new PntGenerica();
private int rows; //Registros de tabla


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
 * @return the rows
 */
public int getRows() {
	return rows;
}
/**
 * @param rows the rows to set
 */
public void setRows(int rows) {
	this.rows = rows;
}

		//Coneccion a base de datos
		//Pool de conecciones JNDI()FARM
		Connection con;
		PreparedStatement pstmt = null;
		ResultSet r;
	
	/**
     * Inserta Configuración.
     * Parametros del Metodo: String codpai, String despai. Pool de conecciones y login
	 * @throws NamingException 
     **/
    private void insert() throws NamingException {
        try {
        	 Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDIMAIL);

     		con = ds.getConnection();
    		

            String query = "INSERT INTO MAILCONFIG VALUES (?,?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, usuario.toLowerCase());
            pstmt.setString(2, clave);
            pstmt.setString(3, starttlsenable);
            pstmt.setString(4, auth);
            pstmt.setString(5, host);
            pstmt.setString(6, puerto);
            ////System.out.println(query);
            consulta.selectPntGenerica("Select usuario from mailconfig", JNDIMAIL);
            int filas = 1;
            filas = consulta.getRows() + 1;
            ////System.out.println("registros: " + filas);
            if (filas>1) {
            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("mailconfigValidacion"), "");
            } else {
            try {
                pstmt.executeUpdate();
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");
                limpiarValores();
                list.clear();
                select();
            } catch (SQLException e)  {
                 //e.printStackTrace();
                 msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            }
            }
            pstmt.close();
            con.close();           
        } catch (Exception e) {
            //e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
    
    /**
     * Borra mailconfig
     * <p>
     * @throws NamingException 
     **/  
    public void delete() throws NamingException  {  

    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	String[] chkbox = request.getParameterValues("toDelete");
    	
    	if (chkbox==null){
    		msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("del"), "");
    		FacesContext.getCurrentInstance().addMessage(null, msj); 
    	} else {
        try {

        	 Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDIMAIL);

     		con = ds.getConnection();
    		
        	
        	String param = "'" + StringUtils.join(chkbox, "','") + "'";

        	String query = "DELETE MAILCONFIG WHERE USUARIO in (" + param + ")";
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
            
            try {
                //Avisando
                pstmt.executeUpdate();
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
                limpiarValores();
                list.clear();
                select();
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
    	
    }
    
    /**
     * Actualiza mailconfig
     **/
    private void update()  {
        try {

        	 Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDIMAIL);

     		con = ds.getConnection();
    		

            String query = "UPDATE MAILCONFIG";
            	   query += " SET USUARIO = ?";
            	   query += " , CLAVE = ?";
            	   query += " , STARTTLSENABLE = ?";
            	   query += " , AUTH = ?";
            	   query += " , HOST = ?";
            	   query += " , PUERTO = ?";

            ////System.out.println(query);
            	   pstmt = con.prepareStatement(query);
                   pstmt.setString(1, usuario.toLowerCase());
                   pstmt.setString(2, clave);
                   pstmt.setString(3, starttlsenable);
                   pstmt.setString(4, auth);
                   pstmt.setString(5, host);
                   pstmt.setString(6, puerto);
            try {
                //Avisando
                pstmt.executeUpdate();
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
                }
                limpiarValores();
                list.clear();
                select();
            } catch (SQLException e) {
                e.printStackTrace();
                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL,  e.getMessage(), "");
            }

            pstmt.close();
            con.close();
              

        } catch (Exception e) {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, msj); 
    }
    
    
    /**
     * Genera las operaciones de guardar o modificar
     * @throws NamingException 
     * @throws SQLException 
     **/ 
    public void guardar() throws NamingException, SQLException{   	
    	if(validarOperacion==0){
    		insert();
    	} else {
    		update();
    	}
    }
    
    /**
     * Leer Datos de mailconfig
     * @throws NamingException 
     * @throws IOException 
     **/ 	
  	public void select() throws SQLException, NamingException, IOException {
     try {	

    	 Context initContext = new InitialContext();     
    		DataSource ds = (DataSource) initContext.lookup(JNDIMAIL);

    		con = ds.getConnection();
   		

  		//Consulta paginada
  		String query = "SELECT USUARIO, CLAVE, STARTTLSENABLE, AUTH, HOST, PUERTO";
        query += " FROM MAILCONFIG";

        
        pstmt = con.prepareStatement(query);
        //c//System.out.println(query);

         r =  pstmt.executeQuery();
        
        
        while (r.next()){
        	Mail select = new Mail();
        	select.setVusuario(r.getString(1));
        	select.setVclave(r.getString(2));
        	select.setVstarttlsenable(r.getString(3));
        	select.setVauth(r.getString(4));
        	select.setVhost(r.getString(5));
        	select.setVpuerto(r.getString(6));
        	//Agrega la lista
        	list.add(select);
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
     * Limpia los valores
     **/
	public void limpiarValores(){
		usuario = "";
		clave = "";
		starttlsenable = "";
		auth = "";
		host = "";
		puerto = "";
		validarOperacion=0;
	}
}
