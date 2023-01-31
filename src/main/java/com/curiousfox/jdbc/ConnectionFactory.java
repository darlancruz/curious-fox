package com.curiousfox.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	/** The url. */
	private final String url = "jdbc:postgresql://localhost:5432/curious_fox";
	
	/** The user. */
	private final String user = "postgres";
	
	/** The password. */
	private final String password = "password";
	
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(
				url, user, password);
			} catch (SQLException  | ClassNotFoundException e) {
				throw new RuntimeException(e);
			} 
		}
	}
