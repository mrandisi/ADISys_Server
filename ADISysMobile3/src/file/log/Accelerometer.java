package file.log;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.IBinder;

public class Accelerometer extends Service {

	private static float xAcc;
	private static float yAcc;
	private static float zAcc;
	private static SensorManager manager;

	@Override
	public void onCreate() {
		super.onCreate();
		onStart();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		manager.unregisterListener(mySensorListener);
	}

	public void onStart() {
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor accelerometro = manager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		manager.registerListener(mySensorListener, accelerometro,
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	SensorEventListener mySensorListener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		public void onSensorChanged(SensorEvent valori) {
			setxAcc(valori.values[0]);// Valore X
			setyAcc(valori.values[1]);// Valore Y
			setzAcc(valori.values[2]);// Valore Z
		}

	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static float getxAcc() {
		return xAcc;
	}

	@SuppressWarnings("static-access")
	public void setxAcc(float xAcc1) {
		this.xAcc = xAcc1;
	}

	public static float getyAcc() {
		return yAcc;
	}

	public static float getzAcc() {
		return zAcc;
	}

	@SuppressWarnings("static-access")
	public void setyAcc(float yAcc1) {
		this.yAcc = yAcc1;
	}

	@SuppressWarnings("static-access")
	public void setzAcc(float zAcc1) {
		this.zAcc = zAcc1;
	}
}