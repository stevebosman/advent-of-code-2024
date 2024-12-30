package uk.co.stevebosman.aoc24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * <pre>
 *     +---+---+
 *     | ^ | A |
 * +---+---+---+
 * | < | v | > |
 * +---+---+---+
 * </pre>
 */
public enum DirectionButton {
  A,
  UP,
  DOWN,
  LEFT,
  RIGHT;

  private static final Map<DirectionButton, Map<DirectionButton, List<String>>> presses = Map.ofEntries(
          entry(A,
                Map.ofEntries(
                        entry(UP, List.of("<A")),
                        entry(DOWN, List.of("<vA", "v<A")),
                        entry(LEFT, List.of("v<<A", "<v<A")),
                        entry(RIGHT, List.of("vA")))),
          entry(UP,
                Map.ofEntries(
                        entry(A, List.of(">A")),
                        entry(DOWN, List.of("vA")),
                        entry(LEFT, List.of("v<A")),
                        entry(RIGHT, List.of("v>A", ">vA")))),
          entry(DOWN,
                Map.ofEntries(
                        entry(A, List.of("^>A", ">^A")),
                        entry(UP, List.of("^A")),
                        entry(LEFT, List.of("<A")),
                        entry(RIGHT, List.of(">A")))),
          entry(LEFT,
                Map.ofEntries(
                        entry(A, List.of(">>^A", ">^>A")),
                        entry(UP, List.of(">^A")),
                        entry(DOWN, List.of(">A")),
                        entry(RIGHT, List.of(">>A")))),
          entry(RIGHT,
                Map.ofEntries(
                        entry(A, List.of("^A")),
                        entry(UP, List.of("<^A", "^<A")),
                        entry(DOWN, List.of("<A")),
                        entry(LEFT, List.of("<<A"))))
  );

  public static DirectionButton of(final char code) {
    return switch (code) {
      case 'A' -> A;
      case '^' -> UP;
      case 'v' -> DOWN;
      case '<' -> LEFT;
      case '>' -> RIGHT;
      default -> throw new IllegalArgumentException("Unknown code: " + code);
    };
  }

  public static String expand(final int depth, final String code) {
    String expanded = code;
    for (int i = 1; i <= depth; i++) {
      DirectionButton currentDirection = DirectionButton.A;
      final StringBuilder directionBuilder = new StringBuilder();
      for (int j = 0; j < expanded.length(); j++) {
        final DirectionButton next = DirectionButton.of(expanded.charAt(j));
        directionBuilder.append(currentDirection.toFirst(next));
        currentDirection = next;
      }
      expanded = directionBuilder.toString();
    }
    return expanded;
  }

  private static final Map<String, Long> MEMO = new HashMap<>();

  public static long expandedLength(final int depth, final String code) {
    if (depth == 0) return code.length();
    final String memoKey = code + depth;
    if (MEMO.containsKey(memoKey)) return MEMO.get(memoKey);
    long result = 0;
    DirectionButton currentDirection = DirectionButton.A;
    for (int j = 0; j < code.length(); j++) {
      final DirectionButton next = DirectionButton.of(code.charAt(j));
      result += expandedLength(depth - 1, currentDirection.toFirst(next));
      currentDirection = next;
    }
    MEMO.put(memoKey, result);
    return result;
  }

  public String toFirst(final DirectionButton d) {
    if (this == d) return "A";
    final List<String> directions = presses.get(this)
                                           .get(d);
    if (directions == null || directions.isEmpty()) {
      throw new UnsupportedOperationException(this + " to " + d + " has not been implemented");
    }
    return directions.getFirst();
  }
}
