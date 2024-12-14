case class Position(x: Int, y:Int) {
  def quadrant(midX: Int, midY: Int): Int = {
    if (x < midX && y < midY)
      1
    else if (x<midX && y> midY)
      2
    else if (x>midX && y< midY)
      3
    else if (x>midX && y> midY)
      4
    else
      0
  }
  override def toString: String =
    s"($x, $y)"
}
