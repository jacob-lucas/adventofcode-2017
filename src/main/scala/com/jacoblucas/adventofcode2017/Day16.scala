package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

case class DancingProgram(name: String)

trait DanceMove {
  def dance(programs: List[DancingProgram]): List[DancingProgram]
}
case class Spin(n: Int) extends DanceMove {
  override def dance(programs: List[DancingProgram]): List[DancingProgram] =
    programs.takeRight(n) ++ programs.take(programs.length - n)
}
case class Exchange(a: Int, b: Int) extends DanceMove {
  override def dance(programs: List[DancingProgram]): List[DancingProgram] = {
    val pA = programs(a)
    val pB = programs(b)
    programs
      .updated(a, pB)
      .updated(b, pA)
  }
}
case class Partner(a: String, b: String) extends DanceMove {
  override def dance(programs: List[DancingProgram]): List[DancingProgram] = {
    val aPos = programs.indexWhere(_.name == a)
    val bPos = programs.indexWhere(_.name == b)
    Exchange(aPos, bPos).dance(programs)
  }
}

object Day16 {
  def parse(str: String): Option[DanceMove] = {
    try {
      str.toList match {
        case 's' :: cs =>
          Some(Spin(Integer.parseInt(cs.mkString)))
        case 'x' :: cs =>
          val nums = cs.mkString.split("/")
          Some(Exchange(Integer.parseInt(nums(0)), Integer.parseInt(nums(1))))
        case 'p' :: cs =>
          val names = cs.mkString.split("/")
          Some(Partner(names(0), names(1)))
        case _   =>
          throw new RuntimeException("Unable to parse: " + str)
      }
    } catch {
      case _: Throwable => None
    }
  }

  @tailrec
  def dance(danceMoves: List[DanceMove], programs: List[DancingProgram]): List[DancingProgram] = {
    danceMoves match {
      case Nil => programs
      case m :: ms => dance(ms, m.dance(programs))
    }
  }

  def main(args: Array[String]): Unit = {
    val programs = (for (
      i <- 0 until 16
    ) yield DancingProgram("" + ('a' + i).toChar)).toList

    val lines = Util.read("/Day16.txt")
    val danceMoves = lines
      .head
      .split(",")
      .flatMap(parse)
      .toList

    val afterDancing = dance(danceMoves, programs)
    println(afterDancing.map(_.name).mkString)
  }
}
