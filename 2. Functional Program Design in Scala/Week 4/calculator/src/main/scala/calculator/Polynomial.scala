package calculator

object Polynomial extends PolynomialInterface {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    Signal {
      val bValue = b()
      (bValue * bValue) - 4 * (a() * c())
    }
  }

  // (-b ± √Δ) / (2a)
  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    val negativeB = Signal(-1 * b())
    val twoTimesA = Signal(2 * a())
    val sqrtDelta = Signal(math.sqrt(delta()))
    Signal {
      if (delta() < 0) Set()
      else Set((negativeB() + sqrtDelta()) / twoTimesA(), (negativeB() - sqrtDelta()) / twoTimesA())
    }
  }
}
