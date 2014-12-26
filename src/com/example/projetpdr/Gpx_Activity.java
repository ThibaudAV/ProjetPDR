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
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;


public class Gpx_Activity extends Activity {

	private GoogleMap myMap;
	private LatLngBounds.Builder llbBuilder =  LatLngBounds.builder();
	
	
	
	
	
	public GPX lireFichierGPX(String file) {
	try {
			InputStream ips = new FileInputStream(file);
			//InputStream ips = getAssets().open("bikeandrun.gpx");
			GPX gpx = StackOverflowXmlParser.parse(ips);
			return gpx;
		} catch (FileNotFoundException e) {
		System.out.println("Erreur : " + e.getMessage());
		} catch (XmlPullParserException e) {
		System.out.println("Erreur : " + e.getMessage());
		} catch (IOException e) {
		System.out.println("Erreur : " + e.getMessage());
		} 
	return null;
	}

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    Intent intent = getIntent();
	    
		setContentView(R.layout.activity_gpx_viewer);
		
		//Création de la Google Map
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
		
		 myMap = mapFragment.getMap();
		 
	        myMap.setMyLocationEnabled(true);
	            
	        // Mattre la carte en Satellite: MAP_TYPE_NORMAL,
	        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
	        myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		         
	        
	       
				//InputStream is = new FileInputStream("/mnt/sdcard/bikeandrun.gpx");
	        	
	        	//Fichier gpx à afficher sur la carte
	        	Uri uri = getIntent().getData();
	        	GPX gpx = lireFichierGPX(uri.getPath());
	        	
				
				// Pour chaque Track
				 for (Track t : gpx.getTracks())
				 {
					 
					 //Pour chaque TrackSeg
					for (TrackSeg ts : t.getTrackSegs())
					{
						//Création d'options de Polyline (couleur et épaisseur)
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
						
						// Ajout d'une polyline à la carte avec les options de la variable chemin
						myMap.addPolyline(chemin);
						
						// Zoom sur le tracé
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
				
	        
	        
	       
				 }}}
