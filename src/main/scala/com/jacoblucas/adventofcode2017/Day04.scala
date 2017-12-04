package com.jacoblucas.adventofcode2017

object Day04 {
  def isValid(passphrase: String): Boolean = {
    val words = passphrase.split(" ")
    words.length == words.distinct.length
  }

  def isValidV2(passphrase: String): Boolean = {
    val words = passphrase.split(" ")
    words.length == words.map(_.sorted).distinct.length
  }

  def main(args: Array[String]): Unit = {
    val passphrases = Util.read("/Day04.txt")
    println(passphrases count isValid)
    println(passphrases count isValidV2)
  }
}
