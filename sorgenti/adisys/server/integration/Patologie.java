/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.integration;

import adisys.server.business.TOPatologia;
import adisys.server.utility.ADISysTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class Patologie.
 * Classe che compone i predicati per il database 
 * per la gestione delle patologie e crea gli oggetti 
 * per i livelli superiori
 * 
 */
public class Patologie {

	/** The Constant NOME_TABELLA. */
	private static final String NOME_TABELLA = "PATOLOGIE";

	/** The Constant NOME_COLONNA_NOME. */
	private static final String NOME_COLONNA_NOME = "NOME_PATOLOGIA";

	/** The Constant NOME_COLONNA_GRAVITA. */
	private static final String NOME_COLONNA_GRAVITA = "GRAVITA";

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
					.println("Patologie -> ERRORE NELLA RICERCA DELL'ID NELLA TABELLA "
							+ NOME_TABELLA);

			return false;
		}

	}

	/**
	 * Crea patologia.
	 * 
	 * @param patologia
	 *            the patologia
	 * @return true, if successful
	 */
	public static boolean creaPatologia(TOPatologia patologia) {
		String stat = "INSERT INTO " + NOME_TABELLA
				+ "(ID,NOME_PATOLOGIA,GRAVITA) VALUES(";
		stat += "'"
				+ Database.string2sqlstring(String.valueOf(patologia.getID()))
				+ "',";
		stat += "'" + Database.string2sqlstring(patologia.getNome()) + "',";
		stat += "'"
				+ Database.string2sqlstring(String.valueOf(patologia
						.getGravita())) + "');";
		return Database.esegui(stat.toUpperCase());
	}

	/**
	 * Modifica patologia.
	 * 
	 * @param patologia
	 *            the patologia
	 * @return true, if successful
	 */
	public static boolean modificaPatologia(TOPatologia patologia) {
		String stat = "UPDATE " + NOME_TABELLA + " SET ";
		stat += "ID='"
				+ Database.string2sqlstring(String.valueOf(patologia.getID()))
				+ "',";
		stat += "NOME_PATOLOGIA='"
				+ Database.string2sqlstring(patologia.getNome()) + "',";
		stat += "GRAVITA='"
				+ Database.string2sqlstring(String.valueOf(patologia
						.getGravita())) + "' ";
		stat += "WHERE ID = " + String.valueOf(patologia.getID()) + ";";
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
	public static boolean cancellaPatologia(int id) {
		String stat = "DELETE FROM " + NOME_TABELLA;
		stat += " WHERE ID = " + String.valueOf(id) + ";";

		return Database.esegui(stat);
	}

	/**
	 * Restituisce un ADISysTableModel con i dati sugli infermieri prelevati
	 * dalla tabella del db.
	 * 
	 * @return the tabella patologie
	 */
	public static ADISysTableModel getTabellaPatologie() {
		return Database.getTabella("SELECT * FROM " + NOME_TABELLA);
	}

	/**
	 * Restituisce un ADISysTableModel con i dati sugli infermieri prelevati
	 * dalla tabella del db.
	 * 
	 * @return the nomi patologie
	 */
	public static String[] getNomiPatologie() {
		// Preparazione ed esecuzione query
		String queryInt = "SELECT NOME_PATOLOGIA FROM " + NOME_TABELLA + ";";
		ResultSet tabellaPatologia = Database.getResultSet(queryInt);
		String[] listaNomiPatologie = new String[10];
		try {
			// Posizionamento del cursore sull'unica riga
			tabellaPatologia.first();

			int i = 0;
			do {
				// Popolamento di un oggetto di tipo Infermiere;
				listaNomiPatologie[i] = tabellaPatologia
						.getString("NOME_PATOLOGIA");
				i++;
			} while (tabellaPatologia.next());

			// String [] lista = {"a","b","c"};

			// Restituzione dell'oggetto
			return listaNomiPatologie;

		} catch (SQLException e) {
			// Notifica errore
			String msgErrore = "Patologie -> Errore: impossibile estrarre la lista dei nomi delle patologie dal DB";
			System.out.println(msgErrore);
			return null;
		}
	}

	/**
	 * Gets the patologia.
	 * 
	 * @param id
	 *            the id
	 * @return the patologia
	 */
	public static TOPatologia getPatologia(int id) {
		// Preparazione ed esecuzione query
		String queryInt = "SELECT * FROM " + NOME_TABELLA + " WHERE ID=" + id
				+ ";";
		ResultSet tabellaPatologia = Database.getResultSet(queryInt);
		try {
			// Posizionamento del cursore sull'unica riga
			tabellaPatologia.first();

			// Popolamento di un oggetto di tipo Infermiere;
			TOPatologia i = new TOPatologia();

			i.setID(id);
			i.setNome(tabellaPatologia.getString(NOME_COLONNA_NOME));
			i.setGravita(tabellaPatologia.getString(NOME_COLONNA_GRAVITA));

			// Restituzione dell'oggetto
			return i;
		} catch (SQLException e) {
			// Notifica errore
			String msgErrore = "Patologie -> Errore: impossibile estrarre la patologia dal DB";
			System.out.println(msgErrore);
			return null;
		}
	}

}
