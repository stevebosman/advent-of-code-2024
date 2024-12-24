package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionsTest {
  @Test
  void tSetCount() {
    final Connections instance = assertDoesNotThrow(() -> new Connections("example1.txt"));
    final int actual = instance.tSetCount();
    assertEquals(7, actual);
  }
}