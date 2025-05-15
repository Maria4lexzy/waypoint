package model;

//todo:create interface to share w vessel
public record Waypoint(String name, Point location) {
  public Waypoint(String name, double lat, double lon) {
    this(name, new Point(lat, lon));
  }
}
