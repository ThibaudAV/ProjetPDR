package gpx;

public class TrackPoint {
	private double latitude;	
	private double longitude;
		
	/*
	 * Constructeur d'un point avec ses coordonn�es
	 */
	public TrackPoint(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
	}
	
	/*
	 * Accesseur � la latitude d'un point
	 */
	public double getLatitude() {
			return latitude;
	}
	
	/*
	 * Accesseur � la longitude d'un point
	 */
	public double getLongitude() {
			return longitude;
	}

}
