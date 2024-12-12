package uk.co.stevebosman.aoc24;

import java.util.List;
import java.util.Optional;

public record Region(long perimeter, long sides, long area) {
  public static Optional<Region> of(final List<char[]> map, final int startRow, final int startColumn, final int height, final int width) {
    final char regionId = map.get(startRow)[startColumn];
    if (regionId == '.') return Optional.empty();

    final int perimeter = countPerimeter(map, regionId, startRow, startColumn);

    final int sides = countSides(map, height, width);


    int area = 0;
    for (int rowIndex = 1; rowIndex < height - 1; rowIndex++) {
      final char[] row2 = map.get(rowIndex);
      for (int columnIndex = 1; columnIndex < width - 1; columnIndex++) {
        if (row2[columnIndex] == '#') {
          area++;
          row2[columnIndex] = '.';
        }
      }
    }

    return Optional.of(new Region(perimeter, sides, area));
  }

  private static int countSides(final List<char[]> map, final int height, final int width) {
    return countHorizontalSides(map, height, width) + countVerticalSides(map, height, width);
  }

  private static int countHorizontalSides(final List<char[]> map, final int height, final int width) {
    // count horizontal sides
    int sides = 0;
    boolean on = false;
    boolean below = false;
    for (int rowIndex = 0; rowIndex < height - 1; rowIndex++) {
      for (int columnIndex = 0; columnIndex < width - 1; columnIndex++) {
        final char current = map.get(rowIndex)[columnIndex];
        final char next = map.get(rowIndex+1)[columnIndex];
        if ( current == '#' && next != '#') {
          if (!on) {
            sides++;
          }
          below = false;
          on = true;
        } else if ( current != '#' && next == '#') {
          if (!below) {
            sides++;
          }
          below = true;
          on = false;
        } else {
          below = false;
          on = false;
        }
      }
    }
    return sides;
  }

  private static int countVerticalSides(final List<char[]> map, final int height, final int width) {
    // count horizontal sides
    int sides = 0;
    boolean on = false;
    boolean below = false;
    for (int columnIndex = 0; columnIndex < width - 1; columnIndex++) {
      for (int rowIndex = 0; rowIndex < height - 1; rowIndex++) {
        final char current = map.get(rowIndex)[columnIndex];
        final char next = map.get(rowIndex)[columnIndex+1];
        if ( current == '#' && next != '#') {
          if (!on) {
            sides++;
          }
          below = false;
          on = true;
        } else if ( current != '#' && next == '#') {
          if (!below) {
            sides++;
          }
          below = true;
          on = false;
        } else {
          below = false;
          on = false;
        }
      }
    }
    return sides;
  }

  private static int countPerimeter(final List<char[]> map, final char regionId, final int startRow, final int startColumn) {
    final char currentRegionId = map.get(startRow)[startColumn];
    if (currentRegionId != regionId) {
      return currentRegionId == '#'
             ? 0
             : 1;
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
