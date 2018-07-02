/*
 * 
 */
package adisys.server.frontEnd.controller;

import adisys.server.business.TOInfermiere;
import adisys.server.business.core.Infermiere;
import adisys.server.utility.UIFeedbackException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * The Class GestioneInfermieri.
 * Classe che riceve dalla Proxy i metodi 
 * inerenti alla gestione degli infermieri, 
 * inviandoli alla classe Infermiere
 * 
 */
public class GestioneInfermieri extends ADISysAS {

	/**
	 * Instantiates a new gestione infermieri.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GestioneInfermieri() throws ParserConfigurationException,
			SAXException, IOException {
		super();
	}

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
		Infermiere Inf = new Infermiere();
		Inf.creaInfermiere(parametri);

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
		Infermiere Inf = new Infermiere();
		Inf.modificaInfermiere(parametri);
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
		Infermiere Inf = new Infermiere();
		Inf.cancellaInfermiere(parametri);
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
		Infermiere Inf = new Infermiere();
		Inf.cancellaTuttiInfermieri(parametri);

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
		Infermiere Inf = new Infermiere();
		return Inf.getTabellaInfermieri(parametri);
	}

	/**
	 * Show info infermiere.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the object
	 */
	public Object showInfoInfermiere(ArrayList<ArrayList<Object>> parametri) {
		Infermiere Inf = new Infermiere();
		return Inf.showInfoInfermiere(parametri);
	}


}
