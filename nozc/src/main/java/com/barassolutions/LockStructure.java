package com.barassolutions;

import org.jetbrains.annotations.Nullable;

public class LockStructure extends Statement {

  /**
   * The optional expression to lock onto.
   */
  @Nullable
  private Expression expression; //Not nullable in Oz => create a new one on the spot ?

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
      expression = expression.analyze(context);
    } else {
      //Nothing to do, we will generate a lock at codegen
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
    output.space();
    if (expression != null) {
      expression.codegen(output);
    } else {
      output.literal("{NewLock _}");
    }
    output.space();
    output.token(TokenOz.THEN);
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
      p.printf("<Auto-generated lock/>\n");
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