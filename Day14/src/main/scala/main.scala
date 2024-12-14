import scala.io.Source
import scala.util.Using

@main
def main(iterations:Int, width:Int, height: Int, filename: String): Unit =
  val robots = Using(Source.fromFile(filename)) {source => source.getLines().map(s=>Robot.of(s)).toList }.get
  (1 to iterations).foreach(i=> {
    robots.foreach(r => r.move(width, height))
  })
  val quadrants = robots.map(r=>r.p.quadrant(width/2, height/2))
  val safetyFactor = quadrants.filter(p=> p!=0).groupBy(identity).map(e => e._2.size).product

  println(s"${safetyFactor}")

