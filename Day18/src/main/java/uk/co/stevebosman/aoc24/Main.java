package uk.co.stevebosman.aoc24;

import java.io.IOException;

public class Main {
  public static void main(final String[] args) throws IOException {
    final String filename = args[0];
    final int size = Integer.parseInt(args[1]);
    final int limit = Integer.parseInt(args[2]);
    System.out.println(filename);
    System.out.println("=".repeat(filename.length()));
    final Solver solver = new Solver(filename, size, limit);
    System.out.println("Part 1 = " + solver.solve());
    System.out.println();
  }
}