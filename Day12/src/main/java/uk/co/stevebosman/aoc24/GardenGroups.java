package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class GardenGroups {
  private final List<char[]> map;
  public GardenGroups(final String filename) throws IOException {
    try (final Stream<String> lines = Files.lines(Path.of(filename))) {
      map = lines.map(String::toCharArray)
                 .toList();
    }
  }

  public long cost() {
    return 0;
  }
}