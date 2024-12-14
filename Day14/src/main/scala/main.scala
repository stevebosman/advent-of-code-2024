import scala.Array.ofDim
import scala.io.Source
import scala.util.Using
import scala.util.boundary
import scala.util.boundary.break

def visualiseMap(width: Int, height: Int, robots: List[Robot]): String = {
  val myMatrix = ofDim[Int](height, width)
  robots.foreach(r => myMatrix(r.p.y)(r.p.x) += 1)
  val map = myMatrix.map(
      l => l.map(i => if (i == 0) "." else "*"
    ).mkString).mkString("\n")
  map
}

@main
def main(iterations:Int, width:Int, height: Int, filename: String, debug: Boolean = false): Unit =
  val robots = Using(Source.fromFile(filename)) {source => source.getLines().map(s=>Robot.of(s)).toList }.get
  (1 to iterations).foreach(i=> {
    robots.foreach(r => r.move(width, height))

    if (debug) {
      val map: String = visualiseMap(width, height, robots)
      println(s"${map}\n")
    }
  })
  val quadrants = robots.map(r=>r.p.quadrant(width/2, height/2))
  val safetyFactor = quadrants.filter(p=> p!=0).groupBy(identity).map(e => e._2.size).product

  println(s"${safetyFactor}")

  // tree hunt
  var quit = false;
  var i = iterations
  boundary {
    while (i < 100_000_000) {
      if (i%1000==0) println(i)
      i += 1
      robots.foreach(r => r.move(width, height))
      val map: String = visualiseMap(width, height, robots)
      if (map.contains("**********")) {
        println(map)
        println(i)
        break()
      }
    }
  }