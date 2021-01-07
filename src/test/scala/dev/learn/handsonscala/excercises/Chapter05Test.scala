package dev.learn.handsonscala.excercises

import dev.learn.handsonscala.excercises.chapter05.ExerciseSimplify._
import dev.learn.handsonscala.excercises.chapter05._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

class Chapter05Test extends AnyFunSuite {
  test("5.7 - Simplify") {
    val example1 = BinOp(Literal(1), "+", Literal(1))
    stringify(simplify(example1)) should equal("2")

    val example2 = BinOp(BinOp(Literal(1), "+", Literal(1)), "*", Variable("x"))
    stringify(simplify(example2)) should equal("2x")

    val example3 = BinOp(
      BinOp(Literal(2), "-", Literal(1)),
      "*",
      Variable("x")
    )
    stringify(simplify(example3)) should equal("x")

    val example4 = BinOp(
      BinOp(BinOp(Literal(1), "+", (Literal(1))), "*", Variable("y")),
      "+",
      BinOp(BinOp(Literal(1), "-", (Literal(1))), "*", Variable("x"))
    )
    stringify(simplify(example4)) should equal("2y")

    val example5 =
      BinOp(BinOp(Literal(1), "-", (Literal(1))), "-", Variable("y"))
    stringify(simplify(example5)) should equal("-y")

    val example6 =
      BinOp(Literal(-2), "*", BinOp(Variable("x"), "-", Variable("y")))
    stringify(simplify(example6)) should equal("(-2x + 2y)")
  }
}
