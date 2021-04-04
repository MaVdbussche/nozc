package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class FunctorDefAnonym extends DeclarationAnonym {

  private String name;

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
      ArrayList<ExportClause> exports, InStatement statement, @Nullable String name) {
    super(line);
    this.imports = imports;
    this.exports = exports;
    this.statement = new InStatementFunctor(statement);
    this.name = name;
  }

  public String name() {
    return name;
  }

  public ArrayList<ImportClause> imports() {
    return imports;
  }

  public ArrayList<ExportClause> exports() {
    return exports;
  }

  public InStatementFunctor statement() {
    return statement;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Expression analyze(Context context) {
    FunctorContext fContext = new FunctorContext(context);

    imports.forEach(i -> {
      i = (ImportClause) i.analyze(context);
      fContext.addImport(i);
    });

    context.assignFunctorAnonym(new FunctorDef(this), fContext);

    statement.analyze(fContext);

    exports.forEach(e -> {
      e = (ExportClause) e.analyze(context);
      fContext.ensureExistsHere(line(), e.exportedValue().name());
    });

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
