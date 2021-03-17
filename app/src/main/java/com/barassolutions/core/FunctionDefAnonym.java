package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;

public class FunctionDefAnonym extends DeclarationAnonym {

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
      boolean lazy) {
    super(line);
    this.args = args;
    this.expression = expression;
    this.lazy = lazy;
  }

  @Override
  public Expression analyze(Context context) {
    // TODO create this function's inner context and add args to it if they don't already exist
    // TODO compute the return type
    // TODO create a Method instance
    args.forEach(a -> a = (Pattern) a.analyze(context));

    MethodContext methContext = new MethodContext(context);

    expression = (InExpression) expression.analyze(methContext);

    // TODO new Method(returnType = this.expression.type())
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

