package fr.formation.bibliotheque.dal;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import fr.formation.bibliotheque.exception.LivreException;
import fr.formation.bibliotheque.model.Livre;



public class LivreDAOJdbc implements LivreDao {

	private static final String SELECTALL = "SELECT id, titre, auteur, isbn, nbPages, dateAchat FROM livre";
	
	/**
	 * findAll() recupere toutes les données
	 * @return
	 * @throws LivreException
	 */
	@Override
	public List<Livre> findAll() throws LivreException {
		List<Livre> maListe = new ArrayList<Livre>();
		try {
			Statement stt = DaoUtil.getConnection().createStatement();
			ResultSet rs = stt.executeQuery(SELECTALL);
			while (rs.next()) {
				int id = rs.getInt("id");
				String titre = rs.getString(2);
				String auteur = rs.getString(3);
				String isbn = rs.getString(4);
				int nbPages = rs.getInt(5);
				LocalDate dateAchat = rs.getDate(6).toLocalDate();
				
				
				Livre l = new Livre(id, titre, auteur, isbn, nbPages, dateAchat);
				maListe.add(l);
			}
		} catch (SQLException e) {
			throw new LivreException("Probleme lors de l'appel a findAll()" + e.getMessage());
		}
		return maListe;
	}
}
