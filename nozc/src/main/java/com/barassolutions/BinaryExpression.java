package com.barassolutions;

import com.barassolutions.util.Utils;

public abstract class BinaryExpression extends Expression {

  /**
   * The binary operator.
   */
  protected String operator;

  /**
   * The lhs operand.
   */
  protected Expression lhs;

  /**
   * The rhs operand.
   */
  protected Expression rhs;

  /**
   * Construct an AST node for a binary expression given its line number, the binary operator, and
   * lhs and rhs operands.
   *
   * @param line     line in which the binary expression occurs in the source file.
   * @param operator the binary operator.
   * @param lhs      the lhs operand.
   * @param rhs      the rhs operand.
   */

  protected BinaryExpression(int line, String operator, Expression lhs, Expression rhs) {
    super(line);
    this.operator = operator;
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<BinaryExpression line=\"%d\" type=\"%s\" "
            + "operator=\"%s\">\n", line(), ((type == null) ? "" : type.toString()),
        Utils.escapeSpecialXMLChars(operator));
    p.indentRight();
    p.printf("<Lhs>\n");
    p.indentRight();
    lhs.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Lhs>\n");
    p.printf("<Rhs>\n");
    p.indentRight();
    rhs.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Rhs>\n");
    p.indentLeft();
    p.printf("</BinaryExpression>\n");
  }
}

/**
 * The AST node for a plus (+) expression. In NewOz, + is overloaded to denote addition for numbers
 * and concatenation for Strings.
 */
class OperationPlus extends BinaryExpression {

  private boolean isConcatenation = false;

  public OperationPlus(int line, Expression lhs, Expression rhs) {
    super(line, "+", lhs, rhs);
  }

  /**
   * Analysis involves first analyzing the operands. If this is a string concatenation, we rewrite
   * the subtree to make that explicit (and analyze that). Otherwise we check the types of the
   * addition operands and compute the result type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = lhs.analyze(context);
    rhs = rhs.analyze(context);
    if (lhs.type() == Type.STRING || rhs.type() == Type.STRING) {
      isConcatenation = true;
      return (new OperationStringConcatenation(line(), lhs, rhs)).analyze(context);
    } else if (lhs.type() == Type.INT && rhs.type() == Type.INT) {
      type = Type.INT;
    } else if (lhs.type() == Type.FLOAT && rhs.type() == Type.FLOAT) {
      type = Type.FLOAT;
    } else if (lhs.type == Type.ANY || rhs.type() == Type.ANY) {
      type = Type.ANY;
    } else {
      type = Type.ANY;
      interStatement.reportSemanticError(line(),
          "Invalid operand types for addition : " + lhs.type() + " and " + rhs.type());
    }
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (isConcatenation) {
      (new OperationStringConcatenation(line(), lhs, rhs)).codegen(output);
    } else {
      lhs.codegen(output);
      output.space();
      output.token(TokenOz.PLUS);
      output.space();
      rhs.codegen(output);
    }
  }
}

class OperationStringConcatenation extends OperationPlus {

  /**
   * Construct an AST node for a string concatenation expression given its line number, and the lhs
   * and rhs operands. An expression of this sort is created during the analysis of a (overloaded) +
   * operation (and not by the Parser).
   *
   * @param line line in which the expression occurs in the source file.
   * @param lhs  lhs operand.
   * @param rhs  rhs operand.
   */
  public OperationStringConcatenation(int line, Expression lhs, Expression rhs) {
    super(line, lhs, rhs);
  }

  /**
   * Analysis is simple here. The operands have already been analyzed (in
   * <code>OperationPlus</code>) so we simply set the result type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    //We checked before that both are strings
    type = Type.STRING;
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.token(TokenOz.HASHTAG);
    rhs.codegen(output);
  }
}

/**
 * The AST node for a minus (-) expression.
 */
class OperationMinus extends BinaryExpression {

  public OperationMinus(int line, Expression lhs, Expression rhs) {
    super(line, "-", lhs, rhs);
  }

  /**
   * Analysis involves analyzing the operands, checking types, and determining the result type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = lhs.analyze(context);
    rhs = rhs.analyze(context);
    if (lhs.type() == Type.INT && rhs.type() == Type.INT) {
      type = Type.INT;
    } else if (lhs.type() == Type.FLOAT && rhs.type() == Type.FLOAT) {
      type = Type.FLOAT;
    } else if (lhs.type == Type.ANY || rhs.type() == Type.ANY) {
      type = Type.ANY;
    } else {
      type = Type.ANY;
      interStatement.reportSemanticError(line(),
          "Invalid operand types for subtraction : " + lhs.type() + " and " + rhs.type());
    }
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    output.token(TokenOz.MINUS);
    output.space();
    rhs.codegen(output);
  }
}

/**
 * The AST node for a multiplication (*) expression.
 */
class OperationMultiplication extends BinaryExpression {

  public OperationMultiplication(int line, Expression lhs, Expression rhs) {
    super(line, "*", lhs, rhs);
  }

  /**
   * Analysis involves analyzing the operands, checking types, and determining the result type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = lhs.analyze(context);
    rhs = rhs.analyze(context);
    if (lhs.type() == Type.INT && rhs.type() == Type.INT) {
      type = Type.INT;
    } else if (lhs.type() == Type.FLOAT && rhs.type() == Type.FLOAT) {
      type = Type.FLOAT;
    } else if (lhs.type == Type.ANY || rhs.type() == Type.ANY) {
      type = Type.ANY;
    } else {
      type = Type.ANY;
      interStatement.reportSemanticError(line(),
          "Invalid operand types for multiplication : " + lhs.type() + " and " + rhs.type());
    }
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    output.token(TokenOz.STAR);
    output.space();
    rhs.codegen(output);
  }
}

/**
 * The AST node for a modulo (%) expression.
 */
class OperationModulo extends BinaryExpression {

  public OperationModulo(int line, Expression lhs, Expression rhs) {
    super(line, "%", lhs, rhs);
  }

  /**
   * Analysis involves analyzing the operands, checking types, and determining the result type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = lhs.analyze(context);
    rhs = rhs.analyze(context);
    if (lhs.type() == Type.INT && rhs.type() == Type.INT) {
      type = Type.INT;
    } else if (lhs.type == Type.ANY || rhs.type() == Type.ANY) {
      type = Type.ANY;
    } else {
      type = Type.ANY;
      interStatement.reportSemanticError(line(),
          "Invalid operand types for modulo : " + lhs.type() + " and " + rhs.type());
    }
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    output.token(TokenOz.MOD);
    output.space();
    rhs.codegen(output);
  }
}

/**
 * The AST node for a division (/) expression.
 */
class OperationDivision extends BinaryExpression {

  public OperationDivision(int line, Expression lhs, Expression rhs) {
    super(line, "/", lhs, rhs);
  }

  /**
   * Analysis involves analyzing the operands, checking types, and determining the result type.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    lhs = lhs.analyze(context);
    rhs = rhs.analyze(context);
    if (lhs.type() == Type.INT && rhs.type() == Type.INT) {
      type = Type.INT;
    } else if (lhs.type() == Type.FLOAT && rhs.type() == Type.FLOAT) {
      type = Type.FLOAT;
    } else if (lhs.type == Type.ANY || rhs.type() == Type.ANY) {
      type = Type.ANY;
    } else {
      type = Type.ANY;
      interStatement.reportSemanticError(line(),
          "Invalid operand types for division : " + lhs.type() + " and " + rhs.type());
    }
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    lhs.codegen(output);
    output.space();
    if (type.equals(Type.INT)) {
      output.token(TokenOz.DIV);
    } else if (type.equals(Type.FLOAT)) {
      output.token(TokenOz.SLASH);
    }
    output.space();
    rhs.codegen(output);
  }
}
