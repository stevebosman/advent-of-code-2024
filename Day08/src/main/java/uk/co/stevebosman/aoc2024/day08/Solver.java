package uk.co.stevebosman.aoc2024.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Solver {
  private final Map<Character, List<Position>> antennaPositions;
  private final int rowCount;
  private final int columnCount;
  private final boolean resonantHarmonics;

  public Solver(final boolean resonantHarmonics, final Path filepath) throws IOException {
    this.resonantHarmonics = resonantHarmonics;
    final List<String> lines = Files.readAllLines(filepath);
    this.rowCount = lines.size();
    this.columnCount = lines.getFirst()
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
    final Set<Position> antiNodes = antennaPositions.values()
                                            .stream()
                                            .filter(s -> s.size() > 1)
                                            .map(this::antiNodes)
                                            .flatMap(Collection::stream)
                                            .collect(Collectors.toSet());
    if (resonantHarmonics) {
      antennaPositions.values()
                      .forEach(antiNodes::addAll);
    }
    return antiNodes.size();
  }

  private boolean inRange(final Position p) {
    return p.x() >= 0 && p.x() < this.columnCount && p.y() >= 0 && p.y() < this.rowCount;
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
          addAntiNodes(antiNodes, antenna1, dx, dy);
          addAntiNodes(antiNodes, antenna2, -dx, -dy);
        }
      }
    }
    return antiNodes;
  }

  private void addAntiNodes(final List<Position> antiNodes, final Position antenna, final int dx, final int dy) {
    for (int i = 1; i < 100; i++) {
      final Position antiNode = new Position(antenna.x() + i * dx, antenna.y() + i * dy);
      if (!inRange(antiNode)) return;
      antiNodes.add(antiNode);
      if (!resonantHarmonics) return;
    }
  }

}
