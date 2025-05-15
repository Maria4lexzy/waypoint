package service;

import java.util.ArrayList;
import java.util.List;

import model.Point;
import model.Vessel;
import model.Waypoint;

public class DistanceCalculator {
  //helper: https://www.baeldung.com/java-find-distance-between-points
  private static final double EARTH_RADIUS = 6371.0; // in km
  private static final double BOUNDING_BOX = 0.5; // Check ships within 0.5 degrees, if it's more then its probably too far?

//  Using Haversine to calculate distance between p1 and p2
  public static double haversine(Point p1, Point p2) {
    // Convert degrees to radians
    double lat1 = Math.toRadians(p1.latitude());
    double lon1 = Math.toRadians(p1.longitude());
    double lat2 = Math.toRadians(p2.latitude());
    double lon2 = Math.toRadians(p2.longitude());

    // Difference in lat and long
    double dLat = lat2 - lat1;
    double dLon = lon2 - lon1;

    // Haversine formula: to find distance on a rounded surface
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return EARTH_RADIUS * c; // Distance in km
  }

  //  Only check ships close to the waypoint
  public static List<Vessel> vesselsInProximity(Waypoint waypoint, List<Vessel> vessels) {
    List<Vessel> nearby = new ArrayList<>();
    for (Vessel vessel : vessels) {
      if (Math.abs(vessel.location().latitude() - waypoint.location().latitude()) < BOUNDING_BOX &&
          Math.abs(vessel.location().longitude() - waypoint.location().longitude()) < BOUNDING_BOX) {
        nearby.add(vessel);
      }
    }
    return nearby;
  }
}
