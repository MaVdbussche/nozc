package com.barassolutions;

import java.util.ArrayList;

/**
 * The AST node for an if-statement.
 */
public class ConditionalStatement extends Statement {

  /**
   * Test expressions.
   */
  private final ArrayList<Expression> conditions;

  /**
   * Then clauses, ordered to match the conditions above. There might be one more if there is an
   * "else" clause.
   */
  private final ArrayList<InStatement> consequences;

  private boolean elsePart;

  /**
   * Construct an AST node for an if-statement given its line number, the test expressions, and the
   * consequences.
   *
   * @param line       line in which the if-statement occurs in the source file.
   * @param conditions test expressions.
   * @param thenPart   then clauses.
   */
  public ConditionalStatement(int line, ArrayList<Expression> conditions,
      ArrayList<InStatement> thenPart) {
    super(line);
    if (conditions.size() == thenPart.size()) {
      elsePart = false;
    } else if (conditions.size() + 1 == thenPart.size()) {
      elsePart = true;
    } else {
      interStatement.reportSemanticError(line(), "Ill-formed conditional block.");
    }
    this.conditions = conditions;
    this.consequences = thenPart;
  }

  /**
   * Analyzing the if-statement means analyzing its components and checking that the test is
   * boolean.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Statement analyze(Context context) {
    conditions.forEach(c -> {
          c = c.analyze(context);
          c.type().mustMatchExpected(line(), Type.BOOLEAN);
        }
    );
    consequences.forEach(c -> c = (InStatement) c.analyze(context));
    return this;
  }

  /**
   * Code generation for an if-statement.
   *
   * @param output the code emitter (basically an abstraction for producing the .oz file).
   */
  public void codegen(Emitter output) {
    for (int i = 0; i < conditions.size(); i++) {
      output.token(TokenOz.IF);
      output.space();
      output.token(TokenOz.LPAREN);
      conditions.get(i).codegen(output);
      output.token(TokenOz.RPAREN);
      output.space();
      output.token(TokenOz.THEN);
      output.newLine();
      output.indentRight();
      consequences.get(i).codegen(output);
      output.newLine();
      output.indentLeft();
      if (i != conditions.size() - 1) { //Next i is an "else if"
        output.token(TokenOz.ELSE);
      } else if (elsePart && (i == conditions.size() - 1)) { // Next i is an "else"
        output.token(TokenOz.ELSE);
        output.newLine();
        output.indentRight();
        consequences.get(i+1).codegen(output);
        output.newLine();
        output.indentLeft();
        output.token(TokenOz.END);
        break;
      } else if (i == consequences.size()-1) { //We are done
        output.token(TokenOz.END);
        break;
      }
    }
  }

  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<IfStatement line=\"%d\">\n", line());
    p.indentRight();

    for (int i = 0; i < conditions.size(); i++) {
      p.printf("<TestExpression>\n");
      p.indentRight();
      conditions.get(i).writeToStdOut(p);
      p.indentLeft();
      p.printf("</TestExpression>\n");
      p.printf("<ThenClause>\n");
      p.indentRight();
      consequences.get(i).writeToStdOut(p);
      p.indentLeft();
      p.printf("</ThenClause>\n");
    }
    if (elsePart) {
      p.printf("<ElseClause>\n");
      p.indentRight();
      consequences.get(consequences.size() - 1).writeToStdOut(p);
      p.indentLeft();
      p.printf("</ElseClause>\n");
    }

    p.indentLeft();
    p.printf("</IfStatement>\n");
  }

}
