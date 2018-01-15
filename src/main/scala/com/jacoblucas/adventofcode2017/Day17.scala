package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

object Day17 {
  def insertAt(len: Int, pos: Int, steps: Int): Int =
    (if (pos + steps < len) pos + steps else (pos + steps) % len) + 1

  def spinlock(n: Int, steps: Int): List[Int] = {
    @tailrec
    def helper(buffer: List[Int], pos: Int, steps: Int, remaining: Int): List[Int] = {
      if (remaining == 0) buffer
      else {
        val (buf, p) = iterate(buffer, pos, steps)
        helper(buf, p, steps, remaining - 1)
      }
    }

    helper(List(0), 0, steps, n)
  }

  def iterate(buffer: List[Int], pos: Int, steps: Int): (List[Int], Int) = {
    val len = buffer.length
    val insertPos = insertAt(len, steps, pos)
    val buf =
      if (insertPos == len) buffer :+ len
      else if (insertPos == 0) len +: buffer
      else buffer.take(insertPos) ++ List(len) ++ buffer.takeRight(len - insertPos)

    (buf, insertPos)
  }

  def main(args: Array[String]): Unit = {
    // part 1
    val res = Day17.spinlock(2017, 377)
    val idx = res.indexOf(2017)
    println(res(idx + 1))
  }
}
