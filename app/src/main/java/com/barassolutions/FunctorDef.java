package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class FunctorDef extends Declaration {

  /**
   * This functor's name.
   */
  @Nullable
  private final String name;

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

  public FunctorDef(int line, @Nullable String name, ArrayList<ImportClause> imports,
      ArrayList<ExportClause> exports, InStatement statement) {
    super(line);
    this.name = name;
    this.imports = imports;
    this.exports = exports;
    this.statement = new InStatementFunctor(statement);
  }

  @Override
  public AST analyze(Context context) {
    //TODO analyze the name in parent context and shadow in this one if necessary
    context.addFunctor(this);

    imports.forEach(i -> i = (ImportClause) i.analyze(context)); //TODO add imports to FunctorContext
    exports.forEach(e -> e = (ExportClause) e.analyze(context)); //TODO make sure exported vars exist in FunctorContext

    FunctorContext fContext = new FunctorContext(context);

    statement.analyze(fContext);

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.FUNCTOR);
    if(name!=null) {
      output.space();
      output.literal(name);
    }
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
    p.printf("<FunctorDeclaration line=\"%d\" name=\"%s\">\n", line(), name);
    p.indentRight();
    imports.forEach(a -> a.writeToStdOut(p));
    exports.forEach(e -> e.writeToStdOut(p));

    statement.writeToStdOut(p);

    p.indentLeft();
    p.printf("</FunctionDeclaration>\n");
  }
}
