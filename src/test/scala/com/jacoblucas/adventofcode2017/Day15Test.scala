package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day15Test extends WordSpec with Matchers {
  "Day15" when {
    "generating a next value" should {
      "generate correctly" in {
        val genA = Generator("A", 16807, 65, Day15.always)
        val genB = Generator("B", 48271, 8921, Day15.always)

        assertThat(genA.generate(), is(1092455))
        assertThat(genA.generate(), is(1181022009))
        assertThat(genA.generate(), is(245556042))
        assertThat(genA.generate(), is(1744312007))
        assertThat(genA.generate(), is(1352636452))

        assertThat(genB.generate(), is(430625591))
        assertThat(genB.generate(), is(1233683848))
        assertThat(genB.generate(), is(1431495498))
        assertThat(genB.generate(), is(137874439))
        assertThat(genB.generate(), is(285222916))
      }
      "generate with a value filter correctly" in {
        val genA = Generator("A", 16807, 65, Day15.divBy4)
        val genB = Generator("B", 48271, 8921, Day15.divBy8)

        assertThat(genA.generate(), is(1352636452))
        assertThat(genA.generate(), is(1992081072))
        assertThat(genA.generate(), is(530830436))
        assertThat(genA.generate(), is(1980017072))
        assertThat(genA.generate(), is(740335192))

        assertThat(genB.generate(), is(1233683848))
        assertThat(genB.generate(), is(862516352))
        assertThat(genB.generate(), is(1159784568))
        assertThat(genB.generate(), is(1616057672))
        assertThat(genB.generate(), is(412269392))
      }
    }
    "detecting matched pairs" should {
      "count a pair when the lowest 16 bits of the binary string matches" in {
        val genA = Generator("A", 16807, 65, Day15.always)
        val genB = Generator("B", 48271, 8921, Day15.always)
        val matches = Day15.findMatchingPairs(genA, genB, 5)
        assertThat(matches.next(), is((245556042, 1431495498)))
      }
    }
  }
}
