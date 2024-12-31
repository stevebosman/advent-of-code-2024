package uk.co.stevebosman.aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Solver2 {
  private static final int TURN_COST = 1000;
  private static final int MOVE_COST = 1;

  private final List<String> lines;
  /** Starting position */
  private final Position start;
  /** Ending position */
  private final Position end;
  /**
   * Stores the minimum cost to reach a specific cell
   * with a specific direction.
   */
  private final Map<DirectionalPosition, Long> distances = new HashMap<>();

  public static void main(final String[] args) throws IOException {
    new Solver2(args[0]).getSeatCount();
  }

  public Solver2(final String filename) throws IOException {
    this.lines = Files.readAllLines(Path.of(filename));

    final int rowCount = lines.size();
    final int columnCount = lines.getFirst()
                           .length();

    this.start = new Position(rowCount - 2, 1);
    this.end = new Position(1, columnCount - 2);

    // Initialise distances for each cell in each direction
    for (int row = 0; row < rowCount; row++) {
      for (int column = 0; column < columnCount; column++) {
        final Position p = new Position(row, column);
        for (final Direction d: Direction.values()) {
          distances.put(new DirectionalPosition(d, p), Long.MAX_VALUE);
        }
      }
    }

    // Initialize the start position, facing East.
    distances.put(new DirectionalPosition(Direction.East, start), 0L);
  }

  private record QueueState(long distance, DirectionalPosition d) {}

  public int getSeatCount() {
    final PriorityQueue<QueueState> pq = new PriorityQueue<>(Comparator.comparingLong(a->a.distance));
    pq.add(new QueueState(0, new DirectionalPosition(Direction.East, start)));

    while(!pq.isEmpty()){
      final QueueState cur = pq.poll();
      final long currentDistance = cur.distance;
      final Direction currentDirection = cur.d.direction();
      final Position currentPosition = cur.d.position();
      if (cur.distance>distances.get(cur.d)) continue;

      // Turn left/right
      for (final Direction nextDirection: currentDirection.leftRightValues()) {
        final long nextDistance = currentDistance + TURN_COST;
        final DirectionalPosition potential = new DirectionalPosition(nextDirection, currentPosition);
        if (nextDistance < distances.get(potential)) {
          distances.put(potential, nextDistance);
          pq.add(new QueueState(nextDistance, potential));
        }
      }

      // Move forward
      final Position newPosition = currentPosition.neighbour(currentDirection);
      // check boundaries
      if (lines.get(newPosition.row()).charAt(newPosition.column()) == '#') continue;
      final long nextDistance = currentDistance + MOVE_COST;
      final DirectionalPosition potential = new DirectionalPosition(currentDirection, newPosition);
      if (nextDistance < distances.get(potential)) {
        distances.put(potential, nextDistance);
        pq.add(new QueueState(nextDistance, potential));
      }
    }

    // Find minimal distance at E
    long best = Long.MAX_VALUE;
    for (final Direction d: Direction.values()) {
      best = Math.min(best, distances.get(new DirectionalPosition(d, end)));
    }

    // Find all the tiles on the best paths
    final Set<DirectionalPosition> onBest = new HashSet<>();
    final Queue<DirectionalPosition> queue = new LinkedList<>();
    // Start from the cheapest end state
    for (final Direction d: Direction.values()) {
      final DirectionalPosition potential = new DirectionalPosition(d, end);
      if (distances.get(potential) == best) {
        onBest.add(potential);
        queue.offer(potential);
      }
    }

    while(!queue.isEmpty()){
      final DirectionalPosition current = queue.poll();
      final Position currentPosition = current.position();
      final Direction currentDirection = current.direction();

      // Try to move straight backwards
      final Position previousPosition = currentPosition.neighbour(currentDirection.opposite());
      if (lines.get(previousPosition.row()).charAt(previousPosition.column()) != '#') {
        // If moving forward from (pr,pc,direction) leads here:
        final DirectionalPosition previous = new DirectionalPosition(currentDirection, previousPosition);
        if (distances.get(previous) + MOVE_COST == distances.get(current)) {
          if (!onBest.contains(previous)) {
            onBest.add(previous);
            queue.offer(previous)
            ;
          }
        }
      }

      // Try to turn
      for (final Direction direction: currentDirection.leftRightValues()) {
        final DirectionalPosition turn = new DirectionalPosition(direction, currentPosition);
        if (distances.get(turn) + TURN_COST == distances.get(current)) {
          if (!onBest.contains(turn)) {
            onBest.add(turn);
            queue.offer(turn);
          }
        }
      }
    }

    final List<Position> bestTiles = onBest.stream().map(DirectionalPosition::position).collect(Collectors.toSet()).stream().sorted().toList();
    System.out.println("bestTiles = " + bestTiles);
    return bestTiles.size();
  }
}
