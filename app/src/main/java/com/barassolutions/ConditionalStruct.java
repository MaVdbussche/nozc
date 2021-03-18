// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions;

import java.util.ArrayList;

/**
 * The AST node for an if-statement.
 */
public class ConditionalStruct extends Statement {

  /**
   * Test expressions.
   */
  private final ArrayList<Expression> conditions;

  /**
   * Then clauses. There might be one more if there is an "else" clause
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
  public ConditionalStruct(int line, ArrayList<Expression> conditions,
      ArrayList<InStatement> thenPart) {
    super(line);
    if (conditions.size() == thenPart.size()) {
      elsePart = false;
    } else if (conditions.size() - 1 == thenPart.size()) {
      elsePart = true;
    } else {
      //TODO error
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
          c = (Expression) c.analyze(context);
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
    for (int i = 0; i < consequences.size(); i++) {
      //generate IF, expression, THEN, statement, ELSE if necessary
      if (i == consequences.size() - 1) {
        //last one is an ELSE alone
      }
    }
    //TODO
  }

  /**
   * @inheritDoc
   */
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
