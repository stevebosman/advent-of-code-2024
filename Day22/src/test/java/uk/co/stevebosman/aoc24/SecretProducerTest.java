package uk.co.stevebosman.aoc24;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecretProducerTest {

  @ParameterizedTest
  @CsvSource({
          "123,10,5908254",
          "1,2000,8685429",
          "10,2000,4700978",
          "100,2000,15273692",
          "2024,2000,8667524",
  })
  void secretAt(final long initial, final int count, final long expected) {
    final SecretProducer instance = new SecretProducer(initial, count);
    assertEquals(expected, instance.secrets.get(count - 1));
  }
}