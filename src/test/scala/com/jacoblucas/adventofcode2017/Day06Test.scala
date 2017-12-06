package com.jacoblucas.adventofcode2017

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class Day06Test extends WordSpec with Matchers {

  val b1 = MemoryBank(0, 0)
  val b2 = MemoryBank(1, 2)
  val b3 = MemoryBank(2, 7)
  val b4 = MemoryBank(3, 0)
  val b5 = MemoryBank(4, 7)
  val b6 = MemoryBank(5, 1)
  val b7 = MemoryBank(6, 3)

  "Day06" when {
    "parsing raw banks list" should {
      "parse correctly" in {
        val raw = List(5,1,10,0,1,7,13,14,3,12,8,10,7,12,0,6)
        assertThat(Day06.parse(raw), is(List(
          MemoryBank(0, 5),   //  11
          MemoryBank(1, 1),   //  13
          MemoryBank(2, 10),  //  5
          MemoryBank(3, 0),   //  -
          MemoryBank(4, 1),   //  14
          MemoryBank(5, 7),   //  8
          MemoryBank(6, 13),  //  2
          MemoryBank(7, 14),  //  1
          MemoryBank(8, 3),   //  12
          MemoryBank(9, 12),  //  3
          MemoryBank(10, 8),  //  7
          MemoryBank(11, 10), //  6
          MemoryBank(12, 7),  //  9
          MemoryBank(13, 12), //  4
          MemoryBank(14, 0),  //  -
          MemoryBank(15, 6)   //  10
        )))
      }
    }
    "sorting memory banks" should {
      "return a list of memory banks, sorted by number of blocks, highest to lowest" in {
        assertThat(Day06.sortByBlocks(List(b1, b2, b3, b4, b5, b6)), is(List(b3, b5, b2, b6, b1, b4)))

        val banks = List(
          MemoryBank(0, 5),   //  11
          MemoryBank(1, 1),   //  13
          MemoryBank(2, 10),  //  5
          MemoryBank(3, 0),   //  -
          MemoryBank(4, 1),   //  14
          MemoryBank(5, 7),   //  8
          MemoryBank(6, 13),  //  2
          MemoryBank(7, 14),  //  1
          MemoryBank(8, 3),   //  12
          MemoryBank(9, 12),  //  3
          MemoryBank(10, 8),  //  7
          MemoryBank(11, 10), //  6
          MemoryBank(12, 7),  //  9
          MemoryBank(13, 12), //  4
          MemoryBank(14, 0),  //  -
          MemoryBank(15, 6)   //  10
        )

        assertThat(Day06.sortByBlocks(banks).map(_.id), is(List(7,6,9,13,2,11,10,5,12,15,0,8,1,4,3,14)))
      }
    }
    "redistributing a memory bank" should {
      "pick the bank with the most blocks and evenly distribute with remainder" in {
        val banks = List(b1, b2, b3, b4)
        assertThat(Day06.redistribute(2, banks, 3, 7), is(List(
          MemoryBank(0, 2),
          MemoryBank(1, 4),
          MemoryBank(2, 1),
          MemoryBank(3, 2)
        )))
      }
      "pick the bank with the most blocks and evenly distribute without remainder (part 1 first cycle)" in {
        val banks = List(
          MemoryBank(0, 5),
          MemoryBank(1, 1),
          MemoryBank(2, 10),
          MemoryBank(3, 0),
          MemoryBank(4, 1),
          MemoryBank(5, 7),
          MemoryBank(6, 13),
          MemoryBank(7, 14),
          MemoryBank(8, 3),
          MemoryBank(9, 12),
          MemoryBank(10, 8),
          MemoryBank(11, 10),
          MemoryBank(12, 7),
          MemoryBank(13, 12),
          MemoryBank(14, 0),
          MemoryBank(15, 6)
        )
        assertThat(Day06.redistribute(7, banks, 8, 14), is(List(
          MemoryBank(0, 6),
          MemoryBank(1, 2),
          MemoryBank(2, 11),
          MemoryBank(3, 1),
          MemoryBank(4, 2),
          MemoryBank(5, 8),
          MemoryBank(6, 13),
          MemoryBank(7, 0),
          MemoryBank(8, 4),
          MemoryBank(9, 13),
          MemoryBank(10, 9),
          MemoryBank(11, 11),
          MemoryBank(12, 8),
          MemoryBank(13, 13),
          MemoryBank(14, 1),
          MemoryBank(15, 7)
        )))
      }
//      "p2 second cycle" in {
//        val banks = List(
//          MemoryBank(0, 6),
//          MemoryBank(1, 2),
//          MemoryBank(2, 11),
//          MemoryBank(3, 1),
//          MemoryBank(4, 2),
//          MemoryBank(5, 8),
//          MemoryBank(6, 14),
//          MemoryBank(7, 0),
//          MemoryBank(8, 4),
//          MemoryBank(9, 13),
//          MemoryBank(10, 9),
//          MemoryBank(11, 11),
//          MemoryBank(12, 8),
//          MemoryBank(13, 13),
//          MemoryBank(14, 0),
//          MemoryBank(15, 7)
//        )
//        assertThat(Day06.redistribute(6, banks, 7, 14), is(List(
//          MemoryBank(0, 7),
//          MemoryBank(1, 3),
//          MemoryBank(2, 12),
//          MemoryBank(3, 2),
//          MemoryBank(4, 3),
//          MemoryBank(5, 9),
//          MemoryBank(6, 0),
//          MemoryBank(7, 1),
//          MemoryBank(8, 5),
//          MemoryBank(9, 14),
//          MemoryBank(10, 10),
//          MemoryBank(11, 12),
//          MemoryBank(12, 9),
//          MemoryBank(13, 14),
//          MemoryBank(14, 0),
//          MemoryBank(15, 8)
//        )))
//      }
    }
    "detecting cycles" should {
      "return the number of cycles to detect a loop" in {
        assertThat(Day06.reallocate(List(b1, b2, b3, b4), Map())._1, is(5))
      }
      "breaks when detecting a cycle" in {
        val seen = Map(
          (List(b2, b3, b4, b1), 1),
          (List(b3, b2, b4, b1), 2),
          (List(b2, b3, b1, b4), 3),
          (List(b1, b2, b3, b4), 4),
          (List(b1, b3, b4, b2), 5),
          (List(b1, b4, b3, b2), 6)
        )
        assertThat(Day06.reallocate(List(b1, b2, b3, b4), seen)._1, is(seen.size))
      }
    }
    "calculating cycle length" should {
      "count the number of cycles between the same banks-to-blocks allocation" in {
        assertThat(Day06.reallocate(List(b1, b2, b3, b4), Map())._2, is(4))
      }
    }
  }
}