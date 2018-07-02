/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.gui;

import adisys.server.integration.Database;
import adisys.server.frontEnd.controller.ADISysAC;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.ArrayList;

/**
 * The Class ADISysMain.
 * Classe pubblica, incaricata di lanciare la finestra pianificatore e le altre interfacce
 * 
 */
public class ADISysMain extends ADISysAC {

	/**
	 * Instantiates a new ADI sys main.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ADISysMain() throws ParserConfigurationException, SAXException,
			IOException {
		super();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws HeadlessException
	 *             the headless exception
	 * @throws SecurityException
	 *             the security exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws SAXException
	 *             the SAX exception
	 */
	public static void main(String[] args) throws HeadlessException,
			SecurityException, IllegalArgumentException,
			ParserConfigurationException, IOException, ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchFieldException, SAXException {

		// Visualizzazione splash screen
		SplashScreen.start();

		// Inizializzazione database
		Database.connetti();

		// Imposta lingua
		adisys.server.presentation.frontController.Fc.setLocale("it", "IT");

		// lancia interfaccia
		visualizzaPianificatore(new ArrayList<ArrayList<Object>>());

		// Chiusura splash screen
		SplashScreen.stop();
	}

	/**
	 * Visualizza la finestra di dialogo del pianificatore.
	 * 
	 * @param parametri
	 *            the parametri
	 * @category Visualizzazione
	 */
	public static void visualizzaPianificatore(
			ArrayList<ArrayList<Object>> parametri) {
		Pianificatore.start();
	}

	/**
	 * Visualizza la finestra di dialogo dell'editor dei pazienti.
	 * 
	 * @param parametri
	 *            the parametri
	 * @category Visualizzazione
	 */
	public static void visualizzaEditorPazienti(
			ArrayList<ArrayList<Object>> parametri) {
		EditorPazienti.start(null);
	}

	/**
	 * Visualizza la finestra di dialogo dell'editor degli infemieri.
	 * 
	 * @param parametri
	 *            the parametri
	 * @category Visualizzazione
	 */
	public static void visualizzaEditorInfermieri(
			ArrayList<ArrayList<Object>> parametri) {
		EditorInfermieri.start(null);
	}

	/**
	 * Visualizza la finestra di dialogo dell'editor degli interventi.
	 * 
	 * @param parametri
	 *            the parametri
	 * @category Visualizzazione
	 */
	public static void visualizzaEditorInterventi(
			ArrayList<ArrayList<Object>> parametri) {
		EditorInterventi.start(null);
	}

	/**
	 * Visualizza la finestra di dialogo dell'editor delle patologie.
	 * 
	 * @param parametri
	 *            the parametri
	 * @category Visualizzazione
	 */
	public static void visualizzaEditorPatologie(
			ArrayList<ArrayList<Object>> parametri) {
		EditorPatologie.start(null);
	}

	/**
	 * Visualizza dialogo verifica.
	 * 
	 * @param parametri
	 *            the parametri
	 * @category Visualizza la finestra di dialogo per la verifica dei file di
	 *           journaling
	 */
	public static void visualizzaDialogoVerifica(
			ArrayList<ArrayList<Object>> parametri) {
		DialogoVerifica.start();
	}

	/**
	 * Visualizza dialogo esportazione.
	 * 
	 * @param parametri
	 *            the parametri
	 * @category Visualizza la finestra di dialogo per l'esportazione della
	 *           Pianificazione di un infermiere
	 */
	public static void visualizzaDialogoEsportazione(
			ArrayList<ArrayList<Object>> parametri) {
		DialogoEsportazione.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adisys.server.frontEnd.controller.Proxy#getResourcePath()
	 */
	@Override
	protected InputStream getResourcePath() {
		// return
		InputStream is = this.getClass().getResourceAsStream(
				"/adisys/server/frontEnd/controller/ConsultAC.xml");
		return is;
	}
}
