package dev.learn.handsonscala.excercises.ch03

import dev.learn.handsonscala.excercises.common.Exercise

import java.io.{BufferedReader, BufferedWriter}
import java.nio.file.Files.{newBufferedReader, newBufferedWriter}
import java.nio.file.Paths

object ExerciseContextManagers extends Exercise {

  def withFileWriter(path: String)(write: BufferedWriter => Unit): Unit = {
    val writer = newBufferedWriter(Paths.get(path))
    try write(writer)
    finally writer.close()
  }

  def withFileReader(path: String)(read: BufferedReader => String): String = {
    val reader = newBufferedReader(Paths.get(path))
    try read(reader)
    finally reader.close()
  }

  override def run(): Unit = {
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
