package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Solver2Test {
  @Test
  void part2Example1() {
    final Solver2 solver = assertDoesNotThrow(() -> new Solver2("example1.txt"));
    assertEquals(45, solver.getSeatCount());
  }

  @Test
  void part2Example2() {
    final Solver2 solver = assertDoesNotThrow(() -> new Solver2("example2.txt"));
    assertEquals(64, solver.getSeatCount());
  }

  @Test
  void part2Input() {
    final Solver2 solver = assertDoesNotThrow(() -> new Solver2("input.txt"));
    assertEquals(561, solver.getSeatCount());
  }
}