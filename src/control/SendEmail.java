package control;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SendEmail implements Cloneable {
	private String from;
	private SendEmail(String from){
		this.from=from;
	}

	private static SendEmail sendEmail;

	public static SendEmail getSendEmail(String from) {
		if(sendEmail==null) {
			sendEmail=new SendEmail(from);
		}
		return sendEmail.getClone();
	}

	public SendEmail getClone() {
		try {
			return (SendEmail)super.clone();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void sendMyMail(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession websession = request.getSession();
		String to = "2903abi@gmail.com";
		  final String username = "javaemailpack@gmail.com";//change accordingly
	      final String password = "javaemailpack@123";
		  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	      // Get a Properties object
	         Properties props = System.getProperties();
	         props.setProperty("mail.smtp.host", "smtp.gmail.com");
	         props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	         props.setProperty("mail.smtp.socketFactory.fallback", "false");
	         props.setProperty("mail.smtp.port", "465");
	         props.setProperty("mail.smtp.socketFactory.port", "465");
	         props.put("mail.smtp.auth", "true");
	         props.put("mail.debug", "true");
	         props.put("mail.store.protocol", "pop3");
	         props.put("mail.transport.protocol", "smtp");

	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
		   }
	         });
	        

	      try {
		   Message message = new MimeMessage(session);
		
		   message.setFrom(new InternetAddress(from));
		
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		
		   message.setSubject("Invoice for your orders");
		
		   BodyPart messageBodyPart = new MimeBodyPart();

	         messageBodyPart.setText("The Inovice for your orders have been attached with this mail");

	         Multipart multipart = new MimeMultipart();

	         multipart.addBodyPart(messageBodyPart);

	         messageBodyPart = new MimeBodyPart();
	         DataSource source = new FileDataSource("E://MynewWebProject.pdf");
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName("E://MynewWebProject.pdf");
	         multipart.addBodyPart(messageBodyPart);

	         message.setContent(multipart);

		   // Send message
		   Transport.send(message);

		   System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	}
}
