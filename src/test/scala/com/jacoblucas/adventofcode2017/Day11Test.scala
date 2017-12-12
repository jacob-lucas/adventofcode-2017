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
        assertThat(Day11.stepsAway(List("ne", "ne", "ne"))._1, is(3))
        assertThat(Day11.stepsAway(List("ne", "ne", "sw", "sw"))._1, is(0))
        assertThat(Day11.stepsAway(List("se", "nw", "se", "nw"))._1, is(0))
        assertThat(Day11.stepsAway(List("se", "ne", "se", "ne"))._1, is(4))
        assertThat(Day11.stepsAway(List("ne", "ne", "s", "s"))._1, is(2))
        assertThat(Day11.stepsAway(List("s", "ne", "n", "nw", "sw", "s", "se"))._1, is(1))
        assertThat(Day11.stepsAway(List("se", "sw", "se", "sw", "sw"))._1, is(3))
      }
    }
  }
}
