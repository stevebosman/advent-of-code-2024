package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
  public static void main(final String[] args) throws IOException {
    final String filename = args[0];
    System.out.println(filename);
    System.out.println("=".repeat(filename.length()));
    try (final var codes = Files.lines(Path.of(filename))) {
      final Integer part1 = codes.map(CodeSequencer::new)
                                 .map(seq -> seq.instructions(2)
                                           .length() * seq.numeric())
                                 .reduce(0, Integer::sum);
      System.out.println("part1 = " + part1);
    }
  }
}