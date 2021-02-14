package com.barassolutions.toCleanUp;

import java.util.ArrayList;

public class CompilationBlock extends AST {

  private final String fileName;

  //TODO modelize this
  private final TypeName functorName;
  private final ArrayList<TypeName> imports;
  private final ArrayList<TypeName> exports;

  private CompilationBlockContext context;

  private boolean errorState;

  public CompilationBlock(String filename, int line, TypeName functorName, ArrayList<TypeName> imports, ArrayList<TypeName> exports) {
    super(line);
    this.fileName = filename;
    this.functorName = functorName;
    this.imports = imports;
    this.exports = exports;
    compilationBlock = this;
  }

  public boolean isInErrorState() {
    return this.errorState;
  }

  public void reportSemanticError(int line, String message, Object... args) {
    errorState = true;
    System.err.printf("%s:%d: ", this.fileName, line);
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
  }

  @Override
  public void codegen(Emitter output) {
//TODO ?
  }

  @Override
  public void writeOut(CustomPrinter p) {
// TODO ?
  }
}
