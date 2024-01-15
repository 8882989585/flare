package leetcode

object Question1067 extends App {

  private def getCount(d: Int, n: Int) = {
    var cnt = 0
    var m = 1
    while (m <= n) {
      val a = n / m
      val b = n % m
      cnt += (a + 9 - d) / 10 * m + (if (a % 10 == d) b + 1 else 0)
      if (d == 0) cnt -= m

      m *= 10
    }
    cnt
  }

  def digitsCount(d: Int, low: Int, high: Int) = {
    getCount(d, high) - getCount(d, low - 1)
  }

  //  println(digitsCount(1, 1, 20))
  //  println(digitsCount(1, 1, 13))
  //  println(digitsCount(0, 1, 35))
  //  println(digitsCount(1, 1, 100))
  //  println(digitsCount(0, 1, 196))
  //  println(digitsCount(0, 1, 1080))
  println(digitsCount(0, 1, 10180))
  //  println(digitsCount(5, 1, 5253))
  //  println(digitsCount(5, 1, 5660))
  //  println(digitsCount(0, 1, 5660))

}
