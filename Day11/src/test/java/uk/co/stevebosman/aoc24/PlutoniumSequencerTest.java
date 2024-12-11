package uk.co.stevebosman.aoc24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlutoniumSequencerTest {
  private static final String INPUT = "572556 22 0 528 4679021 1 10725 2790";
  //  Part 1 tests - now invalid
//  @ParameterizedTest
//  @CsvSource({
//          "125 17,1,253000 1 7",
//                  "125 17,2,253 0 2024 14168",
//                  "125 17,3,512072 1 20 24 28676032",
//                  "125 17,4,512 72 2024 2 0 2 4 2867 6032",
//                  "125 17,5,1036288 7 2 20 24 4048 1 4048 8096 28 67 60 32",
//                  "125 17,6,2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2"
//  })
//  void iterateExample(final String initialState, final int blinks, final String endState) {
//    final PlutoniumSequencer sequencer = new PlutoniumSequencer(initialState);
//    sequencer.iterate(blinks);
//    assertEquals(endState, sequencer.toString());
//  }

  @Test
  void resultExample25() {
    final PlutoniumSequencer sequencer = new PlutoniumSequencer("125 17");
    sequencer.iterate(25);
    assertEquals(55312, sequencer.size());
  }

  @Test
  void runPart1() {
    final PlutoniumSequencer sequencer = new PlutoniumSequencer(INPUT);
    sequencer.iterate(25);
    System.out.println("Part 1 = " + sequencer.size());
  }

  @Test
  void runPart2() {
    final PlutoniumSequencer sequencer = new PlutoniumSequencer(INPUT);
    sequencer.iterate(75);
    System.out.println("Part 2 = " + sequencer.size());
  }
}
