package kindergartenadventures

object Solution {
  def main(args: Array[String]) {
    import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter}
    val reader = new BufferedReader(new InputStreamReader(System.in))
    val writer = new BufferedWriter(new OutputStreamWriter(System.out))
    val n = reader.readLine.toInt
    val prefix = Array.ofDim[Int](n)
    def arr = reader.readLine.split(" ").view.map(_.toInt).zipWithIndex.filter(_._1 < n)
    prefix.map(_ => 0)
    for ((v, i) <- arr) {
      val (l, r, nr) = ((i + 1) % n, (i - v + n) % n, (i - v + n + 1) % n)
      if (l > r) {
        prefix(0) = prefix(0) + 1; prefix(l) = prefix(l) + (if (r == i) 0 else 1); prefix(nr) = prefix(nr) - (if (r == i) 0 else 1)
      }
      else {
        prefix(l) = prefix(l) + 1; prefix(nr) = prefix(nr) - (if (nr == l) 0 else 1)
      }
    }
    var max = prefix(0)
    var index = 1
    for (i <- 1 until n) {
      prefix(i) = prefix(i - 1) + prefix(i)
      if (prefix(i) > max) {
        max = prefix(i); index = i + 1
      }
    }
    println(index)
  }

}
