/*
 * 
 */
package adisys.server.business;

import adisys.server.presentation.frontController.Fc;
import java.util.ArrayList;

/**
 * The Class TOInterventoCompleto.
 * Classe entità che eredita attributi e metodi 
 * della classe Intervento e viene utilizzata per 
 * creare oggetti contenenti i dati di u n intervento 
 * completo (caricato dal file di journaling) comprendente 
 * le rilevazioni effettuate in automatico da ADISys Mobile
 * 
 */
public class TOInterventoCompleto extends TOIntervento {

	// Enumerazioni per verifiche
	/**
	 * The Enum StatoVerifica.
	 */
	public enum StatoVerifica {

		/** The non verificato. */
		nonVerificato,
		/** The verifica ok. */
		verificaOK,
		/** The anomalia. */
		anomalia
	}

	// Oggetti membro
	/** The paziente. */
	private TOPaziente paziente;

	/** The infermiere. */
	private TOInfermiere infermiere;

	/** The misura rilevata. */
	private String misuraRilevata;

	/** The log. */
	private ArrayList<TORilevazione> log;

	/** The stato verifica gps. */
	private StatoVerifica statoVerificaGPS;

	/** The stato verifica accelerometro. */
	private StatoVerifica statoVerificaAccelerometro;

	/** The note. */
	private String note;

	/**
	 * Instantiates a new TO intervento completo.
	 */
	public TOInterventoCompleto() {

		log = new ArrayList<>();
		statoVerificaGPS = StatoVerifica.nonVerificato;
		statoVerificaAccelerometro = StatoVerifica.nonVerificato;
	}

	/**
	 * Gets the paziente.
	 * 
	 * @return the paziente
	 */
	public TOPaziente getPaziente() {
		return paziente;
	}

	/**
	 * Gets the infermiere.
	 * 
	 * @return the infermiere
	 */
	public TOInfermiere getInfermiere() {
		return infermiere;
	}

	/**
	 * Gets the log.
	 * 
	 * @param indice
	 *            the indice
	 * @return the log
	 */
	public TORilevazione getLog(int indice) {
		if (indice >= 0 && indice < contaLog())
			return log.get(indice);
		else {
			System.out.println(Fc.MessageBundle("MsgDialog1270"));
			return null;
		}
	}

	/**
	 * Gets the misura rilevata.
	 * 
	 * @return the misura rilevata
	 */
	public String getMisuraRilevata() {
		return misuraRilevata;
	}

	/**
	 * Gets the stato verifica gps.
	 * 
	 * @return the stato verifica gps
	 */
	public StatoVerifica getStatoVerificaGPS() {
		return statoVerificaGPS;
	}

	/**
	 * Gets the stato verifica accelerometro.
	 * 
	 * @return the stato verifica accelerometro
	 */
	public StatoVerifica getStatoVerificaAccelerometro() {
		return statoVerificaAccelerometro;
	}

	/**
	 * Adds the log.
	 * 
	 * @param u
	 *            the u
	 * @return true, if successful
	 */
	public boolean addLog(TORilevazione u) {
		return log.add(u);
	}

	/**
	 * Conta log.
	 * 
	 * @return the int
	 */
	public int contaLog() {
		return log.size();
	}

	/**
	 * Sets the paziente.
	 * 
	 * @param newPaziente
	 *            the new paziente
	 * @return true, if successful
	 */
	public boolean setPaziente(TOPaziente newPaziente) {
		paziente = newPaziente;
		return true;
	}

	/**
	 * Sets the infermiere.
	 * 
	 * @param newInfermiere
	 *            the new infermiere
	 * @return true, if successful
	 */
	public boolean setInfermiere(TOInfermiere newInfermiere) {
		infermiere = newInfermiere;
		return true;
	}

	/**
	 * Sets the misura rilevata.
	 * 
	 * @param newMisuraRilevata
	 *            the new misura rilevata
	 * @return true, if successful
	 */
	public boolean setMisuraRilevata(String newMisuraRilevata) {
		misuraRilevata = newMisuraRilevata;
		return true;
	}

	/**
	 * Sets the stato verifica gps.
	 * 
	 * @param statoVerificaGPS
	 *            the new stato verifica gps
	 */
	public void setStatoVerificaGPS(StatoVerifica statoVerificaGPS) {
		this.statoVerificaGPS = statoVerificaGPS;
	}

	/**
	 * Sets the stato verifica accelerometro.
	 * 
	 * @param statoVerificaAccelerometro
	 *            the new stato verifica accelerometro
	 */
	public void setStatoVerificaAccelerometro(
			StatoVerifica statoVerificaAccelerometro) {
		this.statoVerificaAccelerometro = statoVerificaAccelerometro;
	}

	/**
	 * Gets the note.
	 * 
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 * 
	 * @param note
	 *            the new note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	// @Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see adisys.server.business.TOIntervento#toString()
	 */
	public String toString() {
		String stringaIntervento = Fc.MessageBundle("MsgDialog1280") + " "
				+ getID();
		stringaIntervento += Fc.MessageBundle("MsgDialog1290")
				+ getIDInfermiere();
		stringaIntervento += "\n" + getPaziente().toString();

		stringaIntervento += Fc.MessageBundle("MsgDialog1300") + "  "
				+ getDataDaFormato("dd/MM/yyyy");
		stringaIntervento += Fc.MessageBundle("MsgDialog1310") + " "
				+ getOraInizioDaFormato("HH:mm");
		stringaIntervento += "\n- Ora fine intervento: "
				+ getOraFineDaFormato("HH:mm");
		stringaIntervento += Fc.MessageBundle("MsgDialog1320") + " "
				+ getCivico();
		stringaIntervento += Fc.MessageBundle("MsgDialog1330") + " "
				+ getCitta();
		stringaIntervento += Fc.MessageBundle("MsgDialog1340") + " " + getCap()
				+ "\n\n";

		stringaIntervento += Fc.MessageBundle("MsgDialog1350") + " ";
		for (TOTipoIntervento t : tipiIntervento)
			stringaIntervento += "\n" + t.toString() + "\n";

		return stringaIntervento;
	}
}
