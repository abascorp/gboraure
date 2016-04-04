/*
 *  Copyright (C) 2011  DVCONSULTORES

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


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;



//La clase que tiene la tarea debe de implementar de la clase Job de Quartz
//ya que el programador de la tarea va a buscar una clase que implemente de ella
//y buscara el metodo execute para ejecutar la tarea
@ManagedBean
@ViewScoped
public class TareaInvocar extends Bd implements Job {

	PntGenerica consulta = new PntGenerica();
	
	//Coneccion a base de datos
	//Pool de conecciones JNDIFARM
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;



	java.util.Date fechadia = new java.util.Date();

 /**
  * Método que se ejecuta según la tarea programada, según tres opciones:
  * 1- Envío de reporte y corre
  * 2- Envío de reporte a una ruta específica sin envío de corre
  * 3- Envío de reporte a una lista de personas en partícular (Ejemplo: recibos de pago o notificaciones particulares)	
  * Nueva opción creada para versión 5 26/10/2014
  */
  //Metodo que se ejecutara cada cierto tiempo que lo programemos despues
  public void execute(JobExecutionContext jec) throws JobExecutionException {
    //Aca pueden poner la tarea o el job que desean automatizar
    //Por ejemplo enviar correo, revisar ciertos datos, etc
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
   
    //System.out.println( "Tarea invocada a la hora: " + formato.format(new Date()));
    JobKey key = jec.getJobDetail().getKey();
    String job = key.toString().replace("unico.", "");
    //System.out.println("Trigger orden: " + job);
    
    java.sql.Date sqlDate = new java.sql.Date(fechadia.getTime());
    //Para reportes
    int rowsrep;
   	String[][] vltablarep;
   	
   	//Para mail
   	int rowsmail;
   	String[][] vltablamail;
   	
   	int counter = 0;
   	
   	
   	
   	try {
   	//Consulta registros por jobs	
   	consulta.selectPntGenerica("select count(*) from t_programacion where hora=trim('" + formato.format(new Date()) + "')", JNDI);
  //Para saber si se repite tarea
   	String[][] vltablajobs = consulta.getArray();
   	
	int numberOfjobs = Integer.parseInt(vltablajobs[0][0]);
	String vlqueryORA;
   	String vlqueryPG ;
   	String vlquerySQL;
	
	//Envía reporte
    //Selecciona nombre del reporte y id del grupo según hora programada
   	
	  if(numberOfjobs > 1){//Si el número de tarea el mayor a uno entonces las recorre
		                   //Esto se debe a que se puede generar el mismo reporte con diferentes parámetros a la misma hora
		                   //También el reporte debe tener un contador para que se llame distinto y se genere sin borrar el anterior
		                   //Modificaciones para que funcionen los reportes con parámetros
		                   //17/08/2015 DvConsultores/Andrés Dominguez
		//Select solo por hora 
		vlqueryORA = "select trim(codrep), trim(rutarep), trim(rutatemp), trim(opctareas), trim(ruta_salida), trim(formato), trim(disparador), trim(paramnames), trim(paramvalues) from t_programacion where hora=trim('" + formato.format(new Date()) + "') order by job";
	   	vlqueryPG = "select trim(codrep), trim(rutarep), trim(rutatemp), trim(opctareas), trim(ruta_salida), trim(formato), trim(disparador),  trim(paramnames), trim(paramvalues) from t_programacion where hora=trim('" + formato.format(new Date()) + "') order by job";
	   	vlquerySQL = "select codrep, rutarep, rutatemp, opctareas, ruta_salida, formato from t_programacion where hora=trim('" + formato.format(new Date()) + "') and job = trim('" + job + "') order by job";
	
		consulta.selectPntGenericaMDB(vlqueryORA, vlqueryPG, vlquerySQL, JNDI);
	   	rowsrep = consulta.getRows();
		vltablarep = consulta.getArray();
	   	
	  //Imprime reporte
		if (rowsrep>0){//Si la consulta es mayor a cero devuelve registros envía el correo
		 for (int i = 0; i < numberOfjobs; i++){//Número de tareas a generar,pase de parámetros en el reporte
			 counter = 1+i;
		 if(!vltablarep[i][3].equals("2")){//Si no es igual a 2, es para envío de correo
		 new RunReport().outReporteRecibo(vltablarep[i][0].toString(), vltablarep[i][5].toString(), vltablarep[i][1].toString(), vltablarep[i][2].toString(), vltablarep[i][0].toString()+"_"+counter, sqlDate, vltablarep[i][6].toString(), formato.format(new Date()), vltablarep[i][7], vltablarep[i][8]);
		 } else { //Se envia a URL particular, no se evía por correo
		 new RunReport().outReporteRecibo(vltablarep[i][0].toString(), vltablarep[i][5].toString(), vltablarep[i][1].toString(), vltablarep[i][4].toString(), vltablarep[i][0].toString()+"_"+counter, sqlDate, vltablarep[i][6].toString(), formato.format(new Date()), vltablarep[i][7], vltablarep[i][8]);	 
		 }
		 }
		}
		
	} else { //Si es una tarea por cada hora hace su recorrido normal como en un principio se desarrolló
   	    vlqueryORA = "select trim(codrep), trim(rutarep), trim(rutatemp), trim(opctareas), trim(ruta_salida), trim(formato), trim(disparador), trim(paramnames), trim(paramvalues), case when  LENGTH(trim(paramvalues)) is null then 0 else LENGTH(trim(paramvalues)) end from t_programacion where hora=trim('" + formato.format(new Date()) + "') and job = trim('" + job + "') order by job";
   	    vlqueryPG = "select trim(codrep), trim(rutarep), trim(rutatemp), trim(opctareas), trim(ruta_salida), trim(formato), trim(disparador),  trim(paramnames), trim(paramvalues), case when  LENGTH(trim(paramvalues)) is null then 0 else LENGTH(trim(paramvalues)) end from t_programacion where hora=trim('" + formato.format(new Date()) + "') and job = trim('" + job + "') order by job";
   	    vlquerySQL = "select codrep, rutarep, rutatemp, opctareas, ruta_salida, formato from t_programacion where hora=trim('" + formato.format(new Date()) + "') and job = trim('" + job + "') order by job";
	
   	    consulta.selectPntGenericaMDB(vlqueryORA, vlqueryPG, vlquerySQL, JNDI);
   	    rowsrep = consulta.getRows();
		vltablarep = consulta.getArray();
	   	
	  //Imprime reporte
		if (rowsrep>0){//Si la consulta es mayor a cero devuelve registros envía el correo
		 if(!vltablarep[0][3].equals("2")){//Si no es igual a 2, es para envío de correo
		 new RunReport().outReporteRecibo(vltablarep[0][0].toString(), vltablarep[0][5].toString(), vltablarep[0][1].toString(), vltablarep[0][2].toString(), vltablarep[0][0].toString(), sqlDate, vltablarep[0][6].toString(), formato.format(new Date()), vltablarep[0][7], vltablarep[0][8]);
		 } else { //Se envia a URL particular, no se evía por correo
		 new RunReport().outReporteRecibo(vltablarep[0][0].toString(), vltablarep[0][5].toString(), vltablarep[0][1].toString(), vltablarep[0][4].toString(), vltablarep[0][0].toString(), sqlDate, vltablarep[0][6].toString(), formato.format(new Date()), vltablarep[0][7], vltablarep[0][8]);	 

		 }
		}
		
	
	}
  
	
	   
	//System.out.println(vlqueryORA);
	
	//Consulta tareas
	String vlquerymailORA = "select distinct trim(disparador), trim(rutatemp), trim(codrep), trim(asunto), trim(contenido), trim(formato)" +
			" from t_programacion" +
			//" and hora=trim('" + formato.format(new Date()) + "') and job =trim('" + job + "') order by a.mail", JNDI);
			//Modificación del 24/08/2014, si la conección es muy lenta ó el reporte es lago la tarea se ejecuta minutos después
	        " where disparador =trim('" + job + "')";
	String vlquerymailPG = "select distinct trim(disparador), trim(rutatemp), trim(codrep), trim(asunto), trim(contenido), trim(formato)" +
			" from t_programacion" +
			//" and hora=trim('" + formato.format(new Date()) + "') and job =trim('" + job + "') order by a.mail", JNDI);
			//Modificación del 24/08/2014, si la conección es muy lenta ó el reporte es lago la tarea se ejecuta minutos después
	        " where disparador =trim('" + job + "')";
	String vlquerymailSQL = "select distinct trim(disparador), trim(rutatemp), trim(codrep), trim(asunto), trim(contenido), trim(formato)" +
			" from t_programacion" +
			//" and hora=trim('" + formato.format(new Date()) + "') and job =trim('" + job + "') order by a.mail", JNDI);
			//Modificación del 24/08/2014, si la conección es muy lenta ó el reporte es lago la tarea se ejecuta minutos después
	        " where disparador =trim('" + job + "')";
	
	consulta.selectPntGenericaMDB(vlquerymailORA, vlquerymailPG, vlquerymailSQL, JNDI);	
	
	
	//System.out.println(vlquerymailPG);
			
	
	rowsmail = consulta.getRows();
	vltablamail = consulta.getArray();
	for (int x = 0; x < numberOfjobs; x++){//Busca nombre de reporte por jobs, recorre la cantidad de jobs
		counter = 1+x; //Contador para renombrar reportes
	if (rowsmail>0 && !vltablarep[x][3].equals("2")){//Si la consulta es mayor a cero devuelve registros envía el correo
		                                             //Envía correo siempre y cuando la opción no sea 2 (Envíar a URL)
		for(int i=0;i<rowsmail;i++){			
		 new Sendmail().mailthread(vltablamail[i][0], vltablamail[i][1], vltablamail[i][2], vltablamail[i][3], vltablamail[i][4], vltablamail[i][5]);
			
		}
	}
	}

	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
  }
  
  

}
