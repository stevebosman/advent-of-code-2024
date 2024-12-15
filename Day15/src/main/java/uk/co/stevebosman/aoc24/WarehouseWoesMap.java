package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class WarehouseWoesMap {
  private final List<char[]> map;
  private int robotRow;
  private int robotColumn;

  public WarehouseWoesMap(final List<String> map) throws IOException {
    final String robotStartLine = map.stream()
                                     .filter(l -> l.contains("@"))
                                     .findFirst()
                                     .orElse("");
    this.map = new ArrayList<>(map.stream()
                                  .map(String::toCharArray)
                                  .toList());
    this.robotRow = map.indexOf(robotStartLine);
    this.robotColumn = robotStartLine.indexOf("@");
    this.map.get(robotRow)[robotColumn] = '.';
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
          currentRow[robotColumn - 1] = '.';
          currentRow[i] = 'O';
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
          currentRow[robotColumn + 1] = '.';
          currentRow[i] = 'O';
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
      // just move left
      result = true;
    } else {
      // attempt push
      result = false;
      // find previous space
      for (int i = robotRow - 2; i > 0; i--) {
        if (this.map.get(i)[robotColumn] == '#') {
          break;
        }
        if (this.map.get(i)[robotColumn] == '.') {
          this.map.get(robotRow - 1)[robotColumn] = '.';
          this.map.get(i)[robotColumn] = 'O';
          result = true;
          break;
        }
      }
    }
    if (result) {
      robotRow--;
    }
    return result;
  }

  private boolean moveDown() {
    boolean result;
    final char next = this.map.get(robotRow + 1)[robotColumn];
    if (next == '#') {
      result = false;
    } else if (next == '.') {
      // just move left
      result = true;
    } else {
      // attempt push
      result = false;
      // find previous space
      for (int i = robotRow + 2; i < this.map.size(); i++) {
        if (this.map.get(i)[robotColumn] == '#') {
          break;
        }
        if (this.map.get(i)[robotColumn] == '.') {
          this.map.get(robotRow + 1)[robotColumn] = '.';
          this.map.get(i)[robotColumn] = 'O';
          result = true;
          break;
        }
      }
    }
    if (result) {
      robotRow++;
    }
    return result;
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
        if (row[columnIndex] == 'O') {
          result+=100L*rowIndex + columnIndex;
        }
      }
    }
    return result;
  }
}
