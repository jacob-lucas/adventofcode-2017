package com.jacoblucas.adventofcode2017

import scala.annotation.tailrec

case class MemoryBank(id: Int, blocks: Int) {
  override def toString: String = (if (id < 10) "0" + id else id) + "=" + (if (blocks < 10) "0" + blocks else blocks)
}

object Day06 {
  def sortByBlocks(banks: List[MemoryBank]): List[MemoryBank] = {
    banks
      .sortWith((b1: MemoryBank, b2: MemoryBank) =>
        // b1 comes before b2 if it has more blocks or in case of a blocks tie, if it has a lower id
        b1.blocks > b2.blocks || (b1.blocks == b2.blocks && b1.id < b2.id))
  }

  @tailrec
  def redistribute(id: Int, banks: List[MemoryBank], pos: Int, blocksRemaining: Int): List[MemoryBank] = {
    if (blocksRemaining == 0 || (blocksRemaining == 1 && pos == id)) banks
    else {
      val arr = banks.toArray

      // take away 1 block from id
      val bankAtId = banks.filter(_.id == id).head
      arr(id) = MemoryBank(id, bankAtId.blocks - 1)

      // jump over id if needed
      val posToInsert =
        if (pos == id) (id + 1) % banks.size
        else pos

      // give 1 block to the block at pos
      val bankAtPos = banks.filter(_.id == posToInsert).head
      arr(posToInsert) = MemoryBank(posToInsert, bankAtPos.blocks + 1)

      // redistribute the remainder
      redistribute(id, arr.toList, (posToInsert + 1) % banks.size, blocksRemaining - 1)
    }
  }

  @tailrec
  def reallocate(banks: List[MemoryBank], seen: Map[List[MemoryBank], Int]): (Int, Int) = {
    if (seen.contains(banks)) {
      val len = seen.size - seen(banks)
      println("Cycle detected [" + banks + "] after " + seen.size + " redistributions (len=" + len + ")")
      (seen.size, len)
    } else {
      val max = sortByBlocks(banks).head
      println("(" + (seen.size + 1) +") Redistributing " + max + " in " + banks)
      val redistributed = redistribute(max.id, banks, (max.id + 1) % banks.size, max.blocks)
      println("In:  " + banks)
      println("Out: " + redistributed)
      reallocate(redistributed, seen updated (banks, seen.size))
    }
  }

  def parse(raw: List[Int]): List[MemoryBank] =
    raw
      .indices
      .map(i => MemoryBank(i, raw(i)))
      .toList

  def main(args: Array[String]): Unit = {
    val lines = Util.read("/Day06.txt")
    lines foreach println
    val banks = lines
      .head
      .split("\t")
      .map(_.toInt)
      .toList

    // part 1 and part 2 (part 1 answer printed to command line, part 2 answer inferred by subtracting from output)
    println(reallocate(parse(banks), Map()))
  }
}
