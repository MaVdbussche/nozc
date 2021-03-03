package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;

public class FunctionDef extends DeclarationPart {

  /**
   * The procedure's name.
   */
  private final String name;

  /**
   * The arguments of this procedure.
   */
  private ArrayList<Pattern> args;

  /**
   * The expression constituting the procedure's body.
   */
  private InExpression expression;

  private boolean lazy;

  public FunctionDef(int line, String name, ArrayList<Pattern> args, InExpression expression,
      boolean lazy) {
    super(line);
    this.name = name;
    this.args = args;
    this.expression = expression;
    this.lazy = lazy;
  }

  @Override
  public AST analyze(Context context) {
    //TODO declare name in the context if it doesn't exist yet
    // TODO create this function's inner context and add args to it if they don't already exist
    // TODO compute the return type
    // TODO create a Method instance
    args.forEach(a -> a = (Pattern) a.analyze(context));

    expression = (InExpression) expression
        .analyze(context); //TODO pass this function's inner context instead !

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
    output.literal(name);
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

