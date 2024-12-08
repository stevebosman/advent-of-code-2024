package uk.co.stevebosman.aoc2024.day08;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
  @Test
  void testExample() {
    assertDoesNotThrow(() -> Main.main(new String[]{"example.txt"}));
  }
  @Test
  void testInput() {
    assertDoesNotThrow(() -> Main.main(new String[]{"input.txt"}));
  }
}