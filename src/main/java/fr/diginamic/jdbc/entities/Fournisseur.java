package fr.diginamic.jdbc.entities;

public class Fournisseur {

	private int id;
	private String nom;
	
	public Fournisseur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return String.format("Fournisseur [id = %s, nom = %s]", id, nom);
	}

	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
