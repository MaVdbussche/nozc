package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class FunctionDefAnonym extends DeclarationAnonym {

  private final String name;

  /**
   * The arguments of this procedure.
   */
  private final ArrayList<Pattern> args;

  /**
   * The expression constituting the procedure's body.
   */
  private InExpression expression;

  private final boolean lazy;

  public FunctionDefAnonym(int line, ArrayList<Pattern> args, InExpression expression,
      boolean lazy, @Nullable String name) {
    super(line);
    this.args = args;
    this.expression = expression;
    this.lazy = lazy;
    this.name = name;
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

  @Override
  public Expression analyze(Context context) {
    MethodContext methContext = new MethodContext(context);
    context.addFunction(new FunctionDef(this), methContext);

    args.forEach(a -> {
      a = (Pattern) a.analyze(context);
      methContext.addArgument(a);
    });

    expression = (InExpression) expression.analyze(methContext);

    Type returnType = this.expression.type();
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

