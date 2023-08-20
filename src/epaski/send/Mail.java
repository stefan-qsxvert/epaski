package epaski.send;

import jakarta.mail.Multipart;
import jakarta.mail.*;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import epaski.gui.Gui;

import java.util.Properties;

public class Mail {
      private String od, temat, sciezka, plik, to;

      private Gui gui;
      
      public Mail() {
      }
      public Mail(Gui gui) {
    	  this.gui = gui;
      }

     public void setOd(String od){
         this.od = od;
     }
     public void setTo(String to){
        this.to = to;
    }
     public void setTemat(String temat){
         this.temat = temat;
     }
  
     public void setSciezka(String sciezka){
         this.sciezka = sciezka;
     }
     public void setPlik(String plik){
         this.plik = plik;
     }	
	
	//public Message getMessage() {
		//return message;
	//}

    public Message mailPrepare() {

       Properties props = new Properties();        
       Auth auth = new Auth(null,null);
       Session session = Session.getInstance(props, auth);
       Attach attach = new Attach();
       Message message = null;
       Address[] repalyTo = new InternetAddress[1];


        try {
            repalyTo[0] = new InternetAddress("stefan.abramek@grupaazoty.com");
            Multipart multipart = new MimeMultipart();
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(od));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(temat);
            multipart.addBodyPart(attach.addAttch(0, sciezka, plik));
            multipart.addBodyPart(attach.addTresc(0));
            multipart.addBodyPart(attach.addImg());
            message.setContent(multipart, "text/html; charset=utf-8, lang=pl");
            message.setReplyTo(repalyTo);
            } catch (Exception e) {
                System.out.println(e.toString());
                //ext.log("logErr.txt", plik.toString() + " błąd wysyłki");
            }
       return message;
}}