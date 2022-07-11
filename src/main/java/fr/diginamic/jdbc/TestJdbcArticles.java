package fr.diginamic.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.entities.Article;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestJdbcArticles {

	public static void main(String[] args) {

		ArticleDaoJdbc dbManage = new ArticleDaoJdbc();	
		
		Fournisseur f = new Fournisseur(5, "La Maison de la Peinture");
		
		List<Article> articles = new ArrayList<>(
			Arrays.asList(
				new Article(11, "P01", "Peinture blanche 1L", 12.5f, f),
				new Article(12, "P02", "Peinture rouge mate 1L", 15.5f, f),
				new Article(13, "P03", "Peinture noire laquée 1L", 17.8f, f),
				new Article(14, "P04", "Peinture bleue mate 1L", 15.5f, f)
			)
		);
		
		for (Article article: articles) {
			dbManage.insert(article);
			
			float price = article.getPrix();
			float newPrice = price + price * .25f;
			dbManage.update(price, newPrice);
		}
		
		for (Article article: dbManage.extraire()) {
			System.out.println(article.toString());
		}
		
		System.out.println();
		try {
			System.out.printf("%.2f\n", dbManage.getAvg());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
		
		for (Article article: dbManage.extraire()) {
			if (article.getDesignation().matches(".*Peinture.*")) {
				dbManage.delete(article);
			}
		}
		
	}

}
