package dev.learn.handsonscala.excercises.chapter03

import dev.learn.handsonscala.excercises.common.Exercise

case class Msg(id: Int, parent: Option[Int], txt: String) {}

object ExercisePrintMessages extends Exercise {
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
