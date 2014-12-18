/**
 * 
 */
package gpx;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * @author Mathilde et Thibaud
 *
 */
public class Track {
	
	public List<TrackSeg> trackSegs;
	private LatLngBounds.Builder bounds;
	
	public Track()
	{
		trackSegs = new ArrayList<TrackSeg>();
	}
	
	public void addTrackSeg(TrackSeg trackSeg)
	{
		trackSegs.add(trackSeg);
	}
	
	public void removeTrackSeg(TrackSeg trackSeg)
	{
		trackSegs.remove(trackSeg);
	}
	
	public List<TrackSeg> getTrackSegs()
	{
		return trackSegs;
	}
	
	public LatLngBounds getLatLngBounds()
	{
		for (TrackSeg ts : this.getTrackSegs())
		{
			bounds.include(ts.getLatLngBounds().getCenter());
		}
		
		return bounds.build();
	}

}
