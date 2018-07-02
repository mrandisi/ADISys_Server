/*
 * 
 */
package adisys.server.business.core;

import java.util.ArrayList;

import adisys.server.business.TOPatologia;
import adisys.server.integration.Patologie;
import adisys.server.utility.ADISysTableModel;
import adisys.server.utility.UIFeedbackException;

/**
 * The Class Patologia.
 * Classe di business incaricata di gestire 
 * i metodi inerenti alle patologie
 * 
 */
public class Patologia {

	/**
	 * Crea patologia.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void creaPatologia(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		// int newIDPatologia, String newNomePatologia, String newGravita
		String newIDPatologia = (String) parametri.get(0).get(1);
		String newNomePatologia = (String) parametri.get(1).get(1);
		String newGravita = (String) parametri.get(2).get(1);

		// Verifica dei dati
		String stringaErrore = new String();
		stringaErrore += TOPatologia.verificaIDPatologia(newIDPatologia);
		stringaErrore += TOPatologia.verificaNomePatologia(newNomePatologia);
		stringaErrore += TOPatologia.verificaGravita(String.valueOf(newGravita));

		if (stringaErrore.isEmpty()) {
			// Creazione nuova patologia
			TOPatologia nuovaPatologia = new TOPatologia();
			nuovaPatologia.setID(Integer.valueOf(newIDPatologia));
			nuovaPatologia.setNome(newNomePatologia);
			nuovaPatologia.setGravita(newGravita);

			// Aggiunta al database
			if (Patologie.creaPatologia(nuovaPatologia)) {
				// Caso patologia aggiunta
				throw new UIFeedbackException("MsgDialog330");
			} else {
				// Caso patologia non aggiunta
				throw new UIFeedbackException("MsgDialog340");
			}

		} else {
			// Caso dati non validi
			ArrayList<String> replacementArray = new ArrayList<String>();
			replacementArray.add(stringaErrore);
			throw new UIFeedbackException("MsgDialog230", replacementArray,
					UIFeedbackException.MsgType.ERROR);
		}
	}

	/**
	 * Modifica patologia.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 * @category Patologia Modifica
	 */
	public void modificaPatologia(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		// int ID, String newNomePatologia, String newGravita

		int ID = (int) parametri.get(0).get(1);
		String newNomePatologia = (String) parametri.get(1).get(1);
		String newGravita = (String) parametri.get(2).get(1);

		// Verifica dei dati
		String stringaErrore = new String();
		if (!Patologie.idEsistente(ID)) {
			throw new UIFeedbackException("MsgDialog1952",
					UIFeedbackException.MsgType.ERROR);
		}
		stringaErrore += TOPatologia.verificaNomePatologia(newNomePatologia);
		stringaErrore += TOPatologia.verificaGravita(newGravita);

		if (stringaErrore.isEmpty()) {
			// Messaggio di conferma

			// APPLICAZIONE MODIFICHE
			// Creazione oggetto
			TOPatologia patologia = new TOPatologia();
			patologia.setID(ID);
			patologia.setNome(newNomePatologia);
			patologia.setGravita(String.valueOf(newGravita));

			// Applicazione modifiche al database
			if (Patologie.modificaPatologia(patologia)) {
				// Caso patologia aggiunta
				throw new UIFeedbackException("MsgDialog1930",
						UIFeedbackException.MsgType.NOTIFY);
			} else {
				// Caso patologia non aggiunta
				throw new UIFeedbackException("MsgDialog350",
						UIFeedbackException.MsgType.WARNING);
			}

		} else {
			// Caso dati non validi
			ArrayList<String> replacementArray = new ArrayList<String>();
			replacementArray.add(stringaErrore);
			throw new UIFeedbackException("MsgDialog230", replacementArray,
					UIFeedbackException.MsgType.ERROR);
		}

	}

	/**
	 * Cancella patologia.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void cancellaPatologia(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		// int id
		int id = Integer.valueOf((String) parametri.get(0).get(1));
		// VERIFICA ESISTENZA INTERVENTO
		if (Patologie.idEsistente(id)) {

			// Cancellazione confermata
			if (Patologie.cancellaPatologia(id)) {
				// Avviso cancellazione andata a buon fine
				throw new UIFeedbackException("MsgDialog450");
			} else {
				// Avviso cancellazione fallita
				throw new UIFeedbackException("MsgDialog370");
			}

		} else {
			throw new UIFeedbackException("MsgDialog440");
		}
	}

	/**
	 * Gets the nomi patologie.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the nomi patologie
	 */
	public String[] getNomiPatologie(ArrayList<ArrayList<Object>> parametri) {
		return Patologie.getNomiPatologie();
	}

	/**
	 * Gets the tabella patologie.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the tabella patologie
	 */
	public ArrayList<TOPatologia> getTabellaPatologie(
			ArrayList<ArrayList<Object>> parametri) {
		TOPatologia patologia;
		ArrayList<TOPatologia> listaPatologie = new ArrayList<>();

		// Prelevamento dati dal database
		ADISysTableModel tabella = Patologie.getTabellaPatologie();

		// Popolamento dei transfer objects
		int qty = tabella.getRowCount();
		for (int i = 0; i < qty; i++) {
			patologia = new TOPatologia();
			patologia.setID((Integer) tabella.getValueAt(i, 0));
			patologia.setNome((String) tabella.getValueAt(i, 1));
			patologia.setGravita((String.valueOf(tabella.getValueAt(i, 2))));
			listaPatologie.add(patologia);
		}

		return listaPatologie;
	}

}
