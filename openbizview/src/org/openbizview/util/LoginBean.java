package org.openbizview.util;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.quartz.SchedulerException;

@ManagedBean
@SessionScoped
public class LoginBean extends Bd {

	private String usuario;
    private String clave;
    FacesMessage msj = null;
    HttpSession sesionOk;
    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private String sessionId;
    StringMD md = new StringMD(); //Objeto encriptador
    String logged = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    String loggedUsr = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("desuser");


	public String getUsuario ()
    {
        return usuario;
    }


    public void setUsuario (final String usuario)
    {
        this.usuario = usuario.toUpperCase();
    }


    public String getClave ()
    {
        return clave;
    }


    public void setClave (final String clave)
    {
        this.clave = clave.toUpperCase();
    }
	

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}


	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	/**
	 * Leer Datos de Usuarios
	 * <p>
	 * Se conecta a la base de datos y valida el usuario y la contraseña.
	 * Adicionalmente valida si el usuario existe ne la base de datos.
	 * @throws IOException 
	 * @return String
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws SchedulerException 
	 * @throws InterruptedException 
	 * **/
	public void login() throws NamingException, ClassNotFoundException, SQLException, SchedulerException, InterruptedException, IOException {
		Bvt002 seg = new Bvt002(); // Crea objeto para el login
		String[][] tabla = null;
		int rows = 0;
		// LLama al método que retorna el usuario y la contraseña
		seg.selectLogin(usuario, JNDI);
		tabla = seg.getArray();
		rows = seg.getRows();
		System.out.println("Método");
		
		if (rows == 0) {
			msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("noreg"), "");
			FacesContext.getCurrentInstance().addMessage(null, msj);
		 usuario = "";
		}
		
		//Se asignan a dos variables String ya que retorna un arreglo y debe convertirse a String
		// y se convierte en mayúscula
		if(rows>0){
		String vLusuario = tabla[0][0].toUpperCase().toString();
		String vLclave = tabla[0][1].toString();
		System.out.println("Usuario: " + vLusuario);
		System.out.println("Clave: " + vLclave);
		
		//Valida que usuario y claves sean los mismos, realiza el login y crea la variable de session
		if(usuario.equals(vLusuario) && !md.getStringMessageDigest(clave,StringMD.SHA256).equals(vLclave)){
			System.out.println(getMessage("logCl"));
			msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("logCl"), "");
			FacesContext.getCurrentInstance().addMessage(null, msj);

		} else	if(usuario.equals(vLusuario) && md.getStringMessageDigest(clave,StringMD.SHA256).equals(vLclave)){
			System.out.println("Usuario y contraseña correctos");

			//Creando la variable de session	
			sesionOk = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sesionOk.setAttribute("usuario", usuario);
			//sesionOk.setMaxInactiveInterval(getSession());
			sessionId = sesionOk.getId();
			//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", usuario.toUpperCase());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol", tabla[0][2].toString());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("desuser", tabla[0][3].toString());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", sessionId);
			//Instancia
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("instancia", tabla[0][5].toString());
			FacesContext.getCurrentInstance().getExternalContext().redirect("/openbizview/ct/openbizview.xhtml"); 
			new Programacion().recuperarTriggers("0");
		} 
		
		}//Fin if valida que sema mayor a cero (0)
	}
	

    
    /**
     * Invalida la session y sale de la aplicación
     * @return String
     */ 
    public void logout() throws IOException{
    	//Invalida la session
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    	//Redirecciona la página
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/openbizview/login.xhtml"); 
 	}
    
    /**
     * Retorna el nombre de la sesión activa.
     * Si la sesión es null,
     * entonces llama al método logout que la invalida
     * redirecciona al logout, retornando blanco.
     * @return String
     */ 
	public String getLogged() throws IOException {		//System.out.println("Usuario Logeado:" + login);
		if (!rq.isRequestedSessionIdValid()) {
			//rq.getCurrentInstance().execute("PF('yourdialogid').show()");
			RequestContext.getCurrentInstance().execute("PF('idleDialog').show()");
			return null;
		} else {
	    return logged.toLowerCase();
		}		
	}
	
	/**
     * Retorna el nombre de la sesión activa.
     * Si la sesión es null,
     * entonces llama al método logout que la invalida
     * redirecciona al logout, retornando blanco.
     * @return String
     */ 
	public String getLoggedUsr() throws IOException {		//System.out.println("Usuario Logeado:" + login);
		if (!rq.isRequestedSessionIdValid()) {
			//rq.getCurrentInstance().execute("PF('yourdialogid').show()");
			RequestContext.getCurrentInstance().execute("PF('idleDialog').show()");
			return null;
		} else {
	    return loggedUsr.toUpperCase();
		}		
	}

    public void isLogged() throws IOException{
    	//Redirecciona la página
    	//System.out.println("Epa: " + logged);
    	if (logged!=null) {
    		FacesContext.getCurrentInstance().getExternalContext().redirect("/openbizview/ct/openbizview.xhtml"); 
    		//System.out.println("Ya se logeó, redireccionar");
	    }
    	//FacesContext.getCurrentInstance().getExternalContext().redirect("/ri/faces/jsf/index.xhtml"); 
 	}


}
