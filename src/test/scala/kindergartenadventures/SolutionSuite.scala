package kindergartenadventures

import org.scalatest.FunSuite

class SolutionSuite extends FunSuite {

  import java.io.{FileInputStream, ByteArrayOutputStream, PrintStream}
  def traverseInputs(n: Int): Unit = {
    Option(getClass.getResourceAsStream("/kindergartenadventures/input_" + n)) match {
      case Some(input) => {
        test(s"Result should match with the output file number $n") {
          val answer = scala.io.Source.fromURL(getClass.getResource("/kindergartenadventures/output_" + n)).mkString
          val output = new ByteArrayOutputStream()
          System.setIn(input)
          Console.withOut(output) {
            Solution.main(Array[String]())
          }
          assert(answer == output.toString)
        }
        traverseInputs(n + 1)
      }
      case None =>
    }
  }
  traverseInputs(1)
}
