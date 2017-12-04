package com.jacoblucas.adventofcode2017

object Day04 {
  def isValid(passphrase: String): Boolean = {
    val words = passphrase.split(" ")
    words
      .map(w => words.count(_ == w))
      .count(_ == 1) == words.length
  }

  def main(args: Array[String]): Unit = {
    val passphrases = Util.read("/Day04.txt")
    println(passphrases count isValid)
  }
}
