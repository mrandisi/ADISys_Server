/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.frontEnd.controller;

import adisys.server.business.TOIntervento;
import adisys.server.business.core.Intervento;
import adisys.server.utility.UIFeedbackException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * The Class GestioneInterventi.
 * Classe che riceve dalla Proxy i metodi inerenti 
 * alla gestione degli interventi, inviandoli alla 
 * classe Infermiere
 * 
 */
public class GestioneInterventi extends ADISysAS {

	/**
	 * Instantiates a new gestione interventi.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GestioneInterventi() throws ParserConfigurationException,
			SAXException, IOException {
		super();
	}

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
		Intervento Int = new Intervento();
		Int.creaIntervento(parametri);

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
		Intervento Int = new Intervento();
		Int.modificaIntervento(parametri);

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

		Intervento Int = new Intervento();
		Int.cancellaIntervento(parametri);

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

		Intervento Int = new Intervento();
		Int.cancellaTuttiInterventi(parametri);
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
		Intervento Int = new Intervento();
		return Int.getTipiIntervento(parametri);
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
		Intervento Int = new Intervento();
		return Int.getTabellaInterventi(parametri);
	}

	/**
	 * Show info intervento.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the object
	 */
	public Object showInfoIntervento(ArrayList<ArrayList<Object>> parametri) {
		Intervento Int = new Intervento();
		return Int.showInfoIntervento(parametri);
	}

}
