package com.barassolutions;

import java.util.ArrayList;

public class List extends Pattern {

  private final ArrayList<Expression> args;

  private final boolean pipeStyle;

  public List(int line, ArrayList<Pattern> expressions, boolean usePipeStyle, boolean patterns) {
    super(line);
    this.args = new ArrayList<>(expressions);
    this.pipeStyle = usePipeStyle;
  }

  public List(int line, ArrayList<Expression> expressions, boolean usePipeStyle) {
    super(line);
    this.args = expressions;
    this.pipeStyle = usePipeStyle;
  }

  @Override
  public Expression analyze(Context context) {
    args.forEach(p -> p = (Pattern) p.analyze(context));

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if(pipeStyle) { // We received something like "(1::2::_::3)"
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
