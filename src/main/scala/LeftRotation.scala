
/**
  * Created by naseers on 28/03/2018.
  */
object LeftRotation extends App {

  var arr = Array(1, 2, 3, 4, 5) // 0 to 4 , 1 to 5-2 = 3,

  println(arrayLeftRotation(arr, 5, 4).mkString(","))

  def arrayLeftRotation(arr: Array[Int], len: Int, b: Int): Array[Int] = {
    var res = new Array[Int](len)
    for ((x, i) <- arr.view.zipWithIndex) {
      res((i + len - b) % len) = arr(i);
    }
    res
  }
}
