package uk.co.stevebosman.aoc24;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class Location {
  private final int height;
  private final Coordinate coordinate;
  private int routesFromPrevious = 0;
  private final Map<Location, Integer> routableFrom = new HashMap<>();

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

  public void addRoutableFrom(final Map<Location, Integer> routableFrom) {
    for(final Map.Entry<Location, Integer> entry: routableFrom.entrySet()) {
      final var current = this.routableFrom.computeIfAbsent(entry.getKey(), k->0);
      this.routableFrom.put(entry.getKey(), current+entry.getValue());
    }
  }

  public int getRoutableFromCount() {
    return routableFrom.size();
  }

  public int getUniqueRoutableFromCount() {
    return routableFrom.values().stream().reduce(Integer::sum).orElse(0);
  }

  public Map<Location, Integer> getRoutableFrom() {
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
