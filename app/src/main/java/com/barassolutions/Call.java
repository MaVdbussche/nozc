package com.barassolutions;

import java.util.ArrayList;

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

  private final String target;

  private final String superClassName;

  public Call(int line, Variable name, ArrayList<Expression> args) {
    super(line);
    this.name = name.name();
    this.arguments = args;
    this.target = "this";
    this.superClassName = null;
  }

  public Call(int line, Variable name, ArrayList<Expression> args, String className) {
    super(line);
    this.name = name.name();
    this.arguments = args;
    this.target = "super";
    this.superClassName = className;
  }

  @Override
  public Expression analyze(Context context) {
    //Find appropriate method in the context, given the name and the nb of arguments.
    //We could check the type to allow overloading, but Oz does not allow so. Instead, it will produce an error at runtime
    ClassContext classContext = context.findClassContext();
    if (classContext == null) {
      interStatement.reportSemanticError(line(),
          "Usage of \"" + target + "\" is only allowed in the context of a Class.");
    } else {
      if (target.equals("this")) {
        MethodDef method = classContext.methodFor(name, arguments.size());
        if (method == null) {
          interStatement.reportSemanticError(line(),
              "Could not find method for: <name:" + name + " args:" + arguments.size()
                  + "> in Class \"" + classContext.name + "\"");
        } else {
          if(method.isAFunction()) {
            this.type = method.returnType();
          }
        }
      } else if (target.equals("super")) {
        classContext = context.findClassContext(superClassName);
        if (classContext == null) {
          interStatement.reportSemanticError(line(),
              "Could not find Class \"" + superClassName + "\" in this context.");
        } else {
          MethodDef method = classContext.methodFor(name, arguments.size());
          if (method == null) {
            interStatement.reportSemanticError(line(),
                "Could not find method for: <name:" + name + " args:" + arguments.size()
                    + "> in Class \"" + superClassName + "\"");
          } else {
            if(method.isAFunction()) {
              this.type = method.returnType();
            }
          }
        }
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
