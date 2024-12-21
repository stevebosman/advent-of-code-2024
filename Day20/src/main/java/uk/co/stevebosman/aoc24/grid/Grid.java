package uk.co.stevebosman.aoc24.grid;

import java.util.List;
import java.util.Optional;

public record Grid<T>(List<List<T>> grid, GridBounds bounds) {
  public Grid(final List<List<T>> grid) {
    this(grid, new GridBounds(Position.ORIGIN, new Position(grid.size() - 1, grid.getFirst()
                                                                                 .size() - 1)));
  }

  public Optional<T> get(final Position position) {
    if (bounds.contains(position)) {
      return Optional.of(grid.get(position.y())
                             .get(position.x()));
    } else {
      return Optional.empty();
    }
  }

  public T getOrElse(final Position position, final T def) {
    if (bounds.contains(position)) {
      return grid.get(position.y())
                 .get(position.x());
    } else {
      return def;
    }
  }

  public Optional<Position> findFirst(final T seeking) {
    Position p = null;
    outer:
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i)
                              .size(); j++) {
        if (grid.get(i)
                .get(j) == seeking) {
          p = new Position(j, i);
          break outer;
        }
      }
    }
    return Optional.ofNullable(p);
  }
}
