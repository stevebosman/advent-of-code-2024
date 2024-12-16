package uk.co.stevebosman.aoc24.part2;

import java.util.Comparator;

public record Coordinate(int row, int column) {
  public static final Comparator<? super Coordinate> TOP_DOWN
          = Comparator.comparingInt((Coordinate o) -> o.row)
                      .thenComparingInt(o -> o.column);
  public static final Comparator<? super Coordinate> BOTTOM_UP
          = Comparator.comparingInt((Coordinate o) -> -o.row)
                      .thenComparingInt(o -> o.column);
}
