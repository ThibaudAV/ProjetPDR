/**
 * 
 */
package gpx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mathilde et Thibaud
 *
 */
public class GPX {
 
	public List<Track> tracks;
	
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
	
	
}
