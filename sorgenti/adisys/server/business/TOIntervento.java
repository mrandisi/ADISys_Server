/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.business;

import adisys.server.presentation.frontController.Fc;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import adisys.server.utility.DateFormatConverter;

/**
 * The Class TOIntervento.
 * Classe entità utilizzata per creare oggetti contenenti i dati di un intervento
 * 
 */
public class TOIntervento {

	// Campi
	/** The id. */
	private int ID;

	/** The ID paziente. */
	private int IDPaziente;

	/** The ID infermiere. */
	private int IDInfermiere;

	/** The data. */
	private Date data;

	/** The ora inizio. */
	private Time oraInizio;

	/** The ora fine. */
	private Time oraFine;

	/** The cognome infermiere. */
	private String cognomeInfermiere;

	/** The nome paziente. */
	private String nomePaziente;

	/** The cognome paziente. */
	private String cognomePaziente;

	/** The citta. */
	private String citta;

	/** The civico. */
	private String civico;

	/** The cap. */
	private String cap;

	/** The tipi intervento. */
	protected ArrayList<TOTipoIntervento> tipiIntervento;

	/** The Constant formatoDataInput. */
	private static final String formatoDataInput = "dd/MM/yyyy";

	/** The Constant formatoOraInput. */
	private static final String formatoOraInput = "HH.mm";

	/** The Constant limitazioneOraria. */
	private static final int limitazioneOraria = 2;

	// Costanti di vincolo
	/** The Constant lunghezzaMassimaID. */
	private static final int lunghezzaMassimaID = 6;

	/** The Constant lunghezzaMassimaNome. */
	private static final int lunghezzaMassimaNome = 20;

	/** The Constant lunghezzaMassimaCognome. */
	private static final int lunghezzaMassimaCognome = 20;

	/** The Constant lunghezzaMassimaCitta. */
	private static final int lunghezzaMassimaCitta = 30;

	/** The Constant lunghezzaMassimaCivico. */
	private static final int lunghezzaMassimaCivico = 30;

	/** The Constant lunghezzaMassimaCap. */
	private static final int lunghezzaMassimaCap = 5; // Nuova vers 2

	/** The Constant lunghezzaMassimaTipo. */
	private static final int lunghezzaMassimaTipo = 300;

	/** The Constant lunghezzaMassimaNote. */
	private static final int lunghezzaMassimaNote = 300;

	/**
	 * Instantiates a new TO intervento.
	 */
	public TOIntervento() {
		// Verifica aggiornamento costruttore
		data = new Date(0);
		oraInizio = new Time(0);
		oraFine = new Time(0);
		tipiIntervento = new ArrayList<>();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets the ID paziente.
	 * 
	 * @return the ID paziente
	 */
	public int getIDPaziente() {
		return IDPaziente;
	}

	/**
	 * Gets the nome paziente.
	 * 
	 * @return the nome paziente
	 */
	public String getNomePaziente() {
		return nomePaziente;
	}

	/**
	 * Gets the cognome paziente.
	 * 
	 * @return the cognome paziente
	 */
	public String getCognomePaziente() {
		return cognomePaziente;
	}

	/**
	 * Gets the cognome infermiere.
	 * 
	 * @return the cognome infermiere
	 */
	public String getCognomeInfermiere() {
		return cognomeInfermiere;
	}

	/**
	 * Gets the ID infermiere.
	 * 
	 * @return the ID infermiere
	 */
	public int getIDInfermiere() {
		return IDInfermiere;
	}

	/**
	 * Gets the data.
	 * 
	 * @param formato
	 *            the formato
	 * @return the data
	 */
	public String getData(String formato) {
		if (data != null) {
			return DateFormatConverter.long2dateString(data.getTime(), formato);
		} else {
			return "";
		}
	}

	/**
	 * Gets the ora inizio.
	 * 
	 * @param formato
	 *            the formato
	 * @return the ora inizio
	 */
	public String getOraInizio(String formato) {
		if (oraInizio != null) {
			return DateFormatConverter.long2dateString(oraInizio.getTime(),
					formato);
		} else {
			return "";
		}
		// return oraInizio.toString();
	}

	/**
	 * Gets the ora fine.
	 * 
	 * @return the ora fine
	 */
	public String getOraFine() {
		return oraFine.toString();
	}

	/**
	 * Gets the citta.
	 * 
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * Gets the civico.
	 * 
	 * @return the civico
	 */
	public String getCivico() {
		return civico;
	}

	/**
	 * Gets the cap.
	 * 
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * Sets the cap.
	 * 
	 * @param cap
	 *            the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Sets the id.
	 * 
	 * @param ID
	 *            the id
	 * @return the string
	 */
	public String setID(String ID) {

		String errore = "";

		// Caso stringa vuota
		if (ID.isEmpty()) {
			errore += Fc.MessageBundle("MsgDialog790");
		} else {
			this.ID = Integer.valueOf(ID);
		}

		// Restituzione stringa
		return errore;
	}

	/**
	 * Sets the id.
	 * 
	 * @param newID
	 *            the new id
	 */
	public void setID(int newID) {
		this.ID = newID;
	}

	/**
	 * Sets the ID paziente.
	 * 
	 * @param IDPaziente
	 *            the new ID paziente
	 */
	public void setIDPaziente(int IDPaziente) {
		this.IDPaziente = IDPaziente;
	}

	/**
	 * Sets the id paziente.
	 * 
	 * @param newIDPaziente
	 *            the new id paziente
	 * @return the string
	 */
	public String setIDPaziente(String newIDPaziente) {

		String errore = "";

		// Caso stringa vuota
		if (newIDPaziente.isEmpty()) {
			errore += Fc.MessageBundle("MsgDialog800");
		} else {
			this.IDPaziente = Integer.valueOf(newIDPaziente);
		}

		// Restituzione stringa
		return errore;
	}

	/**
	 * Sets the nome paziente.
	 * 
	 * @param nomePaziente
	 *            the new nome paziente
	 */
	public void setNomePaziente(String nomePaziente) {
		this.nomePaziente = nomePaziente;
	}

	/**
	 * Sets the cognome paziente.
	 * 
	 * @param cognomePaziente
	 *            the new cognome paziente
	 */
	public void setCognomePaziente(String cognomePaziente) {
		this.cognomePaziente = cognomePaziente;
	}

	/**
	 * Sets the cognome infermiere.
	 * 
	 * @param cognomeInfermiere
	 *            the new cognome infermiere
	 */
	public void setCognomeInfermiere(String cognomeInfermiere) {
		this.cognomeInfermiere = cognomeInfermiere;
	}

	/**
	 * Sets the ID infermiere.
	 * 
	 * @param IDInfermiere
	 *            the new ID infermiere
	 */
	public void setIDInfermiere(int IDInfermiere) {
		this.IDInfermiere = IDInfermiere;
	}

	/**
	 * Sets the id infermiere.
	 * 
	 * @param newIDInfermiere
	 *            the new id infermiere
	 * @return the string
	 */
	public String setIDInfermiere(String newIDInfermiere) {

		String errore = "";

		// Caso stringa vuota
		if (newIDInfermiere.isEmpty()) {
			errore += Fc.MessageBundle("MsgDialog810");
		} else {
			this.IDInfermiere = Integer.valueOf(newIDInfermiere);
		}

		// Restituzione stringa
		return errore;
	}

	/**
	 * Sets the data.
	 * 
	 * @param nuovaData
	 *            the nuova data
	 * @param formato
	 *            the formato
	 */
	public void setData(String nuovaData, String formato) {

		data = new java.sql.Date(DateFormatConverter.dateString2long(nuovaData,
				formato));
	}

	/**
	 * Sets the ora inizio.
	 * 
	 * @param nuovaOraInizio
	 *            the nuova ora inizio
	 * @param formato
	 *            the formato
	 */
	public void setOraInizio(String nuovaOraInizio, String formato) {

		oraInizio = new java.sql.Time(DateFormatConverter.dateString2long(
				nuovaOraInizio, formato));

	}

	/**
	 * Sets the ora fine.
	 * 
	 * @param nuovaOraInizio
	 *            the nuova ora inizio
	 * @return true, if successful
	 */
	public boolean setOraFine(String nuovaOraInizio) {
		try {
			// Tentativo di parsing
			SimpleDateFormat sdf = new SimpleDateFormat(formatoOraInput);
			oraInizio.setTime(sdf.parse(nuovaOraInizio).getTime());
			return true;
		} catch (ParseException e) {
			System.out.println(Fc.MessageBundle("MsgDialog840"));
			return false;

		}
	}

	/**
	 * Sets the ora fine fmt.
	 * 
	 * @param strOra
	 *            the str ora
	 * @param formatoIngresso
	 *            the formato ingresso
	 */
	public void setOraFineFmt(String strOra, String formatoIngresso) {
		oraFine.setTime(DateFormatConverter.dateString2long(strOra,
				formatoIngresso));
	}

	/**
	 * Sets the citta.
	 * 
	 * @param citta
	 *            the new citta
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * Sets the civico.
	 * 
	 * @param civico
	 *            the new civico
	 */
	public void setCivico(String civico) {
		this.civico = civico;
	}

	/**
	 * Restituisce data dell'intervento in un formato specifico (es.
	 * "dd/MM/yyyy")
	 * 
	 * @param formatoData
	 *            the formato data
	 * @return the data da formato
	 */
	public String getDataDaFormato(String formatoData) {
		return DateFormatConverter.long2dateString(data.getTime(), formatoData);
	}

	/**
	 * Restituisce l'ora di inizio dell'intervento in un formato specifico (es.
	 * "hh:mm:ss")
	 * 
	 * @param formatoOra
	 *            the formato ora
	 * @return the ora inizio da formato
	 */
	public String getOraInizioDaFormato(String formatoOra) {
		return DateFormatConverter.long2dateString(oraInizio.getTime(),
				formatoOra);
	}

	/**
	 * Restituisce l'ora di fine dell'intervento in un formato specifico (es.
	 * "hh:mm:ss")
	 * 
	 * @param formatoOra
	 *            the formato ora
	 * @return the ora fine da formato
	 */
	public String getOraFineDaFormato(String formatoOra) {
		return DateFormatConverter.long2dateString(oraFine.getTime(),
				formatoOra);
	}

	/**
	 * Adds the tipo intervento.
	 * 
	 * @param nuovoTipoIntervento
	 *            the nuovo tipo intervento
	 */
	public void addTipoIntervento(TOTipoIntervento nuovoTipoIntervento) {
		tipiIntervento.add(nuovoTipoIntervento);
	}

	/**
	 * Removes the tipo intervento.
	 * 
	 * @param indice
	 *            the indice
	 */
	public void removeTipoIntervento(int indice) {
		tipiIntervento.remove(indice);
	}

	/**
	 * Cancella tipi intervento.
	 */
	public void cancellaTipiIntervento() {
		tipiIntervento.clear();
	}

	/**
	 * Count tip interventi.
	 * 
	 * @return the int
	 */
	public int countTipInterventi() {
		return tipiIntervento.size();
	}

	/**
	 * Gets the tipo intervento.
	 * 
	 * @param n
	 *            the n
	 * @return the tipo intervento
	 */
	public TOTipoIntervento getTipoIntervento(int n) {
		try {
			return tipiIntervento.get(n);
		} catch (Exception e) {
			return null;
		}
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
		if (newID.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog850");
		}
		if (newID.length() > lunghezzaMassimaID) {
			errLog += Fc.MessageBundle("MsgDialog860");
		}
		return errLog;
	}

	/**
	 * Verifica validita nome.
	 * 
	 * @param newNome
	 *            the new nome
	 * @return the string
	 */
	public static String verificaValiditaNome(String newNome) {
		String errLog = "";
		if (newNome.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog870");
		}
		if (newNome.length() > lunghezzaMassimaNome) {
			errLog += Fc.MessageBundle("MsgDialog880") + " "
					+ lunghezzaMassimaNome + " "
					+ Fc.MessageBundle("MsgDialog890");
		}
		return errLog;
	}

	/**
	 * Verifica validita cognome.
	 * 
	 * @param newCognome
	 *            the new cognome
	 * @return the string
	 */
	public static String verificaValiditaCognome(String newCognome) {

		String errLog = "";
		if (newCognome.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog900");
		}
		if (newCognome.length() > lunghezzaMassimaCognome) {
			errLog += Fc.MessageBundle("MsgDialog910") + " "
					+ lunghezzaMassimaCognome + " "
					+ Fc.MessageBundle("MsgDialog920");
		}
		return errLog;
	}

	/**
	 * Verifica validita data.
	 * 
	 * @param data
	 *            the data
	 * @return the string
	 */
	public static String verificaValiditaData(String data) {
		SimpleDateFormat formato = new SimpleDateFormat(formatoDataInput);
		String errLog = "";
		try {
			formato.parse(data);
		} catch (ParseException e) {
			errLog += Fc.MessageBundle("MsgDialog1120") + data
					+ Fc.MessageBundle("MsgDialog1130") + " "
					+ formatoDataInput + ")";
		}
		return errLog;
	}

	/**
	 * Verifica validita note.
	 * 
	 * @param newNote
	 *            the new note
	 * @return the string
	 */
	public static String verificaValiditaNote(String newNote) {
		String errLog = "";
		if (newNote.length() > lunghezzaMassimaNote) {
			errLog += Fc.MessageBundle("MsgDialog1100") + " "
					+ lunghezzaMassimaNote + " "
					+ Fc.MessageBundle("MsgDialog1110");
		}
		return errLog;
	}

	/**
	 * Verifica validita tipo.
	 * 
	 * @param newTipo
	 *            the new tipo
	 * @return the string
	 */
	public static String verificaValiditaTipo(String newTipo) {
		String errLog = "";
		if (newTipo.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog1070");
		}
		if (newTipo.length() > lunghezzaMassimaTipo) {
			errLog += Fc.MessageBundle("MsgDialog1080") + " "
					+ lunghezzaMassimaTipo + " "
					+ Fc.MessageBundle("MsgDialog1090");
		}
		return errLog;
	}

	/**
	 * Verifica coerenza tipi.
	 * 
	 * @param array
	 *            the array
	 * @return the string
	 */
	public static String verificaCoerenzaTipi(ArrayList<TOTipoIntervento> array) {
		String errLog = "";
		if (array.size() < 1) {
			errLog += Fc.MessageBundle("MsgDialog1020");
		}
		for (int i = 0; i < array.size(); i++) {
			TOTipoIntervento t = array.get(i);
			if ("null".equals(t.getNome())) {
				errLog += Fc.MessageBundle("MsgDialog1030") + " "
						+ String.valueOf(i + 1)
						+ Fc.MessageBundle("MsgDialog1040");
			}
			errLog += errLog += TOIntervento.verificaValiditaTipo(t.getNome());
			errLog += TOIntervento.verificaValiditaNote(t.getNote());
		}
		return errLog;
	}

	/**
	 * Verifica validita citta.
	 * 
	 * @param newCitta
	 *            the new citta
	 * @return the string
	 */
	public static String verificaValiditaCitta(String newCitta) {
		String errLog = "";
		if (newCitta.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog930");
		}
		if (newCitta.length() > lunghezzaMassimaCitta) {
			errLog += Fc.MessageBundle("MsgDialog940") + " "
					+ lunghezzaMassimaCitta + " "
					+ Fc.MessageBundle("MsgDialog950");
		}
		return errLog;
	}

	/**
	 * Verifica validita civico.
	 * 
	 * @param newCivico
	 *            the new civico
	 * @return the string
	 */
	public static String verificaValiditaCivico(String newCivico) {
		String errLog = "";
		if (newCivico.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog960");
		}
		if (newCivico.length() > lunghezzaMassimaCivico) {
			errLog += Fc.MessageBundle("MsgDialog970") + " "
					+ lunghezzaMassimaCivico + " "
					+ Fc.MessageBundle("MsgDialog980");
		}
		return errLog;
	}

	/**
	 * Verifica validita cap.
	 * 
	 * @param newCap
	 *            the new cap
	 * @return the string
	 */
	public static String verificaValiditaCap(String newCap) {
		String errLog = "";
		if (newCap.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog990");
		}
		if (newCap.length() > lunghezzaMassimaCap) {
			errLog += Fc.MessageBundle("MsgDialog1000") + " "
					+ lunghezzaMassimaCap + " "
					+ Fc.MessageBundle("MsgDialog1010");
		}
		return errLog;
	}

	/**
	 * Verifica limitazioni data ora.
	 * 
	 * @param data
	 *            the data
	 * @param ora
	 *            the ora
	 * @return the string
	 */
	public static String verificaLimitazioniDataOra(String data, String ora) {
		if ("".equals(data) || "".equals(ora)) {
			return "";
		}
		int insertDay = Integer.parseInt(data.substring(0, 2));
		int insertMonth = Integer.parseInt(data.substring(3, 3 + 2));
		int insertYear = Integer.parseInt(data.substring(6, 6 + 4));
		int insertHour = Integer.parseInt(ora.substring(0, 2));
		int insertMinute = Integer.parseInt(ora.substring(3, 3 + 2));
		GregorianCalendar insertedGC = new GregorianCalendar();
		insertedGC.set(insertYear, insertMonth, insertDay, insertHour,
				insertMinute);
		GregorianCalendar nowGC = new GregorianCalendar();
		nowGC.add(Calendar.MONTH, 1);
		nowGC.add(Calendar.HOUR, limitazioneOraria);
		if (nowGC.after(insertedGC)) {
			return Fc.MessageBundle("MsgDialog1160") + " " + limitazioneOraria
					+ " " + Fc.MessageBundle("MsgDialog1170");
		} else {
			return "";
		}
		// data1.after(data2)
		// data1.before(data2)
	}

	/**
	 * Verifica validita ora.
	 * 
	 * @param ora
	 *            the ora
	 * @return the string
	 */
	public static String verificaValiditaOra(String ora) {
		SimpleDateFormat formato = new SimpleDateFormat(formatoOraInput);
		String errLog = "";
		try {
			formato.parse(ora);
		} catch (ParseException e) {
			errLog += Fc.MessageBundle("MsgDialog1140") + ora
					+ Fc.MessageBundle("MsgDialog1150") + " " + formatoOraInput
					+ ")";
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
		String stringaIntervento = Fc.MessageBundle("MsgDialog1180") + " "
				+ getID();
		stringaIntervento += Fc.MessageBundle("MsgDialog1190")
				+ getIDInfermiere();
		stringaIntervento += Fc.MessageBundle("MsgDialog1200")
				+ getIDPaziente();
		stringaIntervento += Fc.MessageBundle("MsgDialog1210") + " "
				+ getDataDaFormato("dd/MM/yyyy");
		stringaIntervento += Fc.MessageBundle("MsgDialog1220") + " "
				+ getOraInizioDaFormato("HH:mm");
		stringaIntervento += Fc.MessageBundle("MsgDialog1230") + " "
				+ getCivico();
		stringaIntervento += Fc.MessageBundle("MsgDialog1240") + " "
				+ getCitta();
		stringaIntervento += Fc.MessageBundle("MsgDialog1250") + " " + getCap()
				+ "\n\n";
		stringaIntervento += Fc.MessageBundle("MsgDialog1260") + " ";
		for (TOTipoIntervento t : tipiIntervento) {
			stringaIntervento += "\n" + t.toString() + "\n";
		}
		return stringaIntervento;
	}
}
