package castleonthegrid

object Solution {
  import java.io.{BufferedReader, BufferedWriter, FileOutputStream, InputStreamReader, OutputStreamWriter, PrintWriter}
  import scala.collection.mutable.Queue
  import scala.collection.Map

  private val reader = new BufferedReader(new InputStreamReader(System.in))
  private val writer = new BufferedWriter(new OutputStreamWriter(System.out))
  //private val writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/castleonthegrid")))

  case class Point(val x: Int, val y: Int) {
    def move(d: Direction, c: Castle): Either[String, Point] = Point(x + d.x, y + d.y) match {
      case p if c.isBlocked(p) => Left("Invalid point in castle")
      case p => Right(p)
    }
  }
  sealed abstract class Direction(val x: Int, val y: Int)
  case object Center extends Direction(0, 0)
  case object North extends Direction(-1, 0)
  case object South extends Direction(1, 0)
  case object East extends Direction(0, 1)
  case object West extends Direction(0, -1)
  private val nsDirections = North :: South :: Nil
  private val ewDirections = East :: West :: Nil
  private val mTraverse: Map[Direction, List[Direction]] = Map(Center -> (nsDirections ++ ewDirections), North -> ewDirections, South -> ewDirections, West -> nsDirections, East -> nsDirections)
  private val queue = Queue.empty[Arrow]
  private var abracadabra = 0

  sealed trait Character
  case class Wall() extends Character
  case class End(point: Point) extends Character
  case class NotVisited() extends Character
  case class Arrow(point: Point, direction: Direction, value: Int) extends Character

  class Castle(size: Int) {
    private val castle = Array.ofDim[Character](size, size)
    def getCharacter(p: Point) = castle(p.x)(p.y)
    def addCharacter(p: Point, c: Character): Unit = castle(p.x)(p.y) = c
    def isOutside(p: Point) = p.x >= castle.length || p.x < 0 || p.y >= castle(0).length || p.y < 0
    def isBlocked(p: Point) = isOutside(p) || castle(p.x)(p.y) == Wall()
  }

  def solution(start: Arrow, end: End, castle: Castle): Int = {
    def traverse(arrow: Arrow, direction: Direction, value: Int): Unit = {
      arrow.point.move(direction, castle) match {
      case Left(_) => ()
      case Right(p) => {
        castle.getCharacter(p) match {
          case NotVisited() => {
            val a = Arrow(p, direction, value)
            castle.addCharacter(p, a)
            queue.enqueue(a)
            traverse(a, direction, value)
          }
          case a: Arrow => traverse(a, direction, value)
          case End(_) => castle.addCharacter(p, Arrow(p, direction, value))
          case _ => ()
        }
      }}
    }

    queue.enqueue(start)
    while (!queue.isEmpty) {
      val a = queue.dequeue
      mTraverse(a.direction).foreach(traverse(a, _, a.value + 1))
    }

    castle.getCharacter(end.point) match {
      case c: Arrow => c.value
      case _ => -1
    }
  }

  def main(args: Array[String]) {
    val n = reader.readLine.toInt
    val castle = new Castle(n)
    for {
      row <- 0 until n
      c = reader.readLine.getBytes().map(b => if (b==88) Wall() else NotVisited())
      col <- 0 until n
      p = Point(row, col)
      _ = castle.addCharacter(p, c(col))
    } yield ()
    val p = reader.readLine().split(" ").map(_.toInt).grouped(2).map(l => Point(l(0), l(1))).toArray
    val (start, end) = (Arrow(p(0), Center, 0), End(p(1)))
    castle.addCharacter(start.point, start)
    castle.addCharacter(end.point, end)
    println(s"${solution(start, end, castle)}")
  }

}
