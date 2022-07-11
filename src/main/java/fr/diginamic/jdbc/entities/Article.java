package fr.diginamic.jdbc.entities;

public class Article {

	private int id;
	private String ref;
	private String designation;
	private float prix;
	private Fournisseur fournisseur;
	
	public Article(int id, String ref, String designation, float prix, Fournisseur fournisseur) {
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.prix = prix;
		this.fournisseur = fournisseur;
	}

	@Override
	public String toString() {
		return String.format(
			"Article [id = %d, ref = %s, designation = %s, prix = %.2f, fournisseur = %s]",
			id,
			ref,
			designation,
			prix,
			fournisseur.getNom()
		);
	}

	public int getId() {
		return id;
	}

	public String getRef() {
		return ref;
	}

	public String getDesignation() {
		return designation;
	}

	public float getPrix() {
		return prix;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
}
