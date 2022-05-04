package fr.formation.bibliotheque.model;


import java.time.LocalDate;


public class Livre {

	private int id;
	private String titre;
	private String auteur;
	private String isbn;
	private int nbPages;
	private LocalDate dateAchat;
	
	
	public Livre(String titre, String auteur, String isbn, int nbPages, LocalDate dateAchat) {
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = isbn;
		this.nbPages = nbPages;
		this.dateAchat = dateAchat;
	}
	
	
	public Livre(int id, String titre, String auteur, String isbn, int nbPages, LocalDate dateAchat) {
		this(titre, auteur, isbn, nbPages, dateAchat);
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getAuteur() {
		return auteur;
	}


	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public int getNbPages() {
		return nbPages;
	}


	public void setNbPages(int nbPage) {
		this.nbPages = nbPage;
	}


	public LocalDate getDateAchat() {
		return dateAchat;
	}


	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Titre du livre : ");
		builder.append("\n" + titre);
		builder.append("\nAuteur du livre :");
		builder.append("\n" + auteur);
		builder.append("\nNombre de pages du livres :");
		builder.append("\n" + nbPages + " page(s)");
		builder.append("\nDate d'achat :");
		builder.append("\n" + dateAchat);
		builder.append("\nISBN :");
		builder.append("\n" + isbn);
		builder.append("\n\n");
		return builder.toString();
	}


	
	
	
	
	
}
