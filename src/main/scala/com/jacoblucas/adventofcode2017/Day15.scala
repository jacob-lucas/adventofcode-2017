package com.jacoblucas.adventofcode2017

case class Generator(id: String, factor: Int, seed: Int) {
  val iterator: Iterator[Int] = Iterator.iterate(seed)(v => ((v.toLong * factor) % 2147483647).toInt).drop(1)
  def generate(): Int = iterator.next()
}

object Day15 {
  def findMatchingPairs(genA: Generator, genB: Generator, n: Int): Iterator[(Int, Int)] = {
    val pairedIterator = genA.iterator.zip(genB.iterator)
    pairedIterator.take(n).filter(p => p._1.toBinaryString.takeRight(16) == p._2.toBinaryString.takeRight(16))
  }

  def main(args: Array[String]): Unit = {
    val genA = Generator("A", 16807, 116)
    val genB = Generator("B", 48271, 299)

    // part 1
    val matches = findMatchingPairs(genA, genB, 40000000)
    println(matches.size)
  }
}
