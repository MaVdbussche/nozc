package com.barassolutions;

public abstract class Pattern extends Expression {

  /**
   * Construct an AST node for an expression given its line number.
   *
   * @param line line in which the expression occurs in the source file.
   */
  protected Pattern(int line) {
    super(line);
  }
}