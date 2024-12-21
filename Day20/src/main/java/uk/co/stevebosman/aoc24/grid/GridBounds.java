package uk.co.stevebosman.aoc24.grid;

public record GridBounds(Position min, Position max) {
public boolean contains(final Position p) {
  return p.x() >= min().x() && p.y() >= min().y() && p.x() <= max().x() && p.y() <= max.y();
}
}
