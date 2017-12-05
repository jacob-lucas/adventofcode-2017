package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

object Day05 {
  def readInstructionAt(pos: Int, instructions: List[Int]): Option[Int] = {
    try {
      Some(instructions(pos))
    } catch {
      case _: Throwable => None
    }
  }

  def increaseOffsetAt(pos: Int, instructions: List[Int]): List[Int] = {
    val arr = instructions.toArray
    arr(pos) = arr(pos) + 1
    arr.toList
  }

  @tailrec
  def process(instructions: List[Int], pos: Int, stepsTaken: Int): Int = {
    readInstructionAt(pos, instructions) match {
      case Some(n) => process(increaseOffsetAt(pos, instructions), pos + n, stepsTaken + 1)
      case None    => stepsTaken
    }
  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day05.txt")
    println(process(lines.map(_.toInt), 0, 0))
  }
}
