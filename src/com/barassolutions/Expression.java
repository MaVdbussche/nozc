package com.barassolutions;

/**
 * The AST node for an expression. The syntax says all expressions are
 * statements, but a semantic check throws some (those without a side-effect)
 * out.
 *
 * Every expression has a type and a flag saying whether or not it's a
 * statement-expression.
 */
abstract class Expression extends Statement {

  /** Expression type. */
  protected Type type;

  /** Whether or not this expression is a statement. */
  protected boolean isStatementExpression;

  /**
   * Construct an AST node for an expression given its line number.
   *
   * @param line
   *            line in which the expression occurs in the source file.
   */
  protected Expression(int line) {
    super(line);
    isStatementExpression = false; // by default
  }

  /**
   * Return the expression type.
   *
   * @return the expression type.
   */
  public Type type() {
    return type;
  }

  /**
   * Is this a statementExpression?
   *
   * @return whether or not this is being used as a statement.
   */
  public boolean isStatementExpression() {
    return isStatementExpression;
  }

  /**
   * The analysis of any Expression returns an Expression. That's all this
   * (re-)declaration of analyze() says.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  public abstract Expression analyze(Context context);

  /**
   * Perform (short-circuit) code generation for a boolean expression, given
   * the code emitter, a target label, and whether we branch to that label on
   * true or on false.
   *
   * @param output
   *            the code emitter (basically an abstraction for producing the
   *            .oz file).
   * @param targetLabel
   *            the label to which we should branch.
   * @param onTrue
   *            do we branch on true?
   */
  public void codegen(Emitter output, String targetLabel, boolean onTrue) {
    // We should never reach here, i.e., all boolean
    // (including
    // identifier) expressions must override this method.
    System.err.println("Error in code generation");
  }
}
