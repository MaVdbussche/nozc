package com.barassolutions.toCleanUp;

public class VariableDeclaration extends Statement {
    @Override
    public AST analyze(Context context) {
        return null;
    }

    @Override
    public void codegen(Emitter output) {

    }

    @Override
    public void writeOut(CustomPrinter p) {

    } //TODO or a subclass of it
}
