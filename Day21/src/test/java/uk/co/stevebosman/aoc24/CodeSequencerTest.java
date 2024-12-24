package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CodeSequencerTest {
  @Test
  void example1() {
    final int actual = assertDoesNotThrow(() -> CodeSequencer.part1("example1.txt"));
    assertEquals(126384, actual);
  }


  @ParameterizedTest
  @CsvSource({
          "029A, 0, <A^A>^^AvvvA|<A^A^>^AvvvA|<A^A^^>AvvvA"
  })
  void instructions(final String code, final int depth, final String expectedOptions) {
    final Set<String> expected = Arrays.stream(expectedOptions.split("\\|")).collect(Collectors.toSet());
    final String actual = new CodeSequencer(code).instructions(depth);
    assertTrue(expected.contains(actual), () -> "Expected %s to contain actual '%s'".formatted(expected, actual));
  }
}