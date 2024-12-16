package uk.co.stevebosman.aoc24;

public class Cell {
  private final boolean wall;
  private int distance;
  private Direction direction;
  public Cell(final int i) {
    this.wall = (i == '#');
  }
  public void explored(final Direction direction, final int distance) {
    this.direction = direction;
    this.distance = distance;
  }
  public boolean isExplorable() {
    return direction == null && !wall;
  }
  public int getDistance() {
    return distance;
  }

  public String getChartChar() {
    if (wall) return "#";
    if (direction!=null) {
      return switch(direction) {
        case North -> "^";
        case East -> ">";
        case South -> "v";
        case West -> "<";
      };
    }
    return ".";
  }
}
