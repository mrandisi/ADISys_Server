/*
 * 
 */
package adisys.server.presentation.frontController;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import adisys.server.utility.Factory.Factory;

import java.io.InputStream;

/**
 * A factory for creating Ac objects.
 * Classe che estende le caratteristiche del Factory; viene utilizzato dal Front
 * Controller per capire a quale Application Controller si deve rifare per il
 * metodo richiesto.
 * 
 */
public class AcFactory extends Factory {

	/**
	 * Instantiates a new ac factory.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public AcFactory() throws ParserConfigurationException, SAXException,
			IOException {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adisys.server.frontEnd.controller.Factory#getKeyNodeName()
	 */
	@Override
	protected String getKeyNodeName() {
		return "action";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adisys.server.frontEnd.controller.Factory#getResourcePath()
	 */
	@Override
	protected InputStream getResourcePath() {
		InputStream is = this.getClass().getResourceAsStream(
				"ADISysACFactory.xml");
		return is;
	}
}
