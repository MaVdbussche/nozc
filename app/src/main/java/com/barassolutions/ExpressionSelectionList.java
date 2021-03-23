package com.barassolutions;

public class ExpressionSelectionList extends Expression {

  private Variable selectedVariable;

  private Expression selectedElement;

  public ExpressionSelectionList(int line, Variable var, Expression element) {
    super(line);
    this.selectedVariable = var;
    this.selectedElement = element;
  }


  @Override
  public Expression analyze(Context context) {
    selectedVariable = (Variable) selectedVariable.analyze(context);
    selectedElement = (Expression) selectedElement.analyze(context);

    if (selectedVariable.type()!=Type.LIST) {
      interStatement.reportSemanticError(line(), "Expected a list at this position for variable \""+selectedVariable.name()+"\"");
    }
    if (selectedElement.type()!=Type.INT) {
      interStatement.reportSemanticError(line(), "Expected an expression valued to \"1\" or \"2\" at this position when accessing variable \""+selectedVariable.name()+"\"");
    }

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    selectedVariable.codegen(output);
    output.token(TokenOz.DOT);
    selectedElement.codegen(output);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ListSelector line=\"%d\">", line());
    selectedVariable.writeToStdOut(p);
    selectedElement.writeToStdOut(p);
    p.printf("</ListSelector>");
  }
}
