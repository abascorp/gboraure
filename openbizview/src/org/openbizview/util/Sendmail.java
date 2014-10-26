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


	/**
	 * Método que envía por correo reporte generado por Birt Report en PDF
	 * @throws NamingException 
	 * **/
	public void mail(String to, String ruta, String file, String asunto, String contenido) throws NamingException{
		////System.out.println("Registros :" + rows);
				Context initContext = new InitialContext();     
		    	Session session = (Session) initContext.lookup("mail/recibos");
		        
		        MimeMessage mm = new MimeMessage(session);      
		        
		    try {
        	
        	// Crear el mensaje a enviar


			// Establecer las direcciones a las que será enviado
			// el mensaje (test2@gmail.com y test3@gmail.com en copia
			// oculta)
			// mm.setFrom(new
			// InternetAddress("opennomina@dvconsultores.com"));
			mm.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

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
	         String filename = ruta + File.separator + file + ".pdf";
	         javax.activation.DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(file + ".pdf");
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
	
	public void mailthread(String to, String ruta, String file, String asunto, String contenido){
		ExecutorService ex = Executors.newFixedThreadPool(100);   //Número de hilos a usar para el insert
		MailThread th = new MailThread(to, ruta, file, asunto, contenido); //Insert Generic
        ex.execute(th);
	}

}
