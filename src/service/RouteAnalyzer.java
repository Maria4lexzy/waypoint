package service;

import java.util.ArrayList;
import java.util.List;

import model.Vessel;
import model.Waypoint;

public class RouteAnalyzer {
  private List<Waypoint> waypoints = new ArrayList<>();
  private VesselCollection vessels;

  public RouteAnalyzer(String xmlFile) {
    // given waypoints
    waypoints.add(new Waypoint("WP1", 56.255625, 10.442260));
    waypoints.add(new Waypoint("WP2", 56.054509, 10.676264));


    vessels = new VesselCollection();
    vessels.loadFromXML(xmlFile);
  }

  public void analyze() {
    List<Vessel> vesselList = vessels.getVessels();

    // Check if waypoints and vessels exist
    if (waypoints.isEmpty() || vesselList.isEmpty()) {
      System.err.println("No waypoints or ships loaded.");
      return;
    }

    // For each waypoint
    for (Waypoint waypoint : waypoints) {
      // Use shortcut to get nearby ships
      List<Vessel> nearbyVessels = DistanceCalculator.vesselsInProximity(waypoint, vesselList);
      if (nearbyVessels.isEmpty()) {
        nearbyVessels = vesselList; // If there are none nearby then check all 
      }

      double minDistance = Double.MAX_VALUE; // Start with a bigger distance
      String closestVessel = null;

      // Check each nearby ship
      for (Vessel vessel : nearbyVessels) {
        double distance = DistanceCalculator.haversine(waypoint.location(), vessel.location());
        if (distance < minDistance) {
          minDistance = distance;
          closestVessel = vessel.name();
        }
      }

      // Print result
      System.out.printf("model.Waypoint %s: Closest vessel is %s with distance %.2f km%n",
          waypoint.name(), closestVessel, minDistance);
    }
  }
}
