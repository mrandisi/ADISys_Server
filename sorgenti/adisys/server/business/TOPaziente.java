/*
 * 
 */
package adisys.server.business;

import adisys.server.presentation.frontController.Fc;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;

import adisys.server.utility.DateFormatConverter;

/**
 * Struttura per il passaggio dei dati di un singolo paziente.
 * Classe entità utilizzata per creare oggetti contenenti i dati di un paziente
 * 
 */
public class TOPaziente {

	// Variabili di istanza
	/** The id. */
	private int ID;

	/** The nome. */
	private String nome;

	/** The cognome. */
	private String cognome;

	/** The data nascita. */
	private Date dataNascita;

	/** The cellulari. */
	private ArrayList<String> cellulari; // Modifica Ver 2: sostituisce
											// "cellulare"

	// Costanti di vincolo (statiche)
	/** The Constant LUNGHEZZA_MASSIMA_ID. */
	private static final int LUNGHEZZA_MASSIMA_ID = 5;

	/** The Constant LUNGHEZZA_MASSIMA_NOME. */
	private static final int LUNGHEZZA_MASSIMA_NOME = 20;

	/** The Constant LUNGHEZZA_MASSIMA_COGNOME. */
	private static final int LUNGHEZZA_MASSIMA_COGNOME = 20;

	/** The Constant LUNGHEZZA_MASSIMA_CELLULARE. */
	private static final int LUNGHEZZA_MASSIMA_CELLULARE = 10;

	/**
	 * Instantiates a new TO paziente.
	 */
	public TOPaziente() {
		cellulari = new ArrayList<>();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets the nome.
	 * 
	 * @return Il nome del paziente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Gets the cognome.
	 * 
	 * @return il cognome del paziente
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Gets the data nascita.
	 * 
	 * @param formato
	 *            the formato
	 * @return the data nascita
	 */
	public String getDataNascita(String formato) {
		if (dataNascita != null) {
			return DateFormatConverter.long2dateString(dataNascita.getTime(),
					formato);
		} else {
			return "";
		}
	}

	/**
	 * Modificato in versione 2 da getCellulare a getCellulari Metodo che
	 * restituisce un array di numeri di telefono (Qbject[] che contiene
	 * stringhe).
	 * 
	 * @return il numero di cellulare del paziente
	 */
	public Object[] getCellulari() {
		return (cellulari.toArray());
	}

	/**
	 * Aggiunta in versione 2: Cancellazione numeri di telefono del paziente.
	 */
	public void clearCellulari() {
		cellulari.clear();
	}

	/**
	 * Sets the id.
	 * 
	 * @param ID
	 *            il nuovo ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Sets the nome.
	 * 
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Sets the cognome.
	 * 
	 * @param cognome
	 *            the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Aggiunta numero cellulare (sostituisce setCellulare nella vers.2)
	 * 
	 * @param cellulare
	 *            the cellulare to set
	 */
	public void addCellulare(String cellulare) {
		this.cellulari.add(cellulare);
	}

	/**
	 * Sets the data nascita.
	 * 
	 * @param newDataNascita
	 *            the new data nascita
	 * @param formato
	 *            the formato
	 */
	public void setDataNascita(String newDataNascita, String formato) {

		dataNascita = new Date(DateFormatConverter.dateString2long(
				newDataNascita, formato));
	}

	/**
	 * Calcola anni mesi.
	 * 
	 * @return the string
	 */
	public String calcolaAnniMesi() {
		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar nascita = new GregorianCalendar();
		nascita.setTimeInMillis(dataNascita.getTime());

		int yearDiff = today.get(Calendar.YEAR) - nascita.get(Calendar.YEAR);
		int monthDiff = (yearDiff * 12 + today.get(Calendar.MONTH) - nascita
				.get(Calendar.MONTH)) % 12;

		String formatoprovvisorio = "%02d/%02d";
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter();
		formatter.format(formatoprovvisorio, yearDiff, monthDiff);

		System.out.println(Fc.MessageBundle("MsgDialog1580") + " "
				+ formatter.toString());
		return formatter.toString();

	}

	/**
	 * Verifica coerenza id.
	 * 
	 * @param newID
	 *            the new id
	 * @return the string
	 */
	public static String verificaCoerenzaID(String newID) {
		String errLog = "";

		// ID NON VUOTO
		if (newID.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog1480");
		}

		// Controllo lunghezza massima
		if (newID.length() > LUNGHEZZA_MASSIMA_ID) {
			errLog += Fc.MessageBundle("MsgDialog1490");
		}

		// controllo ID numerico - Saltato, operazione effettuata in
		// automatico nell'interfaccia
		return errLog;
	}

	/**
	 * Verifica nome.
	 * 
	 * @param nome
	 *            the nome
	 * @return the string
	 */
	public static String verificaNome(String nome) {
		String errLog = "";

		// NON VUOTO
		if (nome.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog1500");
		}

		// Controllo lunghezza massima
		if (nome.length() > LUNGHEZZA_MASSIMA_NOME) {
			errLog += Fc.MessageBundle("MsgDialog1510")
					+ LUNGHEZZA_MASSIMA_NOME + ")";
		}

		return errLog;
	}

	/**
	 * Verifica cognome.
	 * 
	 * @param cognome
	 *            the cognome
	 * @return the string
	 */
	public static String verificaCognome(String cognome) {
		String errLog = "";

		// NON VUOTO
		if (cognome.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog1520");
		}

		// Controllo lunghezza massima
		if (cognome.length() > LUNGHEZZA_MASSIMA_COGNOME) {
			errLog += Fc.MessageBundle("MsgDialog1530")
					+ LUNGHEZZA_MASSIMA_COGNOME + ")";
		}

		return errLog;
	}

	/**
	 * Verifica data nascita.
	 * 
	 * @param dataNascita
	 *            the data nascita
	 * @param formato
	 *            the formato
	 * @return the string
	 */
	public static String verificaDataNascita(String dataNascita, String formato) {
		String errLog = "";

		if (dataNascita.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog1540");
		} else if (!DateFormatConverter.parseable(dataNascita, formato)) {
			errLog += Fc.MessageBundle("MsgDialog1550");
		}

		return errLog;
	}

	/**
	 * Verifica cellulare.
	 * 
	 * @param cellulare
	 *            the cellulare
	 * @return the string
	 */
	public static String verificaCellulare(String cellulare) {
		String errLog = "";

		// NON VUOTO
		if (cellulare.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog1560");
		}
		// Controllo lunghezza massima
		if (cellulare.length() > LUNGHEZZA_MASSIMA_CELLULARE) {
			errLog += Fc.MessageBundle("MsgDialog1570")
					+ LUNGHEZZA_MASSIMA_CELLULARE + ")";
		}

		return errLog;
	}

	// @Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String stringaPaziente = new String();
		stringaPaziente += Fc.MessageBundle("MsgDialog1590") + " " + getID();
		stringaPaziente += Fc.MessageBundle("MsgDialog1600") + " " + getNome();
		stringaPaziente += Fc.MessageBundle("MsgDialog1610") + " "
				+ getCognome();
		stringaPaziente += Fc.MessageBundle("MsgDialog1620") + " "
				+ getDataNascita("dd/MM/yyyy");
		stringaPaziente += Fc.MessageBundle("MsgDialog1630");
		for (String c : cellulari) {
			stringaPaziente += "\n " + " " + c;
		}
		return stringaPaziente;
	}
}
