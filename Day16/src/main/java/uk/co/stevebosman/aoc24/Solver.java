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
  private final Set<DirectionalPosition> seats = new HashSet<>();

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
    final DirectionalPosition start = new DirectionalPosition(Direction.East, new Position(1, chart.size() - 2));
    final Position end = new Position(chart.getFirst().size() - 2, 1);
    setDistance(start, 0);
    final Map<Integer, Set<DirectionalPosition>> possible = getNeighbours(0, start);
    for (int potentialCost = 1; potentialCost < 1_000_000 && result ==0; potentialCost++) {
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
          for (final Map.Entry<Integer, Set<DirectionalPosition>> entry : neighbours.entrySet()) {
            possible.computeIfAbsent(entry.getKey(), k -> new HashSet<>())
                    .addAll(entry.getValue());
          }
        }
      }
    }
    printMap();
    return result;
  }

  private void setDistance(final DirectionalPosition position, final int distance) {
    chart.get(position.p().y())
         .get(position.p().x())
         .explored(position.d(), distance);
  }

  private Map<Integer, Set<DirectionalPosition>> getNeighbours(final int offset, final DirectionalPosition position) {
    final Map<Integer, Set<DirectionalPosition>> result = new HashMap<>();
    addNeighbour(offset, position.d(), new DirectionalPosition(Direction.North, new Position(position.p().x(), position.p().y() - 1)), result);
    addNeighbour(offset, position.d(), new DirectionalPosition(Direction.South, new Position(position.p().x(), position.p().y() + 1)), result);
    addNeighbour(offset, position.d(), new DirectionalPosition(Direction.East, new Position(position.p().x() + 1, position.p().y())), result);
    addNeighbour(offset, position.d(), new DirectionalPosition(Direction.West, new Position(position.p().x() - 1, position.p().y())), result);
    return result;
  }

  private void addNeighbour(final int offset, final Direction direction, final DirectionalPosition position, final Map<Integer, Set<DirectionalPosition>> result) {
    final int cost = offset + direction.costFrom(position.d());
    if (chart.get(position.p().y())
             .get(position.p().x())
             .isExplorable(cost)) {
      result.computeIfAbsent(cost, k -> new HashSet<>())
            .add(position);
    }
  }

  private void printMap() {
    System.out.println(chart.stream()
                            .map(line -> line.stream()
                                             .map(Cell::getChartChar)
                                             .collect(Collectors.joining()))
                            .collect(
                                    Collectors.joining("\n")));
    System.out.println("\n");
  }
}