/*
 * 
 */
package adisys.server.utility;

import java.sql.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * The Class ADISysTableModel.
 * Modello di tabella che si autogenera a partire 
 * da un ResultSet. Serve a facilitare l’operazione 
 * di visualizzazione dei dati provenie nti da una 
 * query sul database
 * 
 */
public class ADISysTableModel extends AbstractTableModel implements TableModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dati. */
	ResultSet dati;

	/**
	 * Instantiates a new ADI sys table model.
	 * 
	 * @param nuoviDati
	 *            the nuovi dati
	 */
	public ADISysTableModel(ResultSet nuoviDati) {
		dati = nuoviDati;

		try {
			// Trace
			System.out.println("- Creazione modello tabella: \""
					+ dati.getMetaData().getTableName(1) + "\"");
		} catch (SQLException e1) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {

		try {
			return dati.getMetaData().getColumnCount();
		} catch (SQLException e) {
			System.out.println("ERRORE: Calcolo del numero di colonne errato.");
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {

		try {
			// Seleziona l'ultimo elemento
			dati.last();
			// Restituisce l'indice dell'elemento
			return (dati.getRow());

		} catch (SQLException e) {
			System.out
					.println("ERRORE: Calcolo del numero di righe errato. (metodo getRowCount() )");
			return 0;
		}
	}

	// @Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int riga, int colonna) {

		try {
			// Sposta il cursore alla riga desiderata (con sfasamento di 1)
			dati.absolute(riga + 1);

			// Estrae il valore nella colonna specificata e lo restituisce (con
			// sfasamento di 1)
			return dati.getObject(colonna + 1);

		} catch (SQLException e) {

			// Trace
			System.out
					.println("ERRORE: Valore dell'elemento della tabella non valido.");
			return null;
		}
	}

	// @Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rIndex, int cIndex) {
		return false;
	}

	// @Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int col) {
		try {
			return dati.getMetaData().getColumnName(col + 1);
		} catch (SQLException e) {
			return "?";
		}
	}

	/**
	 * Gets the id.
	 * 
	 * @param riga
	 *            the riga
	 * @return the id
	 */
	public Integer getID(int riga) {
		// Ricerca colonna ID
		for (int i = 0; i <= getColumnCount(); i++)
			if (getColumnName(i).equals("ID"))
				return i;
		return null;
	}

	/**
	 * Restituisce l'indice della colonna a partire dal nome della colonna
	 * ricercata <b>N.B. L'indice della prima colonna è 0, l'ultimo è
	 * numeroColonne-1.</b>
	 * 
	 * @param Nome
	 *            - Stringa con il nome della colonna
	 * @return -1 se la colonna non e' stata trovata, altrimenti l'indice della
	 *         colonna
	 */
	public int getColumnIndex(String Nome) {
		for (int i = 0; i < getColumnCount(); i++)
			if (getColumnName(i) == Nome)
				return i;
		return -1;
	}

}
