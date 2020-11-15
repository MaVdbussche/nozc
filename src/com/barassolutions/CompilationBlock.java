package com.barassolutions;

import java.util.ArrayList;

public class CompilationBlock extends AST {

  private String fileName;

  //TODO modelize this
  private ArrayList<Object> imports;
  private ArrayList<Object> exports;

  private CompilationBlockContext context;

  private boolean errorState;

  public CompilationBlock(String filename, int line, ArrayList imports, ArrayList exports) {
    super(line);
    this.fileName = filename;
    this.imports = imports;
    this.exports = exports;
    compilationBlock = this;
  }

  public boolean isInErrorState() {
    return this.errorState;
  }

  public void reportSemanticError(int line, String message, Object... args) {
    errorState = true;
    System.err.printf("%s:%d: ", this.filename, line);
    System.err.printf(message, args);
    System.err.println();
  }

  /**
   * Construct a context for the compilation block, initializing it with
   * imported types. Then pre-analyze the block's type declarations, adding
   * their types to the context.
   */
  public void preAnalyze() {
    // TODO not necessary when no types exist...
  }

  /**
   * Launch the analysis on the AST in the given Context
   */
  public AST analyze(Context context) {
    for (AST )
  }
}
