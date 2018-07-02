package file.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import android.content.Context;
import android.os.Environment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import android.widget.Toast;

public class GestoreLog {
	private static File LogDirectory = new File(
			Environment.getExternalStorageDirectory() + "/ADISYSMobile3/Log");
	private static File fileLog = new File(LogDirectory + "/Log.xml");
	private static String formatoData = "yyyy-MM-dd";
	private static String formatoOra = "HH:mm:ss";

	public static void restituisciRilevazione(String latitude,
			String longitude, String altitude, String accuracy, float xAcc,
			float yAcc, float zAcc, int paziente) throws Exception {

		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().newDocument();
		Element rilevazione = doc.createElement("rilevazione");
		doc.appendChild(rilevazione);
		Element tempo = doc.createElement("timestamp");
		String ora = mobile.control.Controller.Adesso(formatoOra);
		String data = mobile.control.Controller.Adesso(formatoData);
		tempo.setAttribute("data", data);
		tempo.setAttribute("ora", ora);
		rilevazione.appendChild(tempo);

		Element gps = doc.createElement("gps");
		gps.setAttribute("latitudine", latitude);
		gps.setAttribute("longitudine", longitude);
		gps.setAttribute("altitudine", altitude);
		gps.setAttribute("accuratezza", accuracy);
		rilevazione.appendChild(gps);

		Element acc = doc.createElement("accelerometro");
		acc.setAttribute("valorex", String.valueOf(xAcc));
		acc.setAttribute("valorey", String.valueOf(yAcc));
		acc.setAttribute("valorez", String.valueOf(zAcc));
		rilevazione.appendChild(acc);
		scriviLog(doc);

	}

	private static void scriviLog(Document xmlDoc) {
		LogDirectory.mkdir();
		// write the output file
		try {
			// create a transformer
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();

			// set some options on the transformer
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			// transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
			// "2");

			// get a transformer and supporting classes
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			DOMSource source = new DOMSource(xmlDoc);

			// transform the xml document into a string
			transformer.transform(source, result);

			// open the output file
			FileWriter outputWriter = new FileWriter(fileLog);
			outputWriter.append(writer.toString());
			outputWriter.close();

		} catch (javax.xml.transform.TransformerException e) {
			// do something with this error
		} catch (java.io.IOException ex) {
			// do something with this error
		}
	}

	public static void cancellaLog() {
		fileLog.delete();
	}

	public static String leggiLog(Context contesto) {
		FileReader fIn;
		String aDataRow = "";
		String aBuffer = "";
		try {
			fIn = new FileReader(fileLog);
			BufferedReader myReader = new BufferedReader(fIn, 1024);
			while ((aDataRow = myReader.readLine()) != null) {
				aBuffer += (aDataRow + "\n");
			}
			cancellaLog();
			fIn.close();
		} catch (FileNotFoundException e1) {
			Toast.makeText(contesto, "Attenzione errore lettura file di Log!",
					Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			Toast.makeText(contesto, "Attenzione errore lettura file di Log!",
					Toast.LENGTH_SHORT).show();
		}
		return aBuffer;
	}

}
