/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.utility;

import adisys.server.business.TOInterventoCompleto;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * The Class ADISysTableRendererVerifica.
 * Modello di visualizzazione della tabella di verifica 
 * (visualizza i simboli grafici relativi allo stato di 
 * validità dei dati)
 * 
 */
public class ADISysTableRendererVerifica extends DefaultTableCellRenderer {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ind colonna gps. */
	private static int IND_COLONNA_GPS;

	/** The ind colonna acc. */
	private static int IND_COLONNA_ACC;

	/** The Constant VALORE_OK. */
	private static final String VALORE_OK = TOInterventoCompleto.StatoVerifica.verificaOK
			.toString();

	/** The Constant VALORE_KO. */
	private static final String VALORE_KO = TOInterventoCompleto.StatoVerifica.anomalia
			.toString();;

	/** The Constant VALORE_NULL. */
	private static final String VALORE_NULL = TOInterventoCompleto.StatoVerifica.nonVerificato
			.toString();;

	/**
	 * Instantiates a new ADI sys table renderer verifica.
	 * 
	 * @param colonnaGPS
	 *            the colonna gps
	 * @param colonnaACC
	 *            the colonna acc
	 */
	public ADISysTableRendererVerifica(int colonnaGPS, int colonnaACC) {
		IND_COLONNA_ACC = colonnaACC;
		IND_COLONNA_GPS = colonnaGPS;
	}

	// @Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent
	 * (javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel label = (JLabel) super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		String status = (String) value;
		Icon icon = null;

		// Icone per la colonna GPS
		if (column == IND_COLONNA_GPS) {
			if (value.equals(VALORE_OK)) {
				icon = new ImageIcon(getClass().getResource(
						"/adisys/server/img/spie/GPSOK.png"));
			} else if (value.equals(VALORE_KO)) {
				icon = new ImageIcon(getClass().getResource(
						"/adisys/server/img/spie/GPSKO.png"));
			} else if (value.equals(VALORE_NULL)) {
				icon = new ImageIcon(getClass().getResource(
						"/adisys/server/img/spie/GPSnull.png"));
			} else {
				icon = new ImageIcon(getClass().getResource(
						"/adisys/server/img/spie/GPSerr.png"));
			}
		} else if (column == IND_COLONNA_ACC) {
			// Icone per la colonna Accelerometro
			if (value.equals(VALORE_OK)) {
				icon = new ImageIcon(getClass().getResource(
						"/adisys/server/img/spie/ACCOK.png"));
			} else if (value.equals(VALORE_KO)) {
				icon = new ImageIcon(getClass().getResource(
						"/adisys/server/img/spie/ACCKO.png"));
			} else if (value.equals(VALORE_NULL)) {
				icon = new ImageIcon(getClass().getResource(
						"/adisys/server/img/spie/ACCnull.png"));
			} else {
				icon = new ImageIcon(getClass().getResource(
						"/adisys/server/img/spie/ACCerr.png"));
			}
		}

		if (icon == null) {
			label.setText(status);
			label.setIcon(null);
		} else {
			label.setText("");
			label.setIcon(icon);
		}
		return label;
	}

}
