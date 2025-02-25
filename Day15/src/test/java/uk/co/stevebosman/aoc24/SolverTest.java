package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;
import uk.co.stevebosman.aoc24.part1.Solver;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {
  @Test
  void example1() {
    final long actual = assertDoesNotThrow(() -> new Solver("example1.txt")).solve(false);
    assertEquals(2028, actual);
  }

  @Test
  void example2() {
    final long actual = assertDoesNotThrow(() -> new Solver("example2.txt")).solve(false);
    assertEquals(10092, actual);
  }

  @Test
  void part1() {
    final long actual = assertDoesNotThrow(() -> new Solver("input.txt")).solve(false);
    System.out.println("part1 = " + actual);
  }
}