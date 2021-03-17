package com.barassolutions;

public class ThreadExpression extends Expression {

  /** The expression to return in the thread */
  private InExpression expression;

  public ThreadExpression(int line, InExpression expression) {
    super(line);
    this.expression = expression;
  }
  /**
   * Analyzing the raise block means analyzing its component.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    expression = (InExpression) expression.analyze(context);

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
    expression.codegen(output);
    output.space();
    output.token(TokenOz.END);
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ThreadExpression line=\"%d\">\n", line());
    p.indentRight();
    expression.writeToStdOut(p);
    p.indentLeft();
    p.printf("</ThreadExpression>\n");
  }
}
