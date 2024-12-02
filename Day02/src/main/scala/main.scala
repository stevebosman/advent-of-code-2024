import scala.math.abs

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@main
def main(filename: String): Unit = {
  val differences = FileReader.readLines(filename, s => s.split("\\D+").map(v => v.toInt).sliding(2).map(a => a(1) - a(0)).toList).get
  println(differences.count(line => line.forall(v => (1 to 3).contains(abs(v))) && line.sliding(2).forall(d => d.head * d(1) > 0)))
  ()
}
