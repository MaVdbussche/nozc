package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import com.barassolutions.Utils;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class CaseStructure extends Statement {

  /**
   * Expression to be matched against.
   */
  private Expression expression;

  /**
   * Clauses to test against <code>expression</code>
   */
  private ArrayList<CaseStatementClause> clauses;

  /**
   * Optional, default clause
   */
  private InStatement defaultStatement;

  public CaseStructure(int line, Expression expression, ArrayList<CaseStatementClause> clauses,
      @Nullable InStatement statement) {
    super(line);
    this.expression = expression;
    this.clauses = clauses;
    this.defaultStatement = statement;
  }

  /**
   * Analyzing the pattern-matching block means analyzing its components.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context context) {
    expression = (Expression) expression.analyze(context);

    clauses.forEach(c -> c = (CaseStatementClause) c.analyze(context));

    defaultStatement = (InStatement) defaultStatement.analyze(context);
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
    output.newLine();
    clauses.forEach(c -> c.codegen(output));
    if (defaultStatement!=null) {
      output.token(TokenOz.ELSE);
      defaultStatement.codegen(output);
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
    p.printf("<CaseStatement line=\"%d\">\n", line());
    p.indentRight();

    p.printf("<Expression>\n");
    p.indentRight();
    expression.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Expression>\n");

    clauses.forEach(c -> c.writeToStdOut(p));

    if (defaultStatement != null) {
      p.printf("<Statement>\n");
      p.indentRight();
      defaultStatement.writeToStdOut(p);
      p.indentLeft();
      p.printf("</Statement>\n");
    }
    p.indentLeft();
    p.printf("</CaseStatement>\n");
  }
}
