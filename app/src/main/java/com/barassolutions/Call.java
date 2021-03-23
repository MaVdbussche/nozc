package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

/**
 * This class describes calls to methods in the context of a class (Object-application). Their Oz
 * syntax is drastically different from standard functions/procedures calls.
 *
 * @see CallFunction
 * @see CallProcedure
 */
public class Call extends Expression {

  private final String name;

  private final ArrayList<Expression> arguments;

  private String target = null;

  private String superClassName = null;

  private boolean lookForClassCtxt = false;

  public Call(int line, Variable name, ArrayList<Expression> args) {
    super(line);
    this.name = name.name();
    this.arguments = args;
    this.target = "this";
    this.superClassName = null;
    this.lookForClassCtxt = true;
  }

  public Call(int line, Variable name, ArrayList<Expression> args, String className) {
    super(line);
    this.name = name.name();
    this.arguments = args;
    this.target = "super";
    this.superClassName = className;
    this.lookForClassCtxt = true;
  }

  public Call(int line, String name, ArrayList<Expression> args) {
    super(line);
    this.name = name;
    this.arguments = args;
  }

  @Override
  public Expression analyze(Context context) {
    //Resolve method signature in the appropriate context
    if (lookForClassCtxt) {
      ClassContext classContext = context.findClassContext();
      if (classContext == null) {
        interStatement.reportSemanticError(line(),
            "Usage of \"" + target + "\" is only allowed in the context of a Class.");
        return this;
      }

      if (target.equals("this")) {
        MethodDef method = classContext.methodFor(name, arguments.size());
        if (method == null) {
          interStatement.reportSemanticError(line(),
              "Could not find method for: <name:" + name + " args:" + arguments.size()
                  + "> in Class \"" + classContext.name + "\"");
          return this;
        }
      } else if (target.equals("super")) {
        classContext = context.findClassContext(superClassName);
        if (classContext == null) {
          interStatement.reportSemanticError(line(),
              "Could not find Class \"" + superClassName + "\" in this context.");
          return this;
        } else {
          MethodDef method = classContext.methodFor(name, arguments.size());
          if (method == null) {
            interStatement.reportSemanticError(line(),
                "Could not find method for: <name:" + name + " args:" + arguments.size()
                    + "> in Class \"" + superClassName + "\"");
            return this;
          }
        }
      }
    } else {
      FunctionDef function = context.functionFor(name, arguments.size());
      ProcedureDef proc = context.procedureFor(name, arguments.size());
      if (function == null && proc == null) {
        interStatement.reportSemanticError(line(),
            "Could not find function or procedure for: <name:" + name + " args:" + arguments.size()
                + ">");
        return this;
      }
    }

    //Analyze arguments
    arguments.forEach(a -> a = (Expression) a.analyze(context));

    return this;
  }

  @Override
  public void codegen(Emitter output) {

  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {

  }
}
