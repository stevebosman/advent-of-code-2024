package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
  @Test
  void part1Example1() {
    final Solver solver = assertDoesNotThrow(() -> new Solver("example1.txt"));
    assertEquals(7036, solver.getCheapestRoute());
  }

  @Test
  void part1Example2() {
    final Solver solver = assertDoesNotThrow(() -> new Solver("example2.txt"));
    assertEquals(11048, solver.getCheapestRoute());
  }

  @Test
  void part1Input() {
    final Solver solver = assertDoesNotThrow(() -> new Solver("input.txt"));
    assertEquals(107512, solver.getCheapestRoute());
  }

  @Test
  void part2Example1() {
    final Solver solver = assertDoesNotThrow(() -> new Solver("example1.txt"));
    assertEquals(45, solver.getSeatCount());
  }

  @Test
  void part2Example2() {
    final Solver solver = assertDoesNotThrow(() -> new Solver("example2.txt"));
    assertEquals(64, solver.getSeatCount());
  }

  @Test
  @Disabled
  void part2Input() {
    // does not work solver rewritten - see Solver2
    final Solver solver = assertDoesNotThrow(() -> new Solver("input.txt"));
    assertEquals(561, solver.getSeatCount());
  }
}