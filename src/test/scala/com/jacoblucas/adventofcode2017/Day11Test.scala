package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day11Test extends WordSpec with Matchers {
  "Day11" when {
    "calculating steps away" should {
      "calculate correctly" in {
        assertThat(Day11.stepsAway(List("ne", "ne", "ne")), is(3))
        assertThat(Day11.stepsAway(List("ne", "ne", "sw", "sw")), is(0))
        assertThat(Day11.stepsAway(List("se", "nw", "se", "nw")), is(0))
        assertThat(Day11.stepsAway(List("se", "ne", "se", "ne")), is(4))
        assertThat(Day11.stepsAway(List("ne", "ne", "s", "s")), is(2))
        assertThat(Day11.stepsAway(List("s", "ne", "n", "nw", "sw", "s", "se")), is(1))
        assertThat(Day11.stepsAway(List("se", "sw", "se", "sw", "sw")), is(3))
      }
    }
  }
}
