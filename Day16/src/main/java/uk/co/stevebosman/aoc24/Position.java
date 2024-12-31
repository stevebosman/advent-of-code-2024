package uk.co.stevebosman.aoc24;

import java.util.Objects;

public record Position(int row, int column) implements Comparable<Position> {

  public Position neighbour(final Direction direction) {
    return switch (direction) {
      case North -> new Position(row - 1, column);
      case East -> new Position(row, column + 1);
      case South -> new Position(row + 1, column);
      case West -> new Position(row, column - 1);
    };
  }

  @Override
  public boolean equals(final Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    final Position position = (Position) o;
    return column == position.column && row == position.row;
  }

  @Override
  public String toString() {
    return "(" + column +
            ", " + row +
            ')';
  }

  @Override
  public int hashCode() {
    return Objects.hash(column, row);
  }

  @Override
  public int compareTo(final Position o) {
    int compare = o.row - this.row;
    if (compare==0) compare = this.column - o.column;
    return compare;
  }
}
