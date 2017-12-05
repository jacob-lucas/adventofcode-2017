package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day05Test extends WordSpec with Matchers {
  "Day05" when {
    "reading instructions at a position" should {
      "return the number at a position in the list" in {
        val maybeInt = Day05.readInstructionAt(2, List(1, 2, 3))
        maybeInt match {
          case Some(n) => assertThat(n, is(3))
          case None    => fail("did not read number correctly")
        }
      }
      "return None for a negative position" in {
        val maybeInt = Day05.readInstructionAt(-1, List(1,2,3))
        maybeInt match {
          case Some(n) => fail("did not read number correctly: " + n)
          case None    =>
        }
      }
      "return None for a position off the end of the list" in {
        val maybeInt = Day05.readInstructionAt(4, List(1,2,3))
        maybeInt match {
          case Some(n) => fail("did not read number correctly: " + n)
          case None    =>
        }
      }
    }
    "increasing offset at a position" should {
      "return a list with the increased offset" in {
        val instructions = List(1,2,3)
        val modifiedInstructions = Day05.increaseOffsetAt(1, instructions)
        assertThat(instructions, equalTo(List(1,2,3)))
        assertThat(modifiedInstructions, equalTo(List(1,3,3)))
      }
    }
    "processing instructions" should {
      "exit in the expected amount of steps" in {
        assertThat(Day05.process(List(0,3,0,1,-3), 0, 0), is(5))
      }
    }
  }
}