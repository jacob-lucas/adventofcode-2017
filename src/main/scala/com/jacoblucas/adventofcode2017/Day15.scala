package com.jacoblucas.adventofcode2017

case class Generator(id: String, factor: Int, seed: Int, valueFilter: (Int) => Boolean) {
  val iterator: Iterator[Int] =
    Iterator.iterate(seed)(v => ((v.toLong * factor) % 2147483647).toInt)
      .drop(1)
      .filter(valueFilter)

  def generate(): Int = iterator.next()
}

object Day15 {
  def findMatchingPairs(genA: Generator, genB: Generator, n: Int): Iterator[(Int, Int)] = {
    val pairedIterator = genA.iterator.zip(genB.iterator)
    pairedIterator.take(n).filter(p => p._1.toBinaryString.takeRight(16) == p._2.toBinaryString.takeRight(16))
  }

  def always(n: Int): Boolean = true
  def divBy4(n: Int): Boolean = n % 4 == 0
  def divBy8(n: Int): Boolean = n % 8 == 0

  def main(args: Array[String]): Unit = {
    // part 1
    val genA = Generator("A", 16807, 116, always)
    val genB = Generator("B", 48271, 299, always)
    val matches1 = findMatchingPairs(genA, genB, 40000000)
    println(matches1.size)

    // part 2
    val genC = Generator("A", 16807, 116, divBy4)
    val genD = Generator("B", 48271, 299, divBy8)
    val matches2 = findMatchingPairs(genC, genD, 5000000)
    println(matches2.size)
  }
}
