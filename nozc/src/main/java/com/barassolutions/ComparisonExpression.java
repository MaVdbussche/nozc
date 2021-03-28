package com.barassolutions;

/**
 * The AST node for a comparison expression. This class captures common aspects of comparison
 * operations.
 */
public abstract class ComparisonExpression extends BooleanBinaryExpression {

  /**
   * Create an AST node for a comparison expression.
   *
   * @param line     line in which the expression occurs in the source file.
   * @param operator the comparison operator.
   * @param lhs      the lhs operand.
   * @param rhs      the rhs operand.
   */
  protected ComparisonExpression(int line, String operator, Expression lhs, Expression rhs) {
    super(line, operator, lhs, rhs);
  }

  /**
   * The analysis of a comparison operation consists of analyzing its two operands, and making sure
   * they both have the same type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = (Expression) lhs.analyze(context);
    rhs = (Expression) rhs.analyze(context);
    if (lhs.type() == Type.INT) {
      rhs.type().mustMatchExpected(line(), Type.INT);
    } else if (lhs.type() == Type.FLOAT) {
      rhs.type().mustMatchExpected(line(), Type.FLOAT);
    } else if (lhs.type() == Type.BOOLEAN) {
      rhs.type().mustMatchExpected(line(), Type.BOOLEAN);
    }
    type = Type.BOOLEAN;
    return this;
  }
}

/**
 * The AST node for a greater-or-equal (>=) expression.
 */
class OperationGreaterEqual extends ComparisonExpression {

  /**
   * Construct an AST node for a greater-or-equal expression given its line number, and the lhs and
   * rhs operands.
   *
   * @param line line in which the greater-or-equal expression occurs in the source file.
   * @param lhs  lhs operand.
   * @param rhs  rhs operand.
   */
  public OperationGreaterEqual(int line, Expression lhs, Expression rhs) {
    super(line, ">=", lhs, rhs);
  }
}

/**
 * The AST node for a greater-than (>) expression.
 */
class OperationGreaterThan extends ComparisonExpression {

  /**
   * Construct an AST node for a greater-than expression given its line
   * number, and the lhs and rhs operands.
   *
   * @param line
   *            line in which the greater-than expression occurs in the source
   *            file.
   * @param lhs
   *            lhs operand.
   * @param rhs
   *            rhs operand.
   */
  public OperationGreaterThan(int line, Expression lhs, Expression rhs) {
    super(line, ">", lhs, rhs);
  }
}

/**
 * The AST node for a lesser-or-equal (<=) expression.
 */
class OperationSmallerEqual extends ComparisonExpression {

  /**
   * Construct an AST node for a lesser-or-equal expression given its line
   * number, and the lhs and rhs operands.
   *
   * @param line
   *            line in which the lesser-or-equal expression occurs in the source
   *            file.
   * @param lhs
   *            lhs operand.
   * @param rhs
   *            rhs operand.
   */
  public OperationSmallerEqual(int line, Expression lhs, Expression rhs) {
    super(line, "<=", lhs, rhs);
  }
}

/**
 * The AST node for a smaller-than (<) expression.
 */
class OperationSmallerThan extends ComparisonExpression {

  /**
   * Construct an AST node for a smaller-than expression given its line
   * number, and the lhs and rhs operands.
   *
   * @param line
   *            line in which the smaller-than expression occurs in the source
   *            file.
   * @param lhs
   *            lhs operand.
   * @param rhs
   *            rhs operand.
   */
  public OperationSmallerThan(int line, Expression lhs, Expression rhs) {
    super(line, "<", lhs, rhs);
  }
}
