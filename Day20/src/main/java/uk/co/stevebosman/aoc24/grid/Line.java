package uk.co.stevebosman.aoc24.grid;

public record Line(Position start, Position end) {
  public int manhattanDistance() {
    return start.manhattanDistance(end);
  }
}
