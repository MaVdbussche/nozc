package com.barassolutions;

import com.barassolutions.util.Logger;
import com.barassolutions.util.Utils;
import java.util.ArrayList;

public class CallProcedure extends Statement {

  /**
   * The name of the procedure being called
   */
  private final String name;

  /**
   * The arguments to the call.
   */
  private final ArrayList<Expression> args;

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
  @Override
  public AST analyze(Context context) {
    //Find appropriate method in the context, given the name and the nb of arguments.
    //We could check the type to allow overloading, but Oz does not allow so. Instead, it will produce an error at runtime
    ProcedureDef method = context.procedureFor(name, args.size());
    if (method == null) {
      interStatement.reportSemanticError(line(),
          "Could not find procedure for: <name:" + name + " nbArgs:" + args.size() + ">");
    }
    //No return type for procedures

    // Analyzing the arguments
    args.forEach(a -> a = a.analyze(context));

    return this;
  }

  /**
   * Perform code generation for a procedure call, given the code emitter.
   *
   * @param output the code emitter (basically an abstraction for producing the .oz file).
   */
  @Override
  public void codegen(Emitter output) {
    Logger.debug("Generating code for a procedure call <name:"+name+">");
    output.token(TokenOz.LCURLY);
    output.literal(Utils.ozFriendlyName(name));
    args.forEach(a -> {
      output.space();
      if (a instanceof Variable v) {
        v.codegen(output);
      } else {
        a.codegen(output);
      } //Can't remember the purpose of this. But at this point I'm too afraid to ask
    });
    output.token(TokenOz.RCURLY);
    output.newLine();
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
