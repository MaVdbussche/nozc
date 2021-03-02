package com.barassolutions.core;

import com.barassolutions.TokenOz;
import java.util.Map;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import org.jetbrains.annotations.Nullable;

public class ImportClause extends Statement implements Declaration {

  /**
   * Name of the variable to import.
   */
  private String name;

  /**
   * Map of the feature to import and to name to give them in this context.
   */
  private Map<String, Variable> map;

  /**
   * Optional source of this import.
   */
  @Nullable
  private String source;

  public ImportClause(int line, String name, Map<String, Variable> map, @Nullable String source) {
    super(line);
    this.name = name;
    this.map = map;
    this.source = source;
  }

  @Override
  public void preAnalyze(Context context, Emitter partial) {
    //TODO ensure newly declared names do not already exist in this context.
    // Otherwise add them to the context as normal
  }

  @Override
  public AST analyze(Context context) {
    map.forEach((k,v) -> {
      map.put(k,(Variable) v.analyze(context));
    });

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.literal(name);
    if (map.size() != 0) {
      output.token(TokenOz.LPAREN);
      map.forEach((s, v) -> {
        output.space();
        output.literal(s);
        output.token(TokenOz.COLON);
        output.literal(v.name());
      });
      output.token(TokenOz.RPAREN);
    }
    output.space();

    if (source != null) {
      output.token(TokenOz.AT);
      output.space();
      output.literal(source);
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Import name=\"%s\"/>\n", name);
  }
}
