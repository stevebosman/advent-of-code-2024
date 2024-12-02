import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.math.abs
import scala.util.{Try, Using}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@main
def main(part:Int, filename: String): Int = {
  trait FromStringConverter[T] {
    def convert(string: String): T
  }

  def readLines[T](filename: String, converter: String => T): Try[List[T]] =
    Using(Source.fromFile(filename)) { source => source.getLines().map(l => converter.apply(l)).toList }

  def allWithinTolerance(differences: List[Int]): Boolean = {
    differences.forall(v => (1 to 3).contains(abs(v)))
  }

  def allSameSign(differences: List[Int]): Boolean = {
    differences.sliding(2).forall(d => d.head * d(1) > 0)
  }

  def isStrictlySafe(values: List[Int]): Boolean = {
    val differences = values.sliding(2).map(pair => pair(1) - pair.head).toList
    allWithinTolerance(differences) && allSameSign(differences)
  }

  def subList(values: List[Int], droppedIndex: Int) = {
    val ints = ListBuffer.from(values)
    ints.remove(droppedIndex)
    List.from(ints)
  }

  def isFixable(values: List[Int]): Boolean = {
    values.indices.exists(i => isStrictlySafe(subList(values, i)))
  }

  def isSafe(strict: Boolean, values: List[Int]): Boolean = {
    isStrictlySafe(values) || (!strict && isFixable(values))
  }

  // Use isStrictlySafe here for part1 solution
  // and isPotentiallySafe for part2 solution
  val validity = readLines(filename, line => isSafe(part == 1, line.split("\\D+").map(v => v.toInt).toList)).get

  validity.count(p => p)
}
