package com.barassolutions;

/**
 * Most binary expressions that return booleans can be recognized by their syntax. We take advantage
 * of this to define a common codegen().
 */
public abstract class BooleanBinaryExpression extends BinaryExpression {

  /**
   * Construct an AST node for a boolean binary expression.
   *
   * @param line     line in which the boolean binary expression occurs in the source file.
   * @param operator the boolean binary operator.
   * @param lhs      lhs operand.
   * @param rhs      rhs operand.
   */
  protected BooleanBinaryExpression(int line, String operator, Expression lhs, Expression rhs) {
    super(line, operator, lhs, rhs);
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    switch (operator) {
      case "&&" -> output.token(TokenOz.ANDTHEN);
      case "||" -> output.token(TokenOz.ORELSE);
      case "==" -> output.token(TokenOz.EQUAL);
      case ">" -> output.token(TokenOz.GT);
      case ">=" -> output.token(TokenOz.GE);
      case "<" -> output.token(TokenOz.LT);
      case "<=" -> output.token(TokenOz.LE);
    }
    output.space();
    rhs.codegen(output);
  }
}

/**
 * The AST node for a logical AND (&&) expression.
 */
class OperationLogicalAnd extends BooleanBinaryExpression {

  /**
   * Construct an AST node for a logical AND expression given its line number, and lhs and rhs
   * operands.
   *
   * @param line line in which the logical AND expression occurs in the source file.
   * @param lhs  lhs operand.
   * @param rhs  rhs operand.
   */
  public OperationLogicalAnd(int line, Expression lhs, Expression rhs) {
    super(line, "&&", lhs, rhs);
  }

  /**
   * Analyzing a logical AND expression involves analyzing its operands and insuring they are
   * boolean; the result type is of course boolean.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = (Expression) lhs.analyze(context);
    rhs = (Expression) rhs.analyze(context);
    lhs.type().mustMatchExpected(line(), Type.BOOLEAN);
    rhs.type().mustMatchExpected(line(), Type.BOOLEAN);
    type = Type.BOOLEAN;
    return this;
  }
}

/**
 * The AST node for a logical equality (==) expression.
 */
class OperationLogicalEqual extends BooleanBinaryExpression {

  /**
   * Construct an AST node for a logical EQUAL expression given its line number, and lhs and rhs
   * operands.
   *
   * @param line line in which the logical EQUAL expression occurs in the source file.
   * @param lhs  lhs operand.
   * @param rhs  rhs operand.
   */
  public OperationLogicalEqual(int line, Expression lhs, Expression rhs) {
    super(line, "==", lhs, rhs);
  }

  /**
   * Analyzing a logical EQUAL expression involves analyzing its operands and insuring they are
   * boolean; the result type is of course boolean.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = (Expression) lhs.analyze(context);
    rhs = (Expression) rhs.analyze(context);
    lhs.type().mustMatchExpected(line(), Type.BOOLEAN);
    rhs.type().mustMatchExpected(line(), Type.BOOLEAN);
    type = Type.BOOLEAN;
    return this;
  }
}

/**
 * The AST node for a logical OR (||) expression.
 */
class OperationLogicalOr extends BooleanBinaryExpression {

  /**
   * Construct an AST node for a logical OR expression given its line number, and lhs and rhs
   * operands.
   *
   * @param line line in which the logical OR expression occurs in the source file.
   * @param lhs  lhs operand.
   * @param rhs  rhs operand.
   */
  public OperationLogicalOr(int line, Expression lhs, Expression rhs) {
    super(line, "||", lhs, rhs);
  }

  /**
   * Analyzing a logical OR expression involves analyzing its operands and insuring they are
   * boolean; the result type is of course boolean.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = (Expression) lhs.analyze(context);
    rhs = (Expression) rhs.analyze(context);
    lhs.type().mustMatchExpected(line(), Type.BOOLEAN);
    rhs.type().mustMatchExpected(line(), Type.BOOLEAN);
    type = Type.BOOLEAN;
    return this;
  }
}
