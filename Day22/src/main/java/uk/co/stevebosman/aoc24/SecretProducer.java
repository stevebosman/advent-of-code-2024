package uk.co.stevebosman.aoc24;

public class SecretProducer {
  public long sequenceAt(long currentSecret, final int target) {
    for (int i = 0; i < target; i++) {
      final long step1 = prune(mix(currentSecret << 6, currentSecret));
      final long step2 = prune(mix(step1>>5, step1));
      currentSecret = prune(mix(step2<<11, step2));
    }
    return  currentSecret;
  }

  public long mix(final long value, final long currentSecret) {
    return value ^ currentSecret;
  }

  public long prune(final long value) {
    return value % 16777216;
  }
}
