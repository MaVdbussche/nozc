package com.barassolutions;

import java.util.ArrayList;

public class List extends Pattern {

  private final boolean pipeStyle;
  private final boolean usedAsPattern;
  private final ArrayList<Expression> args;
  private final ArrayList<Pattern> patterns;

  public List(int line, ArrayList<Pattern> patterns, boolean usePipeStyle, boolean isAPattern) {
    super(line);
    this.patterns = patterns;
    this.args = null;
    this.pipeStyle = usePipeStyle;
    this.usedAsPattern = true;
  }

  public List(int line, ArrayList<Expression> expressions, boolean usePipeStyle) {
    super(line);
    this.patterns = null;
    this.args = expressions;
    this.pipeStyle = usePipeStyle;
    this.usedAsPattern = false;
  }

  @Override
  public Iterable<Pattern> patterns() {
    return this.patterns;
  }

  @Override
  public Expression analyze(Context context) {
    if (!usedAsPattern) {
      if (args != null) {
        args.forEach(a -> a = a.analyze(context));
      } else {
        interStatement.reportSemanticError(line(), "Ill-formed list found.");
      }
    } else {
      if (patterns != null) {
        patterns.forEach(p -> p = (Pattern) p.analyze(context));
      } else {
        interStatement.reportSemanticError(line(), "Ill-formed list found.");
      }
    }

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (pipeStyle) { // We received something like "(1::2::_::3)"
      if (args != null) {
        args.forEach(arg -> {
          arg.codegen(output);
          if (args.indexOf(arg) != args.size() - 1) {
            output.token(TokenOz.PIPE);
          }
        });
      } else if (patterns != null) {
        patterns.forEach(p -> {
          p.codegen(output);
          if (patterns.indexOf(p) != patterns.size() - 1) {
            output.token(TokenOz.PIPE);
          }
        });
      } //Ok because of checks done before
    } else { // We received something like "[1,2,_,3]"
      output.token(TokenOz.LBRACK);
      if (args != null) {
        args.forEach(arg -> {
          arg.codegen(output);
          if (args.indexOf(arg) != args.size() - 1) {
            output.space();
          }
        });
      } else if (patterns != null) {
        patterns.forEach(p -> {
          p.codegen(output);
          if (patterns.indexOf(p) != patterns.size() - 1) {
            output.space();
          }
        });
      } //Ok because of checks done before
      output.token(TokenOz.RBRACK);
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<List>\n");
    p.indentRight();
    if (args != null) {
      args.forEach(pat -> pat.writeToStdOut(p));
    } else if (patterns != null) {
      patterns.forEach(pat -> pat.writeToStdOut(p));
    } //Ok because of checks done before
    p.indentLeft();
    p.printf("</List>\n");
  }
}
