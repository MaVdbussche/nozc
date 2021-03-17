package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class TryExpression extends Expression {

  /**
   * The expression to try and return.
   */
  private InExpression expression;

  /**
   * The clauses in the "catch" block.
   */
  private final ArrayList<CaseExpressionClause> clauses;

  /**
   * The optional "finally" statement.
   */
  @Nullable
  private InStatement finallyStatement;

  public TryExpression(int line, InExpression expression, ArrayList<CaseExpressionClause> clauses,
      @Nullable InStatement statement2) {
    super(line);
    this.expression = expression;
    this.clauses = clauses;
    this.finallyStatement = statement2;
  }

  /**
   * Analyzing the try..catch block means analyzing its components.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    expression = (InExpression) expression.analyze(context);

    clauses.forEach(c -> c = (CaseExpressionClause) c.analyze(context));

    if (finallyStatement != null) {
      finallyStatement = (InStatement) finallyStatement.analyze(context);
    }
    return this;
  }

  /**
   * Perform code generation for this AST.
   *
   * @param output the code emitter (basically an abstraction for producing the oz file).
   */
  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.TRY);
    output.space();
    expression.codegen(output);
    output.space();
    output.token(TokenOz.CATCH);
    output.newLine();
    output.indentRight();
    clauses.forEach(c -> c.codegen(output));
    output.indentLeft();
    if (finallyStatement != null) {
      output.token(TokenOz.FINALLY);
      finallyStatement.codegen(output);
    }
    output.token(TokenOz.END);
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<TryExpression line=\"%d\">\n", line());
    p.indentRight();

    p.printf("<Expression>\n");
    p.indentRight();
    expression.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Expression>\n");

    clauses.forEach(c -> c.writeToStdOut(p));

    if (finallyStatement != null) {
      p.printf("<Statement>\n");
      p.indentRight();
      finallyStatement.writeToStdOut(p);
      p.indentLeft();
      p.printf("</Statement>\n");
    }
    p.indentLeft();
    p.printf("</TryExpression>\n");
  }
}
