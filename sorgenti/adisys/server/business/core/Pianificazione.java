/*
 * 
 */
package adisys.server.business.core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import adisys.server.business.TOInfermiere;
import adisys.server.business.TOIntervento;
import adisys.server.business.TOPaziente;
import adisys.server.integration.Infermieri;
import adisys.server.integration.Interventi;
import adisys.server.integration.Pazienti;
import adisys.server.integration.StrutturaInterscambio;
import adisys.server.utility.ADISysTableModel;
import adisys.server.utility.DateFormatConverter;
import adisys.server.utility.UIFeedbackException;

/**
 * The Class Pianificazione.
 * Classe di business incaricata di gestire 
 * i metodi inerenti all’esportazione del file 
 * di journaling
 * 
 */
public class Pianificazione {

	/** The Constant FORMATO_NOME_FILE. */
	private static final String FORMATO_NOME_FILE = "codoper_data_device.xml";

	/** The Constant FILE_SCHEMA_XSD_ESP. */
	private static final String FILE_SCHEMA_XSD_ESP = "SchemaXSD/XSDEsp.xsd";

	/** The Constant SEGNAPOSTO_CODICE_OPERATORE. */
	private static final String SEGNAPOSTO_CODICE_OPERATORE = "codoper";

	/** The Constant SEGNAPOSTO_DATA. */
	private static final String SEGNAPOSTO_DATA = "data";

	/** The Constant SEGNAPOSTO_DEVICE. */
	private static final String SEGNAPOSTO_DEVICE = "device";

	/** The Constant DEVICE. */
	private static final String DEVICE = "s";

	/** The Constant CARTELLA_ESP_FILE. */
	private static final String CARTELLA_ESP_FILE = "Esportazione";

	/** The Constant FORMATO_DATA. */
	private static final String FORMATO_DATA = "yyyy-MM-dd";

	/**
	 * Esporta pianificazione.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void esportaPianificazione(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		int idInfermiere = (int) parametri.get(0).get(1);
		ArrayList<String> replacementArray = new ArrayList<String>();

		// ESPORTAZIONE DELLA PIANIFICAZIONE DEGLI INTERVENTI

		// Verifica esistenza infermiere
		if (Infermieri.idEsistente(idInfermiere)) {

			// Recupero informazioni sugli interventi
			ArrayList<TOIntervento> listaInterventi = Interventi
					.getListaInterventiInfermiere(idInfermiere);

			// Creazione struttura di interscambio
			StrutturaInterscambio s = new StrutturaInterscambio();

			// Per ogni intervento aggiunge i dati del paziente alla lista
			for (TOIntervento i : listaInterventi) {
				// Recupero dati paziente
				TOPaziente p = Pazienti.getPaziente(i.getIDPaziente());
				s.addIntervento(i, p);
			}

			// Preparazione percorso e nome file
			String nomeFile = FORMATO_NOME_FILE;
			nomeFile = nomeFile.replace(SEGNAPOSTO_CODICE_OPERATORE,
					StrutturaInterscambio.int2string6(idInfermiere));
			nomeFile = nomeFile.replace(
					SEGNAPOSTO_DATA,
					DateFormatConverter.long2dateString(
							DateFormatConverter.oggi(), FORMATO_DATA));
			nomeFile = nomeFile.replace(SEGNAPOSTO_DEVICE, DEVICE);

			// Trace
			System.out.println("\nPianificazione -> Preparato nome del file : "
					+ nomeFile);

			// Esportazione del file e restituzione di una stringa con il
			// risultato di scrittura e validazione
			String risultato = s.salvaSuFileXML(CARTELLA_ESP_FILE, nomeFile,
					FILE_SCHEMA_XSD_ESP);

			replacementArray.add(risultato);
			throw new UIFeedbackException("Generic", replacementArray);
		} else {
			// Trace
			System.out
					.println("Impossibile completare l'esportazione, infermiere non presente");
			throw new UIFeedbackException("MsgDialog1950",
					UIFeedbackException.MsgType.ERROR);
		}

	}

	/**
	 * Gets the tabella info infermieri.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the tabella info infermieri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public ArrayList<TOInfermiere> getTabellaInfoInfermieri(
			ArrayList<ArrayList<Object>> parametri) throws UIFeedbackException {
		TOInfermiere infermiere;
		ArrayList<TOInfermiere> listaInfermieri = new ArrayList<>();
		int numInterventi;
		// Prelevamento dati dal database
		ADISysTableModel tabella = Infermieri
				.getTabellaInfoInfermieriConInterventi();

		// Popolamento dei transfer objects
		int qty = tabella.getRowCount();
		for (int i = 0; i < qty; i++) {
			infermiere = new TOInfermiere();
			infermiere.setID((Integer) tabella.getValueAt(i, 0));
			infermiere.setNome((String) tabella.getValueAt(i, 1));
			infermiere.setCognome((String) tabella.getValueAt(i, 2));
			numInterventi = Integer.valueOf(String.valueOf(tabella.getValueAt(
					i, 3))); // convert a long to int because of database data
								// type bad engineering
			infermiere.setNumInterventi(numInterventi);
			listaInfermieri.add(infermiere);
		}

		return listaInfermieri;
	}

	/**
	 * Gets the tabella visualizzazione interventi.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the tabella visualizzazione interventi
	 */
	public ArrayList<TOIntervento> getTabellaVisualizzazioneInterventi(
			ArrayList<ArrayList<Object>> parametri) {
		TOIntervento intervento;
		Date data;
		Time ora;
		ArrayList<TOIntervento> listaInterventi = new ArrayList<>();
		String infermiere;
		String nomePaziente;
		String cognomePaziente;

		// Prelevamento dati dal database
		ADISysTableModel tabella = Interventi
				.getTabellaVisualizzazioneInterventi();

		// Popolamento dei transfer objects
		int qty = tabella.getRowCount();
		for (int i = 0; i < qty; i++) {
			intervento = new TOIntervento();
			intervento.setID((Integer) tabella.getValueAt(i, 0));
			infermiere = String.valueOf(tabella.getValueAt(i, 1)); // convert a
																	// long to
																	// int
																	// because
																	// of
																	// database
																	// data type
																	// bad
																	// engineering
			intervento.setCognomeInfermiere(infermiere);
			nomePaziente = String.valueOf(tabella.getValueAt(i, 2));
			intervento.setNomePaziente(nomePaziente);
			cognomePaziente = String.valueOf(tabella.getValueAt(i, 3));
			intervento.setCognomePaziente(cognomePaziente);
			data = (Date) tabella.getValueAt(i, 4);
			intervento.setData(data.toString(), "yyyy-MM-dd");
			ora = (Time) tabella.getValueAt(i, 5);
			intervento.setOraInizio(ora.toString(), "HH:mm:ss");
			intervento.setCitta((String) tabella.getValueAt(i, 7));
			intervento.setCivico((String) tabella.getValueAt(i, 8));

			listaInterventi.add(intervento);
		}

		return listaInterventi;
	}

}
