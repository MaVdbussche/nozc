package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.TokenOz;

public class InStatementFunctor extends InStatement {

  public InStatementFunctor(InStatement statement) {
    super(statement.line, statement.declarations, statement.statements);
    assert statement.declarations.size() > 0;
  }

  /**
   * Generating code for a functor block consists of generating code for each of its declarations
   * and (optional) statements.
   *
   * @param output the code emitter (basically an abstraction for producing the Oz file).
   */
  @Override
  public void codegen(Emitter output) {
    declarations.forEach(e -> e.codegen(output));
    if (statements.size() > 0) {
      output.indentLeft();
      output.token(TokenOz.IN);
      output.newLine();
      output.indentRight();
      statements.forEach(e -> e.codegen(output));
    }
  }
}
