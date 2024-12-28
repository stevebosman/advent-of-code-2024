package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConnectionsTest {
  @Test
  void getTripletsCountWithT() {
    final Connections instance = assertDoesNotThrow(() -> new Connections("example1.txt"));
    final int actual = instance.getTripletsCountWithT();
    assertEquals(7, actual);
  }

  @Test
  void password() {
    final Connections instance = assertDoesNotThrow(() -> new Connections("example1.txt"));
    final String actual = instance.password();
    assertEquals("co,de,ka,ta", actual);
  }
}