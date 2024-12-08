package uk.co.stevebosman.aoc2024.day08;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
  @Test
  void testExamplePart1() throws IOException {
    assertEquals(14, new Solver(false, Path.of("example.txt")).solve());
  }
  @Test
  void testExamplePart2() throws IOException {
    assertEquals(34, new Solver(true, Path.of("example.txt")).solve());
  }
}