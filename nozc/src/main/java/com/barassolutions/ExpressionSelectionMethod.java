package com.barassolutions;

import java.util.ArrayList;

public class ExpressionSelectionMethod extends Expression {

  private final Variable instance;

  private final String methodName;

  private final ArrayList<Expression> args;

  public ExpressionSelectionMethod(int line, Variable var, String methodName,
      ArrayList<Expression> args) {
    super(line);
    this.instance = var;
    this.methodName = methodName;
    this.args = args;
  }

  @Override
  public Expression analyze(Context context) {
    interStatement.reportSemanticError(line(),
        "Method calls on an instance from outside a class context : this is not supported in this release.");
    //TODO to implement in future release

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    //TODO to implement in future release
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<MethodSelector line=\"%d\" name=\"%s\" target=\"%s\">\n", line(),
        methodName, instance);

    p.println("<Arguments>");
    for (Expression argument : args) {
      p.indentRight();
      p.println("<Argument>");
      p.indentRight();
      argument.writeToStdOut(p);
      p.indentLeft();
      p.println("</Argument>");
      p.indentLeft();
    }
    p.println("</Arguments>");

    p.printf("</MethodSelector>\n");
  }
}
