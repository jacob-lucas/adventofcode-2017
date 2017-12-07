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
        })

      findBottomProgramName(updated)
    }
  }

  def weight(name: String, tower: Map[String, Program]): (Int, List[Int]) = {
    val program = tower(name)
    if (program.disc.isEmpty) (program.weight, List())
    else (program.weight, program.disc.map(n => totalWeight(weight(n, tower))))
  }

  def totalWeight(weight: (Int, List[Int])): Int = weight._1 + weight._2.sum

  def isBalanced(program: Program, tower: Map[String, Program]): Boolean = {
    // balances if empty or (all weights on the disc are the same and all programs above it are balanced)
    val disc = program.disc
    val isEmpty = disc.isEmpty
    val allWeightsEqual = disc
      .map(n => totalWeight(weight(n, tower)))
      .distinct
      .size == 1

    isEmpty || (allWeightsEqual && disc.map(n => isBalanced(tower(n), tower)).reduce(_ && _))
  }

  @tailrec
  def detectBalancePoint(towerAt: Program, tower: Map[String, Program]): Option[Program] = {
    val disc = towerAt.disc

    if (disc.isEmpty) None
    else if (isBalanced(towerAt, tower)) {
      // towerAt is where we are balanced, find the parent
      Some(tower.filter(p => p._2.disc.contains(towerAt.name)).head._2)
    }
    else {
      // dig into where the weight is off
      val unbalancedProgram = disc
        .map(n => (n, totalWeight(weight(n, tower))))
        .groupBy(nw => nw._2)
        .filter(p => p._2.size == 1)
        .head
        ._2
        .head
        ._1

      detectBalancePoint(tower(unbalancedProgram), tower)
    }

  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day07.txt")
    val tower = parseTower(lines)
    val bottomProgram = findBottomProgramName(tower)
    println("bottom program = " + bottomProgram)
    val balancePoint = detectBalancePoint(tower(bottomProgram), tower).get
    println("balance point = " + balancePoint)

    balancePoint.disc.foreach(n => {
      val w = weight(n, tower)
      println(n + "\t= " + w + " total = " + totalWeight(w))
    })
  }
}
