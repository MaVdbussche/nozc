package com.barassolutions;

import java.util.ArrayList;

public class Tuple extends Pattern {

  private final ArrayList<Expression> args;

  public Tuple(int line, ArrayList<Pattern> expressions, boolean patterns) {
    super(line);
    this.args = new ArrayList<>(expressions);
  }

  public Tuple(int line, ArrayList<Expression> expressions) {
    super(line);
    this.args = expressions;
  }

  @Override
  public Expression analyze(Context context) {
    args.forEach(p -> p = (Expression) p.analyze(context));

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
