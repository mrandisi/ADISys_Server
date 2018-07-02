/*
 * 
 */
package adisys.server.presentation.frontController;

import java.util.ArrayList;

/**
 * The Interface FrontController.
 * Interfaccia utilizzata dal Front Controller per il 
 * livello di presentazione dell’applicazione.
 * 
 */
public interface FrontController {

	/**
	 * Metodo astratto da estendere nel FrontController dell'applicazione che
	 * implementa la libreria
	 * 
	 * Gestisce le richieste passate dal metodo get.
	 * 
	 * @param request
	 *            : Stringa che contiene il nome della funzione richiesta da
	 *            invocare
	 * @return the object
	 */
	public Object processRequest(String request);

	/**
	 * Process request.
	 * 
	 * @param request
	 *            the request
	 * @param parametri
	 *            the parametri
	 * @return the object
	 */
	public Object processRequest(String request,
			ArrayList<ArrayList<Object>> parametri);

}