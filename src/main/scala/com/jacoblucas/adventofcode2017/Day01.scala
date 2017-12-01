package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

object Day01 {
  @tailrec
  def sum(digits: Array[Int], cur: Int, next: Int, res: Int): Int = {
    val currentValue = digits(cur)
    val len = digits.length

    if (next == len) {
      if (digits(0) == digits(len - 1)) res + currentValue else res
    } else {
      val nextValue = digits(next)
      sum(digits, cur + 1, next + 1, if(currentValue == nextValue) res + currentValue else res)
    }
  }

  def main (args: Array[String] ): Unit = {
    val lines = Util.read("/Day01.txt")
    val digits = lines.head.toCharArray.flatMap(c => {
      try {
        Some(c.asDigit)
      } catch {
        case _: Throwable => None
      }
    })

    println(sum(digits, 0, 1, 0))
  }
}
