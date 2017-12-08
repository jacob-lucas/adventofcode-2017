package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

trait Operation {
  def execute(value: Int): Int
}
case class Increment(by: Int) extends Operation {
  override def execute(value: Int): Int = value + by
}
case class Decrement(by: Int) extends Operation {
  override def execute(value: Int): Int = value - by
}

trait Condition {
  val register: Register
  def evaluate(): Boolean
}
case class GreaterThan(register: Register, n: Int) extends Condition {
  override def evaluate(): Boolean = register.value > n
  override def toString: String = register.value + " > " + n
}
case class GreaterThanOrEqualTo(register: Register, n: Int) extends Condition {
  override def evaluate(): Boolean = register.value >= n
  override def toString: String = register.value + " >= " + n
}
case class EqualTo(register: Register, n: Int) extends Condition {
  override def evaluate(): Boolean = register.value == n
  override def toString: String = register.value + " == " + n
}
case class NotEqualTo(register: Register, n: Int) extends Condition {
  override def evaluate(): Boolean = register.value != n
  override def toString: String = register.value + " != " + n
}
case class LessThanOrEqualTo(register: Register, n: Int) extends Condition {
  override def evaluate(): Boolean = register.value <= n
  override def toString: String = register.value + " <= " + n
}
case class LessThan(register: Register, n: Int) extends Condition {
  override def evaluate(): Boolean = register.value < n
  override def toString: String = register.value + " < " + n
}

case class Register(id: String, value: Int) {
  def apply(operation: Operation, condition: Condition): Register =
    if (condition.evaluate()) {
      println(condition + " is true, executing " + operation + " on " + this)
      Register(id, operation.execute(value))
    } else {
      println(condition + " is false, ignoring " + operation + " on " + this)
      this
    }
}

object Day08 {
  @tailrec
  def process(instructions: List[String], registerMap: Map[String, Register], maxSoFar: Int): (Map[String, Register], Int) = {
    instructions match {
      case Nil => (registerMap, maxSoFar)
      case i :: is =>
        val parts = i.split(" ")
        val id = parts(0)
        val register = registerMap(id)
        val operation = parts(1) match {
          case "inc" => Increment(parts(2).toInt)
          case "dec" => Decrement(parts(2).toInt)
        }
        val registerForCondition = registerMap(parts(4))
        val condition = parts(5) match {
          case ">=" => GreaterThanOrEqualTo(registerForCondition, parts(6).toInt)
          case ">"  => GreaterThan(registerForCondition, parts(6).toInt)
          case "==" => EqualTo(registerForCondition, parts(6).toInt)
          case "!=" => NotEqualTo(registerForCondition, parts(6).toInt)
          case "<=" => LessThanOrEqualTo(registerForCondition, parts(6).toInt)
          case "<"  => LessThan(registerForCondition, parts(6).toInt)
        }
        val updatedMap = registerMap.updated(id, register.apply(operation, condition))
        val max = updatedMap.map(_._2.value).max
        process(is, updatedMap, if (max > maxSoFar) max else maxSoFar)
    }
  }

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day08.txt")

    val registers = lines
      .map(l => {
        val id = l.split(" ")(0)
        id -> Register(id, 0)
      })
      .toMap

    val processed = process(lines, registers, 0)
    println(processed)
    println(processed._1.map(_._2.value).max)
    println(processed._2)
  }
}

