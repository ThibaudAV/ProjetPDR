package com.example.projetpdr;

import pdr.DeviceAttitudeHandler;
import pdr.DeviceAttitudeHandler.DeviceAttitudeListener;
import pdr.StepDetectionHandler;
import pdr.StepDetectionHandler.StepDetectionListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PDRActivity extends Activity implements StepDetectionListener, OnClickListener,DeviceAttitudeListener{
	
	StepDetectionHandler stepDetectionHandler;
	DeviceAttitudeHandler deviceAttitudeHandler;
	int nbPas = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdr);
		
		Intent intent = getIntent();
		
		SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		stepDetectionHandler = new StepDetectionHandler(sm);
		stepDetectionHandler.setStepDetectionListener(this);
		
		deviceAttitudeHandler = new DeviceAttitudeHandler(sm) ;
		deviceAttitudeHandler.setDeviceAttitudeListener(this);
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
	public void onNewStep() {
		
		nbPas++;
		
		TextView TV_nbPas = (TextView) findViewById(R.id.nbPas);
		TV_nbPas.setText( nbPas+"" );
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.resetPDR) {
			nbPas = 0;
		}
		
	}
	@Override
	public void onDeviceAttitudeChanged(float yaw) {
		TextView TV_attHandle = (TextView) findViewById(R.id.attitudeHandle);
		TV_attHandle.setText(yaw+"");
	}
	
	
}
