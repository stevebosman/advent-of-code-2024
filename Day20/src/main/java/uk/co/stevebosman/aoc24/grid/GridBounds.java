package uk.co.stevebosman.aoc24.grid;

public record GridBounds(Position min, Position max) {
  public boolean contains(final Position p) {
    return contains(p.x(), p.y());
  }

  public boolean contains(final int x, final int y) {
    return x >= min().x() && y >= min().y() && x <= max().x() && y <= max.y();
  }
}
