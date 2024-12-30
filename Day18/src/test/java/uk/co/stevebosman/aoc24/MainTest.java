package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class MainTest {
  @Test
  void example1() throws IOException {
    Main.main(new String[]{"example1.txt", "6", "12"});
  }

  @Test
  void input() throws IOException {
    Main.main(new String[]{"input.txt", "70", "1024"});
  }

  @Test
  void inputMid() throws IOException {
    Main.main(new String[]{"input.txt", "70", "2914"});
  }

  @Test
  void inputEnd() throws IOException {
    Main.main(new String[]{"input.txt", "70", "2950"});
  }
}