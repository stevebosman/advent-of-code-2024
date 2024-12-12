package uk.co.stevebosman.aoc24;

import java.util.List;
import java.util.Optional;

public record Region(long perimeter, long area) {
  public static Optional<Region> of(final List<char[]> map, final int startRow, final int startColumn, final int height, final int width) {
    final char regionId = map.get(startRow)[startColumn];
    if (regionId == '.') return Optional.empty();

    final int perimeter = countPerimeter(map, regionId, startRow, startColumn);

    int area = 0;
    for (int rowIndex2 = 0; rowIndex2 < height; rowIndex2++) {
      final char[] row2 = map.get(rowIndex2);
      for (int columnIndex2 = 0; columnIndex2 < width; columnIndex2++) {
        if (row2[columnIndex2]=='#') {
          area++;
          row2[columnIndex2] = '.';
        }
      }
    }

    return Optional.of(new Region(perimeter, area));
  }

  private static int countPerimeter(final List<char[]> map, final char regionId, final int startRow, final int startColumn) {
    final char currentRegionId = map.get(startRow)[startColumn];
    if (currentRegionId != regionId) {
      return currentRegionId == '#'? 0: 1;
    }
    map.get(startRow)[startColumn] = '#';
    int perimeter = 0;
    perimeter += countPerimeter(map, regionId, startRow - 1, startColumn);
    perimeter += countPerimeter(map, regionId, startRow + 1, startColumn);
    perimeter += countPerimeter(map, regionId, startRow, startColumn - 1);
    perimeter += countPerimeter(map, regionId, startRow, startColumn + 1);
    return perimeter;
  }

}
