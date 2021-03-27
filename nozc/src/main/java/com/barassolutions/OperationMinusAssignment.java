package com.barassolutions;

public class OperationMinusAssignment extends OperationAssignment {

  public OperationMinusAssignment(int line, Expression lhs, Expression rhs) {
    super(line, lhs, rhs);
  }

  @Override
  public Expression analyze(Context context) {
    lhs = (Variable) lhs.analyze(context);
    rhs = (Expression) rhs.analyze(context);

    lhs.tryMinusAssign(rhs);

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    output.token(TokenOz.DEFINE);
    output.space();
    output.token(TokenOz.COMMERCAT);
    lhs.codegen(output);
    output.token(TokenOz.MINUS);
    rhs.codegen(output);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<OperationPlusAssignment>\n");
    p.indentRight();

    p.printf("<Left side>");
    lhs.writeToStdOut(p);
    p.printf("</Left side>");
    p.printf("+=");
    p.printf("<Right side>");
    rhs.writeToStdOut(p);
    p.printf("</Right side>");

    p.indentLeft();
    p.printf("</OperationPlusAssignment>\n");
  }
}
