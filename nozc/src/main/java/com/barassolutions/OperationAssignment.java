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
    this.lhs = (Variable) lhs;
    this.rhs = rhs;
    if (rhs instanceof FunctorDefAnonym fda) {
      fda.setName(this.lhs.name());
    } else if (rhs instanceof ClassDefAnonym cda) {
      cda.setName(this.lhs.name());
    } else if (rhs instanceof FunctionDefAnonym fda) {
      fda.setName(this.lhs.name());
    } else if (rhs instanceof ProcedureDefAnonym pda) {
      pda.setName(this.lhs.name()); //Store the name of anonym definitions in them directly
    }
  }

  @Override
  public Expression analyze(Context context) {
    lhs = (Variable) lhs.analyze(context);
    rhs = rhs.analyze(context);

    lhs.tryAssign(rhs);

    //if (!lhs.type().equals(rhs.type())) {
    //  interStatement.reportSemanticError(line(),
    //      "Incompatible types : trying to assign %s to a %s value.",
    //      lhs.type().toString(), rhs.type().toString());
    //} //Actually we don't care, we already checked that lhs is not a val

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    if (lhs.isConstant()) {
      output.token(TokenOz.ASSIGN);
    } else {
      output.token(TokenOz.DEFINE);
    }
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
