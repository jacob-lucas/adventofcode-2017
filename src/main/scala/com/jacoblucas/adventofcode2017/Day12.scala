package com.jacoblucas.adventofcode2017

case class Prog(id: Int, pipes: List[Int])

object Day12 {
  def pathExists(fromId: Int, toId: Int, programsById: Map[Int, Prog], seen: List[Int]): Boolean = {
    if (fromId == toId) true
    else if (seen.contains(fromId)) false
    else {
      val p = programsById(fromId)
      if (p.pipes.contains(toId)) true
      else {
        val pipes = p.pipes
        pipes
          .map(pi => pathExists(pi, toId, programsById, seen :+ fromId))
          .foldLeft(false)(_ || _)
      }
    }
  }

  def canCommunicateWith(id: Int, programs: Map[Int, Prog]): List[Int] =
    (for (
      progId <- programs.keys if pathExists(progId, id, programs, List())
    ) yield progId).toList

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day12.txt")
    val programsById = lines
      .map(l => {
        val parts = l.split(" ")
        val id = parts(0).toInt
        val pipes = parts
          .takeRight(parts.length - 2)
          .map(_.replace(",",""))
          .map(_.toInt)
          .toList
        Prog(id, pipes)
      })
      .map(p => p.id -> p)
      .toMap

    val communicatesWithZero = canCommunicateWith(0, programsById)
      .map(id => programsById(id))
    println(communicatesWithZero.size)
  }
}
