import java.io._
import scala.collection.mutable

object Solution {

  def solution(graph: Array[List[Int]], color: Int): Int = {
    def shortestPath(nodes: List[Int], acc: Int, visited: mutable.HashMap[Int, Int]): Int = {
      nodes.filter(visited.getOrElseUpdate(_, {acc}) == acc) match {
        case Nil => Int.MaxValue
        case list => {
          list.filter(graph(_).head == color) match {
            case Nil => list.map(idx => shortestPath(graph(idx).tail, acc + 1, visited)).min
            case _ => acc
          }
        }
      }
    }
    var idx = graph.indexWhere(_.head == color, 0)
    var min = Int.MaxValue
    while (idx > -1) {
      min = Math.min(min, shortestPath(graph(idx).tail, 1, mutable.HashMap(idx -> 0)))
      idx = graph.indexWhere(_.head == color, idx + 1)
    }
    if (min == Int.MaxValue) -1 else min
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn
    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))
    val sizes = stdin.readLine.split(" ").map(_.toInt)
    val (nodes, edges) = (sizes(0), sizes(1))
    val graph = Array.fill[List[Int]](nodes){Nil}
    for {
      arrows <- 0 until edges
      idxEdges = stdin.readLine.split(" ").map(_.toInt)
      (v1, v2) = (idxEdges(0) - 1, idxEdges(1) - 1)
      _ = graph(v1) = v2 :: graph(v1)
      _ = graph(v2) = v1 :: graph(v2)
    } yield ()
    stdin.readLine.split(" ").map(_.toInt).zipWithIndex.foreach(p => graph(p._2) = p._1 :: graph(p._2))
    val color = stdin.readLine.toInt
    printWriter.println(solution(graph, color))
    printWriter.close()
  }
}
