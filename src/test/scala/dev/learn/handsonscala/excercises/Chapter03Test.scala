package dev.learn.handsonscala.excercises

import dev.learn.handsonscala.excercises.chapter03.ExerciseContextManagers.{
  withFileReader,
  withFileWriter
}
import dev.learn.handsonscala.excercises.chapter03.ExerciseFlexibleFizzBuzz.flexibleFizzBuzz
import dev.learn.handsonscala.excercises.chapter03.ExercisePrintMessages.printMessages
import dev.learn.handsonscala.excercises.chapter03.Msg
import dev.learn.handsonscala.helpers.OutputHelpers.assertOutputLines
import org.scalatest.funsuite.AnyFunSuite

import java.nio.file.Paths

class Chapter03Test extends AnyFunSuite {
  test("3.5 - FlexibleFizzBuzz") {
    assertOutputLines(100) {
      flexibleFizzBuzz(println)
    }

    var i = 0
    val output = new Array[String](100)
    flexibleFizzBuzz { s =>
      output(i) = s
      i += 1
    }

    assertResult(100)(output.length)
  }

  test("3.6 - PrintMessages") {
    val messages = Array(
      Msg(6, Some(5), "Here me moo, moo"),
      Msg(6, Some(5), "Here me moo, moo"),
      Msg(2, None, "I am Cow"),
      Msg(3, Some(2), "Hear me moo"),
      Msg(4, Some(2), "Here I stand"),
      Msg(0, None, "Hello"),
      Msg(1, Some(0), "World"),
      Msg(5, Some(2), "I am Cow")
    )

    assertOutputLines(messages.length) {
      printMessages(messages)
    }
  }

  test("3.7 - ContextManagers") {
    val path = "File.txt"

    withFileWriter(path) { writer =>
      writer.write("Hello\n"); writer.write("World!")
    }
    val result = withFileReader(path) { reader =>
      reader.readLine() + "\n" + reader.readLine()
    }
    assert(result == "Hello\nWorld!")

    Paths.get(path).toFile.delete
  }
}
