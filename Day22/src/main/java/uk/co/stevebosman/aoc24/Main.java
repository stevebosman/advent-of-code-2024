package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
  public static void main(final String[] args) throws IOException {
    final var producer = new SecretProducer();
    try(final var lines = Files.lines(Path.of(args[0]))) {
      final var result = lines.map(l -> producer.sequenceAt(Long.parseLong(l), 2000)).reduce(0L, Long::sum);
      System.out.println("result = " + result);
    }
  }
}