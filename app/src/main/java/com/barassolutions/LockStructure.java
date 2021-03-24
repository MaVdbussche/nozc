package com.barassolutions;

import org.jetbrains.annotations.Nullable;

public class LockStructure extends Statement {

  /**
   * The optional expression to lock onto.
   */
  @Nullable
  private Expression expression; //TODO not nullable in Oz => create a new one on the spot ?

  /**
   * the statement to execute when the lock is available.
   */
  private InStatement statement;

  public LockStructure(int line, @Nullable Expression expression, InStatement statement) {
    super(line);
    this.expression = expression;
    this.statement = statement;
  }

  /**
   * Analyzing the raise block means analyzing its components.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context context) {
    if (expression != null) {
      expression = (Expression) expression.analyze(context);
    } else {
      //TODO
    }
    statement = (InStatement) statement.analyze(context);

    return this;
  }

  /**
   * Perform code generation for this AST.
   *
   * @param output the code emitter (basically an abstraction for producing the oz file).
   */
  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.LOCK);
    if (expression != null) {
      output.space();
      expression.codegen(output);
      output.space();
      output.token(TokenOz.THEN);
    } else {
      //TODO not allowed in Oz
    }
    output.space();
    statement.codegen(output);
    output.space();
    output.token(TokenOz.END);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<LockStatement line=\"%d\">\n", line());
    p.indentRight();

    if (expression != null) {
      p.printf("<Expression>\n");
      p.indentRight();
      expression.writeToStdOut(p);
      p.indentLeft();
      p.printf("</Expression>\n");
    } else {
      //TODO not allowed in Oz
    }

    p.printf("<Statement>\n");
    p.indentRight();
    statement.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Statement>\n");

    p.indentLeft();
    p.printf("</LockStatement>\n");
  }
}
