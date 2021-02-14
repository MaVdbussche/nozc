package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import java.util.ArrayList;

public class InterStatement extends AST {

  ArrayList<DeclarationPart> declarationParts;

  InterStatement interStatement;

  /**
   * Whether a semantic error has been found.
   */
  private boolean isInError;

  public InterStatement(int line, ArrayList<DeclarationPart> decls, InterStatement statement) {
    //TODO
  }

  public InterStatement(int line, Statement statement) {
    //TODO
  }

  /**
   * Has a semantic error occurred up to now?
   *
   * @return true or false.
   */
  public boolean errorHasOccurred() {
    return isInError;
  }

  public void preAnalyze() {
    //TODO
  }

  @Override
  public AST analyze(Context context) {
    return null;
  }

  @Override
  public void codegen(Emitter output) {

  }

  public void writeToStdOut(PrettyPrinter printer) {
    //TODO
  }
}
