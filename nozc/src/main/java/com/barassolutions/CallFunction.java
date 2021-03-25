package com.barassolutions;

import java.util.ArrayList;

public class CallFunction extends
    Expression {

  /**
   * The name of the function being called
   */
  private final String name;

  /**
   * The arguments to the call.
   */
  private final ArrayList<Expression> args;

  public CallFunction(int line, String name, ArrayList<Expression> args) {
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
  @Override
  public Expression analyze(Context context) {
    //Find appropriate function in the context, given the name and the nb of arguments.
    //We could check the type to allow overloading, but Oz does not allow so. Instead, it will produce an error at runtime
    FunctionDef method = context.functionFor(name, args.size());
    if (method == null) {
      interStatement.reportSemanticError(line(),
          "Could not find function for: <name:" + name + " args:" + args.size() + ">");
    } else {
      this.type = method.returnType();
    }

    // Analyzing the arguments
    args.forEach(a -> a = (Expression) a.analyze(context));

    return this;
  }

  /**
   * Perform code generation for a function call, given the code emitter.
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
    p.printf("<CallFunctionExpression line=\"%d\" name=\"%s\">\n", line(),
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
    p.println("</CallFunctionExpression>");
  }
}
