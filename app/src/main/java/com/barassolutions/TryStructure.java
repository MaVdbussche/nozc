package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class TryStructure extends Statement {

  /**
   * The statement to try and execute.
   */
  private InStatement statement;

  /**
   * The clauses in the "catch" block.
   */
  private final ArrayList<CaseStatementClause> clauses;

  /**
   * The optional "finally" statement.
   */
  @Nullable
  private InStatement finallyStatement;

  public TryStructure(int line, InStatement statement, ArrayList<CaseStatementClause> clauses,
      @Nullable InStatement statement2) {
    super(line);
    this.statement = statement;
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
  public AST analyze(Context context) {
    statement = (InStatement) statement.analyze(context);

    clauses.forEach(c -> c = (CaseStatementClause) c.analyze(context));

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
    statement.codegen(output);
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
    p.printf("<TryStatement line=\"%d\">\n", line());
    p.indentRight();

    p.printf("<Statement>\n");
    p.indentRight();
    statement.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Statement>\n");

    clauses.forEach(c -> c.writeToStdOut(p));

    if (finallyStatement != null) {
      p.printf("<Statement>\n");
      p.indentRight();
      finallyStatement.writeToStdOut(p);
      p.indentLeft();
      p.printf("</Statement>\n");
    }
    p.indentLeft();
    p.printf("</TryStatement>\n");
  }
}
