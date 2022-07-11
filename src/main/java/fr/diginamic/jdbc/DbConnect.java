package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbConnect {

	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PW;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		DB_URL = bundle.getString("jdbc.db.url");
		DB_USER = bundle.getString("jdbc.db.user");
		DB_PW = bundle.getString("jdbc.db.pw");
	}
	
	private DbConnect() {}
	
	public static Connection connect() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
		return connection;
	}
	
}
