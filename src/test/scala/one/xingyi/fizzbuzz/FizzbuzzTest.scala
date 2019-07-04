package one.xingyi.fizzbuzz

import org.scalatest.{FlatSpec, Matchers}

import scala.reflect.ClassTag

import Fizzbuzz._

abstract class AbstractFizzbuzzTest[T: RemainderZero : ClassTag] extends FlatSpec with Matchers {


  behavior of "FizzBuzz for " + implicitly[ClassTag[T]].runtimeClass.getSimpleName

  implicit def intToT(i: Int): T

  it should "implement fizz buzz rules" in {
    1.fizzbuzz shouldBe "1"
    2.fizzbuzz shouldBe "2"
    3.fizzbuzz shouldBe "fizz"
    4.fizzbuzz shouldBe "4"
    5.fizzbuzz shouldBe "buzz"
    8.fizzbuzz shouldBe "8"
    9.fizzbuzz shouldBe "fizz"
    10.fizzbuzz shouldBe "buzz"
    11.fizzbuzz shouldBe "11"
    15.fizzbuzz shouldBe "fizzbuzz"
    30.fizzbuzz shouldBe "fizzbuzz"

  }
}

class IntFizzbuzzSpec extends AbstractFizzbuzzTest[Int] {
  override implicit def intToT(i: Int): Int = i
}

class DoubleFizzbuzzSpec extends AbstractFizzbuzzTest[Double] {
  override implicit def intToT(i: Int): Double = i.toDouble
}

class BigIntFizzbuzzSpec extends AbstractFizzbuzzTest[BigInt] {
  override implicit def intToT(i: Int): BigInt = BigInt(i)
}
