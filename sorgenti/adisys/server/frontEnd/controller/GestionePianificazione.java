/*
 * 
 */
package adisys.server.frontEnd.controller;

import java.util.ArrayList;

import adisys.server.business.TOInfermiere;
import adisys.server.business.TOIntervento;
import adisys.server.business.core.Pianificazione;
import adisys.server.utility.UIFeedbackException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * The Class GestionePianificazione.
 * Classe che riceve dalla Proxy i metodi inerenti alla 
 * gestione dell’esportazione del file di journaling, 
 * inviandoli alla classe Pianificazione
 * 
 */
public class GestionePianificazione extends ADISysAS {

	/**
	 * Instantiates a new gestione pianificazione.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GestionePianificazione() throws ParserConfigurationException,
			SAXException, IOException {
		super();

	}

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
		Pianificazione pian = new Pianificazione();
		pian.esportaPianificazione(parametri);
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
		Pianificazione pian = new Pianificazione();
		return pian.getTabellaInfoInfermieri(parametri);
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
		Pianificazione pian = new Pianificazione();
		return pian.getTabellaVisualizzazioneInterventi(parametri);
	}

}
