package uk.co.stevebosman.aoc24;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlutoniumSequencer {
  private Map<String, Long> sequence;

  public PlutoniumSequencer(final String startingSequence) {
    Stream<String> stream = Arrays.stream(startingSequence.split(" "));
    this.sequence = mapCounts(stream);
  }

  private static Map<String, Long> mapCounts(Stream<String> stream) {
    return stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  public void iterate(int blinks) {
    for (int i = 0; i < blinks; i++) {
      blink();
      System.out.println(i + ": " + sequence);
    }
  }

  public void blink() {
    this.sequence = sequence.entrySet()
            .stream().flatMap(this::blinkValue)
            .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)));
  }

  public Stream<Map.Entry<String, Long>> blinkValue(final Map.Entry<String, Long> entry) {
    final String value = entry.getKey();
    if (value.equals("0")) return Stream.of(Map.entry("1", entry.getValue()));
    if (value.length() % 2 == 0) {
      final String left = value.substring(0, value.length() / 2);
      final String right = value.substring(value.length() / 2);
      if (left.equals(right)) {
        return Stream.of(Map.entry(left, 2 * entry.getValue()));
      }
      return Stream.of(Map.entry(left, entry.getValue()), Map.entry(String.valueOf(Long.parseLong(right)), entry.getValue()));
    }
    return Stream.of(Map.entry(String.valueOf(Long.parseLong(value) * 2024L), entry.getValue()));
  }

  public long size() {
    return sequence.values().stream().reduce(Long::sum).orElse(0L);
  }
}
