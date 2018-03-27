/**
  * Created by naseers on 22/03/2018.
  */
object Recursion extends App {

  val list = List.range(1, 100)

  println(sum(list));
  println(sum2(list))

  //List extractor :: demo
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case x :: tail => x + sum(tail)
  }

  def sum2(ints: List[Int]): Int = {

    def sumAccumulator(ints: List[Int], accum: Int): Int = ints match {
      case Nil => accum
      case x :: tail => sumAccumulator(tail, accum + x)
    }

    sumAccumulator(ints, 0)
  }

  def sum3(ints: List[Int]): Int = {
    if (ints.isEmpty) return 0;
    else ints.head + sum3(ints.tail)
  }

  def sumReduce(ints: List[Int]): Int = ints.reduceLeft(_ + _)
}
