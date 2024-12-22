package uk.co.stevebosman.aoc24;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SecretProducerTest {

  @ParameterizedTest
  @CsvSource({
          "123,1,15887950",
          "123,10,5908254",
          "1,2000,8685429",
          "10,2000,4700978",
          "100,2000,15273692",
          "2024,2000,8667524",
  })
  void sequenceAt(final long initial, final int count, final long expected) {
    assertEquals(expected, new SecretProducer().sequenceAt(initial,count));
  }
}