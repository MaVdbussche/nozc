package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import java.util.ArrayList;

/**
 * The abstract syntax tree (AST) node representing an interactive statement, and so the root of the
 * AST.
 */
public class InterStatement extends AST {

  /**
   * List of variables/values/procedures/functions/functors/classes declared in this block.
   */
  private ArrayList<DeclarationPart> declarationParts;

  /** List of statements forming the block body. */
  private ArrayList<Statement> statements;

  /**
   * The context represented by this block.
   */
  private GlobalContext context;

  /**
   * Whether a semantic error has been found.
   */
  private boolean isInError;

  /**
   * Construct an AST node for an interactive statement given a line number, declaration parts, and
   * a possible child InterStatement.
   *
   * @param line      line in which the compilation unit occurs in the source file.
   * @param decls     an (optional list of declarations.
   * @param statements a (optional) list of statements.
   */
  public InterStatement(int line, ArrayList<DeclarationPart> decls,
      ArrayList<Statement> statements) {
    this.line = line;
    this.declarationParts = decls;
    this.statements = statements;
    AST.interStatement = this;
  }

  /**
   * Construct an AST node for an InterStatement given its line number.
   *
   * @param line line in which the statement occurs in the source file.
   */
  public InterStatement(int line, ArrayList<Statement> statements) {
    this.line = line;
    this.declarationParts = null;
    this.statements = statements;
    AST.interStatement = this;
  }

  /**
   * Has a semantic error occurred up to now?
   *
   * @return true or false.
   */
  public boolean errorHasOccurred() {
    return isInError;
  }

  /**
   * Report a semantic error.
   *
   * @param line      line in which the error occurred in the source file.
   * @param message   message identifying the error.
   * @param arguments related values.
   */
  public void reportSemanticError(int line, String message,
      Object... arguments) {
    isInError = true;
    System.err.printf("%s:%d: ", this.fileName, line);
    System.err.printf(message, arguments);
    System.err.println();
  }

  public void preAnalyze() {
    context = new GlobalContext();

    context.addMethod(); //TODO all implicit methods in Oz (Browse etc.)
    //TODO also add system calls etc, things directly available without imports

    if (declarationParts != null) {
      for (DeclarationPart decl : declarationParts) {
        //TODO
      }
    }
    for (Statement statement : statements) {
      //TODO
    }

  }

  @Override
  public AST analyze(Context context) {
    if (declarationParts != null) {
      declarationParts.forEach(dp -> dp = dp.analyze(this.context));
    }
    statements.forEach(s -> s = s.analyze(this.context));
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (declarationParts != null) {
      //TODO codegen DECLARE
      for (DeclarationPart decl : declarationParts) {
        decl.codegen(output);
      }
      //TODO codegen IN
    }
    for (Statement statement : statements) {
        statement.codegen(output);
    }
  }

  /**
   * @inheritDoc
   */
  public void writeToStdOut(PrettyPrinter printer) {
    //TODO
  }
}
