package mobile.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class Intervento implements Parcelable {

	private String ID = "";
	private String idPaziente = "";
	private String nome = "";
	private String cognome = "";
	private String dataNascita= "";
	private String citta = "";
	private String nCivico = "";
	private String note = "";
	private String data = "";
	private String ora = "";
	private String idInf = "";
	private String oraInizio = "";
	private String oraFine = "";
	private String CAP = "";
	private ArrayList<TipiIntervento> tipoIntervento;
	private ArrayList<String> cellulari;

	/**
	 * Standard basic constructor for non-parcel object creation
	 * 
	 * @throws Exception
	 */
	public Intervento() {
		tipoIntervento = new ArrayList<TipiIntervento>();
		cellulari = new ArrayList<String>();
	};

	/**
	 * 
	 * Constructor to use when re-constructing object from a parcel
	 * 
	 * @param p
	 *            a parcel from which to read this object
	 */
	public Intervento(Parcel p) {
		readFromParcel(p);
	}

	/**
	 * 
	 * Called from the constructor to create this object from a parcel.
	 * 
	 * @param in
	 *            parcel from which to re-create object
	 */
	private void readFromParcel(Parcel p) {

		this.nome = p.readString();
		this.cognome = p.readString();
		this.dataNascita = p.readString();
		this.ora = p.readString();
		this.citta = p.readString();
		this.nCivico = p.readString();
		this.note = p.readString();
		this.data = p.readString();
		this.idInf = p.readString();
		this.oraInizio = p.readString();
		this.oraFine = p.readString();
		this.CAP = p.readString();

		// readParcelable needs the ClassLoader
		// but that can be picked up from the class
		// This will solve the BadParcelableException
		// because of ClassNotFoundException
		this.tipoIntervento = p.readParcelable(TipiIntervento.class
				.getClassLoader());
		p.readStringList(cellulari);
	}

	public void writeToParcel(Parcel p, int arg1) {
		p.writeString(nome);
		p.writeString(cognome);
		p.writeString(dataNascita);
		p.writeString(ora);
		p.writeString(citta);
		p.writeString(nCivico);
		p.writeString(note);
		p.writeString(data);
		p.writeString(idInf);
		p.writeStringList(cellulari);
		p.writeList(tipoIntervento);

	}

	public String getCitta() {
		return citta;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public void setCitta(String cittapar) {
		this.citta = cittapar;
	}

	public void setNCivico(String nCivicopar) {
		this.nCivico = nCivicopar;
	}

	public void setData(String datapar) {
		this.data = datapar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita (String dataN){
		this.dataNascita = dataN;
	}
	

	public String getnCivico() {
		return nCivico;
	}

	public String getData() {
		return data;
	}

	public ArrayList<TipiIntervento> getTipoIntervento() {
		return tipoIntervento;
	}

	public void setTipoIntervento(TipiIntervento tipIntervento) {
		this.tipoIntervento.add(tipIntervento);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getOraInizio() {
		return oraInizio;
	}

	public String getOraFine() {
		return oraFine;
	}

	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}

	public void setOraFine(String oraFine) {
		this.oraFine = oraFine;
	}

	public String getIdInf() {
		return idInf;
	}

	public void setIdInf(String idInf) {
		this.idInf = idInf;
	}

	public String getCAP() {
		return CAP;
	}

	public void setCAP(String cAP) {
		CAP = cAP;
	}

	public String getIdPaziente() {
		return idPaziente;
	}

	public void setIdPaziente(String idPaziente) {
		this.idPaziente = idPaziente;
	}

	public List<String> getCellulari() {
		return cellulari;
	}

	public void setCellulari(ArrayList<String> cellulari) {
		this.cellulari = cellulari;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 
	 * This field is needed for Android to be able to create new objects,
	 * individually or as arrays.
	 * 
	 * This also means that you can use use the default constructor to create
	 * the object and use another method to hyrdate it as necessary.
	 * 
	 * I just find it easier to use the constructor. It makes sense for the way
	 * my brain thinks ;-)
	 * 
	 */
	public static final Parcelable.Creator<Intervento> CREATOR = new Parcelable.Creator<Intervento>() {
		public Intervento createFromParcel(Parcel in) {
			return new Intervento(in);
		}

		public Intervento[] newArray(int size) {
			return new Intervento[size];
		}
	};

}
