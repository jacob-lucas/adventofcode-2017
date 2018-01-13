package com.jacoblucas.adventofcode2017

object Day14 {
  def hexToBin(hex: Int): String =
    String
      .format("%4s", hex.toBinaryString)
      .replace(' ', '0')

  def hashToBin(hash: String): String =
    hash
      .map(c => hexToBin(Integer.parseInt(c.toString, 16)))
      .mkString

  def grid(key: String, n: Int): List[String] = {
    val knotHashes = for (
      i <- 0 until n
    ) yield {
      Day10.knotHash(key + "-" + i)
    }

    knotHashes
      .map(hashToBin)
      .toList
  }

  def usedCount(grid: List[String]): Int =
    grid
      .map(row => row.filter(_ == '1'))
      .map(_.length)
      .sum

  def main(args: Array[String]): Unit = {
    val key = "amgozmfv"
    val g = grid(key, 128)
    println(usedCount(g))
  }
}
