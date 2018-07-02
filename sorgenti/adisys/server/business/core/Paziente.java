/*
 * 
 */
package adisys.server.business.core;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import adisys.server.business.TOPaziente;
import adisys.server.integration.Pazienti;
import adisys.server.utility.ADISysTableModel;
import adisys.server.utility.UIFeedbackException;

/**
 * The Class Paziente.
 * Classe di business incaricata di gestire 
 * i metodi inerenti ai pazienti
 * 
 */
public class Paziente {

	/**
	 * Crea paziente.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void creaPaziente(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		String nome = (String) parametri.get(0).get(1);
		String cognome = (String) parametri.get(1).get(1);
		String dataNascita = (String) parametri.get(2).get(1);
		String formatoDataNascita = (String) parametri.get(3).get(1);
		Object[] cellulari = (Object[]) parametri.get(4).get(1);

		// VERIFICA DEI DATI
		String errLog = "";

		errLog += TOPaziente.verificaNome(nome);
		errLog += TOPaziente.verificaCognome(cognome);
		errLog += TOPaziente.verificaDataNascita(dataNascita,
				formatoDataNascita);

		// Modifica versione 2 - ciclo che controlla tutti i numneri di
		// cellulare
		for (Object cellulare : cellulari) {
			errLog += TOPaziente.verificaCellulare(String.valueOf(cellulare));
		}

		// Verifica errori rilevati
		if (errLog.isEmpty()) {

			// Conferma accordata - Creazione del paziente
			TOPaziente p = new TOPaziente();

			p.setNome(nome);
			p.setCognome(cognome);
			p.setDataNascita(dataNascita, formatoDataNascita);

			// Modifica versione 2: aggiunta dei numeri di cellulare
			for (Object cellulare : cellulari) {
				p.addCellulare(String.valueOf(cellulare));
			}

			// Tentativo di aggiunta al DB
			if (Pazienti.creaPaziente(p)) {
				// Ok
				throw new UIFeedbackException("MsgDialog200");
			} else {
				// Aggiunta non effettuata
				throw new UIFeedbackException("MsgDialog210");
			}

		} else {
			// Errori rilevati, visualizza messaggio di errore
			ArrayList<String> replacementArray = new ArrayList<String>();
			replacementArray.add(errLog);
			throw new UIFeedbackException("MsgDialog230", replacementArray,
					UIFeedbackException.MsgType.ERROR);
		}
	}

	/**
	 * Modifica paziente.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void modificaPaziente(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		String ID = (String) parametri.get(0).get(1);
		String nome = (String) parametri.get(1).get(1);
		String cognome = (String) parametri.get(2).get(1);
		String dataNascita = (String) parametri.get(3).get(1);
		String formatoDataNascita = (String) parametri.get(4).get(1);
		Object[] cellulari = (Object[]) parametri.get(5).get(1);

		// VERIFICA DEI DATI
		String errLog = "";

		errLog += TOPaziente.verificaCoerenzaID(ID);
		errLog += TOPaziente.verificaNome(nome);
		errLog += TOPaziente.verificaCognome(cognome);
		errLog += TOPaziente.verificaDataNascita(dataNascita,
				formatoDataNascita);

		// Modifica versione 2 - ciclo che controlla tutti i numneri di
		// cellulare
		for (Object cellulare : cellulari) {
			errLog += TOPaziente.verificaCellulare(String.valueOf(cellulare));
		}

		// verifica esisteza id

		if (!Pazienti.idEsistente(Integer.valueOf(ID))) {
			throw new UIFeedbackException("MsgDialog1951",
					UIFeedbackException.MsgType.ERROR);
		} else {

			// Verifica errori rilevati
			if (errLog.isEmpty()) {

				TOPaziente p = new TOPaziente();

				p.setID(Integer.valueOf(ID));
				p.setNome(nome);
				p.setCognome(cognome);
				p.setDataNascita(dataNascita, formatoDataNascita);

				// Modifica versione 2 ciclo che aggiunge i numeri di
				// cellulare
				for (Object cellulare : cellulari) {
					p.addCellulare(String.valueOf(cellulare));
				}

				// Tentativo di aggiunta al DB
				if (Pazienti.modificaPaziente(p)) {
					// Ok
					throw new UIFeedbackException("MsgDialog250");
				} else {
					// Aggiunta non effettuata
					throw new UIFeedbackException("MsgDialog10");
				}

			} else {
				// Errori rilevati, visualizza messaggio di errore
				ArrayList<String> replacementArray = new ArrayList<String>();
				replacementArray.add(errLog);
				throw new UIFeedbackException("MsgDialog240", replacementArray,
						UIFeedbackException.MsgType.ERROR);
			}
		}

	}

	/**
	 * Cancella paziente.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 * @category Paziente Cancella
	 */
	public void cancellaPaziente(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		int selectedID = (int) parametri.get(0).get(1);

		if (Pazienti.idEsistente(selectedID)) {
			// CANCELLAZIONE
			if (Pazienti.cancellaPaziente(selectedID)) {
				// NOTIFICA OPERAZIONE COMPLETATA
				throw new UIFeedbackException("MsgDialog361");
			} else {
				// NOTIFICA OPERAZIONE FALLITA
				throw new UIFeedbackException("MsgDialog370");
			}
		} else {

			throw new UIFeedbackException("MsgDialog425");
		}
	}

	/**
	 * Cancella tutti pazienti.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 * @category Paziente Cancella
	 */
	public void cancellaTuttiPazienti(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		// Cancellazione
		if (Pazienti.cancellaTutti()) {
			// Conferma cancellazione
			throw new UIFeedbackException("msgDialog270");
		} else {
			// Avviso cancellazione fallita
			throw new UIFeedbackException("msgDialog280");
		}

	}

	/**
	 * Vers 2.0 - Metodo per la lettura dei numeri di cellulare.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the cellulari paziente
	 */
	public DefaultListModel<String> getCellulariPaziente(
			ArrayList<ArrayList<Object>> parametri) {

		int idPaziente = (int) parametri.get(0).get(1);
		// Caricamento paziente
		TOPaziente p = Pazienti.getPaziente(idPaziente);

		// Recupero array cellulari
		Object c[] = p.getCellulari();

		// Creazione modello lista
		DefaultListModel<String> modelloListaCellulari = new DefaultListModel<>();

		// Riempimento modello
		for (int cont = 0; cont < c.length; cont++) {
			modelloListaCellulari.addElement(String.valueOf(c[cont]));
		}

		// Restituzione Modello
		return modelloListaCellulari;
	}

	/**
	 * Gets the tabella pazienti.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the tabella pazienti
	 */
	public ArrayList<TOPaziente> getTabellaPazienti(
	    ArrayList<ArrayList<Object>> parametri) {
		TOPaziente paziente;
		Date data;
		ArrayList<TOPaziente> listaPazienti = new ArrayList<>();

		// Prelevamento dati dal database
		ADISysTableModel tabella = Pazienti.getTabellaPazienti();

		// Popolamento dei transfer objects
		int qty = tabella.getRowCount();
		for (int i = 0; i < qty; i++) {
			paziente = new TOPaziente();
			paziente.setID((Integer) tabella.getValueAt(i, 0));
			paziente.setNome((String) tabella.getValueAt(i, 1));
			paziente.setCognome((String) tabella.getValueAt(i, 2));
			data = (Date) tabella.getValueAt(i, 3);
			paziente.setDataNascita(data.toString(), "yyyy-MM-dd");
			listaPazienti.add(paziente);
		}

		return listaPazienti;
	}

	/**
	 * Show info paziente.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the object
	 */
	public Object showInfoPaziente(ArrayList<ArrayList<Object>> parametri) {
		int id = (int) parametri.get(0).get(1);

		return (Object) Pazienti.getPaziente(id).toString();
	}

}
