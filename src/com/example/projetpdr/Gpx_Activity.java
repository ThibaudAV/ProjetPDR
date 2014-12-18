package com.example.projetpdr;	

import gpx.GPX;
import gpx.StackOverflowXmlParser;

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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class Gpx_Activity extends Activity {

	private GoogleMap myMap;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    Intent intent = getIntent();
	    
		setContentView(R.layout.activity_gpx_viewer);
		
		
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
		
		 myMap = mapFragment.getMap();
		 
		 
		 
		 LatLng grenoble = new LatLng(45.187, 5.726);

	        myMap.setMyLocationEnabled(true);
	        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(grenoble, 12));

	       /* myMap.addMarker(new MarkerOptions()
	                .title("Grenoble")
	                .snippet("The most populous city in France.")
	                .position(grenoble)); */
	        
	        // Other supported types include: MAP_TYPE_NORMAL,
	        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
	        myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		 
	     
	        
	        myMap.addMarker(new MarkerOptions()
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.common_signin_btn_icon_disabled_focus_light))
            .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
            .position(new LatLng(45.189, 5.704)));
	        
	        Polyline chemin1 = myMap.addPolyline(new PolylineOptions()
	        .add(new LatLng(45.189, 5.704), new LatLng(45.188, 5.725), new LatLng(45.191, 5.733))
	        .width(5)
	        .color(Color.RED));
	        
	        
	        Polyline chemin2 = myMap.addPolyline(new PolylineOptions()
	        .add(new LatLng(45.193, 5.740), new LatLng(45.196, 5.748))
	        .width(5)
	        .color(Color.GREEN));
		
		
		System.out.println("=============== DEBUT ===============");
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
		System.out.println("=============== FIN ===============");	
		
		
		
		
		
		
	}
	
}
