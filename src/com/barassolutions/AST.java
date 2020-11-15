package com.barassolutions;

/**
 * Superclass of all nodes in the Abstract Syntax Tree
 */
public abstract class AST {

  public static CompilationBlock compilationBlock;

  /**
   * Line at which the source of this AST was found
   */
  protected int line;

  /**
   * Constructs ans AST from this line in the source file
   */
  protected AST(int line) {
    this.line = line;
  }

  public int line() {
    return this.line;
  }

  /**
   * Launch the semantic analysis of this AST
   * @param context the environment (scope) in which this code is analyzed
   * @return an AST (sometimes modified)
   */
  public abstract AST analyze(Context context);

  /**
   * Launch the code gen for this AST
   */
  public abstract void codegen(Emitter output);

  /**
   * Describe this AST in STDOUT
   */
  public abstract void writeOut(CustomPrinter p);
}
