package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

object Day09 {
  val regexClean = "<[^>]*>"

  def prepare(str: String): List[Char] =
    str
      .replaceAll("!.", "")
      .replaceAll(regexClean, "")
      .replaceAll("[^\\{\\}]", "")
      .toCharArray
      .toList

  @tailrec
  def score(chars: List[Char], acc: Int, level: Int): Int =
    chars match {
      case Nil         => acc
      case '{' :: tail => score(tail, acc + level, level + 1)
      case '}' :: tail => score(tail, acc, level - 1)
    }

  @tailrec
  def len(string: String, count: Int): Int = {
    val cleaned = string.replaceFirst(regexClean, "")
    if (string == cleaned) count
    else len(cleaned, count + string.length - cleaned.length - 2)
  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day09.txt")
    println(score(prepare(lines.head), 0, 1))
    println(len(lines.head.replaceAll("!.", ""), 0))
  }
}