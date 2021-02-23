package com.barassolutions.core;

/**
 * The AST node for a statement (includes expressions). The mother of all
 * statements.
 */
//TODO use Type hierarchy tool on j-- project
abstract class Statement extends AST {

  /**
   * Construct an AST node for a statement given its line number.
   *
   * @param line
   *            line in which the statement occurs in the source file.
   */
  protected Statement(int line) {
    super(line);
  }
}
