package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Day09Test extends WordSpec with Matchers {
  "Day09" when {
    "scoring groups" should {
      "return a score" in {
        assertThat(Day09.score(Day09.prepare("{}"), 0, 1), is(1))
        assertThat(Day09.score(Day09.prepare("{{{}}}"), 0, 1), is(6))
        assertThat(Day09.score(Day09.prepare("{{},{}}"), 0, 1), is(5))
        assertThat(Day09.score(Day09.prepare("{{{},{},{{}}}}"), 0, 1), is(16))
        assertThat(Day09.score(Day09.prepare("{<a>,<a>,<a>,<a>}"), 0, 1), is(1))
        assertThat(Day09.score(Day09.prepare("{{<ab>},{<ab>},{<ab>},{<ab>}}"), 0, 1), is(9))
        assertThat(Day09.score(Day09.prepare("{{<!!>},{<!!>},{<!!>},{<!!>}}"), 0, 1), is(9))
        assertThat(Day09.score(Day09.prepare("{{<a!>},{<a!>},{<a!>},{<ab>}}"), 0, 1), is(3))
      }
    }
  }
}
