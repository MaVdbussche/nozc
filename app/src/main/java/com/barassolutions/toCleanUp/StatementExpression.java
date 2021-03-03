package com.barassolutions.toCleanUp;

import com.barassolutions.Emitter;
import com.barassolutions.core.Expression;

/**
 * The AST node for an expression that appears as a statement. Only the
 * expressions that have a side-effect are valid statement expressions.
 */
class StatementExpression extends AST {

  /** The expression. */
  Expression expr;

  /**
   * Construct an AST node for a statement expression given its line number,
   * and expression.
   *
   * @param line
   *            line in which the expression occurs in the source file.
   * @param expr
   *            the expression.
   */
  public StatementExpression(int line, Expression expr) {
    super(line);
    this.expr = expr;
  }

  /**
   * Analysis involves analyzing the encapsulated expression if indeed it is a
   * statement expression, i.e., one with a side effect.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  public Statement analyze(Context context) {
    if (expr.isStatementExpression) {
      expr = expr.analyze(context);
    }
    return this;
  }

  /**
   * Generating code for the statement expression involves simply generating
   * code for the encapsulated expression.
   *
   * @param output
   *            the code emitter (basically an abstraction for producing the
   *            .oz file).
   */
  public void codegen(Emitter output) {
    expr.codegen(output);
  }

  /**
   * @inheritDoc
   */
  public void writeOut(CustomPrinter p) {
    p.printf("<JStatementExpression line=\"%d\">\n", line());
    p.indentRight();
    expr.writeOut(p);
    p.indentLeft();
    p.printf("</JStatementExpression>\n");
  }
}
