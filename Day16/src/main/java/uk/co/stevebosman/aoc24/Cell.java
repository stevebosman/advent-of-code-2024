package uk.co.stevebosman.aoc24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
  private final boolean wall;
  private boolean route;
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

  public void markOnRoute() {
    route=true;
  }

  @Override
  public String toString() {
    return "Cell{" +
            "wall=" + wall +
            ", route=" + route +
            ", distances=" + distances +
            '}';
  }

  public int getDistance(final Direction direction) {
    return distances.getOrDefault(direction, -1);
  }

  public List<Direction> getDirections() {
    return List.copyOf(distances.keySet());
  }

  public String getChartChar(final boolean showRoute, final boolean showValue) {
    String result;
    if (wall) {
      result = "#";
    } else if (showRoute && route) {
      result = "O";
    } else if (!showRoute) {
      if (distances.size() == 1) {
        result = switch (getDirections().getFirst()) {
          case North -> "^";
          case East -> ">";
          case South -> "v";
          case West -> "<";
        };
      } else if (distances.size() > 1) {
        result =  "x";
      } else {
        result = ".";
      }
    } else {
      result = ".";
    }
    if (showValue) {
      result+="-";
      if (wall) result += "#".repeat(6);
      else if (distances.isEmpty()) result += ".".repeat(6);
      else if (distances.size()>1) result += "?".repeat(6);
      else result += String.format("%06d", distances.values().stream().findFirst().get());
      result+="}";
    }
    return result;
  }

  public boolean hasNextDistance(final int distance) {
    return distances.values()
                    .stream()
                    .anyMatch(d -> distance - d == 1001 || distance - d == 1);
  }
}
