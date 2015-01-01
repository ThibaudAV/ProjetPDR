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
	
	/*
	 * Constructeur de la trace GPX
	 */
	public GPX()
	{
		tracks = new ArrayList<Track>();
	}
	
	/*
	 * Ajoute un chemin à la trace GPX
	 */
	public void addTrack(Track track)
	{
		tracks.add(track);
	}
	
	/*
	 * Supprime un chemin de la trace GPX
	 */
	public void removeTrack(Track track)
	{
		tracks.remove(track);
	}
	
	/*
	 * Accesseur à la liste des chemins de la trace GPX
	 */
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
