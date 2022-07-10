package fr.diginamic.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDelete {

	public static void main(String[] args) {
		
		try(Connection connection = DbConnect.connect()) {
			
			Statement statement = connection.createStatement();
			String delete = "DELETE FROM fournisseur WHERE id = 4";
			
			int res = statement.executeUpdate(delete);
			System.out.println(res > 0);
			
		} catch(SQLException e) {
			
			System.err.println(e.getMessage());
			
		}
		
	}

}
