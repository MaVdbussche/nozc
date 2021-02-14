// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions.toCleanUp;

/**
 * The AST node for a binary expression, defined as an operator and two operands: a lhs and a rhs.
 */
abstract class BinaryExpression extends Expression {

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
     * Construct an AST node for a binary expression given its line number, the
     * binary operator, and lhs and rhs operands.
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

    /**
     * @inheritDoc
     */
    public void writeOut(CustomPrinter p) {
        p.printf("<BinaryExpression line=\"%d\" type=\"%s\" "
                        + "operator=\"%s\">\n", line(), ((type == null) ? "" : type.toString()),
                Utils.escapeSpecialXMLChars(operator));
        p.indentRight();
        p.printf("<Lhs>\n");
        p.indentRight();
        lhs.writeOut(p);
        p.indentLeft();
        p.printf("</Lhs>\n");
        p.printf("<Rhs>\n");
        p.indentRight();
        rhs.writeOut(p);
        p.indentLeft();
        p.printf("</Rhs>\n");
        p.indentLeft();
        p.printf("</BinaryExpression>\n");
    }
}

/**
 * The AST node for a plus (+) expression.
 */
class PlusOp extends BinaryExpression {

    /**
     * Construct an AST node for an addition expression given its line number,
     * and the lhs and rhs operands.
     *
     * @param line
     *            line in which the addition expression occurs in the source
     *            file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */
    public PlusOp(int line, Expression lhs, Expression rhs) {
        super(line, "+", lhs, rhs);
    }

    /**
     * Analysis involves first analyzing the operands. We check the types of the addition operands and compute
     * the result type.
     *
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    public Expression analyze(Context context) {
        lhs = (Expression) lhs.analyze(context);
        rhs = (Expression) rhs.analyze(context);
        if (lhs.type() == Type.INT && rhs.type() == Type.INT) {
            type = Type.INT;
        } else { //TODO add floats
            type = Type.ANY;
            AST.compilationBlock.reportSemanticError(line(), "Invalid operand types for +");
        }
        return this;
    }

    /**
     * Code generation here involves simply generating code for loading the operands and then generating the
     * appropriate add instruction.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .oz file).
     */
    public void codegen(Emitter output) { //TODO rework code emission format
        if (type == Type.INT) { //TODO add floats
            lhs.codegen(output);
            rhs.codegen(output);
            //output.addNoArgInstruction(IADD);
        }
    }

}

/**
 * The AST node for a subtraction (-) expression.
 */
class SubtractOp extends BinaryExpression {

    /**
     * Construct an AST node for a subtraction expression given its line number,
     * and lhs and rhs operands.
     *
     * @param line
     *            line in which the subtraction expression occurs in the source
     *            file.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */
    public SubtractOp(int line, Expression lhs, Expression rhs) {
        super(line, "-", lhs, rhs);
    }

    /**
     * Analyzing the - operation involves analyzing its operands, checking
     * types, and determining the result type.
     *
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    public Expression analyze(Context context) { //TODO add floats
        lhs = (Expression) lhs.analyze(context);
        rhs = (Expression) rhs.analyze(context);
        lhs.type().mustMatchExpected(line(), Type.INT);
        rhs.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    /**
     * Generating code for the - operation involves generating code for the two
     * operands, and then the subtraction instruction.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .oz file).
     */

    public void codegen(Emitter output) { //TODO rework code emission format
        lhs.codegen(output);  //TODO add floats
        rhs.codegen(output);
        output.addNoArgInstruction(ISUB);
    }

}

/**
 * The AST node for a multiplication (*) expression.
 */
class MultiplyOp extends BinaryExpression {

    /**
     * Construct an AST for a multiplication expression given its line number,
     * and the lhs and rhs operands.
     *
     * @param line line in which the multiplication expression occurs in the
     *             source file.
     * @param lhs  the lhs operand.
     * @param rhs  the rhs operand.
     */
    public MultiplyOp(int line, Expression lhs, Expression rhs) {
        super(line, "*", lhs, rhs);
    }

    /**
     * Analyzing the * operation involves analyzing its operands, checking
     * types, and determining the result type.
     *
     * @param context context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    public Expression analyze(Context context) { //TODO add floats
        lhs = (Expression) lhs.analyze(context);
        rhs = (Expression) rhs.analyze(context);
        lhs.type().mustMatchExpected(line(), Type.INT);
        rhs.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    /**
     * Generating code for the * operation involves generating code for the two
     * operands, and then the multiplication instruction.
     *
     * @param output the code emitter (basically an abstraction for producing the
     *               .oz file).
     */
    public void codegen(Emitter output) { //TODO same + add floats
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IMUL);
    }
}

/**
 * The AST node for a division (/) expression.
 */
class DivisionOp extends BinaryExpression {

    /**
     * Construct an AST for a division expression given its line number,
     * and the lhs and rhs operands.
     *
     * @param line line in which the multiplication expression occurs in the
     *             source file.
     * @param lhs  the lhs operand.
     * @param rhs  the rhs operand.
     */
    public DivisionOp(int line, Expression lhs, Expression rhs) {
        super(line, "*", lhs, rhs);
    }

    /**
     * Analyzing the / operation involves analyzing its operands, checking
     * types, and determining the result type.
     *
     * @param context context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    public Expression analyze(Context context) { //TODO add floats
        lhs = (Expression) lhs.analyze(context);
        rhs = (Expression) rhs.analyze(context);
        lhs.type().mustMatchExpected(line(), Type.INT);
        rhs.type().mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        return this;
    }

    /**
     * Generating code for the / operation involves generating code for the two
     * operands, and then the division instruction.
     *
     * @param output the code emitter (basically an abstraction for producing the
     *               .oz file).
     */
    public void codegen(Emitter output) { //TODO same + add floats
        lhs.codegen(output);
        rhs.codegen(output);
        output.addNoArgInstruction(IMUL);
    }
}