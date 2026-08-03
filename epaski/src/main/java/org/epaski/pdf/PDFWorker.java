package org.epaski.pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFWorker {
	
	public void myDocument() throws IOException {
		PDDocument document = new PDDocument();
		document.addPage(new PDPage());
		document.save(new File("d:\\myPDF.pdf"), null);
		document.close();
	}

}
