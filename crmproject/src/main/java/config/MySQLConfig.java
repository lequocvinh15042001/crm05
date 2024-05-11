package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConfig {

	// tao ra ham mo ket noi toi CSDL, static de luu ham tren RAM luon de dung lien tuc
	public static Connection getConnection() {
		
		Connection connection = null;
		
		try {
			
			String url = "jdbc:mysql://localhost:3307/crmapp";
			String username = "root";
			String password = "admin123";
			
			// Khai bao driver su dung la mySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Khai bao thong tin duong dan mysql server se ket noi toi
			connection = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			System.out.println("Loi ket noi CSDL " + e.getMessage());
		}
		
		return connection;
		
	}
	
}
