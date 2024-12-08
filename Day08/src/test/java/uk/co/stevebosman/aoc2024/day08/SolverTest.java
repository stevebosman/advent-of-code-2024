package uk.co.stevebosman.aoc2024.day08;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
  @Test
  void testExample() throws IOException {
    assertEquals(14, new Solver(Path.of("example.txt")).solve());
  }
}