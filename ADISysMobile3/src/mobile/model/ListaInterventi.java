package mobile.model;

import java.util.ArrayList;

public class ListaInterventi {

	private static ArrayList<Intervento> lista = null;

	private ListaInterventi() {
	}

	public static synchronized ArrayList<Intervento> getListaInterventi() {
		if (lista == null)
			lista = new ArrayList<Intervento>();
		return lista;
	}

	public static synchronized void setTipoIntervento(
			TipiIntervento tipoIntervento, int posizionePaziente,
			int posizioneIntervento) {
		lista.get(posizionePaziente).getTipoIntervento()
				.set(posizioneIntervento, tipoIntervento);
	}

	public static void removeIntervento(int position) {
		lista.remove(position);
	}

}
