package org.epaski.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.epaski.share.SharedData;

public class DBWorkSEC {

	

private Connection conn;

	private SharedData sharedData;
    private String dataBase, user, pass, port, ip;

    public DBWorkSEC(SharedData sharedData) {
    	this.sharedData = sharedData;
    }

	public void connectDB() {
		
//		Connection conn = null;
		
		ip = sharedData.getDbServer();
		port = sharedData.getDbServerPort();
		dataBase = sharedData.getDbBaseName();
		user = sharedData.getDbUser();
		pass = sharedData.getDbUserPass();
		
//		String url = "jdbc:postgresql://"+ ip + "/" + dataBase;
		String url = "jdbc:mysql://"+ ip + ":" + port + "/" + dataBase;
		
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			System.out.println("Połączenie otwarte: " + !conn.isClosed());
		} catch (Exception e) {
			System.out.println("Nie tym razem!");
			e.printStackTrace();
		}
	}
	
	public void closConn() {
		try {
			conn.close();
			System.out.println("Połączenie zamknięte: " + conn.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ResultSet getResult(String query){
		
		MailWorker mailWorker = new MailWorker(sharedData);
		String to;
		ResultSet rs = null;
		
		query = "select * from users;";
		
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
						
			while (rs.next()) {
				to = rs.getString("username") + "@" + rs.getString("domain");
				System.out.print("Próba wysyłki do: " + to);
				mailWorker.createMail(to);
				System.out.println(": poszło!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
}
