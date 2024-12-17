package uk.co.stevebosman.aoc24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
  private final boolean wall;
  private final Map<Direction, Integer> distances = new HashMap<>();

  public Cell(final int i) {
    this.wall = (i == '#');
  }

  public void explored(final Direction direction, final int distance) {
    if (isExplorable(direction)) {
      distances.put(direction, distance);
    }
  }

  public boolean isExplorable(final Direction direction) {
    return !wall && !distances.containsKey(direction) && !distances.containsKey(direction.opposite());
  }

  public boolean isWall() {
    return wall;
  }

  @Override
  public String toString() {
    return "Cell{" +
            "wall=" + wall +
            ", distances=" + distances +
            '}';
  }

  public int getDistance(final Direction direction) {
    return distances.getOrDefault(direction, -1);
  }

  public List<Direction> getDirections() {
    return List.copyOf(distances.keySet());
  }

  public String getChartChar() {
    if (wall) return "#";
    if (distances.size() == 1) {
      return switch (getDirections().getFirst()) {
        case North -> "^";
        case East -> ">";
        case South -> "v";
        case West -> "<";
      };
    } else if (distances.size() > 1) {
      return "x";
    }
    return ".";
  }

  public boolean hasDistanceLessThan(final int distance) {
    return distances.values()
                    .stream()
                    .anyMatch(d -> d < distance);
  }
}
