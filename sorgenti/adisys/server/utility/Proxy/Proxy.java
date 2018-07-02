/*
 * 
 */
package adisys.server.utility.Proxy;

import adisys.server.utility.UIFeedbackException;
import adisys.server.presentation.frontController.Fc;
import adisys.server.utility.ReadXML;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * The Class Proxy.
 * Classe astratta delle classi di servizio del sistema. 
 * Usa ReadXml per leggere dal file Xml il metodo. 
 * E’ estesa dalla classe ADISysAC, che la utilizza per 
 * interfacciarsi con le classi di business mediante il 
 * metodo execute
 * 
 */
public abstract class Proxy {

	/**
	 * una mappa in cui vengono inseriti i dati relativi agli oggetti reali che
	 * devono essere rappresentati dalla classe Proxy, ovvero le classi da
	 * istanziare e i metodi da invocare.
	 */
	private HashMap<String, ArrayList<String>> proxyMap = null;

	/**
	 * di tipo ReadXML un tipo costruito ad hoc che si occupa della gestione di
	 * file XML opportunamente costruiti.
	 */
	private ReadXML proxyReadxml = null;

	/**
	 * metodo che restituisce il percorso del file XML contenente le
	 * informazioni da caricare nella mappa proxyMap.
	 * 
	 * @return the resource path
	 */
	abstract protected InputStream getResourcePath();

	/**
	 * metodo che restituisce il nome del nodo da cui estarre le informazioni.
	 * 
	 * @return the key node name
	 */
	abstract protected String getKeyNodeName();

	/**
	 * metodo che in base alla chiave,{@code key}, restituisce il nome del
	 * metodo da invocare.
	 * 
	 * @param key
	 *            the key
	 * @return the method name
	 */
	abstract protected String getMethodName(String key);

	/**
	 * Costruttore della classe Proxy , che si occupa dell’avvaloramento dei due
	 * attributi.
	 * 
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Proxy() throws ParserConfigurationException, SAXException,
			IOException {
		if (proxyReadxml == null) {
			proxyReadxml = new ReadXML(this.getResourcePath());
			this.initMap();
		} else {
			if (proxyMap == null) {
				this.initMap();
			}
		}
	}

	/**
	 * Metodo privato che si occupa dell’inizializzazione di proxyMap attraverso
	 * il caricamento dei dati dal file XML.
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
		proxyMap = proxyReadxml.getMap(this.getKeyNodeName());
	}

	/**
	 * Metodo pubblico che si occupa del recupero da proxyMap del valore
	 * contenuto in posizione {@code position} che ha chiave {@code key}.
	 * 
	 * @param key
	 *            the key
	 * @param position
	 *            the position
	 * @return methodName nome del metodo
	 */
	public String getValue(String key, int position) {
		String methodName = null;
		ArrayList<String> names = proxyMap.get(key);
		// prendo il primo e unico elemento (è unico per le assunzioni fatte)
		if ((names != null) && (names.size() > 0)) {
			methodName = names.get(position);
		}
		return methodName;
	}

	/**
	 * Metodo pubblico che si occupa di invocare mediante riflessione il metodo
	 * la cui chiave in proxyMap è {@code key} e i cui parametri sono contenuti
	 * in {@code parametri} e che quindi racchiude in sé la logica del pattern.
	 * Nel dettaglio il metodo : 1. Recupera il nome del metodo, se il nome è
	 * {@code this} l’invocazione viene fatta direttamente su {@code this}
	 * altrimenti il metodo viene creato ed istanziata e su questo avviene
	 * l’invocazione; 2. Effettua il controllo sul numero dei parametri, in base
	 * al quale avremo invocazioni differenti; 3. Effettua il controllo sul
	 * campo static, se il metodo è statico l’invocazione viene fatta sulla
	 * classe, altrimenti sull’oggetto. Il metodo gestisce in entrata ed in
	 * uscita un array di Object, parametri, questo ci permette di poter
	 * lavorare su oggetti di qualsiasi tipo rendendo il metodo riusabile in
	 * qualsiasi contesto
	 * 
	 * @param key
	 *            the key
	 * @param parametri
	 *            the parametri
	 * @return the object
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws Throwable
	 *             the throwable
	 */
	@SuppressWarnings("unchecked")
	public Object execute(String key, ArrayList<ArrayList<Object>> parametri)
			throws SecurityException, NoSuchMethodException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, Throwable {
		System.out.println(key);
		System.out.println("PROXY - key: " + key + "  parameters: " + parametri);
		String staticMethod = this.getValue(key, 2);
		System.out.println(staticMethod);
		
		String className = null;

		// prendo il primo e unico elemento (è unico per le assunzioni fatte)
		if ((this.getValue(key, 0) != null)) {
			className = this.getValue(key, 0);
			System.out.println(className);
			if (className.equals("this")) {
				if (parametri == null) { // se i parametri sono nulli
					Class<? extends Proxy> c = this.getClass();
					Method m = c.getDeclaredMethod(this.getMethodName(key),
							(Class[]) null);
					m.setAccessible(true);
					System.out.println("****parametri nulli*****");

					// invoca il metodo che fa le operazioni e modifica il
					// responseContext
					try {
						Object risultato = m.invoke(this);
						System.out.println("risultato m.invoke: " + risultato);
						return risultato;
					} catch (IllegalAccessException | IllegalArgumentException e) {
						System.err
								.println("Errore nell'invocare la funzione \n"
										+ m.toString() + "\n - errore " + e
										+ "\n");
					} catch (InvocationTargetException e) {
						try {
							throw e.getCause();
						} catch (UIFeedbackException msg) {
							String msgCode = UIFeedbackException.getMsg();
							ArrayList<String> replacementArray = UIFeedbackException
									.getReplacementArray();
							Fc.visualizzaMessaggioPopup(Fc.MessageBundle(
									msgCode, replacementArray));
						}
					}
				} else {// se ci sono parametri
					Class<? extends Proxy> c = this.getClass();
					
					Method m = c.getDeclaredMethod(this.getMethodName(key), parametri.getClass());
					System.out.println("c= " + c);
					m.setAccessible(true);
					System.out.println("m= " + m);
					try {
						Object risultato = m.invoke(this, parametri);
						System.out.println("else risultato=m.invoke:"
								+ risultato);
						return risultato;

					} catch (IllegalAccessException | IllegalArgumentException e) {
						System.out
								.println("non posso invocare il metodo in Proxy 146");
						System.err
								.println("Errore nell'invocare la funzione \n"
										+ m.toString() + "\n - errore " + e
										+ "\n");
					} catch (InvocationTargetException e) {
						try {
							throw e.getCause();
						} catch (UIFeedbackException msg) {
							String msgCode = UIFeedbackException.getMsg();
							UIFeedbackException.MsgType type = UIFeedbackException
									.getType();
							ArrayList<String> replacementArray = UIFeedbackException
									.getReplacementArray();

							if (!replacementArray.isEmpty()) {
								if (type == UIFeedbackException.MsgType.EMPTY)
									Fc.visualizzaMessaggioPopup(Fc
											.MessageBundle(msgCode,
													replacementArray));
								else
									Fc.visualizzaMessaggioPopup(Fc
											.MessageBundle(msgCode,
													replacementArray), type);
							} else {
								if (type == UIFeedbackException.MsgType.EMPTY)
									Fc.visualizzaMessaggioPopup(Fc
											.MessageBundle(msgCode));
								else
									Fc.visualizzaMessaggioPopup(
											Fc.MessageBundle(msgCode), type);
							}
						}
					}
				}
			} else {
				Class<?> proxyClass = Class.forName(className);
				Object obj = proxyClass.newInstance();
				Class<? extends Object> c = obj.getClass();

				Method m = proxyClass.getDeclaredMethod(
						this.getMethodName(key), (Class[]) null);
				m.setAccessible(true);
				// invoca il metodo che fa le operazioni e modifica il
				// responseContext
				try {
					Object risultato;
					if (staticMethod.equals("yes")) {
						risultato = (ArrayList<Object>) m.invoke(c,
								(Object[]) null);
					} else {
						risultato = (ArrayList<Object>) m.invoke(obj,
								(Object[]) null);
					}

					return risultato;
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.err.println("Errore nell'invocare la funzione \n"
							+ m.toString() + "\n - errore " + e + "\n");
				}
			}
		}
		return null;
	}
}// END Proxy.java
