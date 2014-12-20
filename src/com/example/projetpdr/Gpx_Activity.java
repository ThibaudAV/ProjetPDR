package com.example.projetpdr;	

import gpx.GPX;
import gpx.StackOverflowXmlParser;
import gpx.Track;
import gpx.TrackPoint;
import gpx.TrackSeg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class Gpx_Activity extends Activity {

	private GoogleMap myMap;
	private LatLngBounds.Builder llbBuilder =  LatLngBounds.builder();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    Intent intent = getIntent();
	    
		setContentView(R.layout.activity_gpx_viewer);
		
		//Cr�ation de la Google Map
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
		
		 myMap = mapFragment.getMap();
		 
		 //Latitude et Longitude de Grenoble pour centrer la map au d�pammare de l'application
		// LatLng grenoble = new LatLng(45.187, 5.726);

	        myMap.setMyLocationEnabled(true);
	        
	        //Remplac� pour centrer la carte sur la Trace GPX
	        //myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(grenoble, 12));

	        //Ajout d'un marqueur sur la carte de Grenoble
	       /* myMap.addMarker(new MarkerOptions()
	                .title("Grenoble")
	                .snippet("The most populous city in France.")
	                .position(grenoble)); */
	        
	        
	        // Other supported types include: MAP_TYPE_NORMAL,
	        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
	        myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		 
	     
	       
	        
	        /* Test d'affichage des Polylines
	         * 
	         * Polyline chemin1 = myMap.addPolyline(new PolylineOptions()
	        .add(new LatLng(45.189, 5.704), new LatLng(45.188, 5.725), new LatLng(45.191, 5.733))
	        .width(5)
	        .color(Color.RED));
	        
	        
	        Polyline chemin2 = myMap.addPolyline(new PolylineOptions()
	        .add(new LatLng(45.191, 5.733), new LatLng(45.196, 5.748))
	        .width(5)
	        .color(Color.GREEN));*/
	        
	        
	        
	        try {
				//InputStream is = new FileInputStream("/mnt/sdcard/bikeandrun.gpx");
	        	
	        	//Fichier gpx � afficher sur la carte
	        	InputStream is = getAssets().open("bikeandrun.gpx");

	        	//Fichier gpx pars�
				GPX gpx = StackOverflowXmlParser.parse(is);
				
				// Pour chaque Track
				 for (Track t : gpx.getTracks())
				 {
					 
					 //Pour chaque TrackSeg
					for (TrackSeg ts : t.getTrackSegs())
					{
						//Cr�ation d'options de Polyline (couleur et �paisseur)
						PolylineOptions chemin = new PolylineOptions();
						chemin.color(Color.GREEN);
						chemin.width(5);
						
						//Pour chaque point
						for (TrackPoint tp : ts.getTrackPoints())
						{
							//Ajout du point dans les options
							
							LatLng point = new LatLng(tp.getLatitude(), tp.getLongitude());
					        chemin.add(point); 
					        llbBuilder.include(point);
						}
						
						// Ajout d'une polyline � la carte avec les options de la variable chemin
						myMap.addPolyline(chemin);
						
						// Zoom sur le trac�
						if (this.llbBuilder != null) {
							myMap.setOnCameraChangeListener(new OnCameraChangeListener() {
								@Override
								public void onCameraChange(CameraPosition arg0) {
									myMap.moveCamera(CameraUpdateFactory.newLatLngBounds(llbBuilder.build(), 20));
									myMap.setOnCameraChangeListener(null);
								}
							});
						}
					}
					
					
				 }
				 
				 
				 
				 
			} catch (FileNotFoundException e) {
				System.out.println("Erreur : " + e.getMessage());
			} catch (XmlPullParserException e) {
				System.out.println("Erreur : " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Erreur : " + e.getMessage());
			}
	        
	        
	       
	        
		
		
	/*	Utiliser pour r�aliser des tests lors du d�veloppement du Parseur
	 * 
	 * 
	 * System.out.println("=============== DEBUT ===============");
		try {
			InputStream is = new FileInputStream("/mnt/sdcard/bikeandrun.gpx");
			GPX gpx = StackOverflowXmlParser.parse(is);
		} catch (FileNotFoundException e) {
			System.out.println("Erreur : " + e.getMessage());
		} catch (XmlPullParserException e) {
			System.out.println("Erreur : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		System.out.println("=============== FIN ===============");	*/
		
		
		
		
		
		
	}
	
}
