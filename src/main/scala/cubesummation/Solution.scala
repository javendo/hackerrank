package cubesummation

object Solution {
  //def size: (Int, Int) => Int = (n, acc) => if (n > 1) size(n >> 1, acc + 1) else acc
  //def secondComplement: Int => Int = n => ((1 << size(n, 1)) - 1) ^ n
  //def parent: Int => Int = n => n - (secondComplement(n) + 1)
  //def next: Int => Int = n => secondComplement(n) + 1 + n

  def update(arr: Array[Array[Array[Int]]], x: Int, y: Int, z: Int, v: Int) = arr(x-1)(y-1)(z-1) += v

  def query(arr: Array[Array[Array[Int]]], x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) = (for (_x <- x1 - 1 to x2 - 1; _y <- y1 - 1 to y2 - 1; _z <- z1 - 1 to z2 - 1) yield arr(_x)(_y)(_z)).sum

  def main(args: Array[String]) {
    import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter}
    val reader = new BufferedReader(new InputStreamReader(System.in))
    val writer = new BufferedWriter(new OutputStreamWriter(System.out))
    val cubes = reader.readLine.toInt
    for (_ <- 1 to cubes) {
      val params = reader.readLine.split(" ").map(_.toInt)
      val size = params(0)
      val arr = Array.ofDim[Int](size, size, size)
      val queries = params(1)
      for (_ <- 1 to queries) {
        val qry = reader.readLine.split(" ")
        val qryType = qry.head
        val qryVal = qry.tail.map(_.toInt)
        if (qryType == "UPDATE") update(arr, qryVal(0), qryVal(1), qryVal(2), qryVal(3))
        if (qryType == "QUERY") println(query(arr, qryVal(0), qryVal(1), qryVal(2), qryVal(3), qryVal(4), qryVal(5)))
      }
    }
  }

}
