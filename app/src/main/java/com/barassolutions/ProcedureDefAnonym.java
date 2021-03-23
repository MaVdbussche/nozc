package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class ProcedureDefAnonym extends DeclarationAnonym {

  private final String name;

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

  @Override
  public Expression analyze(Context context) {
    MethodContext methContext = new MethodContext(context);
    context.addProcedure(new ProcedureDef(this), methContext);

    args.forEach(a -> {
      a = (Pattern) a.analyze(context); //TODO actually we don't need to analyze args in the top context ? Since we will either shadow, or we just don't care if they don't exist ?! [!!!! applicable for all 25 classes that use this mehtContext]
      methContext.addArgument(a);
    });

    statement = (InStatement) statement
        .analyze(methContext);

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.PROC);
    output.token(
        TokenOz.LCURLY); //TODO see if we couldn't merge FunctionDef & ProcedureDef (see MethodDef for reference)
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

