package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;

public class AnonymExpression extends Expression {

  public AnonymExpression(int line) {
    super(line);
  }

  @Override
  public Expression analyze(Context context) {
    this.type = Type.ANY;
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.DOLLAR);
    output.space();
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<AnonymExpression line=\"%d\">", line());
  }
}
