package fr.formation.bibliotheque.ihm;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import fr.formation.bibliotheque.dal.DaoUtil;
import fr.formation.bibliotheque.exception.LivreException;
import fr.formation.bibliotheque.model.Livre;
import fr.formation.bibliotheque.service.LivreService;

public class MaBibliotheque {

	public static void main(String[] args) {

		LivreService ls = new LivreService();

		Scanner sc = new Scanner(System.in);
		System.out.println("MENU PRINCIPAL");
		System.out.println("----------------");
		System.out.println("\n");

		System.out.println("0 : Quitter");
		System.out.println("1 : Ajouter un livre");
		System.out.println("2 : Afficher la liste des livres");
		System.out.println("3 : Trouver un livre par son auteur");
		System.out.println("4 : Trouver un livre par son titre");
		System.out.println("5 : Modifier un livre");
		System.out.println("6 : Supprimer un livre");
		System.out.println("Votre choix : ");

		int value = sc.nextInt();

		if (value == 0) {
			System.out.println("L'application a bien été fermée !");
			sc.close();
		}
		if (value == 1) {
			sc.nextLine();
			System.out.println("Titre du livre : ");
			String titre = sc.nextLine();
			System.out.println("Auteur du livre : ");
			String auteur = sc.nextLine();
			System.out.println("Nombre de page : ");
			int nbpages = sc.nextInt();
			sc.nextLine()
;			System.out.println("Date d'achat :  ");
			String date_entry = sc.nextLine();
			System.out.println("ISBN : ");
			String isbn = sc.nextLine();

			Date dateachat = Date.valueOf(date_entry);

			Livre livre = new Livre(titre, auteur, isbn, nbpages, dateachat.toLocalDate());
			

			try {
				ls.ajouterLivre(livre);
				System.out.println("Le livre a bien était enregistré");
			} catch (LivreException e) {
				System.out.println(e.getMessage());
			}
		}
		if (value == 2) {
			afficherTousLesLivres(ls);
		}

		if (value == 3) {
			System.out.println("Entrer le nom de votre auteur ou le début de son nom :");
            String auteur = sc.nextLine();
            try {
				ls.chercherParAuteur(auteur);
			} catch (LivreException e) {
				System.out.println(e.getMessage());
			}
		}

		if (value == 4) {
			System.out.println("Entrer le nom de votre auteur ou le début de son nom :");
            String titre = sc.nextLine();
            try {
            	ls.chercherParTitre(titre);
			} catch (LivreException e) {
				System.out.println(e.getMessage());
			}
		}

		if (value == 5) {
			System.out.println("Quel identifiant de livre souhaitez vous modifier ?");
			int id = sc.nextInt();
			
			sc.nextLine();
			System.out.println("Titre du livre : ");
			String titre = sc.nextLine();
			System.out.println("Auteur du livre : ");
			String auteur = sc.nextLine();
			System.out.println("Nombre de page : ");
			int nbpages = sc.nextInt();
			sc.nextLine()
;			System.out.println("Date d'achat :  ");
			String date_entry = sc.nextLine();
			System.out.println("ISBN : ");
			String isbn = sc.nextLine();

			Date dateachat = Date.valueOf(date_entry);

				

			try {
				ls.modifierLivre(id, titre, auteur, isbn, nbpages, dateachat);
				System.out.println("Le livre a bien était enregistrer");
				afficherTousLesLivres(ls);
			} catch (LivreException e) {
				System.out.println(e.getMessage());
			}
		}
		if (value == 6) {
			
			System.out.println("Quel identifiant de livre souhaitez vous supprimer ?");
			int id = sc.nextInt();
			try {
				ls.supprimerLivre(id);
				System.out.println("Le livre avec l'id : " + id + " a étais supprimer");
			} catch (LivreException e) {
				System.out.println(e.getMessage());
			}
		}

		sc.close();
		DaoUtil.close();
	}

	private static void afficherTousLesLivres(LivreService ls) {
		System.out.println("\n");
		try {
			List<Livre> listeS = ls.recupererTousLesLivres();
			for (Livre livre : listeS) {
				System.out.println(livre);
			}
		} catch (LivreException e) {
			System.out.println(e.getMessage());
			;
		}
	}

}
