package uk.co.stevebosman.aoc24;

import java.io.IOException;

public class Main {
  public static void main(final String[] args) throws IOException {
    final String filename = args[0];
    final int depth = Integer.parseInt(args[1]);
    System.out.println(filename);
    System.out.println("=".repeat(filename.length()));
    System.out.println(depth + " = " + CodeSequencer.lengthAtDepth(filename, depth));
    System.out.println();
  }
}