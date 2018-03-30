package leet

/**
  * Created by naseers on 29/03/2018.
  */
object ContainerWithMostWater extends App {

  var arr = Array(1, 8, 6, 2, 5, 4, 8, 3, 7)

  var maxArea = 0
  var left = 0
  var right = arr.length - 1
  while (left < right) {
    maxArea = Math.max(maxArea, Math.min(arr(left), arr(right)) * (right - left))
    if (arr(left) < arr(right)) {
      left += 1
    } else {
      right -= 1
    }
  }
  println(maxArea)
}
