package com.example.projetpdr;

import pdr.DeviceAttitudeHandler;
import pdr.DeviceAttitudeHandler.DeviceAttitudeListener;
import pdr.StepDetectionHandler;
import pdr.StepDetectionHandler.StepDetectionListener;
import pdr.StepPositioningHandler;
import viewer.GoogleMapTracer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class PDR_GMap_Activity extends Activity implements 	StepDetectionListener
															,OnMapClickListener {
	
	private StepDetectionHandler stepDetectionHandler;
	private DeviceAttitudeHandler deviceAttitudeHandler;
	private StepPositioningHandler stepPositioningHandler;
	private int nbPas = 0;
	private GoogleMapTracer googleMapTracer;
	private GoogleMap myMap;
	
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// A chaques nouveaux pas 
	public void onNewStep() {
		
		nbPas++;
		// on met a jour la current location
		float bearing = deviceAttitudeHandler.getYaw();
		stepPositioningHandler.computeNextStep((float) 0.8, bearing);

		Toast.makeText(PDR_GMap_Activity.this, "Pas "+nbPas+" : [0.8, "+bearing+"]", Toast.LENGTH_SHORT).show();

		
		LatLng point =  new LatLng(stepPositioningHandler.getmCurrentLocation().getLatitude(), stepPositioningHandler.getmCurrentLocation().getLongitude());

		
		// on ajoute un nouveau point au segment 
		googleMapTracer.newPoint(point);
	}

	
	
	// Quand on click sur la map on cree un nouveau segement 
	public void onMapClick(LatLng point) {
		
		Toast.makeText(PDR_GMap_Activity.this, "Nouveau Segement", Toast.LENGTH_SHORT).show();
		// nouveau segment
		googleMapTracer.newSegment(point);
		// on met a jour la Current Location
		
		stepPositioningHandler.setmCurrentLocation(point.latitude,point.longitude);
	}
	
	
}
