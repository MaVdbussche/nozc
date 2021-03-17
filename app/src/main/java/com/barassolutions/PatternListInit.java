package com.barassolutions;

import java.util.ArrayList;

public class PatternListInit extends Pattern {

  private final ArrayList<Pattern> args;

  public PatternListInit(int line, ArrayList<Pattern> patterns) {
    super(line);
    this.args = patterns;
  }

  @Override
  public Expression analyze(Context context) {
    args.forEach(p -> p = (Pattern) p.analyze(context));

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.LBRACK);
    for (int i=0; i<args.size(); i++) {
      args.get(i).codegen(output);
      if(i!=args.size()-1) {
        output.space();
      }
    }
    output.token(TokenOz.RBRACK);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<PatternListInit>\n");
    p.indentRight();
    args.forEach(pat -> pat.writeToStdOut(p));
    p.indentLeft();
    p.printf("</PatternListInit>\n");
  }
}
