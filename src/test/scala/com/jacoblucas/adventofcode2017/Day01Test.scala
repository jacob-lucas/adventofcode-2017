package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Day01Test extends WordSpec with Matchers {
  "Day01" when {
    "calculating next index" should {
      "cur < len - 1" in {
        assertThat(Day01.next(0, 6), is(1))
      }
      "cur == len - 1" in {
        assertThat(Day01.next(5, 6), is(0))
      }
    }
    "calculating the sum of all digits that match the next" should {
      "1122 -> 3" in {
        assertThat(Day01.sum(Array(1,1,2,2), 0, Day01.next, 0), is(3))
      }
      "1111 -> 4" in {
        assertThat(Day01.sum(Array(1,1,1,1), 0, Day01.next, 0), is(4))
      }
      "1234 -> 0" in {
        assertThat(Day01.sum(Array(1,2,3,4), 0, Day01.next, 0), is(0))
      }
      "91212129 -> 0" in {
        assertThat(Day01.sum(Array(9,1,2,1,2,1,2,9), 0, Day01.next, 0), is(9))
      }
    }

    "calculating half way around index" should {
      "cur < len / 2" in {
        assertThat(Day01.halfWayAround(0, 6), is(3))
        assertThat(Day01.halfWayAround(1, 6), is(4))
        assertThat(Day01.halfWayAround(2, 6), is(5))
      }
      "cur >= len / 2" in {
        assertThat(Day01.halfWayAround(3, 6), is(0))
        assertThat(Day01.halfWayAround(4, 6), is(1))
        assertThat(Day01.halfWayAround(5, 6), is(2))
      }
    }
    "calculating the sum of all digits that match the digit half way around" should {
      "1212 -> 6" in {
        assertThat(Day01.sum(Array(1,2,1,2), 0, Day01.halfWayAround, 0), is(6))
      }
      "1221 -> 0" in {
        assertThat(Day01.sum(Array(1,2,2,1), 0, Day01.halfWayAround, 0), is(0))
      }
      "123425 -> 4" in {
        assertThat(Day01.sum(Array(1,2,3,4,2,5), 0, Day01.halfWayAround, 0), is(4))
      }
      "123123 -> 12" in {
        assertThat(Day01.sum(Array(1,2,3,1,2,3), 0, Day01.halfWayAround, 0), is(12))
      }
      "12131415 -> 4" in {
        assertThat(Day01.sum(Array(1,2,1,3,1,4,1,5), 0, Day01.halfWayAround, 0), is(4))
      }
    }
  }
}
