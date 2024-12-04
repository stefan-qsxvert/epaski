package org.epaski.app;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.epaski.gui.Gui;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfRename {
Gui gui;

	public PdfRename(){
	}

	public PdfRename(Gui gui){
		this.gui = gui;
	}
	
	public void pdfRename(){

        Ext ext = new Ext();

        ExtFilter extFilter = new ExtFilter();
        extFilter.setExt(".pdf");

        File pliki = new File(gui.getTextFieldText(1));

        String[] plikName = pliki.list(extFilter);

        String fileText = null;
        File file = null;
        PDFTextStripper pdfTextStripper = null;

        gui.progressBar[0].setMinimum(0);
        gui.progressBar[0].setMaximum(plikName.length);
        gui.progressBar[0].setValue(0);

        for (int i = 0; i < plikName.length; i++) {

            gui.progressBar[0].setValue(i + 1);
            PDDocument document = null;
            try {
                file = new File(gui.getTextFieldText(1) + plikName[i]);
                document = Loader.loadPDF(file);
                pdfTextStripper = new PDFTextStripper();
                fileText = new String(pdfTextStripper.getText(document));
                document.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            String[] redLinie = fileText.split(System.lineSeparator());
            String pdfName = new String();

            for (int ii = 0; ii < redLinie.length; ii++) {
                if (redLinie[ii].contains("Numer ewid")) {
                    pdfName = new String(redLinie[ii]);
                    pdfName = new String(pdfName.substring(25, pdfName.length() - 8));
                    pdfName = new String(pdfName.replace(" ", ""));
                    break;
                }
            }

            File pasek = new File(gui.getTextFieldText(1) + pdfName + ".pdf");
            if (pasek.exists() && !pasek.getName().equals(file.getName())) {
                try {
                    PDDocument pdd0 = Loader.loadPDF(pasek);
                    PDDocument pdd = Loader.loadPDF(file);
                    pdd0.addPage(pdd.getPage(0));
                    pdd0.save(pasek);
                    pdd.close();
                    pdd0.close();
                    Files.delete(Path.of(file.getPath()));
                }catch (Exception ex) {
                   // System.out.println("eee");
                }
                }else if(pasek.exists() && pasek.getName().equals(file.getName())){

                }else {

                try {
                    if (pdfName.length() != 0) {
                        Files.move(Path.of(gui.getTextFieldText(1) + plikName[i]), Path.of(gui.getTextFieldText(1) + pdfName + ".pdf"));
                    } else {
                        try {
                            Files.delete(Path.of(gui.getTextFieldText(1) + plikName[i]));
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                if (gui.getCheckBoxSelected(4)) {
                    System.out.println(plikName[i] + " ->-> " + pdfName + ".pdf");
                } else {
                }
                ext.log("logSplit.txt", plikName[i] + " ->-> " + pdfName + ".pdf");
            }
        }
	}
	
}
