// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions.toCleanUp;

import com.barassolutions.Emitter;

abstract class Assignment extends BinaryExpression {

    /**
     * Construct an AST node for an assignment operation.
     *
     * @param line
     *            line in which the assignment operation occurs in the source
     *            file.
     * @param operator
     *            the actual assignment operator.
     * @param lhs
     *            the lhs operand.
     * @param rhs
     *            the rhs operand.
     */
    public Assignment(int line, String operator, Expression lhs, Expression rhs) {
        super(line, operator, lhs, rhs);
    }
}

/**
 * The AST node for an assignment (=) expression. The = operator has two
 * operands: a lhs and a rhs.
 */
class AssignOp extends Assignment {

    /**
     * Construct the AST node for an assignment (=) expression given the lhs and
     * rhs operands.
     *
     * @param line
     *            line in which the assignment expression occurs in the source
     *            file.
     * @param lhs
     *            lhs operand.
     * @param rhs
     *            rhs operand.
     */
    public AssignOp(int line, Expression lhs, Expression rhs) {
        super(line, "=", lhs, rhs);
    }

    /**
     * Analyze the lhs and rhs, checking that types match, and set the result
     * type.
     *
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */ //TODO check lhs if not final / a VAL ! Or if it is, make sure that is has no value yet.
    public Expression analyze(Context context) {
        if (!(lhs instanceof Lhs)) {
            AST.compilationBlock.reportSemanticError(line(),
                    "Illegal lhs for assignment");
        } else {
            lhs = (Expression) ((Lhs) lhs).analyzeLhs(context);
        }
        rhs = (Expression) rhs.analyze(context);
        rhs.type().mustMatchExpected(line(), lhs.type());
        type = rhs.type();
        if (lhs instanceof Variable) {
            IDefn defn = ((Variable) lhs).iDefn();
            if (defn != null) {
                // Local variable; consider it to be initialized now.
                ((LocalVariableDefn) defn).initialize();
            }
        }
        return this;
    }

    /**
     * Code generation for an assignment involves, generating code for loading
     * any necessary Lvalue onto the stack, for loading the Rvalue, for (unless
     * a statement) copying the Rvalue to its proper place on the stack, and for
     * doing the store.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .oz file).
     */
    public void codegen(Emitter output) {
        ((Lhs) lhs).codegenLoadLhsLvalue(output);
        rhs.codegen(output);
        if (!isStatementExpression) {
            // Generate code to leave the Rvalue atop stack
            ((Lhs) lhs).codegenDuplicateRvalue(output);
        }
        ((Lhs) lhs).codegenStore(output);
    }

}
