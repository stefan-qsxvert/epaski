package org.epaski.app;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import org.epaski.gui.Gui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class Ext {
	Gui gui;
	
	public Ext() {
		
	}
//	
	public Ext(Gui gui) {
		this.gui = gui;
	}
	
	private Boolean silent = true;
	
    public String passy(String sciezka) {
        String plik = null;
        

        try {
            BufferedReader fileReader = new BufferedReader( new FileReader(sciezka));
            plik = fileReader.readLine() ;
            fileReader.close();
        }catch (Exception e){
        	if (silent) {
            System.out.printf(e.toString());
        	}
        }
        return plik.toString();
    }

    public void log(String log, String msg){
    try{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:/epaski/log/" + log, true));
            bufferedWriter.write(new Date() + " " + msg);
            bufferedWriter.newLine();
            bufferedWriter.close();
    }catch (Exception e) {
        }
    }
    public void logBcp(String log){
        try {
            Files.move(Path.of("C:/epaski/log/" + log), Path.of("C:/epaski/log/" + String.valueOf(new Date()).replace(":", "_") + " " + log));
        }catch (Exception e){
           // System.out.println(e.toString());
        }
    }
    public String fileChooser(String path){
        JFileChooser fileChooser = new JFileChooser("d:", FileSystemView.getFileSystemView());
        fileChooser.setDialogTitle("select folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(null);
        try {
            return fileChooser.getSelectedFile().toString() + "\\";
        }catch(Exception e){
            return path;
        }
    }

    public void setNfo(String path) {
        Ext ext =  new Ext();
        String[] mkplik = null;
        try {
            String redCont = "0";
            String pth = path.substring(0, path.length()-1).replace("/", "\\");
            pth = new String(pth.substring(0, pth.lastIndexOf("\\")+1));
            File file = new File(pth + "setNfo.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((redCont = new String(bufferedReader.readLine())) != null){
                mkplik = redCont.split(",");
                BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("C:/epaski/nfo/" + mkplik[0] + ".txt"));
                bufferedWriter.write(mkplik[1] + "," + mkplik[2]);
                bufferedWriter.close();
                mkplik[0] = "0";
                ext.log("logNfo.txt", mkplik[0] + " utworzony");
            }
            bufferedReader.close();
        } catch (Exception e){
            if (mkplik[0] != "0"){
            ext.log("logErr.txt", mkplik[0] + " nie udało się utworzyć");
            if (!silent) {
             System.out.println("brak pliku setNfo.txt " + e.toString());
            }
            }
        }
    }
    public void rmPki(String pathPki){
        File pliki = new File(pathPki);
        String[] plikiList = pliki.list();

        for (int i = 0; i < plikiList.length; i++) {
            try {
                Files.delete(Path.of(pliki.toPath() + "\\" + plikiList[i]));
            } catch (Exception e) {
            	if (!silent) {
                System.out.printf(e.toString());
            	}
            }
        }
    }
    public void cleanFiles() {
    String filePath = gui.getTextFieldText(1);
	File folder = new File(filePath);
	String[] files = folder.list();
	if(files.length > 0){
	gui.progressBar[0].setMinimum(0);
	gui.progressBar[0].setMaximum(files.length);
	if (gui.getCheckBoxSelected(3)){
		for (int i = 0; i < files.length; i++) {
			gui.progressBar[0].setValue(i+1);
			try{
				Files.delete(Path.of(gui.getTextFieldText(1) + files[i]));
			}catch (Exception e){}
		}
	}}else{}
    }
}
