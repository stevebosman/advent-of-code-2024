package uk.co.stevebosman.aoc24;

public record DirectionalPosition(Direction d, Position p) {
  public DirectionalPosition neighbour(final Direction direction) {
    return new DirectionalPosition(direction, p.neighbour(direction));
  }

  @Override
  public String toString() {
    return "{" +
            "d=" + d +
            ", p=" + p +
            '}';
  }
}
