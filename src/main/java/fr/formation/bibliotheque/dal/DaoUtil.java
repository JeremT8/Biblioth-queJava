package fr.formation.bibliotheque.dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoUtil {

	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/bibliotheque";
	private static final String LOGIN = "root";
	private static final String PASSWD = "";

	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, LOGIN, PASSWD);
			} catch (Exception e) {
				System.out.println("Connection non effectué " + e.getMessage());
				conn = null;
			}
		}

		return conn;

	}

	public static void close() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println("Probleme de fermeture de connexion : " + e.getMessage());
		}
		conn = null;
	}
}
