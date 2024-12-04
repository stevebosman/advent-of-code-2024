package uk.co.stevebosman.aoc24.day04;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class X_MasCounterTest {
  @Test
  void textExample() {
    final List<String> grid = assertDoesNotThrow(() -> Files.readAllLines(Path.of("src/test/resources/example1.txt")));
    final int actual = new X_MasCounter(grid).count();
    assertEquals(9, actual);
  }
}