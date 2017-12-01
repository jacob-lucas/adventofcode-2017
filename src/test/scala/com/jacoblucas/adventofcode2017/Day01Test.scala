package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Day01Test extends WordSpec with Matchers {
  "Day01" when {
    "calculating the sum of all digits that match the next" should {
      "1122 -> 3" in {
        assertThat(Day01.sum(Array(1,1,2,2), 0, 1, 0), is(3))
      }
      "1111 -> 4" in {
        assertThat(Day01.sum(Array(1,1,1,1), 0, 1, 0), is(4))
      }
      "1234 -> 0" in {
        assertThat(Day01.sum(Array(1,2,3,4), 0, 1, 0), is(0))
      }
      "91212129 -> 0" in {
        assertThat(Day01.sum(Array(9,1,2,1,2,1,2,9), 0, 1, 0), is(9))
      }
    }
  }
}
