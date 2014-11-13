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
public class Track {
	
	public List<TrackSeg> trackSegs;
	
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
	
	public List<TrackSeg> getTrackSeg()
	{
		return trackSegs;
	}
	
	

}
