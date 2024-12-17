package uk.co.stevebosman.aoc24;

import static java.lang.Math.abs;

public enum Direction {
  North, East, South, West;

  public int costFrom(final Direction d) {
    if (this == d) {
      return 1;
    } else if (abs(d.ordinal() - this.ordinal()) == 2) {
      return 2001;
    }
    return 1001;
  }
}
