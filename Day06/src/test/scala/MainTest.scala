import org.scalatest.funsuite.AnyFunSuite

class MainTest extends AnyFunSuite:
  test("Given straight ahead, part 1") {
    assert(main("src/test/resources/straight.txt") === (2, 0))
  }
  test("Given example, part 1 - strict") {
    assert(main("src/test/resources/example1.txt") === (41, 6))
  }
