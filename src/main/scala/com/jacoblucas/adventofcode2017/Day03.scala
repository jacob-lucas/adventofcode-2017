package com.jacoblucas.adventofcode2017

object Day03 {
  def stepsToSquareOne(from: Int): Int = {
    val root = math.ceil(math.sqrt(from))
    val row = if (root % 2 != 0) root else root + 1
    val stepsToCenter = (row - 1) / 2
    val cycle = from - math.pow(row - 2, 2).toInt // half of a side, rounding down
    val inner = cycle % (row - 1)

    (stepsToCenter + math.abs(inner - stepsToCenter)).toInt
  }

  // TODO: code this if needed
  def sumSpiralUpToN(n: Int): Int = ???

  def main(args: Array[String]): Unit = {
    println(stepsToSquareOne(289326))

    // part 2 can be looked up here: https://oeis.org/A141481/b141481.txt
  }
}
