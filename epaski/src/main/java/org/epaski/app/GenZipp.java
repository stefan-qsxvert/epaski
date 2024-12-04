package org.epaski.app;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import java.io.*;

import org.epaski.gui.Gui;

public class GenZipp{
	Gui gui;
	
	public GenZipp() {
	}
	
	public GenZipp(Gui gui){
		this.gui = gui;
		
	}

    private String sciezkaPliki;
    private String sciezkaHasla;
    private String pass;
    
    public void setSciezkaPliki(String sciezka){
        this.sciezkaPliki = sciezka;
    }
    public void setSciezkaHasla(String sciezka){
        this.sciezkaHasla = sciezka;
    }

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	public void genZipp() {
		
		Boolean raport = gui.getCheckBoxSelected(4);

        sciezkaPliki = gui.getTextFieldText(1);
        sciezkaHasla = gui.getTextFieldText(0);

        Ext ext  = new Ext();
        String[] nfo = null;
        ZipFile plik = null;

        ExtFilter filter = new ExtFilter();
        filter.setExt(".pdf");
        File folder = new File(gui.getTextFieldText(1));
        String[] pliki = folder.list(filter);
        	
        gui.progressBar[1].setMinimum(0);
        gui.progressBar[1].setMaximum(pliki.length);

        for (int i = 0 ; i < pliki.length; i++) {
            boolean zipped = true;
            gui.progressBar[1].setValue(i+1);

            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(sciezkaHasla + pliki[i].toString().substring(0, pliki[i].lastIndexOf(".")) + ".txt"));
                nfo = fileReader.readLine().split(",");
                fileReader.close();

                pass = nfo[0];

                plik = new ZipFile(sciezkaPliki + pliki[i].toString().substring(0, pliki[i].toString().lastIndexOf("."))+".zip", pass.toCharArray());
                ZipParameters parameters = new ZipParameters();
                parameters.setEncryptFiles(true);
                parameters.setCompressionLevel(CompressionLevel.NORMAL);
                parameters.setCompressionMethod(CompressionMethod.STORE);
                parameters.setOverrideExistingFilesInZip(true);
                parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
                plik.addFile( sciezkaPliki + pliki[i], parameters);
            } catch (Exception e) {
                zipped = false;
                System.out.println(e.getMessage());
                ext.log("logErr.txt", pliki[i].toString() + " nie spakowany");
            }
            
          try {
         if(zipped){
           
		  if (raport){
                System.out.println( pliki[i].toString() + " spakowany");
			}else{}
                ext.log("logZipp.txt", pliki[i].toString() + " spakowany");
          }else{
                ext.log("logErr.txt", pliki[i].toString() + " nie spakowany");
            }
            }catch (Exception e) {
				// TODO: handle exception
            	System.out.println(e.toString());
			}
       }

	}
}
