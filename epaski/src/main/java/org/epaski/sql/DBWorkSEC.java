package org.epaski.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorkSEC {

	

private Connection conn;


    private String dataBase, user, pass, port, ip;


	public void connectDB() {
		
//		Connection conn = null;
		
		
		
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
		
		ResultSet rs = null;
		
		query = "select * from users;";
		
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
						
			while (rs.next()) {
				System.out.println("Użytkownik: " + rs.getString("username") + "@" + rs.getString("domain"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
}
