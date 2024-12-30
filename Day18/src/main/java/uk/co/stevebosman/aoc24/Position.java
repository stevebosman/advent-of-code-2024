package uk.co.stevebosman.aoc24;

import java.util.Objects;

public record Position(int x, int y) implements Comparable<Position> {

  public Position neighbour(final Direction direction) {
    return switch (direction) {
      case North -> new Position(x, y - 1);
      case East -> new Position(x + 1, y);
      case South -> new Position(x, y + 1);
      case West -> new Position(x - 1, y);
    };
  }

  @Override
  public boolean equals(final Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    final Position position = (Position) o;
    return x == position.x && y == position.y;
  }

  @Override
  public String toString() {
    return "(" +
            "x=" + x +
            ", y=" + y +
            ')';
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public int compareTo(final Position o) {
    int compare = o.y - this.y;
    if (compare==0) compare = this.x - o.x;
    return compare;
  }
}
