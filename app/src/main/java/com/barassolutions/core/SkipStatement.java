package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;

/**
 * The (dummy) AST node for representing the empty statement. Nothing needs to
 * be done during analysis or code generation. It simply represents empty
 * statements that are denoted by "skip" in Oz.
 */
public class SkipStatement extends Statement {

  /**
   * Construct an AST node for an empty statement.
   *
   * @param line
   *            line in which the empty statement occurs in the source file.
   */
  protected SkipStatement(int line) {
    super(line);
  }

  /**
   * @inheritDoc
   */
  public AST analyze(Context context) {
    // Nothing to do.
    return this;
  }

  /**
   * @inheritDoc
   */
  public void codegen(Emitter output) {
    // Nothing to do.
  }

  /**
   * @inheritDoc
   */

  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<SkipStatement line=\"%d\"/>\n", line());
  }
}
