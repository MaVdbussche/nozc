package com.barassolutions;

import com.barassolutions.util.Logger;
import com.barassolutions.util.Utils;
import java.util.List;

public class FunctionDef extends Declaration {

  /**
   * The function's name.
   */
  private final String name;

  /**
   * The arguments of this function.
   */
  private final List<Pattern> args;

  /**
   * The expression constituting the function's body.
   */
  private InExpression expression;

  private final boolean lazy;

  private Type returnType;

  public FunctionDef(int line, String name, List<Pattern> args, InExpression expression,
      boolean lazy) {
    super(line);
    this.name = name;
    this.args = args;
    this.expression = expression;
    this.lazy = lazy;
    this.returnType = Type.ANY;
  }

  public FunctionDef(FunctionDefAnonym f) {
    this(f.line(), f.name(), f.args(), f.expression(), f.lazy());
  }

  public Type returnType() {
    return returnType;
  }

  public void setReturnType(Type t) {
    this.returnType = t;
  }

  public String name() {
    return name;
  }

  public int nbArgs() {
    return args.size();
  }

  @Override
  public AST analyze(Context context) {
    MethodContext methContext = new MethodContext(context);
    //Temporary assigning a type to allow analysis of potential recursive calls
    methContext.setReturnType(returnType);
    Logger.debug("Temporarily assigned a type to FunctionDef");

    args.forEach(a -> {
      a = (Pattern) a.analyze(context);
      methContext.addArgument(a);
    });

    context.addFunction(this, methContext);

    expression = (InExpression) expression.analyze(methContext);

    returnType = expression.type();
    Logger.debug("Function return type is now " + returnType);
    methContext.setReturnType(returnType);

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.FUN);
    if (lazy) {
      output.space();
      output.token(TokenOz.LAZY);
      output.space();
    }
    output.token(TokenOz.LCURLY);
    output.literal(Utils.ozFriendlyName(name));
    args.forEach(a -> {
      output.space();
      a.codegen(output);
    });
    output.token(TokenOz.RCURLY);
    output.newLine();
    output.indentRight();

    expression.codegen(output);

    output.newLine();
    output.indentLeft();
    output.token(TokenOz.END);
    output.newLine();
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    if (lazy) {
      p.printf("<FunctionDeclaration line=\"%d\" name=\"%s\" lazy>\n", line(), name);
    } else {
      p.printf("<FunctionDeclaration line=\"%d\" name=\"%s\">\n", line(), name);
    }
    p.indentRight();
    args.forEach(a -> {
      p.printf("<Argument>\n");
      p.indentRight();
      a.writeToStdOut(p);
      p.indentLeft();
      p.printf("</Argument>\n");
    });

    expression.writeToStdOut(p);

    p.indentLeft();
    p.printf("</FunctionDeclaration>\n");
  }
}

