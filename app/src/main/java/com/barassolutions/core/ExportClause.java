package com.barassolutions.core;

import com.barassolutions.TokenOz;
import java.util.Map;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import org.jetbrains.annotations.Nullable;

public class ExportClause extends Statement {

  /**
   * Optional name under which the variable will be made available.
   */
  @Nullable
  private String name;

  /**
   * Variable to export.
   */
  private Variable var;

  public ExportClause(int line, @Nullable String label, Variable var) {
    super(line);
    this.name = label;
    this.var = var;
  }

  //TODO we don't actually check for presence the variable in the context, since it probably hasn't been declared at this time.
  // Best would be to defer the check until later (how?). For now we just let it slip through, ozc will catch it anyway
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
    output.literal(var.name());
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Export name=\"%s\" alias=\"%s\"/>\n", var.name(), name);
  }
}
