package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

public class TestUpdate {

	public static void main(String[] args) {
		
		FournisseurDaoJdbc dbManage = new FournisseurDaoJdbc();
		
		int updateRes = dbManage.update("La Maison de la Peinture", "La Maison des Peintures");
		
		if (updateRes > 0) {
			System.out.println("MAJ des données effectuée");
			return;
		}
		System.out.println("Une erreur est survenue lors de la MAJ");
		System.out.println();
		
	}

}
