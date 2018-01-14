package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day16Test extends WordSpec with Matchers {
  val programs = List(
    DancingProgram("a"),
    DancingProgram("b"),
    DancingProgram("c"),
    DancingProgram("d"),
    DancingProgram("e")
  )

  "Day16" when {
    "parsing dance moves" should {
      "parse spin correctly" in {
        Day16.parse("s12") match {
          case Some(s) =>
            assertThat(s.asInstanceOf[Spin].n, is(12))
          case None    => fail("did not parse correctly")
        }
      }
      "parse exchange correctly" in {
        Day16.parse("x15/10") match {
          case Some(e) =>
            assertThat(e.asInstanceOf[Exchange].a, is(15))
            assertThat(e.asInstanceOf[Exchange].b, is(10))
          case None    => fail("did not parse correctly")
        }
      }
      "parse partner correctly" in {
        Day16.parse("pA/B") match {
          case Some(p) =>
            assertThat(p.asInstanceOf[Partner].a, is("A"))
            assertThat(p.asInstanceOf[Partner].b, is("B"))
          case None    => fail("did not parse correctly")
        }
      }
      "handle error string correctly" in {
        Day16.parse("garbage") match {
          case Some(p) => fail("did not parse correctly")
          case None    => // pass
        }
      }
    }
    "dancing" should {
      "spin" in {
        val spin = Spin(3)
        assertThat(spin.dance(programs), is(List(
          DancingProgram("c"),
          DancingProgram("d"),
          DancingProgram("e"),
          DancingProgram("a"),
          DancingProgram("b")
        )))
      }
      "exchange" in {
        val exchange = Exchange(3, 4)
        assertThat(exchange.dance(programs), is(List(
          DancingProgram("a"),
          DancingProgram("b"),
          DancingProgram("c"),
          DancingProgram("e"),
          DancingProgram("d")
        )))
      }
      "partner" in {
        val partner = Partner("e", "b")
        assertThat(partner.dance(programs), is(List(
          DancingProgram("a"),
          DancingProgram("e"),
          DancingProgram("c"),
          DancingProgram("d"),
          DancingProgram("b")
        )))
      }
    }
    "executing dance instructions" should {
      "call over the list of programs repeatedly" in {
        val danceMoves = List(Spin(1), Exchange(3, 4), Partner("e", "b"))
        assertThat(Day16.dance(danceMoves, programs), is(List(
          DancingProgram("b"),
          DancingProgram("a"),
          DancingProgram("e"),
          DancingProgram("d"),
          DancingProgram("c")
        )))
      }
    }
  }
}
