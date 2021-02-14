package com.barassolutions.toCleanUp;

import java.util.ArrayList;

/**
 * The AST node for a block, which delimits a nested level of scope.
 */
public class Block extends Statement {

  /** List of statements forming the block body. */
  private final ArrayList<Statement> statements;

  /**
   * The new context (built in analyze()) represented by this block.
   */
  private LocalContext context;

  /**
   * Construct an AST node for a block given its line number, and the list of
   * statements forming the block body.
   *
   * @param line
   *            line in which the block occurs in the source file.
   * @param statements
   *            list of statements forming the block body.
   */
  public Block(int line, ArrayList<Statement> statements) {
    super(line);
    this.statements = statements;
  }

  /**
   * Return the list of statements comprising the block.
   *
   * @return list of statements.
   */
  public ArrayList<Statement> statements() {
    return statements;
  }

  /**
   * Analyzing a block consists of creating a new nested context for that
   * block and analyzing each of its statements within that context.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  public Block analyze(Context context) {
    // { ... } defines a new level of scope.
    this.context = new LocalContext(context);

    for (int i = 0; i < statements.size(); i++) {
      statements.set(i, (Statement) statements.get(i).analyze(
          this.context));
    }
    return this;
  }

  /**
   * Generating code for a block consists of generating code for each of its
   * statements.
   *
   * @param output
   *            the code emitter (basically an abstraction for producing the
   *            .oz file).
   */
  public void codegen(Emitter output) {
    for (Statement statement : statements) {
      statement.codegen(output);
    }
  }

  /**
   * @inheritDoc
   */
  public void writeOut(CustomPrinter p) {
    p.printf("<Block line=\"%d\">\n", line());
    if (context != null) {
      p.indentRight();
      context.writeOut(p);
      p.indentLeft();
    }
    for (Statement statement : statements) {
      p.indentRight();
      statement.writeOut(p);
      p.indentLeft();
    }
    p.printf("</Block>\n");
  }
}
