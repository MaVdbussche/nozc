package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;

public class Functor extends DeclarationPart {

  /**
   * This functor's name.
   */
  private String name;

  /**
   * Elements imported by this functor.
   */
  private ArrayList<ImportClause> imports;

  /**
   * Elements exported by this functor.
   */
  private ArrayList<ExportClause> exports;

  /**
   * Statement block represented by this functor.
   */
  private InStatement statement;

  public Functor(int line, String name, ArrayList<ImportClause> imports,
      ArrayList<ExportClause> exports, InStatement statement) {
    super(line);
    this.name = name;
    this.imports = imports;
    this.exports = exports;
    this.statement = statement;
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
    //TODO
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    //TODO
  }
}
