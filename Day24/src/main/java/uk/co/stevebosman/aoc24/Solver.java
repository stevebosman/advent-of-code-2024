package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {
  private static final Pattern PATTERN = Pattern.compile(
          "(?<wire1>[a-z0-9]+) (?<type>AND|OR|XOR) (?<wire2>[a-z0-9]+) -> (?<output>[a-z0-9]+)");
  private final Map<String, Boolean> states = new HashMap<>();
  private final Set<Transition> stateTransitions = new HashSet<>();
  private final Set<String> unknownZed = new HashSet<>();

  public Solver(final String filename) throws IOException {
    final List<String> lines = Files.readAllLines(Path.of(filename));
    boolean initialStates = true;
    for (final String line : lines) {
      if (line.isBlank()) {
        initialStates = false;
      } else if (initialStates) {
        states.put(line.substring(0, 3), "1".equals(line.substring(5)));
      } else {
        final Matcher matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
          final String type = matcher.group("type");
          final String wire1 = matcher.group("wire1");
          final String wire2 = matcher.group("wire2");
          final String output = matcher.group("output");
          if (!states.containsKey(wire1) && wire1.startsWith("z")) unknownZed.add(wire1);
          if (!states.containsKey(wire2) && wire2.startsWith("z")) unknownZed.add(wire2);
          if (!states.containsKey(output) && output.startsWith("z")) unknownZed.add(output);
          if ("AND".equals(type)) {
            stateTransitions.add(new And(wire1, wire2, output));
          } else if ("OR".equals(type)) {
            stateTransitions.add(new Or(wire1, wire2, output));
          } else if ("XOR".equals(type)) {
            stateTransitions.add(new Xor(wire1, wire2, output));
          }
        }
      }
    }
  }

  public long solve() {
    while (!unknownZed.isEmpty()) {
      for (final var transition : stateTransitions) {
        transition.combine(states, unknownZed);
      }
    }
    return states.entrySet()
                 .stream()
                 .filter(e -> e.getKey()
                               .startsWith("z") && e.getValue())
                 .map(e -> 1L << Integer.parseInt(e.getKey()
                                                   .substring(1)))
                 .reduce(0L, Long::sum);
  }

  private interface Transition {
    void combine(final Map<String, Boolean> states, final Set<String> unknownZed);
  }

  private record Or(String wire1, String wire2, String outputWire) implements Transition {
    public void combine(final Map<String, Boolean> states, final Set<String> unknownZed) {
      if (states.containsKey(wire1) && states.containsKey(wire2)) {
        states.put(outputWire, states.get(wire1) | states.get(wire2));
        if (outputWire.startsWith("z")) unknownZed.remove(outputWire);
      }
    }
  }

  private record And(String wire1, String wire2, String outputWire) implements Transition {
    public void combine(final Map<String, Boolean> states, final Set<String> unknownZed) {
      if (states.containsKey(wire1) && states.containsKey(wire2)) {
        states.put(outputWire, states.get(wire1) & states.get(wire2));
        if (outputWire.startsWith("z")) unknownZed.remove(outputWire);
      }
    }
  }

  private record Xor(String wire1, String wire2, String outputWire) implements Transition {
    public void combine(final Map<String, Boolean> states, final Set<String> unknownZed) {
      if (states.containsKey(wire1) && states.containsKey(wire2)) {
        states.put(outputWire, states.get(wire1) ^ states.get(wire2));
        if (outputWire.startsWith("z")) unknownZed.remove(outputWire);
      }
    }
  }
}
