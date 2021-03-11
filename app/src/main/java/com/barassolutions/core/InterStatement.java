package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;

/**
 * The abstract syntax tree (AST) node representing an interactive statement, and so the root of the
 * AST.
 */
public class InterStatement extends AST {

  /**
   * List of statements forming the block body.
   */
  private ArrayList<Statement> statements;

  /**
   * Block in this declare structure
   */
  private InStatement statement;

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
   * @param line        line in which the compilation unit occurs in the source file.
   * @param inStatement a block of statements.
   */
  public InterStatement(int line, InStatement inStatement) {
    this.line = line;
    this.statement = inStatement;
    this.statements = null;
    AST.interStatement = this;
  }

  /**
   * Construct an AST node for an InterStatement given its line number.
   *
   * @param line       line in which the statement occurs in the source file.
   * @param statements a list of statements.
   */
  public InterStatement(int line, ArrayList<Statement> statements) {
    this.line = line;
    this.statement = null;
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

    //Launch recursive preAnalyze
  }

  @Override
  public AST analyze(Context context) {
    if (statements != null) {
      statements.forEach(s -> s = (Statement) s.analyze(this.context));
    } else if (statement != null) {
      statement = (Statement) statement.analyze(this.context);
    } else {
      //TODO critical error
    }
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (statement != null) {
      output.token(TokenOz.DECLARE);
      output.newLine();
      output.indentRight();
      statement.codegen(output);
      output.newLine();
      output.indentLeft();
    } else if (statements != null) {
      statements.forEach(s -> s.codegen(output));
    }
  }


  /**
   * @inheritDoc
   */
  public void writeToStdOut(PrettyPrinter printer) {
    //TODO
  }
}
