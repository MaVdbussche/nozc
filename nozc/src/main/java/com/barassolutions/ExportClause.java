package com.barassolutions;

import com.barassolutions.util.Utils;
import org.jetbrains.annotations.Nullable;

public class ExportClause extends Statement {

  /**
   * Optional name under which the variable will be made available.
   */
  @Nullable
  private final String name;

  /**
   * Variable to export.
   */
  private final Variable var;

  public ExportClause(int line, @Nullable String label, Variable var) {
    super(line);
    this.name = label;
    this.var = var;
  }

  public Variable exportedValue() {
    return var;
  }

 @Override
  public AST analyze(Context context) {
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (name != null) {
      output.literal(name);
      output.token(TokenOz.COLON);
    }
    output.literal(Utils.ozFriendlyName(var.name()));
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Export name=\"%s\" alias=\"%s\"/>\n", var.name(), name);
  }
}
