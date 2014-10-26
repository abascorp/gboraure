/*
 *  Copyright (C) 2011  ANDRES DOMINGUEZ, CHRISTIAN DiAZ

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

/*
 *  Esta clase controla todos los archivos de subida y procesamiento de txt al servidos
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ManagedBean
@ViewScoped
public class FileUploadController extends Bd {
	// Constructor
	public FileUploadController() {

	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Variables seran utilizadas para capturar mensajes de errores de Oracle y
	// parametros de metodos
	FacesMessage msj = null;
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////UPLOAD DE ARCHIVOS PARA AUTOSERVICIO/////////////////////////////////////////////////  

	    
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////UPLOAD DE ARCHIVOS PARA AUTOSERVICIO//////////////////////////////////////////////////
	    
	public void handleReportUpload(FileUploadEvent event) throws InterruptedException {
		try {
		ExecutorService ex = null;
		
			ex = Executors.newFixedThreadPool(Integer.parseInt(THREADNUMBER));
			
			FileUploadControllerThread th = new FileUploadControllerThread(event);		
			
	        ex.execute(th);
	        //
	        Thread.sleep(100);
	        th.setStop(true);

	        ex.shutdown();
	        //Toma el mensaje que devuelva el thread
	        if(th.getExito()){
	        msj = new FacesMessage(FacesMessage.SEVERITY_FATAL,  th.getRetornoThread(), "");
	     	FacesContext.getCurrentInstance().addMessage(null, msj);
	        } else {
	        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("autos02Uploaded"), "");
			FacesContext.getCurrentInstance().addMessage(null, msj);	
	        }
		} catch (NumberFormatException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msj = new FacesMessage(FacesMessage.SEVERITY_FATAL,  e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage(null, msj);
			
		}   //Número de hilos a usar para el insert
		
	}


}
