package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;

public class OperationAssignment extends Expression {

  /**
   * Left-hand side.
   */
  Expression lhs;

  /**
   * Right-hand side
   */
  Expression rhs;

  public OperationAssignment(int line, Expression lhs, Expression rhs) {
    super(line);
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public Expression analyze(Context context) {
    lhs = (Expression) lhs.analyze(context);
    rhs = (Expression) rhs.analyze(context);

    if (!lhs.type().equals(rhs.type())) {
      AST.interStatement
          .reportSemanticError(line, "Incompatible types : trying to assign %s to a %s value.",
              lhs.type().toString(), rhs.type().toString());
    }

    //TODO we need to check that lhs is not a VAL (except if it was not assigned before, in which case it's ok)
    //TODO refresh the type and value stored in the variable object
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    output.token(TokenOz.DEFINE);
    output.space();
    rhs.codegen(output);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<OperationAssignment>\n");
    p.indentRight();

    p.printf("<Left side>");
    lhs.writeToStdOut(p);
    p.printf("</Left side>");
    p.printf("=");
    p.printf("<Right side>");
    rhs.writeToStdOut(p);
    p.printf("</Right side>");

    p.indentLeft();
    p.printf("</OperationAssignment>\n");
  }
}
