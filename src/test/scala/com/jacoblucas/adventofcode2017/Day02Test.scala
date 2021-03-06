package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day02Test extends WordSpec with Matchers {
  "Day02" when {
    "calculating checksum for a line" should {
      "return difference between largest and smallest value" in {
        assertThat(Day02.checksum(List(5,1,9,5)), is(8))
      }
    }
    "calculating division for a line" should {
      "return the result of dividing the only two numbers that evenly divide" in {
        assertThat(Day02.division(List(5,9,2,8)), is(4))
      }
    }
  }
}
