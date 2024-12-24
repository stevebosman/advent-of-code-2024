package uk.co.stevebosman.aoc24;

import java.io.IOException;

public class Main {
  public static void main(final String[] args) throws IOException {
    final Connections instance = new Connections(args[0]);
    System.out.println("part1 = " + instance.tSetCount());
  }
}