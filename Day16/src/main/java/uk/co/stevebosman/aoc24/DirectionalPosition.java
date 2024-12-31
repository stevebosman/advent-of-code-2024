package uk.co.stevebosman.aoc24;

public record DirectionalPosition(Direction direction, Position position) {
  public DirectionalPosition neighbour(final Direction direction) {
    return new DirectionalPosition(direction, position.neighbour(direction));
  }

  @Override
  public String toString() {
    return "{" +
            "direction=" + direction +
            ", position=" + position +
            '}';
  }
}
