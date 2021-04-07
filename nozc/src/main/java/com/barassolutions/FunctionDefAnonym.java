package com.barassolutions;

import com.barassolutions.util.Logger;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class FunctionDefAnonym extends DeclarationAnonym {

  /**
   * The arguments of this procedure.
   */
  private final ArrayList<Pattern> args;
  private final boolean lazy;
  private String name;
  /**
   * The expression constituting the procedure's body.
   */
  private InExpression expression;
  private Type returnType;

  public FunctionDefAnonym(int line, ArrayList<Pattern> args, InExpression expression,
      boolean lazy, @Nullable String name) {
    super(line);
    this.args = args;
    this.expression = expression;
    this.lazy = lazy;
    this.name = name;
    this.returnType = Type.ANY;
  }

  public Type returnType() {
    return returnType;
  }

  public String name() {
    return name;
  }

  public ArrayList<Pattern> args() {
    return args;
  }

  public InExpression expression() {
    return expression;
  }

  public boolean lazy() {
    return lazy;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Expression analyze(Context context) {
    MethodContext methContext = new MethodContext(context);
    //Temporary assigning a type to allow analysis of potential recursive calls
    methContext.setReturnType(returnType);
    Logger.debug("Temporarily assigned a type to FunctionDefAnonym");

    args.forEach(a -> {
      a = (Pattern) a.analyze(context);
      methContext.addArgument(a);
    });

    FunctionDef f = new FunctionDef(this);
    f.setReturnType(returnType);
    context.assignFunctionAnonym(f, methContext);

    expression = (InExpression) expression.analyze(methContext);

    returnType = expression.type(); //Kinda useless
    f.setReturnType(returnType);
    methContext.setReturnType(returnType);
    Logger.debug("FunctionAnonym return type is now " + returnType);

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
    output.token(TokenOz.DOLLAR);
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
      p.printf("<FunctionDeclaration line=\"%d\" Anonym lazy>\n", line());
    } else {
      p.printf("<FunctionDeclaration line=\"%d\" Anonym>\n", line());
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

