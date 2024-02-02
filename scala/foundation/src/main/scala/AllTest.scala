import java.util
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.util.control.Breaks._

object AllTest extends App {

  class Node(var left: Node, var right: Node, var v: Int)

  private def createLinkedList(): Unit = {
    val t = new Node(new Node(null, null, 7), new Node(null, null, 9), 10)
    print(t)
  }

  private def usingJavaCollections(): Unit = {
    val treeMap = new util.TreeMap[Int, Int]()
    treeMap.put(6, 4)
    treeMap.put(1, 5)
    treeMap.put(5, 4)

    println(treeMap.ceilingEntry(4))

    treeMap.forEach((k, v) => print(k))
    treeMap.forEach({ case (k, v) => print(k) })

  }

  private def sortingInScala(): Unit = {
    val comp1: Ordering[Node] = Ordering.by[Node, Int](_.v).reverse
    val comp2: Ordering[Node] = new Ordering[Node]() {
      override def compare(x: Node, y: Node): Int = x.v compareTo y.v
    }
    val treeMap1 = new util.TreeSet[Node](comp1)
    val treeMap2 = new util.TreeSet[Node](comp2)
    treeMap1.add(new Node(null, null, 6))
    treeMap1.add(new Node(null, null, 1))
    treeMap1.add(new Node(null, null, 5))
    treeMap1.forEach(k => print(k.v))

  }

  private def switchCase(t: Int): Unit = {
    t match {
      case 1 => println("ho ha")
      case 2 => println("fgf")
    }
  }

  private def lists(): Unit = {
    val ll = new util.LinkedList[Node]()
    ll.add(new Node(null, null, 19))
    val t: Node = ll.removeFirst()
    print(t.v)
    ll.add(new Node(null, null, 19))
    ll.forEach(e => {
      println(e.v)
    })
  }

  private def scalaCollections(): Unit = {
    val seq = Seq(1, 3, 4)
    val seq1: ListBuffer[Int] = new ListBuffer[Int]()
    seq1 += 9
    seq1 ++= seq
    val seq2: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    seq2 ++= seq

    val treeMap = new mutable.TreeMap[Int, Int]()
    for (e <- treeMap) {
      println(e._1)
    }
  }

  private def scalaArrays(): Unit = {
    val arr = Array.ofDim[Int](3, 3)
    val arr2 = Array.fill[Int](3, 3)(1)
    println(arr2.mkString("Array(", ", ", ")"))
    println(arr2(1)(2))
  }

  private def loopsTheHoops(): Unit = {
    val ss = for (i <- 2 to 90 by 2)
      yield {
        i - 1
      }

    println(ss.toList.mkString("", ",", ""))
    val t = ss.toList.mkString("", ",", "").toCharArray
    println(t.mkString("Array(", ", ", ")"))
  }

  private def reassignment(): Unit = {
    val t = new Node(null, null, 6)
    val u = new Node(null, null, 1)
    val v = new Node(null, null, 5)
    t.left = v
    t.right = u
    println(t)
  }

  createLinkedList()
  usingJavaCollections()
  sortingInScala()
  switchCase(1)
  switchCase(2)
  lists()
  scalaCollections()
  scalaArrays()
  loopsTheHoops()
  reassignment()
}
