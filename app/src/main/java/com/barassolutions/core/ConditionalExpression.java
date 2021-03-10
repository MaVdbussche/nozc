package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import java.util.ArrayList;

public class ConditionalExpression extends Expression {

  /**
   * Test expressions.
   */
  private ArrayList<Expression> conditions;

  /**
   * Then clauses, ordered to match the conditions above. There might be one more if there is an
   * "else" clause. The clauses are either all statements, or all expressions.
   */
  private ArrayList<InExpression> consequences;

  private boolean elsePart;

  /**
   * Construct an AST node for an if-expression given its line number, the test expressions, and the
   * expressions to return.
   *
   * @param line        line in which the if-statement occurs in the source file.
   * @param conditions  test expressions.
   * @param expressions then clauses.
   */
  public ConditionalExpression(int line, ArrayList<Expression> conditions,
      ArrayList<InExpression> expressions) {
    super(line);
    if (conditions.size() == expressions.size()) {
      elsePart = false;
    } else if (conditions.size() - 1 == expressions.size()) {
      elsePart = true;
    } else {
      //TODO error
    }
    this.conditions = conditions;
    this.consequences = expressions;
  }

  /**
   * Analyzing the if-expression means analyzing its components and checking that the test is
   * boolean.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    conditions.forEach(c -> {
          c = (Expression) c.analyze(context);
          c.type().mustMatchExpected(line(), Type.BOOLEAN);
        }
    );
    consequences.forEach(c -> c = (InExpression) c.analyze(context));
    return this;
  }

  /**
   * Code generation for an if-expression.
   *
   * @param output the code emitter (basically an abstraction for producing the .oz file).
   */
  public void codegen(Emitter output) {
    for (int i = 0; i < consequences.size(); i++) {
      //generate IF, expression, THEN, statement, ELSE if necessary
      if (elsePart && (i==consequences.size() - 1)) {
        // ELSE alone
      }
    }
    //TODO
  }

  /**
   * @inheritDoc
   */
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<IfExpression line=\"%d\">\n", line());
    p.indentRight();

    for (int i = 0; i < conditions.size(); i++) {
      p.printf("<TestExpression>\n");
      p.indentRight();
      conditions.get(i).writeOut(p);
      p.indentLeft();
      p.printf("</TestExpression>\n");
      p.printf("<ThenClause>\n");
      p.indentRight();
      consequences.get(i).writeOut(p);
      p.indentLeft();
      p.printf("</ThenClause>\n");
    }
    if (elsePart) {
      p.printf("<ElseClause>\n");
      p.indentRight();
      consequences.get(consequences.size() - 1).writeOut(p);
      p.indentLeft();
      p.printf("</ElseClause>\n");
    }

    p.indentLeft();
    p.printf("</IfExpression>\n");
  }
}
