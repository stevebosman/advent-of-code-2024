package uk.co.stevebosman.aoc24.day04;

import lombok.AllArgsConstructor;

import java.util.List;

public class XmasCounter {
  private final List<char[]> grid;

  public XmasCounter(final List<String> grid) {
    this.grid = grid.stream()
                    .map(String::toCharArray)
                    .toList();
  }

  public int count() {
    int count = 0;
    for (int rowIndex = 0; rowIndex < grid.size(); rowIndex++) {
      final char[] row = grid.get(rowIndex);
      for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
        if (row[columnIndex] == 'X') {
          for (final Scan scan : Scan.values()) {
            try {
              if (scan.scannable(row.length, grid.size(), columnIndex, rowIndex)
                      && grid.get(rowIndex + scan.offsetRow)[columnIndex + scan.offsetColumn] == 'M'
                      && grid.get(rowIndex + 2 * scan.offsetRow)[columnIndex + 2 * scan.offsetColumn] == 'A'
                      && grid.get(rowIndex + 3 * scan.offsetRow)[columnIndex + 3 * scan.offsetColumn] == 'S') {
                count++;
              }
            } catch (final IndexOutOfBoundsException e) {
              System.out.println("e.getMessage() = " + e.getMessage());
              System.out.println("rowIndex = " + rowIndex);
              System.out.println("columnIndex = " + columnIndex);
              System.out.println("----");
            }
          }
        }
      }
    }
    return count;
  }

  @AllArgsConstructor
  private enum Scan {
    N(0, -1),
    NE(1, -1),
    E(1, 0),
    SE(1, 1),
    S(0, 1),
    SW(-1, 1),
    W(-1, 0),
    NW(-1, -1);
    private final int offsetColumn;
    private final int offsetRow;

    public boolean scannable(final int columnCount, final int rowCount, final int columnIndex, final int rowIndex) {
      return !((offsetColumn > 0 && columnIndex > columnCount - 4)
              || (offsetColumn < 0 && columnIndex < 3)
              || (offsetRow > 0 && rowIndex > rowCount - 4)
              || (offsetRow < 0 && rowIndex < 3));
    }
  }
}
