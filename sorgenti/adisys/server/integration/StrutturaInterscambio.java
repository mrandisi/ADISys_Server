/*
 * 
 */
package adisys.server.integration;

import adisys.server.business.TOInterventoCompleto;
import adisys.server.business.TOIntervento;
import adisys.server.business.TOPaziente;
import adisys.server.business.TORilevazione;
import adisys.server.business.TOTipoIntervento;
import adisys.server.presentation.frontController.Fc;
import adisys.server.utility.UIFeedbackException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

/**
 * The Class StrutturaInterscambio.
 * Classe incaricata della creazione, validazione 
 * e lettura dei file di interscambio XML
 * 
 */
public class StrutturaInterscambio {

	/** The formato data xml. */
	private final String FORMATO_DATA_XML = "yyyy-MM-dd";

	/** The formato ora xml. */
	private final String FORMATO_ORA_XML = "hh:mm:ss";

	/** The interventi. */
	Element interventi;

	/**
	 * Instantiates a new struttura interscambio.
	 */
	public StrutturaInterscambio() {
		interventi = new Element("interventi");
	}

	/**
	 * Adds the intervento.
	 * 
	 * @param datiIntervento
	 *            the dati intervento
	 * @param datiPaziente
	 *            the dati paziente
	 */
	public void addIntervento(TOIntervento datiIntervento,
			TOPaziente datiPaziente) {
		// crea il nuovo oggetto Element intervento
		Element nuovoIntervento = new Element("intervento");

		// trasferisce i dati dall'oggetto intervento all'element
		// Creazione e inserimento ID intervento
		Attribute id = new Attribute("id", int2string6(datiIntervento.getID()));

		// Attributo orafine non vengono inseriti (a carico di ADISysMobile)
		Attribute oraInizio = new Attribute("orainizio",
				datiIntervento.getOraInizioDaFormato(FORMATO_ORA_XML));
		Attribute oraFine = new Attribute("orafine",
				datiIntervento.getOraInizioDaFormato(FORMATO_ORA_XML));

		// Elemento Operatore intervento
		Element operatoreIntervento = new Element("operatoreIntervento");
		operatoreIntervento.setAttribute("id",
				int2string6(datiIntervento.getIDInfermiere()));

		// Elemento data
		Element data = new Element("data");
		data.setText(datiIntervento.getDataDaFormato(FORMATO_DATA_XML));

		// Elemento luogo
		Element luogo = new Element("luogo");
		luogo.setAttribute("citta", datiIntervento.getCitta());
		luogo.setAttribute("indirizzo", datiIntervento.getCivico());
		luogo.setAttribute("cap", datiIntervento.getCap());

		// Elemento paziente con ID
		Element paziente = new Element("paziente");
		paziente.setAttribute("id", int2string6(datiPaziente.getID()));

		{
			// Nome paziente
			Element paziente_nome = new Element("nome");
			paziente_nome.setText(datiPaziente.getNome());
			paziente.addContent(paziente_nome);

			// Cognome paziente
			Element paziente_cognome = new Element("cognome");
			paziente_cognome.setText(datiPaziente.getCognome());
			paziente.addContent(paziente_cognome);

			Element paziente_data = new Element("dataNascita");
			paziente_data
					.setText(datiPaziente.getDataNascita(FORMATO_DATA_XML));
			paziente.addContent(paziente_data);

			// Cellulari
			Element cellulari = new Element("Cellulari");

			// Ciclo di aggiunta dei numeri di cellulare
			Object elencoCellulari[] = datiPaziente.getCellulari();
			for (Object numeroCellulare : elencoCellulari) {
				// Creazione elemento cellulare
				Element cellulare = new Element("Cellulare");
				// Aggiunta numero
				cellulare.setAttribute("numero",
						String.valueOf(numeroCellulare));
				// Aggiunta elemento a cellulari
				cellulari.addContent(cellulare);
			}

			paziente.addContent(cellulari);
		}

		// TipiInterventi
		Element tipiInterventi = new Element("tipiInterventi");

		for (int cont = 0; cont < datiIntervento.countTipInterventi(); cont++) {

			Element Patologia = new Element("Patologia");
			// Aggiunta attributo nome
			Patologia.setAttribute("nome",
					datiIntervento.getTipoIntervento(cont).getPatologia());

			Element tipoIntervento = new Element("tipoIntervento");
			// Aggiunta attributo nome
			tipoIntervento.setText(datiIntervento.getTipoIntervento(cont)
					.getNome());

			// Creazione Elementi valore rilevato e note con attributi
			// Element valoreRilevato = new Element("valoreRilevato");
			Element note = new Element("note");
			note.setText(datiIntervento.getTipoIntervento(cont).getNote());

			// Attributo tempointervento non obbligatorio, sarà aggiunto da
			// ADISys Mobile.
			// Aggiunta elementi a tipoIntervento
			// tipoIntervento.addContent(valoreRilevato);
			Patologia.addContent(tipoIntervento);
			Patologia.addContent(note);

			// Aggiunta TipoIntervento a lista
			tipiInterventi.addContent(Patologia);
			// tipiInterventi.addContent(tipoIntervento);

		}

		// Creazione elemento log (vuoto)
		Element log = new Element("log");
		Element log_note = new Element("note");
		Element rilevazioni = new Element("rilevazioni");
		log.addContent(rilevazioni);
		log.addContent(log_note);

		// TODO aggiunge l'element a "interventi
		// ID
		nuovoIntervento.setAttribute(oraInizio);
		nuovoIntervento.setAttribute(oraFine);
		nuovoIntervento.setAttribute(id);
		// Operatore intervento
		nuovoIntervento.addContent(operatoreIntervento);
		// Data
		nuovoIntervento.addContent(data);
		// Luogo
		nuovoIntervento.addContent(luogo);
		// Paziente
		nuovoIntervento.addContent(paziente);
		// TipiInterventi
		nuovoIntervento.addContent(tipiInterventi);
		// Log
		nuovoIntervento.addContent(log);

		interventi.addContent(nuovoIntervento);
	}

	/**
	 * Salva su file xml.
	 * 
	 * @param cartella
	 *            the cartella
	 * @param nomeFile
	 *            the nome file
	 * @param percorsoXSD
	 *            the percorso xsd
	 * @return the string
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public String salvaSuFileXML(String cartella, String nomeFile,
			String percorsoXSD) throws UIFeedbackException {
		// Creazione e preparazione Document XML
		Document fileXMLInterscambio = new Document();
		fileXMLInterscambio.setRootElement(interventi);

		// TODO Controllo esistenza file/percorso
		File fCartella = new File(cartella);
		File fileXML = new File(cartella + "/" + nomeFile);
		File fileXSD = new File(percorsoXSD);

		// Creazione cartella se non esistente
		if (!(fCartella.exists()))
			fCartella.mkdir();

		String errLog = Fc.MessageBundle("MsgDialog1650");

		// Controllo esistenza file
		org.jdom2.output.XMLOutputter estrattore = new XMLOutputter();
		try {
			estrattore.output(fileXMLInterscambio, new FileWriter(fileXML));
			System.out.println(Fc.MessageBundle("MsgDialog1660"));
			errLog += Fc.MessageBundle("MsgDialog1670");

			errLog += "\n" + validaXML(fileXML, fileXSD);

		} catch (IOException e) {
			System.out.println(Fc.MessageBundle("MsgDialog1680"));
			errLog += Fc.MessageBundle("MsgDialog1690");
		}

		return errLog;
	}

	/**
	 * Carica da file xml.
	 * 
	 * @param percorsoXML
	 *            the percorso xml
	 * @param schemaXsdImp
	 *            the schema xsd imp
	 * @return the string
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public String caricaDaFileXML(String percorsoXML, String schemaXsdImp)
			throws UIFeedbackException {
		// Stringa per feedback
		String errLog = Fc.MessageBundle("MsgDialog1700");
		// Controllo esistenza file XML
		File XML = new File(percorsoXML);
		File XSD = new File(schemaXsdImp);

		// Controllo esistenza file XSD
		if (!XSD.exists())
			errLog += Fc.MessageBundle("MsgDialog1710") + " " + XSD.getName()
					+ " " + Fc.MessageBundle("MsgDialog1720");

		// Validazione
		errLog += "\n- " + validaXML(XML, XSD);

		// Tentativo caricamento
		try {
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(XML);
			interventi = doc.getRootElement();
			errLog += Fc.MessageBundle("MsgDialog1730") + "\"" + XML.getName()
					+ "\" " + Fc.MessageBundle("MsgDialog1740");
		} catch (JDOMException e) {
			// Auto-generated catch block
			errLog += Fc.MessageBundle("MsgDialog1750") + " " + XML.getName();
		} catch (IOException e) {
			// Auto-generated catch block
			errLog += Fc.MessageBundle("MsgDialog1760") + " " + XML.getName();
		}

		return errLog;
	}

	/**
	 * Gets the interventi completi.
	 * 
	 * @return the interventi completi
	 */
	public ArrayList<TOInterventoCompleto> getInterventiCompleti() {
		// Creazione Lista
		ArrayList<TOInterventoCompleto> listaInterventi = new ArrayList<>();

		for (Element intervento : interventi.getChildren()) {
			// Creazione oggetto intervento
			TOInterventoCompleto i = new TOInterventoCompleto();

			// Trasferimento dati
			i.setID(intervento.getAttributeValue("id"));

			i.setOraInizio(intervento.getAttributeValue("orainizio"),
					FORMATO_ORA_XML);
			i.setOraFineFmt(intervento.getAttributeValue("orafine"),
					FORMATO_ORA_XML);

			i.setIDInfermiere(intervento.getChild("operatoreIntervento")
					.getAttributeValue("id"));

			i.setData(intervento.getChildText("data"), FORMATO_DATA_XML);

			i.setCitta(intervento.getChild("luogo").getAttributeValue("citta"));
			i.setCivico(intervento.getChild("luogo").getAttributeValue(
					"indirizzo"));
			i.setCap(intervento.getChild("luogo").getAttributeValue("cap"));

			// Creazione paziente
			TOPaziente p = new TOPaziente();
			// Aggiunta dati paziente
			p.setID(Integer.valueOf(intervento.getChild("paziente")
					.getAttributeValue("id")));
			p.setNome(intervento.getChild("paziente").getChildText("nome"));
			p.setCognome(intervento.getChild("paziente")
					.getChildText("cognome"));

			p.setDataNascita(
					intervento.getChild("paziente").getChildText("dataNascita"),
					FORMATO_DATA_XML);

			// Aggiunta numeri di cellulare paziente se ce ne sono
			if (intervento.getChild("paziente").getChild("cellulari")
					.getChildren() != null)
				for (Element cellulare : intervento.getChild("paziente")
						.getChild("cellulari").getChildren()) {
					p.addCellulare(cellulare.getAttributeValue("numero"));
				}
			// Aggiunta paziente all'intervento
			i.setPaziente(p);

			// Aggiunta tipi interventi
			for (Element tipoIntervento : intervento.getChild("tipiInterventi")
					.getChildren()) {
				TOTipoIntervento t = new TOTipoIntervento();
				t.setPatologia(tipoIntervento.getAttributeValue("nome"));
				t.setNome(tipoIntervento.getChildText("tipoIntervento"));
				t.setValoreRilevato(tipoIntervento
						.getChildText("valoreRilevato"));
				t.setTempoIntervento(tipoIntervento.getChild("valoreRilevato")
						.getAttributeValue("tempoIntervento"));
				t.setNote(tipoIntervento.getChildText("note"));
				i.addTipoIntervento(t);
			}

			// Aggiunta Log/Rilevazioni
			for (Element rilevazione : intervento.getChild("log")
					.getChild("rilevazioni").getChildren()) {
				TORilevazione r = new TORilevazione();

				// Lettura e inserimento Timestamp
				String data = rilevazione.getChild("timestamp")
						.getAttributeValue("data");
				String ora = rilevazione.getChild("timestamp")
						.getAttributeValue("ora");
				String timestamp = data + " " + ora;
				String formatoTimestamp = FORMATO_DATA_XML + " "
						+ FORMATO_ORA_XML;
				r.setTimestampFromString(timestamp, formatoTimestamp);

				// GPS
				r.setGpsLatitude(Double.valueOf(rilevazione.getChild("gps")
						.getAttributeValue("latitudine")));
				r.setGpsLongitude(Double.valueOf(rilevazione.getChild("gps")
						.getAttributeValue("longitudine")));
				r.setGpsAltitude(Double.valueOf(rilevazione.getChild("gps")
						.getAttributeValue("altitudine")));
				r.setGpsAccuracy(Double.valueOf(rilevazione.getChild("gps")
						.getAttributeValue("accuratezza")));

				// ACCELEROMETRO
				r.setAccX(Double.valueOf(rilevazione.getChild("accelerometro")
						.getAttributeValue("valorex")));
				r.setAccY(Double.valueOf(rilevazione.getChild("accelerometro")
						.getAttributeValue("valorey")));
				r.setAccZ(Double.valueOf(rilevazione.getChild("accelerometro")
						.getAttributeValue("valorez")));

				// Aggiunta rilevazione all'intervento
				i.addLog(r);
			}

			i.setNote(intervento.getChild("log").getChildText("note"));

			// TODO:Aggiunta dell'intervento alla ista
			listaInterventi.add(i);
		}
		return listaInterventi;
	}

	/**
	 * Int2string6.
	 * 
	 * @param numero
	 *            the numero
	 * @return the string
	 */
	public static String int2string6(int numero) {
		return String.format("%06d", numero);
	}

	/**
	 * Valida xml.
	 * 
	 * @param XML
	 *            the xml
	 * @param XSD
	 *            the xsd
	 * @return the string
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public static String validaXML(File XML, File XSD)
			throws UIFeedbackException {
		Source xmlFile = new StreamSource(XML);
		Source schemaFile = new StreamSource(XSD);

		if (!XSD.exists())
			return Fc.MessageBundle("MsgDialog1770") + " " + XSD.getName()
					+ " " + Fc.MessageBundle("MsgDialog1780");
		if (!XML.exists())
			return Fc.MessageBundle("MsgDialog1790") + " " + XML.getName()
					+ " " + Fc.MessageBundle("MsgDialog1800");

		try {
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);

			Validator validator = schema.newValidator();
			validator.validate(xmlFile);

			return (" \"" + XML.getName() + "\" " + Fc
					.MessageBundle("MsgDialog1810"));

		} catch (SAXException e) {

			return (Fc.MessageBundle("MsgDialog1820") + " \"" + XML.getName()
					+ "\" " + Fc.MessageBundle("MsgDialog1830") + ".");

		} catch (IOException e) {
			return Fc.MessageBundle("MsgDialog1840");
		}

	}

}
