package dev.learn.handsonscala.excercises.chapter04

import dev.learn.handsonscala.excercises.common.Exercise

object ExerciseRenderSudoku extends Exercise {
  val borderRow = "+-------+-------+-------+\n"

  def renderSudoku(grid: Array[Array[Int]]): String = {
    grid.indices.foldLeft(borderRow) { (output, i) =>
      output + renderRow(grid(i)) + (if (i % 3 == 2) borderRow else "")
    }
  }

  private def renderRow(row: Array[Int]): String = {
    row
      .map(v => if (v == 0) " " else v.toString)
      .grouped(3)
      .map(_.mkString(" "))
      .mkString("| ", " | ", " |\n")
  }
}
