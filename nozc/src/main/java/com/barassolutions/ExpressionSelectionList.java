package com.barassolutions;

public class ExpressionSelectionList extends Expression {

  private Variable selectedVariable;

  private Expression target;

  public ExpressionSelectionList(int line, Variable var, Expression element) {
    super(line);
    this.selectedVariable = var;
    this.target = element;
  }


  @Override
  public Expression analyze(Context context) {
    selectedVariable = (Variable) selectedVariable.analyze(context);
    target = target.analyze(context);
    this.type = target.type();

    //We can't actually enable this check, since we have no way of knowing, when we add it to the context, if the var is a List or something else (there are exceptions but overall we can't be always sure)
    //if (! (selectedVariable.type()==Type.LIST || selectedVariable.type()==Type.UNKNOWN)) {
    //  interStatement.reportSemanticError(line(), "Expected a list at this position for variable \""+selectedVariable.name()+"\"");
    //}
    if (target.type() != Type.INT) {
      interStatement.reportSemanticError(line(),
          "Expected an expression valued to \"1\" or \"2\" at this position when accessing variable \""
              + selectedVariable.name() + "\"");
    }

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    selectedVariable.codegen(output);
    output.token(TokenOz.DOT);
    target.codegen(output);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ListSelector line=\"%d\">", line());
    selectedVariable.writeToStdOut(p);
    target.writeToStdOut(p);
    p.printf("</ListSelector>");
  }
}
