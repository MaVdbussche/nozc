package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;

public class LoopStructure extends Statement {

  /** The loop declarations */
  private ArrayList<LoopDec> loopDecs;

  /** The body of the loop */
  private InStatement statement;

  /**
   * Construct an AST node for a loop given its line number, the loop declarations, and the
   * statement block.
   *
   * @param line       line in which the if-statement occurs in the source file.
   * @param loopDecs   loop declaration(s).
   * @param statement  statement block executed in the loop.
   */
  public LoopStructure(int line, ArrayList<LoopDec> loopDecs, InStatement statement) {
    super(line);
    this.loopDecs = loopDecs;
    this.statement = statement;
  }

  /**
   * Analyzing the if-statement means analyzing its components.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context context) {
    loopDecs.forEach(l -> l = (LoopDec) l.analyze(context));

    statement = (InStatement) statement.analyze(context);
    return this;
  }

  /**
   * Perform code generation for this AST.
   *
   * @param output the code emitter (basically an abstraction for producing the oz file).
   */
  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.FOR);
    output.space();
    loopDecs.forEach(l -> {
      l.codegen(output);
      output.space();
    });
    output.token(TokenOz.DO);
    statement.codegen(output);
    output.token(TokenOz.END);
    output.newLine();
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<LoopStatement line=\"%d\">\n", line());
    p.indentRight();
    loopDecs.forEach(l -> {
      p.printf("<LoopDec>\n");
      p.indentRight();
      l.writeToStdOut(p);
      p.indentLeft();
      p.printf("</LoopDec>\n");
    });
    p.printf("<Body>\n");
    p.indentRight();
    statement.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Body>\n");
    p.indentLeft();
    p.printf("</LoopStatement>\n");
  }
}
