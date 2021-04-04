package com.barassolutions;

import com.barassolutions.util.Logger;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class ConditionalExpression extends Expression {

  /**
   * Test expressions.
   */
  private final ArrayList<Expression> conditions;

  /**
   * Then clauses, ordered to match the conditions above. There might be one more if there is an
   * "else" clause.
   */
  private final ArrayList<InExpression> consequences;

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
    } else if (conditions.size() + 1 == expressions.size()) {
      elsePart = true;
    } else {
      interStatement.reportSemanticError(line(), "Ill-formed conditional block.");
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
          c = c.analyze(context);
          c.type().mustMatchExpected(line(), Type.BOOLEAN);
        }
    );

    AtomicReference<Type> returnedType = new AtomicReference<>();
    returnedType.set(null);
    consequences.forEach(c -> {
      c = (InExpression) c.analyze(context);
      if (returnedType.get() == null) {
        returnedType.set(c.type());
      } else {
        if (!c.type().matchesExpected(returnedType.get())) {
          Logger.warn(
              "Line %d : All the returned expressions in a given method should be of the same type. this is not an error, but it might have unpredictable results.",
              line());
        }
      }
    });

    this.type = returnedType.get();
    return this;
  }

  /**
   * Code generation for an if-expression.
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
      } else if (i == conditions.size() - 1) { //We are done
        output.token(TokenOz.END);
        break;
      }
    }
  }

  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<IfExpression line=\"%d\">\n", line());
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
    p.printf("</IfExpression>\n");
  }
}
