/*
 * 
 */
package adisys.server.business.core;

import java.util.ArrayList;

import adisys.server.business.TOInfermiere;
import adisys.server.integration.Infermieri;
import adisys.server.utility.ADISysTableModel;
import adisys.server.utility.UIFeedbackException;

/**
 * The Class Infermiere.
 * Classe di business incaricata di gestire 
 * i metodi inerenti agli infermieri
 * 
 */
public class Infermiere {

	
	/**
	 * Crea infermiere.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void creaInfermiere(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		String newNome = (String) parametri.get(0).get(1);
		String newCognome = (String) parametri.get(1).get(1);

		String stringaErrore = new String();
		stringaErrore += TOInfermiere.verificaNome(newNome);
		stringaErrore += TOInfermiere.verificaCognome(newCognome);

		if (stringaErrore.isEmpty()) {
			// Creazione nuovo infermiere
			TOInfermiere nuovoInfermiere = new TOInfermiere();
			nuovoInfermiere.setNome(newNome);
			nuovoInfermiere.setCognome(newCognome);

			boolean esito = Infermieri.creaInfermiere(nuovoInfermiere);
			if (esito) {
				// Caso infermiere aggiunto
				throw new UIFeedbackException("MsgDialog290");
			} else {
				throw new UIFeedbackException("MsgDialog300");
			}
		} else {
			ArrayList<String> replacementArray = new ArrayList<>();
			replacementArray.add(stringaErrore);
			throw new UIFeedbackException("MsgDialog230", replacementArray,
					UIFeedbackException.MsgType.ERROR);
		}

	}

	/**
	 * Modifica infermiere.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void modificaInfermiere(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		int Id = (int) parametri.get(0).get(1);
		String newNome = (String) parametri.get(1).get(1);
		String newCognome = (String) parametri.get(2).get(1);
		

		// Verifica dei dati
		String stringaErrore = new String();
		if (!Infermieri.idEsistente(Id)) {
			throw new UIFeedbackException("MsgDialog1950",
					UIFeedbackException.MsgType.ERROR);
		} else {
			stringaErrore += TOInfermiere.verificaNome(newNome);
			stringaErrore += TOInfermiere.verificaCognome(newCognome);

			if (stringaErrore.isEmpty()) {

				// APPLICAZIONE MODIFICHE
				// Creazione oggetto
				TOInfermiere infermiere = new TOInfermiere();
				infermiere.setID(Id);
				infermiere.setNome(newNome);
				infermiere.setCognome(newCognome);

				// Applicazione modifiche al database
				if (Infermieri.modificaInfermiere(infermiere)) {
					// Caso infermiere aggiunto
					throw new UIFeedbackException("MsgDialog310");
				} else {
					// Caso infermiere non aggiunto
					throw new UIFeedbackException("MsgDialog320");
				}

			} else {
				// Caso dati non validi
				ArrayList<String> replacementArray = new ArrayList<>();
				replacementArray.add(stringaErrore);
				throw new UIFeedbackException("MsgDialog230", replacementArray,
						UIFeedbackException.MsgType.ERROR);
			}
		}
	}

	/**
	 * Cancella infermiere.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void cancellaInfermiere(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		int id = Integer.valueOf((String) parametri.get(0).get(1));

		if (Infermieri.idEsistente(id)) {
			if (Infermieri.cancellaInfermiere(id)) {
				// NOTIFICA OPERAZIONE COMPLETATA
				throw new UIFeedbackException("MsgDialog360");
			} else {
				// NOTIFICA OPERAZIONE FALLITA
				throw new UIFeedbackException("MsgDialog370");
			}
		} else {
			throw new UIFeedbackException("MsgDialog435");
		}
	}

	/**
	 * Cancella tutti infermieri.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void cancellaTuttiInfermieri(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		if (Infermieri.cancellaTutti()) {
			throw new UIFeedbackException("ConfirmMsg180");
		} else {
			throw new UIFeedbackException("ConfirmMsg190");
		}
	}

	/**
	 * Gets the tabella infermieri.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the tabella infermieri
	 */
	public ArrayList<TOInfermiere> getTabellaInfermieri(
			ArrayList<ArrayList<Object>> parametri) {
		TOInfermiere infermiere;
		ArrayList<TOInfermiere> listaInfermieri = new ArrayList<>();

		// Prelevamento dati dal database
		ADISysTableModel tabella = Infermieri.getTabellaInfermieri();

		// Popolamento dei transfer objects
		int qty = tabella.getRowCount();
		for (int i = 0; i < qty; i++) {
			infermiere = new TOInfermiere();
			infermiere.setID((Integer) tabella.getValueAt(i, 0));
			infermiere.setNome((String) tabella.getValueAt(i, 1));
			infermiere.setCognome((String) tabella.getValueAt(i, 2));
			listaInfermieri.add(infermiere);
		}

		return listaInfermieri;
	}

	/**
	 * Show info infermiere.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the object
	 */
	public Object showInfoInfermiere(ArrayList<ArrayList<Object>> parametri) {
		int id = (int) parametri.get(0).get(1);

		return (Object) Infermieri.getInfermiere(id).toString();
	}

}
