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


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.naming.NamingException;

import org.eclipse.birt.report.engine.api.*;



public class RunReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Constructor
	public RunReport(){

	}
	
	private int rows;
	private String[][] vltabla;
	private String vlquery;
	PntGenerica consulta = new PntGenerica();


	/**
     * Este método utiliza el BIRT engine para generar reportes
	 * desde el API utilizando POJO.
     * @param reporte: Nombre del reporte que esá leyendo, debe ser .rptdesing
	 * @parama format: Formato de salida: HTML, PDF, DOC
	 * @param ubicacionrep: Ubicación del reporte
	 * @param rutasalida: Salida del reporte ubicación en disco
	 * @param nbrreporte: nombre del reporte al momento de la salida
	 * @param nbrreporte: nombre del reporte al momento de la salida
	 * @param feccon
     **/ 
	@SuppressWarnings("deprecation")
	public void outReporteRecibo(String reporte, String format, String ubicacionrep
			, String rutasalida, String nbrreporte, Date feccon){
		
	  //Variables used to control BIRT Engine instance
  	  //Now, setup the BIRT engine configuration. The Engine Home is hardcoded
  	  EngineConfig config = new EngineConfig( );
  	  //Create new Report engine based off of the configuration
      ReportEngine engine = new ReportEngine( config );
      IReportRunnable report = null;
      IRunAndRenderTask task;
      IRenderOption options = new RenderOption();     
      PDFRenderOption pdfOptions = new PDFRenderOption();
      final HashMap<String, Date> PARAMAMFECCON = new HashMap<String, Date>();
      
      PARAMAMFECCON.put("FECCON", feccon);
      
      
      //Lectura de parámeros dinámicos desde la tabla t_programación
      //Función agregada para versión 5 de bizview 11/10/2014
      //Por los momentos solo se trabajará con Strings
      final HashMap<String, String> PARAMAMARRAY = new HashMap<String, String>();
      vlquery = "select trim(paramnames), trim(paramvalues), case when  LENGTH(trim(paramvalues)) is null then 0 else LENGTH(trim(paramvalues)) end from t_programacion where codrep = '" + reporte + "'";
      try {
		consulta.selectPntGenerica(vlquery, Bd.JNDI);
	  } catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	  }
      rows = consulta.getRows();
      if(rows>0){
    	  vltabla = consulta.getArray();
      
      //Si la longitud de los valores de parámetros es diferente de cero
      //Procede a leer y pasar parámetros
      if(Integer.parseInt(vltabla[0][2])>0){
    	  String[] arr1 = vltabla[0][0].split("\\|", -1);//Toma la lista de parametros de la tabla y hace un split, trabaja el arreglo y lo recorre
    	  String[] arr2 = vltabla[0][1].split("\\|", -1);//Toma la lista de parametros de la tabla y hace un split, trabaja el arreglo y lo recorre
    	  for(int i = 0; i < vltabla[0][0].split("\\|", -1).length; i++){
    		  PARAMAMARRAY.put(arr1[i], arr2[i]);
    	  }
    	  
       }//Valida longitud 
      }//Valida registros
      //
 
        
        //With our new engine, lets try to open the report design
        try
        {
      	  report = engine.openReportDesign( ubicacionrep + "/" + reporte + ".rptdesign"); 

        }
        catch (Exception e)
        {
        	 //msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
//			 FacesContext.getCurrentInstance().addMessage(null, msj);
        	 e.printStackTrace();
            // System.exit(-1);
        }
        
        //With the file open, create the Run and Render task to run the report
        task = engine.createRunAndRenderTask(report);
          

        //This will set the output file location, the format to render to, and
        //apply to the task
        //System.out.println(format.toUpperCase());
        if( format.toUpperCase().equalsIgnoreCase("PDF") ){//TODO: modificación para que el reporte quepa en toda la página 15/01/2015
			pdfOptions.setOption( IPDFRenderOption.FIT_TO_PAGE, true );
			pdfOptions.setOutputFormat(format);
			pdfOptions.setOutputFileName( rutasalida + "/" + nbrreporte + "." + format);
			task.setRenderOption(pdfOptions);
		} else {
            options.setOutputFormat(format);
            options.setOutputFileName( rutasalida + "/" + nbrreporte + "." + format);
            task.setRenderOption(options);
		}
        
       
        
        
        try
        {
        	 
        	 task.setParameterValues(PARAMAMFECCON);
        	 task.setParameterValues(PARAMAMARRAY);
        	 task.validateParameters();
             task.run();
        }
        catch (Exception e)
        {
        	 //msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			 //FacesContext.getCurrentInstance().addMessage(null, msj);
             e.printStackTrace();
             //System.exit(-1);
        }
        
        //Yeah, we finished. Now destroy the engine and let the garbage collector
        //do its thing
        //System.out.println("Reporte ejecutado : " + nbrreporte);
        engine.destroy();
        task.close();
   }


}
