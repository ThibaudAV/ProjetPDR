package pdr;

import android.location.Location;

public class StepPositioningHandler {
	private Location mCurrentLocation;

	public StepPositioningHandler() {
		mCurrentLocation = new Location("");
	}
	// retourne la current location
	public Location getmCurrentLocation() {
		return mCurrentLocation;
	}

	// On set la Current Location avec une Latitude et un Longitude
	public void setmCurrentLocation(double lat, double lon) {
		this.mCurrentLocation.setLatitude(lat);
		this.mCurrentLocation.setLongitude(lon);
	}


	// met a jour la Current Location en fonction de la rotation et de la taille du pas
	public void computeNextStep(float stepSize, float bearing) {
		
		double bearingRad = Math.toRadians(bearing); //(bearing*Math.PI/180);
		
		
		float R = (float) 6378100; //Radius of the Earth en metre
		double lat =  Math.toRadians(this.mCurrentLocation.getLatitude());
		double lon = Math.toRadians(this.mCurrentLocation.getLongitude());
		
		double lat2 = Math.asin( Math.sin(lat)*Math.cos(stepSize/R) +
	            Math.cos(lat)*Math.sin(stepSize/R)*Math.cos(bearingRad));
		double lon2 = lon + Math.atan2(Math.sin(bearingRad)*Math.sin(stepSize/R)*Math.cos(lat),
	                 Math.cos(stepSize/R)-Math.sin(lat)*Math.sin(lat2));
		
		this.mCurrentLocation.setLatitude(Math.toDegrees(lat2));
		this.mCurrentLocation.setLongitude(Math.toDegrees(lon2));
	}
	
	
}
