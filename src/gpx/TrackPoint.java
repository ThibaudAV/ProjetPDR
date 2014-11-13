package gpx;

public class TrackPoint {
	private double latitude;	
	private double longitude;
		
	public TrackPoint(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
	}
	
	public double getLatitude() {
			return latitude;
	}
	
	public double getLongitude() {
			return longitude;
	}

}
