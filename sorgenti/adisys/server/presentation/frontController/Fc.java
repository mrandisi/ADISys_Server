/*
 * 
 */
package adisys.server.presentation.frontController;

import java.util.ArrayList;
import adisys.server.frontEnd.controller.ApplicationController;
import adisys.server.frontEnd.controller.ADISysAC;
import adisys.server.utility.UIFeedbackException.MsgType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Regola la navigazione tra finestre e delega le richieste di servizi al
 * BusinessDelegate. Tutte le finestre invocano questa classe per effettuare una
 * richiesta.
 * 
 */
public class Fc implements FrontController {

	/** The fc. */
	private static Fc fc = null;

	/** The current locale. */
	private static Locale currentLocale;

	/**
	 * 
	 * @return single instance of Fc
	 */
	/*
	 * l'unico punto di accesso alla classe per il mondo esterno viene fornito
	 * attraverso il metodo statico getInstance() che si occupa di restituire
	 * (creandola prima se non � mai stata creata) l'unica istanza della classe.
	 */
	public static Fc getInstance() {
		if (fc == null) {
			Fc.fc = new Fc();
		}
		return fc;
	}

	/**
	 * Instantiates a new fc.
	 */
	private Fc() {
	}

	;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * adisys.server.presentation.frontController.FrontController#processRequest
	 * (java.lang.String)
	 */
	public Object processRequest(String request) {
		return processRequest(request, new ArrayList<ArrayList<Object>>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 
	 */
	public Object processRequest(String request,
			ArrayList<ArrayList<Object>> parametri) {
		try {
			/*
			 * recupera l'Application Controller di ADISys in base
			 * al contenuto dell'attributo map avvalorato secondo il contenuto
			 * del file XML ADISysACFactory
			 */
			ApplicationController ac = (ADISysAC) (new AcFactory().getInstance(request));
			System.out.println(ac + request);

			// restituisce il risultato dell'esecuzione del metodo richiesto

			return ac.handleRequest(request, parametri);
		} catch (ParserConfigurationException | SAXException | IOException
				| SecurityException | IllegalArgumentException
				| ClassNotFoundException | NoSuchMethodException
				| InstantiationException | IllegalAccessException
				| InvocationTargetException | NoSuchFieldException e) {
			System.err
					.println("PROCESSREQUEST: \nIMPOSSIBILE RESTITUIRE UN RISULTATO: \n"
							+ e);
		}
		return null;
	}

	/**
	 * Message bundle.
	 * 
	 * @param msgCode
	 *            the msg code
	 * @return the string
	 */
	public static String MessageBundle(String msgCode) {
		String textMsg = ResourceBundle.getBundle(
				"adisys/server/lang/MessagesBundle", currentLocale).getString(
				msgCode);
		return textMsg;
	}

	/**
	 * Message bundle.
	 * 
	 * @param msgCode
	 *            the msg code
	 * @param replacementArray
	 *            the replacement array
	 * @return the string
	 */
	public static String MessageBundle(String msgCode,
			ArrayList<String> replacementArray) {
		String textMsg = Fc.MessageBundle(msgCode);
		String composedMsg = Fc.composeString(textMsg, replacementArray);
		return composedMsg;
	}

	/**
	 * Message bundle.
	 * 
	 * @param msgCode
	 *            the msg code
	 * @param type
	 *            the type
	 * @return the string
	 */
	public static String MessageBundle(String msgCode, String type) {
		String textMsg = ResourceBundle.getBundle(
				"adisys/server/lang/MessagesBundle", currentLocale).getString(
				msgCode);
		return textMsg;
	}

	/**
	 * Message bundle.
	 * 
	 * @param msgCode
	 *            the msg code
	 * @param replacementArray
	 *            the replacement array
	 * @param type
	 *            the type
	 * @return the string
	 */
	public static String MessageBundle(String msgCode,
			ArrayList<String> replacementArray, String type) {
		String textMsg = Fc.MessageBundle(msgCode);
		String composedMsg = Fc.composeString(textMsg, replacementArray);
		return composedMsg;
	}

	/**
	 * Sets the locale.
	 * 
	 * @param language
	 *            the language
	 * @param country
	 *            the country
	 */
	public static void setLocale(String language, String country) {
		currentLocale = new Locale(language, country);
	}

	/**
	 * Stampa i mssaggi popup.
	 * 
	 * @param messaggio
	 *            the messaggio
	 */
	public static void visualizzaMessaggioPopup(String messaggio) {
		JOptionPane.showMessageDialog(null, messaggio);
	}

	/**
	 * Visualizza messaggio popup.
	 * 
	 * @param messaggio
	 *            the messaggio
	 * @param type
	 *            the type
	 */
	public static void visualizzaMessaggioPopup(String messaggio, MsgType type) {
		if (type == MsgType.NOTIFY) {
			JOptionPane.showMessageDialog(null, messaggio,
					Fc.MessageBundle("MsgDialog4"),
					JOptionPane.INFORMATION_MESSAGE);
		} else if (type == MsgType.WARNING) {
			JOptionPane
					.showMessageDialog(null, messaggio,
							Fc.MessageBundle("MsgDialog5"),
							JOptionPane.WARNING_MESSAGE);
		} else if (type == MsgType.ERROR) {
			JOptionPane.showMessageDialog(null, messaggio,
					Fc.MessageBundle("MsgDialog6"), JOptionPane.ERROR_MESSAGE);
		} else {
			visualizzaMessaggioPopup(messaggio);
		}
	}

	/**
	 * Method composeString Replace variables into a string.
	 * 
	 * @param message
	 *            the message
	 * @param replacementArray
	 *            - variables to replace
	 * @return the string
	 */
	public static String composeString(String message,
			ArrayList<String> replacementArray) {
		for (int i = 0; i < replacementArray.size(); i++) {
			message = message.replaceFirst("%" + Integer.toString(0),replacementArray.get(i));
		}

		return message;
	}

	/**
	 * getEmptyTableModel.
	 * 
	 * @param tableBundleIdentifier
	 *            the table bundle identifier
	 * @return the table header names
	 */
	public static String[] getTableHeaderNames(String tableBundleIdentifier) {
		// getting column header names
		String columnNamesImploded = MessageBundle(tableBundleIdentifier);

		// exploding content
		String[] columnNamesExploded = columnNamesImploded.split(",");

		return columnNamesExploded;
	}

	/**
	 * Settig table headers.
	 * 
	 * @param table
	 *            the table
	 * @param tableBundleIdentifier
	 *            the table bundle identifier
	 * @return the j table
	 */
	public static JTable setTableHeaders(JTable table,
			String tableBundleIdentifier) {
		String headers[] = getTableHeaderNames(tableBundleIdentifier);

		for (int i = 0; i < headers.length; i++) {
			table.getColumnModel().getColumn(i).setHeaderValue(headers[i]);
		}

		return table;
	}
}