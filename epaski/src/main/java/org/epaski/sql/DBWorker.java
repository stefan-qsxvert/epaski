package org.epaski.sql;

import org.epaski.share.SharedData;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public class DBWorker {
	
	public DBWorker() {
		}
	
	public static void main(String[] args) throws AddressException, MessagingException {
		SharedData sharedData = new SharedData();
		
		DBWorkSEC dbWorkSEC = new DBWorkSEC(sharedData);
		dbWorkSEC.connectDB();
		dbWorkSEC.getResult(null);
		dbWorkSEC.closConn();
		
//		MailWorker mailWorker = new MailWorker();
//		for (int i = 0; i <= 500; i++) {
//			mailWorker.createMail();
//			System.out.println(i);
//		}
	}
}
