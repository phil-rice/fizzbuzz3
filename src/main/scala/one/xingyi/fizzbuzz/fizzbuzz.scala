package one.xingyi.fizzbuzz

trait RemainderZero[T] extends Function2 [T, Int, Boolean]

object RemainderZero {
  implicit val remainerZeroForInt: RemainderZero[Int] = (value, divisor) => value % divisor == 0
  implicit val remainerZeroForDouble: RemainderZero[Double] = (value, divisor) => value % divisor == 0
  implicit val remainerZeroForBigInt: RemainderZero[BigInt] = (value, divisor) => value % divisor == 0
}


object Fizzbuzz {
  private def defaultString[T]: T => String = _.toString

  def ifDividesBy[T](divisor: Int, s: String)(implicit rem: RemainderZero[T]): PartialFunction[T, String] = {
    case inp if rem(inp, divisor) => s
  }

  implicit class FixbuzzOps[T: RemainderZero](t: T) {
    def fizzbuzz =
      ifDividesBy(15, "fizzbuzz") orElse ifDividesBy(3, "fizz") orElse ifDividesBy(5, "buzz") applyOrElse(t, defaultString)
  }

}

object FizzbuzzApp extends App {

  import Fizzbuzz._

  (1 to 50) foreach { i => println(i.fizzbuzz) }
}

