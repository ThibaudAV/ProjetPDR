package pdr;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class DeviceAttitudeHandler implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private float[] mRotationMatrixFromVector = new float[16];
	private float[] mRotationMatrix = new float[16];
	private float[] orientationVals = new float[3];
	
	public DeviceAttitudeHandler(SensorManager sm) {
		this.mSensorManager = sm;
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		
	}
	
	public void start(){
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void stop(){
		mSensorManager.unregisterListener(this);
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		
//		System.arraycopy(event.values, 0, rotationVector, 0, event.values.length);
		if( mDeviceAttitudeListener != null)
		{
			// Convert the rotation-vector to a 4x4 matrix.
	        SensorManager.getRotationMatrixFromVector(mRotationMatrixFromVector, event.values);
	        
			SensorManager.remapCoordinateSystem(mRotationMatrixFromVector,
	                    SensorManager.AXIS_X, SensorManager.AXIS_Z,
	                    mRotationMatrix);
	        SensorManager.getOrientation(mRotationMatrix, orientationVals);

	        // Optionally convert the result from radians to degrees
	        orientationVals[0] = (float) Math.toDegrees(orientationVals[0]);
	        orientationVals[1] = (float) Math.toDegrees(orientationVals[1]);
	        orientationVals[2] = (float) Math.toDegrees(orientationVals[2]);

	        mDeviceAttitudeListener.onDeviceAttitudeChanged(" Yaw: " + orientationVals[0] + "\n Pitch: "
	                + orientationVals[1] + "\n Roll (not used): "
	                + orientationVals[2]);
		}
		
	}

	private DeviceAttitudeListener mDeviceAttitudeListener;
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	public void setDeviceAttitudeListener(DeviceAttitudeListener listener) {
		mDeviceAttitudeListener = listener;
	}
	
	public interface DeviceAttitudeListener {
		public void onDeviceAttitudeChanged(String string);
	}

}
