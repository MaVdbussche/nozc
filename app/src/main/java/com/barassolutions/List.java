package com.barassolutions;

import java.util.ArrayList;

public class List extends Pattern {

  private final boolean pipeStyle;
  private final boolean usedAsPattern;
  private ArrayList<Expression> args;
  private ArrayList<Pattern> patterns;

  public List(int line, ArrayList<Pattern> patterns, boolean usePipeStyle, boolean isAPattern) {
    super(line);
    this.patterns = patterns;
    this.pipeStyle = usePipeStyle;
    this.usedAsPattern = isAPattern;
  }

  public List(int line, ArrayList<Expression> expressions, boolean usePipeStyle) {
    super(line);
    this.args = expressions;
    this.pipeStyle = usePipeStyle;
    this.usedAsPattern = false;
  }

  @Override
  public Expression analyze(Context context) {
    if (!usedAsPattern) {
      args.forEach(a -> a = (Expression) a.analyze(context));
    } else {
      patterns.forEach(p -> p = (Pattern) p.analyze(context));
    }

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (pipeStyle) { // We received something like "(1::2::_::3)"
      args.forEach(arg -> {
        arg.codegen(output);
        output.token(TokenOz.PIPE);
      });
      output.token(TokenOz.NIL);
    } else { // We received something like "[1,2,_,3]"
      output.token(TokenOz.LBRACK);
      args.forEach(arg -> {
        arg.codegen(output);
        output.space();
      });
      output.token(TokenOz.RBRACK);
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<List>\n");
    p.indentRight();
    args.forEach(pat -> pat.writeToStdOut(p));
    p.indentLeft();
    p.printf("</List>\n");
  }
}
