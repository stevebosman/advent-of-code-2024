import org.scalatest.funsuite.AnyFunSuite

class MainTest extends AnyFunSuite:
  test("Given example") {
    assert(main("example.txt") === 1928)
  }

  test("Given input") {
    assert(main("input.txt") === 1928)
  }
