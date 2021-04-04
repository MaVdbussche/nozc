package com.barassolutions;

public class RaiseStructure extends Statement {

  /**
   * The expression to raise.
   */
  private Expression expression;

  public RaiseStructure(int line, Expression expression) {
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
  public AST analyze(Context context) {
    expression = expression.analyze(context);

    return this;
  }

  /**
   * Perform code generation for this AST.
   *
   * @param output the code emitter (basically an abstraction for producing the oz file).
   */
  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.RAISE);
    output.space();
    expression.codegen(output);
    output.space();
    output.token(TokenOz.END);
    output.newLine();
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<RaiseStatement line=\"%d\">\n", line());
    p.indentRight();
    expression.writeToStdOut(p);
    p.indentLeft();
    p.printf("</RaiseStatement>\n");
  }
}
