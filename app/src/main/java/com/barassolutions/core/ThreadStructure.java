package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;

public class ThreadStructure extends Statement {

  /** The statement to execute in the thread */
  private InStatement statement;

  public ThreadStructure(int line, InStatement statement) {
    super(line);
    this.statement = statement;
  }
  /**
   * Analyzing the raise block means analyzing its component.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context context) {
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
    output.token(TokenOz.THREAD);
    output.space();
    statement.codegen(output);
    output.space();
    output.token(TokenOz.END);
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ThreadStatement line=\"%d\">\n", line());
    p.indentRight();
    statement.writeToStdOut(p);
    p.indentLeft();
    p.printf("</ThreadStatement>\n");
  }
}
