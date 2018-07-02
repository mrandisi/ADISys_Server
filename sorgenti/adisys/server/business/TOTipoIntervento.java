/*
 * 
 */
package adisys.server.business;

import adisys.server.presentation.frontController.Fc;

/**
 * The Class TOTipoIntervento.
 * Classe entità utilizzata per creare oggetti contenenti i dati di un tipo Intervento
 * 
 */
public class TOTipoIntervento {

	/** The patologia. */
	private String patologia;

	/** The nome. */
	private String nome;

	/** The valore rilevato. */
	private String valoreRilevato;

	/** The tempo intervento. */
	private String tempoIntervento;

	/** The note. */
	private String note;

	/**
	 * Instantiates a new TO tipo intervento.
	 */
	public TOTipoIntervento() {
		patologia = "";
		nome = "";
		valoreRilevato = "";
		tempoIntervento = "";
		note = "";
	}

	/**
	 * Instantiates a new TO tipo intervento.
	 * 
	 * @param newPatologia
	 *            the new patologia
	 * @param newNome
	 *            the new nome
	 * @param newNote
	 *            the new note
	 */
	public TOTipoIntervento(String newPatologia, String newNome, String newNote) {
		patologia = newPatologia;
		nome = newNome;
		valoreRilevato = "";
		tempoIntervento = "";
		note = newNote;
	}

	/**
	 * Gets the patologia.
	 * 
	 * @return the patologia
	 */
	public String getPatologia() {
		return patologia;
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
	 * Gets the valore rilevato.
	 * 
	 * @return the valore rilevato
	 */
	public String getValoreRilevato() {
		return valoreRilevato;
	}

	/**
	 * Gets the tempo intervento.
	 * 
	 * @return the tempo intervento
	 */
	public String getTempoIntervento() {
		return tempoIntervento;
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
	 * Sets the patologia.
	 * 
	 * @param patologia
	 *            the new patologia
	 */
	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}

	/**
	 * Sets the nome.
	 * 
	 * @param nome
	 *            the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Sets the valore rilevato.
	 * 
	 * @param valoreRilevato
	 *            the new valore rilevato
	 */
	public void setValoreRilevato(String valoreRilevato) {
		this.valoreRilevato = valoreRilevato;
	}

	/**
	 * Sets the tempo intervento.
	 * 
	 * @param tempoIntervento
	 *            the new tempo intervento
	 */
	public void setTempoIntervento(String tempoIntervento) {
		this.tempoIntervento = tempoIntervento;
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
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String stringaTipo = Fc.MessageBundle("MsgDialog1850") + " "
				+ getPatologia();
		stringaTipo += Fc.MessageBundle("MsgDialog1860") + " " + getNome();
		stringaTipo += Fc.MessageBundle("MsgDialog1890") + " " + getNote();
		stringaTipo += Fc.MessageBundle("MsgDialog1870") + " "
				+ getValoreRilevato();
		stringaTipo += Fc.MessageBundle("MsgDialog1880") + " "
				+ getTempoIntervento();

		return stringaTipo;
	}
}
