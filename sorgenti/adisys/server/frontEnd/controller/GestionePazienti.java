/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.frontEnd.controller;

import adisys.server.business.TOPaziente;
import adisys.server.business.core.Paziente;
import adisys.server.utility.UIFeedbackException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * The Class GestionePazienti.
 * Classe che riceve dalla Proxy i metodi inerenti 
 * alla gestione dei pazienti, inviandoli alla classe Paziente
 * 
 */
public class GestionePazienti extends ADISysAS {

	/**
	 * Instantiates a new gestione pazienti.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GestionePazienti() throws ParserConfigurationException,
			SAXException, IOException {
		super();
	}

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

		Paziente paz = new Paziente();
		paz.creaPaziente(parametri);

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
		Paziente paz = new Paziente();
		paz.modificaPaziente(parametri);

	}

	/**
	 * Cancella paziente.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void cancellaPaziente(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		Paziente paz = new Paziente();
		paz.cancellaPaziente(parametri);
	}

	/**
	 * Cancella tutti pazienti.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void cancellaTuttiPazienti(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {

		Paziente paz = new Paziente();
		paz.cancellaTuttiPazienti(parametri);
	}

	/**
	 * Gets the cellulari paziente.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the cellulari paziente
	 */
	public DefaultListModel<String> getCellulariPaziente(
			ArrayList<ArrayList<Object>> parametri) {

		Paziente paz = new Paziente();
		return paz.getCellulariPaziente(parametri);
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
		Paziente paz = new Paziente();
		return paz.getTabellaPazienti(parametri);
	}

	/**
	 * Show info paziente.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the object
	 */
	public Object showInfoPaziente(ArrayList<ArrayList<Object>> parametri) {
		Paziente paz = new Paziente();
		return paz.showInfoPaziente(parametri);
	}
}
