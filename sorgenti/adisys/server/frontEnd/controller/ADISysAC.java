/*
 * 
 */
package adisys.server.frontEnd.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import adisys.server.utility.Proxy.Proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class ADISysAC.
 * Classe che estende Proxy ed Implementa l’Interfaccia 
 * applicationController; viene utilizzato per capire, 
 * tramite l’ausilio del Proxy, la classe da istanziare per 
 * usufruire del metodo richiesto. Crea una sottoclasse della 
 * classe Proxy
 * 
 */
abstract public class ADISysAC extends Proxy implements ApplicationController {

	/**
	 * Costruttore della classe ScheduleAC che crea una sottoclasse della classe
	 * Proxy.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	public ADISysAC() throws ParserConfigurationException, SAXException,
			IOException {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * adisys.server.frontEnd.controller.ApplicationController#handleRequest
	 * (java.lang.String, java.util.ArrayList)
	 */
	public Object handleRequest(String key,
			ArrayList<ArrayList<Object>> parametri) throws SecurityException,
			IllegalArgumentException, ParserConfigurationException,
			SAXException, IOException, ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchFieldException {
		System.out.println("passo2");
		try {
			return execute(key, parametri);
		} catch (Throwable ex) {
			Logger.getLogger(ADISysAC.class.getName()).log(Level.SEVERE, null,
					ex);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adisys.server.frontEnd.controller.Proxy#getKeyNodeName()
	 */
	@Override
	protected String getKeyNodeName() {
		return "action";
	}

	/**
	 * implementa il metodo ereditato dalla classe Proxy.
	 * 
	 * @param key
	 *            the key
	 * @return {@link ApplicationController}
	 */
	@Override
	protected String getMethodName(String key) {
		String methodName = null;
		methodName = getValue(key, 1);
		return methodName;
	}
}
