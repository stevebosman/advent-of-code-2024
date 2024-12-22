package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  public static void main(final String[] args) throws IOException {
    final String filename = args[0];
    System.out.println(filename);
    System.out.println("=".repeat(filename.length()));
    try (final var lines = Files.lines(Path.of(filename))) {
      final var producers = lines.map(l -> new SecretProducer(Long.parseLong(l), 2000))
                                 .toList();
      final var part1 = producers.stream()
                                 .map(p -> p.secrets.get(1999))
                                 .reduce(0L, Long::sum);
      System.out.println("part1 = " + part1);

      final var uniqueSequences = producers.stream()
                                           .flatMap(p -> p.priceDifferenceGroups.keySet()
                                                                                .stream())
                                           .collect(Collectors.toSet());
      final var part2 = uniqueSequences.stream()
                                       .map(s -> bananasForSequence(s, producers))
                                       .max(Long::compareTo);
      System.out.println("part2 = " + part2);
      System.out.println("-".repeat(40));
    }
  }

  private static long bananasForSequence(final String sequence, final List<SecretProducer> producers) {
    return producers.parallelStream()
                    .map(p -> bananasForSequence(sequence, p))
                    .reduce(0L, Long::sum);
  }

  private static long bananasForSequence(final String sequence, final SecretProducer producer) {
    final Integer index = producer.priceDifferenceGroups.get(sequence);
    if (index == null) return 0L;
    return producer.prices.get(index);
  }
}