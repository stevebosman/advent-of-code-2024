package uk.co.stevebosman.aoc24.grid;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public record Position(int x, int y) implements Comparable<Position> {

  public static final Position ORIGIN = new Position(0, 0);

  public Position neighbour(final Direction direction, final int offset) {
    return switch (direction) {
      case North -> new Position(x, y - offset);
      case East -> new Position(x + offset, y);
      case South -> new Position(x, y + offset);
      case West -> new Position(x - offset, y);
    };
  }

  public Position neighbour(final Direction direction) {
    return neighbour(direction, 1);
  }

  public Set<Position> neighbours() {
    return Arrays.stream(Direction.values())
                 .map(this::neighbour)
                 .collect(Collectors.toSet());
  }

  public Set<Position> neighbours(final GridBounds bounds) {
    return Arrays.stream(Direction.values())
                 .map(this::neighbour)
                 .filter(bounds::contains)
                 .collect(Collectors.toSet());
  }

  public Set<Line> neighbourPairs(final GridBounds bounds, final int offsetStart, final int offsetEnd) {
    return Arrays.stream(Direction.values())
                 .map(d -> new Line(neighbour(d, offsetStart), neighbour(d, offsetEnd)))
                 .filter(line -> bounds.contains(line.end()))
                 .collect(Collectors.toSet());
  }

  @Override
  public boolean equals(final Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    final Position position = (Position) o;
    return x == position.x && y == position.y;
  }

  @Override
  public String toString() {
    return "(" + "x=" + x + ", y=" + y + ')';
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public int compareTo(final Position o) {
    int compare = o.y - this.y;
    if (compare == 0) compare = this.x - o.x;
    return compare;
  }
}
