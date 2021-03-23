package com.barassolutions;

public class ExpressionSelectionFeature extends Expression {

  private final Variable selectedVariable;

  private final String selectedElement;

  public ExpressionSelectionFeature(int line, Variable var, String member) {
    super(line);
    this.selectedVariable = var;
    this.selectedElement = member;
  }


  @Override
  public Expression analyze(Context context) {
    interStatement.reportSemanticError(line(), "Class features are not supported in this release.");
    //TODO to implement in future release

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    //TODO to implement in future release
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<FeatureSelector line=\"%d\">\n", line());
    selectedVariable.writeToStdOut(p);
    p.println(selectedElement);
    p.printf("</FeatureSelector>\n");
  }

}
