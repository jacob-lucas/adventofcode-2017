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
  def hash(input: List[Int], cur: Int, skip: Int, lengths: List[Int]): List[Int] = {
    lengths match {
      case Nil => input
      case l :: ls =>
        val sub = sublist(input, cur, l)
        val reversed = sub.reverse
        val merged = merge(reversed, input, input, cur)
        hash(merged, (cur + l + skip) % input.size, skip + 1, ls)
    }
  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day10.txt")
    val input = (for (i <- 0 until 256) yield i).toList
    val lengths = lines
      .head
      .split(",")
      .toList
      .map(_.toInt)
    val res = hash(input, 0, 0, lengths)
    println(res)
    println(res.head + " * " + res(1) + " = " + (res.head * res(1)))
  }
}
