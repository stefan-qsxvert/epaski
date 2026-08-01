package org.epaski.sql;


import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

public class MailWorker {
	
	public void createMail() throws AddressException, MessagingException {
		
		String username = " ";
		String password = " ";
	
	Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "mail.manester.org");
    props.put("mail.smtp.port", "587");
	
    Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
	
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(username));
	message.setSubject("Moja wiadomość");
	message.setRecipient(RecipientType.TO, new InternetAddress("helpdesk@manester.org"));
	message.setText("Treść mojej wiadomości");
	
	Transport.send(message);
	}	
	
}
