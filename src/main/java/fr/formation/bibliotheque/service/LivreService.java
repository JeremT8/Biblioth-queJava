package fr.formation.bibliotheque.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import fr.formation.bibliotheque.dal.DaoFactory;
import fr.formation.bibliotheque.dal.LivreDao;
import fr.formation.bibliotheque.exception.LivreException;
import fr.formation.bibliotheque.model.Livre;

public class LivreService {

	private LivreDao lDao;
	
	public LivreService() {
		lDao = DaoFactory.getLivreDao();
	}
	
	public void ajouterLivre(Livre l) throws LivreException {
		lDao.add(l);
	}
	
	public List<Livre> recupererTousLesLivres() throws LivreException{
		return lDao.findAll();
	}
	
	public void supprimerLivre(int id) throws LivreException {
		lDao.delete(id);
	}
	
	public Optional<Livre> modifierLivre(int id, String titre, String auteur, String isbn, int nbPages, Date dateAchat ) throws LivreException {
		return lDao.update(id, titre, auteur, isbn, nbPages, dateAchat);
	}
	
	
	public List<Livre> chercherParAuteur(String auteur) throws LivreException {
		return lDao.findByAuteur(auteur);
	}
	
	public List<Livre> chercherParTitre(String titre) throws LivreException {
		return lDao.findByTitre(titre);
	}
	
	
}
