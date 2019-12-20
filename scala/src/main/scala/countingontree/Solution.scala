package countingontree

object Solution {
  import scala.collection.mutable.HashMap
  import scala.collection.mutable.HashSet
  import scala.collection.mutable.Set
  def main(args: Array[String]) {
    import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter}
    val reader = new BufferedReader(new InputStreamReader(System.in))
    val writer = new BufferedWriter(new OutputStreamWriter(System.out))
    val first = reader.readLine.split(" ").map(_.toInt)
    val (n, q) = (first(0), first(1))
    val values = reader.readLine.split(" ")
    val tree = new Array[Int](n + 1)
    for (_ <- 1 until n) {
      val nodes = reader.readLine.split(" ").map(_.toInt)
      val (max, min) = (Math.max(nodes(0), nodes(1)), Math.min(nodes(0), nodes(1)))
      tree(max) = min
    }
    val m = HashMap.empty[String, Set[Int]]
    for (_ <- 1 to q) {
      val line = reader.readLine.split(" ").map(_.toInt)
      path(m, values, tree, line(0), line(1), 0)
      println(path(m, values, tree, line(2), line(3), 1))
      m.clear
    }
  }

  def path(m: HashMap[String, Set[Int]], values: Array[String], tree: Array[Int], node1: Int, node2: Int, idx: Int): Int = {
    def p(n1: Int, n2: Int, acc: Int): Int = (n1, n2) match {
      case _ if n1 == n2 => {
        buildMap(m, values(n1 - 1), n1, idx, acc)
      }
      case _ if n1 > n2 => {
        val accint = buildMap(m, values(n1 - 1), n1, idx, acc)
        p(tree(n1), n2, accint)
      }
      case _ if n1 < n2 => {
        val accint = buildMap(m, values(n2 - 1), n2, idx, acc)
        p(n1, tree(n2), accint)
      }
    }
    p(node1, node2, 0)
  }

  def buildMap(m: HashMap[String, Set[Int]], value: String, node: Int, idx: Int, acc: Int): Int = idx match {
    case 0 => {
      m.getOrElseUpdate(value, HashSet.empty[Int]).add(node)
      0
    }
    case 1 => m.get(value) match {
      case None => acc + 0
      case Some(v) => acc + v.size + (if (v.contains(node)) -1 else 0)
    }
  }
}
