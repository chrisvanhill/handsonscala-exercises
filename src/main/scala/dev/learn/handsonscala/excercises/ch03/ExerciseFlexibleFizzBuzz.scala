package dev.learn.handsonscala.excercises.ch03

import dev.learn.handsonscala.excercises.common.Exercise

object ExerciseFlexibleFizzBuzz extends Exercise {
  def flexibleFizzBuzz(cb: String => Unit): Unit = {
    Range
      .inclusive(1, 100)
      .foreach(i =>
        cb(
          if (i % 3 == 0 && i % 5 == 0) "FizzBuzz"
          else if (i % 3 == 0) "Fizz"
          else if (i % 5 == 0) "Buzz"
          else i.toString
        )
      )
  }

  override def run(): Unit = {
    flexibleFizzBuzz(s => {} /* do nothing @ var i = 0 */ )
    flexibleFizzBuzz(s => println(s))

    var i = 0
    val output = new Array[String](100)
    flexibleFizzBuzz { s =>
      output(i) = s
      i += 1
    }
    println(output.mkString("Array(", ", ", ")"))
  }
}
