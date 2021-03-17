package com.barassolutions;

import java.util.ArrayList;

public class FunctorDefAnonym extends DeclarationAnonym {

  /**
   * Elements imported by this functor.
   */
  private final ArrayList<ImportClause> imports;

  /**
   * Elements exported by this functor.
   */
  private final ArrayList<ExportClause> exports;

  /**
   * Statement block represented by this functor.
   */
  private final InStatementFunctor statement;

  public FunctorDefAnonym(int line, ArrayList<ImportClause> imports,
      ArrayList<ExportClause> exports, InStatement statement) {
    super(line);
    this.imports = imports;
    this.exports = exports;
    this.statement = new InStatementFunctor(statement);
  }

  @Override
  public Expression analyze(Context context) {
    imports.forEach(i -> i = (ImportClause) i.analyze(context)); //TODO add imports to FunctorContext
    exports.forEach(e -> e = (ExportClause) e.analyze(context)); //TODO make sure exported vars exist in FunctorContext

    FunctorContext fContext = new FunctorContext(context);

    statement.analyze(fcontext);

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.FUNCTOR);
    output.space();
    output.token(TokenOz.DOLLAR);
    output.newLine();
    if (imports.size() > 0) {
      output.token(TokenOz.IMPORT);
      output.newLine();
      output.indentRight();
      imports.forEach(c -> {
        c.codegen(output);
        output.newLine();
      });
      output.indentLeft();
    }
    if (exports.size() > 0) {
      output.token(TokenOz.EXPORT);
      output.newLine();
      output.indentRight();
      exports.forEach(c -> {
        c.codegen(output);
        output.newLine();
      });
      output.indentLeft();
    }
    output.token(TokenOz.DEF);
    output.indentRight();
    statement.codegen(output);
    output.indentLeft();
    output.token(TokenOz.END);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<FunctorDeclaration line=\"%d\" Anonym>\n", line());
    p.indentRight();
    imports.forEach(a -> a.writeToStdOut(p));
    exports.forEach(e -> e.writeToStdOut(p));

    statement.writeToStdOut(p);

    p.indentLeft();
    p.printf("</FunctionDeclaration>\n");
  }
}
