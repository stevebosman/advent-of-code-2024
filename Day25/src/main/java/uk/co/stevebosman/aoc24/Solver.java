package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
  public static final int LOCK_HEIGHT = 7;
  private final Set<int[]> locks = new HashSet<>();
  private final Set<int[]> keys = new HashSet<>();

  public Solver(final String filename) throws IOException {
    final List<String> lines = Files.readAllLines(Path.of(filename));
    for (int i = 0; i < lines.size(); i += LOCK_HEIGHT + 1) {
      if ("#####".equals(lines.get(i))) {
        locks.add(createLockOrKey(lines.subList(i, i + LOCK_HEIGHT)));
      } else {
        keys.add(createLockOrKey(lines.subList(i, i + LOCK_HEIGHT)));
      }
    }
  }

  private int[] createLockOrKey(final List<String> lines) {
    final int[] counts = {0, 0, 0, 0, 0};
    for (final String line : lines) {
      for (int i = 0; i < line.length(); i++) {
        if (line.charAt(i) == '#') counts[i]++;
      }
    }
    return counts;
  }

  public long solve() {
    long result = 0L;
    for(final var key: keys) {
      for(final var lock: locks) {
        boolean fit = true;
        for (int i = 0; i < lock.length; i++) {
          if (key[i] + lock[i]> LOCK_HEIGHT) {
            fit = false;
            break;
          }
        }
        if (fit) result++;
      }
    }
    return result;
  }
}
