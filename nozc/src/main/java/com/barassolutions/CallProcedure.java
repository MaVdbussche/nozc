package com.barassolutions;

import com.barassolutions.util.BuiltInType;
import com.barassolutions.util.BuiltIns;
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

  private boolean isActuallyAFunction;
  private Type returnType; //!!!Only makes sense if it is actually a function TODO

  public CallProcedure(int line, String name, ArrayList<Expression> args) {
    super(line);
    this.name = name;
    this.args = args;
  }

  public boolean isActuallyAFunction() {
    return isActuallyAFunction;
  }

  public Type returnType() {
    return returnType;
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
    ProcedureDef proc = context.procedureFor(name, args.size());
    if (proc == null) {
      FunctionDef function = context.functionFor(name, args.size());
      if (function == null) { //TODO refactor this when we merge CallFunction and CallProcedure
        interStatement.reportSemanticError(line(),
            "Could not find procedure or function for: <name:" + name + " nbArgs:" + args.size()
                + ">");
      } else {
        isActuallyAFunction = true;
        this.returnType = function.returnType();
        Logger.debug("Could not find procedure for: <name:" + name + " nbArgs:" + args.size()
                + ">, but found a matching function with returnType \"%s\". Using that one, but this may lead to problems down the line.",
            function.returnType());
      }
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
    Logger.debug("Generating code for a procedure call <name:" + name + ">");
    output.token(TokenOz.LCURLY);
    BuiltIns builtIn = Utils.findBuiltIn(name, BuiltInType.PROCEDURE);
    output.literal(builtIn!=null ? builtIn.ozString() : Utils.ozFriendlyName(name));
    args.forEach(a -> {
      output.space();
      if (a instanceof Variable v) {
        if (args.indexOf(a)==args.size()-1 && builtIn!=null && builtIn.name().charAt(builtIn.name().length()-1)=='P') {
          (new Variable(v, false)).codegen(output); //Ugly hack to avoid readmode on the last argument for the procedures that "overload" built-in functions (see BuiltIns enums definitions)
        } else {
          v.codegen(output);
        }
      } else {
        a.codegen(output);
      } //Can't remember the purpose of this. But at this point I'm too afraid to ask
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
