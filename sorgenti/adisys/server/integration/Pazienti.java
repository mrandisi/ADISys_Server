/*
 * 
 */
package adisys.server.integration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;

import javax.swing.JOptionPane;

import adisys.server.business.TOPaziente;
import adisys.server.utility.ADISysTableModel;

/**
 * The Class Pazienti.
 * Classe che compone i predicati per il database 
 * per la gestione dei pazienti e crea gli oggetti 
 * per i livelli superiori
 * 
 */
public class Pazienti {

	/** The Constant NOME_TABELLA. */
	private static final String NOME_TABELLA = "PAZIENTI";

	/** The Constant NOME_COLONNA_ID. */
	private static final String NOME_COLONNA_ID = "ID";

	/** The Constant NOME_COLONNA_NOME. */
	private static final String NOME_COLONNA_NOME = "NOME";

	/** The Constant NOME_COLONNA_COGNOME. */
	private static final String NOME_COLONNA_COGNOME = "COGNOME";

	/** The Constant NOME_COLONNA_DATA_NASCITA. */
	private static final String NOME_COLONNA_DATA_NASCITA = "DATANASCITA";
	// Modifica vers 2 -> Rimossa costante NOME_COLONNA_CELLULARE
	/** The Constant FORMATO_DATA_NASCITA. */
	private static final String FORMATO_DATA_NASCITA = "yyyy-MM-dd";

	// AGGIUNTE VERS 2
	/** The Constant NOME_TABELLA_CELLULARI. */
	private static final String NOME_TABELLA_CELLULARI = "CELLULARIPAZIENTI";

	/** The Constant NOME_COLONNA_PAZIENTE_CELLULARI. */
	private static final String NOME_COLONNA_PAZIENTE_CELLULARI = "PAZIENTE";

	/** The Constant NOME_COLONNA_NUMERO_CELLULARI. */
	private static final String NOME_COLONNA_NUMERO_CELLULARI = "NUMERO";

	/**
	 * Id esistente.
	 * 
	 * @param ID
	 *            the id
	 * @return true, if successful
	 */
	public static boolean idEsistente(int ID) {
		// Preparazione query
		String stat = "SELECT COUNT(" + NOME_COLONNA_ID + ") FROM "
				+ NOME_TABELLA + " WHERE " + NOME_COLONNA_ID + " =" + ID;
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
			return false;
		}

	}

	/**
	 * Riceve un oggetto paziente ed inserisce il record nel database Versione 2
	 * -> gestisce separatamente tabella paziente e tabellla cellulari.
	 * 
	 * @param p
	 *            the p
	 * @return true, if successful
	 */
	public static boolean creaPaziente(TOPaziente p) {
		@SuppressWarnings("resource")
		Formatter formattatore = new Formatter();

		// Inserimento dei dati del paziente
		String formatoIstruzione = "INSERT INTO %s(%s) VALUES(%s);";
		String formatoColonne = "%s,%s,%s";
		String formatoValori = "'%s','%s','%s'";

		// Preparazione elenco colonne
		String elencoColonne = formattatore.format(formatoColonne,
				NOME_COLONNA_NOME, NOME_COLONNA_COGNOME,
				NOME_COLONNA_DATA_NASCITA).toString();
		formattatore = new Formatter();

		// Preparazione elenco valori
		String elencoValori = formattatore.format(formatoValori,
				Database.string2sqlstring(p.getNome()),
				Database.string2sqlstring(p.getCognome()),
				p.getDataNascita(FORMATO_DATA_NASCITA)).toString();

		formattatore = new Formatter();

		// Preparazione query
		String istruzione = formattatore.format(formatoIstruzione,
				NOME_TABELLA, elencoColonne, elencoValori).toString();

		// Inserimento elemento
		// Esecuzione query
		System.out.println("Paziente -> Istruzione SQL: "
				+ istruzione.toUpperCase());
		if (Database.esegui(istruzione.toUpperCase())) {
			// Inserimento paziente effettuato con successo
			// Recupero ID assegnato -> ATTENZIONE AGLI OMONIMI!!!
			try {
				p.setID(recuperaID(p));

				// Inserimento dei numeri di cellulare
				Object[] cellulari = p.getCellulari();
				for (Object cellulare : cellulari) {
					// Preparazione stringa
					String stringaUpdate = "INSERT INTO "
							+ NOME_TABELLA_CELLULARI + "("
							+ NOME_COLONNA_PAZIENTE_CELLULARI + ", "
							+ NOME_COLONNA_NUMERO_CELLULARI + ") ";
					stringaUpdate += "VALUES(" + String.valueOf(p.getID())
							+ ",'" + String.valueOf(cellulare) + "');";

					// Inserimento dei numeri di cellulare (messaggio in caso di
					// fallimento)
					if (!Database.esegui(stringaUpdate.toUpperCase()))
						JOptionPane.showMessageDialog(
								null,
								java.util.ResourceBundle.getBundle(
										"adisys/server/lang/MessagesBundle")
										.getString("MsgDialog480")
										+ String.valueOf(cellulare));
				}
			} catch (Exception e) {
				// Inserimento dei numeri di cellulare fallito
				JOptionPane.showMessageDialog(null, java.util.ResourceBundle
						.getBundle("adisys/server/lang/MessagesBundle")
						.getString("MsgDialog490"));
				return false;
			}
			return true;
		} else {
			// Inserimento paziente effettuato con successo
			JOptionPane.showMessageDialog(
					null,
					java.util.ResourceBundle.getBundle(
							"adisys/server/lang/MessagesBundle").getString(
							"MsgDialog490"));
			return false;
		}

	}

	/**
	 * Modifica paziente.
	 * 
	 * @param p
	 *            the p
	 * @return true, if successful
	 */
	public static boolean modificaPaziente(TOPaziente p) {
		// Inserimento dei dati nel caso nome e cognome non siano vuoti
		String formatoIstruzione = "UPDATE %s SET %s WHERE %s ;";
		String formatoAssegnazioni = "%s = '%s', %s = '%s', %s = '%s'";

		String condizione = NOME_COLONNA_ID + "=" + p.getID();

		@SuppressWarnings("resource")
		Formatter formattatore = new Formatter();
		String assegnazioni = formattatore.format(formatoAssegnazioni,
				NOME_COLONNA_NOME, Database.string2sqlstring(p.getNome()),
				NOME_COLONNA_COGNOME,
				Database.string2sqlstring(p.getCognome()),
				NOME_COLONNA_DATA_NASCITA,
				p.getDataNascita(FORMATO_DATA_NASCITA)).toString();

		formattatore = new Formatter();
		String istruzione = formattatore.format(formatoIstruzione,
				NOME_TABELLA, assegnazioni, condizione).toString();

		// Aggiunta istruzioni x inserimento cellulari
		// Cancellazione numeri di cellulare esistenti
		// Praparazone query
		String queryCancellazioneCellulari = "DELETE FROM "
				+ NOME_TABELLA_CELLULARI + " WHERE "
				+ NOME_COLONNA_PAZIENTE_CELLULARI + "="
				+ String.valueOf(p.getID() + ";");
		// Esecuzione
		Database.esegui(queryCancellazioneCellulari);

		// TODO Verificarenserimento dei numeri di cellulare
		Object[] cellulari = p.getCellulari();
		for (Object cellulare : cellulari) {
			// Preparazione stringa
			String stringaUpdate = "\nINSERT INTO " + NOME_TABELLA_CELLULARI
					+ "(" + NOME_COLONNA_PAZIENTE_CELLULARI + ", "
					+ NOME_COLONNA_NUMERO_CELLULARI + ") ";
			stringaUpdate += "VALUES(" + String.valueOf(p.getID()) + ",'"
					+ String.valueOf(cellulare) + "');";

			// Aggiunta alla stringa dell'istruzione
			istruzione += stringaUpdate;

		}
		System.out.println("Paziente -> Istruzione SQL: "
				+ istruzione.toUpperCase());
		return Database.esegui(istruzione.toUpperCase());
	}

	/**
	 * Cancella paziente.
	 * 
	 * @param selectedID
	 *            the selected id
	 * @return true, if successful
	 */
	public static boolean cancellaPaziente(int selectedID) {
		boolean success = Database.esegui("DELETE FROM " + NOME_TABELLA
				+ " WHERE " + NOME_COLONNA_ID + "="
				+ String.valueOf(selectedID));
		return success;
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
		Database.esegui("ALTER TABLE " + NOME_TABELLA + " ALTER COLUMN "
				+ NOME_COLONNA_ID + " RESTART WITH 0");

		return success;
	}

	/**
	 * Gets the tabella pazienti.
	 * 
	 * @return Restituisce un <b>TableModel</b> contenente i dati dei pazienti
	 *         (va aggiornato in caso di modifiche)
	 */
	public static ADISysTableModel getTabellaPazienti() {
		return Database.getTabella("SELECT * FROM " + NOME_TABELLA);
	}

	/**
	 * Gets the paziente.
	 * 
	 * @param id
	 *            the id
	 * @return the paziente
	 */
	public static TOPaziente getPaziente(int id) {
		// Recupero informazioni dal database
		String queryText = "SELECT * FROM " + NOME_TABELLA + " WHERE "
				+ NOME_COLONNA_ID + " = " + id + ";";
		ResultSet tabellaPaziente = Database.getResultSet(queryText);

		// Copia delle informazioni in un oggetto di tipo Paziente
		try {
			// Posizionamento del cursore sull'unica riga
			tabellaPaziente.first();

			// Creazione oggetto
			TOPaziente p = new TOPaziente();

			// Popolamento
			p.setID(id);
			p.setNome(tabellaPaziente.getString(NOME_COLONNA_NOME));
			p.setCognome(tabellaPaziente.getString(NOME_COLONNA_COGNOME));
			p.setDataNascita(
					tabellaPaziente.getString(NOME_COLONNA_DATA_NASCITA),
					FORMATO_DATA_NASCITA);

			// Aggiunta dei numeri di cellulare
			// TODO verificare cosa succede in caso di nessun numero di
			// cellulare..
			String queryCellulari = "SELECT * FROM " + NOME_TABELLA_CELLULARI
					+ " WHERE " + NOME_COLONNA_PAZIENTE_CELLULARI + "="
					+ String.valueOf(id);
			ResultSet rs = Database.getResultSet(queryCellulari);
			if (rs.first())
				for (; !(rs.isAfterLast()); rs.next())
					p.addCellulare(rs.getString(NOME_COLONNA_NUMERO_CELLULARI));

			// Restituzione oggetto
			return p;

		} catch (SQLException e) {
			String msgErrore = "Pazienti -> Errore metodo getPaziente()";
			System.out.println(msgErrore);
			return null;
		}

	}

	/**
	 * Recupera id.
	 * 
	 * @param p
	 *            the p
	 * @return the integer
	 */
	private static Integer recuperaID(TOPaziente p) {
		// Recupero informazioni dal database
		String queryText = "SELECT " + NOME_COLONNA_ID + " FROM "
				+ NOME_TABELLA;
		queryText += " WHERE ";
		queryText += NOME_COLONNA_NOME + " = '" + p.getNome() + "' ";
		queryText += "and " + NOME_COLONNA_COGNOME + " = '" + p.getCognome()
				+ "' ";
		queryText += "and " + NOME_COLONNA_DATA_NASCITA + " = '"
				+ p.getDataNascita(FORMATO_DATA_NASCITA) + "' ";
		queryText += ";";
		ResultSet tabellaPaziente = Database.getResultSet(queryText
				.toUpperCase());

		// Copia delle informazioni in un oggetto di tipo Paziente
		try {
			// Posizionamento del cursore sull'unica riga
			tabellaPaziente.first();
			// Restituzione oggetto
			return tabellaPaziente.getInt(NOME_COLONNA_ID);

		} catch (SQLException e) {
			String msgErrore = "Pazienti -> Errore metodo recuperaID()";
			System.out.println(msgErrore);
			return null;
		}

	}
}
