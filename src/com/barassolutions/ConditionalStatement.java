// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions;

/**
 * The AST node for an if-statement.
 */

class ConditionalStatement extends Statement {

    /** Test expression. */
    private Expression condition;

    /** Then clause. */
    private Statement thenPart;

    /** Else clause. */
    private Statement elsePart;

    /**
     * Construct an AST node for an if-statement given its line number, the test
     * expression, the consequent, and the alternate.
     *
     * @param line
     *            line in which the if-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param thenPart
     *            then clause.
     * @param elsePart
     *            else clause.
     */
    public ConditionalStatement(int line, Expression condition, Statement thenPart,
                                Statement elsePart) {
        super(line);
        this.condition = condition;
        this.thenPart = thenPart;
        this.elsePart = elsePart;
    }

    /**
     * Analyzing the if-statement means analyzing its components and checking
     * that the test is boolean.
     *
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    public Statement analyze(Context context) {
        condition = (Expression) condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        thenPart = (Statement) thenPart.analyze(context);
        if (elsePart != null) {
            elsePart = (Statement) elsePart.analyze(context);
        }
        return this;
    }

    /**
     * Code generation for an if-statement. We generate code to branch over the
     * consequent if !test; the consequent is followed by an unconditonal branch
     * over (any) alternate.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .oz file).
     */
    public void codegen(Emitter output) {
        //TODO
    }

    /**
     * @inheritDoc
     */
    public void writeOut(CustomPrinter p) {
        p.printf("<JIfStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<ThenClause>\n");
        p.indentRight();
        thenPart.writeOut(p);
        p.indentLeft();
        p.printf("</ThenClause>\n");
        if (elsePart != null) {
            p.printf("<ElseClause>\n");
            p.indentRight();
            elsePart.writeOut(p);
            p.indentLeft();
            p.printf("</ElseClause>\n");
        }
        p.indentLeft();
        p.printf("</JIfStatement>\n");
    }

}
