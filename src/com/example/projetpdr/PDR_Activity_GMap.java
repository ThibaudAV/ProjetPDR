package com.example.projetpdr;

import gpx.GPX;

import java.io.File;

import pdr.DeviceAttitudeHandler;
import pdr.GoogleMapTracer;
import pdr.StepDetectionHandler;
import pdr.StepDetectionHandler.StepDetectionListener;
import pdr.StepPositioningHandler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class PDR_Activity_GMap extends Activity implements 	StepDetectionListener
															,OnMapClickListener {
	
	private StepDetectionHandler stepDetectionHandler;
	private DeviceAttitudeHandler deviceAttitudeHandler;
	private StepPositioningHandler stepPositioningHandler;
	private int nbPas = 0;
	private GoogleMapTracer googleMapTracer;
	private GoogleMap myMap;
	private boolean readyToGo = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdr_gmap);
		
		Intent intent = getIntent();
		// get Sensor service
		SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		// Init de la detection des pas
		stepDetectionHandler = new StepDetectionHandler(sm);
		stepDetectionHandler.setStepDetectionListener(this);
		
		// Init du capteur de rotation 
		deviceAttitudeHandler = new DeviceAttitudeHandler(sm) ;
		
		// Init de la methode de calcul de position
		stepPositioningHandler = new StepPositioningHandler();
		
		// On recupére la Map
		MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
		myMap = mapFragment.getMap();
		
		myMap.setMyLocationEnabled(true);
		// on zoom la camera sur la derniére position de l'utilisateur 
        Location location = getLastBestLocation();
        if (location != null)
        {
	        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
	        		new LatLng(location.getLatitude(), location.getLongitude()),
	        		19));
        }
		
		// on Set le ClickListener quand on click sur la Map
		myMap.setOnMapClickListener(this);
		
		// on Init les Traces ( Polyline ) sur la map 
		googleMapTracer = new GoogleMapTracer(myMap);
	}
	@Override
	protected void onPause() {
		stepDetectionHandler.stop();
		deviceAttitudeHandler.stop();
		super.onPause();
	}
	@Override
	protected void onResume() {
		stepDetectionHandler.start();
		deviceAttitudeHandler.start();
		super.onResume();
	}
	
	private Location getLastBestLocation() {
		Location maLocation;
		LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

	    long GPSLocationTime = 0;
	    if (null != locationGPS) { 
	    	GPSLocationTime = locationGPS.getTime(); 
	    }

	    long NetLocationTime = 0;

	    if (null != locationNet) {
	        NetLocationTime = locationNet.getTime();
	    }
	    
	    if(GPSLocationTime > NetLocationTime) {
	    	maLocation = locationGPS;
	    }
	    else {
	    	maLocation = locationNet;
	    }
	    
	    
	    if (maLocation == null) {
	    	
	    	maLocation = new Location("");
	    	maLocation.setLatitude(45.187);
	    	maLocation.setLongitude(5.726);
	    }
//	    if ( 0 < GPSLocationTime - NetLocationTime ) {
//	        return locationGPS;
//	    }
//	    else {
//	        return locationNet;
//	    }
	    return maLocation;
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pdr, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.exporter_gpx) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private void exportToGPX(GPX gpx,File file) {
		
		
	}

	// A chaques nouveaux pas 
	@Override
	public void onNewStep() {
		// si l'utilisateur a defini le 1er point 
		if(readyToGo) {
			nbPas++;
			// on met a jour la current location avec la rotation et la taille d'un pas 
			float bearing = deviceAttitudeHandler.getYaw();
			stepPositioningHandler.computeNextStep((float) 0.8, bearing);
	
			Toast.makeText(PDR_Activity_GMap.this, "Pas "+nbPas+" : [0.8, "+bearing+"]", Toast.LENGTH_SHORT).show();
	
			LatLng point =  new LatLng(stepPositioningHandler.getmCurrentLocation().getLatitude(), stepPositioningHandler.getmCurrentLocation().getLongitude());
			
			//la caméra de la GoogleMap suive la position de l’utilisateur à chaque nouveau pas.
			myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,myMap.getCameraPosition().zoom));
			
			// on ajoute un nouveau point au segment 
			googleMapTracer.newPoint(point);
		}
	}

	
	
	// Quand on click sur la map on cree un nouveau segement 
	@Override
	public void onMapClick(LatLng point) {
		
		Toast.makeText(PDR_Activity_GMap.this, "Nouveau Segement", Toast.LENGTH_SHORT).show();
		// nouveau segment
		googleMapTracer.newSegment(point);
		// on met a jour la Current Location
		
		stepPositioningHandler.setmCurrentLocation(point.latitude,point.longitude);
		
		readyToGo = true;
	}
	
	
}
