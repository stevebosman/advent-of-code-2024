package uk.co.stevebosman.aoc2024.day08;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
  public static void main(final String[] args) throws IOException {
    System.out.println("Part 1: " + new Solver(false, Path.of(args[0])).solve());
    System.out.println("Part 2: " + new Solver(true, Path.of(args[0])).solve());
  }
}