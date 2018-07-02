/*
 * 
 */
package adisys.server.integration;

import adisys.server.business.TOIntervento;
import adisys.server.business.TOTipoIntervento;
import adisys.server.utility.ADISysTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class Interventi.
 * Classe che compone i predicati per il database 
 * per la gestione degli interventi e crea gli oggetti 
 * per i livelli superiori
 * 
 */
public class Interventi {
	// COSTANTI PER AGGIORNAMENTI E INTERROGAZIONE
	/** The Constant NOME_TABELLA_INTERVENTI. */
	private static final String NOME_TABELLA_INTERVENTI = "INTERVENTI";

	/** The Constant NOME_COLONNA_ID. */
	private static final String NOME_COLONNA_ID = "ID";

	/** The Constant NOME_COLONNA_ID_PAZIENTE. */
	private static final String NOME_COLONNA_ID_PAZIENTE = "IDPAZIENTE";

	/** The Constant NOME_COLONNA_ID_INFERMIERE. */
	private static final String NOME_COLONNA_ID_INFERMIERE = "IDINFERMIERE";

	/** The Constant NOME_COLONNA_CITTA. */
	private static final String NOME_COLONNA_CITTA = "CITTA";

	/** The Constant NOME_COLONNA_CIVICO. */
	private static final String NOME_COLONNA_CIVICO = "CIVICO";

	/** The Constant NOME_COLONNA_CAP. */
	private static final String NOME_COLONNA_CAP = "CAP";

	/** The Constant NOME_COLONNA_DATA. */
	private static final String NOME_COLONNA_DATA = "DATAINT";

	/** The Constant NOME_COLONNA_ORA_INIZIO. */
	private static final String NOME_COLONNA_ORA_INIZIO = "ORAINIZIO";

	/** The Constant NOME_TABELLA_TIPI_INTERVENTI. */
	private static final String NOME_TABELLA_TIPI_INTERVENTI = "TIPIINTERVENTI";

	/** The Constant NOME_COLONNA_IDINT_TIPO. */
	private static final String NOME_COLONNA_IDINT_TIPO = "IDINTERVENTO";

	/** The Constant NOME_COLONNA_PATOLOGIA. */
	private static final String NOME_COLONNA_PATOLOGIA = "PATOLOGIA";

	/** The Constant NOME_COLONNA_NOME_TIPO. */
	private static final String NOME_COLONNA_NOME_TIPO = "NOME";

	/** The Constant NOME_COLONNA_VALORE_RILEVATO. */
	private static final String NOME_COLONNA_VALORE_RILEVATO = "VALORERILEVATO";

	/** The Constant NOME_COLONNA_TEMPO_INTERVENTO. */
	private static final String NOME_COLONNA_TEMPO_INTERVENTO = "TEMPOINTERVENTO";

	/** The Constant NOME_COLONNA_NOTE_TIPO. */
	private static final String NOME_COLONNA_NOTE_TIPO = "NOTE";

	// COSTANTI DI FORMATO
	/** The Constant FORMATO_DATA_SQL. */
	private static final String FORMATO_DATA_SQL = "yyyy-MM-dd";

	/** The Constant FORMATO_ORA_SQL. */
	private static final String FORMATO_ORA_SQL = "HH:mm:ss";

	/**
	 * Verifica l'esistenza dell'id.
	 * 
	 * @param ID
	 *            the id
	 * @return true se l'ID è presente nel database.
	 */
	public static boolean idEsistente(int ID) {
		// Preparazione query
		String stat = "SELECT COUNT(ID) FROM " + NOME_TABELLA_INTERVENTI
				+ " WHERE ID=" + ID + ";";
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
	 * Crea intervento.
	 * 
	 * @param nuovoIntervento
	 *            the nuovo intervento
	 * @return true, if successful
	 */
	public static boolean creaIntervento(TOIntervento nuovoIntervento) {

		// Preparazione dei formati SQL
		@SuppressWarnings("resource")
		Formatter f = new Formatter();
		@SuppressWarnings("resource")
		Formatter f2 = new Formatter();
		String formatoElencoColonne = "%s,%s,%s,%s,%s,%s,%s";
		String formatoElencoValori = "%s,%s,'%s','%s','%s','%s','%s'";

		String elencoColonne = f.format(formatoElencoColonne,
				NOME_COLONNA_ID_INFERMIERE, NOME_COLONNA_ID_PAZIENTE,

				NOME_COLONNA_CITTA, NOME_COLONNA_CIVICO, NOME_COLONNA_CAP,

				NOME_COLONNA_DATA, NOME_COLONNA_ORA_INIZIO).toString();

		String elencoValori = f2.format(formatoElencoValori,
				String.valueOf(nuovoIntervento.getIDInfermiere()),
				String.valueOf(nuovoIntervento.getIDPaziente()),

				Database.string2sqlstring(nuovoIntervento.getCitta()),
				Database.string2sqlstring(nuovoIntervento.getCivico()),
				Database.string2sqlstring(nuovoIntervento.getCap()),

				nuovoIntervento.getDataDaFormato(FORMATO_DATA_SQL),
				nuovoIntervento.getOraInizioDaFormato(FORMATO_ORA_SQL)

		).toString();

		// Composizione istruzione
		String istruzione = "INSERT INTO " + NOME_TABELLA_INTERVENTI + " ("
				+ elencoColonne + ") VALUES (" + elencoValori + ");";

		// Trace
		System.out.println("SQL: " + istruzione.toUpperCase());

		// Esecuzione
		if (Database.esegui(istruzione.toUpperCase())) {
			// Caso inserimento dati di base eseguito con successo ->
			// Inserimento dei tipi di intervento

			// Trace
			System.out
					.println("Interventi -> Intervento creato con successo, inserimento tipi in corso...");

			// TODO Inserire valori tipi intervento nel caso di inserimento
			// andato a buon fine.
			// Recupero id dell'intervento:
			ResultSet idAssegnato = Database
					.getResultSet("SELECT MAX(ID) FROM INTERVENTI");

			int newID; // ID del nuovo intervento

			try {
				// Posizionamento nel resultset generato
				idAssegnato.first();
				// Recupero dell'id
				newID = idAssegnato.getInt(1);

			} catch (SQLException e) {
				// Trace
				System.out
						.println("Interventi -> Inserimento dei tipi di intervento fallito.");

				return false;

			}

			// TODO Inserimento dei tipi di intervento:
			// Contatore dei tipi di intervento inseriti
			int numeroTipiInseriti = 0;

			// Ciclo di update
			for (int i = 0; i < nuovoIntervento.countTipInterventi(); i++) {
				// Esecuzione predicato con incremento indice in caso di
				// successo.
				if (inserisciTipoIntervento(newID, nuovoIntervento
						.getTipoIntervento(i).getPatologia(), nuovoIntervento
						.getTipoIntervento(i).getNome(), nuovoIntervento
						.getTipoIntervento(i).getNote())) {
					// Trace
					System.out.println("Interventi -> Tipo intervento <<"
							+ nuovoIntervento.getTipoIntervento(i).getNome()
							+ ">> inserito con successo");
					numeroTipiInseriti++;

				} else {
					// Trace
					System.out.println("Interventi -> Tipo intervento <<"
							+ nuovoIntervento.getTipoIntervento(i).getNome()
							+ ">> non inserito. Contattare l'assistenza");
				}
			}

			// Trace
			System.out.println("Interventi -> Inseriti " + numeroTipiInseriti
					+ " tipi di intervento su "
					+ nuovoIntervento.countTipInterventi() + ".");

			// Se sono stati inseriti tutti i tipi di intervento da eseguire
			// restituisce true, altrimenti false
			if (nuovoIntervento.countTipInterventi() == numeroTipiInseriti) {
				return true;
			} else {
				return false;
			}
		} else {
			// Trace
			System.out
					.println("Interventi -> Inserimento intervento fallito!!");

			return false;
		}

	}

	/**
	 * Modifica intervento.
	 * 
	 * @param interventoTarget
	 *            the intervento target
	 * @return true, if successful
	 */
	public static boolean modificaIntervento(TOIntervento interventoTarget) {

		// Preparazione dei formati SQL
		@SuppressWarnings("resource")
		Formatter f = new Formatter();
		String formatoUpdate = "%s = %s, %s = %s, %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' ";

		String elencoUpdate = f.format(formatoUpdate,
				NOME_COLONNA_ID_INFERMIERE,
				String.valueOf(interventoTarget.getIDInfermiere()),
				NOME_COLONNA_ID_PAZIENTE,
				String.valueOf(interventoTarget.getIDPaziente()),

				NOME_COLONNA_CITTA,
				Database.string2sqlstring(interventoTarget.getCitta()),
				NOME_COLONNA_CIVICO,
				Database.string2sqlstring(interventoTarget.getCivico()),
				NOME_COLONNA_CAP, interventoTarget.getCap(),

				NOME_COLONNA_DATA,
				interventoTarget.getDataDaFormato(FORMATO_DATA_SQL),
				NOME_COLONNA_ORA_INIZIO,
				interventoTarget.getOraInizioDaFormato(FORMATO_ORA_SQL))
				.toString();

		// Composizione istruzione
		String istruzione = "UPDATE " + NOME_TABELLA_INTERVENTI + " SET "
				+ elencoUpdate + " WHERE ID = " + interventoTarget.getID()
				+ ";";

		// Trace
		System.out.println("SQL: " + istruzione.toUpperCase());

		// Esecuzione
		if (Database.esegui(istruzione.toUpperCase())) {
			// TODO Esecuzione andata a buon fine, reinserimento tipi intervento
			// Trace
			System.out
					.println("Interventi -> Dati intervento modificati. Reinserimento dei tipi di intervento in corso...");

			// TODO VERIFICARE - Cancellazione vecchi tipi di intervento
			if (cancellaTipiIntervento(interventoTarget.getID())) {
				// Trace
				System.out
						.println("Interventi -> Tipi intervento "
								+ interventoTarget.getID()
								+ "cancellati con successo!");
			} else {
				// Trace
				System.out.println("Interventi -> ERRORE:Tipi intervento "
						+ interventoTarget.getID()
						+ " non cancellati, modifica fallita.");
				return false;
			}

			// TODO VERIFICARE - Ciclo di inserimento
			// TODO Inserimento dei tipi di intervento:

			// Contatore dei tipi di intervento inseriti
			int numeroTipiInseriti = 0;

			// Ciclo di update
			for (int i = 0; i < interventoTarget.countTipInterventi(); i++) {
				// Esecuzione predicato con incremento indice in caso di
				// successo.
				if (inserisciTipoIntervento(interventoTarget.getID(),
						interventoTarget.getTipoIntervento(i).getPatologia(),
						interventoTarget.getTipoIntervento(i).getNome(),
						interventoTarget.getTipoIntervento(i).getNote())) {
					// Trace
					System.out.println("Interventi -> Tipo intervento <<"
							+ interventoTarget.getTipoIntervento(i).getNome()
							+ ">> inserito con successo");
					numeroTipiInseriti++;

				} else {
					// Trace
					System.out.println("Interventi -> Tipo intervento <<"
							+ interventoTarget.getTipoIntervento(i).getNome()
							+ ">> non inserito. Contattare l'assistenza");
				}
			}

			// Trace
			System.out.println("Interventi -> Inseriti " + numeroTipiInseriti
					+ " tipi di intervento su "
					+ interventoTarget.countTipInterventi() + ".");

			// Se sono stati inseriti tutti i tipi di intervento da eseguire
			// restituisce true, altrimenti false
			if (interventoTarget.countTipInterventi() == numeroTipiInseriti) {
				return true;
			} else {
				return false;
			}
			// throw new
			// UnsupportedOperationException("Errore:Funzione non implementata.");
		} else {
			// TODO Esecuzione fallita.
			// Trace
			System.out.println("Interventi -> ERRORE: Modifica fallita.");

			return false;
		}

	}

	/**
	 * Cancella dal database l'intervento con effetti sulle tabelle con foreign
	 * key.
	 * 
	 * @param id
	 *            - Il numero identificativo dell'indervento da cancellare.
	 * @return <b>true</b> se la cancellazione � stata effettuata con successo,
	 *         <b>false</b> se si � verificato qualche errore.
	 */
	public static boolean cancellaIntervento(int id) {
		// Cancellazione attività con lo stesso id
		return Database.esegui("DELETE FROM INTERVENTI WHERE ID="
				+ String.valueOf(id) + ";");

	}

	/**
	 * Svuota completamente la lista degli interventi nel database. Ne vengono
	 * influenzate tutte le tabelle dipendenti.
	 * 
	 * @return <b>true</b> se la cancellazione � stata effettuata con successo,
	 *         <b>false</b> se si � verificato qualche errore.
	 */
	public static boolean cancellaTutti() {
		// Svuota la tabella
		boolean success = Database.esegui("DELETE FROM INTERVENTI;");
		// Reimposta la chiave ID a 0
		Database.esegui("ALTER TABLE INTERVENTI ALTER COLUMN ID RESTART WITH 0");
		return success;
	}

	/**
	 * Gets the tabella visualizzazione interventi.
	 * 
	 * @return La tabella con la lista guidata degli interventi
	 *         (ADISysTableModel) utilizzabile da una JTable per la
	 *         visualizzazione dei dati
	 */
	public static ADISysTableModel getTabellaVisualizzazioneInterventi() {
		return Database.getTabella("SELECT * FROM VISUALIZZAZIONEINTERVENTI;");
	}

	/**
	 * Gets the tabella interventi.
	 * 
	 * @return La tabella con la lista degli interventi (ADISysTableModel)
	 *         utilizzabile da una JTable per la visualizzazione dei dati
	 */
	public static ADISysTableModel getTabellaInterventi() {
		return Database.getTabella("SELECT * FROM INTERVENTI;");
	}

	/**
	 * Gets the lista interventi infermiere.
	 * 
	 * @param id
	 *            the id
	 * @return the lista interventi infermiere
	 */
	public static ArrayList<TOIntervento> getListaInterventiInfermiere(int id) {
		// Recupero dati sugli interventi dell'infermiere
		String queryText = "SELECT * FROM " + NOME_TABELLA_INTERVENTI
				+ " WHERE " + NOME_COLONNA_ID_INFERMIERE + "=" + id;
		ResultSet tabella = Database.getResultSet(queryText);

		// **Scaricamento dei dati in una lista di interventi**
		// Creazione della lista
		ArrayList<TOIntervento> lista = new ArrayList<>();

		// Ciclo di lettura dalla tabella e scrittura in un nuovo oggetto
		try {
			// Posizionamento del cursore ad inizio lista
			if (tabella.first()) {
				do {
					// Creazione oggetto Intervento
					TOIntervento i = new TOIntervento();

					// Popolamento dell'oggetto

					// Dati intervento
					i.setID(tabella.getString(NOME_COLONNA_ID));
					i.setIDInfermiere(tabella
							.getString(NOME_COLONNA_ID_INFERMIERE));
					i.setIDPaziente(tabella.getString(NOME_COLONNA_ID_PAZIENTE));

					i.setCitta(tabella.getString(NOME_COLONNA_CITTA));
					i.setCivico(tabella.getString(NOME_COLONNA_CIVICO));
					i.setCap(tabella.getString(NOME_COLONNA_CAP));

					// TODO Controllare FORMATTAZIONE - Saltato, filtro
					// impostato nella gui.
					i.setData(tabella.getString(NOME_COLONNA_DATA),
							FORMATO_DATA_SQL);
					i.setOraInizio(tabella.getString(NOME_COLONNA_ORA_INIZIO),
							FORMATO_ORA_SQL);

					// Creazione array dei tipi dell'intervento corrente dal
					// database
					ArrayList<TOTipoIntervento> listaInterventi = leggiTipiIntervento(i
							.getID());

					// Aggiunta dei tipi di intervento all'intervento corrente
					for (TOTipoIntervento t : listaInterventi)
						i.addTipoIntervento(t);

					// Aggiunta dell'oggetto intervento alla lista
					lista.add(i);

				} while (tabella.next());
			} else {
				// Caso tabella vuota
				String errore = "Interventi.getListaInterventiInfermiere(...) -> Nessun intervento relativo all'infermiere selezionato";
				System.out.println(errore);
			}

		} catch (SQLException e) {
			System.out
					.println("Interventi.getListaInterventiInfermiere(...) -> Errore nel trasfermienti tabella/lista, la lista restituita potrebbe essere incompleta");
		}

		// Restituzione lista
		return lista;
	}

	/**
	 * Inserisci tipo intervento.
	 * 
	 * @param idIntervento
	 *            the id intervento
	 * @param patologia
	 *            the patologia
	 * @param nomeTipo
	 *            the nome tipo
	 * @param noteTipo
	 *            the note tipo
	 * @return true, if successful
	 */
	private static boolean inserisciTipoIntervento(int idIntervento,
			String patologia, String nomeTipo, String noteTipo) {
		// TODO Inserimento del tipo di intervento:

		// Composizione colonne
		String colonna1 = NOME_COLONNA_IDINT_TIPO;
		String colonna2 = NOME_COLONNA_PATOLOGIA;
		String colonna3 = NOME_COLONNA_NOME_TIPO;
		String colonna4 = NOME_COLONNA_NOTE_TIPO;
		String colonna5 = NOME_COLONNA_TEMPO_INTERVENTO;
		String colonna6 = NOME_COLONNA_VALORE_RILEVATO;

		String elencoColonneTipi = "(" + colonna1 + "," + colonna2 + ","
				+ colonna3 + "," + colonna4 + "," + colonna5 + "," + colonna6
				+ ")";

		// Composizione valori
		String valore1 = String.valueOf(idIntervento);
		String valore2 = "'" + Database.string2sqlstring(patologia) + "'";
		String valore3 = "'" + Database.string2sqlstring(nomeTipo) + "'";
		String valore4 = "'" + Database.string2sqlstring(noteTipo) + "'";
		String valore5 = "null";
		String valore6 = "null";

		String elencoValoriTipi = "(" + valore1 + "," + valore2 + "," + valore3
				+ "," + valore4 + "," + valore5 + "," + valore6 + ")";

		// Composizione predicato SQL
		String predicatoInserimentoTipo = "INSERT INTO "
				+ NOME_TABELLA_TIPI_INTERVENTI + elencoColonneTipi + " VALUES "
				+ elencoValoriTipi + ";";

		// Trace
		System.out.println("SQL: " + predicatoInserimentoTipo);

		// Esecuzione predicato con incremento indice in caso di successo.
		if (Database.esegui(predicatoInserimentoTipo)) {
			// Trace
			System.out.println("Interventi -> Tipo intervento <<" + nomeTipo
					+ ">> inserito con successo");
			return true;

		} else {
			// Trace
			System.out.println("Interventi -> Tipo intervento <<" + nomeTipo
					+ ">> non inserito.");
			return false;
		}
	}

	/**
	 * Cancella tipi intervento.
	 * 
	 * @param IDIntervento
	 *            the ID intervento
	 * @return true, if successful
	 */
	private static boolean cancellaTipiIntervento(int IDIntervento) {
		// Preparazione predicato
		String predicato = "DELETE FROM " + NOME_TABELLA_TIPI_INTERVENTI
				+ " WHERE " + NOME_COLONNA_IDINT_TIPO + "="
				+ String.valueOf(IDIntervento) + ";";

		// Esecuzione
		return Database.esegui(predicato);
	}

	/**
	 * Leggi tipi intervento.
	 * 
	 * @param IDIntervento
	 *            the ID intervento
	 * @return the array list
	 */
	public static ArrayList<TOTipoIntervento> leggiTipiIntervento(
			int IDIntervento) {
		// Preparazione query
		String query = "SELECT * FROM " + NOME_TABELLA_TIPI_INTERVENTI
				+ " WHERE " + NOME_COLONNA_IDINT_TIPO + "="
				+ String.valueOf(IDIntervento) + ";";

		// Esecuzione query
		ResultSet tabella = Database.getResultSet(query);

		// Creazione lista dei tipi di intervento da restituire
		ArrayList<TOTipoIntervento> array = new ArrayList<>();

		// Trasfermento dati (se la tabella non è vuota)
		try {
			if (tabella.first()) {
				for (; !(tabella.isAfterLast()); tabella.next()) {
					// Creazione e popolamento nuovo tipo
					TOTipoIntervento nuovoTipo = new TOTipoIntervento();
					nuovoTipo.setPatologia(tabella
							.getString(NOME_COLONNA_PATOLOGIA));
					nuovoTipo
							.setNome(tabella.getString(NOME_COLONNA_NOME_TIPO));
					nuovoTipo.setValoreRilevato(tabella
							.getString(NOME_COLONNA_VALORE_RILEVATO));
					nuovoTipo.setTempoIntervento(tabella
							.getString(NOME_COLONNA_TEMPO_INTERVENTO));
					nuovoTipo
							.setNote(tabella.getString(NOME_COLONNA_NOTE_TIPO));

					// Aggiunta del tipo di intervento alla lista
					array.add(nuovoTipo);
				}
			}
		} catch (SQLException e) {
			// trace
			System.out
					.println("Interventi -> ERRORE: Impossibile leggere i tipi dell'intervento con ID="
							+ String.valueOf(IDIntervento));

		}

		return array;
	}

	/**
	 * Gets the intervento.
	 * 
	 * @param id
	 *            the id
	 * @return the intervento
	 */
	public static TOIntervento getIntervento(int id) {
		try {
			String query = "SELECT * FROM " + NOME_TABELLA_INTERVENTI
					+ " WHERE " + NOME_COLONNA_ID + "=" + String.valueOf(id)
					+ ";";
			ResultSet risultato = Database.getResultSet(query);
			if (risultato.first()) {
				TOIntervento i = new TOIntervento();
				i.setID(risultato.getInt(NOME_COLONNA_ID));
				i.setIDInfermiere(risultato.getInt(NOME_COLONNA_ID_INFERMIERE));
				i.setIDPaziente(risultato.getInt(NOME_COLONNA_ID_PAZIENTE));
				i.setData(risultato.getString(NOME_COLONNA_DATA),
						FORMATO_DATA_SQL);
				i.setOraInizio(risultato.getString(NOME_COLONNA_ORA_INIZIO),
						FORMATO_ORA_SQL);
				// i.setOraFineFmt(risultato.getString(NOME_COLONNA_ORA_FINE),FORMATO_ORA_SQL);
				i.setCitta(risultato.getString(NOME_COLONNA_CITTA));
				i.setCivico(risultato.getString(NOME_COLONNA_CIVICO));
				i.setCap(risultato.getString(NOME_COLONNA_CAP));

				for (TOTipoIntervento t : leggiTipiIntervento(id))
					i.addTipoIntervento(t);

				return i;
			}

		} catch (SQLException ex) {
			Logger.getLogger(Interventi.class.getName()).log(Level.SEVERE,
					null, ex);
			// trace
			System.out.println("Interventi -> ERRORE: Intervento n."
					+ String.valueOf(id) + " non trovato.");

		}
		return null;
	}
}
