package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day14Test extends WordSpec with Matchers {
  "Day14" when {
    "convert hex to binary" should {
      "convert high bit first" in {
        assertThat(Day14.hexToBin(0x0), is("0000"))
        assertThat(Day14.hexToBin(0x1), is("0001"))
        assertThat(Day14.hexToBin(0x2), is("0010"))
        assertThat(Day14.hexToBin(0x6), is("0110"))
        assertThat(Day14.hexToBin(0xA), is("1010"))
        assertThat(Day14.hexToBin(0xE), is("1110"))
        assertThat(Day14.hexToBin(0xF), is("1111"))
      }
    }
    "converting an output knot hash to binary" should {
      "calculate correctly" in {
        assertThat(Day14.hashToBin("a0c2017"), is("1010000011000010000000010111"))
      }
    }
    "building a grid" should {
      "create each row in the grid from a knot hash" in {
        val key = "flqrgnkx"
        val grid = Day14.grid(key, 1)
        val row = grid.head
        assertThat(row.length, is(128))
        val knotHash = Day10.knotHash(key + "-" + 0)
        assertThat(row, is(Day14.hashToBin(knotHash)))
      }
    }
    "counting used squares" should {
      "return the number of 1s" in {
        val grid = List(
          "1010",
          "0000",
          "1100",
          "0001"
        )
        assertThat(Day14.usedCount(grid), is(5))
      }
      "example" in {
        val grid = Day14.grid("flqrgnkx", 8).map(row => row.take(8))
        assertThat(grid.length, is(8))
        assertThat(grid, is(
          List(
            "11010100",
            "01010101",
            "00001010",
            "10101101",
            "01101000",
            "11001001",
            "01000100",
            "11010110"
          )
        ))
        assertThat(Day14.usedCount(grid), is(29))
      }
    }
    "counting regions" should {
      "count correctly" in {
        val grid = List(
          "11010100",
          "01010101",
          "00001010",
          "10101101",
          "01101000",
          "11001001",
          "01000100",
          "11010110"
        )
        assertThat(Day14.regionCount(grid), is(12))
      }
    }
  }
}
