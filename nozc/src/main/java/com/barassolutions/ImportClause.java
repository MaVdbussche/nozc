package com.barassolutions;

import com.barassolutions.util.Utils;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

public class ImportClause extends Statement {

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

  public Map<String, Variable> getValues() {
    return map;
  }

  @Override
  public AST analyze(Context context) {
    // Imported values are added to the context in FunctorDef#analyze()
    map.forEach((k, v) -> {
      if (v!=null) {
        map.put(k, (Variable) v.analyze(context));
      }
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
        if(v!=null) {
        output.token(TokenOz.COLON);
          output.literal(Utils.ozFriendlyName(v.name()));
        }
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
    p.indentRight();
    map.forEach((k,v) -> {
      p.println(k);
      p.println(v.name());
      p.println();
    });
  }
}
