package leetcode

object Question1414 extends App {

  def findMinFibonacciNumbers(k: Int): Int = {
    import scala.collection.mutable
    val treeSet = mutable.TreeSet[Int]()(Ordering[Int].reverse)
    var x = 0
    var y = 1
    while (x <= k && y <=k) {
      val t = y
      y = x + y
      treeSet.add(y)
      x = t
    }
    var count = 0
    var i = k
    while (i > 0) {
      i = i - treeSet.from(i).to(1).firstKey
      count = count + 1
    }
    count
  }

//  println(findMinFibonacciNumbers(10000*10000*10))
  println(findMinFibonacciNumbers(19))
}
