package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;

public class ThisExpression extends Expression {

  public ThisExpression(int line) {
    super(line);
  }

  @Override
  public Expression analyze(Context context) {
    this.type = Type.OBJECT;
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.SELF);
    output.space();
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ThisExpression line=\"%d\">", line());
  }
}
