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
        assertThat(Day10.hash(List(0,1,2,3,4), 0, 0, List(3,4,1,5)), is(List(3,4,2,1,0)))
      }
    }
  }
}
