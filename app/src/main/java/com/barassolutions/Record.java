package com.barassolutions;

import java.util.HashMap;
import java.util.Map;

public class Record extends Term {

  private final String name;

  private final Map<Feature, Expression> members;

  private final boolean hasMoreFeatures;

  public Record(int line, String name, Map<Feature, Pattern> map, boolean hasMore, boolean patterns) {
    super(line);
    this.name = name;
    this.members = new HashMap<>(map);
    this.hasMoreFeatures = hasMore;
  }

  public Record(int line, String name, Map<Feature, Expression> map, boolean hasMore) {
    super(line);
    this.name = name;
    this.members = map;
    this.hasMoreFeatures = hasMore;
  }

  @Override
  public Expression analyze(Context context) {
    //TODO make sure this name does not exist in this context. If it exists in parent context, shadow it.

    members.values().forEach(v -> v = (Expression) v.analyze(context));

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.literal(name);
    output.token(TokenOz.LPAREN);
    members.forEach((k,v) -> {
      k.codegen(output);
      output.token(TokenOz.COLON);
      v.codegen(output);
      output.space();
    });
    if(hasMoreFeatures) {
      output.token(TokenOz.ELLIPSIS);
    }
    output.token(TokenOz.RPAREN);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Record>\n");
    p.indentRight();
    members.forEach((k,v) -> {
      k.writeToStdOut(p);
      v.writeToStdOut(p);
      p.println();
    });
    if(hasMoreFeatures) {
      p.println("...");
    }
    p.indentLeft();
    p.printf("</Record>\n");
  }
}
