package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.MissingResourceException;

public class App {
	
	public static void main(String[] args) {
		
		try(Connection connection = DbConnect.connect()) {
			
			System.out.println(connection);
			
		} catch(MissingResourceException | SQLException e) {
			
			System.err.println(e.getMessage());
			
		}
		
	}
	
}
