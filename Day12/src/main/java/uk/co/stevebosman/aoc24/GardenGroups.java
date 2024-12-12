package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GardenGroups {
  private final List<char[]> map;
  private final int width;
  private final int height;

  public GardenGroups(final String filename) throws IOException {
    // build map with a border
    try (final Stream<String> lines = Files.lines(Path.of(filename))) {
      map = new ArrayList<>(lines.map(l -> ("." + l + ".").toCharArray())
                                 .toList());
    }
    width = map.getFirst().length;
    map.addFirst(".".repeat(width)
                    .toCharArray());
    map.addLast(".".repeat(width)
                   .toCharArray());
    height = map.size();

  }

  public long cost() {
    return getRegions().stream()
                       .map(r -> r.area() * r.perimeter())
                       .reduce(Long::sum)
                       .orElse(0L);
  }

  public long discountedCost() {
    return getRegions().stream()
                       .map(r -> r.area() * r.sides())
                       .reduce(Long::sum)
                       .orElse(0L);
  }

  private List<Region> getRegions() {
    final var clonedMap = map.stream()
                             .map(char[]::clone)
                             .toList();
    final List<Region> regions = new ArrayList<>();
    for (int rowIndex = 1; rowIndex < height - 1; rowIndex++) {
      final char[] row = clonedMap.get(rowIndex);
      for (int columnIndex = 1; columnIndex < width - 1; columnIndex++) {
        if (row[columnIndex] != '.') {
          Region.of(clonedMap, rowIndex, columnIndex, this.height, this.width)
                .ifPresent(regions::add);
        }
      }
    }
    return regions;
  }
}