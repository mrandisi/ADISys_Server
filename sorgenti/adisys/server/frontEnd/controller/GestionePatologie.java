/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.frontEnd.controller;

import adisys.server.business.TOPatologia;
import adisys.server.business.core.Patologia;
import adisys.server.utility.UIFeedbackException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * The Class GestionePatologie.
 * Classe che riceve dalla Proxy i metodi inerenti 
 * alla gestione delle patologie, inviandoli alla classe Patologia
 * 
 */
public class GestionePatologie extends ADISysAS {

	/**
	 * Instantiates a new gestione patologie.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GestionePatologie() throws ParserConfigurationException,
			SAXException, IOException {
		super();
	}

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

		Patologia Pat = new Patologia();
		Pat.creaPatologia(parametri);
	}

	/**
	 * Modifica patologia.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void modificaPatologia(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		Patologia Pat = new Patologia();
		Pat.modificaPatologia(parametri);
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
		Patologia Pat = new Patologia();
		Pat.cancellaPatologia(parametri);
	}

	/**
	 * Gets the nomi patologie.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the nomi patologie
	 */
	public String[] getNomiPatologie(ArrayList<ArrayList<Object>> parametri) {
		Patologia Pat = new Patologia();
		return Pat.getNomiPatologie(parametri);
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
		Patologia Pat = new Patologia();
		return Pat.getTabellaPatologie(parametri);
	}

}