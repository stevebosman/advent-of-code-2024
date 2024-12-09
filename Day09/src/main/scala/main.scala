import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Try, Using}

@main
def main(filename: String): Long =
  def readLines(filename: String): Try[List[String]] =
    Using(Source.fromFile(filename)) { source => source.getLines().toList }

  var runningLocation = 0

  def allocateSpace(amount: Int, index: Int): Option[DiskSpace] = {
    val space = if (index % 2 == 0)
      Some(DiskSpace(runningLocation, index / 2, amount))
    else if (amount != 0)
      Some(DiskSpace(runningLocation, -1, amount))
    else
      None
    runningLocation += amount
    space
  }

  @tailrec
  def remapSpaces(spaces: List[DiskSpace], result: List[DiskSpace] = List()): List[DiskSpace] = {
    if (spaces.isEmpty) return result
    val current = spaces.head
    val (next, rem) = if (current.value == -1)
      var remaining = spaces.tail
      var start = current.start
      var freespace = current.size
      var next: List[DiskSpace] = List()
      while (freespace > 0 && remaining.nonEmpty) {
        val use = remaining.last
        remaining = remaining.dropRight(1)
        if (use.value != -1) {
          if (use.size <= freespace) {
            next = next :+ DiskSpace(start, use.value, use.size)
            freespace -= use.size
            start += use.size
          } else {
            next = next :+ DiskSpace(start, use.value, freespace)
            remaining = remaining :+ DiskSpace(use.start, use.value, use.size - freespace)
            freespace = 0
          }
        }
      }
      (next, remaining)
    else
      (List(current), spaces.tail)
    remapSpaces(rem, result ++ next)
  }

  val line = readLines(filename).get(0)

  val spaces = line.zipWithIndex.flatMap { (a, i) => allocateSpace(a.toInt - 48, i) }.toList

  remapSpaces(spaces).map(d => d.value * ((d.start * d.size) + d.size * (d.size - 1) / 2)).sum

case class DiskSpace(start: Int, value: Long, size: Int)
