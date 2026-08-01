package org.epaski.sql;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public class DBWorker {
	
	public DBWorker() {
		}
	
	public static void main(String[] args) throws AddressException, MessagingException {
//		DBWorkSEC dbWorkSEC = new DBWorkSEC();
//		dbWorkSEC.connectDB();
//		dbWorkSEC.getResult(null);
//		dbWorkSEC.closConn();
		
		MailWorker mailWorker = new MailWorker();
		for (int i = 0; i <= 500; i++) {
			mailWorker.createMail();
			System.out.println(i);
		}
	}
}
