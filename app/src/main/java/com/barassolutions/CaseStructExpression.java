package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class CaseStructExpression extends Expression {


  /**
   * Expression to be matched against.
   */
  private Expression expression;

  /**
   * Clauses to test against <code>expression</code>
   */
  private final ArrayList<CaseExpressionClause> clauses;

  /**
   * Optional, default clause
   */
  private InExpression defaultExpression;

  public CaseStructExpression(int line, Expression expression, ArrayList<CaseExpressionClause> clauses,
      @Nullable InExpression defaultExpression) {
    super(line);
    this.expression = expression;
    this.clauses = clauses;
    this.defaultExpression = defaultExpression;
  }

  /**
   * Analyzing the pattern-matching block means analyzing its components.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    expression = expression.analyze(context);

    clauses.forEach(c -> c = (CaseExpressionClause) c.analyze(context));

    defaultExpression = (InExpression) defaultExpression.analyze(context);
    return this;
  }

  /**
   * Perform code generation for this AST.
   *
   * @param output the code emitter (basically an abstraction for producing the oz file).
   */
  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.CASE);
    output.space();
    expression.codegen(output);
    output.space();
    output.token(TokenOz.OF);
    output.newLine();
    output.indentRight();
    clauses.forEach(c -> c.codegen(output));
    if (defaultExpression != null) {
      output.token(TokenOz.ELSE);
      defaultExpression.codegen(output);
      output.newLine();
    }
    output.token(TokenOz.END);
    output.newLine();
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<CaseExpression line=\"%d\">\n", line());
    p.indentRight();

    p.printf("<Expression>\n");
    p.indentRight();
    expression.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Expression>\n");

    clauses.forEach(c -> c.writeToStdOut(p));

    if (defaultExpression != null) {
      p.printf("<Statement>\n");
      p.indentRight();
      defaultExpression.writeToStdOut(p);
      p.indentLeft();
      p.printf("</Statement>\n");
    }
    p.indentLeft();
    p.printf("</CaseExpression>\n");
  }
}
