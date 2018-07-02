package mobile.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TipiIntervento implements Parcelable {

	private String Patologia = "n.d.";
	private String Nome = "n.d.";
	private String Note = "n.d.";
	private String Misura = "n.d.";
	private String Tempo = "00:00:00";

	TipiIntervento() {
		;
	};

	TipiIntervento(Parcel r) {
		this();
		this.Patologia = r.readString();
		this.Nome = r.readString();
		this.Note = r.readString();
		this.Misura = r.readString();
		this.Tempo = r.readString();
	}

	public String getPatologia() {
		return Patologia;
	}

	public String getNome() {
		return Nome;
	}

	public String getNote() {
		return Note;
	}

	public String getMisura() {
		return Misura;
	}

	public String getTempo() {
		return Tempo;
	}

	public void setPatologia(String patologia) {
		Patologia = patologia;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public void setNote(String note) {
		Note = note;
	}

	public void setMisura(String misura) {
		Misura = misura;
	}

	public void setTempo(String tempo) {
		Tempo = tempo;
	}

	/*
	 * public void readFromParcel(Parcel r) { Nome = r.readString(); Note =
	 * r.readString(); Misura= r.readString(); Tempo= r.readString(); }
	 */
	/*
	 * public void setDettaglio(String tempo,String misura,String note,String
	 * tipo) { Tempo = tempo; Misura=misura; Note=note; Nome=tipo; }
	 */
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel p, int arg1) {
		p.writeString(Patologia);
		p.writeString(Nome);
		p.writeString(Note);
		p.writeString(Misura);
		p.writeString(Tempo);
	}

	public static final Parcelable.Creator<TipiIntervento> CREATOR = new Parcelable.Creator<TipiIntervento>() {
		public TipiIntervento createFromParcel(Parcel in) {
			return new TipiIntervento(in);
		}

		public TipiIntervento[] newArray(int size) {
			return new TipiIntervento[size];
		}
	};

}
