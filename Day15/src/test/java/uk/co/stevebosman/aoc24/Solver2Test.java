package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;
import uk.co.stevebosman.aoc24.part2.Solver2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Solver2Test {
  @Test
  void example2() {
    final long actual = assertDoesNotThrow(() -> new Solver2("example2.txt")).solve(true);
    assertEquals(9021, actual);
  }

  @Test
  void example3() {
    final long actual = assertDoesNotThrow(() -> new Solver2("example3.txt")).solve(true);
    assertEquals(618, actual);
  }

  @Test
  void example4() {
    final long actual = assertDoesNotThrow(() -> new Solver2("example4.txt")).solve(true);
    assertEquals(424, actual);
  }

  @Test
  void example5() {
    final long actual = assertDoesNotThrow(() -> new Solver2("example5.txt")).solve(true);
    assertEquals(1023, actual);
  }

  @Test
  void part2() {
    final long actual = assertDoesNotThrow(() -> new Solver2("input.txt")).solve(false);
    System.out.println("part2 = " + actual);
  }
}