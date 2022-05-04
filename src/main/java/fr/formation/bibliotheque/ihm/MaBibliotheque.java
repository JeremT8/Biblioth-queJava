package fr.formation.bibliotheque.ihm;

import java.util.List;

import fr.formation.bibliotheque.dal.DaoUtil;
import fr.formation.bibliotheque.exception.LivreException;
import fr.formation.bibliotheque.model.Livre;
import fr.formation.bibliotheque.service.LivreService;



public class MaBibliotheque {

	public static void main(String[] args) {
		
		LivreService ls = new LivreService();
		
		afficherTousLesLivres(ls);
		
		DaoUtil.close();
	}
	
	private static void afficherTousLesLivres(LivreService ls) {
		System.out.println("Liste des livres : " );
		try {
			List<Livre> listeS = ls.recupererTousLesLivres();
			for (Livre livre : listeS) {
				System.out.println("   -  " + livre);
			}
		} catch (LivreException e) {
			System.out.println(e.getMessage());;
		}
	}
	
	

}
