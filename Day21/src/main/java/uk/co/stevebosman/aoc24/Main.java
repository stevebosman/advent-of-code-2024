package uk.co.stevebosman.aoc24;

import java.io.IOException;

public class Main {
  public static void main(final String[] args) throws IOException {
    final String filename = args[0];
    System.out.println(filename);
    System.out.println("=".repeat(filename.length()));
    System.out.println("part1 = " + CodeSequencer.part1(filename));
  }
}