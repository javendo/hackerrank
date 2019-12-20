package castleonthegrid

import org.scalatest.FunSuite

class SolutionSuite extends FunSuite {

  test("Result should match with the output file") {
    import java.io.{FileInputStream, ByteArrayOutputStream, PrintStream}
    def traverseInputs(n: Int): Unit = Option(getClass.getResourceAsStream("/castleonthegrid/input_" + n)) match {
      case Some(input) => {
        val answer = scala.io.Source.fromURL(getClass.getResource("/castleonthegrid/output_" + n)).mkString
        val output = new ByteArrayOutputStream()
        System.setIn(input)
        Console.withOut(output) {
          Solution.main(Array[String]())
        }
        assert(answer == output.toString)
        traverseInputs(n + 1)
      }
      case None =>
    }
    traverseInputs(1)
  }

}
