package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

object Day11 {
  /*
     Treats the hex grid as 3D with (x,y,z) coordinates

          - x-axis             + y-axis
               ^                 ^
                \              /
                 \ (-1,1,0) n /
      (-1,0,1) nw +----------+ ne (0,1,-1)
                 /            \
+ z-axis <--   -+   (0,0,0)    +- ----> - z-axis
                 \            /
      (0,-1,1) sw +----------+ se (1,0,-1)
                 / (1,-1,0) s \
                /              \
               v                v
           - y-axis            + x-axis
   */
  @tailrec
  def walk(path: List[String], start: (Int, Int, Int), cur: (Int, Int, Int), maxStepsAway: Int): ((Int, Int, Int), Int) = {
    path match {
      case Nil =>
        println("Walked from " + start + " to " + cur)
        (cur, maxStepsAway)
      case p :: ps =>
        val next = p match {
          case "nw" => (cur._1 - 1, cur._2, cur._3 + 1)
          case "se" => (cur._1 + 1, cur._2, cur._3 - 1)

          case "ne" => (cur._1, cur._2 + 1, cur._3 - 1)
          case "sw" => (cur._1, cur._2 - 1, cur._3 + 1)

          case "n"  => (cur._1 - 1, cur._2 + 1, cur._3)
          case "s"  => (cur._1 + 1, cur._2 - 1, cur._3)
        }
        println("At " + cur + ", turning " + p)
        val stepsAway = shortestDistanceToOrigin(next)
        walk(ps, start, next, if (stepsAway > maxStepsAway) stepsAway else maxStepsAway)
    }
  }

  def shortestDistanceToOrigin(from: (Int, Int, Int)): Int =
    math.max(
      math.max(math.abs(from._1), math.abs(from._2)),
      math.max(math.abs(from._2), math.abs(from._3))
    )

  def stepsAway(path: List[String]): (Int, Int) = {
    val start = (0,0,0)
    val (end, maxStepsAway) = walk(path, start, start, 0)
    (shortestDistanceToOrigin(end), maxStepsAway)
  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day11.txt")
    val path = lines
      .head
      .split(",")
      .toList
    println(stepsAway(path))
  }
}
