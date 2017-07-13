package gboraure;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

/**
 * Crea Usuarios y clave de Base de Datos para todos los programas
 */

@ManagedBean(name = "bd")
@RequestScoped
public class Bd {

    // Constructor	
    public Bd() {
    }

    //Declaracion de variables para manejo de mensajes multi idioma y pais
    private String lenguaje = "es";
    private String pais = "VEN";
    private Locale localidad = new Locale(lenguaje, pais);
    ResourceBundle recursos = ResourceBundle.getBundle("gboraure.MessagesBundle", localidad);
    private String Message = "";
    String productName; //Manejador de base de datos
    @SuppressWarnings("unused")
    private Locale OsLang = Locale.getDefault();

    /**
     * Recursos de lenguaje. Archivos Properties
	 *
     * @param mensaje
     * @return 
     */
    public String getMessage(String mensaje) {
        Message = recursos.getString(mensaje);
        return Message;
    }

    //Declaracion de variables y manejo de mensajes
    String userLang = System.getProperty("user.language");//Identifica el lenguaje el OS
    String userCountry = System.getProperty("user.country");//Identifica el pais el OS
    Locale locale = new Locale(userLang, userCountry);//Identifica idioma y pais, por defecto le colocamos ven
    java.util.Date fecact = new java.util.Date();
    //Para trabajar con quartz properties, por alguna razón no funciona con external context
    static FacesContext ctx = FacesContext.getCurrentInstance();
    protected static final String JNDI = ctx.getExternalContext().getInitParameter("JNDI_BD"); //"jdbc/orabiz"; //Nombre del JNDI oracle local
    protected static final String JNDISERVER = ctx.getExternalContext().getInitParameter("JNDI_SERVER"); //"jdbc/orabiz"; //Nombre del JNDI sqlserver local
    static final String JNDIMAIL = ctx.getExternalContext().getInitParameter("JNDI_MAIL"); //"jdbc/orabiz"; //Nombre del JNDI mails
    static final String THREADNUMBER = ctx.getExternalContext().getInitParameter("THREAD_NUMBER");
    static final String FECHAFORMAT = ctx.getExternalContext().getInitParameter("FORMAT_DATE");
    static final String BIRT_VIEWER_WORKING_FOLDER = ctx.getExternalContext().getInitParameter("BIRT_VIEWER_WORKING_FOLDER");//Url logout
    java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat(FECHAFORMAT, locale);
    java.text.SimpleDateFormat sdfDefautl = new java.text.SimpleDateFormat("dd/MM/yyyy");
    java.text.SimpleDateFormat sdfecha2 = new java.text.SimpleDateFormat("dd/MM/yyyy", locale );
    

    String fecha = sdfecha.format(fecact); //Fecha formateada para insertar en tablas

    //Variables para uso internos de servlet
    private static final String PARAMINFOA = "dirUploadFiles"; //Uploads
    private static final String PARAMINFOB = "dirUploadRep"; //Uploads

    private int msg = 50000; //Duración de los mensajes

    /**
     * @return the msg
     */
    public int getMsg() {
        return msg;
    }

    /**
     * @return the paraminfob
     */
    public static String getPARAMINFOB() {
        return PARAMINFOB;
    }

    /**
     * @return the paraminfoa
     */
    public static String getPARAMINFOA() {
        return PARAMINFOA;
    }

    /**
     * Obtiene la fecha del dia, ya que se va a utilizar en todas la tablas se
     * crea el metodo.
     *
     * @return 
     */
    public String getFecha() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String ff
                = ctx.getExternalContext().getInitParameter("FORMAT_DATE");
        java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat(ff, locale);
        fecha = sdfecha.format(fecact);
        return fecha;
    }

    /**
     * Formatea la fecha leyendo el formato desde xml de configuración
     *
     * @param pfecha
     * @return 
     * @throws IOException
     */
    public String getFechaFormat(Date pfecha) throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String ff
                = ctx.getExternalContext().getInitParameter("FORMAT_DATE");
        java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat(ff, locale);
        return sdfecha.format(pfecha);
    }


    /*
	 * Java method to sort Map in Java by value e.g. HashMap or Hashtable
	 * throw NullPointerException if Map contains null values
	 * It also sort values even if they are duplicates
     */
    @SuppressWarnings("rawtypes")
    public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {

            @SuppressWarnings("unchecked")
            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K, V> sortedMap = new LinkedHashMap<K, V>();

        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    /**
     * Setea categoria1
     *
     * @param cat1
     */
    public void setAccCat1(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat1", cat1);
    }

    public void setAccCat7(String cat7) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat7", cat7);
    }

    public void setAccCat6(String cat6) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat6", cat6);
    }

    public void setAccCat4(String cat4) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat4", cat4);
    }

    public void setAccCat5(String cat5) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat5", cat5);
    }

    public void setNotas(String notas) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("notas", notas);
    }

    public void setAudi(String audi) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("audi", audi);
    }

    public void setActconge(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actconge", cat1);
    }

    public void setActaba(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actaba", cat1);
    }

    public void setActembutido(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actembutido", cat1);
    }

    public void setActppa(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actppa", cat1);
    }

    public void setActsub(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actsub", cat1);
    }

    public void setActdes(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actdes", cat1);
    }

    public void setActprove(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actprove", cat1);
    }

    public void setActcor(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actcor", cat1);
    }

    public void setActdis(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actdis", cat1);
    }

    public void setActcli(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("actcli", cat1);
    }

    public void setAceconge(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aceconge", cat1);
    }

    public void setAceaba(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aceaba", cat1);
    }

    public void setAceembutido(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aceembutido", cat1);
    }

    public void setAceppa(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aceppa", cat1);
    }

    public void setAcesub(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acesub", cat1);
    }

    public void setAcedes(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acedes", cat1);
    }

    public void setAceprove(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aceprove", cat1);
    }

    public void setAcecor(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acecor", cat1);
    }

    public void setAcedis(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acedis", cat1);
    }

    public void setAcecli(String cat1) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acecli", cat1);
    }

    public void setAccCat2(String cat2) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat2", cat2);
    }

    public void setAccCat3(String cat3) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat3", cat3);
    }

    public void setRol(String segrol) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("segrol", segrol);
    }

    public void setCodgrup(String codgrup) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codgrup", codgrup);
    }

    public void setIdgrupo(String idgrupo) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idgrupo", idgrupo);
    }

    public void setBcoduser(String bcoduser) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bcoduser", bcoduser);
    }

    public void setFecha(String fecha) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fecha", fecha);
    }

    public void setselec(String selec) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selec", selec);
    }

    public void setOpcTareas(String opc) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("opc", opc);
        if (opc.equals("2")) {
            RequestContext.getCurrentInstance().execute("PF('dlg3').show()");
        }
    }

}
