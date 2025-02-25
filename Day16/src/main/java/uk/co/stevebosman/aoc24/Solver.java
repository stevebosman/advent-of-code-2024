package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Solver {
  private final List<List<Cell>> chart;
  private final List<Position> seats;
  private final int cheapestRoute;
  private final DirectionalPosition start;
  private final Position end;

  public Solver(final String filename) throws IOException {
    try (final var lines = Files.lines(Path.of(filename))) {
      this.chart = lines.map(line -> line.chars()
                                         .boxed()
                                         .map(Cell::new)
                                         .toList())
                        .toList();
    }
    this.start = new DirectionalPosition(Direction.East, new Position(chart.size() - 2, 1));
    this.end = new Position(1, chart.getFirst()
                                    .size() - 2);
    this.cheapestRoute = solve();
    this.seats = findSeats().stream().sorted().toList();
  }

  private int solve() {
    int result = 0;
    setDistance(start, 0);
    final Map<Integer, Set<DirectionalPosition>> possible = getNeighbours(0, start);
//    System.out.println("neighbours of " + start + ": " + possible);
    for (int potentialCost = 1; potentialCost < 1_000_000 && result == 0; potentialCost++) {
      if (possible.containsKey(potentialCost)) {
        final Set<DirectionalPosition> positions = possible.get(potentialCost);
        possible.remove(potentialCost);
        if (positions.stream()
                     .anyMatch(p -> end.equals(p.position()))) {
          result = potentialCost;
        }
        for (final DirectionalPosition position : positions) {
          final Map<Integer, Set<DirectionalPosition>> neighbours = getNeighbours(potentialCost, position);
          if (!neighbours.isEmpty() || end.equals(position.position())) {
            setDistance(position, potentialCost);
          }
//          System.out.println("neighbours of " + position + ": " + neighbours);
          for (final Map.Entry<Integer, Set<DirectionalPosition>> entry : neighbours.entrySet()) {
            possible.computeIfAbsent(entry.getKey(), k -> new HashSet<>())
                    .addAll(entry.getValue());
          }
        }
      }
    }
    printMap(false, false);
    return result;
  }

  private Set<Position> findSeats() {
    final Set<Position> seats = new TreeSet<>();
    seats.add(end);
    Set<Position> potentialSeats = getNeighbours(end);
    while (!potentialSeats.isEmpty()) {
      potentialSeats.forEach(p -> {
        getCell(p).markOnRoute();
      });
      seats.addAll(potentialSeats);
      final Set<Position> nextPotentialSeats = new HashSet<>();
      for (final Position potential : potentialSeats) {
        nextPotentialSeats.addAll(getNeighbours(potential));
      }
      potentialSeats = new TreeSet<>(nextPotentialSeats);
      potentialSeats.removeAll(seats);
    }
    printMap(true, false);
    printMap(true, true);
    return seats;
  }

  public int getCheapestRoute() {
    return cheapestRoute;
  }

  public int getSeatCount() {
    System.out.println("seats = " + seats);
    return seats.size();
  }

  private void setDistance(final DirectionalPosition position, final int distance) {
    getCell(position.position()).explored(position.direction(), distance);
//    System.out.println("setDistance: " + distance + "@" + position + " => " + getCell(position.position()));
  }

  private Cell getCell(final Position position) {
    return chart.get(position.row())
                .get(position.column());
  }

  private Map<Integer, Set<DirectionalPosition>> getNeighbours(final int offset, final DirectionalPosition position) {
    final Map<Integer, Set<DirectionalPosition>> result = new HashMap<>();
    for (final Direction direction : Direction.values()) {
      addNeighbour(offset, position.direction(), position.neighbour(direction), result);
    }
    return result;
  }

  private void addNeighbour(final int offset, final Direction direction, final DirectionalPosition position, final Map<Integer, Set<DirectionalPosition>> result) {
    final int cost = offset + direction.costFrom(position.direction());
    final Cell cell = getCell(position.position());
    if (cell.isExplorable(position.direction()) && !direction.isOppositeOf(position.direction())) {
      result.computeIfAbsent(cost, k -> new HashSet<>())
            .add(position);
    }
  }

  private Set<Position> getNeighbours(final Position position) {
    final Set<Position> result = new HashSet<>();
    for (final Direction direction : Direction.values()) {
      addNeighbour(position, direction, result);
    }
    return result;
  }

  private void addNeighbour(final Position position, final Direction direction, final Set<Position> result) {
    final Position potentialPosition = position.neighbour(direction);
    final Cell currentCell = getCell(position);
    final Cell potentialCell = getCell(potentialPosition);
    final int distance = currentCell.getDistance(direction.opposite());
    if (!potentialCell.isWall() && distance != -1 && potentialCell.hasNextDistance(distance, direction.opposite())) {
      result.add(potentialPosition);
    }
  }

  private void printMap(final boolean showRoute, final boolean showValues) {
    System.out.println(chart.stream()
                            .map(line -> line.stream()
                                             .map(c -> c.getChartChar(showRoute, showValues))
                                             .collect(Collectors.joining()))
                            .collect(Collectors.joining("\n")));
    System.out.println("\n");
  }
}