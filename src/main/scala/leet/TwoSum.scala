package leet

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

/**
  * Created by naseers on 29/03/2018.
  */
object TwoSum extends App {

  var nums = Array(2, 7, 11, 15)
  var target = 26;

  var map = new mutable.HashMap[Int, Int]();

  var indexes = new ArrayBuffer[Int]();
  breakable {
    for ((x, i) <- nums.view.zipWithIndex) {
      map(x) = i;
      if (map.contains(target - x)) {
        indexes += map(target-x)
        indexes += i
        break()
      }
    }
  }

  println(indexes.mkString(","))
}
