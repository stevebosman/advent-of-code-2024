import org.scalatest.funsuite.AnyFunSuite

class MainTest extends AnyFunSuite:
  test("Given good_values, part 1 - strict") {
    assert(main(1, "src/test/resources/good_values.txt") === 2)
  }
  test("Given bad_difference_direction, part 1 - strict") {
    assert(main(1, "src/test/resources/bad_difference_direction.txt") === 0)
  }
  test("Given bad_difference_flat, part 1 - strict") {
    assert(main(1, "src/test/resources/bad_difference_flat.txt") === 0)
  }
  test("Given bad_difference_size, part 1 - strict") {
    assert(main(1, "src/test/resources/bad_difference_size.txt") === 0)
  }
  test("Given example, part 1 - strict") {
    assert(main(1, "example1.txt") === 2)
  }

  test("Given good_values, part 2 - with dampener") {
    assert(main(2, "src/test/resources/good_values.txt") === 2)
  }
  test("Given bad_difference_direction, part 2 - with dampener") {
    assert(main(2, "src/test/resources/bad_difference_direction.txt") === 1)
  }
  test("Given bad_difference_flat, part 2 - with dampener") {
    assert(main(2, "src/test/resources/bad_difference_flat.txt") === 1)
  }
  test("Given bad_difference_size, part 2 - with dampener") {
    assert(main(2, "src/test/resources/bad_difference_size.txt") === 1)
  }
  test("Given example, part 2 - with dampener") {
    assert(main(2, "example1.txt") === 4)
  }
