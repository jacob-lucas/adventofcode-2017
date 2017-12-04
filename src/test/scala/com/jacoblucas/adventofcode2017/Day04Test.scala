package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day04Test extends WordSpec with Matchers {
  "Day04" when {
    "validate v1 passphrases" should {
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

    "validate v2 passphrases" should {
      "abcde fghij" in {
        assertThat(Day04.isValidV2("abcde fghij"), is(true))
      }
      "abcde xyz ecdab" in {
        assertThat(Day04.isValidV2("abcde xyz ecdab"), is(false))
      }
      "a ab abc abd abf abj" in {
        assertThat(Day04.isValidV2("a ab abc abd abf abj"), is(true))
      }
      "iiii oiii ooii oooi oooo" in {
        assertThat(Day04.isValidV2("iiii oiii ooii oooi oooo"), is(true))
      }
      "oiii ioii iioi iiio" in {
        assertThat(Day04.isValidV2("oiii ioii iioi iiio"), is(false))
      }
    }
  }
}