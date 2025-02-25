package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodeSequencer {
  private final String code;

  public CodeSequencer(final String code) {
    this.code = code;
  }

  public static long lengthAtDepth(final String filename, final int depth) throws IOException {
    try (final var codes = Files.lines(Path.of(filename))) {
      return codes.map(CodeSequencer::new)
                  .map(seq -> seq.instructionLength(depth) * seq.numeric())
                  .reduce(0L, Long::sum);
    }
  }

  public String instructions(final int depth) {
    if (depth < 0) throw new IllegalArgumentException("depth should be greater than 1");
    final StringBuilder expandedBuilder = new StringBuilder();
    CodeButton current = CodeButton.A;
    for (int i = 0; i < code.length(); i++) {
      final CodeButton next = CodeButton.of(code.charAt(i));
      expandedBuilder.append(current.toFirst(next));
      current = next;
    }
    String expanded = expandedBuilder.toString();
//    System.out.println(code + ": depth 0 = " + expanded);
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
    System.out.println(code + " to depth " + depth + ": " + numeric() + " * " + expanded.length());
    return expanded;
  }

  public long instructionLength(final int depth) {
    if (depth < 0) throw new IllegalArgumentException("depth should be greater than 1");
    long length = 0;
    CodeButton current = CodeButton.A;
    for (int i = 0; i < code.length(); i++) {
      final CodeButton next = CodeButton.of(code.charAt(i));
      length += DirectionButton.expandedLength(depth, current.toFirst(next));
      current = next;
    }
    System.out.println(code + " to depth " + depth + ": " + numeric() + " * " + length);
    return length;
  }

  public int numeric() {
    return Integer.parseInt(code.replaceAll("^0*([0-9]*)A$", "$1"));
  }
}

