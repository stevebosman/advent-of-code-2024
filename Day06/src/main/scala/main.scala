import Direction.North

import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Try, Using}

@main
def main(filename: String): Int =
  def readLines(filename: String): Try[List[String]] =
    Using(Source.fromFile(filename)) { source => source.getLines().toList }

  val map = readLines(filename).get
  val start = map.zipWithIndex.filter {(s,i) => s.contains("^")}.map((s,i) => (s.indexOf("^"),i))(0)
  val result = walk(map, start)
  println(result)
  result

@tailrec
def walk(map: List[String], position: (Int, Int), direction: Direction = North, positions: Set[(Int, Int)] = Set()): Int = {
  val newPositions = positions + position
  val forward = (position._1 + direction.dx, position._2 + direction.dy)
  if (forward._2 < 0 || forward._1 < 0 || forward._2 >= map.length || forward._1 >= map.head.length) return positions.size + 1
  val (newPosition, newDirection) = if (map(forward._2)(forward._1) == '#') {
    (position, direction.turnRight)
  } else {
    (forward, direction)
  }
  walk(map, newPosition, newDirection, newPositions)
}

enum Direction(val dx: Int, val dy: Int):
  def turnRight: Direction = Direction.fromOrdinal((this.ordinal + 1) % 4)

  case North extends Direction(0, -1)
  case East extends Direction(1, 0)
  case South extends Direction(0, 1)
  case West extends Direction(-1, 0)
