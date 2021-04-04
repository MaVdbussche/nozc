package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class ProcedureDefAnonym extends DeclarationAnonym {

  private String name;

  /**
   * The arguments of this procedure.
   */
  private final ArrayList<Pattern> args;

  /**
   * The statement constituting the procedure's body.
   */
  private InStatement statement;

  public ProcedureDefAnonym(int line, ArrayList<Pattern> args, InStatement statement,
      @Nullable String name) {
    super(line);
    this.args = args;
    this.statement = statement;
    this.name = name;
  }

  public String name() {
    return name;
  }

  public ArrayList<Pattern> args() {
    return args;
  }

  public InStatement statement() {
    return statement;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Expression analyze(Context context) {
    MethodContext methContext = new MethodContext(context);

    args.forEach(a -> {
      a = (Pattern) a.analyze(context);
      methContext.addArgument(a);
    });

    context.assignProcedureAnonym(new ProcedureDef(this), methContext);

    statement = (InStatement) statement
        .analyze(methContext);

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.PROC);
    output.token(
        TokenOz.LCURLY);
    output.token(TokenOz.DOLLAR);
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
    p.printf("<ProcedureDeclaration line=\"%d\" Anonym>\n", line());
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

