package com.jacoblucas.adventofcode2017

object Day02 {
  def checksum(input: List[Int]): Int = input.max - input.min

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
  }
}
