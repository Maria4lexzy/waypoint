package model;

//todo:create interface w waypoint
public record Vessel(String name, Point location) {
  public Vessel(String name, double lat, double lon) {
    this(name, new Point(lat, lon));
  }
}
