package uk.co.stevebosman.aoc24.day20;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {
  @ParameterizedTest
  @CsvSource({
          "2, 44",
          "4, 30",
          "6, 16",
          "8, 14",
          "10, 10",
          "12, 8",
          "20, 5",
          "36, 4",
          "38, 3",
          "40, 2",
          "64, 1",
  })
  void example1(final long target, final long expected) throws IOException {
    final long actual = assertDoesNotThrow(() -> new Solver("example1.txt")).cheatsSaveAtLeast(target);
    assertEquals(expected, actual);
  }

  @Test
  void input1() throws IOException {
    final long actual = assertDoesNotThrow(() -> new Solver("input.txt")).cheatsSaveAtLeast(100);
    System.out.println("Part1 = " + actual);
  }
}