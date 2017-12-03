package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day03Test extends WordSpec with Matchers {
  "Day03" when {
    "calculating steps to square 1" should {
      "1 -> 0" in {
        assertThat(Day03.stepsToSquareOne(1), is(0))
      }
      "12 -> 3" in {
        assertThat(Day03.stepsToSquareOne(12), is(3))
      }
      "23 -> 2" in {
        assertThat(Day03.stepsToSquareOne(23), is(2))
      }
      "1024 -> 31" in {
        assertThat(Day03.stepsToSquareOne(1024), is(31))
      }
    }
  }
}