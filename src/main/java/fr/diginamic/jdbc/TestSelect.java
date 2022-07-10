package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entities.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		
		List<Fournisseur> fournisseurs = new ArrayList<>();
		
		try(Connection connection = DbConnect.connect()) {
			
			Statement statement = connection.createStatement();
			String select = "SELECT * FROM fournisseur";
			
			ResultSet res = statement.executeQuery(select);
			while(res.next()) {
				int id = res.getInt("id");
				String nom = res.getString("nom");
				Fournisseur f = new Fournisseur(id, nom);
				fournisseurs.add(f);			
			}
			
		} catch(SQLException e) {
			
			System.err.println(e.getMessage());
			
		}
		
		for (Fournisseur f: fournisseurs) {
			System.out.println(f.toString());
		}
		
	}

}
