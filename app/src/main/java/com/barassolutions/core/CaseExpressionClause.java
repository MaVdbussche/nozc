package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import com.barassolutions.Utils;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class CaseExpressionClause extends Expression {

  /**
   * Base pattern that this clause should try to match
   */
  private Pattern pattern;

  /**
   * Additional operators to compute
   */
  private ArrayList<Operator> operators;

  /**
   * Additional expressions to compute
   */
  private ArrayList<Expression> expressions;

  /**
   * Expression to be returned in case of a successful match
   */
  private InExpression expression;

  /**
   * This variable is necessary because Oz's syntax is different for the first pattern-matching
   * clause
   */
  private boolean isFirstClause;

  /**
   * Construct an AST node for a pattern-matching clause followed by an expression, given its line
   * number, the test expressions, and the consequences.
   *
   * @param line          line in which the clause occurs in the source file.
   * @param ops           optional operations to perform to match this clause.
   * @param exprs         optional operations to perform to match this clause.
   * @param pattern       pattern to match.
   * @param expression    expression to return if the matching succeeds.
   * @param isFirstClause is this clause the first one that will be tested ?
   */
  public CaseExpressionClause(int line, @NotNull Pattern pattern, @NotNull ArrayList<Operator> ops,
      @NotNull ArrayList<Expression> exprs, @NotNull InExpression expression, boolean isFirstClause) {
    super(line);
    this.pattern = pattern;
    if (ops.size() == exprs.size()) {
      this.operators = ops;
      this.expressions = exprs;
      this.expression = expression;
      this.isFirstClause = isFirstClause;
    } else {
      //TODO error
    }
  }

  /**
   * Analyzing the pattern-matching clause means analyzing its components and checking that the
   * additional operations are boolean.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    pattern = (Pattern) pattern.analyze(context);

    operators.forEach(o -> {
      if (!(o == Operator.LAND || o == Operator.LOR)) {
        AST.interStatement.reportSemanticError(line,
            "Invalid operator %s : expected a logical operator instead.",
            o.image());
      } // This should probably be picked up by the Scanner instead tbh
    });

    expressions.forEach(e -> {
      e = (Expression) e.analyze(context);
      e.type().mustMatchExpected(line(), Type.BOOLEAN);
    });

    expression = (InExpression) expression.analyze(context);
    return this;
  }

  /**
   * Code generation for a pattern-matching clause.
   *
   * @param output the code emitter (basically an abstraction for producing the .oz file).
   */
  @Override
  public void codegen(Emitter output) {
    if(!isFirstClause) {
      output.token(TokenOz.LBRACK);
      output.token(TokenOz.RBRACK);
    } else {
      output.space();
      output.space();
    }
    output.space();
    pattern.codegen(output);
    output.space();
    for (int i = 0; i < expressions.size(); i++) {
      output.token(operators.get(i).getOzTokenInt()); //Avoiding an awkward switch here
      output.space();
      expressions.get(i).codegen(output);
      output.space();
    }
    output.token(TokenOz.THEN);
    output.space();
    expression.codegen(output);
    output.newLine();
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<CaseStatementClause line=\"%d\">\n", line());
    p.indentRight();

    p.printf("<Pattern>\n");
    p.indentRight();
    pattern.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Pattern>\n");

    for (int i = 0; i < expressions.size(); i++) {
      p.printf("<Operator \"%s\">\n", Utils.escapeSpecialXMLChars(operators.get(i).image()));
      p.printf("<Expression>\n");
      p.indentRight();
      expressions.get(i).writeToStdOut(p);
      p.indentLeft();
      p.printf("</Expression>\n");
    }

    expression.writeToStdOut(p);

    p.indentLeft();
    p.printf("</CaseStatementClause>\n");
  }
}
