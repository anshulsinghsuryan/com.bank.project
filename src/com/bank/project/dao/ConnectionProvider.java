package com.bank.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName(DatabaseDetails.DRIVER);
			con = DriverManager.getConnection(DatabaseDetails.URL, 
					DatabaseDetails.USERNAME, DatabaseDetails.PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
