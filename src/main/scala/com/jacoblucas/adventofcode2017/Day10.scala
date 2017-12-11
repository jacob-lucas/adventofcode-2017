package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

object Day10 {
  def sublist(input: List[Int], start: Int, length: Int): List[Int] = {
    if (length > input.size) input
    else {
      (for (
        i <- 0 until length
      ) yield {
        input((start + i) % input.size)
      }).toList
    }
  }

  @tailrec
  def merge(sublist: List[Int], original: List[Int], destination: List[Int], start: Int): List[Int] = {
    sublist match {
      case Nil => destination
      case i :: is =>
        val arr = destination.toArray
        arr(start) = i
        merge(is, original, arr.toList, (start + 1) % original.size)
    }
  }

  @tailrec
  def hash(input: List[Int], cur: Int, skip: Int, lengths: List[Int]): (List[Int], Int, Int) = {
    lengths match {
      case Nil => (input, cur, skip)
      case l :: ls =>
        val sub = sublist(input, cur, l)
        val reversed = sub.reverse
        val merged = merge(reversed, input, input, cur)
        hash(merged, (cur + l + skip) % input.size, skip + 1, ls)
    }
  }

  def toBytes(input: String): List[Int] = input.map(_.toInt).toList

  @tailrec
  def densify(sparseHash: List[Int], dest: List[Int]): List[Int] = {
    if (sparseHash.isEmpty) dest
    else densify(sparseHash.takeRight(sparseHash.length - 16), dest :+ sparseHash.take(16).foldLeft(0)(_ ^ _))
  }

  def toHex(input: List[Int]): String = input
    .map(i => {
      val hex = i.toHexString
      if (hex.length() == 1) "0" + hex else hex
    })
    .mkString("")

  def knotHash(input: List[Int], lengths: List[Int], suffix: List[Int], remaining: Int): String = {
    val appendedLengths = lengths ++ suffix

    @tailrec
    def process(input: List[Int], remaining: Int, cur: Int, skip: Int): (List[Int], Int, Int) = {
      if (remaining == 0) {
        (input, cur, skip)
      } else {
        val (res, c, s) = hash(input, cur, skip, appendedLengths)
        process(res, remaining - 1, c, s)
      }
    }

    val sparseHash = process(input, remaining, 0, 0)._1
    val denseHash = densify(sparseHash, List())
    toHex(denseHash)
  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day10.txt")
    val input = (for (i <- 0 until 256) yield i).toList
    val lengths = lines
      .head
      .split(",")
      .toList
      .map(_.toInt)

    // part 1
    val (res, _, _) = hash(input, 0, 0, lengths)
    println(res)
    println(res.head + " * " + res(1) + " = " + (res.head * res(1)))

    // part 2
    println(knotHash(input, toBytes(lines.head), List(17, 31, 73, 47, 23), 64))
  }
}
