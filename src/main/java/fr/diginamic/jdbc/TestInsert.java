package fr.diginamic.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class TestInsert {

	public static void main(String[] args) {
		
		try(Connection connection = DbConnect.connect()) {
			
			Statement statement = connection.createStatement();
			String insert1 = "INSERT INTO fournisseur (id, nom) VALUES (4, 'La Maison de la Peinture')";
			
			int res = statement.executeUpdate(insert1);
			System.out.println(res > 0);
			
		} catch(SQLException e) {
			
			System.err.println(e.getMessage());
			
		}
		
	}

}
