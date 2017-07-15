package gboraure;

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

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean
        extends Bd {

    private String usuario;
    private String clave;
    FacesMessage msj = null;
    HttpSession sesionOk;
    HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private String sessionId;
    StringMD md = new StringMD();
    String logged = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    String loggedUsr = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("desuser");
    String Rol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol");

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario.toUpperCase();
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave.toUpperCase();
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void login() throws NamingException, ClassNotFoundException, SQLException, SchedulerException, InterruptedException, IOException {
        //System.out.println("Entre al metodo login");
    	Usuarios seg = new Usuarios();
        String[][] tabla = null;
        int rows = 0;
        seg.selectLogin(this.usuario, JNDI);
        //System.out.println(this.usuario + JNDI);
        tabla = seg.getArray();
        rows = seg.getRows();
        if (rows == 0) {
            this.msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, this.getMessage("noreg"), "");
            FacesContext.getCurrentInstance().addMessage(null, this.msj);
            this.usuario = "";
        }
        if (rows > 0) {
            String vLusuario = tabla[0][0].toUpperCase();
            String vLclave = tabla[0][1];
            //this.md.getStringMessageDigest(this.clave, "SHA-256").equals(vLclave) clave encryptada
            //this.clave.equals(vLclave)  clave sin encriptar
            if (this.usuario.equals(vLusuario) && !this.md.getStringMessageDigest(this.clave, "SHA-256").equals(vLclave)) {
            	//System.out.println("entre al IF");
            	//System.out.println("USUARIO: " + vLusuario);
            	//System.out.println("CLAVE: " + vLclave);
            	//System.out.println(this.md.getStringMessageDigest(this.clave, "SHA-256").equals(vLclave));
            	//System.out.println(this.md.getStringMessageDigest(this.clave, "SHA-256"));
            	//System.out.println(clave);
                this.msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, this.getMessage("logCl"), "");
                FacesContext.getCurrentInstance().addMessage(null, this.msj);
            } else if (this.usuario.equals(vLusuario) && this.md.getStringMessageDigest(this.clave, "SHA-256").equals(vLclave)) {
            	//System.out.println("entre al else");
            	//System.out.println("USUARIO: " + vLusuario);
            	//System.out.println("CLAVE: " + vLclave);
                this.sesionOk = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                this.sesionOk.setAttribute("usuario", (Object) this.usuario);
                this.sessionId = this.sesionOk.getId();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", this.usuario.toUpperCase());
                //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol", tabla[0][2]);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("desuser", tabla[0][2]);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", this.sessionId);
                //FacesContext.getCurrentInstance().getExternalContext().redirect("ct/index.xhtml");
                FacesContext.getCurrentInstance().getExternalContext().redirect("ct/index.xhtml");
            }
        }
    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
    }

    public String getLogged() throws IOException {
        if (!this.rq.isRequestedSessionIdValid()) {
            RequestContext.getCurrentInstance().execute("PF('idleDialog').show()");
            return null;
        }
        return this.logged.toLowerCase();
    }

    public String getLoggedUsr() throws IOException {
        if (!this.rq.isRequestedSessionIdValid()) {
            RequestContext.getCurrentInstance().execute("PF('idleDialog').show()");
            return null;
        }
        return this.loggedUsr.toUpperCase();
    }

    public String getRol() throws IOException {
        if (!this.rq.isRequestedSessionIdValid()) {
            RequestContext.getCurrentInstance().execute("PF('idleDialog').show()");
            return null;
        }
        return this.Rol;
    }
}
