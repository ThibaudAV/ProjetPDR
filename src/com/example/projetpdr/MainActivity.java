package com.example.projetpdr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;




public class MainActivity extends Activity  implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button_gpx_view = (Button) findViewById(R.id.button_gpx_view);
		button_gpx_view.setOnClickListener(this);
		
		Button button_PDR = (Button) findViewById(R.id.button_PDR);
		button_PDR.setOnClickListener(this);

		Button button_PDR_gMap = (Button) findViewById(R.id.button_pdr_gmap);
		button_PDR_gMap.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View view) {
		Intent intent;
		
		int id = view.getId();
		if (id == R.id.button_gpx_view) {
//			Toast.makeText(MainActivity.this, "Button GPX Viewer", Toast.LENGTH_SHORT).show();
			intent = new Intent(MainActivity.this, GpxViewer.class);
			startActivity(intent);
		} else if (id == R.id.button_PDR) {
//			Toast.makeText(MainActivity.this, "Button PDR Viewer", Toast.LENGTH_SHORT).show();
			intent = new Intent(MainActivity.this, PDRActivity.class);
			startActivity(intent);
			
			
		} else if (id == R.id.button_pdr_gmap) {
//			Toast.makeText(MainActivity.this, "Button PDR Viewer", Toast.LENGTH_SHORT).show();
			intent = new Intent(MainActivity.this, PDR_GMap_Activity.class);
			startActivity(intent);
		}

	}
}
