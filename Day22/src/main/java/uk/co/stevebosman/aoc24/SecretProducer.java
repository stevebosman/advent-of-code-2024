package uk.co.stevebosman.aoc24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecretProducer {
  public final List<Long> secrets;
  public final List<Integer> prices;
  public final List<Integer> priceDifferences;
  public final Map<String, Integer> priceDifferenceGroups;

  public SecretProducer(final long currentSecret, final int target) {
    this.secrets = sequenceTo(currentSecret, target);
    this.prices = this.secrets.stream()
                              .map(s -> (int) (s % 10))
                              .toList();
    final List<Integer> differences = new ArrayList<>(this.prices.size() - 1);
    for (int i = 1; i < this.prices.size(); i++) {
      differences.add(this.prices.get(i) - this.prices.get(i - 1));
    }
    this.priceDifferences = differences.stream()
                                       .toList();
    this.priceDifferenceGroups = priceDifferenceGroups(priceDifferences);
  }

  private static List<Long> sequenceTo(long currentSecret, final int target) {
    final List<Long> sequence = new ArrayList<>();
    for (int i = 0; i < target; i++) {
      final long step1 = prune(mix(currentSecret << 6, currentSecret));
      final long step2 = prune(mix(step1 >> 5, step1));
      currentSecret = prune(mix(step2 << 11, step2));
      sequence.add(currentSecret);
    }
    return sequence.stream()
                   .toList();
  }

  public static long mix(final long value, final long currentSecret) {
    return value ^ currentSecret;
  }

  public static long prune(final long value) {
    return value % 16777216;
  }

  private Map<String, Integer> priceDifferenceGroups(final List<Integer> priceDifferences) {
    final Map<String, Integer> result = new HashMap<>();
    for (int i = 0; i < priceDifferences.size() - 3; i++) {
      final String sub = priceDifferences.subList(i, i + 4)
                                         .toString();
      result.putIfAbsent(sub, i + 4);
    }
    return result;
  }
}
