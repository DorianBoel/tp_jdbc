package fr.diginamic.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class TestUpdate {

	public static void main(String[] args) {
		
		try(Connection connection = DbConnect.connect()) {
			
			Statement statement = connection.createStatement();
			String update = "UPDATE fournisseur SET nom = 'La Maison des Peintures' WHERE id = 4";
			
			int res = statement.executeUpdate(update);
			System.out.println(res > 0);
			
		} catch(SQLException e) {
			
			System.err.println(e.getMessage());
			
		}
		
	}

}
