/*
 * 
 */
package adisys.server.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * The Class ReadXML.
 * Classe di utility per la lettura del file XML
 * 
 */
public class ReadXML {

	/** The doc. */
	private Document doc = null;

	/**
	 * Costruttore di classe.
	 * 
	 * @param percorsoFile
	 *            percorso del file XML da cui leggere i dati
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ReadXML(String percorsoFile) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse(new File(percorsoFile));
	}

	/**
	 * Instantiates a new read xml.
	 * 
	 * @param is
	 *            the is
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ReadXML(InputStream is) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse(is);
	}
	
	
	/**
	 * crea una mappa con valore chiave e lista di elementi (corrispondenti
	 * all'elemento di nome "keyNodeName").
	 * 
	 * @param keyNodeName
	 *            the key node name
	 * @return the map
	 */
	public HashMap<String, ArrayList<String>> getMap(String keyNodeName) {

		HashMap<String, ArrayList<String>> map = new HashMap<>();
		Element rootElement = doc.getDocumentElement();
		

		NodeList keyList = rootElement.getElementsByTagName(keyNodeName);
		System.out.println(keyNodeName);
		
		

		for (int s = 0; s < keyList.getLength(); s++) {
			ArrayList<String> mapElements = new ArrayList<>();
			Node keyNode = keyList.item(s);

			// prendo il valore della chiave contenuto nell'attributo del nodo
			// "key"
			String mapKey = keyNode.getAttributes().item(0).getNodeValue();
			
			

			String node1Value = 
		    keyNode.getFirstChild().getNextSibling().getFirstChild().getNodeValue();
			mapElements.add(node1Value);
			
			
			if (keyNode.getChildNodes().item(2).getNextSibling() != null) {
				
				String node2Value = 
			    keyNode.getChildNodes().item(2).getNextSibling().getFirstChild().getNodeValue();
				mapElements.add(node2Value);
				
				
				if (keyNode.getLastChild().getPreviousSibling().getFirstChild() != null) {
					
					String node3Value = 
				    keyNode.getLastChild().getPreviousSibling().getFirstChild().getNodeValue();
					mapElements.add(node3Value);
					
				}
			}

			// ora posso inserire la coppia (K,V) nella map
			map.put(mapKey, mapElements);
			
		}
		
		return map;
	}

}// END ReadXML
