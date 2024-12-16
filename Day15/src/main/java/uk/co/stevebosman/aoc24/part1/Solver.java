package uk.co.stevebosman.aoc24.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Solver {
  private final WarehouseWoesMap map;
  private final String moves;

  public Solver(final String filename) throws IOException {
    final List<String> lines = Files.readAllLines(Path.of(filename));
    final int separator = lines.indexOf("");

    final var map = lines.subList(0, separator);
    this.map = new WarehouseWoesMap(map);
    this.moves = String.join("", lines.subList(separator, lines.size()));
  }

  public static void main(final String[] args) throws IOException {
    new Solver(args[0]).solve(false);
  }

  public long solve(final boolean debug) {
    if (debug) System.out.println(map + "\n");
    for (int i = 0; i < moves.length(); i++) {
      final String move = moves.substring(i, i + 1);
      if (debug) System.out.println(i + ". " + move);
      this.map.move(move);
      if (debug) System.out.println(map + "\n");
    }
    return map.coordinateSum();
  }

}