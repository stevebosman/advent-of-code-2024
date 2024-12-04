package uk.co.stevebosman.aoc24.day04;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XmasCounterTest {
  @Test
  void textForwardHorizontal() {
    final List<String> grid = List.of("XMAS0X0XMAS","X000XMAS00X");
    final int actual = new XmasCounter(grid).count();
    assertEquals(3, actual);
  }

  @Test
  void textReverseHorizontal() {
    final List<String> grid = List.of("SAMX000SAMXX","X000SAMX00X");
    final int actual = new XmasCounter(grid).count();
    assertEquals(3, actual);
  }

  @Test
  void textExample() {
    final List<String> grid = assertDoesNotThrow(() -> Files.readAllLines(Path.of("src/test/resources/example1.txt")));
    final int actual = new XmasCounter(grid).count();
    assertEquals(18, actual);
  }
}