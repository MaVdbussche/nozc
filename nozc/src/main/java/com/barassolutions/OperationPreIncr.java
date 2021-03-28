package com.barassolutions;

/**
 * The AST node for a ++expr expression. //TODO will not be supported in the first release
 */
public class OperationPreIncr extends UnaryExpression {

  /**
   * Construct an AST node for a ++expr given its line number, and the
   * operand.
   *
   * @param line
   *            line in which the expression occurs in the source file.
   * @param arg
   *            the operand.
   */
  public OperationPreIncr(int line, Expression arg) {
    super(line, "++pre", arg);
  }

  /**
   * Analyze the operand as a lhs (since there is a side effect), check types
   * and determine the type of the result.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    arg = arg.analyze(context);
    arg.type().mustMatchExpected(line(), Type.INT, Type.FLOAT);
    type = arg.type;

    return this;
  }

  @Override
  public void codegen(Emitter output) {

  }
}
