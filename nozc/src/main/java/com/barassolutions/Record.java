package com.barassolutions;

import java.util.Map;

public class Record extends Pattern {

  private final String name;

  private Map<Feature, Expression> members;
  private Map<Feature, Pattern> patterns;

  private final boolean hasMoreFeatures;

  private final boolean usedAsPattern;

  public Record(int line, String name, Map<Feature, Pattern> map, boolean hasMore, boolean isAPattern) {
    super(line);
    this.name = name;
    this.patterns = map;
    this.hasMoreFeatures = hasMore;
    this.usedAsPattern = isAPattern;
  }

  public Record(int line, String name, Map<Feature, Expression> map, boolean hasMore) {
    super(line);
    this.name = name;
    this.members = map;
    this.hasMoreFeatures = hasMore;
    this.usedAsPattern = false;
  }

  public String name() {
    return name;
  }

  @Override
  public Iterable<Pattern> patterns() {
    return this.patterns.values();
  }

  @Override
  public Expression analyze(Context context) {
    if(!usedAsPattern) {
      Record var = context.recordFor(name);
      if(var==null) {
        interStatement.reportSemanticError(line(),
            "Could not find record for: <name:"+name+">");
      } else {
        members.values().forEach(v -> v = (Expression) v.analyze(context));
      }
    } else {
      patterns.values().forEach(p -> p = (Pattern) p.analyze(context));
    }

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
