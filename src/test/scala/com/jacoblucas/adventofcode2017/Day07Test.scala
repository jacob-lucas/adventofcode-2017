package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day07Test extends WordSpec with Matchers {

  val testInput = "pbga (66)\nxhth (57)\nebii (61)\nhavc (66)\nktlj (57)\nfwft (72) -> ktlj, cntj, xhth\nqoyq (66)\npadx (45) -> pbga, havc, qoyq\ntknk (41) -> ugml, padx, fwft\njptl (61)\nugml (68) -> gyxo, ebii, jptl\ngyxo (61)\ncntj (57)"
  val bottomProgram = Program("tknk", 41, List("ugml", "padx", "fwft"))
  val testTower = Map(
    "tknk" -> bottomProgram,
    "ugml" -> Program("ugml", 68, List("gyxo", "ebii", "jptl")),
    "padx" -> Program("padx", 45, List("pbga", "havc", "qoyq")),
    "fwft" -> Program("fwft", 72, List("ktlj", "cntj", "xhth")),
    "gyxo" -> Program("gyxo", 61, List()),
    "ebii" -> Program("ebii", 61, List()),
    "jptl" -> Program("jptl", 61, List()),
    "pbga" -> Program("pbga", 66, List()),
    "havc" -> Program("havc", 66, List()),
    "qoyq" -> Program("qoyq", 66, List()),
    "ktlj" -> Program("ktlj", 57, List()),
    "cntj" -> Program("cntj", 57, List()),
    "xhth" -> Program("xhth", 57, List())
  )

  "Day07" when {
    "parsing a single line" should {
      "return a program" in {
        assertThat(Day07.parse("pbga (66)"), is(Program("pbga", 66, List())))
        assertThat(Day07.parse("fwft (72) -> ktlj, cntj, xhth"), is(Program("fwft", 72, List("ktlj", "cntj", "xhth"))))
      }
    }
    "parsing a tower" should {
      "return the a map of names -> programs" in {
        val lines = testInput.split("\n").toList
        val tower = Day07.parseTower(lines)
        assertThat(tower.keys.size, is(testTower.keys.size))
        assertThat(tower.keySet.toList.sorted, is(testTower.keySet.toList.sorted))
        for (
          key <- tower.keys
        ) {
          val towerProgram = tower(key)
          val resultProgram = testTower(key)
          assertThat(towerProgram.disc.size, is(resultProgram.disc.size))

          if (towerProgram.disc.nonEmpty) {
            assertThat("discs of " + key + " do not match!", towerProgram.disc.sorted, is(resultProgram.disc.sorted))
          }
        }
      }
    }
    "finding the bottom program in a tower" should {
      "return the bottom program" in {
        assertThat(Day07.findBottomProgramName(testTower), is(bottomProgram.name))
      }
    }
    "calculating the weight of a tower" should {
      "calculate the weight of that tower plus all the programs on its disc" in {
        assertThat(Day07.weight("ugml", testTower), is((68, List(61,61,61))))
        assertThat(Day07.weight("padx", testTower), is((45, List(66,66,66))))
        assertThat(Day07.weight("fwft", testTower), is((72, List(57,57,57))))
        assertThat(Day07.weight("tknk", testTower), is((41, List(251, 243, 243))))
      }
      "calculating total weight" in {
        assertThat(Day07.totalWeight(68, List(61,61,61)), is(251))
        assertThat(Day07.totalWeight(45, List(66,66,66)), is(243))
        assertThat(Day07.totalWeight(72, List(57,57,57)), is(243))
      }
    }
    "seeing if a tower is balanced" should {
      "return true if each of the prorams on its disc, and all the programs above it have the same weight" in {
        assertThat(Day07.isBalanced(Program("xhth", 68, List()), testTower), is(true))
        assertThat(Day07.isBalanced(Program("ugml", 68, List("gyxo", "ebii", "jptl")), testTower), is(true))
        assertThat(Day07.isBalanced(Program("padx", 45, List("pbga", "havc", "qoyq")), testTower), is(true))
        assertThat(Day07.isBalanced(Program("fwft", 72, List("ktlj", "cntj", "qoyq")), testTower), is(false))
        assertThat(Day07.isBalanced(bottomProgram, testTower), is(false))

        val balancedTestTower = Map(
        "tknk" -> Program("tknk", 41, List("padx", "fwft")),
        "padx" -> Program("padx", 45, List("pbga", "havc", "qoyq")),
        "fwft" -> Program("fwft", 72, List("ktlj", "cntj", "xhth")),
        "pbga" -> Program("pbga", 66, List()),
        "havc" -> Program("havc", 66, List()),
        "qoyq" -> Program("qoyq", 66, List()),
        "ktlj" -> Program("ktlj", 57, List()),
        "cntj" -> Program("cntj", 57, List()),
        "xhth" -> Program("xhth", 57, List())
        )
        assertThat(Day07.isBalanced(Program("tknk", 41, List("padx", "fwft")), balancedTestTower), is(true))
      }
    }
    "detecting the balance point" should {
      "return None for empty disc" in {
        assertThat(Day07.detectBalancePoint(Program("", 0, List()), testTower).isDefined, is(false))
      }
      "find the program where the tower is not balanced" in {
        assertThat(Day07.detectBalancePoint(bottomProgram, testTower).get, is(bottomProgram))
      }
    }
  }
}
