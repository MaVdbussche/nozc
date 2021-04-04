package com.barassolutions;

import com.barassolutions.util.Logger;
import java.util.ArrayList;

/**
 * This class describes calls to methods in the context of a class (Object-application). Their Oz
 * syntax is drastically different from standard functions/procedures calls.
 *
 * @see CallFunction
 * @see CallProcedure
 */
public class Call extends Expression {

  /**
   * The name of the method being called
   */
  private final String name;

  /**
   * The arguments to the call.
   */
  private final ArrayList<Expression> arguments;

  private final String target;

  private final String superClassName;

  public Call(int line, String name, ArrayList<Expression> args) {
    super(line);
    this.name = name;
    this.arguments = args;
    this.target = "this";
    this.superClassName = null;
  }

  public Call(int line, String name, ArrayList<Expression> args, String className) {
    super(line);
    this.name = name;
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
              "Could not find method for: <name:" + name + " nbArgs:" + arguments.size()
                  + "> in Class \"" + classContext.name + "\"");
        } else {
          if (method.isAFunction()) {
            Logger.debug("Call : return type is " + method.returnType());
            this.type = method.returnType();
          }
        }
      } else if (target.equals("super")) {
        classContext = context.findClassContext(superClassName);
        if (classContext == null) {
          interStatement.reportSemanticError(line(),
              "Could not find Class \"" + superClassName + "\" in this context.");
        } else {
          if (Character.isUpperCase(name.charAt(0))) {
            interStatement.reportSemanticError(line(),
                "Calling a private method on a superclass is not allowed <" + name + ">");
          } else {
            MethodDef method = classContext.methodFor(name, arguments.size());
            if (method == null) {
              interStatement.reportSemanticError(line(),
                  "Could not find method for: <name:" + name + " nbArgs:" + arguments.size()
                      + "> in Class \"" + superClassName + "\"");
            } else {
              if (method.isAFunction()) {
                Logger.debug("Call : return type is " + method.returnType());
                this.type = method.returnType();
              }
            }
          }
        }
      }
    }

    //Analyze arguments
    arguments.forEach(a -> a = a.analyze(context));

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (target.equals("this")) {
      output.token(TokenOz.LCURLY);
      output.token(TokenOz.SELF);
      output.space();
    } else if (target.equals("super") && superClassName != null) {
      output.literal(superClassName);
      output.token(TokenOz.COMMA);
    }
    output.literal(name); // Might have a capital letter or not (don't modify it)
    output.token(TokenOz.LPAREN);
    arguments.forEach(e -> {
      if (arguments.indexOf(e) != 0) {
        output.space();
      }
      e.codegen(output);
    });
    output.token(TokenOz.RPAREN);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<CallMethod line=\"%d\" name=\"%s\" target=\"%s(" + (superClassName != null
            ? superClassName : "") + ")\">\n", line(),
        name, target);
    p.indentRight();

    p.println("<Arguments>");
    for (Expression argument : arguments) {
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
    p.println("</CallMethod>");
  }
}
