package com.barassolutions;

public class OperationAssignment extends Expression {

  /**
   * Left-hand side.
   */
  Variable lhs;

  /**
   * Right-hand side
   */
  Expression rhs;

  public OperationAssignment(int line, Expression lhs, Expression rhs) {
    super(line);
    this.lhs = (Variable) lhs; //TODO cast probably won't work
    this.rhs = rhs;
    if (rhs instanceof FunctorDefAnonym || rhs instanceof ClassDefAnonym || rhs instanceof FunctionDefAnonym || rhs instanceof ProcedureDefAnonym) {
      rhs.name = this.lhs.name(); //Store the name of anonym definitions in them directly
    }
  }

  @Override
  public Expression analyze(Context context) {
    lhs = (Variable) lhs.analyze(context);
    rhs = (Expression) rhs.analyze(context);

    lhs.tryAssign(rhs);

    if (!lhs.type().equals(rhs.type())) {
      interStatement.reportSemanticError(line(),
          "Incompatible types : trying to assign %s to a %s value.",
          lhs.type().toString(), rhs.type().toString());
    } //TODO actually we don't care !?

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    if (lhs.isConstant()) {
      output.token(TokenOz.ASSIGN);
      output.space();
      rhs.codegen(output);
    } else {
      output.token(TokenOz.DEFINE);
      output.space();
      output.token(TokenOz.COMMERCAT);
      output.token(TokenOz.LPAREN);
      rhs.codegen(output);
      output.token(TokenOz.RPAREN);
    }
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
