package com.barassolutions;

import com.barassolutions.util.BuiltIns;
import com.barassolutions.util.Utils;

public abstract class UnaryExpression extends Expression {

  /**
   * The operator.
   */
  protected String operator;

  /**
   * The operand.
   */
  protected Expression arg;

  /**
   * Construct an AST node for a unary expression given its line number, the
   * unary operator, and the operand.
   *
   * @param line
   *            line in which the unary expression occurs in the source file.
   * @param operator
   *            the unary operator.
   * @param arg
   *            the operand.
   */
  protected UnaryExpression(int line, String operator, Expression arg) {
    super(line);
    this.operator = operator;
    this.arg = arg;
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<UnaryExpression line=\"%d\" type=\"%s\" operator=\"%s\">\n", line(), ((type == null) ? "" : type
        .toString()), Utils.escapeSpecialXMLChars(operator));
    p.indentRight();
    p.printf("<Operand>\n");
    p.indentRight();
    arg.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Operand>\n");
    p.indentLeft();
    p.printf("</UnaryExpression>\n");
  }
}

/**
 * The AST node for a unary negation (-) expression.
 */
class OperationNegate extends UnaryExpression {

  public OperationNegate(int line, Expression arg) {
    super(line, "-", arg);
  }

  /**
   * Analyzing the negation operation involves analyzing its operand, checking
   * its type and determining the result type.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    arg = arg.analyze(context);
    arg.type().mustMatchExpected(line(), Type.INT, Type.FLOAT);
    type = arg.type();
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.WAVE);
    arg.codegen(output);
  }
}

/**
 * The AST node for a unary validation (+) expression.
 */
class OperationValidate extends UnaryExpression {

  public OperationValidate(int line, Expression arg) {
    super(line, "+", arg);
  }

  /**
   * Analyzing the validation operation involves analyzing its operand, checking
   * its type and determining the result type.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    arg = arg.analyze(context);
    arg.type().mustMatchExpected(line(), Type.INT, Type.FLOAT);
    type = arg.type();
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    //output.token(TokenOz.PLUS); Not valid in Oz. But also not necessary...
    arg.codegen(output);
  }
}

/**
 * The AST node for a unary negation (-) expression.
 */
class OperationLogicalNot extends UnaryExpression {

  public OperationLogicalNot(int line, Expression arg) {
    super(line, "!", arg);
  }

  /**
   * Analyzing the negation operation involves analyzing its operand, checking
   * its type and determining the result type.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    arg = arg.analyze(context);
    arg.type().mustMatchExpected(line(), Type.BOOLEAN);
    type = Type.BOOLEAN;
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.LCURLY);
    output.literal(BuiltIns.NOT.ozString());
    output.space();
    arg.codegen(output);
    output.token(TokenOz.RCURLY);
  }
}
