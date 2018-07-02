package adisys.server.business;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import adisys.server.utility.Proxy.Proxy;


abstract public class ApplicationService extends Proxy{

	/**
	 * Costruttore che crea una sottoclasse della classe Proxy;
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public ApplicationService() throws ParserConfigurationException,
			SAXException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * metodo protetto richiesto dall�estensione del Proxy, 
	 * recupera il nome del metodo da invocare in base al parametro {@code key}
	 */
	protected String getMethodName(String key) {
		String methodName = null;
		methodName = getValue(key, 1);
		return methodName;
	}
	
	/**
	 * metodo pubblico che esegue il metodo recuperato mediante il parametro {@code key} 
	 * che ha come parametri {@code parameters}, e lo fa mediante il metodo execute 
	 * ereditato dalla classe Proxy
	 * 
	 * @param key
	 * @param parameters
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 */
        public Object delegate(String key, ArrayList<ArrayList<Object>> parameters) throws SecurityException, 
		IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException,
			NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable{
			
		return execute(key, parameters);
	}
}
