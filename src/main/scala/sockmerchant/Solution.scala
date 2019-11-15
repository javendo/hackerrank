package sockmerchant

object Solution {
  import scala.collection.mutable.HashMap
  import scala.collection.mutable.HashSet
  import scala.collection.mutable.Set
  def main(args: Array[String]) {
    import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter}
    val reader = new BufferedReader(new InputStreamReader(System.in))
    val writer = new BufferedWriter(new OutputStreamWriter(System.out))
    val n = reader.readLine.map(_.toInt)
    val ar = reader.readLine.split(" ")
    val control = new Array[Int](99)
    val summarize = (position: Int, control: Array[Int], collors: Array[Int], acc: Int) => (control(ar(i)) + 1) % 2 match {
      0 if position < 100 => summarize(position + 1, control, collors, acc + 1)
      1 if position < 100 => summarize(position + 1, control, collors, acc)
      _ => acc
    }
    return summarize(0, control, ar, 0)
  }
}
