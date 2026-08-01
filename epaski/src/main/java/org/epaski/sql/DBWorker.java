package org.epaski.sql;

public class DBWorker {
	
	public DBWorker() {
		}
	
	public static void main(String[] args) {
		DBWorkSEC dbWorkSEC = new DBWorkSEC();
		dbWorkSEC.connectDB();
		dbWorkSEC.getResult(null);
		dbWorkSEC.closConn();
	}
}
