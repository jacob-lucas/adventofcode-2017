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

  def regionCount(grid: List[String]): Int = {
    val visited = Array.ofDim[Boolean](grid.length, grid.head.length)

    def adjacentTo(pos: (Int, Int), min: Int, max: Int): List[(Int, Int)] = {
      val x = pos._1
      val y = pos._2

      List(
        (x - 1, y),
        (x + 1, y),
        (x, y - 1),
        (x, y + 1)
      ).filter(p => p._1 >= min && p._2 >= min && p._1 <= max && p._2 <= max)
    }

    def isUsed(pos: (Int, Int)) = grid(pos._1).charAt(pos._2) == '1'

    def dfs(g: List[String], v: (Int, Int), detecting: Boolean): Boolean = {
      val foundNewRegion = isUsed(v) && !visited(v._1)(v._2) && detecting
      visited(v._1)(v._2) = true

      for (
        e <- adjacentTo(v, 0, g.head.length - 1) if isUsed(e)
      ) {
        if (!visited(e._1)(e._2) && isUsed(v)) {
          dfs(g, e, detecting = false)
        }
      }
      foundNewRegion
    }

    val found = for (
      x <- grid.indices;
      y <- 0 until grid.head.length if !visited(x)(y)
    ) yield {
      dfs(grid, (x, y), detecting = true)
    }

    found.count(_ == true)
  }

  def main(args: Array[String]): Unit = {
    val key = "amgozmfv"
    val g = grid(key, 128)

    // part 1
    println(usedCount(g))

    // part 2
    println(regionCount(g))
  }
}
