/*
 * 
 */
package adisys.server.business;

import adisys.server.presentation.frontController.Fc;
import java.util.Date;
import adisys.server.utility.DateFormatConverter;

/**
 * The Class TORilevazione.
 * Classe entità utilizzata per creare oggetti 
 * contenenti i dati di una rilevazione 
 * (Timestamp + GPS + Accelerometro).
 * 
 */
public class TORilevazione {
	// Costanti

	/** The timestamp. */
	private Date timestamp;

	/** The gps latitude. */
	private double gpsLatitude;

	/** The gps longitude. */
	private double gpsLongitude;

	/** The gps altitude. */
	private double gpsAltitude;

	/** The gps accuracy. */
	private double gpsAccuracy;

	/** The acc x. */
	private double accX;

	/** The acc y. */
	private double accY;

	/** The acc z. */
	private double accZ;

	/**
	 * Instantiates a new TO rilevazione.
	 */
	public TORilevazione() {
		timestamp = new Date();
	}

	/**
	 * Gets the timestamp.
	 * 
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * Gets the gps latitude.
	 * 
	 * @return the gps latitude
	 */
	public double getGpsLatitude() {
		return gpsLatitude;
	}

	/**
	 * Gets the gps longitude.
	 * 
	 * @return the gps longitude
	 */
	public double getGpsLongitude() {
		return gpsLongitude;
	}

	/**
	 * Gets the gps altitude.
	 * 
	 * @return the gps altitude
	 */
	public double getGpsAltitude() {
		return gpsAltitude;
	}

	/**
	 * Gets the gps accuracy.
	 * 
	 * @return the gps accuracy
	 */
	public double getGpsAccuracy() {
		return gpsAccuracy;
	}

	/**
	 * Gets the acc x.
	 * 
	 * @return the acc x
	 */
	public double getAccX() {
		return accX;
	}

	/**
	 * Gets the acc y.
	 * 
	 * @return the acc y
	 */
	public double getAccY() {
		return accY;
	}

	/**
	 * Gets the acc z.
	 * 
	 * @return the acc z
	 */
	public double getAccZ() {
		return accZ;
	}

	/**
	 * Sets the timestamp.
	 * 
	 * @param timestamp
	 *            the new timestamp
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Sets the gps latitude.
	 * 
	 * @param gpsLatitude
	 *            the new gps latitude
	 */
	public void setGpsLatitude(double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	/**
	 * Sets the gps longitude.
	 * 
	 * @param gpsLongitude
	 *            the new gps longitude
	 */
	public void setGpsLongitude(double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	/**
	 * Sets the gps altitude.
	 * 
	 * @param gpsAltitude
	 *            the new gps altitude
	 */
	public void setGpsAltitude(double gpsAltitude) {
		this.gpsAltitude = gpsAltitude;
	}

	/**
	 * Sets the gps accuracy.
	 * 
	 * @param gpsAccuracy
	 *            the new gps accuracy
	 */
	public void setGpsAccuracy(double gpsAccuracy) {
		this.gpsAccuracy = gpsAccuracy;
	}

	/**
	 * Sets the acc x.
	 * 
	 * @param accX
	 *            the new acc x
	 */
	public void setAccX(double accX) {
		this.accX = accX;
	}

	/**
	 * Sets the acc y.
	 * 
	 * @param accY
	 *            the new acc y
	 */
	public void setAccY(double accY) {
		this.accY = accY;
	}

	/**
	 * Sets the acc z.
	 * 
	 * @param accZ
	 *            the new acc z
	 */
	public void setAccZ(double accZ) {
		this.accZ = accZ;
	}

	/**
	 * Sets the timestamp from string.
	 * 
	 * @param newTimestamp
	 *            the new timestamp
	 * @param formato
	 *            the formato
	 * @return true, if successful
	 */
	public boolean setTimestampFromString(String newTimestamp, String formato) {
		if (DateFormatConverter.parseable(newTimestamp, formato)) {
			timestamp.setTime(DateFormatConverter.dateString2long(newTimestamp,
					formato));
			return true;
		} else {
			System.out.println(Fc.MessageBundle("MsgDialog1640"));
			return false;
		}
	}

}
