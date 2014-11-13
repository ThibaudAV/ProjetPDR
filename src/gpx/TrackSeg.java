/**
 * 
 */
package gpx;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Mathilde
 *
 */
public class TrackSeg {
	
	public List<TrackPoint> trackPoints;
	
	public TrackSeg()
	{
		trackPoints = new ArrayList<TrackPoint>();
	}
	
	public void addTrackPoint(TrackPoint trackPoint)
	{
		trackPoints.add(trackPoint);
	}
	
	public void removeTrackPoint(TrackPoint trackPoint)
	{
		trackPoints.remove(trackPoint);
	}
	
	public List<TrackPoint> getTrackPoints()
	{
		return trackPoints;
	}

}
