/*
 * 
 */
package adisys.server.business.core;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import adisys.server.business.TOInfermiere;
import adisys.server.business.TOIntervento;
import adisys.server.business.TOPaziente;
import adisys.server.business.TOTipoIntervento;
import adisys.server.integration.Interventi;
import adisys.server.utility.ADISysTableModel;
import adisys.server.utility.UIFeedbackException;

/**
 * The Class Intervento.
 * Classe di business incaricata di gestire 
 * i metodi inerenti agli interventi
 * 
 */
public class Intervento {

	
	/** The Constant PRIMA_RIGA_DTM. */
	private static final int PRIMA_RIGA_DTM = 0; // Prma riga del
													// defaulttablemodel
	/** The Constant COLONNA_PATOLOGIA_TIPO_DTM. */
	private static final int COLONNA_PATOLOGIA_TIPO_DTM = 0; // Prma riga del
	// defaulttablemodel
	/** The Constant COLONNA_NOME_TIPO_DTM. */
	private static final int COLONNA_NOME_TIPO_DTM = 1; // Prma riga del
	// defaulttablemodel
	/** The Constant COLONNA_NOTE_TIPO_DTM. */
	private static final int COLONNA_NOTE_TIPO_DTM = 2; // Prma riga del

	// defaulttablemodel

	/**
	 * Crea intervento.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void creaIntervento(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		// VERIFICA VALIDITA' E CORREZIONE DEI DATI
		String idPaziente = (String) parametri.get(0).get(1);
		String idInfermiere = (String) parametri.get(1).get(1);
		String citta = (String) parametri.get(2).get(1);
		String civico = (String) parametri.get(3).get(1);
		String cap = (String) parametri.get(4).get(1);
		String data = (String) parametri.get(5).get(1);
		String ora = (String) parametri.get(6).get(1);
		DefaultTableModel tipiIntervento = (DefaultTableModel) parametri.get(7).get(1);
		
		
		
		String errLog = new String();
		errLog += TOPaziente.verificaCoerenzaID(idPaziente);
		errLog += TOInfermiere.verificaCoerenzaID(idInfermiere);

		errLog += TOIntervento.verificaValiditaCitta(citta);
		errLog += TOIntervento.verificaValiditaCivico(civico);
		errLog += TOIntervento.verificaValiditaCap(cap);
		// verificaCAP

		errLog += TOIntervento.verificaValiditaData(data);
		// verifica
		// data
		errLog += TOIntervento.verificaValiditaOra(ora);
		// verifica
		// ora
		// inizio

		errLog += TOIntervento.verificaLimitazioniDataOra(data, ora);

		// Set costante per esplorazione del modello dei tipi di intervento
		final int postUltimaRiga = tipiIntervento.getRowCount();

		// Array per il controllo di coerenza e l'inserimento della lista
		// interventi
		ArrayList<TOTipoIntervento> listaTipi = new ArrayList<>();

		// Inserimento dati nell'array
		for (int i = PRIMA_RIGA_DTM; i < postUltimaRiga; i++) {
			listaTipi.add(new TOTipoIntervento(String.valueOf(tipiIntervento
					.getValueAt(i, COLONNA_PATOLOGIA_TIPO_DTM)), String
					.valueOf(tipiIntervento
							.getValueAt(i, COLONNA_NOME_TIPO_DTM)), String
					.valueOf(tipiIntervento
							.getValueAt(i, COLONNA_NOTE_TIPO_DTM))));
		}

		// verifica tipi intervento
		errLog += TOIntervento.verificaCoerenzaTipi(listaTipi);

		if (errLog.isEmpty()) {

			// INSERIMENTO DATI
			// Creazione nuovo oggetto intervento
			TOIntervento nuovoIntervento = new TOIntervento();

			nuovoIntervento.setIDPaziente(idPaziente);
			nuovoIntervento.setIDInfermiere(idInfermiere);

			nuovoIntervento.setCitta(citta);
			nuovoIntervento.setCivico(civico);
			nuovoIntervento.setCap(cap);

			nuovoIntervento.setData(data, "yyyy-MM-dd");
			nuovoIntervento.setOraInizio(ora, "HH:mm:ss");

			// Aggiunta tipi intervento
			for (TOTipoIntervento t : listaTipi) {
				nuovoIntervento.addTipoIntervento(t);
			}

			if (Interventi.creaIntervento(nuovoIntervento)) {
				throw new UIFeedbackException("MsgDialog380");
			} else {
				throw new UIFeedbackException("MsgDialog390");
			}

		} else {
			// NOTIFICA ERRORE
			ArrayList<String> replacementArray = new ArrayList<String>();
			replacementArray.add(errLog);
			throw new UIFeedbackException("MsgDialog230", replacementArray,
					UIFeedbackException.MsgType.ERROR);

		}
	}

	/**
	 * Modifica intervento.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void modificaIntervento(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		int id = (int) parametri.get(0).get(1);
		String idPaziente = (String) parametri.get(1).get(1);
		String idInfermiere = (String) parametri.get(2).get(1);
		String citta = (String) parametri.get(3).get(1);
		String civico = (String) parametri.get(4).get(1);
		String cap = (String) parametri.get(5).get(1);
		String data = (String) parametri.get(6).get(1);
		String ora = (String) parametri.get(7).get(1);
		DefaultTableModel tipiIntervento = (DefaultTableModel) parametri.get(8).get(1);

			
		// VERIFICA VALIDITA' E CORREZIONE DEI DATI
		String errLog = "";
		errLog += TOPaziente.verificaCoerenzaID(idPaziente);
		errLog += TOPaziente.verificaCoerenzaID(idInfermiere);

		errLog += TOIntervento.verificaValiditaCitta(citta);
		errLog += TOIntervento.verificaValiditaCivico(civico);
		errLog += TOIntervento.verificaValiditaCap(cap);
		// verificaCAP

		errLog += TOIntervento.verificaValiditaData(data);
		// verifica
		// data
		errLog += TOIntervento.verificaValiditaOra(ora);
		// verifica
		// ora
		// inizio

		errLog += TOIntervento.verificaLimitazioniDataOra(data, ora);

		// Set costante per esplorazione del modello dei tipi di intervento
		final int postUltimaRiga = tipiIntervento.getRowCount();

		// Array per il controllo di coerenza e l'inserimento della lista
		// interventi
		ArrayList<TOTipoIntervento> listaTipi = new ArrayList<>();

		// Inserimento dati nell'array
		for (int i = PRIMA_RIGA_DTM; i < postUltimaRiga; i++) {
			listaTipi.add(new TOTipoIntervento(String.valueOf(tipiIntervento
					.getValueAt(i, COLONNA_PATOLOGIA_TIPO_DTM)), String
					.valueOf(tipiIntervento
							.getValueAt(i, COLONNA_NOME_TIPO_DTM)), String
					.valueOf(tipiIntervento
							.getValueAt(i, COLONNA_NOTE_TIPO_DTM))));
		}

		// verifica tipi intervento
		errLog += TOIntervento.verificaCoerenzaTipi(listaTipi);

		// VERIFICA ESISTENZA INTERVENTO
		if (!Interventi.idEsistente(Integer.valueOf(id))) {
			throw new UIFeedbackException("MsgDialog1953",
					UIFeedbackException.MsgType.ERROR);
		} else {

			// Controllo errori
			if (errLog.isEmpty()) {

				// Creazione nuovo oggetto intervento
				TOIntervento intervento = new TOIntervento();

				// INSERIMENTO DATI
				intervento.setID(id);
				intervento.setIDPaziente(idPaziente);
				intervento.setIDInfermiere(idInfermiere);

				intervento.setCitta(citta);
				intervento.setCivico(civico);
				intervento.setCap(cap);

				intervento.setData(data, "dd/MM/yyyy");
				intervento.setOraInizio(ora, "HH.mm");

				// Aggiunta tipi intervento
				for (TOTipoIntervento t : listaTipi) {
					intervento.addTipoIntervento(t);
				}

				// APPLICAZIONE MODIFICHE
				if (Interventi.modificaIntervento(intervento)) {
					throw new UIFeedbackException("MsgDialog400");
				} else {
					throw new UIFeedbackException("MsgDialog410");
				}
			} else {
				// Notifica errore
				ArrayList<String> replacementArray = new ArrayList<String>();
				replacementArray.add(errLog);
				throw new UIFeedbackException("MsgDialog230", replacementArray,
						UIFeedbackException.MsgType.ERROR);
			}
		}
	}

	/**
	 * Cancella intervento.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void cancellaIntervento(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		int id = (int) parametri.get(0).get(1);

		// VERIFICA ESISTENZA INTERVENTO
		if (Interventi.idEsistente(id)) {

			// Cancellazione confermata
			if (Interventi.cancellaIntervento(id)) {
				// Avviso cancellazione andata a buon fine
				throw new UIFeedbackException("MsgDialog420");
			} else {
				// Avviso cancellazione fallita
				throw new UIFeedbackException("MsgDialog370");
			}

		} else {
			throw new UIFeedbackException("MsgDialog430");
		}
	}

	/**
	 * Cancella tutti interventi.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void cancellaTuttiInterventi(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		// Cancellazione confermata
		if (Interventi.cancellaTutti()) {
			// Avviso cancellazione andata a buon fine
			throw new UIFeedbackException("MsgDialog460");
		} else {
			// Avviso cancellazione fallita
			throw new UIFeedbackException("MsgDialog370");
		}

	}

	/**
	 * Gets the tipi intervento.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the tipi intervento
	 */
	public DefaultTableModel getTipiIntervento(
			ArrayList<ArrayList<Object>> parametri) {
		int IDIntervento = (int) parametri.get(0).get(1);
		String[] colonne = (String[]) parametri.get(1).get(1);

		DefaultTableModel modelloTipi = new DefaultTableModel(colonne, 0);

		// Recupero dati sui tipi di intervento
		ArrayList<TOTipoIntervento> tipi = Interventi
				.leggiTipiIntervento(IDIntervento);

		for (TOTipoIntervento t : tipi) {
			String[] valori = { t.getPatologia(), t.getNome(), t.getNote() };
			modelloTipi.addRow(valori);
		}

		return modelloTipi;
	}

	/**
	 * Gets the tabella interventi.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the tabella interventi
	 */
	public ArrayList<TOIntervento> getTabellaInterventi(
			ArrayList<ArrayList<Object>> parametri) {
		TOIntervento intervento;
		Date data;
		Time ora;
		ArrayList<TOIntervento> listaInterventi = new ArrayList<>();

		// Prelevamento dati dal database
		ADISysTableModel tabella = Interventi.getTabellaInterventi();

		// Popolamento dei transfer objects
		int qty = tabella.getRowCount();
		for (int i = 0; i < qty; i++) {
			intervento = new TOIntervento();
			intervento.setID((Integer) tabella.getValueAt(i, 0));
			intervento
					.setIDInfermiere(String.valueOf(tabella.getValueAt(i, 1)));
			intervento.setIDPaziente(String.valueOf(tabella.getValueAt(i, 2)));
			intervento.setCitta((String) tabella.getValueAt(i, 3));
			intervento.setCivico((String) tabella.getValueAt(i, 4));
			intervento.setCap((String) tabella.getValueAt(i, 5));
			data = (Date) tabella.getValueAt(i, 6);
			intervento.setData(data.toString(), "yyyy-MM-dd");
			ora = (Time) tabella.getValueAt(i, 7);
			intervento.setOraInizio(ora.toString(), "HH:mm:ss");

			listaInterventi.add(intervento);
		}

		return listaInterventi;
	}

	/**
	 * Show info intervento.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the object
	 */
	public Object showInfoIntervento(ArrayList<ArrayList<Object>> parametri) {
		int id = (int) parametri.get(0).get(1);

		return (Object) Interventi.getIntervento(id).toString();
	}

}
