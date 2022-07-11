package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestInsert {

	public static void main(String[] args) {
		
		FournisseurDaoJdbc dbManage = new FournisseurDaoJdbc();
		
		Fournisseur f = new Fournisseur(4, "La Maison de la Peinture");
		
		dbManage.insert(f);
		
		Fournisseur f2 = new Fournisseur(5, "L'Espace Création");
		
		dbManage.insert(f2);
		
	}

}
