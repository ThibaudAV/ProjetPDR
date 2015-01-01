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
	
	/*
	 * Constructeur d'une chemin
	 */
	public Track()
	{
		trackSegs = new ArrayList<TrackSeg>();
	}
	
	/*
	 * Ajoute une partie d'un chemin
	 */
	public void addTrackSeg(TrackSeg trackSeg)
	{
		trackSegs.add(trackSeg);
	}
	
	/*
	 * Supprime une partie d'un chemin
	 */
	public void removeTrackSeg(TrackSeg trackSeg)
	{
		trackSegs.remove(trackSeg);
	}
	
	/*
	 * Liste les parties d'un chemin
	 */
	public List<TrackSeg> getTrackSegs()
	{
		return trackSegs;
	}
	
	/*public LatLngBounds getLatLngBounds()
	{
		for (TrackSeg ts : this.getTrackSegs())
		{
			bounds.include(ts.getLatLngBounds().getCenter());
		}
		
		return bounds.build();
	}*/

}
