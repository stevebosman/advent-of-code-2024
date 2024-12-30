package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solver {
  private final int[][] grid;

  public Solver(final String filename, final int size, final int limit) throws IOException {
    this.grid = new int[size+1][size+1];
    final var lines = Files.readAllLines(Path.of(filename));
    for (int i = 0; i < Math.min(lines.size(), limit); i++) {
      final var coordText = lines.get(i).split(",");
      final int x = Integer.parseInt(coordText[0]);
      final int y = Integer.parseInt(coordText[1]);
      this.grid[y][x] = i+1;
    }
  }

  public int solve() {
    System.out.println("#".repeat(grid.length+2));
    for (int i = 0; i < grid.length; i++) {
      final var row = grid[grid.length-i-1];
      System.out.print("#");
      for (int j = 0; j < row.length; j++) {
        System.out.print(row[j]==0?".":"#");
      }
      System.out.print("#");
      System.out.println();
    }
    System.out.println("#".repeat(grid.length+2));
    return 0;
  }
}
