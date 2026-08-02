package org.epaski.sql;


import java.util.Properties;
import org.epaski.share.SharedData;

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
	
	private SharedData sharedData;
	
	public MailWorker(SharedData sharedData) {
		this.sharedData = sharedData;
	}
	
	public void createMail(String to) throws AddressException, MessagingException {
		
	Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", sharedData.getMailServer());
    props.put("mail.smtp.port", sharedData.getMailServerPort());
	
    Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sharedData.getMailUser(), sharedData.getMailPass());
                }
            });
	
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(sharedData.getMailUser()));
	message.setSubject("Moja wiadomość");
	message.setRecipient(RecipientType.TO, new InternetAddress(to));
	message.setText("Treść mojej wiadomości");
	
	Transport.send(message);
	}
}
