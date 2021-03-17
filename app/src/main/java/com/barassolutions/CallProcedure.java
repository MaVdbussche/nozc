package com.barassolutions;

import java.util.ArrayList;

public class CallProcedure extends Statement {

  /**
   * The name of the procedure being called
   */
  private String name;

  /**
   * The arguments to the call.
   */
  private ArrayList<Expression> args;

  /**
   * Types of arguments.
   */
  private Type[] argTypes;

  public CallProcedure(int line, String name, ArrayList<Expression> args) {
    super(line);
    this.name = name;
    this.args = args;
  }

  /**
   * Analysis of a message expression involves: (1) analyzing and computing the types for the actual
   * arguments, (2) finding the appropriate Method in the context, (3) checking accessibility, and
   * (4) determining the result type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  //TODO we are always static here, method calls on object are in another class
  @Override
  public AST analyze(Context context) {
    // Analyzing the arguments
    argTypes = new Type[args.size()];
    for (int i = 0; i < argTypes.length; i++) {
      args.set(i, (Expression) args.get(i).analyze(context));
      argTypes[i] = args.get(i).type();
    }

    //Find appropriate method in the context, given the name and the nb of arguments.
    //We could check the type to allow overloading, but Oz does not allow so. Instead, it will produce an error at runtime
    Method method = context.methodFor(name, argTypes.length);
    if (method == null) {
      interStatement.reportSemanticError(line,
          "Could not find procedure for: " + Type.signatureFor(name, argTypes.length));
    }
    //No return type for procedures

    return this;
  }

  /**
   * Perform code generation for a call, given the code emitter.
   *
   * @param output the code emitter (basically an abstraction for producing the .oz file).
   */
  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.LCURLY);
    output.literal(name);
    args.forEach(a -> {
      output.space();
      a.codegen(output);
    });
    output.token(TokenOz.RCURLY);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<CallProcedureStatement line=\"%d\" name=\"%s\">\n", line(),
        name);
    p.indentRight();

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

    p.indentLeft();
    p.println("</CallProcedureStatement>");
  }
}
