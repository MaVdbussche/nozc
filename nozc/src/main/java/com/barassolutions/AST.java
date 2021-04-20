package com.barassolutions;

/**
 * The abstract superclass of all nodes in the abstract syntax tree (AST).
 */
abstract class AST {

  /**
   * Current interactive statement (set in InterStatement() constructor).
   */
  protected static InterStatement interStatement;

  private String fileName;

  /**
   * Line in which the source for the AST was found.
   */
  private final int line;

  /**
   * Construct an AST node.
   */
  protected AST() {
    this.line = 0;
  }

  /**
   * Construct an AST node the given its line number in the source file.
   *
   * @param line line in which the source for the AST was found.
   */
  protected AST(int line) {
    this.line = line;
  }

  /**
   * Return the line in which the source for the AST was found.
   *
   * @return the line number.
   */
  public int line() {
    return line;
  }

  public String fileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Perform semantic analysis on this AST. In some instances a new returned AST reflects surgery.
   *
   * @param context the environment (scope) in which code is analyzed.
   * @return a (rarely modified) AST.
   */
  public abstract AST analyze(Context context);

  /**
   * Generate a partial code piece for this type, reflecting only the member information required to
   * do analysis.
   *
   * @param context the parent context.
   * @param partial the code emitter (basically an abstraction for producing the partial code
   *                piece).
   */
  public void partialCodegen(Context context, Emitter partial) {
    // A dummy -- redefined where necessary.
  }

  /**
   * Perform code generation for this AST.
   *
   * @param output the code emitter (basically an abstraction for producing the oz file).
   */
  public abstract void codegen(Emitter output);

  /**
   * Write the information pertaining to this AST to STDOUT.
   *
   * @param p for pretty printing with indentation.
   */
  public abstract void writeToStdOut(PrettyPrinter p);
}
