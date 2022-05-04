package fr.formation.bibliotheque.service;

import java.util.List;

import fr.formation.bibliotheque.dal.DaoFactory;
import fr.formation.bibliotheque.dal.LivreDao;
import fr.formation.bibliotheque.exception.LivreException;
import fr.formation.bibliotheque.model.Livre;


public class LivreService {

	private LivreDao lDao;
	
	public LivreService() {
		lDao = DaoFactory.getLivreDao();
	}
	
	public List<Livre> recupererTousLesLivres() throws LivreException{
		return lDao.findAll();
	}
}
