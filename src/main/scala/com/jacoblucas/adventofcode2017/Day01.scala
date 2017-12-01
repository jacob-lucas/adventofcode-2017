package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

object Day01 {
  def next(cur: Int, len: Int): Int = (cur + 1) % len

  def halfWayAround(cur: Int, len: Int): Int = (cur + len / 2) % len

  @tailrec
  def sum(digits: Array[Int], cur: Int, nextFunc: (Int, Int) => Int, res: Int): Int = {
    if (cur == digits.length) res
    else {
      val currentValue = digits(cur)
      val nextValue = digits(nextFunc(cur, digits.length))
      sum(digits, cur + 1, nextFunc, if (currentValue == nextValue) res + currentValue else res)
    }
  }

  def main(args: Array[String] ): Unit = {
    val lines = Util.read("/Day01.txt")
    val digits = lines.head.toCharArray.flatMap(c => {
      try {
        Some(c.asDigit)
      } catch {
        case _: Throwable => None
      }
    })

    println(sum(digits, 0, Day01.next, 0))
    println(sum(digits, 0, Day01.halfWayAround, 0))
  }
}
