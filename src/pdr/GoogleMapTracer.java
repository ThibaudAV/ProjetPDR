package pdr;

import java.util.List;

import android.graphics.Color;

import com.example.projetpdr.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class GoogleMapTracer {
	private GoogleMap gMap;

	private Polyline line = null;

	public GoogleMapTracer(GoogleMap _gMap) {
		gMap = _gMap;
		line = gMap.addPolyline(new PolylineOptions());
	}
	
	// cree un nouveau segment avec les marker (icon)
	public void newSegment(LatLng point) {
		List<LatLng> points = line.getPoints();

		// On ajoute un marker pour la fin du segment precedant si il y en a un
		if(points.size() > 0) {
			// on recupére le dernier points du segement 
			LatLng lastPoint = points.get(points.size()-1);
			
			// Instantiating the class MarkerOptions to plot marker on the map
			MarkerOptions markerOptions = new MarkerOptions();
			// Setting latitude and longitude of the marker position
			markerOptions.position(lastPoint);
			// Setting titile of the infowindow of the marker
			markerOptions.title("Position");
			// Setting the content of the infowindow of the marker
			markerOptions.snippet("Latitude:"+lastPoint.latitude+","+"Longitude:"+lastPoint.longitude);
			markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.finish_flag));
	
			// Adding the marker to the map
			gMap.addMarker(markerOptions);
		}
		
		// on cree le nouveu segment 
		line = gMap.addPolyline(new PolylineOptions()
		.add(point)
		.width(5)
		.color(Color.RED));

		// On ajoute un marker pour le debut du segment 

		// Instantiating the class MarkerOptions to plot marker on the map
		MarkerOptions markerOptions = new MarkerOptions();
		// Setting latitude and longitude of the marker position
		markerOptions.position(point);
		// Setting titile of the infowindow of the marker
		markerOptions.title("Position");
		// Setting the content of the infowindow of the marker
		markerOptions.snippet("Latitude:"+point.latitude+","+"Longitude:"+point.longitude);

		// Adding the marker to the map
		gMap.addMarker(markerOptions);
	}

	// on ajoute un point au segment courrant 
	public void newPoint(LatLng point) {
		
		List<LatLng> points = line.getPoints();
		points.add(point);

		line.setPoints(points);
		
	}

}
