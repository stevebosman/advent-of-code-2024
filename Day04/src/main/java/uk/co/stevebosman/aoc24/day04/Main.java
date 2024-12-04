package uk.co.stevebosman.aoc24.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
  public static void main(final String[] args) throws IOException {
    final List<String> lines = Files.readAllLines(Path.of(args[0]));
    System.out.println("XMAS count: " + new XmasCounter(lines).count());
    System.out.println("X-MAS count: " + new X_MasCounter(lines).count());
  }
}