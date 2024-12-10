import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class MainTest extends AnyFunSuite:
  test("Given example") {
    main("example.txt") should equal (1928, 2858)
    // if example input works then run the full input
    println(main("input.txt"))
  }

