package com.barassolutions;

import java.util.Map;

import org.jetbrains.annotations.Nullable;

public class ImportClause extends Statement implements DeclarationToDeleteMaybe {

  /**
   * Name of the variable to import.
   */
  private final String name;

  /**
   * Map of the feature to import and the name to give them in this context.
   */
  private final Map<String, Variable> map;

  /**
   * Optional source of this import.
   */
  @Nullable
  private final String source;

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
    map.forEach((k, v) -> {
      map.put(k, (Variable) v.analyze(context));
    });

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.literal(name);
    if (map.size() > 0) {
      output.token(TokenOz.LPAREN);
      map.forEach((s, v) -> {
        output.space();
        output.literal(s);
        output.token(TokenOz.COLON);
        output.literal(Utils.ozFriendlyName(v.name()));
      });
      output.token(TokenOz.RPAREN);
    }

    if (source != null) {
      output.space();
      output.token(TokenOz.AT);
      output.space();
      output.literal(source);
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Import name=\"%s\"/>\n", name);
    //TODO come on, you know there is more to it
  }
}
