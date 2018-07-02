/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.business;

import adisys.server.presentation.frontController.Fc;

/**
 * The Class TOInfermiere.
 * Classe entità utilizzata per creare oggetti contenenti i dati di un infermiere
 * 
 */
public class TOInfermiere {

	/** The Constant LUNGHEZZAMASSIMAID. */
	private static final int LUNGHEZZAMASSIMAID = 5;

	/** The Constant LUNGHEZZAMASSIMANOME. */
	private static final int LUNGHEZZAMASSIMANOME = 20;

	/** The Constant LUNGHEZZAMASSIMACOGNOME. */
	private static final int LUNGHEZZAMASSIMACOGNOME = 20;

	/** The id. */
	private int ID;

	/** The nome. */
	private String nome;

	/** The cognome. */
	private String cognome;

	/** The num interventi. */
	private int numInterventi;

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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Gets the cognome.
	 * 
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Gets the num interventi.
	 * 
	 * @return the numInterventi
	 */
	public int getNumInterventi() {
		return numInterventi;
	}

	/**
	 * Sets the id.
	 * 
	 * @param ID
	 *            the ID to set
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
	 * Sets the num interventi.
	 * 
	 * @param numInterventi
	 *            the cognome to set
	 */
	public void setNumInterventi(int numInterventi) {
		this.numInterventi = numInterventi;
	}

	/**
	 * Verifica coerenza dell'ID.
	 * 
	 * @param newID
	 *            - l'ID da controllare
	 * @return una stringa vuota se la verifica è andata a buon fine, la stringa
	 *         di errore nel caso opposto
	 */
	public static String verificaCoerenzaID(String newID) {
		String errLog = "";

		// ID NON VUOTO
		if (newID.isEmpty()) {
			errLog += Fc.MessageBundle("MsgDialog680");
		}

		// Controllo lunghezza massima
		if (newID.length() > LUNGHEZZAMASSIMAID) {
			errLog += Fc.MessageBundle("MsgDialog690");
		}

		// controllo ID numerico - Saltato, operazione effettuata in
		// automatico nell'interfaccia
		return errLog;
	}

	/**
	 * Verifica coerenza del nome.
	 * 
	 * @param nomeProva
	 *            - il nome da controllare
	 * @return una stringa vuota se la verifica è andata a buon fine, la stringa
	 *         di errore nel caso opposto
	 */
	public static String verificaNome(String nomeProva) {
		String risultato = new String();

		// Verifica non vuoto
		if (nomeProva.isEmpty()) {
			risultato += Fc.MessageBundle("MsgDialog700");
		}

		// Verifica lunghezza
		if (nomeProva.length() > LUNGHEZZAMASSIMANOME) {
			risultato += Fc.MessageBundle("MsgDialog710") + " "
					+ LUNGHEZZAMASSIMANOME + " "
					+ Fc.MessageBundle("MsgDialog720");
		}

		// Restituzione risultato verifica
		return risultato;

	}

	/**
	 * Verifica coerenza del Cognome.
	 * 
	 * @param cognomeProva
	 *            - il cognome da controllare
	 * @return una stringa vuota se la verifica è andata a buon fine, la stringa
	 *         di errore nel caso opposto
	 */
	public static String verificaCognome(String cognomeProva) {
		String risultato = new String();

		// Verifica non vuoto
		if (cognomeProva.isEmpty()) {
			risultato += Fc.MessageBundle("MsgDialog730");
		}

		// Verifica lunghezza
		if (cognomeProva.length() > LUNGHEZZAMASSIMACOGNOME) {
			risultato += Fc.MessageBundle("MsgDialog740") + " "
					+ LUNGHEZZAMASSIMACOGNOME + " "
					+ Fc.MessageBundle("MsgDialog750");
		}

		// Restituzione risultato verifica
		return risultato;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String stringaInfermiere = new String();
		stringaInfermiere += Fc.MessageBundle("MsgDialog760") + " " + getID();
		stringaInfermiere += Fc.MessageBundle("MsgDialog770") + " " + getNome();
		stringaInfermiere += Fc.MessageBundle("MsgDialog780") + " "
				+ getCognome();

		return stringaInfermiere;
	}
}
