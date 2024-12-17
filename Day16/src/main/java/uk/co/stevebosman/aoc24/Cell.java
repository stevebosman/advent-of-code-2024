package uk.co.stevebosman.aoc24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cell {
  private final boolean wall;
  private final List<Direction> directions = new ArrayList<>();
  private int distance;

  public Cell(final int i) {
    this.wall = (i == '#');
  }

  public void explored(final Direction direction, final int distance) {
    if (isExplorable(distance)) {
      this.directions.add(direction);
      this.distance = distance;
    }
  }

  public boolean isExplorable(final int distance) {
    return (this.distance == 0 || this.distance == distance) && !wall;
  }

  public int getDistance() {
    return distance;
  }

  public List<Direction> getDirections() {
    return Collections.unmodifiableList(directions);
  }

  public String getChartChar() {
    if (wall) return "#";
    if (directions.size() == 1) {
      return switch (directions.getFirst()) {
        case North -> "^";
        case East -> ">";
        case South -> "v";
        case West -> "<";
      };
    } else if (directions.size() > 1) {
      return "x";
    }
    return ".";
  }
}
