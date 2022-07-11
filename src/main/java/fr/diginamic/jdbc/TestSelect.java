package fr.diginamic.jdbc;

import java.util.List;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		
		FournisseurDaoJdbc dbManage = new FournisseurDaoJdbc();
		
		List<Fournisseur> fournisseurs = dbManage.extraire();
		
		for (Fournisseur f: fournisseurs) {
			System.out.println(f.toString());
		}
		//Essayez d’insérer en base de données un fournisseur dont le nom contient un simple quote commepar exemple «L’Espace Création». Que se passe t’il?
		
	}

}
