package com.barassolutions;

public class ThisExpression extends Expression {

  public ThisExpression(int line) {
    super(line);
  }

  @Override
  public Expression analyze(Context context) {
    this.type = Type.ANY;
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.SELF);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ThisExpression line=\"%d\">", line());
  }
}
