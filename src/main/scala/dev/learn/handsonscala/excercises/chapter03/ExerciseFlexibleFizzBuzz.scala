package dev.learn.handsonscala.excercises.chapter03

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
}
