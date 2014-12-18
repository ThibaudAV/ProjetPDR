package pdr;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepDetectionHandler implements SensorEventListener{
	private SensorManager mSensorManager;
	private Sensor mSensor;
	float exLinearAcc = 0;
	boolean ifPasok = false;
	
	private float[] acceleration = new float[3];
	private List<Float> acceleration_moy = new ArrayList<Float>();
	
	public StepDetectionHandler(SensorManager sm) {
		this.mSensorManager = sm;
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
	}
	
	public void start(){
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void stop(){
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		
		
		System.arraycopy(event.values, 0, acceleration, 0, event.values.length);
		float linearAccY = acceleration[1];
		

		acceleration_moy.add(linearAccY);
		
		if(acceleration_moy.size() >= 4)
			acceleration_moy.remove(0);
		
		float somme = 0;
		
		for(float val : acceleration_moy) {
			somme += val;
		}
		float moy = somme / acceleration_moy.size();
		
		if( mStepDetectionListner != null)
		{
			
			if(moy > 1.2 && !ifPasok){
				ifPasok = true;
				mStepDetectionListner.onNewStep();
			}
			if(moy < -0.4 && ifPasok) {
				ifPasok = false;
			}
			exLinearAcc = moy;
		}
		
	}
	private StepDetectionListener mStepDetectionListner;
	
	public void setStepDetectionListener(StepDetectionListener listener) {
		mStepDetectionListner = listener;
	}
	
	public interface StepDetectionListener {
		public void onNewStep();
	}
	

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	

}