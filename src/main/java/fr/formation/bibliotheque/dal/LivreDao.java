package fr.formation.bibliotheque.dal;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import fr.formation.bibliotheque.exception.LivreException;
import fr.formation.bibliotheque.model.Livre;



public interface LivreDao {

	/**
	 * findAll() recupere toutes les données
	 * @return
	 * @throws LivreException
	 */
	List<Livre> findAll() throws LivreException;

	/**
	 * add() ajoute une Livre
	 * 
	 * @param p
	 * @throws LivreException
	 */
	Livre add(Livre l) throws LivreException;

	
	/**
	 * delete() supprime un livre
	 * @param l
	 * @return
	 * @throws LivreException
	 */
	void delete(int id) throws LivreException;
	
	
	/**
	 * update() modifie un livre
	 * @param l
	 * @return
	 * @throws LivreException
	 */
	Optional<Livre> update(int id, String titre, String auteur, String isbn, int nbPages, Date dateAchat ) throws LivreException;

	/**
	 * findById() recupere les données selon un id
	 * @param id
	 * @return
	 * @throws LivreException
	 */
	Optional<Livre> findById(int id) throws LivreException;

	/**
	 * findAll() recupere toutes les données
	 * @return
	 * @throws LivreException
	 */
	List<Livre> findByAuteur(String auteur) throws LivreException;

	/**
	 * findAll() recupere toutes les données
	 * @return
	 * @throws LivreException
	 */
	List<Livre> findByTitre(String titre) throws LivreException;

	

	
}
