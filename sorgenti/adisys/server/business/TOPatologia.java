/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.business;

import adisys.server.presentation.frontController.Fc;

/**
 * The Class TOPatologia.
 * Classe entità utilizzata per creare oggetti contenenti i dati di una patologia
 * 
 */
public class TOPatologia {

	/** The id. */
	private int ID;

	/** The nome_patologia. */
	private String nome_patologia;

	/** The gravita. */
	private String gravita;

	/** The Constant LUNGHEZZAMASSIMAID. */
	private static final int LUNGHEZZAMASSIMAID = 6;

	/** The Constant LUNGHEZZAMASSIMANOMEPATOLOGIA. */
	private static final int LUNGHEZZAMASSIMANOMEPATOLOGIA = 20;

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
		return nome_patologia;
	}

	/**
	 * Gets the gravita.
	 * 
	 * @return the cognome
	 */
	public String getGravita() {
		return gravita;
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
		this.nome_patologia = nome;
	}

	/**
	 * Sets the gravita.
	 * 
	 * @param gravita
	 *            the new gravita
	 */
	public void setGravita(String gravita) {
		this.gravita = gravita;
	}

	/**
	 * Verifica id.
	 * 
	 * @param IDPatologia
	 *            the ID patologia
	 * @return una stringa vuota se la verifica è andata a buon fine, la stringa
	 *         di errore nel caso opposto
	 */
	public static String verificaIDPatologia(String IDPatologia) {
		String risultato = new String();

		// Verifica non vuoto
		if (IDPatologia.isEmpty())
			risultato += Fc.MessageBundle("MsgDialog1390");

		// Verifica lunghezza
		if (IDPatologia.length() > LUNGHEZZAMASSIMAID)
			risultato += Fc.MessageBundle("MsgDialog1400") + " "
					+ LUNGHEZZAMASSIMAID + " "
					+ Fc.MessageBundle("MsgDialog1410");

		// Restituzione risultato verifica
		return risultato;
	}

	/**
	 * Verifica coerenza del nome.
	 * 
	 * @param nomePatologia
	 *            the nome patologia
	 * @return una stringa vuota se la verifica è andata a buon fine, la stringa
	 *         di errore nel caso opposto
	 */
	public static String verificaNomePatologia(String nomePatologia) {
		String risultato = new String();

		// Verifica non vuoto
		if (nomePatologia.isEmpty())
			risultato += Fc.MessageBundle("MsgDialog1420");

		// Verifica lunghezza
		if (nomePatologia.length() > LUNGHEZZAMASSIMANOMEPATOLOGIA)
			risultato += Fc.MessageBundle("MsgDialog1430") + " "
					+ LUNGHEZZAMASSIMANOMEPATOLOGIA + " "
					+ Fc.MessageBundle("MsgDialog1440");

		// Restituzione risultato verifica
		return risultato;
	}

	/**
	 * Verifica coerenza della gravita.
	 * 
	 * @param gravita
	 *            the gravita
	 * @return una stringa vuota se la verifica è andata a buon fine, la stringa
	 *         di errore nel caso opposto
	 */
	public static String verificaGravita(String gravita) {
		String risultato = new String();
		if (gravita.isEmpty())
			risultato += Fc.MessageBundle("MsgDialog1420");
		return risultato;
	}

	// @Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String stringaPatologia = new String();
		stringaPatologia += Fc.MessageBundle("MsgDialog1450") + " " + getID();
		stringaPatologia += Fc.MessageBundle("MsgDialog1460") + " " + getNome();
		stringaPatologia += Fc.MessageBundle("MsgDialog1470") + " " + getGravita();

		return stringaPatologia;
	}
}
