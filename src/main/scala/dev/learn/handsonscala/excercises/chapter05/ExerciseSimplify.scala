package dev.learn.handsonscala.excercises.chapter05

import dev.learn.handsonscala.excercises.common.Exercise

sealed trait Expr
case class BinOp(left: Expr, op: String, right: Expr) extends Expr
case class Literal(value: Int) extends Expr
case class Variable(name: String, sign: String = "") extends Expr

object ExerciseSimplify extends Exercise {

  def stringify(expr: Expr): String =
    expr match {
      case BinOp(left, op, right) =>
        s"(${stringify(left)} $op ${stringify(right)})"
      case Literal(value)       => value.toString
      case Variable(name, sign) => s"$sign$name"
    }

  // limited to binomials. anything that results in a trinom or higher is impossible.
  // e.g. (x + y) * (z + 2) => xz + 2yz + 2y
  def simplify(expr: Expr): Expr =
    expr match {
      case Variable(_, _) | Literal(_) => expr

      case BinOp(left: Literal, op, right: Literal) =>
        Literal(
          op match {
            case "+" => left.value + right.value
            case "-" => left.value - right.value
            case "*" => left.value * right.value
          }
        )

      case BinOp(left: Variable, op, right: Variable) =>
        op match {
          case "*" =>
            Variable(
              left.name + right.name,
              if ((left.sign + right.sign).length == 1) "-" else ""
            )
          case "-" =>
            BinOp(
              left,
              if (right.sign == "-") "+" else "-",
              Variable(right.name)
            )
          case _ => expr
        }

      case BinOp(_, "*", Literal(0)) | BinOp(Literal(0), "*", _) => Literal(0)
      case BinOp(Literal(1), "*", right)                         => simplify(right)
      case BinOp(left, "*", Literal(1))                          => simplify(left)

      case BinOp(Literal(0), "-", right) =>
        right match {
          case Variable(name, "")  => Variable(name, "-")
          case Variable(name, "-") => Variable(name)
          case _                   => simplify(BinOp(Literal(0), "-", simplify(right)))
        }
      case BinOp(Literal(0), _, right) => simplify(right)
      case BinOp(left, _, Literal(0))  => simplify(left)

      case BinOp(literal: Literal, op, variable: Variable) => {
        op match {
          case "*" => literalTimesVariable(literal, variable)
          case _   => expr
        }
      }
      case BinOp(variable: Variable, op, literal: Literal) => {
        op match {
          case "*" => literalTimesVariable(literal, variable)
          case _   => expr
        }
      }

      case BinOp(
            literal: Literal,
            "*",
            BinOp(left: Variable, op, right: Variable)
          ) =>
        simplify(
          BinOp(
            BinOp(literal, "*", left),
            op,
            BinOp(literal, "*", right)
          )
        )
      case BinOp(
            BinOp(left: Variable, op, right: Variable),
            "*",
            literal: Literal
          ) =>
        simplify(
          BinOp(
            BinOp(literal, "*", left),
            op,
            BinOp(literal, "*", right)
          )
        )

      case BinOp(left, op, right) =>
        simplify(BinOp(simplify(left), op, simplify(right)))
    }

  private def literalTimesVariable(
      literal: Literal,
      variable: Variable
  ): Expr = {
    val sign = (literal.value, variable.sign) match {
      case (value, "")  => if (value < 0) "-" else ""
      case (value, "-") => if (value < 0) "" else "-"
    }

    literal.value match {
      case 1 | -1 => Variable(variable.name, sign)
      case 0      => Literal(0)
      case _      => Variable(s"${literal.value.abs}${variable.name}", sign)
    }
  }
}
