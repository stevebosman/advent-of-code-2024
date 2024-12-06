import Direction.North

import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Try, Using}

@main
def main(filename: String): (Int, Int) =
  def readLines(filename: String): Try[List[String]] =
    Using(Source.fromFile(filename)) { source => source.getLines().toList }

  val map = readLines(filename).get
  val start = map.zipWithIndex.filter { (s, i) => s.contains("^") }.map((s, i) => Position(s.indexOf("^"), i)).head
  val uniquePositions = walk(map, DirectedPosition(start, North)).get.map(dp => dp.position)
  val obstacles = uniquePositions.filter { p => walk(map, DirectedPosition(start, North), p).isEmpty }
  val result = (uniquePositions.size, obstacles.size)
  println(result)
  result

@tailrec
def walk(map: List[String], position: DirectedPosition, obstacle: Position = Position(-2, -2), positions: Set[DirectedPosition] = Set()): Option[Set[DirectedPosition]] = {
  if (positions.contains(position)) return None
  val newPositions = positions + position
  val forward = Position(position.position.x + position.direction.dx, position.position.y + position.direction.dy)
  if (forward.y < 0 || forward.y >= map.length || forward.x < 0 || forward.x >= map.head.length) return Some(newPositions)
  val newPosition = if (forward == obstacle || map(forward.y)(forward.x) == '#') {
    DirectedPosition(position.position, position.direction.turnRight)
  } else {
    DirectedPosition(forward, position.direction)
  }
  walk(map, newPosition, obstacle, newPositions)
}

case class Position(x: Int, y: Int)

case class DirectedPosition(position: Position, direction: Direction)

enum Direction(val dx: Int, val dy: Int):
  def turnRight: Direction = Direction.fromOrdinal((this.ordinal + 1) % 4)

  case North extends Direction(0, -1)
  case East extends Direction(1, 0)
  case South extends Direction(0, 1)
  case West extends Direction(-1, 0)
