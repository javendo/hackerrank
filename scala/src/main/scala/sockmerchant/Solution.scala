package sockmerchant

object Solution {
  import scala.collection.mutable.HashMap
  import scala.collection.mutable.HashSet
  import scala.collection.mutable.Set
  def main(args: Array[String]) {
    import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter, PrintWriter}
    val reader = new BufferedReader(new InputStreamReader(System.in))
    val writer = new BufferedWriter(new OutputStreamWriter(System.out))
    val n = reader.readLine.toInt
    val ar = reader.readLine.split(" ")
    def summarize(position: Int, control: Array[Int], acc: Int): Int = position match {
      case `n` => acc
      case _ => {
        control(ar(position).toInt - 1) = (control(ar(position).toInt - 1) + 1) % 2
        control(ar(position).toInt - 1) match {
          case 0 => summarize(position + 1, control, acc + 1)
          case 1 => summarize(position + 1, control, acc)
        }
      }
    }
    println(summarize(0, new Array[Int](100), 0))
  }
}
