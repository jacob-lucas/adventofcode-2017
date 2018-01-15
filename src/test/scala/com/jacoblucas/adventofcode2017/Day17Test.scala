package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day17Test extends WordSpec with Matchers {
  "Day17" when {
    "executing spinlock" should {
      "calculate insert position correctly" in {
        assertThat(Day17.insertAt(1, 0, 3), is(1))
        assertThat(Day17.insertAt(2, 1, 3), is(1))
        assertThat(Day17.insertAt(3, 1, 3), is(2))
        assertThat(Day17.insertAt(4, 2, 3), is(2))
        assertThat(Day17.insertAt(5, 2, 3), is(1))
        assertThat(Day17.insertAt(6, 1, 3), is(5))
        assertThat(Day17.insertAt(7, 5, 3), is(2))
        assertThat(Day17.insertAt(8, 2, 3), is(6))
        assertThat(Day17.insertAt(9, 6, 3), is(1))
      }
      "iterates correctly" in {
        assertThat(Day17.iterate(List(0), 0, 3), is((List(0,1), 1)))
        assertThat(Day17.iterate(List(0,1), 1, 3), is((List(0,2,1), 1)))
        assertThat(Day17.iterate(List(0,2,1), 1, 3), is((List(0,2,3,1), 2)))
        assertThat(Day17.iterate(List(0,2,3,1), 2, 3), is((List(0,2,4,3,1), 2)))
        assertThat(Day17.iterate(List(0,2,4,3,1), 2, 3), is((List(0,5,2,4,3,1), 1)))
        assertThat(Day17.iterate(List(0,5,2,4,3,1), 1, 3), is((List(0,5,2,4,3,6,1), 5)))
        assertThat(Day17.iterate(List(0,5,2,4,3,6,1), 5, 3), is((List(0,5,7,2,4,3,6,1), 2)))
        assertThat(Day17.iterate(List(0,5,7,2,4,3,6,1), 2, 3), is((List(0,5,7,2,4,3,8,6,1), 6)))
        assertThat(Day17.iterate(List(0,5,7,2,4,3,8,6,1), 6, 3), is((List(0,9,5,7,2,4,3,8,6,1), 1)))
      }
      "example spinlock" in {
        assertThat(Day17.spinlock(9, 3), is(List(0,9,5,7,2,4,3,8,6,1)))
      }
      "after 2017 executions" in {
        val res = Day17.spinlock(2017, 3)
        val idx = res.indexOf(2017)
        assertThat(Day17.spinlock(2017, 3).slice(idx - 3, idx + 4), is(List(1512,1134,151,2017,638,1513,851)))
      }
    }
  }
}
