package uk.co.stevebosman.aoc24;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GardenGroupsTest {

  @ParameterizedTest
  @CsvSource({
          "example1.txt,140",
          "example2.txt,772",
          "example3.txt,1930",
  })
  void part1(final String filename, final long expected) throws IOException {
    final long actual = new GardenGroups(filename).cost();
    assertEquals(expected, actual);
  }
}