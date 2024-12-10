package uk.co.stevebosman.aoc24;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrailheadTest {
  @ParameterizedTest
  @CsvSource(value = {
          "example1.txt, 2",
          "example2.txt, 4",
          "example3.txt, 3",
          "example4.txt, 36",
          "input.txt, 0",
  })
  void part1(final String filename, final int expected) throws IOException {
    final int actual = new Trailhead(filename).countTrails();
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource(value = {
          "example5.txt, 3",
          "example6.txt, 13",
          "example7.txt, 227",
          "input.txt, 0",
  })
  void part2(final String filename, final int expected) throws IOException {
    final int actual = new Trailhead(filename).countTrailRoutes();
    assertEquals(expected, actual);
  }
}
