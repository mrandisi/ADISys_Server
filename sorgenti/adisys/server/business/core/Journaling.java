/*
 * 
 */
package adisys.server.business.core;

import adisys.server.business.TOInterventoCompleto;
import adisys.server.business.TORilevazione;
import adisys.server.business.TOInterventoCompleto.StatoVerifica;
import adisys.server.integration.StrutturaInterscambio;
import adisys.server.utility.DateFormatConverter;
import adisys.server.utility.UIFeedbackException;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * The Class Journaling.
 * Classe incaricata dell’importazione del file di journaling
 * 
 */
public class Journaling {

	// Parametri di controllo
	/** The Constant DISTANZA_MAX_DOMICILIO. */
	private static final double DISTANZA_MAX_DOMICILIO = 30; // metri

	/** The Constant TEMPO_MAX_ACCEL_NULLO. */
	private static final long TEMPO_MAX_ACCEL_NULLO = 1800000; // millisecondi
																// (30 min x 60
																// sec/min *
																// 1000 ms/sec)

	// Parametri di importazione del file
	/** The Constant CARTELLA_JOURNALING. */
	private static final String CARTELLA_JOURNALING = "Importazione";

	/** The Constant ESTENSIONE_JOURNALING. */
	private static final String ESTENSIONE_JOURNALING = "m.xml";

	/** The Constant SCHEMA_XSD_IMP. */
	private static final String SCHEMA_XSD_IMP = "SchemaXSD/XSDImp.xsd";

	// Lista degli interventi per l'analisi dei dati
	/** The lista interventi. */
	private static ArrayList<TOInterventoCompleto> listaInterventi;

	/**
	 * Gets the lista interventi.
	 * 
	 * @return the lista interventi
	 */
	public static ArrayList<TOInterventoCompleto> getListaInterventi() {
		return listaInterventi;
	}

	/**
	 * Carica file.
	 * 
	 * @param nomeFile
	 *            the nome file
	 * @return the string
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public static String caricaFile(String nomeFile) throws UIFeedbackException {
		String percorsoJournaling = CARTELLA_JOURNALING + "/" + nomeFile;

		String errLog = "";

		// CARICAMENTO FILE
		StrutturaInterscambio s = new StrutturaInterscambio();
		errLog += "\n" + s.caricaDaFileXML(percorsoJournaling, SCHEMA_XSD_IMP);

		// Aggiunta interventi (completi) alla lista
		listaInterventi = s.getInterventiCompleti();
		if (listaInterventi.isEmpty())
			errLog += "\n- ERRORE LETTURA FILE DI JOURNALING.";

		// Verifica GPS
		verificaGPS();

		// Verifica Accelerometro
		verificaAccelerometro();

		return errLog;
	}

	/**
	 * Verifica gps.
	 */
	public static void verificaGPS() {
		for (TOInterventoCompleto i : listaInterventi) {
			/*
			 * Ciclo che legge tutti i log dopo il primo e liconfronta con il
			 * primo per la verifica del GPSE' inutile considerare il primo log
			 * (indiceLog=0) perche' al fine della verificanon contiene dati
			 * rilevanti.
			 */
			for (int indiceLog = 1; indiceLog < i.contaLog(); indiceLog++) {
				// Allarme se:
				// GPS oltre 30m dal domicilio del paziente

				// Lettura prima rilevazione
				TORilevazione primoLog = i.getLog(0);

				// Lettura rilevazione attuale
				TORilevazione unitaLog = i.getLog(indiceLog);

				// Calcolo della distanza
				// Punto 0
				double Lat0 = primoLog.getGpsLatitude();
				double Lon0 = primoLog.getGpsLongitude();
				double Alt0 = primoLog.getGpsAltitude();
				// Dato disponibile per il calcolo dell'errore
				// (decommentare)
				// double Acc0 = primoLog.getGpsAccuracy();

				// Punto n
				double LatN = unitaLog.getGpsLatitude();
				double LonN = unitaLog.getGpsLongitude();
				double AltN = unitaLog.getGpsAltitude();
				// Dato disponibile per il calcolo dell'errore
				// (decommentare)
				// double AccN = unitaLog.getGpsAccuracy();

				// Calcolo distanza
				double distanza;
				distanza = Math.sqrt(Math.pow((LatN - Lat0), 2)
						* Math.pow((LonN - Lon0), 2)
						* Math.pow((AltN - Alt0), 2));

				// Verifica
				if (distanza > DISTANZA_MAX_DOMICILIO) {
					i.setStatoVerificaGPS(StatoVerifica.anomalia);
					// Trace
					String msg = "Verifica -> Rilevata anomalia GPS:";
					msg += " distanza di " + distanza;
					msg += " metri (max consentito " + DISTANZA_MAX_DOMICILIO
							+ " metri)";

					System.out.println(msg);

				}
			}

			// Se al termine del ciclo la verifica non ha dato anomalie si
			// imposta OK
			if (i.getStatoVerificaGPS() == StatoVerifica.nonVerificato)
				i.setStatoVerificaGPS(StatoVerifica.verificaOK);

		}
	}

	/**
	 * Verifica accelerometro.
	 */
	public static void verificaAccelerometro() {
		for (TOInterventoCompleto i : listaInterventi) {
			/* Ciclo che legge tutti i log */

			// Dichiarazione cursori per timestamp
			Long primo = null;
			Long ultimo = null;

			for (int indiceLog = 0; indiceLog < i.contaLog(); indiceLog++) {
				// log attuale
				TORilevazione logCorrente = i.getLog(indiceLog);

				// Verifica accelerometro fermo
				boolean accelerometroFermo = (logCorrente.getAccX() == 0)
						&& (logCorrente.getAccY() == 0)
						&& (logCorrente.getAccZ() == 0);

				if (accelerometroFermo) {

					if (primo == null) {
						/*
						 * Se l'accelerometro e' fermo per la prima volta viene
						 * settato il primo cursore per il calcolo del tempo
						 * trascorso
						 */
						primo = logCorrente.getTimestamp().getTime();
					} else {
						/*
						 * Se l'accelerometro era gia' fermo durante l'ultima
						 * rilevazioneviene settata la rilevazione attuale come
						 * secondo valore peril calcolo del tempo trascorso e se
						 * questo e' superiore allasoglia viene impostato
						 * l'alert
						 */
						ultimo = logCorrente.getTimestamp().getTime();

						if (ultimo - primo > TEMPO_MAX_ACCEL_NULLO) {
							i.setStatoVerificaAccelerometro(StatoVerifica.anomalia);
							// Trace
							String msg = "Verifica -> Rilevata anomalia accelerometro:";
							msg += " nullo dalle "
									+ DateFormatConverter.long2dateString(
											primo, "HH:mm");
							msg += " alle "
									+ DateFormatConverter.long2dateString(
											ultimo, "HH:mm");

							System.out.println(msg);
						}
					}
				} else {
					// L'accelerometro non e' fermo, vengono resettati i cursori
					primo = null;
					ultimo = null;
				}

			}
			// Se al termine del ciclo la verifica non ha dato anomalie si
			// imposta OK
			if (i.getStatoVerificaAccelerometro() == StatoVerifica.nonVerificato)
				i.setStatoVerificaAccelerometro(StatoVerifica.verificaOK);
		}
	}

	/**
	 * Lista file journaling.
	 * 
	 * @return the string[]
	 */
	public static String[] listaFileJournaling() {
		// Creazione oggetto File
		File cartellaJournaling = new File(CARTELLA_JOURNALING);

		// Controllo esistenza percorso
		if (cartellaJournaling.exists()) {
			// Cartella esistente.
			// Creazione di un filtro
			FilenameFilter filtro = new FilenameFilter() {
				@Override
				public boolean accept(File cartella, String nomeFile) {
					if (nomeFile.endsWith(ESTENSIONE_JOURNALING))
						return true;
					else
						return false;
				}

			};

			// Estrazione lista file
			return cartellaJournaling.list(filtro);
		} else {
			// Cartlla inesistente
			System.out
					.println("Verifica -> Impossibile individuare la cartella ");
			return null;
		}

	}

	/**
	 * Gets the intervento.
	 * 
	 * @param indice
	 *            the indice
	 * @return the intervento
	 */
	public static TOInterventoCompleto getIntervento(int indice) {
		return listaInterventi.get(indice);
	}
}
