/*
 * 
 */
package adisys.server.integration;

import java.sql.ResultSet;
import java.sql.SQLException;

import adisys.server.business.TOInfermiere;
import adisys.server.utility.ADISysTableModel;

/**
 * The Class Infermieri.
 * Classe che compone i predicati per il database 
 * per la gestione degli infermieri e crea gli oggetti 
 * per i livelli superiori
 * 
 */
public class Infermieri {

	/** The Constant NOME_TABELLA. */
	private static final String NOME_TABELLA = "INFERMIERI";

	/** The Constant NOME_COLONNA_NOME. */
	private static final String NOME_COLONNA_NOME = "NOME";

	/** The Constant NOME_COLONNA_COGNOME. */
	private static final String NOME_COLONNA_COGNOME = "COGNOME";

	/**
	 * Id esistente.
	 * 
	 * @param ID
	 *            the id
	 * @return true, if successful
	 */
	public static boolean idEsistente(int ID) {
		// Preparazione query
		String stat = "SELECT COUNT(ID) FROM " + NOME_TABELLA + " WHERE ID="
				+ ID;

		// Esecuzione query
		ResultSet tabConto = Database.getResultSet(stat);

		try {
			// Estrazione valore
			tabConto.first();
			if (tabConto.getInt(1) == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.out.println("ERRORE RICERCA ID!!!");

			// TRACE
			System.out
					.println("Infermieri -> ERRORE NELLA RICERCA DELL'ID NELLA TABELLA "
							+ NOME_TABELLA);

			return false;
		}

	}

	/**
	 * Crea infermiere.
	 * 
	 * @param infermiere
	 *            the infermiere
	 * @return true, if successful
	 */
	public static boolean creaInfermiere(TOInfermiere infermiere) {
		String stat = "INSERT INTO " + NOME_TABELLA + "(NOME,COGNOME) VALUES(";
		stat += "'" + Database.string2sqlstring(infermiere.getNome()) + "',";
		stat += "'" + Database.string2sqlstring(infermiere.getCognome())
				+ "');";
		return Database.esegui(stat.toUpperCase());
	}

	/**
	 * Modifica infermiere.
	 * 
	 * @param infermiere
	 *            the infermiere
	 * @return true, if successful
	 */
	public static boolean modificaInfermiere(TOInfermiere infermiere) {
		String stat = "UPDATE " + NOME_TABELLA + " SET ";
		stat += "NOME='" + Database.string2sqlstring(infermiere.getNome())
				+ "',";
		stat += "COGNOME='"
				+ Database.string2sqlstring(infermiere.getCognome()) + "' ";
		stat += "WHERE ID = " + infermiere.getID() + ";";
		return Database.esegui(stat.toUpperCase());
	}

	/**
	 * Cancella l'infermiere e tutti i dati relativi (interventi, attivita'
	 * ecc..)
	 * 
	 * @param id
	 *            the id
	 * @return true se la cancellazione va a buon fine
	 */
	public static boolean cancellaInfermiere(int id) {
		String stat = "DELETE FROM " + NOME_TABELLA;
		stat += " WHERE ID = " + String.valueOf(id) + ";";
		return Database.esegui(stat);
	}

	/**
	 * Cancella tutti.
	 * 
	 * @return true, if successful
	 */
	public static boolean cancellaTutti() {
		// Svuota la tabella
		boolean success = Database.esegui("DELETE FROM " + NOME_TABELLA);
		// Reimposta la chiave ID a 0
		Database.esegui("ALTER TABLE INFERMIERI ALTER COLUMN ID RESTART WITH 0");
		return success;
	}

	/**
	 * Restituisce un ADISysTableModel con i dati sugli infermieri prelevati
	 * dalla tabella del db e il numero di interventi per ogni infermiere.
	 * 
	 * @return the tabella info infermieri con interventi
	 */
	public static ADISysTableModel getTabellaInfoInfermieriConInterventi() {
		return Database.getTabella("SELECT * FROM INTERV_INFERM");
	}

	/**
	 * Restituisce un ADISysTableModel con i dati sugli infermieri prelevati
	 * dalla tabella del db.
	 * 
	 * @return the tabella infermieri
	 */
	public static ADISysTableModel getTabellaInfermieri() {
		return Database.getTabella("SELECT * FROM " + NOME_TABELLA);
	}

	/**
	 * Gets the infermiere.
	 * 
	 * @param id
	 *            the id
	 * @return the infermiere
	 */
	public static TOInfermiere getInfermiere(int id) {
		// Preparazione ed esecuzione query
		String queryInt = "SELECT * FROM " + NOME_TABELLA + " WHERE ID=" + id
				+ ";";
		ResultSet tabellaInfermiere = Database.getResultSet(queryInt);
		try {
			// Posizionamento del cursore sull'unica riga
			tabellaInfermiere.first();

			// Popolamento di un oggetto di tipo Infermiere;
			TOInfermiere i = new TOInfermiere();

			i.setID(id);
			i.setNome(tabellaInfermiere.getString(NOME_COLONNA_NOME));
			i.setCognome(tabellaInfermiere.getString(NOME_COLONNA_COGNOME));

			// Restituzione dell'oggetto
			return i;
		} catch (SQLException e) {
			// Notifica errore
			String msgErrore = "Infermieri -> Errore: impossibile estrarre l'infermiere dal DB";
			System.out.println(msgErrore);
			return null;
		}
	}
}
