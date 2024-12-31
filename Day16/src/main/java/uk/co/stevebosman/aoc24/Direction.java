package uk.co.stevebosman.aoc24;

import java.util.Set;

import static java.lang.Math.abs;

public enum Direction {
  North, East, South, West;

  public Direction opposite() {
    return values()[(this.ordinal() + 2) % 4];
  }

  public int costFrom(final Direction d) {
    if (this == d) {
      return 1;
    } else if (this.isOppositeOf(d)) {
      return 2001;
    }
    return 1001;
  }

  public boolean isOrthogonalTo(final Direction direction) {
    return (4 + this.ordinal() - direction.ordinal()) % 2 == 1;
  }

  public boolean isOppositeOf(final Direction direction) {
    return this.opposite() == direction;
  }

  public Set<Direction> leftRightValues() {
    return Set.of(Direction.values()[(this.ordinal() + 1) % 4], Direction.values()[(this.ordinal() + 3) % 4]);
  }
}
