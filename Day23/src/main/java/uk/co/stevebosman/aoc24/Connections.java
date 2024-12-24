package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Connections {
  final Map<String, Set<String>> connections;

  public Connections(final String filename) throws IOException {
    final Map<String, Set<String>> connections = new HashMap<>();
    try (final var lines = Files.lines(Path.of(filename))) {
      lines.forEach(s -> {
        final String c1 = s.substring(0, 2);
        final String c2 = s.substring(3);
        final var c1Set = connections.computeIfAbsent(c1, k -> new HashSet<>());
        c1Set.add(c2);
        final var c2Set = connections.computeIfAbsent(c2, k -> new HashSet<>());
        c2Set.add(c1);
      });
    }
    this.connections = connections;
  }

  public int tSetCount() {
    final Set<Set<String>> triplets = new HashSet<>();
    connections.entrySet()
               .stream()
               .filter(e ->
                               e.getKey()
                                .startsWith("t")
                                       && e.getValue()
                                           .size() > 1)
               .forEach(e -> {
                  for (final var v1: e.getValue()) {
                    for (final var v2: e.getValue()) {
                      if (!v1.equals(v2)) {
                        if (connections.get(v1).contains(v2)) {
                          triplets.add(Set.of(e.getKey(), v1, v2));
                        }
                      }
                    }
                  }
               });
    return triplets.size();
  }
}
