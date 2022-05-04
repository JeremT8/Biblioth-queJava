package fr.formation.bibliotheque.dal;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import fr.formation.bibliotheque.exception.LivreException;
import fr.formation.bibliotheque.model.Livre;



public class LivreDAOJdbc implements LivreDao {

	private static final String SELECTALL = "SELECT id, titre, auteur, isbn, nbPages, dateAchat FROM livre";
	private static final String INSERT = "INSERT INTO livre (titre, auteur, isbn, nbPages, dateAchat) values(?, ?, ?, ?, ?)";
	private static final String DELETEONEBYID = "DELETE  FROM livre WHERE id = ?";
	private static final String UPDATE = "UPDATE livre SET titre = ?, auteur = ?, isbn = ?, nbPages = ?, dateAchat = ? WHERE id = ? ";	
	private static final String SELECTONE = "Select id, titre, auteur, isbn, nbPages, dateAchat FROM livre where id = ?";
	private static final String FINDBYAUTEUR = "SELECT id, titre, auteur, isbn, nbPages, dateAchat FROM livre WHERE auteur LIKE ?";
	
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
	
	/**
	 * findAll() recupere toutes les données
	 * @return
	 * @throws LivreException
	 */
	@Override
	public List<Livre> findByAuteur(String auteur) throws LivreException {
        List<Livre> maListe = new ArrayList<Livre>();
        try {
            PreparedStatement pstt = DaoUtil.getConnection().prepareStatement(FINDBYAUTEUR);
            pstt.setString(1, "%" + auteur + "%");
            ResultSet rs = pstt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                String auteur_livre = rs.getString("auteur");
                String isbn = rs.getString("isbn");
                int nbPages = rs.getInt("nbPages");
                Date dateAchat = rs.getDate("dateAchat");

                Livre l = new Livre(id, titre, auteur_livre, isbn, nbPages, dateAchat.toLocalDate());
                maListe.add(l);
            }
        } catch (SQLException e) {
            throw new LivreException("Probleme a l'appel du findAll : " + e.getMessage());
        }
        return maListe;
    }
	
	/**
	 * findAll() recupere toutes les données
	 * @return
	 * @throws LivreException
	 */
	@Override
	public List<Livre> findByTitre(String titre) throws LivreException {
        List<Livre> maListe = new ArrayList<Livre>();
        try {
            PreparedStatement pstt = DaoUtil.getConnection().prepareStatement(FINDBYAUTEUR);
            pstt.setString(1, "%" + titre + "%");
            ResultSet rs = pstt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titre_value = rs.getString("titre");
                String auteur = rs.getString("auteur");
                String isbn = rs.getString("isbn");
                int nbPages = rs.getInt("nbPages");
                Date dateAchat = rs.getDate("dateAchat");

                Livre l = new Livre(id, titre_value, auteur, isbn, nbPages, dateAchat.toLocalDate());
                maListe.add(l);
            }
        } catch (SQLException e) {
            throw new LivreException("Probleme a l'appel du findAll : " + e.getMessage());
        }
        return maListe;
    }
	
	
	/**
	 * findById() recupere les données selon un id
 	 * @param id
	 * @return
	 * @throws LivreException
	 */
	@Override
	public Optional<Livre> findById(int id) throws LivreException {
		Optional<Livre> optLivre = Optional.empty();
		try {
			PreparedStatement pstt = DaoUtil.getConnection().prepareStatement(SELECTONE);
			pstt.setInt(1, id);
			ResultSet rs = pstt.executeQuery();
			if(rs.next()) {
				String titre = rs.getString(2);
				String auteur = rs.getString(3);
				String isbn = rs.getString(4);
				int nbPages = rs.getInt(5);
				LocalDate dateAchat = rs.getDate(6).toLocalDate();
				
				Livre l = new Livre(id, titre, auteur, isbn, nbPages, dateAchat);
				optLivre = Optional.of(l);
				
			}
		} catch (SQLException e) {
			throw new LivreException("Probleme lors de l'appel de findById()" + e.getMessage());
		}
		
		return optLivre;
	}
	
	
	
	
	/**
	 * add() ajoute une Livre
	 * 
	 * @param p
	 * @throws LivreException
	 */
	@Override
	public Livre add(Livre l) throws LivreException {
		try {
			PreparedStatement pstt = DaoUtil.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			pstt.setString(1, l.getTitre());
			pstt.setString(2, l.getAuteur());
			pstt.setString(3, l.getIsbn());
			pstt.setInt(4, l.getNbPages());
			pstt.setObject(5, l.getDateAchat());
			
			pstt.executeUpdate();

			ResultSet rs = pstt.getGeneratedKeys();
			if (rs.next()) {
				int cle = rs.getInt(1);
				l.setId(cle);
			}
		} catch (SQLException e) {
			throw new LivreException("Probleme lors de l'appel de add()" + e.getMessage());
		}

		return l;
	}
	
	
	@Override
	public void delete(int id) throws LivreException {
		try {
			PreparedStatement stt = DaoUtil.getConnection().prepareStatement(DELETEONEBYID);
			stt.setInt(1, id);
			stt.executeQuery();
		} catch (SQLException e) {
			throw new LivreException("Probleme lors de l'appel de delete()" + e.getMessage());
		}
		
	}
	
	
	@Override
	public Optional<Livre> update(int id, String titre, String auteur, String isbn, int nbPages, Date dateAchat ) throws LivreException {
		
		try {
			PreparedStatement pstt = DaoUtil.getConnection().prepareStatement(UPDATE);
			Optional<Livre> l = this.findById(id);
			
			if (titre != null ) {
                pstt.setString(1, titre);
            } else {
                pstt.setString(1,l.get().getTitre());
            }
			
			if (auteur != null ) {
                pstt.setString(2, auteur);
            } else {
                pstt.setString(2,l.get().getAuteur());
            }
			
			if (isbn != null ) {
                pstt.setString(3, isbn);
            } else {
                pstt.setString(3,l.get().getIsbn());
            }
			
			if (nbPages == 0 ) {
                pstt.setInt(4, nbPages);
            } else {
                pstt.setInt(4,l.get().getNbPages());
            }
			
			if (dateAchat != null ) {
                pstt.setObject(5, dateAchat);
            } else {
                pstt.setObject(5,l.get().getDateAchat());
            }
			
			pstt.setInt(6,l.get().getId());
			pstt.executeUpdate();
			return l;
		} catch (SQLException e) {
			throw new LivreException("Probleme lors de l'appel de update()" + e.getMessage());
		}
		
		
		
	}


	
}
