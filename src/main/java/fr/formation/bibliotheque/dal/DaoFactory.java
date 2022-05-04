package fr.formation.bibliotheque.dal;


public class DaoFactory {

	public static LivreDao getLivreDao() {
		return new LivreDAOJdbc();
		
	}
}
