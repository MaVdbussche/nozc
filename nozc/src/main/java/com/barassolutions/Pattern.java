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

  /**
   * Returns an <code>Iterable</code> of the patterns constituting this <code>Pattern</code>.
   * This mostly makes sense for structures like tuples, lists, etc.
   * When it doesn't make sense, subclasses may return null.
   */
  public abstract Iterable<Pattern> patterns();
}