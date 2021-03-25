package com.barassolutions;

public class OperationStringConcatenation extends BinaryExpression {

  /**
   * Construct an AST node for a string concatenation expression given its
   * line number, and the lhs and rhs operands. An expression of this sort is
   * created during the analysis of a (overloaded) + operation (and not by the
   * Parser).
   *
   * @param line
   *            line in which the expression occurs in the source file.
   * @param lhs
   *            lhs operand.
   * @param rhs
   *            rhs operand.
   */
  public OperationStringConcatenation(int line, Expression lhs, Expression rhs) {
    super(line, "+", lhs, rhs);
  }

  /**
   * Analysis is simple here. The operands have already been analyzed (in
   * <code>OperationPlus</code>) so we simply set the result type.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    type = Type.STRING;
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.token(TokenOz.HASHTAG);
    rhs.codegen(output);
  }
}
