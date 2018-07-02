package mobile.control;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import android.content.Context;
import android.util.Log;

import mobile.model.Intervento;

public class Controller {

	public static void leggiFile(Context context) {

		mobile.model.GestoreAttivita.leggi(context);
	}

	public static void scriviAttivita(Intervento inter) {

		try {
			mobile.model.GestoreAttivita.scrivi(inter);
		} catch (Exception e) {
			Log.i("Errore", "Errore Scrittura!");
		}
	}

	public static void chiudiAttivita() {

		try {
			mobile.model.GestoreAttivita.chiudiFile();
		} catch (Exception e) {
			Log.i("Errore", "Errore Scrittura!");
		}
	}

	public static String Adesso(String formatoUscita) {
		GregorianCalendar gc = new GregorianCalendar();
		Long dataOra = gc.getTimeInMillis();

		// Creazione formattatore
		SimpleDateFormat dataOraOutput = new SimpleDateFormat(formatoUscita);

		// Creazione oggetto data
		java.util.Date data = new java.util.Date(dataOra);

		// Formattazione e output
		String stringaUscita = dataOraOutput.format(data);

		return stringaUscita;
	}

}
