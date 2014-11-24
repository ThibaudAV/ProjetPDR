package gpx;

import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;


public class StackOverflowXmlParser {
    // We don't use namespaces
    private static final String ns = null;
   
    public static GPX parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readGPX(parser);
        } finally {
            in.close();
        }
    } 
    
    
    
 // Processes GPX tags.
    private static GPX readGPX(XmlPullParser parser) throws XmlPullParserException, IOException {
       GPX entries = new GPX();
       System.out.println("Coucou.");

        parser.require(XmlPullParser.START_TAG, ns, "gpx");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the gpx tag
            if (name.equals("trk")) {
  	               entries.addTrack(readTrack(parser));
  	             System.out.println("Coucou..");
            } else {
            	 System.out.println("Coucou...");
                skip(parser);
            }
        }  
        return entries;    
    }
    
    
    
 // Processes Track tags.
    private static Track readTrack(XmlPullParser parser) throws XmlPullParserException, IOException {
        Track entries = new Track();
        
        System.out.println("Coucou+");

         parser.require(XmlPullParser.START_TAG, ns, "trk");
         while (parser.next() != XmlPullParser.END_TAG) {
             if (parser.getEventType() != XmlPullParser.START_TAG) {
                 continue;
             }
             String name = parser.getName();
             // Starts by looking for the trk tag
             if (name.equals("trkseg")) {
                entries.addTrackSeg(readTrackSeg(parser));
                System.out.println("Coucou++");
             } else {
            	 System.out.println("Coucou+++");
                 skip(parser);
             }
         }  
         return entries;    
     }
    
    
 // Processes TrackSeg tags.
    private static TrackSeg readTrackSeg(XmlPullParser parser) throws XmlPullParserException, IOException {
        TrackSeg entries = new TrackSeg();
        System.out.println("Coucou-");
         parser.require(XmlPullParser.START_TAG, ns, "trkseg");
         while (parser.next() != XmlPullParser.END_TAG) {
             if (parser.getEventType() != XmlPullParser.START_TAG) {
                 continue;
             }
             String name = parser.getName();
             // Starts by looking for the trkseg tag
             if (name.equals("trkpt")) {
            	 System.out.println("Coucou--");
                 entries.addTrackPoint(readTrackPoint(parser));
             } else {
            	 System.out.println("Coucou---");
                 skip(parser);
             }
         }  
         return entries;    
    }
    
    
    
    
 // Processes TrackPoint tags.
    private static TrackPoint readTrackPoint(XmlPullParser parser) throws XmlPullParserException, IOException {
        TrackPoint entries;
        double lat = 0;
        double lon = 0;

         parser.require(XmlPullParser.START_TAG, ns, "trkpt");
         
             String name = parser.getName();
             // Starts by looking for the trkseg tag
             if (name.equals("trkpt")) {                   	 
            	  lat =  Double.parseDouble(parser.getAttributeValue(null, "lat"));
            	  lon =  Double.parseDouble(parser.getAttributeValue(null, "lon"));
            	  System.out.println("Lat : " + lat +  " Lon : " + lon );
             } 
         else {
                 skip(parser);
             }   
          entries = new TrackPoint(lat, lon);
          return entries;
    }
    
    
    //Skip
    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                depth--;
                break;
            case XmlPullParser.START_TAG:
                depth++;
                break;
            }
        }
     }      
}
