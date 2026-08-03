package org.epaski.sql;

import java.io.IOException;

import org.epaski.pdf.PDFWorker;
import org.epaski.share.SharedData;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public class DBWorker {
	
	public DBWorker() {
		}
	
	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		SharedData sharedData = new SharedData();
		
//		DBWorkSEC dbWorkSEC = new DBWorkSEC(sharedData);
//		dbWorkSEC.connectDB();
//		dbWorkSEC.getResult(null);
//		dbWorkSEC.closConn();
		
		PDFWorker pdfWorker = new PDFWorker();
		pdfWorker.myDocument();

		
	}
}
