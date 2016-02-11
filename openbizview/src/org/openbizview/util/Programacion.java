package org.openbizview.util;

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
    
    Clase para programar tareas de envio
 */

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterDefn;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.ReportEngine;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerKey.*;
import static org.quartz.DateBuilder.*;
import static org.quartz.JobKey.*;





@ManagedBean
@ViewScoped
public class Programacion extends Bd implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private LazyDataModel<Programacion> lazyModel;  
	
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Programacion> getLazyModel() {
		return lazyModel;
	}	
	
	//Constructor

	public Programacion() throws SchedulerException {
		//System.out.println("Estatus TRIGGER1: " + schd.checkExists(triggerKey("TRIGGER1", "unico")));
		//System.out.println("Estatus TRIGGER2: " + schd.checkExists(triggerKey("TRIGGER2", "unico")));
		//System.out.println("Estatus TRIGGER3: " + schd.checkExists(triggerKey("TRIGGER3", "unico")));	

		String[] vecreporte = reporte.split("\\ - ", -1);
		inputs = new String[paramsNumber(vecreporte[0])];
		lazyModel  = new LazyDataModel<Programacion>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7217573531435419432L;
			
            @Override
			public List<Programacion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
            	//Filtro
            	if (filters != null) {
               	 for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
               		 String filterProperty = it.next(); // table column name = field name
                     filterValue = filters.get(filterProperty);
               	 }
                }
            	try { 
            		//Consulta
					select(first, pageSize,sortField, filterValue);
					//Counter
					counter(filterValue);
					//Contador lazy
					lazyModel.setRowCount(rows);  //Necesario para crear la paginación
				} catch (SQLException | NamingException e) {	
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
	
	public void reset() {
		setOpcTareas("1");
    }
	
	//public void init() throws SchedulerException, NamingException{
	//	recuperarTriggers();
	//}
	private String session = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session"); //Session
	private String instancia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("instancia"); //Usuario logeado
	private String tarea = ""; //Nombre de la tarea
    private String vltrigger = ""; //Nombre del disparador
    private String diasemana = ""; //Día de la semana
    private String hora = ""; //Hora de la semana
    private String minuto = ""; //Minuto de la semana
    private String frecuencia = ""; //Fercuencia de repetición
    private String dias = ""; // Días de repetición del reporte
    private String diames = "1"; //Día del mes personalizado
    private String horarepeticion = "0";
    private String asunto = "";
    private String contenido = "";
    private Date diainicio = new Date();
    private Object filterValue = "";
	private List<Programacion> list = new ArrayList<Programacion>();
    private int rows;
    FacesMessage msj = null; 
    PntGenerica consulta = new PntGenerica();
    private String idgrupo = "";
    private String reporte = "";
    ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext(); //Toma ruta real de la aplicación
	private String RUTA_REPORTE = File.separator + "reportes";
	private String RUTA_LOGS = File.separator + "logs";
	SchedulerFactory schdFact = new StdSchedulerFactory();
    Scheduler schd = schdFact.getScheduler();
    java.util.Date fechadia = new java.util.Date();
    
  //Programación lista tabla
  	private String vdisparador;
  	private String vgrupo;
  	private String vdiasem;
  	private String vhora;
  	private String vminuto;
  	private String vfrecuencia;
  	private String vfrecuenciades;
  	private String vasunto;
  	private String vcontenido;
  	private String vcodrep;
  	private String vjob;
  	private String vidgrupo;
  	private String viddesidgrupo;
  	private String vdiames;
    private String vdiainicio;
    private String vactivadetalletb;
    
    //Para detener las tareas
    private String activa;
    private String clase;
    
    
    //Variables para tomar los nombres de parámetros y pasarlos a la tabla
    private String[][] vlTabla;
    String arregloParametros; //Toma el valor del arreglo de los parámetros del reporte  
    String arregloNombreParametros; //Toma el valor del arreglo de los parámetros del reporte  
    
    //Opciones de envío
    private String opctareas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("opc"); 
    private String ruta_salida; //Directorio de envío
    
    //Formatos
    private String formato;
    private String vformato;
    
    
    
    
    
	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}

	/**
	 * @param formato the formato to set
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}

	/**
	 * @return the vformato
	 */
	public String getVformato() {
		return vformato;
	}

	/**
	 * @param vformato the vformato to set
	 */
	public void setVformato(String vformato) {
		this.vformato = vformato;
	}

	/**
	 * @return the ruta_salida
	 */
	public String getRuta_salida() {
		return ruta_salida;
	}

	/**
	 * @param ruta_salida the ruta_salida to set
	 */
	public void setRuta_salida(String ruta_salida) {
		this.ruta_salida = ruta_salida;
	}

	/**
	 * @return the opctareas
	 */
	public String getOpctareas() {
		return opctareas;
	}

	/**
	 * @param opctareas the opctareas to set
	 */
	public void setOpctareas(String opctareas) {
		this.opctareas = opctareas;
	}

	/**
	 * @return the vactivadetalletb
	 */
	public String getVactivadetalletb() {
		return vactivadetalletb;
	}

	/**
	 * @param vactivadetalletb the vactivadetalletb to set
	 */
	public void setVactivadetalletb(String vactivadetalletb) {
		this.vactivadetalletb = vactivadetalletb;
	}

	/**
	 * @return the activa
	 */
	public String getActiva() {
		return activa;
	}

	/**
	 * @param activa the activa to set
	 */
	public void setActiva(String activa) {
		this.activa = activa;
	}

	
	
	/**
	 * @return the clase
	 */
	public String getClase() {
		return clase;
	}

	/**
	 * @param clase the clase to set
	 */
	public void setClase(String clase) {
		this.clase = clase;
	}

	/**
	 * @return the vdisparador
	 */
	public String getVdisparador() {
		return vdisparador;
	}

	/**
	 * @param vdisparador the vdisparador to set
	 */
	public void setVdisparador(String vdisparador) {
		this.vdisparador = vdisparador;
	}

	/**
	 * @return the vgrupo
	 */
	public String getVgrupo() {
		return vgrupo;
	}

	/**
	 * @param vgrupo the vgrupo to set
	 */
	public void setVgrupo(String vgrupo) {
		this.vgrupo = vgrupo;
	}

	/**
	 * @return the vdiasem
	 */
	public String getVdiasem() {
		return vdiasem;
	}

	/**
	 * @param vdiasem the vdiasem to set
	 */
	public void setVdiasem(String vdiasem) {
		this.vdiasem = vdiasem;
	}

	/**
	 * @return the vhora
	 */
	public String getVhora() {
		return vhora;
	}

	/**
	 * @param vhora the vhora to set
	 */
	public void setVhora(String vhora) {
		this.vhora = vhora;
	}

	/**
	 * @return the vminuto
	 */
	public String getVminuto() {
		return vminuto;
	}

	/**
	 * @param vminuto the vminuto to set
	 */
	public void setVminuto(String vminuto) {
		this.vminuto = vminuto;
	}

	/**
	 * @return the vfrecuencia
	 */
	public String getVfrecuencia() {
		return vfrecuencia;
	}

	/**
	 * @param vfrecuencia the vfrecuencia to set
	 */
	public void setVfrecuencia(String vfrecuencia) {
		this.vfrecuencia = vfrecuencia;
	}

	/**
	 * @return the vfrecuenciades
	 */
	public String getVfrecuenciades() {
		return vfrecuenciades;
	}

	/**
	 * @param vfrecuenciades the vfrecuenciades to set
	 */
	public void setVfrecuenciades(String vfrecuenciades) {
		this.vfrecuenciades = vfrecuenciades;
	}

	/**
	 * @return the vasunto
	 */
	public String getVasunto() {
		return vasunto;
	}

	/**
	 * @param vasunto the vasunto to set
	 */
	public void setVasunto(String vasunto) {
		this.vasunto = vasunto;
	}

	/**
	 * @return the vcontenido
	 */
	public String getVcontenido() {
		return vcontenido;
	}

	/**
	 * @param vcontenido the vcontenido to set
	 */
	public void setVcontenido(String vcontenido) {
		this.vcontenido = vcontenido;
	}

	/**
	 * @return the vcodrep
	 */
	public String getVcodrep() {
		return vcodrep;
	}

	/**
	 * @param vcodrep the vcodrep to set
	 */
	public void setVcodrep(String vcodrep) {
		this.vcodrep = vcodrep;
	}

	/**
	 * @return the vjob
	 */
	public String getVjob() {
		return vjob;
	}

	/**
	 * @param vjob the vjob to set
	 */
	public void setVjob(String vjob) {
		this.vjob = vjob;
	}

	/**
	 * @return the vidgrupo
	 */
	public String getVidgrupo() {
		return vidgrupo;
	}

	/**
	 * @param vidgrupo the vidgrupo to set
	 */
	public void setVidgrupo(String vidgrupo) {
		this.vidgrupo = vidgrupo;
	}

	/**
	 * @return the viddesidgrupo
	 */
	public String getViddesidgrupo() {
		return viddesidgrupo;
	}

	/**
	 * @param viddesidgrupo the viddesidgrupo to set
	 */
	public void setViddesidgrupo(String viddesidgrupo) {
		this.viddesidgrupo = viddesidgrupo;
	}

	/**
	 * @return the vdiames
	 */
	public String getVdiames() {
		return vdiames;
	}

	/**
	 * @param vdiames the vdiames to set
	 */
	public void setVdiames(String vdiames) {
		this.vdiames = vdiames;
	}

	/**
	 * @return the vdiainicio
	 */
	public String getVdiainicio() {
		return vdiainicio;
	}

	/**
	 * @param vdiainicio the vdiainicio to set
	 */
	public void setVdiainicio(String vdiainicio) {
		this.vdiainicio = vdiainicio;
	}

	/**
	 * @return the filterValue
	 */
	public Object getFilterValue() {
		return filterValue;
	}

	/**
	 * @param filterValue the filterValue to set
	 */
	public void setFilterValue(Object filterValue) {
		this.filterValue = filterValue;
	}

	

	/**
	 * @return the list
	 */
	public List<Programacion> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Programacion> list) {
		this.list = list;
	}

	/**
	 * @param lazyModel the lazyModel to set
	 */
	public void setLazyModel(LazyDataModel<Programacion> lazyModel) {
		this.lazyModel = lazyModel;
	}

	/**
	 * @return the idgrupo
	 */
	public String getIdgrupo() {
		return idgrupo;
	}

	/**
	 * @param idgrupo the idgrupo to set
	 */
	public void setIdgrupo(String idgrupo) {
		this.idgrupo = idgrupo;
	}

	

	/**
	 * @return the tarea
	 */
	public String getTarea() {
		return tarea;
	}

	/**
	 * @param tarea the tarea to set
	 */
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	
	


	/**
	 * @return the vltrigger
	 */
	public String getVltrigger() {
		return vltrigger;
	}

	/**
	 * @param vltrigger the vltrigger to set
	 */
	public void setVltrigger(String vltrigger) {
		this.vltrigger = vltrigger;
	}
	

	/**
	 * @return the diasemana
	 */
	public String getDiasemana() {
		return diasemana;
	}

	/**
	 * @param diasemana the diasemana to set
	 */
	public void setDiasemana(String diasemana) {
		this.diasemana = diasemana;
	}

	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @return the minuto
	 */
	public String getMinuto() {
		return minuto;
	}

	/**
	 * @param minuto the minuto to set
	 */
	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}
	
	

	/**
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * @param frecuencia the frecuencia to set
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

    
	
	
	
	/**
	 * @return the dias
	 */
	public String getDias() {
		return dias;
	}


	/**
	 * @param dias the dias to set
	 */
	public void setDias(String dias) {
		this.dias = dias;
	}


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
	
	
	

	/**
	 * @return the reporte
	 */
	public String getReporte() {
		return reporte;
	}

	/**
	 * @param reporte the reporte to set
	 */
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	
	

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	   
    
    /**
	 * @return the diames
	 */
	public String getDiames() {
		return diames;
	}


	/**
	 * @param diames the diames to set
	 */
	public void setDiames(String diames) {
		this.diames = diames;
	}

	

	/**
	 * @return the diainicio
	 */
	public Date getDiainicio() {
		return diainicio;
	}


	/**
	 * @param diainicio the diainicio to set
	 */
	public void setDiainicio(Date diainicio) {
		this.diainicio = diainicio;
	}
	
	


	/**
	 * @return the horarepeticion
	 */
	public String getHorarepeticion() {
		return horarepeticion;
	}

	/**
	 * @param horarepeticion the horarepeticion to set
	 */
	public void setHorarepeticion(String horarepeticion) {
		this.horarepeticion = horarepeticion;
	}
	
	
    

	/**
	 * @return the vlTabla
	 */
	public String[][] getVlTabla() {
		return vlTabla;
	}

	/**
	 * @param vlTabla the vlTabla to set
	 */
	public void setVlTabla(String[][] vlTabla) {
		this.vlTabla = vlTabla;
	}

	/**
	* Metodo que da la informacion mas detallada sobre el horario, como hora de inicio de la tarea y cada
    * cuanto tiempo se ejecute la tarea
    */
    public void iniciarTarea() throws NamingException {
    	//
    	if(hora.length()==1){
 			hora = "0"+hora;
 		}
 		if(minuto.length()==1){
 			minuto = "0"+minuto;
 		}	
 		String[] vecreporte = reporte.split("\\ - ", -1);
 		arregloParametros =  StringUtils.join(inputs, "|").toUpperCase();
 		
    	 if(frecuencia.equals("0")){    		 
    	   iniciarTareaDiaria(tarea.toUpperCase(), vltrigger.toUpperCase(), hora, minuto, arregloParametros, "0", arregloParamNames(vecreporte[0]));
    	 } else if (frecuencia.equals("1")){
    	   iniciarTareaSemanal(tarea.toUpperCase(), vltrigger.toUpperCase(), dias, hora, minuto, arregloParametros, "0", arregloParamNames(vecreporte[0]));
    	 } else if (frecuencia.equals("2")){
    	   iniciarTareaDiaMes(tarea.toUpperCase(), vltrigger.toUpperCase(), diames, hora, minuto, arregloParametros, "0", arregloParamNames(vecreporte[0]));	
    	 } else if (frecuencia.equals("3")){
    	   iniciarTareaIntervaloHora(tarea.toUpperCase(), vltrigger.toUpperCase(), diames, hora, minuto, horarepeticion, arregloParametros, "0", arregloParamNames(vecreporte[0]));	
    	 } else {
    	   iniciarTareaIntervaloMensual(tarea.toUpperCase(), vltrigger.toUpperCase(), frecuencia, hora, minuto, arregloParametros, "0", arregloParamNames(vecreporte[0]));	 
    	 }
    //	}
    }
    
    
    
    /**Programa tarea diaria
     * @throws NamingException */
    private void iniciarTareaDiaria(String ptarea, String ptrigger, String phora, String pminuto, String paramvalues, String isrecupera, String paramnames) throws NamingException {
    	////System.out.println("Iniciando tarea diaria");
        //Definir la instancia del job
    	JobDetail job = newJob(TareaInvocar.class)
    			.withIdentity(ptarea.toUpperCase(), "unico")
    			.build();
    	//Define el Trigger y la frecuencia en que se va a ejecutar
    	Trigger trigger = newTrigger()
    		    .withIdentity(ptrigger.toUpperCase(), "unico")
    		    .startNow()
    		    .withSchedule(dailyAtHourAndMinute(Integer.parseInt(phora), Integer.parseInt(pminuto)))
    		    .build();
  		
    	try {
    		//Inicia el cronograma
			schd.start();
			schd.scheduleJob(job, trigger);
			msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("mailtareamsgexito") + " " + ptrigger.toUpperCase(), "");
			if(isrecupera=="0"){
			insert("0", "0", paramvalues, "0", paramnames);
			}
			deleteTemps(session, "bvtparams_temp");
			deleteTemps(session, "bvtparams_number_temp");
			limpiar();
    	} catch (SchedulerException e) {
			e.printStackTrace();
			msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
		}
    	FacesContext.getCurrentInstance().addMessage(null, msj);
    }	
    
    
  
    
    /**Programa tarea semanal
     * @throws NamingException */
    private void iniciarTareaSemanal(String ptarea, String ptrigger, String pdias, String phora, String pminuto, String paramvalues, String isrecupera, String paramnames) throws NamingException {

        //Definir la instancia del job
    	JobDetail job = newJob(TareaInvocar.class)
    			.withIdentity(ptarea.toUpperCase(), "unico")
    			.build();
    	//Define el Trigger y la frecuencia en que se va a ejecutar
    	Trigger trigger = newTrigger()
    		    .withIdentity(ptrigger.toUpperCase(), "unico")
    		    .startNow()
    		    .withSchedule(weeklyOnDayAndHourAndMinute(Integer.parseInt(pdias), Integer.parseInt(phora), Integer.parseInt(pminuto)))
    		    .build();
  		
    	try {
    		//Inicia el cronograma
			schd.start();
			schd.scheduleJob(job, trigger);
			msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("mailtareamsgexitosemanal") + " " + ptrigger.toUpperCase(), "");
			if(isrecupera=="0"){
			insert(dias, "0", paramvalues, "0", paramnames);
			}
			deleteTemps(session, "bvtparams_temp");
			deleteTemps(session, "bvtparams_number_temp");
			limpiar();
    	} catch (SchedulerException e) {
			e.printStackTrace();
			msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
		}
    	FacesContext.getCurrentInstance().addMessage(null, msj);
    }	
    
    
    /**Programa tarea diaria usando un cron, un día del mes especifico con repeticion
     * @throws NamingException */
    private void iniciarTareaDiaMes(String ptarea, String ptrigger, String pdiames, String phora, String pminuto, String paramvalues, String isrecupera, String paramnames) throws NamingException {

        //Definir la instancia del job
    	JobDetail job = newJob(TareaInvocar.class)
    			.withIdentity(ptarea.toUpperCase(), "unico")
    			.build();
    	//Define el Trigger y la frecuencia en que se va a ejecutar
    	CronTrigger trigger = newTrigger()
    		    .withIdentity(ptrigger.toUpperCase(), "unico")
    		    .withSchedule(cronSchedule("0 " + pminuto + " " + phora + " "+ pdiames + " * ?"))
    		    .build();
  		
    	try {
    		//Inicia el cronograma
			schd.start();
			schd.scheduleJob(job, trigger);
			msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("mailtareamsgexitodiaria") + " " + ptrigger.toUpperCase(), "");
			if(isrecupera=="0"){
			insert(dias, pdiames, paramvalues, "0", paramnames);
			}
			deleteTemps(session, "bvtparams_temp");
			deleteTemps(session, "bvtparams_number_temp");
			limpiar();
    	} catch (SchedulerException e) {
			e.printStackTrace();
			msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
		}
    	FacesContext.getCurrentInstance().addMessage(null, msj);
    }	
    

    /**Programa tarea por intervalo mensual puede ser , Mensual, Bimensual o trimestral
     * @throws NamingException */
    private void iniciarTareaIntervaloMensual(String ptarea, String ptrigger, String pfrecuencia, String phora, String pminuto, String paramvalues, String isrecupera, String paramnames) throws NamingException {
    		
    	
    	 //Creamos la instancia calendario para separar en dia, mes y año la fecha seleccionada
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTime(diainicio);
    	 int anio = cal.get(Calendar.YEAR);
    	 int vmes = cal.get(Calendar.MONTH); //Para iniciar meses comenzando desde enero = 0
    	 int dia = cal.get(Calendar.DAY_OF_MONTH);
    	 
        //Definir la instancia del job
    	JobDetail job = newJob(TareaInvocar.class)
    			.withIdentity(ptarea.toUpperCase(), "unico")
    			.build();
    	//Define el Trigger y la frecuencia en que se va a ejecutar
    	Trigger trigger = newTrigger()
    		    .withIdentity(ptrigger.toUpperCase(), "unico")
    		     .startAt(dateOf(Integer.parseInt(phora), //Hora de inicio
    		    	       Integer.parseInt(pminuto), //Minutos de inicio
    		    	       00, //Segundos de inicio
    		    	       dia, //Dia de inicio
    		    	       vmes, //Mes de Inicio
    		    	       anio)) //Año de inicio,Get a Date object that represents the given time, on the  given date.
    		    .withSchedule(calendarIntervalSchedule()
    		            .withIntervalInMonths(1)) // interval is set in calendar months
    		    .build();
  		
    	try {
    		//Inicia el cronograma
			schd.start();
			schd.scheduleJob(job, trigger);
			msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("mailtareamsgexitoMesIntervalo") + " " + ptrigger.toUpperCase(), "");
			if(isrecupera=="0"){
			insert(dias, "0", paramvalues, "0", paramnames);
			}
			deleteTemps(session, "bvtparams_temp");
			deleteTemps(session, "bvtparams_number_temp");
			limpiar();
    	} catch (SchedulerException e) {
			e.printStackTrace();
			msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
		}
    	FacesContext.getCurrentInstance().addMessage(null, msj);
    	
    }	
    
    
    /**Programa tarea por intervalo por hora puede ser , Mensual, Bimensual o trimestral
     * @throws NamingException */
    private void iniciarTareaIntervaloHora(String ptarea, String ptrigger, String pfrecuencia, String phora, String pminuto, String phorarepeticion, String paramvalues, String isrecupera, String paramnames) throws NamingException {
    		
    	
    	 //Creamos la instancia calendario para separar en dia, mes y año la fecha seleccionada
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTime(diainicio);
    	 int anio = cal.get(Calendar.YEAR);
    	 int vmes = cal.get(Calendar.MONTH); //Para iniciar meses comenzando desde enero = 0
    	 int dia = cal.get(Calendar.DAY_OF_MONTH);
    	 
        //Definir la instancia del job
    	JobDetail job = newJob(TareaInvocar.class)
    			.withIdentity(ptarea.toUpperCase(), "unico")
    			.build();
    	//Define el Trigger y la frecuencia en que se va a ejecutar
    	Trigger trigger = newTrigger()
    		    .withIdentity(ptrigger.toUpperCase(), "unico")
    		     .startAt(dateOf(Integer.parseInt(phora), //Hora de inicio
    		    	       Integer.parseInt(pminuto), //Minutos de inicio
    		    	       00, //Segundos de inicio
    		    	       dia, //Dia de inicio
    		    	       vmes, //Mes de Inicio
    		    	       anio)) //Año de inicio,Get a Date object that represents the given time, on the  given date.
    		    .withSchedule(calendarIntervalSchedule().withIntervalInHours(Integer.parseInt(phorarepeticion))) // interval is set in calendar months
    		    .build();
  		
    	try {
    		//Inicia el cronograma
			schd.start();
			schd.scheduleJob(job, trigger);
			msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("mailtareamsgexitoMesIntervalo") + " " + ptrigger.toUpperCase(), "");
			insert("0", "0", paramvalues, phorarepeticion, paramnames);
			if(isrecupera=="0"){
			deleteTemps(session, "bvtparams_temp");
			}
			deleteTemps(session, "bvtparams_number_temp");
			limpiar();
    	} catch (SchedulerException e) {
			e.printStackTrace();
			msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
		}
    	FacesContext.getCurrentInstance().addMessage(null, msj);
    	
    }	
    
  
    
    
    /**Detiene la tarea
     * @throws NamingException */ 
   public void detenerTarea() throws NamingException  {
	   try {
		//schd.unscheduleJob(triggerKey(vltrigger.toUpperCase(), "unico")); 
		schd.deleteJob(jobKey(vltrigger.toUpperCase(), "unico"));//Reimplementación, anteriormente solo la quitaba de la tarea no la borraba
		                                         //Nuevo cambio 20/09/2014
		delete();
		msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("mailtareaborrada"), "");
		FacesContext.getCurrentInstance().addMessage(null, msj);
	} catch (SchedulerException e) {
		e.printStackTrace();
		msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
    	FacesContext.getCurrentInstance().addMessage(null, msj);
	} 	
   }
   
   /**Detiene la tarea para recargarla
    * Usada en el método iniar tarea
    * @throws NamingException */ 
   /*
  private void detenerTarea_Recargar() throws NamingException  {
	   try {
		//schd.unscheduleJob(triggerKey(vltrigger.toUpperCase(), "unico")); 
		schd.deleteJob(jobKey(vltrigger.toUpperCase(), "unico"));//Reimplementación, anteriormente solo la quitaba de la tarea no la borraba
		                                         //Nuevo cambio 20/09/2014
		delete();
		//msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("mailtareaborrada"), "");
		//FacesContext.getCurrentInstance().addMessage(null, msj);
	} catch (SchedulerException e) {
		e.printStackTrace();
		//msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
   	    //FacesContext.getCurrentInstance().addMessage(null, msj);
	} 	
  }*/
   
   
   /**Si el servidor se reinicia o la tarea no está activa se inicia desde los 
    * registros de la base de datos
 * @throws SchedulerException
 * @throws NamingException 
 * @throws IOException **/
   protected void recuperarTriggers(String estatus) throws SchedulerException, NamingException{
	   consulta.selectPntGenerica("select job, disparador, grupo, diasem, substr(hora,1,2), substr(hora,4,2), frecuencia, diames, PARAMVALUES, intervalo, paramnames from t_programacion where activa = '" + estatus + "' order by disparador", JNDI);
	   int rows = consulta.getRows();
	   String[][] vltabla = consulta.getArray();
	   
	   if(rows>0){//Si la consulta es mayor a cero comienza a recorrerla para verificar si la tarea existe en ram, si no la reactiva
		          //desde la base de datos
		   for(int i=0; i<rows; i++){//Recorre los triggers almacenados y verifica por cada uno
			   if(schd.checkExists(triggerKey(vltabla[i][1].toUpperCase(), "unico"))==false){//Si la tarea no existe la crea
				   //Opción para generar la tarea diaria
				   if(vltabla[i][6].toString().equals("0")){
					   //Tarea diaria
					   iniciarTareaDiaria(vltabla[i][0].toUpperCase(), vltabla[i][1].toUpperCase(), vltabla[i][4],vltabla[i][5], vltabla[i][8], "1", vltabla[i][9]);				    	
				   } else if(vltabla[i][6].toString().equals("1")){//Fin valida que sea diaria, de lo contrario es semanal
					   //Tarea semanal
					   iniciarTareaSemanal(vltabla[i][0].toUpperCase(), vltabla[i][1].toUpperCase(), vltabla[i][3], vltabla[i][4],vltabla[i][5], vltabla[i][8], "1", vltabla[i][9]);					   
				   } else if(vltabla[i][6].toString().equals("2")){
					   //Tarea dia mes
					   iniciarTareaDiaMes(vltabla[i][0].toUpperCase(), vltabla[i][1].toUpperCase(), vltabla[i][7], vltabla[i][4],vltabla[i][5], vltabla[i][8], "1", vltabla[i][9]);
				   } else if(vltabla[i][6].toString().equals("3")){
					   //Tarea dia hora
					   iniciarTareaIntervaloHora(vltabla[i][0].toUpperCase(), vltabla[i][1].toUpperCase(), vltabla[i][3], vltabla[i][4], vltabla[i][5], vltabla[i][9], vltabla[i][8], "1", vltabla[i][9]);	   
				   } else {
					   //Intervalos mensuales
					   iniciarTareaIntervaloMensual(vltabla[i][0].toUpperCase(), vltabla[i][1].toUpperCase(), vltabla[i][6].toString(), vltabla[i][4],vltabla[i][5], vltabla[i][8], "1", vltabla[i][9]);
				   }
			   }//Fin de chequeo de tarea en ram
		   }//Fin del recorrido
	   }
   }
   
     
    public void limpiar(){
    	reporte = "";
    	tarea = ""; //Nombre de la tarea
        vltrigger = ""; //Nombre del disparador
        diasemana = ""; //Día de la semana
        hora = ""; //Hora de la semana
        minuto = ""; //Minuto de la semana
        frecuencia = "0"; //Fercuencia de repetición
        dias = "2"; // Días de repetición del reporte
        idgrupo = "";
        asunto = "";
        contenido = "";
        diames = "1";
        diainicio = new Date();
        inputs = null;
        ruta_salida = "";
    }
  //Coneccion a base de datos
  	//Pool de conecciones JNDIFARM
  	Connection con;
  	PreparedStatement pstmt = null;
  	ResultSet r;

    
    /**
     * Inserta Configuración.
     * Parametros del Metodo: String codpai, String despai. Pool de conecciones y login
  	 * @throws NamingException 
     **/
    private void delete() throws NamingException {

        try {
        	 Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
     		
     		if(hora.length()==1){
     			hora = "0"+hora;
     		}
     		if(minuto.length()==1){
     			minuto = "0"+minuto;
     		}

     		con = ds.getConnection();
     		
            String query = "DELETE  from T_PROGRAMACION WHERE HORA ='" + hora +":" + minuto + "' and disparador = '" + vltrigger.toUpperCase() + "' and instancia = '" + instancia + "'";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);
          
            try {
                pstmt.executeUpdate();
                limpiar();
            } catch (SQLException e)  {
                 e.printStackTrace();
            }
            
            pstmt.close();
            con.close();           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inserta Configuración.
     * 
  	 * @throws NamingException 
     **/
    private void insert(String pdias, String pdiames, String paramvalues, String intervalo, String paramnames) throws NamingException {
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
     		
     		String[] vecidgrupo = idgrupo.split("\\ - ", -1);
     		
     		if(hora.length()==1){
     			hora = "0"+hora;
     		}
     		if(minuto.length()==1){
     			minuto = "0"+minuto;
     		}
     		
     		String[] vecreporte = reporte.split("\\ - ", -1);
     		
     		String query = "INSERT INTO T_PROGRAMACION VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,'" + sdfecha.format(diainicio) + "',?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, vltrigger.toUpperCase());
            pstmt.setString(2, "unico");
            pstmt.setInt(3, Integer.parseInt(pdias));
            pstmt.setString(4, hora +":" + minuto);            
            pstmt.setString(5, frecuencia);
            pstmt.setString(6, asunto);
            pstmt.setString(7, contenido);
            pstmt.setString(8, vecreporte[0].toUpperCase());
            pstmt.setString(9, extContext.getRealPath(RUTA_REPORTE));
            pstmt.setString(10, extContext.getRealPath(RUTA_LOGS)); 
            pstmt.setInt(11, Integer.parseInt(vecidgrupo[0]));
            pstmt.setString(12, tarea.toUpperCase());
            pstmt.setString(13, pdiames);
            pstmt.setString(14, "0");
            pstmt.setString(15, paramvalues);
            pstmt.setInt(16, Integer.parseInt(intervalo));
            pstmt.setString(17, paramnames);
            pstmt.setString(18, ruta_salida);
            pstmt.setString(19, opctareas);
            pstmt.setString(20, formato);
            pstmt.setInt(21, Integer.parseInt(instancia));

            try {
                pstmt.executeUpdate();
                limpiar();
            } catch (SQLException e)  {
                 e.printStackTrace();
            }
            
            pstmt.close();
            con.close();           
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    
    
    /**
     * Leer Datos de mailconfig
     * @throws NamingException 
     * @throws IOException 
     **/ 	
  	public void select(int first, int pageSize, String sortField, Object filterValue) throws SQLException, NamingException {
     try {	

    	 Context initContext = new InitialContext();     
         DataSource ds = (DataSource) initContext.lookup(JNDI);
    	 con = ds.getConnection();
    		
    	//Reconoce la base de datos de conección para ejecutar el query correspondiente a cada uno
     	 DatabaseMetaData databaseMetaData = con.getMetaData();
     	 String productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección	
   		
     	String query = "";
     	
     	if(opctareas==null){
     		opctareas = "1";
     	}
     	
     	switch ( productName ) {
        case "Oracle":
        	query += "  select * from ";
     	    query += " ( select query.*, rownum as rn from";
        	query += " ( select trim(a.job), trim(a.disparador), trim(to_char(substr(a.hora,1,2),'00')), trim(to_char(substr(a.hora,4,2),'00')), decode(a.frecuencia,'0', '" + getMessage("mailintervalo1") + "','1','" + getMessage("mailintervalo2") + "','2','" + getMessage("mailintervalo3") + "','3','" + getMessage("mailintervalo4") + "','4','" + getMessage("mailintervalo5") + "','" + getMessage("mailintervalo6") + "'), a.diasem";
      		query += " , trim(a.codrep),  trim(a.idgrupo), trim(a.asunto), trim(a.contenido), trim(b.desgrupo) , trim(a.frecuencia), trim(a.diames), trim(to_char(a.diainicio, 'dd/mm/yyyy')), decode(trim(activa),'0','chkActiva','chkInactiva'), trim(activa), decode(trim(activa),'0','"+getMessage("mailtarea0")+"','"+ getMessage("mailtarea1")+"'), trim(a.formato)";
      		query += " from t_programacion a, mailgrupos b";
            query += " where a.idgrupo=b.idgrupo";
            query += " and A.instancia=B.instancia";
            query += " and a.job||a.disparador||a.codrep like '%" + ((String) filterValue).toUpperCase() + "%'";
            query += " and a.opctareas like '" + opctareas + "%'";
            query += " AND   a.instancia = '" + instancia + "'";
            query += " order by " + sortField.replace("v", "") + ") query";
	        query += " ) where rownum <= " + pageSize ;
	        query += " and rn > (" + first + ")";
             break;
        case "PostgreSQL":
        	query = "select trim(a.job), trim(a.disparador), trim(cast(substr(a.hora,1,2) as text)), trim(cast(substr(a.hora,4,2) as text)),  case when a.frecuencia = '0' then '" + getMessage("mailintervalo1") + "' when a.frecuencia = '1' then '" + getMessage("mailintervalo2") + "' when a.frecuencia = '2' then'" + getMessage("mailintervalo3") + "' when a.frecuencia = '3' then '" + getMessage("mailintervalo4") + "' when a.frecuencia = '4' then '" + getMessage("mailintervalo5") + "' else '" + getMessage("mailintervalo6") + "' end, a.diasem";
      		query += " , trim(a.codrep),  trim(cast(a.idgrupo as text)), trim(a.asunto), trim(a.contenido), trim(b.desgrupo) , trim(a.frecuencia), trim(a.diames), trim(to_char(a.diainicio, 'dd/mm/yyyy')),  case when trim(activa) = '0' then 'chkActiva' else 'chkInactiva' end, trim(activa), case when trim(activa) = '0' then '"+ getMessage("mailtarea0")+"' else '"+ getMessage("mailtarea1")+"' end, trim(a.formato)";
      		query += " from t_programacion a, mailgrupos b";
            query += " where a.idgrupo=b.idgrupo";
            query += " and A.instancia=B.instancia";
            query += " and a.job||a.disparador||a.codrep like '%" + ((String) filterValue).toUpperCase() + "%'";
            query += " and a.opctareas like '" + opctareas + "%'";
            query += " AND   a.instancia = '" + instancia + "'";
            query += " order by " + sortField.replace("v", "") ;
	        query += " LIMIT " + pageSize;
	        query += " OFFSET " + first;
             break;
        }


        
        pstmt = con.prepareStatement(query);
        //System.out.println(query);

         r =  pstmt.executeQuery();
        
        
        while (r.next()){
        	Programacion select = new Programacion();
            select.setVjob(r.getString(1));
            select.setVdisparador(r.getString(2));
            select.setVhora(r.getString(3));
            select.setVminuto(r.getString(4));
            select.setVfrecuenciades(r.getString(5));
            select.setVdiasem(r.getString(6));
            select.setVcodrep(r.getString(7));
            select.setVidgrupo(r.getString(8));
            select.setVasunto(r.getString(9));
            select.setVcontenido(r.getString(10));
            select.setViddesidgrupo(r.getString(11));
            select.setVfrecuencia(r.getString(12));
            select.setVdiames(r.getString(13));
            select.setVdiainicio(r.getString(14));
            select.setClase(r.getString(15));
            select.setActiva(r.getString(16));
            select.setVactivadetalletb(r.getString(17));
            select.setVformato(r.getString(18).toUpperCase());
        	//Agrega la lista
        	list.add(select);
        	rows = list.size();
        }
     } catch (SQLException | SchedulerException e){
         e.printStackTrace();    
     }
        //Cierra las conecciones
        pstmt.close();
        con.close();
        r.close();

  	}
  	
  	/**
     * Leer registros en la tabla
     * @throws NamingException 
     * @throws IOException 
     **/ 	
  	public void counter(Object filterValue ) throws SQLException, NamingException {
     try {	
    	Context initContext = new InitialContext();     
   		DataSource ds = (DataSource) initContext.lookup(JNDI);
   		con = ds.getConnection();
   	   //Reconoce la base de datos de conección para ejecutar el query correspondiente a cada uno
 		DatabaseMetaData databaseMetaData = con.getMetaData();
 		productName    = databaseMetaData.getDatabaseProductName();//Identifica la base de datos de conección
 	
   	      		
 		String query = "";
 		
 		
 		switch ( productName ) {
        case "Oracle":
        	 query = "SELECT count_programacion('" + ((String) filterValue).toUpperCase() + "','" + opctareas + "','" + instancia + "') from dual";
             break;
        case "PostgreSQL":
        	 query = "SELECT count_programacion('" + ((String) filterValue).toUpperCase() + "','" + opctareas + "','" + instancia + "')";
             break;
        }

        
        pstmt = con.prepareStatement(query);
        //System.out.println(query);

         r =  pstmt.executeQuery();
        
        
        while (r.next()){
        	rows = r.getInt(1);
        }
     } catch (SQLException e){
         e.printStackTrace();    
     }
        //Cierra las conecciones
        pstmt.close();
        con.close();
        r.close();

  	}
  	
  	
  	  	
  	
  	
  	/**En caso que se requiera envia correos manualmente al grupo
  	 * @throws IOException **/
  	
  	public void enviarMailmanual() throws IOException{

  	    java.sql.Date sqlDate = new java.sql.Date(fechadia.getTime());
  	    int rows;
  		String[][] vltabla;
  		
  		
  	    //Envía reporte
  	    //Selecciona nombre del reporte y id del grupo según hora programada
  	  
  		try {
  			consulta.selectPntGenerica("select trim(codrep), trim(rutarep), trim(rutatemp), trim(job), trim(formato), hora, trim(paramnames), trim(paramvalues) from t_programacion where disparador='" + vltrigger.toUpperCase() + "'", JNDI);
  		
  		////System.out.println("select nombrereporte, idgrupo, trim(rutareporte), trim(rutatemp) from mailtarea where hora='" + formato.format(new Date()) + "'");
  		   
  	    rows = consulta.getRows();
  		vltabla = consulta.getArray();
  		
  		//Imprime reporte
  		if (rows>0){//Si la consulta es mayor a cero devuelve registros envía el correo
  		 new RunReport().outReporteRecibo(vltabla[0][0].toString(), vltabla[0][4].toString(), vltabla[0][1].toString(), vltabla[0][2].toString(), vltabla[0][0].toString(), sqlDate, vltabla[0][3].toString(), vltabla[0][5].toString(), vltabla[0][6].toString(), vltabla[0][7].toString());

  		}
  		//Consulta lista de correos
  		consulta.selectPntGenerica("select trim(a.mail), trim(b.rutatemp), trim(b.codrep), trim(b.asunto), trim(b.contenido), trim(formato)" +
  				" from maillista a, t_programacion b" +
  				" where a.idgrupo=b.idgrupo  " +
  				" and disparador='" + vltrigger.toUpperCase() + "'", JNDI);
  		
  		rows = consulta.getRows();
  		vltabla = consulta.getArray();
  		if (rows>0){//Si la consulta es mayor a cero devuelve registros envía el correo  		
  			for(int i=0;i<rows;i++){	

  			 new Sendmail().mailthread(vltrigger.toUpperCase(), vltabla[i][1], vltabla[i][2], vltabla[i][3], vltabla[i][4], vltabla[i][5]);
  				
  			}
  			msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "FIN DEL BLOQUE", "");
			FacesContext.getCurrentInstance().addMessage(null, msj);
  		}
  		
  		
  		} catch (NamingException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  	
  


  	
  	/**Cambia estatus en la base de datos a la tarea como inactiva
     * @throws NamingException */
  	private void updateJobStatusBd(String job, String estatus){
  		 try {
        	Context initContext = new InitialContext();     
      		DataSource ds = (DataSource) initContext.lookup(JNDI);

      		con = ds.getConnection();		
      		
             String query = "UPDATE t_programacion";
             query += " SET activa = '" + estatus + "'";
             query += " WHERE disparador = '" + job.toUpperCase() + "'";
           // //System.out.println(query);
            pstmt = con.prepareStatement(query);
            // Antes de ejecutar valida si existe el registro en la base de Datos.
                pstmt.executeUpdate();
                //Cierra conecciones
                pstmt.close();
                con.close(); 	
            } catch (SQLException | NamingException e) {
            	e.printStackTrace();
            } 			 
  	}
  	
  	
  	
  	/**Activa o desactiva temporalmente las tareas seleccionadas
     * @throws NamingException 
  	 * @throws SchedulerException */
	public void activaDesactiva() throws NamingException, SchedulerException{
		//Tomando los valores de checkbox
  		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	String[] chkbox = request.getParameterValues("activa");
    	//
    	//Convierte para el query con string utils
    	String param = "'" + StringUtils.join(chkbox, "','") + "'";
    	String query = "select trim(disparador) from t_programacion where disparador not in (" + param + ") and activa = '0'";
    	//System.out.println(query);
    	
    	//Consultando las tareas
    	consulta.selectPntGenerica(query, JNDI);//Consultando en la base de datos
    	int vlrows = consulta.getRows();//Toma registros de la consulta
    	String[][] vltabla = consulta.getArray();
    	//System.out.println("Registros: " + vlrows);
    	
    	if(vlrows>0){//Valida si la consulta devuelve registros
    	for(int i = 0; i < vlrows; i++){  		
  
    			//Si está inactiva no hace nada y dispara un mensaje
                if(schd.interrupt(vltabla[i][0])){
                	//System.out.println("La tarea está inactiva, no hay programación que detener");
                	msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("mailtareNoActivas"), "");
                	FacesContext.getCurrentInstance().addMessage(null, msj);
                } else {
                	//System.out.println("Desactivando al disparador: " + vltabla[i][0]);
                	
                	schd.unscheduleJob(triggerKey(vltabla[i][0], "unico"));//Unschedule
                	updateJobStatusBd(vltabla[i][0],"1");//Actualiza estatus en la BD
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("mailtareadetenida") + " : " + vltabla[i][0], "");
                	FacesContext.getCurrentInstance().addMessage(null, msj);
                }
    			//Desactiva las tareas seleccionadas
    		}
    	
    	} else {
    		//Los activa a todos
    		//System.out.println("Todos activos");
    		query = "select trim(disparador) from t_programacion where disparador in (" + param + ")";
    		consulta.selectPntGenerica(query, JNDI);//Consultando en la base de datos
    		vlrows = consulta.getRows();
    		vltabla = consulta.getArray();
    		if(vlrows>0){//Valida si la consulta devuelve registros
    			for(int i = 0; i < vlrows; i++){  
    				updateJobStatusBd(vltabla[i][0],"0");//Actualiza estatus en la BD
    				recuperarTriggers("0");
    			}  			
    		}
    		//FacesContext.getCurrentInstance().addMessage(null, msj);
    	}
      }
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////PARAMETROS DEL REPORTE///////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private List<String> listR = new ArrayList<String>();//Guarda el nombre que se va a mostrar en la pantalla
	private List<String> listPN = new ArrayList<String>();//Guarda el nombre del parámetro
	private List<Integer> listPT = new ArrayList<Integer>();//Guarda el tipo del parámetro
	private String[] inputs;
	private int rowsParam;
	

	/**
	 * @return the inputs
	 */
	public String[] getInputs() {
		return inputs;
	}

	/**
	 * @param inputs the inputs to set
	 */
	public void setInputs(String[] inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the listR
	 */
	public List<String> getListR() {
		return listR;
	}

	/**
	 * @param listR the listR to set
	 */
	public void setListR(List<String> listR) {
		this.listR = listR;
	}

	/**
	 * Retorna la ruta real del reporte 
	 * y se lo asigna al método ReadParamsBirtReport
	 * de la clase RunReport, el mismo leerá los parámetros
	 * del reporte parra programar la tarea
	 */
	 public String getRutaRepReal(){
		 String ruta = extContext.getRealPath(RUTA_REPORTE) + File.separator + reporte.split(" - ")[0].toUpperCase() + ".rptdesign";
		 return ruta;
	 }
  		
	 
	 /**
		 * Lee parámetros del los reportes
		 * Usa el API del birt report, Iterando los parámetros
		 * y guardando los valores en un ArrayList
		 * @param: Ruta, la ruta física de ubicación del reporte
		 */
		public void ReadParamsBirtReport(String preporte) {
			//Borra todo lo que contenga la session
			try {
				deleteTemps(session, "bvtparams_temp");
				deleteTemps(session, "bvtparams_number_temp");
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Lectura de parámetros de reportes, BIRT ENGINE
			//
			EngineConfig config = new EngineConfig( );
			ReportEngine engine = new ReportEngine( config );
			//Open a report design 
			IReportRunnable design = null;
			try {
				design = engine.openReportDesign(preporte);
			} catch (EngineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			IGetParameterDefinitionTask task = engine.createGetParameterDefinitionTask( design );
			@SuppressWarnings("rawtypes")
			Collection params = task.getParameterDefns( true );

			@SuppressWarnings("rawtypes")
			Iterator iter = params.iterator( ); 
			//Iterate over all parameters
			while ( iter.hasNext( ) )
			{			
			IParameterDefn param = (IParameterDefn) iter.next( );		
	          if (!param.isHidden() && param.isRequired()){
	        	  listPN.add(param.getName());//Guarda el nombre del parámetro
	        	  listPT.add(param.getDataType());//Guarda el tipo de parámetro
				{
					if(param.getPromptText()==null){
						listR.add(param.getName());
					} else {
						listR.add(param.getPromptText());
					}
					rowsParam = params.size();
					inputs = new String[rowsParam];//Asigna el tamaño a los inputs
				}
	          }
			}
			//Una vez obtenido los parámetros los guardamos en una tabla temporal
			//Esto permite hace un mejor manejo en el for each loop al momento de insertar los valores
			//Recorre la lista y va insertando, blanquea la tabla antes de cargarla
			//
			for(int i = 0; i < rowsParam; i++){				
		         try {
		        	//Inserta parámetros 
					deleteParam(reporte.split(" - ")[0].toUpperCase(), listPN.get(i), listPT.get(i), session);//Borra temporal
					insertParam(reporte.split(" - ")[0].toUpperCase(), listPN.get(i), listPT.get(i), session, listR.get(i));//CArga temporal
					//Inserta el número de parámetros
					deleteParamNumber(reporte.split(" - ")[0].toUpperCase(), session);
					insertParamNumber(reporte.split(" - ")[0].toUpperCase(), session, rowsParam);
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }			
		   task.close();
		   
		   //Lectura de datos en la tabla posteriormente recorre el arreglo para impimir en pantalla
		   //
		   String[] vecreporte = reporte.split("\\ - ", -1);
		   String vlquery = "select promptext from bvtparams_temp where sessionid = '" + session + "' and codrep = '" + vecreporte[0].toUpperCase() + "'";
		   try {
			consulta.selectPntGenerica(vlquery, JNDI);
			rowsParam = consulta.getRows();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		   //Si hay registros en la temporal recorre la tabla 
		   if(rowsParam>0){//Si hay registros muestra los parámetros				     
			     vlTabla = consulta.getArray();
				 RequestContext.getCurrentInstance().execute("PF('dlg2').show()");
	        }
		}
		
		
		/**
	     * Inserta Temporal de parametros.
	     * <p>
	     **/
	    private void insertParam(String codrep, String paramName, Integer paramType, String sessionId, String promptext) throws  NamingException {
	        try {
	        	Context initContext = new InitialContext();     
	     		DataSource ds = (DataSource) initContext.lookup(JNDI);
	            con = ds.getConnection();
	            
	            String query = "INSERT INTO bvtparams_temp VALUES (?,?,?,?,?)";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, codrep.toUpperCase());
	            pstmt.setString(2, paramName);
	            pstmt.setInt(3, paramType);
	            pstmt.setString(4, sessionId);
	            pstmt.setString(5, promptext.toUpperCase());

	            ////System.out.println(query);
	            try {
	                //Avisando
	            	pstmt.executeUpdate();
	             } catch (SQLException e)  {
	            	e.printStackTrace();
	            }
	            
	            pstmt.close();
	            con.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }	
	    }
	    
	    /**
	     * Borra Temporal de parametros.
	     * <p>
	     **/
	    private void deleteParam(String codrep, String paramName, Integer paramType, String sessionId) throws  NamingException {
	        try {
	        	Context initContext = new InitialContext();     
	     		DataSource ds = (DataSource) initContext.lookup(JNDI);
	            con = ds.getConnection();
	            
	            String query = "delete from bvtparams_temp where codrep = ? and paramname = ? and paramtype = ? and sessionid = ?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, codrep.toUpperCase());
	            pstmt.setString(2, paramName.toUpperCase());
	            pstmt.setInt(3, paramType);
	            pstmt.setString(4, sessionId);

	            ////System.out.println(query);
	            try {
	                //Avisando
	            	pstmt.executeUpdate();
	             } catch (SQLException e)  {
	            	e.printStackTrace();
	            }	            
	            pstmt.close();
	            con.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }	
	    }
	    
	    /**
	     * Inserta Temporal de parametros.
	     * <p>
	     **/
	    private void insertParamNumber(String codrep, String sessionId, Integer paramnumber) throws  NamingException {
	        try {
	        	Context initContext = new InitialContext();     
	     		DataSource ds = (DataSource) initContext.lookup(JNDI);
	            con = ds.getConnection();
	            
	            String query = "INSERT INTO bvtparams_number_temp VALUES (?,?,?)";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, codrep.toUpperCase());
	            pstmt.setString(2, sessionId);
	            pstmt.setInt(3, paramnumber);

	            ////System.out.println(query);
	            try {
	                //Avisando
	            	pstmt.executeUpdate();
	             } catch (SQLException e)  {
	            	e.printStackTrace();
	            }
	            
	            pstmt.close();
	            con.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }	
	    }
	    
	    /**
	     * Borra Temporal de parametros.
	     * <p>
	     **/
	    private void deleteParamNumber(String codrep,  String sessionId) throws  NamingException {
	        try {
	        	Context initContext = new InitialContext();     
	     		DataSource ds = (DataSource) initContext.lookup(JNDI);
	            con = ds.getConnection();
	            
	            String query = "delete from bvtparams_number_temp where codrep = ? and sessionid = ?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, codrep.toUpperCase());
	            pstmt.setString(2, sessionId);

	            ////System.out.println(query);
	            try {
	                //Avisando
	            	pstmt.executeUpdate();
	             } catch (SQLException e)  {
	            	e.printStackTrace();
	            }	            
	            pstmt.close();
	            con.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }	
	    }
	    
	    
	    /**
	     * Borra Temporal de parametros.
	     * <p>
	     **/
	    private void deleteTemps(String sessionId, String tabla) throws  NamingException {
	        try {
	        	Context initContext = new InitialContext();     
	     		DataSource ds = (DataSource) initContext.lookup(JNDI);
	            con = ds.getConnection();
	            
	            String query = "delete from " + tabla + " where sessionid = '" + sessionId + "'";
	            pstmt = con.prepareStatement(query);
	            //System.out.println(sessionId);
	            try {
	                //Avisando
	            	pstmt.executeUpdate();
	             } catch (SQLException e)  {
	            	e.printStackTrace();
	            }	            
	            pstmt.close();
	            con.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }	
	    }
	    
	    
	    /**
	     * Retorna el número de parámetros
	     * <p>
	     **/
	    public Integer paramsNumber(String reporte){
	    	Integer param = 0;
	    	int vlrows = 0; 
	    	String vlquery = "select paramnumber from bvtparams_number_temp where sessionid = '" + session + "'";
	    	try {
				consulta.selectPntGenerica(vlquery, JNDI);
				vlrows = consulta.getRows();
				if(vlrows>0){
					String [][] vltabla = consulta.getArray();
					param = Integer.parseInt(vltabla[0][0]);
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return param;
	    }
	    
	    
	    /**
	     * Retorna el nombre de parámetros
	     * de la tabla temporal que almacena los parámetros
	     * Convierte las columnas en filas separadas por (|)
	     * <p>
	     **/
	    public String  arregloParamNames(String reporte){
	    	String paramNames = "";
	    	int vlrows = 0; 
	    	String vlqueryOra = "select codrep, listagg (paramname, '|') WITHIN GROUP (ORDER BY paramname) arr FROM bvtparams_temp  where SESSIONID = '" + session + "' and codrep = '" + reporte + "' GROUP BY codrep";
	    	String vlqueryPg = "select codrep, string_agg(paramname,'|' order by paramname)  arr from bvtparams_temp where SESSIONID = '" + session + "' and codrep = '" + reporte + "' GROUP BY codrep";
	    	String vlquerySqlSrv = "";
	    	try {
				consulta.selectPntGenericaMDB(vlqueryOra, vlqueryPg, vlquerySqlSrv, JNDI);
				vlrows = consulta.getRows();
				if(vlrows>0){
					String [][] vltabla = consulta.getArray();
					paramNames = vltabla[0][1];
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return paramNames;
	    }
	    
}

