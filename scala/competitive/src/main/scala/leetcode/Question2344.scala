package leetcode

object Question2344 extends App {

  import scala.collection.mutable.ListBuffer

  def reduceArrays(arr: Array[Int]): ListBuffer[Int] = {
    val processList = ListBuffer[Int]()
    for (i <- arr.indices) {
      var f = false
      for (j <- 0 until i) {
        if (arr(i) % arr(j) == 0) {
          f = true
        }
      }
      if (!f) {
        processList.append(arr(i))
      }
    }
    processList
  }

  //  def minOperations(nums: Array[Int], numsDivide: Array[Int]): Int = {
  //    val sortedNums = nums.sorted
  //    val disNums = sortedNums.distinct.sorted
  //    val sortedDivide = numsDivide.distinct.sorted
  //    val downSizedDivide = reduceArrays(sortedDivide)
  //    var r = -1
  //    breakable {
  //      for (i <- disNums) {
  //        if (downSizedDivide.count(x => x % i != 0) == 0) {
  //          r = i
  //          break
  //        }
  //      }
  //    }
  //    if (r != -1) {
  //      for (i <- sortedNums.indices) {
  //        if (sortedNums(i) == r) {
  //          return i
  //        }
  //      }
  //    }
  //    -1
  //  }

  def gcd(a: Int, b: Int): Int = {
    if (b == 0) return a
    gcd(b, a % b)
  }

  def minOperations(nums: Array[Int], numsDivide: Array[Int]): Int = {
    var gc = 0
    for (i <- numsDivide) {
      gc = gcd(i, gc)
    }
    val sortedNums = nums.sorted
    for (i <- nums.sorted.indices) {
      if (gc % sortedNums(i) == 0) {
        return i
      }
    }
    -1
  }

  println(minOperations(Array(2, 3, 2, 4, 3), Array(9, 6, 9, 3, 15)))
  println(minOperations(Array(4, 3, 6), Array(8, 2, 6, 10)))
  println(minOperations(Array(3, 2, 6, 2, 35, 5, 35, 2, 5, 8, 7, 3, 4), Array(105, 70, 70, 175, 105, 105, 105)))


}
