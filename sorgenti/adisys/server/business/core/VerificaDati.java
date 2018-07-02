/*
 * 
 */

package adisys.server.business.core;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import adisys.server.business.TOInterventoCompleto;
import adisys.server.business.TORilevazione;
import adisys.server.integration.Infermieri;
import adisys.server.utility.ADISysTableRendererVerifica;
import adisys.server.utility.DateFormatConverter;
import adisys.server.utility.UIFeedbackException;

/**
 * The Class VerificaDati.
 * Classe di business incaricata di gestire 
 * i metodi inerenti alla verifica dei dati 
 * del file di journaling
 * 
 */
public class VerificaDati {

	/**
	 * Carica file.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the object
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public Object caricaFile(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		String nomeFile = (String) parametri.get(0).get(1);
		return (Object) Journaling.caricaFile(nomeFile);
	}

	/**
	 * Gets the dati infermiere.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the dati infermiere
	 */
	public void getDatiInfermiere(ArrayList<ArrayList<Object>> parametri) {
		JLabel labelInfermiere = (JLabel) parametri.get(0).get(1);
		String stringaInfermiere = (String) parametri.get(1).get(1);

		int intervento = Journaling.getListaInterventi().get(0)
				.getIDInfermiere();
		stringaInfermiere += Infermieri.getInfermiere(intervento).getNome();
		stringaInfermiere += " "
				+ Infermieri.getInfermiere(
						Journaling.getListaInterventi().get(0)
								.getIDInfermiere()).getCognome();
		labelInfermiere.setText(stringaInfermiere);
	}

	/**
	 * Popola tabella attivita.
	 * 
	 * @param parametri
	 *            the parametri
	 */
	public void popolaTabellaAttivita(ArrayList<ArrayList<Object>> parametri) {
		JTable tabella = (JTable) parametri.get(0).get(1);
		final String FORMATO_DATA_GUI = "dd/MM/yyyy";
		final String FORMATO_ORA_GUI = "HH:mm:ss";

		// Costruzione della matrice dei dati
		ArrayList<TOInterventoCompleto> listaInterventi = Journaling
				.getListaInterventi();

		// TableModel per popolamento tabella
		DefaultTableModel modello = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Settaggio colonne
		String titoli[] = { "IL", "DALLE", "ALLE", "LUOGO", "PAZIENTE", "GPS",
				"ACCEL." };

		modello.setColumnCount(titoli.length);
		modello.setColumnIdentifiers(titoli);

		if (!listaInterventi.isEmpty()) {
			for (TOInterventoCompleto i : listaInterventi) {
				String riga[] = {
						i.getDataDaFormato(FORMATO_DATA_GUI),
						i.getOraInizioDaFormato(FORMATO_ORA_GUI),
						i.getOraFineDaFormato(FORMATO_ORA_GUI),
						i.getCivico() + "- " + i.getCitta(),
						i.getPaziente().getNome() + " "
								+ i.getPaziente().getCognome(),
						i.getStatoVerificaGPS().toString(),
						i.getStatoVerificaAccelerometro().toString() };

				modello.addRow(riga);

			}
		}

		tabella.setModel(modello);

		int colonnaGraficaGPS = 5;
		int colonnaGraficaACC = 6;

		ADISysTableRendererVerifica renderer = new ADISysTableRendererVerifica(
				colonnaGraficaGPS, colonnaGraficaACC);
		tabella.setDefaultRenderer(tabella.getColumnClass(0), renderer);
	}

	/**
	 * Popola tabella log.
	 * 
	 * @param parametri
	 *            the parametri
	 */
	public void popolaTabellaLog(ArrayList<ArrayList<Object>> parametri) {
		JTable tabella = (JTable) parametri.get(0).get(1);
		int indiceIntervento = (int) parametri.get(0).get(3);

		ArrayList<TOInterventoCompleto> listaInterventi = Journaling
				.getListaInterventi();
		DefaultTableModel modelloLog = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		modelloLog.addColumn("Timestamp");
		modelloLog.addColumn("Latitude");
		modelloLog.addColumn("Longitude");
		modelloLog.addColumn("Altitude");
		modelloLog.addColumn("Accuracy");
		modelloLog.addColumn("Accel X");
		modelloLog.addColumn("Accel Y");
		modelloLog.addColumn("Accel Z");

		int i = 0;
		while (listaInterventi.get(indiceIntervento).getLog(i) != null) {
			TORilevazione u = listaInterventi.get(indiceIntervento).getLog(i);
			Object[] nuovaRiga = {
					DateFormatConverter.long2dateString(u.getTimestamp()
							.getTime(), "dd/MM/yyyy-HH:mm:ss"),
					u.getGpsLatitude(), u.getGpsLongitude(),
					u.getGpsAltitude(), u.getGpsAccuracy(), u.getAccX(),
					u.getAccY(), u.getAccZ() };
			modelloLog.addRow(nuovaRiga);
			i++;
		}
		tabella.setModel(modelloLog);
	}

	/**
	 * Gets the lista journaling.
	 * 
	 * @param parametri
	 *            the parametri
	 * @return the lista journaling
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void getListaJournaling(ArrayList<ArrayList<Object>> parametri)
			throws UIFeedbackException {
		@SuppressWarnings("unchecked")
		JComboBox<String> cbLista = (JComboBox<String>) parametri.get(0).get(1);
		String[] listaFile = Journaling.listaFileJournaling();

		if (listaFile == null) {
			throw new UIFeedbackException("MsgDialog470");
		} else {
			DefaultComboBoxModel<String> modello = new DefaultComboBoxModel<>();
			for (String s : listaFile) {
				modello.addElement(s);
			}
			cbLista.setModel(modello);
		}

	}

	/**
	 * Show info intervento completo.
	 * 
	 * @param parametri
	 *            the parametri
	 * @throws UIFeedbackException
	 *             the UI feedback exception
	 */
	public void showInfoInterventoCompleto(
			ArrayList<ArrayList<Object>> parametri) throws UIFeedbackException {
		int indiceTabella = (int) parametri.get(0).get(1);
		int indiceLista = indiceTabella;
		// Trace
		System.out.println("ControllerVerifica - Prelievo elemento "
				+ indiceTabella + " -> " + indiceLista);

		ArrayList<String> replacementArray = new ArrayList<String>();
		replacementArray.add(Journaling.getIntervento(indiceLista).toString());
		throw new UIFeedbackException("Generic", replacementArray);
	}

}
