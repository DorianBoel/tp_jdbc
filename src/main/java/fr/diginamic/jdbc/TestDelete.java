package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entities.Fournisseur;

public class TestDelete {

	public static void main(String[] args) {
		
		FournisseurDaoJdbc dbManage = new FournisseurDaoJdbc();
		
		Fournisseur f = new Fournisseur(4, "La Maison de la Peinture");
		
		if (dbManage.delete(f)) {
			System.out.println("Suppression des données effectuée");
			return;
		}
		System.out.println("Une erreur est survenue lors de la suppression");
		System.out.println();

	}

}
