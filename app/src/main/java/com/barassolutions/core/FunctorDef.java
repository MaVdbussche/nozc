package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;

public class FunctorDef extends Declaration {

  /**
   * This functor's name.
   */
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

  public FunctorDef(int line, String name, ArrayList<ImportClause> imports,
      ArrayList<ExportClause> exports, InStatement statement) {
    super(line);
    this.name = name;
    this.imports = imports;
    this.exports = exports;
    this.statement = new InStatementFunctor(statement);
  }

  //TODO this functor has its own context : add imports to it so they are available in the inStatement's context
  //TODO make sure this name is not already existing in passed context
  @Override
  public AST analyze(Context context) {
    imports.forEach(i -> i = (ImportClause) i.analyze(context));
    exports.forEach(e -> e = (ExportClause) e.analyze(context));
    statement.analyze(context);

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.FUNCTOR);
    output.space();
    output.literal(name);
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
