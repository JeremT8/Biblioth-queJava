package fr.formation.bibliotheque.dal;

import java.util.List;

import fr.formation.bibliotheque.exception.LivreException;
import fr.formation.bibliotheque.model.Livre;



public interface LivreDao {

	/**
	 * findAll() recupere toutes les données
	 * @return
	 * @throws LivreException
	 */
	List<Livre> findAll() throws LivreException;

}
