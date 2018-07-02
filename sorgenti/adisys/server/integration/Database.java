/*
 * 
 */
package adisys.server.integration;

import adisys.server.utility.ADISysTableModel;
import java.sql.*;

/**
 * The Class Database.
 * Classe che effettua la connessione al database, 
 * effettua le query sullo stesso e restituisce 
 * i dati ottenuti
 * 
 */
public class Database {

	/** The Constant dbPath. */
	private static final String dbPath = "jdbc:hsqldb:file:database/ADISysData";

	/** The Constant dbUser. */
	private static final String dbUser = "asl";

	/** The Constant dbPass. */
	private static final String dbPass = "";

	/** The connessione. */
	private static Connection connessione;

	// Inizializzatore
	/**
	 * Connetti.
	 */
	public static void connetti() {
		try {
			System.out.println("Caricamento Database. Attendere...");
			// CONNESSIONE AL DATABASE
			connessione = DriverManager.getConnection(dbPath, dbUser, dbPass);

			// SCRITTURA TABELLE SU CONSOLE
			System.out.println("CONNESSIONE OK su " + dbPath);

		} catch (SQLException e) {
			System.out.println("CONNESSIONE FALLITA:\n" + e);
			System.exit(0);
		}

	}

	/**
	 * Esegui.
	 * 
	 * @param SQLString
	 *            the SQL string
	 * @return true, if successful
	 */
	public static boolean esegui(String SQLString) {
		// Crea un oggetto per le operazioni sul database
		try {
			// Connessione
			Statement st = connessione.createStatement();
			System.out.println("Database -> Esecuzione predicato SQL: "
					+ SQLString);
			st.executeUpdate(SQLString);
			return true;
		} catch (SQLException e) {
			System.out.println("Errore: Impossibile aggiornare il database.");
			return false;
		}
	}

	/**
	 * Esegue una query sul database e restituisce il relativo tableModel da
	 * utilizzare in una JTable.
	 * 
	 * @param queryText
	 *            the query text
	 * @return ADISysTableModel
	 */
	public static ADISysTableModel getTabella(String queryText) {

		Statement enunciato;
		try {

			// Crea uno statement per l'interrogazione del database
			enunciato = connessione.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			// Trace
			System.out.println("Database -> Interrogazione SQL: " + queryText);

			// Crea un ResultSet eseguendo l'interrogazione desiderata
			ResultSet tabellina = enunciato.executeQuery(queryText);

			return new ADISysTableModel(tabellina);

		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * Esegue una query sul database e restituisce il relativo ResultSet.
	 * 
	 * @param queryText
	 *            the query text
	 * @return ADISysTableModel
	 */
	public static ResultSet getResultSet(String queryText) {

		Statement enunciato;
		try {

			// Crea uno statement per l'interrogazione del database
			enunciato = connessione.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			// Trace
			System.out.println("Database -> Interrogazione SQL: " + queryText);

			// Crea un ResultSet eseguendo l'interrogazione desiderata
			ResultSet tabellina = enunciato.executeQuery(queryText);

			return tabellina;

		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * Metodo da applicare ai valori di tipo stringa prima dell'inserimento nel
	 * database.
	 * 
	 * @param stringaInput
	 *            the stringa input
	 * @return the string
	 */
	public static String string2sqlstring(String stringaInput) {
		String stringaOutput;

		// Rimedio bug apostrofo
		stringaOutput = stringaInput.replaceAll("'", "''");

		return stringaOutput;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable {
		try {
			disconnetti();
		} finally {
			super.finalize();
		}
	}

	/**
	 * Disconnetti.
	 */
	public void disconnetti() {
		try {
			System.out.println("Database -> Disconnessione.");
			connessione.close();
		} catch (SQLException e) {
			System.out.println("Impossibile terminare la connessione");
		}

	}

}
