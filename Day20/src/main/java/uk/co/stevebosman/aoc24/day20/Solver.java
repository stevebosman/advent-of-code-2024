package uk.co.stevebosman.aoc24.day20;

import uk.co.stevebosman.aoc24.grid.Grid;
import uk.co.stevebosman.aoc24.grid.GridReader;
import uk.co.stevebosman.aoc24.grid.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public record Solver(String filename) {

  public static final char SPACE = '.';

  private static List<Position> getReversePath(final Grid<Character> grid, final Position start, final Position end) {
    final List<Position> reversePath = new ArrayList<>();
    reversePath.add(end);
    Position previous = null;
    Position current = end;
    while (!current.equals(start)) {
      final Position finalPrevious = previous;
      final Optional<Position> next = current.neighbours(grid.bounds())
                                             .stream()
                                             .filter(p -> !p.equals(finalPrevious)
                                                     && (p.equals(start) || Optional.of(SPACE)
                                                                                    .equals(grid.get(p))))
                                             .findFirst();
      if (next.isEmpty()) throw new IllegalStateException("Strayed from path");
      previous = current;
      current = next.get();
      reversePath.add(current);
    }
    return reversePath;
  }

  public long cheatsSaveAtLeast(final long target) throws IOException {
    final Grid<Character> grid = new GridReader<>(Function.identity()).read(filename);
    final Position start = grid.findFirst('S')
                               .orElseThrow(() -> new IllegalStateException("Start not found"));
    final Position end = grid.findFirst('E')
                             .orElseThrow(() -> new IllegalStateException("End not found"));
    System.out.println("grid = " + grid);
    System.out.println("start = " + start);
    System.out.println("end = " + end);

    final List<Position> reversePath = getReversePath(grid, start, end);
    System.out.println("reversePath = " + reversePath);

    long result = 0;
    for (int i = 4; i < reversePath.size(); i++) {
      result += countTargetCheatsForPosition(target, reversePath, i, grid);
    }

    return result;
  }

  private long countTargetCheatsForPosition(final long target, final List<Position> reversePath, final int finalI, final Grid<Character> grid) {
    return reversePath.get(finalI)
                      .neighbourPairs(grid.bounds(), 1, 2)
                      .stream()
                      .filter(l -> '#' == grid.get(l.start())
                                              .orElse('-') && '#' != grid.get(l.end())
                                                                         .orElse('-'))
                      .map(l -> finalI - reversePath.indexOf(l.end()) - 2)
                      .filter(v -> v >= target)
                      .count();
  }
}