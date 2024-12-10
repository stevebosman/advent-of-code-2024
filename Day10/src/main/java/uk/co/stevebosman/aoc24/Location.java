package uk.co.stevebosman.aoc24;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Location {
  private final int height;
  private final Coordinate coordinate;
  private int routesFromPrevious = 0;
  private final Set<Location> routableFrom = new HashSet<>();

  public Location(final int height, final Coordinate coordinate) {
    this.height = height;
    this.coordinate = coordinate;
  }

  public int height() {
    return height;
  }

  public Coordinate coordinate() {
    return coordinate;
  }

  public int getRoutesFromPreviousCount() {
    return routesFromPrevious;
  }

  public void incrementRoutesFromPrevious() {
    routesFromPrevious++;
  }

  public void addRoutableFrom(final Set<Location> routableFrom) {
    this.routableFrom.addAll(routableFrom);
  }

  public int getRoutableFromCount() {
    return routableFrom.size();
  }

  public Set<Location> getRoutableFrom() {
    return this.routableFrom;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    final var that = (Location) obj;
    return this.height == that.height &&
            this.coordinate == that.coordinate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, coordinate);
  }

  @Override
  public String toString() {
    return "Location[" +
            "height=" + height + ", " +
            "coordinate=" + coordinate + ", " +
            "routesFromPrevious=" + routesFromPrevious + ", " +
            "routableFrom=" + routableFrom.size() + ']';
  }

}
