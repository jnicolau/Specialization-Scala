package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PascalSuite extends FunSuite {
  import Main.pascal

//  test("pascal: 22 did not equal 232") {
//    assert(pascal(22,232) === pascal(232, 22))
//  }
  test("pascal: col=2,row=3") {
    assert(pascal(2,3) === 3)
  }

  test("pascal: col=0,row=2") {
    assert(pascal(0,2) === 1)
  }

  test("pascal: col=1,row=2") {
    assert(pascal(1,2) === 2)
  }

  test("pascal: col=1,row=3") {
    assert(pascal(1,3) === 3)
  }

  test("pascal: exception on negative args") {
    intercept[IllegalArgumentException] {
      pascal(-1,-2)
      pascal(-5,-3)
      pascal(-9,-10)
    }
  }

  test("pascal: exception if col > row") {
    intercept[IllegalArgumentException] {
      pascal(5,2)
      pascal(1,0)
    }
  }
}
