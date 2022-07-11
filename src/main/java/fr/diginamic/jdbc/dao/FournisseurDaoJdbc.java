package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.DbConnect;
import fr.diginamic.jdbc.entities.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {
	
	private int tryUpdate(String update, int id, String name) {
		int response = 0;
		try(Connection connection = DbConnect.connect()) {
			PreparedStatement prepst = connection.prepareStatement(update);
			prepst.setInt(1, id);
			if(name != null) {				
				prepst.setString(2, name);
			}
			response = prepst.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return response;
	}
	
	private int tryUpdate(String update, String ancien, String nouveau) {
		int response = 0;
		try(Connection connection = DbConnect.connect()) {
			PreparedStatement prepst = connection.prepareStatement(update);
			prepst.setString(1, ancien);
			prepst.setString(2, nouveau);
			response = prepst.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return response;
	}
	
	private int tryUpdate(String update, int id) {
		return tryUpdate(update, id, null);
	}
	
	private ResultSet selectAll() {
		ResultSet results = null;
		try(Connection connection = DbConnect.connect()) {
			String selectQuery = "SELECT * FROM fournisseur";
			Statement st = connection.createStatement();
			results = st.executeQuery(selectQuery);
		} catch(SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		String insertQuery = "INSERT INTO fournisseur (id, nom) VALUES (?, ?)";
		if (tryUpdate(insertQuery, fournisseur.getId(), fournisseur.getNom()) > 0) {
			System.out.println("Insertion des données effectuée");
			System.out.println();
			return;
		}
		System.out.println("Une erreur est survenue lors de l'insertion");
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		String updateQuery = "UPDATE fournisseur SET nom = ? WHERE nom = ?";
		return tryUpdate(updateQuery, ancienNom, ancienNom);
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		String deleteQuery = "DELETE FROM fournisseur WHERE id = ?";
		return tryUpdate(deleteQuery, fournisseur.getId()) > 0;
	}	
	
}
