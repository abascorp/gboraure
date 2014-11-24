/*
 *  Copyright (C) 2011  ANDRES DOMINGUEZ

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

package org.openbizview.util;

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
@ManagedBean
@RequestScoped
public class Bd  {



	// Constructor	
	public Bd()  {
    }
	

	
    //Declaracion de variables para manejo de mensajes multi idioma y pais
    private String lenguaje = "es";
    private String pais = "VEN";
    private Locale  localidad = new Locale(lenguaje, pais);
    ResourceBundle recursos =  ResourceBundle.getBundle("org.openbizview.util.MessagesBundle",localidad);
    private String Message = "";
    String productName; //Manejador de base de datos
    @SuppressWarnings("unused")
	private Locale OsLang = Locale.getDefault();

    
      /**
     * Recursos de lenguaje. Archivos Properties
     **/
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
    protected static final String JNDI = ctx.getExternalContext().getInitParameter("JNDI_BD"); //"jdbc/orabiz"; //Nombre del JNDI
	static final String JNDIMAIL = ctx.getExternalContext().getInitParameter("JNDI_MAIL"); //"jdbc/orabiz"; //Nombre del JNDI
    static final String THREADNUMBER = ctx.getExternalContext().getInitParameter("THREAD_NUMBER");
   
    java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat("dd/MMM/yyyy", locale );
    java.text.SimpleDateFormat sdfDefautl = new java.text.SimpleDateFormat("dd/MM/yyyy");

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
     * Obtiene la fecha del dia, ya que se va a utilizar en todas la tablas
     * se crea el metodo.
 * @throws IOException 
     */
    public String getFecha(){
    	FacesContext ctx = FacesContext.getCurrentInstance();
        String ff =
                ctx.getExternalContext().getInitParameter("FORMAT_DATE");
    	java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat(ff, locale );
    	fecha = sdfecha.format(fecact);
        return fecha;
    }
      

	
	/**
     * Formatea la fecha leyendo el formato desde xml de configuración
     * @param La fecha a cargar 
     * @throws IOException 
     */
    public String getFechaFormat(Date pfecha) throws IOException {
    	FacesContext ctx = FacesContext.getCurrentInstance();
    	String ff =
                ctx.getExternalContext().getInitParameter("FORMAT_DATE");
    	java.text.SimpleDateFormat sdfecha = new java.text.SimpleDateFormat(ff, locale );    	 
        return sdfecha.format(pfecha);
    }
    
    
  	/*
     * Java method to sort Map in Java by value e.g. HashMap or Hashtable
     * throw NullPointerException if Map contains null values
     * It also sort values even if they are duplicates
     */
    @SuppressWarnings("rawtypes")
	public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
     
        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

            @SuppressWarnings("unchecked")
			@Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
     
        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
     
        for(Map.Entry<K,V> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
     
        return sortedMap;
    }

    
    /**
   	 * Setea compañia seleccionada para copias
   	 * @param next
   	 */
   	public void setCia(String ciacopia){
       	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cia", ciacopia);
       }
   	
    /**
   	 * Setea periodo seleccionada para copias
   	 * @param next
   	 */
   	public void setPeriodo(String periodo){
       	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("periodo", periodo);
       }
   	
   	/**
   	 * Setea escenario seleccionada para copias
   	 * @param next
   	 */
   	public void setEsc(String esc){
       	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("esc", esc);
       }
   	
   	/**
   	 * Setea escenario seleccionada para copias
   	 * @param next
   	 */
   	public void setTipneg(String tipneg){
       	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipneg", tipneg);
       }
   	
    /**
	 * Setea compañia seleccionada para copias
	 * @param next
	 */
	public void setCiaCopia(String ciacopia){
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ciacopia", ciacopia);
    }
	
	 /**
		 * Setea categoria1
		 * @param next
		 */
	public void setAccCat1(String cat1){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat1", cat1);
	}
	
	 /**
	 * Setea categoria1
	 * @param next
	 */
	public void setAccCat2(String cat2){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat2", cat2);
	}
	
	 /**
		 * Setea categoria1
		 * @param next
		 */
	public void setAccCat3(String cat3){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cat3", cat3);
	}
	
	/**
	 * Setea Rol de usuario
	 * @param next
	 */
	public void setRol(String segrol){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("segrol", segrol);
	}
	
	/**
	 * Setea uorg
	 * @param next
	 */
	public void setUorg(String uorg){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uorg", uorg);
	}
	
	/**
	 * Setea codgrup
	 * @param next
	 */
	public void setCodgrup(String codgrup){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codgrup", codgrup);
	}
	
	/**
	 * Setea dim
	 * @param next
	 */
	public void setDim(String dim){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dim", dim);
	}
	
	/**
	 * Setea list
	 * @param next
	 */
	public void setIdgrupo(String idgrupo){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idgrupo", idgrupo);
	}
	
	/**
	 * Setea list
	 * @param next
	 */
	public void setTippollo(String tippollo){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tippollo", tippollo);
	}
	
	/**
	 * Setea list
	 * @param next
	 */
	public void setTZona(String zona){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("zona", zona);
	}
	
	/**
	 * Setea usuario lista
	 * @param next
	 */
	public void setBcoduser(String bcoduser){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bcoduser", bcoduser);
	}
	
	/**
	 * Setea una fecha para busqueda
	 * @param next
	 */
	public void setFecha(String fecha){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fecha", fecha);
	}
	
	/**
	 * Setea las opciones de envio, solo para usar en mailprogramación
	 * @param next
	 */
	public void setOpcTareas(String opc){
	   	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("opc", opc);
	   if(opc.equals("2")){
		   RequestContext.getCurrentInstance().execute("PF('dlg3').show()");
	   }
	}
	
}




