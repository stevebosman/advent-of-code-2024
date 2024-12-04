package uk.co.stevebosman.aoc24.day04;

import lombok.AllArgsConstructor;

import java.util.List;

public class X_MasCounter {
  private final List<char[]> grid;

  public X_MasCounter(final List<String> grid) {
    this.grid = grid.stream()
                    .map(String::toCharArray)
                    .toList();
  }

  public int count() {
    int count = 0;
    for (int rowIndex = 1; rowIndex < grid.size() - 1; rowIndex++) {
      final char[] row = grid.get(rowIndex);
      for (int columnIndex = 1; columnIndex < row.length - 1; columnIndex++) {
        if (row[columnIndex] == 'A') {
          final boolean diagonal1 = ((grid.get(rowIndex - 1)[columnIndex - 1] == 'M' && grid.get(rowIndex + 1)[columnIndex + 1] == 'S')
                  || (grid.get(rowIndex - 1)[columnIndex - 1] == 'S' && grid.get(rowIndex + 1)[columnIndex + 1] == 'M'));
          final boolean diagonal2 = ((grid.get(rowIndex - 1)[columnIndex + 1] == 'M' && grid.get(rowIndex + 1)[columnIndex - 1] == 'S')
                  || (grid.get(rowIndex - 1)[columnIndex + 1] == 'S' && grid.get(rowIndex + 1)[columnIndex - 1] == 'M'));
          if (diagonal1 && diagonal2) {
            count++;
          }
        }
      }
    }
    return count;
  }
}
