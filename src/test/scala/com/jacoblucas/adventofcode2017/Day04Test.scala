package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day04Test extends WordSpec with Matchers {
  "Day04" when {
    "validate passphrases" should {
      "aa bb cc dd ee" in {
        assertThat(Day04.isValid("aa bb cc dd ee"), is(true))
      }
      "aa bb cc dd aa" in {
        assertThat(Day04.isValid("aa bb cc dd aa"), is(false))
      }
      "aa bb cc dd aaa" in {
        assertThat(Day04.isValid("aa bb cc dd aaa"), is(true))
      }
    }
  }
}