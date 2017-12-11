package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day10Test extends WordSpec with Matchers {
  "Day10" when {
    "creating a sublist" should {
      "return elements within the list bounds" in {
        assertThat(Day10.sublist(List(0,1,2,3,4), 1, 2), is(List(1,2)))
      }
      "wrap around the list if needed" in {
        assertThat(Day10.sublist(List(0,1,2,3,4), 3, 3), is(List(3,4,0)))
      }
      "handles invalid lengths (larger than the size of the list)" in {
        assertThat(Day10.sublist(List(0,1,2,3,4), 2, 6), is(List(0,1,2,3,4)))
      }
    }
    "merging a sublist into a list" should {
      "merge at the correct positions" in {
        assertThat(Day10.merge(List(2,1,0), List(0,1,2,3,4), List(0,1,2,3,4), 0), is(List(2,1,0,3,4)))
      }
      "merge with equal size lists" in {
        assertThat(Day10.merge(List(2,1,0,4,3), List(0,1,2,3,4), List(0,1,2,3,4), 2), is(List(4,3,2,1,0)))
      }
      "merge with wrapping around the end of the list" in {
        assertThat(Day10.merge(List(1,2,4,3), List(2,1,0,3,4), List(2,1,0,3,4), 3), is(List(4,3,0,1,2)))
      }
    }
    "calculating a hash" should {
      "return a correct result" in {
        assertThat(Day10.hash(List(0,1,2,3,4), 0, 0, List(3,4,1,5))._1, is(List(3,4,2,1,0)))
      }
    }
    "converting a string to bytes" should {
      "turn each character in the string to its ASCII code" in {
        assertThat(Day10.toBytes("1,2,3"), is(List(49, 44, 50, 44, 51)))
      }
    }
    "calculating a knot hash" should {
      "converting a list of ints to a hex string" in {
        assertThat(Day10.toHex(List(64, 7, 255)), is("4007ff"))
      }
      "return a correct result" in {
        val input = (for (i <- 0 until 256) yield i).toList
        assertThat(Day10.knotHash(input, Day10.toBytes(""), List(17, 31, 73, 47, 23), 64), is("a2582a3a0e66e6e86e3812dcb672a272"))
        assertThat(Day10.knotHash(input, Day10.toBytes("AoC 2017"), List(17, 31, 73, 47, 23), 64), is("33efeb34ea91902bb2f59c9920caa6cd"))
        assertThat(Day10.knotHash(input, Day10.toBytes("1,2,3"), List(17, 31, 73, 47, 23), 64), is("3efbe78a8d82f29979031a4aa0b16a9d"))
        assertThat(Day10.knotHash(input, Day10.toBytes("1,2,4"), List(17, 31, 73, 47, 23), 64), is("63960835bcdc130f0b66d7ff4f6a5a8e"))
      }
    }
  }
}
