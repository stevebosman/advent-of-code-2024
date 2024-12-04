package uk.co.stevebosman.aoc24.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

  @Test
  void textMain() {
    assertDoesNotThrow(() -> Main.main(new String[]{"src/test/resources/example1.txt"}));
  }
}