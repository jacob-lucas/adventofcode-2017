package com.jacoblucas.adventofcode2017

object Day02 {
  def checksum(input: List[Int]): Int = input.max - input.min

  def division(input: List[Int]): Int = {
    val pairs = for (
      i <- input.indices;
      j <- input.indices if i != j
    ) yield {
      val max = Math.max(input(i), input(j))
      val min = Math.min(input(i), input(j))

      (max, min)
    }

    pairs
      .distinct
      .filter(p => p._1 % p._2 == 0)
      .map(p => p._1 / p._2)
      .head
  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day02.txt")
    val rows = lines.map(l => {
      l.split("\t").flatMap(c => {
        try {
          Some(c.toInt)
        } catch {
          case _: Throwable => None
        }
      })
    })

    println(rows.map(r => checksum(r.toList)).sum)
    println(rows.map(r => division(r.toList)).sum)
  }
}
