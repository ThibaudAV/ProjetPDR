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
import android.os.Bundle;
import android.widget.TextView;

public class GpxViewer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

	    Intent intent = getIntent();
	    
		setContentView(R.layout.activity_gpx_viewer);
		
		
		
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
