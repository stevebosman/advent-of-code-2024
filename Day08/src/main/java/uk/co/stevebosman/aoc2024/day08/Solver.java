package uk.co.stevebosman.aoc2024.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solver {
  private final Map<Character, List<Position>> antennaPositions;
  private int rowCount;
  private int columnCount;

  public Solver(final Path filepath) throws IOException {
    final List<String> lines = Files.readAllLines(filepath);
    this.rowCount = lines.size();
    this.columnCount = lines.get(0)
                            .length();
    final Map<Character, List<Position>> antennaPositions = new HashMap<>();
    for (int rowIndex = 0; rowIndex < this.rowCount; rowIndex++) {
      final String line = lines.get(rowIndex);
      for (int columnIndex = 0; columnIndex < this.columnCount; columnIndex++) {
        final char c = line.charAt(columnIndex);
        if (c != '.') {
          antennaPositions.computeIfAbsent(c, (k) -> new ArrayList<>())
                          .add(new Position(columnIndex, rowIndex));
        }
      }
    }
    this.antennaPositions = antennaPositions;
  }

  public int solve() {
    return antennaPositions.values()
                           .stream()
                           .map(this::antiNodes)
                           .flatMap(Collection::stream)
                           .filter(p -> p.x() >= 0 && p.x() < this.columnCount && p.y() >= 0 && p.y() < this.rowCount)
                           .collect(Collectors.toSet())
                           .size();
  }

  private List<Position> antiNodes(final List<Position> antennas) {
    final List<Position> antiNodes = new ArrayList<>();
    for (int i = 0; i < antennas.size(); i++) {
      final Position antenna1 = antennas.get(i);
      for (int j = 0; j < antennas.size(); j++) {
        if (i != j) {
          final Position antenna2 = antennas.get(j);
          final int dx = antenna1.x() - antenna2.x();
          final int dy = antenna1.y() - antenna2.y();
          final Position antiNode1 = new Position(antenna1.x() + dx, antenna1.y() + dy);
          antiNodes.add(antiNode1);
          final Position antiNode2 = new Position(antenna2.x() - dx, antenna2.y() - dy);
          antiNodes.add(antiNode2);
        }
      }
    }
    return antiNodes;
  }
}
