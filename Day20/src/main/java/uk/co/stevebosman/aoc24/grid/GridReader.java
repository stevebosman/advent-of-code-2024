package uk.co.stevebosman.aoc24.grid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public record GridReader<T>(Function<Character, T> mappingFunction) {
  public Grid<T> read(final String filename) throws IOException {
    try (final var lines = Files.lines(Path.of(filename))) {
      return new Grid<>(lines.map(this::mapLine).toList());
    }
  }

  private List<T> mapLine(final String line) {
    return line.codePoints()
               .mapToObj(c -> (char) c)
               .map(mappingFunction)
               .toList();
  }
}
