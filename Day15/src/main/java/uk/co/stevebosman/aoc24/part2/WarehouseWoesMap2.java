package uk.co.stevebosman.aoc24.part2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class WarehouseWoesMap2 {
  private final List<char[]> map;
  private int robotRow;
  private int robotColumn;

  public WarehouseWoesMap2(final List<String> map) throws IOException {
    final String robotStartLine = map.stream()
                                     .filter(l -> l.contains("@"))
                                     .findFirst()
                                     .orElse("");
    this.map = new ArrayList<>(map.stream()
                                  .map(this::doubleLine)
                                  .toList());
    this.robotRow = map.indexOf(robotStartLine);
    final int robotColumn = robotStartLine.indexOf("@");
    this.robotColumn = robotColumn * 2;
    this.map.get(robotRow)[this.robotColumn] = '.';
    this.map.get(robotRow)[this.robotColumn+1] = '.';
  }

  private char[] doubleLine(final String line) {
    final char[] chars = line.toCharArray();
    final char[] result = new char[2*line.length()];
    for (int i = 0; i < chars.length; i++) {
      if (chars[i]=='O') {
        result[2 * i] = '[';
        result[2 * i + 1] = ']';
      } else {
        result[2 * i] = chars[i];
        result[2 * i + 1] = chars[i];
      }
    }
    return result;
  }

  public void move(final String move) {
    if ("<".equals(move)) moveLeft();
    else if (">".equals(move)) moveRight();
    else if ("^".equals(move)) moveUp();
    else if ("v".equals(move)) moveDown();
  }

  private boolean moveLeft() {
    boolean result;
    final char[] currentRow = this.map.get(robotRow);
    final char next = currentRow[robotColumn - 1];
    if (next == '#') {
      result = false;
    } else if (next == '.') {
      // just move left
      result = true;
    } else {
      // attempt push
      result = false;
      // find previous space
      for (int i = robotColumn - 2; i > 0; i--) {
        if (currentRow[i] == '#') {
          break;
        }
        if (currentRow[i] == '.') {
          for (int j = i; j < robotColumn - 1; j++) {
            currentRow[j] = currentRow[j+1];
          }
          currentRow[robotColumn - 1] = '.';
          result = true;
          break;
        }
      }
    }
    if (result) {
      robotColumn--;
    }
    return result;
  }

  private boolean moveRight() {
    boolean result;
    final char[] currentRow = this.map.get(robotRow);
    final char next = currentRow[robotColumn + 1];
    if (next == '#') {
      result = false;
    } else if (next == '.') {
      // just move right
      result = true;
    } else {
      // attempt push
      result = false;
      // find next space
      for (int i = robotColumn + 2; i < currentRow.length; i++) {
        if (currentRow[i] == '#') {
          break;
        }
        if (currentRow[i] == '.') {
          for (int j = i; j > robotColumn + 1; j--) {
            currentRow[j] = currentRow[j-1];
          }
          currentRow[robotColumn + 1] = '.';
          result = true;
          break;
        }
      }
    }
    if (result) {
      robotColumn++;
    }
    return result;
  }

  private boolean moveUp() {
    boolean result;
    final char next = this.map.get(robotRow - 1)[robotColumn];
    if (next == '#') {
      result = false;
    } else if (next == '.') {
      // just move up
      result = true;
    } else {
      // attempt push
      result = false;
      // move up
      final Set<Coordinate> moveMe = new TreeSet<>(Coordinate.TOP_DOWN);
      final Coordinate c1;
      final Coordinate c2;
      if (next == '[') {
        c1 = new Coordinate(robotRow - 1, robotColumn);
        c2 = new Coordinate(robotRow - 1, robotColumn + 1);
      } else {
        c1 = new Coordinate(robotRow - 1, robotColumn - 1);
        c2 = new Coordinate(robotRow - 1, robotColumn);
      }
      if (addAbove(c1, moveMe, map) && addAbove(c2, moveMe, map)) {
        result = true;
        moveMe.forEach(c -> {
          System.out.println("c = " + c);
          map.get(c.row() - 1)[c.column()] = map.get(c.row())[c.column()];
          map.get(c.row())[c.column()] = '.';
        });
        System.out.println("map =\n" + this);
      }
    }
    if (result) {
      robotRow--;
    }
    return result;
  }

  private boolean addAbove(final Coordinate coordinate, final Set<Coordinate> moveMe, final List<char[]> map) {
    moveMe.add(coordinate);
    final char above1 = this.map.get(coordinate.row()-1)[coordinate.column()];
    if (above1 == '#') return false;
    if (above1 == '.') return true;
    final Coordinate c1;
    final Coordinate c2;
    if (above1 == '[') {
      c1 = new Coordinate(coordinate.row() - 1, coordinate.column());
      c2 = new Coordinate(coordinate.row() - 1, coordinate.column() + 1);
    } else if (above1 == ']') {
      c1 = new Coordinate(coordinate.row() - 1, coordinate.column() - 1);
      c2 = new Coordinate(coordinate.row() - 1, coordinate.column());
    } else {
      return false;
    }
    return addAbove(c1, moveMe, map) && addAbove(c2, moveMe, map);
  }

  private boolean moveDown() {
    boolean result;
    final char next = this.map.get(robotRow + 1)[robotColumn];
    if (next == '#') {
      result = false;
    } else if (next == '.') {
      // just move down
      result = true;
    } else {
      // attempt push
      result = false;
      // move down
      final Set<Coordinate> moveMe = new TreeSet<>(Coordinate.BOTTOM_UP);
      final Coordinate c1;
      final Coordinate c2;
      if (next == '[') {
        c1 = new Coordinate(robotRow + 1, robotColumn);
        c2 = new Coordinate(robotRow + 1, robotColumn + 1);
      } else {
        c1 = new Coordinate(robotRow + 1, robotColumn - 1);
        c2 = new Coordinate(robotRow + 1, robotColumn);
      }
      if (addBelow(c1, moveMe, map) && addBelow(c2, moveMe, map)) {
        result = true;
        moveMe.forEach(c -> {
          System.out.println("c = " + c);
          map.get(c.row() + 1)[c.column()] = map.get(c.row())[c.column()];
          map.get(c.row())[c.column()] = '.';
        });
        System.out.println("map =\n" + this);
      }
    }
    if (result) {
      robotRow++;
    }
    return result;
  }

  private boolean addBelow(final Coordinate coordinate, final Set<Coordinate> moveMe, final List<char[]> map) {
    moveMe.add(coordinate);
    final char below1 = this.map.get(coordinate.row()+1)[coordinate.column()];
    if (below1 == '#') return false;
    if (below1 == '.') return true;
    final Coordinate c1;
    final Coordinate c2;
    if (below1 == '[') {
      c1 = new Coordinate(coordinate.row() + 1, coordinate.column());
      c2 = new Coordinate(coordinate.row() + 1, coordinate.column() + 1);
    } else {
      c1 = new Coordinate(coordinate.row() + 1, coordinate.column() - 1);
      c2 = new Coordinate(coordinate.row() + 1, coordinate.column());
    }
    return addBelow(c1, moveMe, map) && addBelow(c2, moveMe, map);
  }

  public String toString() {
    this.map.get(robotRow)[robotColumn]='@';
    final String result = this.map.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    this.map.get(robotRow)[robotColumn]='.';
    return result;
  }

  public long coordinateSum() {
    long result = 0;
    for (int rowIndex = 0; rowIndex < map.size(); rowIndex++) {
      final char[] row = map.get(rowIndex);
      for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
        if (row[columnIndex] == '[') {
          result+=100L*rowIndex + columnIndex;
        }
      }
    }
    return result;
  }
}
