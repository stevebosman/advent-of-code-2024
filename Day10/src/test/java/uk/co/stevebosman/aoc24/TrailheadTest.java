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
  void examples(final String filename, final int expected) throws IOException {
    final int actual = new Trailhead(filename).countTrails();
    assertEquals(expected, actual);
  }
}
