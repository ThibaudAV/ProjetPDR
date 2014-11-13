package com.example.projetpdr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GpxViewer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

	    Intent intent = getIntent();
		
	    
	    
		setContentView(R.layout.activity_gpx_viewer);
	}
	
}
