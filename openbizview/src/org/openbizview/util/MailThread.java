package org.openbizview.util;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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


public class MailThread extends Thread {

	String trigger;
	String ruta;
	String file;
	String asunto;
	String contenido;
	String formato;
	PntGenerica consulta = new PntGenerica();


	public MailThread(String trigger, String ruta, String file, String asunto,
			String contenido, String formato) {
		this.trigger = trigger;
		this.ruta = ruta;
		this.file = file;
		this.asunto = asunto;
		this.contenido = contenido;
		this.formato = formato;
	}

	public void run() {
		try {
			// //System.out.println("Registros :" + rows);
			Context initContext = new InitialContext();
			Session session = (Session) initContext.lookup(Bd.JNDIMAIL);
			
			MimeMessage mm = new MimeMessage(session);

			// Crear el mensaje a enviar

			// Establecer las direcciones a las que será enviado
			// el mensaje (test2@gmail.com y test3@gmail.com en copia
			// oculta)
			// mm.setFrom(new
			// InternetAddress("opennomina@dvconsultores.com"));
            String vlquery = "select trim(a.mail)";
            	   vlquery += " from maillista a, t_programacion b";
            	   vlquery += " where a.idgrupo=b.idgrupo  ";
            	   vlquery += " and disparador='" + trigger.toUpperCase() + "'";
			//Consulta lista de correos
		  	consulta.selectPntGenerica(vlquery,  Bd.JNDI);
		  	System.out.println(vlquery);	
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
			// mm.setText(getMessage("mailContent"));

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
			mm.setContent(multipart);

			// Enviar el correo electrónico
			Transport.send(mm);
			//System.out.println("Correo enviado exitosamente a :" + to + ". Reporte:" + file);

		} catch (MessagingException|NamingException e) {
            e.printStackTrace();
		}
	}

}
