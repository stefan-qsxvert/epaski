package epaski.app;


import org.apache.pdfbox.pdmodel.PDDocument;

import epaski.gui.Gui;

import java.io.File;


public class PdfSplit{
	private Gui gui;
	
	public PdfSplit(){
	}
	
	public PdfSplit(Gui gui){
		this.gui = gui;
	}
	
    public void pdfSplit() {

        Integer d = 0;
        Ext ext = new Ext();

        File paskiPdf = new File(gui.getTextFieldText(9));
        String[] paskiPdfLista = paskiPdf.list();
        
        for (int i = 0 ; i < paskiPdfLista.length; i++) {
        	  
            PDDocument document = null;
            //Splitter splitter = null;
            //List<PDDocument> Pages;
            //Iterator<PDDocument> iterator = null;
            File file = null;

            try {
                file = new File(gui.getTextFieldText(9) + paskiPdfLista[i].toString());
                document = PDDocument.load(file);
                //splitter = new Splitter();
                //Pages = splitter.split(document);
                //iterator = Pages.listIterator();
                //PDDocument pd = null;
                
                gui.progressBar[0].setMinimum(0);
                gui.progressBar[0].setMaximum(document.getNumberOfPages());
                gui.progressBar[0].setValue(0);

                for (int ii = 0; ii < document.getNumberOfPages(); ii++){
                   //while(iterator.hasNext()){
            	  gui.progressBar[0].setValue(d+1);
                    //pd = iterator.next();
                    PDDocument pd = new PDDocument();
                    pd.addPage(document.getPage(ii));
                    pd.save(gui.getTextFieldText(1) + d++ + ".pdf");
                    pd.close();
               }
                document.close();
                //System.out.println( "dokument " + paskiPdfLista[i] + " podzielony");
                ext.log("logSplit.txt", "dokument " + paskiPdfLista[i] + " podzielony");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
  	}
}

