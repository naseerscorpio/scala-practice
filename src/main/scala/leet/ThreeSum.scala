package leet

import java.util

/**
  * Created by naseers on 30/03/2018.
  */
object ThreeSum extends App {


  var nums = Array(-1, 0, 1, 2, -1, -4)
  nums = nums.sorted
  var result = new util.ArrayList[List[Integer]]()

  for ((x, i) <- nums.view.zipWithIndex if i < nums.length - 2) {
    if (i == 0 || (i > 0 && nums(i) != nums(i - 1))) {
      var lo = i + 1;
      var hi = nums.length - 1;
      var sum = -nums(i);
      while (lo < hi) {
        if (nums(lo) + nums(hi) == sum) {
          result.add(List(nums(i), nums(lo), nums(hi)))
          while (lo < hi && nums(lo) == nums(lo + 1)) lo += 1
          while (lo < hi && nums(hi) == nums(hi - 1)) hi -= 1
          lo += 1
          hi -= 1
        } else if (nums(lo) + nums(hi) < sum) {
          lo += 1
        } else
          hi -= 1
      }
    }
  }

  println(result)
  result
}
