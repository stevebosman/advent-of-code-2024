package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Connections {
  final Map<String, Set<String>> connections;
  private final Set<Set<String>> memo = new HashSet<>();

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

  private static Set<String> concat(final Set<String> currentCluster, final String c) {
    return Stream.concat(currentCluster.stream(), Stream.of(c))
                 .collect(Collectors.toSet());
  }

  private static boolean isTeeComputer(final Map.Entry<String, Set<String>> e) {
    return e.getKey()
            .startsWith("t");
  }

  private static boolean hasMoreThanOneConnection(final Map.Entry<String, Set<String>> e) {
    return e.getValue()
            .size() > 1;
  }

  public int getTripletsCountWithT() {
    return getTripletsWithT().size();
  }

  private Set<Set<String>> getStartingClusters() {
    final Set<Set<String>> triplets = new HashSet<>();
    connections.entrySet()
               .stream()
               .filter(Connections::hasMoreThanOneConnection)
               .forEach(e -> triplets.addAll(addTriplets(e)));
    return triplets;
  }

  private Set<Set<String>> getTripletsWithT() {
    final Set<Set<String>> triplets = new HashSet<>();
    connections.entrySet()
               .stream()
               .filter(e ->
                               isTeeComputer(e)
                                       && hasMoreThanOneConnection(e))
               .forEach(e -> triplets.addAll(addTriplets(e)));
    return triplets;
  }

  private Set<Set<String>> addTriplets(final Map.Entry<String, Set<String>> e) {
    final Set<Set<String>> triplets = new HashSet<>();
    for (final var v1 : e.getValue()) {
      for (final var v2 : e.getValue()) {
        if (!v1.equals(v2)) {
          if (connections.get(v1)
                         .contains(v2)) {
            triplets.add(Set.of(e.getKey(), v1, v2));
          }
        }
      }
    }
    return triplets;
  }

  public String password() {
    final Set<Set<String>> triplets = getStartingClusters();
    final Set<Set<String>> clusters = new HashSet<>();
    triplets.forEach(triplet -> clusters.addAll(getLargestCluster(triplet)));
    final int max = clusters.stream()
                            .map(Set::size)
                            .max(Integer::compareTo)
                            .orElse(0);
    final List<String> maximal = clusters.stream()
                                         .filter(c -> c.size() == max)
                                         .map(c -> c.stream()
                                                    .sorted()
                                                    .collect(Collectors.joining(",")))
                                         .toList();
    return maximal.getFirst();
  }

  private Set<Set<String>> getLargestCluster(final Set<String> currentCluster) {
    if (memo.contains(currentCluster)) return new HashSet<>();
    memo.add(currentCluster);
    final Set<String> potentialConnections = new HashSet<>();
    boolean first = true;
    for (final String t : currentCluster) {
      if (first) {
        first = false;
        potentialConnections.addAll(connections.get(t));
      } else {
        potentialConnections.retainAll(connections.get(t));
      }
    }
    if (potentialConnections.isEmpty()) return Set.of(currentCluster);
    final Set<Set<String>> result = new HashSet<>();
    potentialConnections.stream()
                        .filter(c -> connections.get(c)
                                                .containsAll(currentCluster))
                        .forEach(c -> result.addAll(getLargestCluster(concat(currentCluster, c))));
    return result.isEmpty()
           ? Set.of(currentCluster)
           : result;
  }
}
