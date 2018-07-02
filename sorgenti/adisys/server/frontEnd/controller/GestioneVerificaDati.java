/*
 * 
 */
package adisys.server.frontEnd.controller;

import adisys.server.business.core.VerificaDati;
import adisys.server.utility.UIFeedbackException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * The Class GestioneVerificaDati.
 * Classe che riceve dalla Proxy i metodi inerenti 
 * alla verifica del file di journaling, inviandoli 
 * alla classe VerificaDati
 * 
 */
public class GestioneVerificaDati extends ADISysAS {

	/**
	 * Instantiates a new gestione verifica dati.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GestioneVerificaDati() throws ParserConfigurationException,
			SAXException, IOException {
		super();
	}

	/**
	 * Carica file.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the object
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public Object caricaFile(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		VerificaDati ver = new VerificaDati();
		return ver.caricaFile(parametri);
	}

	/**
	 * Gets the dati infermiere.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the dati infermiere
	 */
	public void getDatiInfermiere(ArrayList<ArrayList<Object>> parametri) {
		VerificaDati ver = new VerificaDati();
		ver.getDatiInfermiere(parametri);
	}

	/**
	 * Popola tabella attivita.
	 * 
	 * @param parametri
	 *            the parametri
	 */
	public void popolaTabellaAttivita(ArrayList<ArrayList<Object>> parametri) {
		VerificaDati ver = new VerificaDati();
		ver.popolaTabellaAttivita(parametri);
	}

	/**
	 * Popola tabella log.
	 * 
	 * @param parametri
	 *            the parametri
	 */
	public void popolaTabellaLog(ArrayList<ArrayList<Object>> parametri) {
		VerificaDati ver = new VerificaDati();
		ver.popolaTabellaLog(parametri);
	}

	/**
	 * Gets the lista journaling.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the lista journaling
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void getListaJournaling(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		VerificaDati ver = new VerificaDati();
		ver.getListaJournaling(parametri);
	}

	/**
	 * Show info intervento completo.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void showInfoInterventoCompleto(
			ArrayList<ArrayList<Object>> parametri) throws UIFeedbackException {
		VerificaDati ver = new VerificaDati();
		ver.showInfoInterventoCompleto(parametri);
	}

}
