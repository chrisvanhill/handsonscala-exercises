package dev.learn.handsonscala.excercises.ch03

import dev.learn.handsonscala.excercises.common.Exercise

case class Msg(id: Int, parent: Option[Int], txt: String) {}

object ExercisePrintMessages extends Exercise {
  def run(): Unit = {
    printMessages(
      Array(
        Msg(6, Some(5), "Here me moo, moo"),
        Msg(6, Some(5), "Here me moo, moo"),
        Msg(2, None, "I am Cow"),
        Msg(3, Some(2), "Hear me moo"),
        Msg(4, Some(2), "Here I stand"),
        Msg(0, None, "Hello"),
        Msg(1, Some(0), "World"),
        Msg(5, Some(2), "I am Cow")
      )
    )
  }

  def printMessages(messages: Array[Msg]): Unit = {
    printOrdered(None, messages)
  }

  def printOrdered(
      parent: Option[Msg],
      messages: Array[Msg],
      indent: Int = 0
  ): Unit = {
    for (Msg(id, _, txt) <- parent) {
      println("  " * indent + s"#$id $txt")
    }

    messages
      .filter(withSameParent(parent))
      .sortBy(_.id)
      .foreach(msg => {
        printOrdered(Some(msg), messages, indent + 1)
      })
  }

  private def withSameParent(parentMsg: Option[Msg])(msg: Msg): Boolean = {
    parentMsg match {
      case Some(parentMsg) => msg.parent.contains(parentMsg.id)
      case None            => msg.parent.isEmpty
    }
  }
}
