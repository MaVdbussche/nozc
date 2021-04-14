package com.barassolutions;

public class InStatementFunctor extends InStatement {

  public InStatementFunctor(InStatement statement) {
    super(statement.line(), statement.declarations, statement.statements, false);
    if (! (statement.declarations.size() > 0)) {
      interStatement.reportSemanticError(line(), "You ned at least one declaration in the functor's body");
    }
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
