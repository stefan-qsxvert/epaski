package org.epaski.send;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Collectors;

import org.epaski.app.ExtFilter;

public class Attach {

    //private BodyPart zalacznik, tresc, img;
    //private File file;
    //private DataSource dataSource;
    //private DataHandler dataHandler;
    //private File wiadomosc;
    //private String txt;

    public BodyPart addAttch(int i, String sciezka, String plik){
    	BodyPart zalacznik = null;
        try {
        	zalacznik = new MimeBodyPart();
            File file = new File(sciezka.replace("\\","//") + plik);
            DataSource dataSource = new FileDataSource(file);
            DataHandler dataHandler = new DataHandler(dataSource);
            zalacznik.setDataHandler(dataHandler);
            zalacznik.setFileName(plik);
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return zalacznik;
    }
    public BodyPart addTresc(int i){
        ExtFilter extFilter =  new ExtFilter();
        extFilter.setExt(".html");
        BodyPart tresc = null;
        try {
            tresc = new MimeBodyPart();
            File msg = new File("c://epaski//msg//");
            String[] asd = msg.list(extFilter);
            File wiadomosc = new File("c://epaski//msg//" + asd[0]);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(wiadomosc)));
            String txt = (bufferedReader.lines().collect(Collectors.joining()));
            tresc.setContent(txt, "text/html; charset=utf-8");
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return tresc;
    }
    public BodyPart addImg(){
        ExtFilter extFilter =  new ExtFilter();
        extFilter.setExt(".png");
        BodyPart img = null;
        try {
            img = new MimeBodyPart();
            File msg = new File("c://epaski//msg//");
            String[] asd = msg.list(extFilter);
            File attImg = new File("c://epaski//msg//" + asd[0]);
            DataSource dataSource = new FileDataSource(attImg);
            DataHandler dataHandler = new DataHandler(dataSource);
            img.setDataHandler(dataHandler);
            img.setFileName(asd[0]);
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return img;
    }
}
