package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class MainTest {
  @Test
  void example1() throws IOException {
    Main.main(new String[]{"example1.txt"});
  }

  @Test
  void input() throws IOException {
    Main.main(new String[]{"input.txt"});
  }
}