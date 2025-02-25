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
    final long actual = assertDoesNotThrow(() -> CodeSequencer.lengthAtDepth("example1.txt", 2));
    assertEquals(126384L, actual);
  }

  @Test
  void input() {
    final long actual = assertDoesNotThrow(() -> CodeSequencer.lengthAtDepth("input.txt", 2));
    assertEquals(278568L, actual);
  }

//  @Test
//  void input_5() {
//    final int actual = assertDoesNotThrow(() -> CodeSequencer.lengthAtDepth("input.txt", 25));
//    assertEquals(278568, actual);
//  }

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