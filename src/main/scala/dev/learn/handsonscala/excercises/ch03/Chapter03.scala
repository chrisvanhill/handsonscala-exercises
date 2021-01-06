package dev.learn.handsonscala.excercises.ch03

import dev.learn.handsonscala.excercises.common.Chapter

object Chapter03 extends Chapter {
  override def exercises(): Unit = {
    ExerciseFlexibleFizzBuzz.run()
    ExercisePrintMessages.run()
    ExerciseContextManagers.run()
  }
}
