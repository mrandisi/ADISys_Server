package file.log;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;

public class Gps extends Service {

	private static double accuracy;
	private static double latitude;
	private static double longitude;
	private static double altitude;
	private static String providerId = LocationManager.GPS_PROVIDER;
	private static LocationManager locationManager = null;
	private static Location location = null;

	@Override
	public void onCreate() {
		super.onCreate();
		onStart();

	}

	public void onStart() {
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(providerId)) {
			LocationProvider gpsProvider = locationManager
					.getProvider(providerId);
			if (gpsProvider != null) {
				locationManager.requestLocationUpdates(providerId, 30000, 5,
						myLocationListener);
				location = locationManager
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (location != null)
					updateLocationData(location);
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		locationManager.removeUpdates(myLocationListener);
	}

	private void updateLocationData(Location location) {

		latitude = location.getLatitude();
		longitude = location.getLongitude();
		altitude = location.getAltitude();
		accuracy = location.getAccuracy();

	}

	public LocationListener myLocationListener = new LocationListener() {

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}

		public void onLocationChanged(Location location) {
			updateLocationData(location);
		}

	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public static double getLatitude() {
		return latitude;
	}

	public static double getLongitude() {
		return longitude;
	}

	public static double getAltitude() {
		return altitude;
	}

	public static double getAccuracy() {
		return accuracy;
	}

	public static void setLatitude(double latitude) {
		Gps.latitude = latitude;
	}

	public static void setLongitude(double longitude) {
		Gps.longitude = longitude;
	}

	public static void setAltitude(double altitude) {
		Gps.altitude = altitude;
	}

	public static void setAccuracy(double accuracy) {
		Gps.accuracy = accuracy;
	}
}