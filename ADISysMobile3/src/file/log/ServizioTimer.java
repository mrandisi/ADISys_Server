package file.log;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class ServizioTimer extends Service {

	private Timer timer;
	private static String accuracy;
	private static String latitude;
	private static String longitude;
	private static String altitude;
	private static float xAcc;
	private static float yAcc;
	private static float zAcc;
	private Intent gServiceIntent;
	private Intent aServiceIntent;
	private int intervallo = 60000;
	private int paziente;
	public Context cnt = this;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		gServiceIntent = new Intent(this, Gps.class);
		aServiceIntent = new Intent(this, Accelerometer.class);
		startService(gServiceIntent);
		startService(aServiceIntent);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					stampaRisultati();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		timer = new Timer();
		timer.schedule(task, 500, intervallo);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		Bundle b = intent.getExtras();
		paziente = b.getInt("paziente", 0);
		return paziente;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		timer.cancel();
		timer = null;
		stopService(gServiceIntent);
		stopService(aServiceIntent);
	}

	protected void stampaRisultati() throws Exception {
		getCoordinate();
		getPosizione();
		GestoreLog.restituisciRilevazione(latitude, longitude, altitude,
				accuracy, xAcc, yAcc, zAcc, paziente);

	}

	public static void getCoordinate() {
		setxAcc(Accelerometer.getxAcc());
		setyAcc(Accelerometer.getyAcc());
		setzAcc(Accelerometer.getzAcc());

	}

	public static void getPosizione() {
		setLatitude(Gps.getLatitude());
		setLongitude(Gps.getLongitude());
		setAltitude(Gps.getAltitude());
		setAccuracy(Gps.getAccuracy());
	}

	public static String getLatitude() {
		return latitude;
	}

	public static String getLongitude() {
		return longitude;
	}

	public static String getAltitude() {
		return altitude;
	}

	public static float getxAcc() {
		return xAcc;
	}

	public static float getyAcc() {
		return yAcc;
	}

	public static float getzAcc() {
		return zAcc;
	}

	public static String getAccuracy() {
		return accuracy;
	}

	public static void setLatitude(double latitude) {
		ServizioTimer.latitude = String.valueOf(latitude);
	}

	public static void setLongitude(double longitude) {
		ServizioTimer.longitude = String.valueOf(longitude);
	}

	public static void setAltitude(double altitude) {
		ServizioTimer.altitude = String.valueOf(altitude);
	}

	public static void setxAcc(float xAcc) {
		ServizioTimer.xAcc = xAcc;
	}

	public static void setyAcc(float yAcc) {
		ServizioTimer.yAcc = yAcc;
	}

	public static void setzAcc(float zAcc) {
		ServizioTimer.zAcc = zAcc;
	}

	public static void setAccuracy(double accuracy) {
		ServizioTimer.accuracy = String.valueOf((int) accuracy);
	}
}
