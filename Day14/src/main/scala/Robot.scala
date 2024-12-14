

class Robot(var p: Position, val v: Velocity) {
  def move(limitX: Int, limitY: Int): Unit = {
    p = Position((p.x + v.dx + limitX) % limitX, (p.y + v.dy + limitY) % limitY)
  }

  override def toString: String =
    s"$p @ $v"
}

object Robot {
  def of(info: String): Robot = {
    val pattern = "p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)".r
    val iterator = pattern.findAllIn(info).subgroups.toArray

    Robot(Position(iterator(0).toInt, iterator(1).toInt), Velocity(iterator(2).toInt, iterator(3).toInt))
  }
}