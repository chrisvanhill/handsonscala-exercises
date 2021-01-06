package dev.learn.handsonscala.helpers

import java.io.ByteArrayOutputStream

object OutputHelpers {
  def assertOutputLines(expected: Int)(test: => Any): Unit = {
    val baos = new ByteArrayOutputStream()
    Console.withOut(baos)(test)
    assert(expected == baos.toString.split("\n").length)
    baos.close()
  }
}
