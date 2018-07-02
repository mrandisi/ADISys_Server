/*
 * 
 */
package adisys.server.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * The Class DateFormatConverter.
 * Classe con membri statici utilizzata per il parsing 
 * delle date da stringhe e la composizione di stringhe 
 * da oggetti GregorianCaledar, Date e DateTime(SQL) che 
 * hanno specifiche diverse
 * 
 */
public class DateFormatConverter {

	/**
	 * Cambia il formato di una stringa contenente una data.
	 * 
	 * @param dataOra
	 *            the data ora
	 * @param formatoIngresso
	 *            the formato ingresso
	 * @param formatoUscita
	 *            the formato uscita
	 * @return restituisce un Timestamp in caso di successo del parsing,
	 *         altrimenti <b>null</b>
	 * @category utility
	 */
	public static String cambiaFormato(String dataOra, String formatoIngresso,
			String formatoUscita) {
		SimpleDateFormat dataOraInput = new SimpleDateFormat(formatoIngresso);
		SimpleDateFormat dataOraOutput = new SimpleDateFormat(formatoUscita);

		try {

			// trace
			System.out.format(
					"\nFormattazione stringa '%s' da formato '%s' a '%s'",
					dataOra, formatoIngresso, formatoUscita);

			java.util.Date data = dataOraInput.parse(dataOra);
			String stringaUscita = dataOraOutput.format(data);

			// trace
			System.out.format("\nStringa ottenuta: '%s'", stringaUscita);

			return stringaUscita;

		} catch (ParseException e) {

			// Avviso parsing fallito
			System.out
					.println("DateFormatConverter -> ERRORE nel parsing. Controllare i formati delle date");
			return null;
		}
	}

	/**
	 * Date string2long.
	 * 
	 * @param dataOra
	 *            the data ora
	 * @param formatoIngresso
	 *            the formato ingresso
	 * @return the long
	 */
	@SuppressWarnings("deprecation")
	public static long dateString2long(String dataOra, String formatoIngresso) {

		SimpleDateFormat dataOraInput = new SimpleDateFormat(formatoIngresso);

		try {
			// Trace
			System.out.println();
			System.out.println("Inserimento data/ora " + dataOra
					+ " nel formato: " + formatoIngresso);

			java.util.Date data = dataOraInput.parse(dataOra);

			// Trace
			System.out.println("Parsing effettuato, data ottenuta: "
					+ data.toGMTString());

			return data.getTime();

		} catch (ParseException e) {
			// Avviso parsing fallito
			System.out
					.println("DateFormatConverter -> ERRORE nel parsing. Controllare i formati delle date");
			return 0;
		}
	}

	/**
	 * Long2date string.
	 * 
	 * @param dataOra
	 *            the data ora
	 * @param formatoUscita
	 *            the formato uscita
	 * @return the string
	 */
	public static String long2dateString(long dataOra, String formatoUscita) {
		// Creazione formattatore
		SimpleDateFormat dataOraOutput = new SimpleDateFormat(formatoUscita);

		// Creazione oggetto data
		java.util.Date data = new java.util.Date(dataOra);

		// Formattazione e output
		String stringaUscita = dataOraOutput.format(data);

		return stringaUscita;
	}

	/**
	 * Parseable.
	 * 
	 * @param dataOra
	 *            the data ora
	 * @param formato
	 *            the formato
	 * @return true, if successful
	 */
	public static boolean parseable(String dataOra, String formato) {
		SimpleDateFormat dataOraInput = new SimpleDateFormat(formato);

		try {
			dataOraInput.parse(dataOra);
			return true;

		} catch (ParseException e) {
			// Avviso parsing fallito
			System.out
					.println("DateFormatConverter -> ERRORE nel parsing. Controllare i formati delle date");
			return false;
		}
	}

	/**
	 * Oggi.
	 * 
	 * @return the long
	 */
	public static long oggi() {
		GregorianCalendar today = new GregorianCalendar();
		return today.getTimeInMillis();
	}
}
