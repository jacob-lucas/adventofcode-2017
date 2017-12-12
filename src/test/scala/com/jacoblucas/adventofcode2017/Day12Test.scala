package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day12Test extends WordSpec with Matchers {
  val programs: Map[Int, Prog] = List(
    0 -> Prog(0, List(2)),
    1 -> Prog(1, List(1)),
    2 -> Prog(2, List(0, 3, 4)),
    3 -> Prog(3, List(2, 4)),
    4 -> Prog(4, List(2, 3, 6)),
    5 -> Prog(5, List(6)),
    6 -> Prog(6, List(4, 5)),
  ).toMap

  "Day12" when {
    "detecting a path" should {
      "calculate correctly" in {
        assertThat(Day12.pathExists(0, 0, programs, List()), is(true))
        assertThat(Day12.pathExists(1, 0, programs, List()), is(false))
        assertThat(Day12.pathExists(2, 0, programs, List()), is(true))
        assertThat(Day12.pathExists(3, 0, programs, List()), is(true))
        assertThat(Day12.pathExists(4, 0, programs, List()), is(true))
        assertThat(Day12.pathExists(5, 0, programs, List()), is(true))
        assertThat(Day12.pathExists(6, 0, programs, List()), is(true))
      }
    }
    "detecting which programs can communicate with a given program" should {
      "calculate correctly" in {
        assertThat(Day12.canCommunicateWith(0, programs).sorted, is(List(0, 2, 3, 4, 5, 6)))
      }
    }
    "detecting groups of programs" should {
      "calculate correctly" in {
        assertThat(Day12.group(programs.keys.toList, programs, List()), is(
          List(
            List(0, 2, 3, 4, 5, 6),
            List(1)
          )
        ))
      }
    }
  }
}
