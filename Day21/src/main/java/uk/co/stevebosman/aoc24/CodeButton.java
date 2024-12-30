package uk.co.stevebosman.aoc24;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * <pre>
 * +---+---+---+
 * | 7 | 8 | 9 |
 * +---+---+---+
 * | 4 | 5 | 6 |
 * +---+---+---+
 * | 1 | 2 | 3 |
 * +---+---+---+
 *     | 0 | A |
 *     +---+---+
 * </pre>
 */
public enum CodeButton {
  A,
  ZERO,
  ONE,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE;

  private static final Map<CodeButton, Map<CodeButton, List<String>>> presses = Map.ofEntries(
          entry(A,
                Map.ofEntries(
                        entry(ZERO,
                              List.of("<A")),
                        entry(ONE,
                              List.of("^<<A", "<^<A")),
                        entry(TWO,
                              List.of("<^A", "^<A")),
                        entry(THREE,
                              List.of("^A")),
                        entry(FOUR,
                              List.of("^^<<A", "^<<^A", "<^<^A", "^<^<A", "<^^<A")),
                        entry(FIVE,
                              List.of("<^^A", "^^<A", "^<^A")),
                        entry(SIX,
                              List.of("^^A")),
                        entry(SEVEN,
                              List.of("^^^<<A", "^^<^<A", "^<^^<A", "<^^^<A", "^<<^^A", "<^<^^A", "^^<<^A",
                                      "^<^<^A", "<^^<^A")),
                        entry(EIGHT,
                              List.of("<^^^A", "^^<^A", "^<^^A", "^^^<A")),
                        entry(NINE,
                              List.of("^^^A")))),
          entry(ZERO,
                Map.ofEntries(
                        entry(A, List.of(">A")),
                        entry(ONE, List.of()),
                        entry(TWO, List.of("^A")),
                        entry(THREE, List.of()),
                        entry(FOUR, List.of()),
                        entry(FIVE, List.of()),
                        entry(SIX, List.of()),
                        entry(SEVEN, List.of()),
                        entry(EIGHT, List.of()),
                        entry(NINE, List.of()))),
          entry(ONE,
                Map.ofEntries(
                        entry(A, List.of(">>vA", ">v>A")),
                        entry(ZERO, List.of(">vA")),
                        entry(TWO, List.of(">A")),
                        entry(THREE, List.of()),
                        entry(FOUR, List.of()),
                        entry(FIVE, List.of()),
                        entry(SIX, List.of()),
                        entry(SEVEN, List.of("^^A")),
                        entry(EIGHT, List.of()),
                        entry(NINE, List.of()))),
          entry(TWO,
                Map.ofEntries(
                        entry(A, List.of("v>A", ">vA")),
                        entry(ZERO, List.of()),
                        entry(ONE, List.of()),
                        entry(THREE, List.of(">A")),
                        entry(FOUR, List.of()),
                        entry(FIVE, List.of()),
                        entry(SIX, List.of("^>A")),
                        entry(SEVEN, List.of()),
                        entry(EIGHT, List.of()),
                        entry(NINE, List.of("^^>A", "^>^A", ">^^A")))),
          entry(THREE,
                Map.ofEntries(
                        entry(A, List.of("vA")),
                        entry(ZERO, List.of()),
                        entry(ONE, List.of()),
                        entry(TWO, List.of()),
                        entry(FOUR, List.of()),
                        entry(FIVE, List.of()),
                        entry(SIX, List.of()),
                        entry(SEVEN, List.of("<<^^A", "^^<<A", "^<^<A", "^<<^A", "<^^<A", "<^<^A")),
                        entry(EIGHT, List.of()),
                        entry(NINE, List.of("^^A")))),
          entry(FOUR,
                Map.ofEntries(
                        entry(A, List.of(">>vvA", "v>>vA", "v>v>A", ">v>vA", ">vv>A")),
                        entry(ZERO, List.of(">vvA", "v>vA")),
                        entry(ONE, List.of()),
                        entry(TWO, List.of()),
                        entry(THREE, List.of()),
                        entry(FIVE, List.of(">A")),
                        entry(SIX, List.of(">A")),
                        entry(SEVEN, List.of()),
                        entry(EIGHT, List.of()),
                        entry(NINE, List.of()))),
          entry(FIVE,
                Map.ofEntries(
                        entry(A, List.of("vv>A", ">vvA", "v>vA")),
                        entry(ZERO, List.of()),
                        entry(ONE, List.of()),
                        entry(TWO, List.of()),
                        entry(THREE, List.of()),
                        entry(FOUR, List.of("<A")),
                        entry(SIX, List.of(">A")),
                        entry(SEVEN, List.of()),
                        entry(EIGHT, List.of()),
                        entry(NINE, List.of()))),
          entry(SIX,
                Map.ofEntries(
                        entry(A, List.of("vvA")),
                        entry(ZERO, List.of()),
                        entry(ONE, List.of()),
                        entry(TWO, List.of()),
                        entry(THREE, List.of()),
                        entry(FOUR, List.of()),
                        entry(FIVE, List.of()),
                        entry(SEVEN, List.of()),
                        entry(EIGHT, List.of("<^A", "^<A")),
                        entry(NINE, List.of()))),
          entry(SEVEN,
                Map.ofEntries(
                        entry(A, List.of(">>vvvA", ">v>vvA", ">vv>vA", ">vvv>A", "v>>vvA", "vv>>vA", "v>vv>A", "vv>v>A",
                                         "v>v>vA")),
                        entry(ZERO, List.of()),
                        entry(ONE, List.of()),
                        entry(TWO, List.of()),
                        entry(THREE, List.of()),
                        entry(FOUR, List.of("vA")),
                        entry(FIVE, List.of()),
                        entry(SIX, List.of()),
                        entry(EIGHT, List.of(">A")),
                        entry(NINE, List.of(">>A")))),
          entry(EIGHT,
                Map.ofEntries(entry(A, List.of("vvv>A", ">vvvA", "v>vvA", "vv>vA")),
                              entry(ZERO, List.of("vvvA")),
                              entry(ONE, List.of()),
                              entry(TWO, List.of("vvA")),
                              entry(THREE, List.of("vv>A", "v>vA", ">vvA")),
                              entry(FOUR, List.of()), entry(FIVE, List.of()),
                              entry(SIX, List.of()), entry(SEVEN, List.of()),
                              entry(NINE, List.of(">A")))),
          entry(NINE,
                Map.ofEntries(entry(A, List.of("vvvA")), entry(ZERO, List.of()),
                              entry(ONE, List.of()),
                              entry(TWO, List.of()),
                              entry(THREE, List.of()), entry(FOUR, List.of()),
                              entry(FIVE, List.of()),
                              entry(SIX, List.of()),
                              entry(SEVEN, List.of("<<A")),
                              entry(EIGHT, List.of("<A"))))
  );

  public static CodeButton of(final char code) {
    return switch (code) {
      case 'A' -> A;
      case '0' -> ZERO;
      case '1' -> ONE;
      case '2' -> TWO;
      case '3' -> THREE;
      case '4' -> FOUR;
      case '5' -> FIVE;
      case '6' -> SIX;
      case '7' -> SEVEN;
      case '8' -> EIGHT;
      case '9' -> NINE;
      default -> throw new IllegalArgumentException("Unknown code: " + code);
    };
  }

  public String toFirst(final CodeButton d) {
    if (this == d) return "A";
    final List<String> directions = presses.get(this)
                                           .get(d);
    if (directions.isEmpty()) throw new UnsupportedOperationException(this + " to " + d + " has not been implemented");
    return directions.getFirst();
  }
}
