import java.util.stream.Collectors

import scala.collection.mutable

/**
  * Created by naseers on 27/03/2018.
  */
object JewelsAndStones extends App {

  def numJewelsInStones(J: String, S: String): Int = {

    var map = new mutable.HashMap[Char, Int]()
    for (c <- S.toCharArray) {
      if (map.contains(c)) {
        map.put(c, map(c) + 1)
      } else {
        map.put(c, 1)
      }
    }

    var count: Int = 0;
    for (c <- J.toCharArray) {
      if (map.contains(c)) count += map(c)
    }

    count
  }

  var s = "aAAbbbb";
  var j = "aA";
  println(numJewelsInStones(j, s))

}
