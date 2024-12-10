package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trailhead {
  private final int rowCount;
  private final int columnCount;
  private final Map<Integer, List<Location>> heightMap;
  private final List<List<Location>> map;

  public Trailhead(final String filename) throws IOException {
    final var lines = Files.readAllLines(Path.of(filename));
    this.rowCount = lines.size();
    this.columnCount = lines.getFirst().length();

    final Map<Integer, List<Location>> heightMap = new HashMap<>();
    final List<List<Location>> map = new ArrayList<>();
    for (final String ignored: lines) map.add(new ArrayList<>());
    for (int i = 0; i < 10; i++) {
      heightMap.put(i, new ArrayList<>());
      map.add(new ArrayList<>());
    }

    for (int row = 0; row < rowCount; row++) {
      final char[] line = lines.get(row).toCharArray();
      for (int column = 0; column < columnCount; column++) {
        final int height = line[column] - '0';
        final Location location = height==-2 ? null : new Location(height, new Coordinate(row, column));
        map.get(row).add(location);
        if (height >= 0) {
          heightMap.get(height)
                   .add(location);
        }
      }
    }

    this.heightMap = heightMap;
    this.map = map;
  }

  private static void hasNextHeight(final Location location, final int row, final int column, final List<List<Location>> map, final int nextHeight) {
    final Location nextLocation = map.get(row)
                           .get(column);
    if (nextLocation != null && nextHeight == nextLocation.height()) {
      nextLocation.incrementRoutesFromPrevious();
      if (nextHeight == 1) {
        nextLocation.addRoutableFrom(Set.of(location));
      } else {
        nextLocation.addRoutableFrom(location.getRoutableFrom());
      }
    }
  }

  public int countTrails() {
    for (int height = 0; height < 9; height++) {
      final int nextHeight = height + 1;
      heightMap.get(height)
               .forEach(location -> {
                 if (location.coordinate().row()>0) {
                   hasNextHeight(location, location.coordinate().row()-1, location.coordinate().column(), map, nextHeight);
                 }
                 if (location.coordinate().row()<rowCount - 1) {
                   hasNextHeight(location, location.coordinate().row()+1, location.coordinate().column(), map, nextHeight);
                 }
                 if (location.coordinate().column()>0) {
                   hasNextHeight(location, location.coordinate().row(), location.coordinate().column() - 1, map, nextHeight);
                 }
                 if (location.coordinate().column()<columnCount - 1) {
                   hasNextHeight(location, location.coordinate().row(), location.coordinate().column() + 1, map, nextHeight);
                 }
               });
    }

    return heightMap.get(9).stream().map(Location::getRoutableFromCount).reduce(Integer::sum).orElse(0);
  }
}
