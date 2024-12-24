package uk.co.stevebosman.aoc24.grid;

public enum Direction {
  North, East, South, West;

  public Direction opposite() {
    return values()[(this.ordinal() + 2) % 4];
  }
}