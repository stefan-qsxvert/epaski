package epaski.send;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import epaski.app.Ext;
import epaski.app.ExtFilter;
import epaski.gui.Gui;
import jakarta.mail.Address;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;

public class Push{

	Gui gui;
	
	public Push() {
	}
	public Push(Gui gui){
		this.gui = gui;
	}
	
	public void sendMail() {
		Ext ext = new Ext();
        ext.logBcp("logNfo.txt");
        ext.logBcp("logSend.txt");
        ext.logBcp("logErr.txt");

        String[] plik = null;
        String nfo[] = null;

        File folderZippy = new File(gui.getTextFieldText(1));

        ExtFilter filter = new ExtFilter();
        filter.setExt(".zip");

        plik = folderZippy.list(filter);
        gui.progressBar[2].setMinimum(0);
        gui.progressBar[2].setMaximum(plik.length);
        
        String[] smtp = gui.getTextFieldText(2).split(":");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", String.valueOf(gui.getCheckBoxSelected(0)));
        props.put("mail.smtp.host", smtp[0]);
        props.put("mail.smtp.user", gui.getTextFieldText(3));
        
        if (gui.getCheckBoxSelected(6)) {
        	props.put("mail.smtp.starttls.enable", "true");
        	props.put("mail.smtp.port", smtp[1]);
          }else {}
          if (gui.getCheckBoxSelected(7)) {
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.port", smtp[1]);
          }else {}
          if (gui.getCheckBoxSelected(8)) {
          }else {}
          
        
        
        if (gui.getCheckBoxSelected(1)) {
            props.put("mail.smtp.proxy.host", gui.getTextFieldText(6));
            props.put("mail.smtp.proxy.port", gui.getTextFieldText(7));
            props.put("mail.smtp.proxy.user", gui.getTextFieldText(8));
            props.put("mail.smtp.proxy.password", String.valueOf(gui.getPasswordFildText(1)));
        }
        
        Auth auth = new Auth(gui.getTextFieldText(3),String.valueOf(gui.getPasswordFildText(0)));
        Session session = Session.getInstance(props, auth);

        Transport tr = null;
        try {
        	if (!gui.getCheckBoxSelected(2)) {
          		tr = session.getTransport();
        		tr.connect();
				System.out.println("connected: " + tr.isConnected());
             }else {}   
        	} catch (Exception e) {
        		e.toString();
		}
       int j = 0;
        for (int i = 0; i < plik.length; i++) {
        	gui.progressBar[2].setValue(i+1);
        		Mail mail = null;
        		
        		try {
                BufferedReader fileReader = new BufferedReader( new FileReader(gui.getTextFieldText(0) + plik[i].toString().substring(0, plik[i].toString().lastIndexOf(".")) + ".txt"));
                nfo = fileReader.readLine().split(",");
                fileReader.close();
                mail = new Mail(gui);

                mail.setTo(nfo[1]);
                mail.setOd(gui.getTextFieldText(5));
                mail.setTemat(gui.getTextFieldText(4));
                mail.setSciezka(gui.getTextFieldText(1));
                mail.setPlik(plik[i]);
                mail.mailPrepare();
 
                Address[] addr = new Address[1];
                addr[0] = new InternetAddress(nfo[1]);
               
                if (!gui.getCheckBoxSelected(2)) {
                tr.sendMessage(mail.mailPrepare(), addr);
                    ext.log("logSent.txt" , plik[i].toString() + " wysłany na adres: " + nfo[1]);
                    if (gui.getCheckBoxSelected(4)) {
                        System.out.println(plik[i].toString() + " wysłany na adres: " + nfo[1]);
                    }else{}
                }else {
                	System.out.println(plik[i].toString() + " zostanie wysłany na adres: " + nfo[1]);
                    ext.log("logSent.txt" , plik[i].toString() + " zostanie wysłany na adres: " + nfo[1]);
                	try {
    					Thread.sleep(16);
    				} catch (Exception e) {
    				}
                }
        		
        		}catch (Exception e){
            	System.out.println(e.toString());
                //System.out.println(plik[i].toString() + " błąd wysyłki");
                ext.log("logErr.txt" , plik[i].toString() + " nie wysłany, brak nfo");
            }
        }
        
        try {
        	if (!gui.getCheckBoxSelected(2)) {
        	tr.close();
			System.out.println("disconnected: " + !tr.isConnected());
			}else {}
        } catch (Exception e) {
            ext.log("logErr.txt" , plik[j].toString() + " " + e.toString());
			e.toString();
		}
        gui.setCheckBoxSelect(2);

        try {
            Thread.sleep(500);
        }catch (Exception e){}
	}
}

