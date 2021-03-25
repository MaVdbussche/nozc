package com.barassolutions;

public class ReturnStatement extends Statement {

  /**
   * The returned expression.
   */
  private Expression expr;

  /**
   * Construct an AST node for a return-statement given its line number, and the expression that is
   * returned.
   *
   * @param line line in which the return-statement appears in the source file.
   * @param expr the returned expression.
   */
  public ReturnStatement(int line, Expression expr) {
    super(line);
    this.expr = expr;
  }

  /**
   * Analyze the expression and check types. Determine the (possibly void) return type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context context) {
    MethodContext methodContext = context.findMethodContext();

    Type returnType = methodContext.returnType();
    if (expr != null) {
      if (returnType == Type.ANY) {
        // Can't return a value from void method
        interStatement.reportSemanticError(line(),
            "cannot return a value from a procedure");
      } else {
        // There's a (non-void) return expression.
        // Its type must match the return type of the
        // method
        expr = expr.analyze(context);
        expr.type().mustMatchExpected(line(), returnType);
      }
    } else {
      interStatement.reportSemanticError(line(),
          "missing return value for function");
    }
    return this;
}

  /**
   * Generate code for the return statement. In the case of procedures, generate a simple (void)
   * return. In the case of a return expression, generate code for the appropriate return
   * instruction.
   *
   * @param output the code emitter (basically an abstraction for producing the Oz file).
   */
  @Override
  public void codegen(Emitter output) {
    if (expr == null) {
      //Nothing to do in Oz
    } else {
      expr.codegen(output);
      //Nothing else to do in Oz
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    if (expr != null) {
      p.printf("<ReturnStatement line=\"%d\">\n", line());
      p.indentRight();
      expr.writeToStdOut(p);
      p.indentLeft();
      p.printf("</ReturnStatement>\n");
    } else {
      p.printf("<ReturnStatement line=\"%d\"/>\n", line());
    }
  }
}
