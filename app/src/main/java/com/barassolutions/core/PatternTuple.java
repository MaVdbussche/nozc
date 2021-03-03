package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;

public class PatternTuple extends Pattern {

  private final ArrayList<Pattern> args;

  public PatternTuple(int line, ArrayList<Pattern> patterns) {
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
    for (int i=0; i<args.size(); i++) {
      args.get(i).codegen(output);
      if(i!=args.size()-1) {
        output.token(TokenOz.HASHTAG);
      }
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<PatternTuple>\n");
    p.indentRight();
    args.forEach(pat -> pat.writeToStdOut(p));
    p.indentLeft();
    p.printf("</PatternTuple>\n");
  }
}
