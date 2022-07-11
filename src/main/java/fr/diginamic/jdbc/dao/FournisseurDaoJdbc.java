package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.DbConnect;
import fr.diginamic.jdbc.entities.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {
	
	private int tryUpdate(String update) {
		int response = 0;
		try(Connection connection = DbConnect.connect()) {
			Statement st = connection.createStatement();
			System.out.println(update);
			System.out.println();
			response = st.executeUpdate(update);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return response;
	}
	
	private ResultSet selectAll() {
		ResultSet results = null;
		try(Connection connection = DbConnect.connect()) {
			String selectQuery = "SELECT * FROM fournisseur";
			Statement st = connection.createStatement();
			results = st.executeQuery(selectQuery);
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return results;
	}
	
	private Fournisseur createInstance(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String nom = result.getString("nom");
		return new Fournisseur(id, nom);
	}

	@Override
	public List<Fournisseur> extraire() {
		try {
			ResultSet allRows = selectAll();
			List<Fournisseur> instanceList = new ArrayList<>();
			while(allRows.next()) {
				Fournisseur newInstance = createInstance(allRows);
				instanceList.add(newInstance);
			}
			return instanceList;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		String insertQuery = String.format(
			"INSERT INTO fournisseur (id, nom) VALUES (%d, '%s')",
			fournisseur.getId(),
			fournisseur.getNom()
		);
		if (tryUpdate(insertQuery) > 0) {
			System.out.println("Insertion des données effectuée");
			System.out.println();
			return;
		}
		System.out.println("Une erreur est survenue lors de l'insertion");
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		String updateQuery = String.format(
			"UPDATE fournisseur SET nom = '%s' WHERE nom = '%s'",
			nouveauNom,
			ancienNom
		);
		return tryUpdate(updateQuery);
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		String deleteQuery = String.format(
			"DELETE FROM fournisseur WHERE id = %d",
			fournisseur.getId()
		);
		return tryUpdate(deleteQuery) > 0;
	}	
	
}
