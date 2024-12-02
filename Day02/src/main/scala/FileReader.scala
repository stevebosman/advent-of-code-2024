import scala.io.Source
import scala.util.{Try, Using}

trait FromStringConverter[T] {
  def convert(string: String): T
}

object FileReader {
  def readLines[T](filename: String, converter: String => T): Try[List[T]] =
    Using(Source.fromFile(filename)) { source => source.getLines().map(l => converter.apply(l)).toList }
}
