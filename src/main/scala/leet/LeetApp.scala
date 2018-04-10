package leet

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

/**
  * Created by naseers on 06/04/2018.
  */
object LeetApp extends App {


  println("===========Hamming Distance=================")
  println(hammingDistance(1, 4))

  def hammingDistance(x: Int, y: Int): Int = {
    var n = x ^ y
    var count = 0
    while (n > 0) {
      count += n & 1
      n >>= 1
    }
    count
  }

  println("===================Unique Morse Codes=============================")
  println(uniqueMorseRepresentations(Array("gin", "zen", "gig", "msg")))

  def uniqueMorseRepresentations(words: Array[String]): Int = {
    var morseCodes = Array(".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..")

    var result = new mutable.HashSet[String]()

    for (word <- words) {
      result.add(getCode(word))
    }

    def getCode(word: String): String = {
      var str = new mutable.StringBuilder();
      for (c <- word.toCharArray) {
        str.append(morseCodes(c - 'a'))
      }
      str.toString()
    }

    result.size
  }


  /*def smallestDistancePair(nums: Array[Int], k: Int): Int = {

  }*/

  /*println("=================K Smallest Pairs INCOMPLETE================================")
  println(kSmallestPairs(Array(1, 7, 11), Array(2, 4, 6), 3).foreach(ar => println(ar.mkString(","))))

  def kSmallestPairs(nums1: Array[Int], nums2: Array[Int], k: Int): List[Array[Int]] = {

    var m = nums1.length;
    var n = nums2.length

    var res = new ListBuffer[Array[Int]]
    if (nums1 == null || (nums1.length == 0) || nums2 == null || (nums2.length == 0) || k <= 0) return res.toList

    var pq = new mutable.PriorityQueue[Tuple]()
    for (i <- 0 until n) pq.enqueue(new Tuple(0, i, nums1(0) + nums2(i)))

    for (j <- 0 until Math.min(k, m * n)) {
      Breaks.breakable {
        var t = pq.dequeue()
        res += Array(nums1(t.x), nums2(t.y))
        if (t.x == m - 1) Breaks.break();
        pq.enqueue(new Tuple(t.x + 1, t.y, nums1(t.x + 1) + nums2(t.y)))
      }
    }
    res.toList
  }

  case class Tuple(x: Int, y: Int, value: Int)

  object Tuple {
    implicit def orderingByValue[A <: Tuple]: Ordering[A] = Ordering.by(e => e.value)

    val orderingByX: Ordering[Tuple] = Ordering.by(e => e.x)

  }

  */

}
