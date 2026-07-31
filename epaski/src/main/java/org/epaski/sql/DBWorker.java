package org.epaski.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBWorker {
	
	private Connection conn;
	private Boolean connectionState;
	private String ip;
	private String dataBase;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBWorker dbw = new DBWorker();
		dbw.connectDB();
	}

	public void connectDB() {
		
		ip = "10.8.0.10";
		dataBase = "mail";

		String url = "jdbc:postgresql://"+ ip + "/" + dataBase;
		
		connectionState = false;
		
		try {

			Object sharedObjects;
			conn = DriverManager.getConnection(url, "mailuser", "adgjmpad"); 
//					sharedObjects.getLoginScreen().getUserTextField().getText(), 
//					sharedObjects.getLoginScreen().getPasswordField().getText());
			System.out.println("Baza połączona!");
		} catch (Exception e) {
			System.out.println("Nie tym razem!");
			//e.printStackTrace();
		}
	}
	
	public ResultSet getResult(String query){
		
		ResultSet rs = null;
		
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
		} catch (Exception e) {
			System.out.println();
		}
		return rs;
	}
}
