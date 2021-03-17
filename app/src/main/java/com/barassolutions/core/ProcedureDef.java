package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;

public class ProcedureDef extends Declaration {

  /**
   * The procedure's name.
   */
  private final String name;

  /**
   * The arguments of this procedure.
   */
  private final ArrayList<Pattern> args;

  /**
   * The statement constituting the procedure's body.
   */
  private InStatement statement;

  public ProcedureDef(int line, String name, ArrayList<Pattern> args, InStatement statement) {
    super(line);
    this.name = name;
    this.args = args;
    this.statement = statement;
  }

  @Override
  public AST analyze(Context context) {
    //TODO declare name in the context if it doesn't exist yet
    // TODO create this procedure's inner context and add args to it (shadow if necessary)
    // TODO create a Method instance (17/03 WHY ?)
    args.forEach(a -> a = (Pattern) a.analyze(context));

    MethodContext methContext = new MethodContext(context);

    statement = (InStatement) statement
        .analyze(methContext);
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.PROC);
    output.token(TokenOz.LCURLY); //TODO see if we couldn't merge FunctionDef & ProcedureDef (see MethodDef for reference)
    output.literal(name);
    args.forEach(a -> {
      output.space();
      a.codegen(output);
    });
    output.token(TokenOz.RCURLY);
    output.newLine();
    output.indentRight();

    statement.codegen(output);

    output.newLine();
    output.indentLeft();
    output.token(TokenOz.END);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ProcedureDeclaration line=\"%d\" name=\"%s\">\n", line(), name);
    p.indentRight();
    args.forEach(a -> {
      p.printf("<Argument>\n");
      p.indentRight();
      a.writeToStdOut(p);
      p.indentLeft();
      p.printf("</Argument>\n");
    });

    statement.writeToStdOut(p);

    p.indentLeft();
    p.printf("</ProcedureDeclaration>\n");
  }
}
