package com.barassolutions;

import org.jetbrains.annotations.NotNull;

/**
 * A simple parenthesized expression
 */
public class ParExpression extends Expression {

  @NotNull
  private Expression expression;

  /**
   * Construct an AST node for a parenthesized expression given its line number, and the enclosed
   * expression.
   *
   * @param line       line in which the block occurs in the source file.
   * @param expression expression present in the body.
   */
  public ParExpression(int line, @NotNull Expression expression) {
    super(line);
    this.expression = expression;
  }

  /**
   * Analyzing a parenthesized expression consists analyzing the expression in the current context
   * and applying its type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    expression = expression.analyze(context);
    this.type = expression.type();

    return this;
  }

  /**
   * Generating code for a parenthesized expression consists of generating code for the parentheses
   * around the expression.
   *
   * @param output the code emitter (basically an abstraction for producing the Oz file).
   */
  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.LPAREN);
    expression.codegen(output);
    output.token(TokenOz.RPAREN);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    expression.writeToStdOut(p);
  }
}
