/*
 * 
 */
package adisys.server.frontEnd.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Classe Controllore delle richieste.
 * Interfaccia utilizzata dall’ADISysAC per il 
 * livello di business dell’applicazione
 * 
 */
public interface ApplicationController {

	//
	// /**
	// * Oggetto richiesta
	// */
	// protected RequestContext requestContext;
	//
	// /**
	// * Oggetto risposta
	// */
	// protected ResponseContext responseContext;
	// /**
	// * Inizializza il Controllore
	// */
	// public void init() {}
	//
	/**
	 * metodo che data la richiesta effettuata dal Client, {@code key}, esegue
	 * il metodo recuperato mediante il parametro key, e lo fa mediante il
	 * metodo execute ereditato dalla classe Proxy.
	 * 
	 * @param key
	 *            : richiesta
	 * @param parametri
	 *            the parametri
	 * @return risposta
	 * @throws SecurityException
	 *             the security exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	public Object handleRequest(String key,
			ArrayList<ArrayList<Object>> parametri) throws SecurityException,
			IllegalArgumentException, ParserConfigurationException,
			SAXException, IOException, ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchFieldException;
	// /**
	// * metodo che manipola la risposta
	// *
	// * @param requestContext
	// * richiesta
	// * @param responseContextContext
	// * risposta
	// */
	// public void handleResponse(RequestContext requestContext,
	// ResponseContext responseContext) {
	// System.err.println("APPLICATIONCONTROLLER handleResponse: CI DOVREBBE ESSERE UNA RISPOSTA..");
	// }
}
