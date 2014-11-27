package pdr;

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
				
		if( mStepDetectionListner != null)
		{
			
			if(linearAccY > 1.5 && !ifPasok){
				ifPasok = true;
				mStepDetectionListner.onNewStep();
			}
			if(linearAccY < -0.6 && ifPasok) {
				ifPasok = false;
			}
			exLinearAcc = linearAccY;
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