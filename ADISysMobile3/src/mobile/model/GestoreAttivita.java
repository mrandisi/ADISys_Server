package mobile.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import mobile.control.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;
import file.log.GestoreLog;
import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;

public class GestoreAttivita {
	private static String FS = File.separator;
	private static String sPath = Environment.getExternalStorageDirectory().getAbsolutePath()+FS+"ADISysMobile3"+FS;
	private static File exportDirectory = new File(sPath+"Esportazione");
	private static File importDirectory = new File(sPath+"Importazione");
	private static ArrayList<Intervento> list;
	private static Context contesto;
	protected static int ind = 0;
	private static FileWriter fw;
	private static String formatoData = "yyyy-MM-dd";
	private static String stringaFile = "";
	private static String stringaFileEsportazione = "";
	private static File fileExport;
	private static File fileImport;

	public static void leggi(Context context) {
		contesto = context;
		if (list != null)
			list = ListaInterventi.getListaInterventi();
		else
			ricevi();
	}

	public static void scrivi(Intervento interScrittura) {
		try {
			scriviIntervento(interScrittura);
		} catch (Exception e) {
			Toast.makeText(contesto, "Attenzione errore di scrittura!\n"+e,
					Toast.LENGTH_SHORT).show();
		}
	}

	private static void ricevi() {
		try {
			importDirectory.mkdir();
			ricerca();
			fileImport = new File(importDirectory + File.separator
					+ stringaFile);
			
			if (fileImport.exists()) {
				FileInputStream fDir = new FileInputStream(fileImport);
				letturaXml(fDir);
			}
		}

		catch (Exception e) {
			Toast.makeText(contesto, "Attenzione errore di lettura!\n"+e,
					Toast.LENGTH_SHORT).show();
		}
	}

	private static void deleteDir(File dir) {

		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				deleteDir(new File(dir, children[i]));
			}
		}
		// Una volta che la directory è vuota posso cancellarla
		dir.delete();
	}

	private static void ricerca() {
		try {
			if (importDirectory.exists()) {

				String[] listaFile;
				listaFile = importDirectory.list();
				for (int i = 0; i < listaFile.length; i++) {
					if ((listaFile[i].endsWith("s.xml")) == true) {
						stringaFile = listaFile[i];
					}
				}
			}
		} catch (Exception e) {
			Toast.makeText(contesto, "Attenzione errore di lettura!",
					Toast.LENGTH_SHORT).show();
		}
	}

	private static void ricercaEsportazione() {
		try {
			if (exportDirectory.exists()) {

				String[] listaFile;
				// String data=Controller.Adesso(formatoData);
				listaFile = exportDirectory.list();
				for (int i = 0; i < listaFile.length; i++) {
					if ((listaFile[i].endsWith("m.xml")) == true) {
						stringaFileEsportazione = listaFile[i];
					}
				}
			}
		} catch (Exception e) {
			Toast.makeText(contesto, "Attenzione errore di scrittura!\n"+e,
					Toast.LENGTH_SHORT).show();
		}
	}

	public static void letturaXml(FileInputStream xmlFile) {

		// Lista oggetti intervento
		list = ListaInterventi.getListaInterventi();

		// Creazione documento DOM
		DocumentBuilderFactory fattoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder costruttore;
		Document documento;
		try {
			costruttore = fattoria.newDocumentBuilder();
			// SOSTITUIRE AL NOME DEL FILE LA STRINGA CON IL FILE DA CARICARE
			documento = costruttore.parse(fileImport);

			// Lettura dati
			System.out.println("URI:" + documento.getDocumentURI());

			// Interventi
			Element root = documento.getDocumentElement();

			// Ciclo per intervento
			NodeList listaInterventi = root.getChildNodes();
			int numInterventi = listaInterventi.getLength();
			System.out.println("Numero interventi" + " -> " + numInterventi);

			for (int contInterv = 0; contInterv < numInterventi; contInterv++) {

				// Creazione intervento
				Intervento i = new Intervento();

				Element interventoCorrente = (Element) (listaInterventi
						.item(contInterv));
				System.out.println("Intervento n." + contInterv);

				// Caricamento degli attributi

				// ID Intervento
				String idIntervento = interventoCorrente.getAttribute("id");
				System.out.println("\tID:" + idIntervento);
				// QUI IL SET DEL VALORE DI ID DELL'INTERVENTO
				i.setID(idIntervento);

				// OraInizio
				String oraInizio = interventoCorrente.getAttribute("orainizio");
				System.out.println("\tOra Inizio:" + oraInizio);
				// QUI IL SET DELL'ORARIO DI INIZIO DELL'INTERVENTO
				i.setOraInizio(oraInizio);

				// OraFine
				String oraFine = interventoCorrente.getAttribute("orafine");
				System.out.println("\tOra Fine:" + oraFine);
				// QUI IL SET DELL'ORA DI FINE DELL'INTERVENTO
				i.setOraFine(oraFine);

				// Caricamento degli elementi rimanenti
				NodeList listaNodi = interventoCorrente.getChildNodes();

				for (int contNodi = 0; contNodi < listaNodi.getLength(); contNodi++) {
					Node elementoCorrente = listaNodi.item(contNodi);
					if (elementoCorrente.getNodeType() == Node.ELEMENT_NODE) { // anche
																				// in
																				// questo
																				// caso
																				// controlliamo
																				// se
																				// si
																				// tratta
																				// di
																				// tag
						Element detail = (Element) elementoCorrente; // cast
						String tag = detail.getNodeName();
						if (tag.equals("operatoreIntervento")) {
							String idOperatore = detail.getAttribute("id");
							System.out.println("\tID Operatore:" + idOperatore);
							// QUI IL SET DELL'ID DELL'OPERATORE
							i.setIdInf(idOperatore);
						} else if (tag.equals("data")) {
							String dataIntervento = detail.getTextContent();
							System.out.println("\tData:" + dataIntervento);
							// QUI IL SET DELLA DATA
							i.setData(dataIntervento);
						} else if (tag.equals("luogo")) {
							String indirizzo = detail.getAttribute("indirizzo");
							System.out.println("\tIndirizzo:" + indirizzo);
							// QUI IL SET DELL'INDIRIZZO
							i.setNCivico(indirizzo);

							String citta = detail.getAttribute("citta");
							System.out.println("\tCitta:" + citta);
							// QUI IL SET DELLA CITTA'
							i.setCitta(citta);

							String cap = detail.getAttribute("cap");
							System.out.println("\tCAP:" + cap);
							// QUI IL SET DEL CAP
							i.setCAP(cap);
						} else if (tag.equals("paziente")) {

							String idPaziente = detail.getAttribute("id");
							System.out.println("\tID Paziente:" + idPaziente);
							// QUI IL SET DELL'ID DEL PAZIENTE

							i.setIdPaziente(idPaziente);
							NodeList listaPaziente = detail.getChildNodes();

							String nomePaziente = listaPaziente.item(0)
									.getTextContent();
							System.out.println("\tNome paziente:"
									+ nomePaziente);
							// QUI IL SET DEL NOME DEL PAZIENTE
							i.setNome(nomePaziente);

							String cognomePaziente = listaPaziente.item(1)
									.getTextContent();// errore
							System.out.println("\tCognome paziente:"
									+ cognomePaziente);
							// QUI IL SET DEL COGNOME DEL PAZIENTE
							i.setCognome(cognomePaziente);

							String dataNascita = listaPaziente.item(2).getTextContent();
							System.out.println("\tData paziente:"
									+ dataNascita);
							i.setDataNascita(dataNascita);
							
							// Estrazione dei numeri di cellulare
							NodeList listaCellulari = listaPaziente.item(3)
									.getChildNodes();

							// Creazione lista cellulari
							ArrayList<String> cellulari = new ArrayList<String>();

							for (int contCellulari = 0; contCellulari < listaCellulari
									.getLength(); contCellulari++)
							// do
							{
								Element cellulareCorrente = (Element) (listaCellulari
										.item(contCellulari));
								String numeroCellCorrente = cellulareCorrente
										.getAttribute("numero");
								System.out.println("\tCellulare Paziente:"
										+ numeroCellCorrente);
								// QUI IL SET DEL NUOVO NUMERO DI CELLULARE
								// (APPEND ALLA LISTA)
								cellulari.add(numeroCellCorrente);

							}
							// Aggiunta dei numeri di cellulare del paziente ai
							// dati dell'intervento
							i.setCellulari(cellulari);

						} else if (tag.equals("tipiInterventi")) {
							NodeList listaTipi = elementoCorrente
									.getChildNodes();

							// Creazione lista per i tipi di intervento
							// ArrayList<TipiIntervento> listTipi= new
							// ArrayList<TipiIntervento>();

							for (int contTipi = 0; contTipi < listaTipi
									.getLength(); contTipi++)
							// do
							{
								Element tipoCorrente = (Element) (listaTipi
										.item(contTipi));

								// String nomeTipo =
								// tipoCorrente.getAttribute("nome");
								// System.out.println("\n\tTipo Interv:" +
								// nomeTipo);
								String nomePatologia = tipoCorrente
										.getAttribute("nome");

								// QUI IL SET DEL NOME DEL NUOVO TIPO DI
								// INTERVENTO
								// Creazione oggetto
								TipiIntervento tipoIntervento = new TipiIntervento();
								tipoIntervento.setPatologia(nomePatologia);
								// tipoIntervento.setNome(nomeTipo);

								// Saltato il valore rilevato perchè non
								// presente

								String nomeTipo = tipoCorrente.getChildNodes()
										.item(0).getTextContent();
								System.out.println("\tNome:" + nomeTipo);

								String noteTipo = tipoCorrente.getChildNodes()
										.item(1).getTextContent();
								System.out.println("\tNote:" + noteTipo);

								// QUI IL SET DELLE NOTE DEL NUOVO TIPO DI
								// INTERVENTO
								tipoIntervento.setNome(nomeTipo);
								tipoIntervento.setNote(noteTipo);

								// Aggiunta alla lista
								i.setTipoIntervento(tipoIntervento);

							}
						} else if (tag.equals("note")) {
							String noteIntervento = detail.getTextContent();
							i.setNote(noteIntervento);
						}
					}
				}
				// Aggiunta intervento alla lista
				list.add(i);

			}
			// fileAttivita.delete();
			inizializzaFile(list.get(0).getIdInf());
		} catch (ParserConfigurationException e) {
			System.out.println("ERRORE: creazione Parser fallita!! - "
					+ e.getMessage());
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRORE: parsing fallito!! - " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRORE: impossibile aprire il file!! - "
					+ e.getMessage());
			e.printStackTrace();
		}

	}

	private static void inizializzaFile(String fileid) {
		try {
			fileExport = new File(exportDirectory + File.separator + fileid
					+ "_" + Controller.Adesso(formatoData) + "_m.xml");
			if (fileExport.exists())
				deleteDir(fileExport);
			exportDirectory.mkdirs();
			fw = new FileWriter(fileExport, true);
			fw.append("<interventi>\n");
			fw.close();
		} catch (IOException e) {
			Toast.makeText(contesto, "Attenzione errore di scrittura!\n"+e,
					Toast.LENGTH_SHORT).show();
		}

	}

	public static void chiudiFile() {
		ricercaEsportazione();
		try {
			fileExport = new File(exportDirectory + File.separator
					+ stringaFileEsportazione);
			fw = new FileWriter(fileExport, true);
			fw.append("</interventi>");
			fw.close();
		} catch (IOException e) {
			Toast.makeText(contesto, "Attenzione errore di scrittura!\n"+e,
					Toast.LENGTH_SHORT).show();
		}
	}

	public static void scriviIntervento(Intervento intervento) {
		ricercaEsportazione();
		fileExport = new File(exportDirectory + File.separator
				+ stringaFileEsportazione);
		try {
			fw = new FileWriter(fileExport, true);
			XmlSerializer xmlSerializer = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			xmlSerializer.setFeature(
					"http://xmlpull.org/v1/doc/features.html#indent-output",
					true);
			xmlSerializer.setOutput(writer);

			xmlSerializer.startTag("", "operatoreIntervento");
			xmlSerializer.attribute("", "id", intervento.getIdInf());
			xmlSerializer.endTag("", "operatoreIntervento");

			xmlSerializer.startTag("", "data");
			xmlSerializer.text(intervento.getData());
			xmlSerializer.endTag("", "data");

			xmlSerializer.startTag("", "luogo");
			xmlSerializer.attribute("", "citta", intervento.getCitta());
			xmlSerializer.attribute("", "indirizzo", intervento.getnCivico());
			xmlSerializer.attribute("", "cap", intervento.getCAP());
			xmlSerializer.endTag("", "luogo");

			xmlSerializer.startTag("", "paziente");
			xmlSerializer.attribute("", "id", intervento.getIdPaziente());

			xmlSerializer.startTag("", "nome");
			xmlSerializer.text(intervento.getNome());
			xmlSerializer.endTag("", "nome");

			xmlSerializer.startTag("", "cognome");
			xmlSerializer.text(intervento.getCognome());
			xmlSerializer.endTag("", "cognome");
			
			xmlSerializer.startTag("", "dataNascita");
			xmlSerializer.text(intervento.getDataNascita());
			xmlSerializer.endTag("", "dataNascita");

			xmlSerializer.startTag("", "cellulari");
			inserisciCellulari(xmlSerializer, intervento.getCellulari());
			xmlSerializer.endTag("", "cellulari");

			xmlSerializer.endTag("", "paziente");
			ArrayList<TipiIntervento> tipo = new ArrayList<TipiIntervento>();
			tipo = intervento.getTipoIntervento();
			xmlSerializer.startTag("", "tipiInterventi");
			inserisciTipi(xmlSerializer, tipo);
			xmlSerializer.endTag("", "tipiInterventi");

			// end DOCUMENT
			xmlSerializer.endDocument();
			fw.append("<intervento id=\"" + intervento.getID()
					+ "\" orainizio=\"" + intervento.getOraInizio()
					+ "\" orafine=\"" + intervento.getOraFine() + "\">");
			fw.append(writer.toString());
			fw.append("<log>");
			fw.append("<rilevazioni>");
			fw.append(GestoreLog.leggiLog(contesto));
			// fr.close();
			fw.append("</rilevazioni>");
			fw.append("</log>");
			fw.append("</intervento>\n");
			fw.close();
			GestoreLog.cancellaLog();
		} catch (Exception e) {
			Toast.makeText(contesto, "Attenzione errore di scrittura!\n"+e,
					Toast.LENGTH_SHORT).show();
		}
	}

	private static void inserisciCellulari(XmlSerializer xmlSerializer,
			List<String> cellulari) {
		for (int i = 0; i < cellulari.size(); i++) {
			try {
				xmlSerializer.startTag("", "cellulare");
				xmlSerializer.attribute("", "numero", cellulari.get(i));
				xmlSerializer.endTag("", "cellulare");
			} catch (Exception e) {
				Toast.makeText(contesto, "Attenzione errore di scrittura!\n"+e,
						Toast.LENGTH_SHORT).show();
			}

		}
	}

	private static void inserisciTipi(XmlSerializer xmlSerializer,
			List<TipiIntervento> tipo) {
		for (int i = 0; i < tipo.size(); i++) {
			try {
				xmlSerializer.startTag("", "Patologia");
				xmlSerializer.attribute("", "nome", tipo.get(i).getPatologia());
				xmlSerializer.startTag("", "tipoIntervento");
				xmlSerializer.text(tipo.get(i).getNome());
				xmlSerializer.endTag("", "tipoIntervento");
				xmlSerializer.startTag("", "valoreRilevato");
				xmlSerializer.attribute("", "tempoIntervento", tipo.get(i)
						.getTempo());
				xmlSerializer.text(tipo.get(i).getMisura());
				xmlSerializer.endTag("", "valoreRilevato");
				xmlSerializer.startTag("", "note");
				xmlSerializer.text(tipo.get(i).getNote());
				xmlSerializer.endTag("", "note");

				xmlSerializer.endTag("", "Patologia");

			} catch (Exception e) {
				Toast.makeText(contesto, "Attenzione errore di scrittura!\n"+e,
						Toast.LENGTH_SHORT).show();
			}

		}
	}

}
