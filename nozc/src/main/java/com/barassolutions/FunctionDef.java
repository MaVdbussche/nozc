package com.barassolutions;

import com.barassolutions.util.Utils;
import java.util.ArrayList;

public class FunctionDef extends Declaration {

  /**
   * The function's name.
   */
  private final String name;

  /**
   * The arguments of this function.
   */
  private final ArrayList<Pattern> args;

  /**
   * The expression constituting the function's body.
   */
  private InExpression expression;

  private final boolean lazy;

  private Type returnType;

  public FunctionDef(int line, String name, ArrayList<Pattern> args, InExpression expression,
      boolean lazy) {
    super(line);
    this.name = name;
    this.args = args;
    this.expression = expression;
    this.lazy = lazy;
  }

  public FunctionDef(FunctionDefAnonym f) {
    this(f.line(), f.name(), f.args(), f.expression(), f.lazy());
  }

  public Type returnType() {
    return returnType;
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

    args.forEach(a -> {
      a = (Pattern) a.analyze(context);
      methContext.addArgument(a);
    });

    expression = (InExpression) expression.analyze(methContext);

    returnType = expression.type();
    methContext.setReturnType(returnType);

    context.addFunction(this, methContext);
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

