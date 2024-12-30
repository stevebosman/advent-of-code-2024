package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solver {
  private final Cell[][] grid;
  private final DirectionalPosition start;
  private final Position end;

  public Solver(final String filename, final int size, final int limit) throws IOException {
    this.grid = new Cell[size + 3][size + 3];
    for (int r = 0, gridLength = grid.length; r < gridLength; r++) {
      final Cell[] row = grid[r];
      for (int c = 0; c < row.length; c++) {
        row[c] = new Cell(c == 0 || c == size + 2 || r == 0 || r == size + 2
                          ? '#'
                          : 0);
      }
    }

    final var lines = Files.readAllLines(Path.of(filename));
    for (int i = 0; i < Math.min(lines.size(), limit); i++) {
      final var coordText = lines.get(i)
                                 .split(",");
      final int x = Integer.parseInt(coordText[0]) + 1;
      final int y = Integer.parseInt(coordText[1]) + 1;
      this.grid[y][x] = new Cell('#');
    }

    this.start = new DirectionalPosition(Direction.East, new Position(1, 1));
    this.end = new Position(size +1, size +1);
  }

  public Integer solve() {
    int result = 0;
    setDistance(start, 0);
    final Map<Integer, Set<DirectionalPosition>> possible = getNeighbours(0, start);
//    System.out.println("neighbours of " + start + ": " + possible);
    for (int potentialCost = 1; potentialCost < 3450 && result == 0; potentialCost++) {
      if (possible.containsKey(potentialCost)) {
        final Set<DirectionalPosition> positions = possible.get(potentialCost);
        possible.remove(potentialCost);
        if (positions.stream()
                     .anyMatch(p -> end.equals(p.p()))) {
          result = potentialCost;
        }
        for (final DirectionalPosition position : positions) {
          setDistance(position, potentialCost);
          final Map<Integer, Set<DirectionalPosition>> neighbours = getNeighbours(potentialCost, position);
//          System.out.println("neighbours of " + position + ": " + neighbours);
          for (final Map.Entry<Integer, Set<DirectionalPosition>> entry : neighbours.entrySet()) {
            possible.computeIfAbsent(entry.getKey(), k -> new HashSet<>())
                    .addAll(entry.getValue());
          }
        }
      }
    }
//    printMap(false, false);
    return result;
  }

  private void setDistance(final DirectionalPosition position, final int distance) {
    getCell(position.p()).explored(position.d(), distance);
//    System.out.println("setDistance: " + distance + "@" + position + " => " + getCell(position.p()));
  }

  private Cell getCell(final Position position) {
    return grid[position.y()][position.x()];
  }

  private void addNeighbour(final Position position, final Direction direction, final Set<Position> result) {
    final Position potentialPosition = position.neighbour(direction);
    final Cell currentCell = getCell(position);
    final Cell potentialCell = getCell(potentialPosition);
    final int distance = currentCell.getDistance(direction.opposite());
    if (!potentialCell.isWall() && distance != -1 && potentialCell.hasNextDistance(distance)) {
      result.add(potentialPosition);
    }
  }

  private void addNeighbour(final int offset, final Direction direction, final DirectionalPosition position, final Map<Integer, Set<DirectionalPosition>> result) {
    final int cost = offset + 1;
    if (getCell(position.p()).isExplorable(direction) && direction != position.d()
                                                                              .opposite()) {
      result.computeIfAbsent(cost, k -> new HashSet<>())
            .add(position);
    }
  }

  private Map<Integer, Set<DirectionalPosition>> getNeighbours(final int offset, final DirectionalPosition position) {
    final Map<Integer, Set<DirectionalPosition>> result = new HashMap<>();
    for (final Direction direction : Direction.values()) {
      addNeighbour(offset, position.d(), position.neighbour(direction), result);
    }
    return result;
  }

  private void printMap(final boolean showRoute, final boolean showValues) {
    for (int r = 0, gridLength = grid.length; r < gridLength; r++) {
      final Cell[] row = grid[r];
      for (int c = 0; c < row.length; c++) {
        System.out.print(row[c].getChartChar(showRoute, showValues));
      }
      System.out.println();
    }
    System.out.println("\n");
  }
}
