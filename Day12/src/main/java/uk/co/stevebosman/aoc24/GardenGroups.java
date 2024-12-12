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
      map = new ArrayList<>(lines.map(l -> ("."+l+".").toCharArray())
                 .toList());
    }
    width = map.getFirst().length;
    map.addFirst(".".repeat(width).toCharArray());
    map.addLast(".".repeat(width).toCharArray());
    height = map.size();

  }

  public long cost() {
    final var clonedMap = map.stream()
                             .map(char[]::clone)
                             .toList();
    final List<Region> regions = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < height; rowIndex++) {
      final char[] row = clonedMap.get(rowIndex);
      for (int columnIndex = 0; columnIndex < width; columnIndex++) {
        if (row[columnIndex] != '.') {
          Region.of(clonedMap, rowIndex, columnIndex, this.height, this.width)
                .ifPresent(regions::add);
        }
      }
    }
    return regions.stream()
                  .map(r -> r.area() * r.perimeter())
                  .reduce(Long::sum)
                  .orElse(0L);
  }
}