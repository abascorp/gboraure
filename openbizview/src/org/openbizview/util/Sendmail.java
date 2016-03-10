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


import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


@ManagedBean
@ViewScoped
public class Sendmail extends Bd {


	public Sendmail() {

	}

	PntGenerica consulta = new PntGenerica();

	/**
	 * Método que envía por correo reporte generado por Birt Report en PDF
	 * @throws NamingException 
	 * **/
	public void mail(String trigger, String ruta, String file, String asunto, String contenido, String formato) throws NamingException{
		////System.out.println("Registros :" + rows);
				Context initContext = new InitialContext();     
		    	Session session = (Session) initContext.lookup(JNDIMAIL);
		        
		        MimeMessage mm = new MimeMessage(session);      
		        
		    try {
        	
        	// Crear el mensaje a enviar


			// Establecer las direcciones a las que será enviado
			// el mensaje (test2@gmail.com y test3@gmail.com en copia
			// oculta)
			// mm.setFrom(new
			// InternetAddress("opennomina@dvconsultores.com"));
		    //Consulta lista de correos
		  	consulta.selectPntGenerica("select trim(a.mail)" +
		  	" from maillista a, t_programacion b" +
		  	" where a.idgrupo=b.idgrupo  " +
		  	" and disparador='" + trigger.toUpperCase() + "'", JNDI);
		  		
		  	int rows = consulta.getRows();
		  	String[][] vltabla = consulta.getArray();
		  	if (rows>0){//Si la consulta es mayor a cero devuelve registros envía el correo  
		  	//Recorre la lista de correos	
		  	for(int i=0;i<rows;i++){	
  				mm.addRecipient(Message.RecipientType.TO,
					new InternetAddress(vltabla[i][0]));		  				
  			}
		  	}

			// Establecer el contenido del mensaje
			mm.setSubject(asunto);
			//mm.setText(getMessage("mailContent"));
			
			// Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	      // Fill the message
	         messageBodyPart.setContent(contenido, "text/html; charset=utf-8");
	         
			// Create a multipar message
	         Multipart multipart = new MimeMultipart();
	         
	        // Set text message part
	         multipart.addBodyPart(messageBodyPart);
	         
	        // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = ruta + File.separator + file + "." + formato;
	         javax.activation.DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(file + "." + formato);
	         multipart.addBodyPart(messageBodyPart);
	         
	         // Send the complete message parts
	         mm.setContent(multipart );


			// Enviar el correo electrónico
			Transport.send(mm);
			//System.out.println("Correo enviado exitosamente a :" + to + ". Reporte:" + file);

        } catch (MessagingException e) {
            throw new RuntimeException(e);      
      }	    
	}
	
	public void mailthread(String trigger, String ruta, String file, String asunto, String contenido, String formato) {
		ExecutorService ex = Executors.newFixedThreadPool(50);   //Número de hilos a usar para el insert
		MailThread th = new MailThread(trigger, ruta, file, asunto, contenido, formato); //Insert Generic
        ex.execute(th);
        ex.shutdown();
	}

}
