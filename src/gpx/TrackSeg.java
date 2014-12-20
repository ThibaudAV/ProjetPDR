/**
 * 
 */
package gpx;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


/**
 * @author Mathilde
 *
 */
public class TrackSeg {
	
	public List<TrackPoint> trackPoints;
	private LatLngBounds.Builder bounds;
	
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

	/*public LatLngBounds getLatLngBounds()
	{
		for (TrackPoint tp : this.getTrackPoints())
		{
			//Erreur s'arrete de lire après avoir récupéré le premier point
			System.out.println(tp.getLatitude() + ", "+ tp.getLongitude());
			bounds.include(new LatLng(tp.getLatitude(), tp.getLongitude()));
		}
		return bounds.build();
	}*/
	
}
