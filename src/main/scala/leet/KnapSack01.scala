package leet

import leet.KnapSack01.values

import scala.util.control.Breaks._

/**
  * Created by naseers on 31/03/2018.
  */
object KnapSack01 extends App {

  val values = Array(22, 20, 15, 30, 24, 54, 21, 32, 18, 25)
  val wt = Array(4, 2, 3, 5, 5, 6, 9, 7, 8, 10)

  println(bottomUpDP(values, wt, 30))

  def bottomUpDP(values: Array[Int], weights: Array[Int], maxWeight: Int): Int = {
    val K = Array.ofDim[Int](values.length+1, maxWeight+1)

    for (i <- 1 until  values.length) {
      for (j <- 1 until  weights.length) {

        if (j < weights(i - 1)) {
          K(i)(j) = K(i - 1)(j)
        } else {
          K(i)(j) = Math.max(values(i) + K(i - 1)(j - weights(i - 1)), K(i - 1)(j))
        }
      }
    }
    return K(values.length)(maxWeight)
  }


}
