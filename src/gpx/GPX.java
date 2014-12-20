/**
 * 
 */
package gpx;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLngBounds;

/**
 * @author Mathilde et Thibaud
 *
 */
public class GPX {
 
	public List<Track> tracks;
	private LatLngBounds.Builder bounds;
	
	public GPX()
	{
		tracks = new ArrayList<Track>();
	}
	
	public void addTrack(Track track)
	{
		tracks.add(track);
	}
	
	
	public void removeTrack(Track track)
	{
		tracks.remove(track);
	}
	
	public List<Track> getTracks() 
	{
				return tracks;
	}
	
	/*public LatLngBounds getLatLngBounds()
	{
		for (Track tt : this.getTracks())
		{
			bounds.include(tt.getLatLngBounds().getCenter());
		}
		
		return bounds.build();
	}*/
	
	
}
