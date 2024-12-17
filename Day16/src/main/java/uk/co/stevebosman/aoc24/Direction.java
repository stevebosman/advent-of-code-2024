package uk.co.stevebosman.aoc24;

import static java.lang.Math.abs;

public enum Direction {
  North, East, South, West;

  public Direction opposite() {
    return values()[(this.ordinal() + 2) % 4];
  }

  public int costFrom(final Direction d) {
    if (this == d) {
      return 1;
    } else if (d == d.opposite()) {
      return 2001;
    }
    return 1001;
  }
}
