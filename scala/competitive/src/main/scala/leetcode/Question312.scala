package leetcode

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}

/**
 * Logic is correct getting into memory limit exceeded error.
 */
object Question312 extends App {

  class Node(val arr: Array[Boolean], val s: Int)

  def maxCoins(nums: Array[Int]): Int = {
    val bfs = ListBuffer[Node]()
    val map = mutable.HashMap[String, Int]()
    for (i <- nums.indices) {
      val arr = Array.ofDim[Boolean](nums.length)
      arr(i) = true
      bfs.append(new Node(arr, nums(i)))
      map(arr.map(x => if (x) 1 else 0).mkString("")) = nums(i)
    }
    while (bfs.nonEmpty) {
      val tn: Node = bfs.remove(0)
      for (i <- nums.indices) {
        if (!tn.arr(i)) {
          val arr = tn.arr.map(x => x)
          arr(i) = true
          val uq = arr.map(x => if (x) 1 else 0).mkString("")
          var from: Int = -1
          var to: Int = -1
          breakable {
            for (k <- i until -1 by -1) {
              if (tn.arr(k)) {
                from = k
                break
              }
            }
          }
          breakable {
            for (k <- i + 1 until nums.length) {
              if (tn.arr(k)) {
                to = k
                break
              }
            }
          }
          val score = Math.max(tn.s, map.getOrElse(tn.arr.map(x => if (x) 1 else 0).mkString(""), 1)) + (nums(i) * (if (from == -1) 1 else nums(from)) * (if (to == -1) 1 else nums(to)))
          if (!map.contains(uq)) {
            bfs.append(new Node(arr, score))
          }
          map(uq) = Math.max(score, map.getOrElse(uq, 1))
        }
      }
    }
    map.values.max

  }

  println(maxCoins(Array(3, 1, 5, 8)))
  println(maxCoins(Array(1, 5)))
}
