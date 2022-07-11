package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.DbConnect;
import fr.diginamic.jdbc.entities.Article;
import fr.diginamic.jdbc.entities.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao {
	
	private int tryUpdate(String update, Article article) {
		int response = 0;
		try(Connection connection = DbConnect.connect()) {
			PreparedStatement prepst = connection.prepareStatement(update);
			prepst.setInt(1, article.getId());			
			prepst.setString(2, article.getRef());
			prepst.setString(3, article.getDesignation());
			prepst.setFloat(4, article.getPrix());
			prepst.setInt(5, article.getFournisseur().getId());
			response = prepst.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return response;
	}
	
	private int tryUpdate(String update, float ancien, float nouveau) {
		int response = 0;
		try(Connection connection = DbConnect.connect()) {
			PreparedStatement prepst = connection.prepareStatement(update);
			prepst.setFloat(1, nouveau);
			prepst.setFloat(2, ancien);
			System.out.println(prepst.toString());
			response = prepst.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return response;
	}
	
	private ResultSet selectAll() {
		ResultSet results = null;
		try(Connection connection = DbConnect.connect()) {
			String selectQuery = "SELECT * FROM article";
			Statement st = connection.createStatement();
			results = st.executeQuery(selectQuery);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return results;
	}
	
	private Article createInstance(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String ref = result.getString("ref");
		String designation = result.getString("designation");
		float prix = result.getFloat("prix");
		Fournisseur f = new FournisseurDaoJdbc().extraire().get(result.getInt("id_fournisseur") - 1);
		return new Article(id, ref, designation, prix, f);
	}

	@Override
	public List<Article> extraire() {
		try {
			ResultSet allRows = selectAll();
			List<Article> instanceList = new ArrayList<>();
			while(allRows.next()) {
				Article newInstance = createInstance(allRows);
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
	public void insert(Article article) {
		String insertQuery = "INSERT INTO article (id, ref, designation, prix, id_fournisseur) VALUES (?, ?, ?, ?, ?)";
		if (tryUpdate(insertQuery, article) > 0) {
			System.out.println("Insertion des données effectuée");
			System.out.println();
			return;
		}
		System.out.println("Une erreur est survenue lors de l'insertion");
	}

	@Override
	public int update(float ancienPrix, float nouveauPrix) {
		String updateQuery = "UPDATE article SET prix = ? WHERE prix = ?";
		return tryUpdate(updateQuery, ancienPrix, ancienPrix);
	}

	@Override
	public boolean delete(Article article) {
		String deleteQuery = "DELETE FROM article WHERE id = ?";
		return tryUpdate(deleteQuery, article) > 0;
	}
	
	public float getAvg() throws SQLException {
		ResultSet results = null;
		try(Connection connection = DbConnect.connect()) {
			String selectQuery = "SELECT AVG(prix) AS average FROM article";
			Statement st = connection.createStatement();
			results = st.executeQuery(selectQuery);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		results.next();
		return results.getFloat(1);
	}
	
}
