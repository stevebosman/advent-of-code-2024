package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Solver {
  private final List<List<Cell>> chart;

  public Solver(final String filename) throws IOException {
    try (final var lines = Files.lines(Path.of(filename))) {
      this.chart = lines.map(line -> line.chars()
                                         .boxed()
                                         .map(Cell::new)
                                         .toList())
                        .toList();
    }
  }

  public int solve() {
    int result = 0;
    final Position start = new Position(Direction.East, 1, chart.size() - 2);
    final Position end = new Position(null, chart.getFirst()
                                                 .size() - 2, 1);
    setDistance(start, 0);
    final Map<Integer, Set<Position>> possible = getNeighbours(0, start);
    for (int i = 1; i < 1_000_000; i++) {
      if (possible.containsKey(i)) {
        final int ifinal = i;
        final Set<Position> positions = possible.get(i);
        possible.remove(i);
        if (positions.stream()
                     .anyMatch(p -> p.x() == end.x() && p.y() == end.y())) {
          result = i;
          break;
        }
        for (final Position p : positions) {
          setDistance(p, ifinal);
          final Map<Integer, Set<Position>> neighbours = getNeighbours(ifinal, p);
          for (final Map.Entry<Integer, Set<Position>> entry : neighbours.entrySet()) {
            possible.computeIfAbsent(entry.getKey(), k -> new HashSet<>())
                    .addAll(entry.getValue());
          }
        }
        ;
      }
    }
    return result;
  }

  private void setDistance(final Position position, final int distance) {
    chart.get(position.y())
         .get(position.x())
         .explored(position.d(), distance);
  }

  private Map<Integer, Set<Position>> getNeighbours(final int offset, final Position position) {
    final Map<Integer, Set<Position>> result = new HashMap<>();
    addNeighbour(offset, position.d(), new Position(Direction.North, position.x(), position.y() - 1), result);
    addNeighbour(offset, position.d(), new Position(Direction.South, position.x(), position.y() + 1), result);
    addNeighbour(offset, position.d(), new Position(Direction.East, position.x() + 1, position.y()), result);
    addNeighbour(offset, position.d(), new Position(Direction.West, position.x() - 1, position.y()), result);
    return result;
  }

  private void addNeighbour(final int offset, final Direction direction, final Position position, final Map<Integer, Set<Position>> result) {
    if (chart.get(position.y())
             .get(position.x())
             .isExplorable()) {
      final int cost = offset + direction.costFrom(position.d());
      result.computeIfAbsent(cost, k -> new HashSet<>())
            .add(position);
    }
  }

  public void printMap() {
    System.out.println(chart.stream()
                            .map(line -> line.stream()
                                             .map(Cell::getChartChar)
                                             .collect(Collectors.joining()))
                            .collect(
                                    Collectors.joining("\n")));
    System.out.println("\n");
  }
}