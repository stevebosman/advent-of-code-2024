package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
  @Test
  void example1() {
    final Solver solver = assertDoesNotThrow(() -> new Solver("example1.txt"));
    assertEquals(7036, solver.solve());
  }

  @Test
  void example2() {
    final Solver solver = assertDoesNotThrow(() -> new Solver("example2.txt"));
    final int actual = solver.solve();
    solver.printMap();
    assertEquals(11048, actual);
  }

  @Test
  void input() {
    final Solver solver = assertDoesNotThrow(() -> new Solver("input.txt"));
    final int actual = solver.solve();
    solver.printMap();
    System.out.println("part1 = " + actual);
  }
}