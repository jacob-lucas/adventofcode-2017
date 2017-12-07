package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

case class Program(name: String, weight: Int, disc: List[String])

object Day07 {
  def parse(raw: String): Program = {
    val parts = raw.split(" ")
    val name = parts(0)
    val weight = parts(1).substring(1, parts(1).length - 1).toInt
    if (raw.contains("->")) {
      val children = raw
        .split("->")(1)
        .mkString
        .replace(" ", "")
        .split(",")
        .toList
      Program(name, weight, children)
    } else {
      Program(name, weight, List())
    }
  }

  def parseTower(raw: List[String]): Map[String, Program] = {
    @tailrec
    def buildMap(raw: List[String], programMap: Map[String, Program]): Map[String, Program] = {
      raw match {
        case Nil => programMap
        case p :: ps =>
          val program = parse(p)
          buildMap(ps, programMap updated(program.name, program))
      }
    }

    buildMap(raw, Map())
  }

  @tailrec
  def findBottomProgramName(programsByName: Map[String, Program]): String = {
    val leafNodes = programsByName.filter(p => p._2.disc.isEmpty)
    if (leafNodes.size == 1)
      // only one node left, this is the bottom program
      leafNodes.head._1
    else {
      // filter the leaf nodes out of the map
      val updated = programsByName
        .filter(p => p._2.disc.nonEmpty)
        .map(p => {
          val overlap = p._2.disc.intersect(leafNodes.keys.toList)
          if (overlap.isEmpty) p
          else
            p._1 -> Program(
                p._1,
                p._2.weight,
                p._2.disc.filterNot(d => overlap contains d))
        }
        )

      findBottomProgramName(updated)
    }
  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day07.txt")
    val tower = parseTower(lines)
    println(findBottomProgramName(tower))
  }
}
