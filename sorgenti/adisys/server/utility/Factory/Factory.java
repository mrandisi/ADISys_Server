/*
 * 
 */
package adisys.server.utility.Factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import adisys.server.utility.ReadXML;
import java.io.InputStream;

/**
 * Il pattern Factory Method definisce un�interfaccia per la creazione di un
 * oggetto, e delega alle sottoclassi la scelta di quale classe deve essere
 * istanziata.
 * 
 */
public abstract class Factory {

	/**
	 * una mappa in cui vengono inseriti i dati relativi alle classi di cui
	 * bisogna ricavare l�istanza il primo valore dell'ArrayList corrisponde a
	 * class, il secondo a method.
	 */
	private HashMap<String, ArrayList<String>> map = null;

	/**
	 * di tipo ReadXML un tipo costruito ad hoc che si occupa della gestione di
	 * file XML opportunamente costruiti.
	 */
	private ReadXML readxml = null;

	/**
	 * un metodo che restituisce il percorso del file XML contenente le
	 * informazioni da caricare nella mappa map.
	 * 
	 * @return the resource path
	 */
	abstract protected InputStream getResourcePath();

	/**
	 * un metodo che restituisce il nome del nodo da cui estarre le
	 * informazioni.
	 * 
	 * @return the key node name
	 */
	abstract protected String getKeyNodeName();

	/**
	 * il costruttore, che si occupa dell'avvaloramento dei due attributi
	 * {@code map} e {@code readxml}.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Factory() throws ParserConfigurationException, SAXException,
			IOException {
		if (readxml == null) {
			readxml = new ReadXML(this.getResourcePath());
			this.initMap();
		} else {
			if (this.map == null) {
				this.initMap();
			}
		}
	}

	/**
	 * metodo privato che si occupa dell'inizializzazione di map attraverso il
	 * caricamento dei dati dal file XML.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void initMap() throws ParserConfigurationException, SAXException,
			IOException {
		//
		map = readxml.getMap(this.getKeyNodeName());
		System.out.println("\nCLASSE FACTORY\nmappa inizializzata: "+map.toString());
	}

	/**
	 * metodo pubblico che si occupa di recuperare l'istanza della classe la cui
	 * chiave in map � key, scopo proprio del pattern Factory Method. Nel
	 * dettaglio il metodo recupera il nome della classe di cui si desidera
	 * l'istanza, e mediante riflessione, la crea e la istanzia dando come
	 * risultato un generico Object.
	 * 
	 * @param key
	 *            the key
	 * @return single instance of Factory
	 */
	public Object getInstance(String key) {
		String className = null;
		// ottiene il primo e unico elemento (unico per le assunzioni operate)
		if ((this.getValue(key, 0) != null)) {
			className = this.getValue(key, 0);
			try {
				className = "adisys.server." + className;
				Class<?> ccc = Class.forName(className);
				System.out.println("FACTORY - ccc-> " + ccc.getName()+"\n");
				Object obj = ccc.newInstance();
				return obj;
			} catch (ClassNotFoundException | IllegalAccessException
					| InstantiationException e) {
				return null;
			}
		} else {
			return null;
		}
	}// END getInstance

	/**
	 * metodo pubblico che si occupa del recupero da proxyMap del valore
	 * contenuto in posizione position che ha chiave key.
	 * 
	 * @param key
	 *            the key
	 * @param position
	 *            the position
	 * @return the value
	 */
	private String getValue(String key, int position) {
		String className = null;
		ArrayList<String> names = map.get(key);
		// ottiene il primo e unico elemento (unico per le assunzioni operate)
		if ((names != null) && (names.size() > 0))
			className = names.get(position);
		System.out.println("nome della classe: " +className);
		return className;
	}
}
