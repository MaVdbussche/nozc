package com.barassolutions;

import java.util.ArrayList;

/**
 * In Oz, loops have the following form :
 * <code>
 * for <it>Declarations</it> do ... end
 * </code>
 * where Declarations is a sequence of 0 or more iterator and feature declarations. An iterator has
 * the form: <code>Pat in Generator</code> where Generator describes how to generate the successive
 * values for pattern Pat whose variables are local to the loop. The generators are stepped in
 * parallel and the loop terminates as soon as one of the generators runs out of values.
 *
 * @see LoopDeclaration
 */
public class LoopStructure extends Statement {

  /**
   * The loop declarations
   */
  private ArrayList<LoopDeclaration> loopDecs;

  /**
   * The body of the loop
   */
  private InStatement statement;

  /**
   * Construct an AST node for a loop given its line number, the loop declarations, and the
   * statement block.
   *
   * @param line      line in which the if-statement occurs in the source file.
   * @param loopDecs  loop declaration(s).
   * @param statement statement block executed in the loop.
   */
  public LoopStructure(int line, ArrayList<LoopDeclaration> loopDecs, InStatement statement) {
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
    loopDecs.forEach(l -> l = (LoopDeclaration) l.analyze(context));

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

    loopDecs.forEach(l -> l.writeToStdOut(p));
    p.printf("<Body>\n");
    p.indentRight();
    statement.writeToStdOut(p);
    p.indentLeft();
    p.printf("</Body>\n");

    p.indentLeft();
    p.printf("</LoopStatement>\n");
  }
}
