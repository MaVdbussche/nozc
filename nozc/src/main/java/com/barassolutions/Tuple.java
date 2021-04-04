package com.barassolutions;

import java.util.ArrayList;

public class Tuple extends Pattern {

  private ArrayList<Expression> args;
  private ArrayList<Pattern> patterns;

  private final boolean usedAsPattern;

  public Tuple(int line, ArrayList<Pattern> patterns, boolean isAPattern) {
    super(line);
    this.patterns = patterns;
    this.usedAsPattern = isAPattern;
  }

  public Tuple(int line, ArrayList<Expression> expressions) {
    super(line);
    this.args = expressions;
    this.usedAsPattern = false;
  }

  @Override
  public Iterable<Pattern> patterns() {
    return this.patterns;
  }

  @Override
  public Expression analyze(Context context) {
    if(!usedAsPattern) {
      args.forEach(a -> a = (Expression) a.analyze(context));
    } else {
      patterns.forEach(p -> p = (Pattern) p.analyze(context));
    }

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    for (int i=0; i<args.size(); i++) {
      args.get(i).codegen(output);
      if(i!=args.size()-1) {
        output.token(TokenOz.HASHTAG);
      }
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Tuple>\n");
    p.indentRight();
    args.forEach(pat -> pat.writeToStdOut(p));
    p.indentLeft();
    p.printf("</Tuple>\n");
  }
}
