package com.barassolutions;

/**
 * This class represents exactly the same structure as <code>RaiseStructure</code>, but it used in places where an Expression is expected.
 *
 * @see RaiseStructure
 */
public class RaiseExpression extends Expression {

  /**
   * The expression to raise.
   */
  private Expression expression;

  public RaiseExpression(int line, Expression expression) {
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
    expression = (Expression) expression.analyze(context);

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
    p.printf("<RaiseStatement(expression) line=\"%d\">\n", line());
    p.indentRight();
    expression.writeToStdOut(p);
    p.indentLeft();
    p.printf("</RaiseStatement(expression)>\n");
  }
}
