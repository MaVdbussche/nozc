package com.barassolutions;

import java.util.ArrayList;

public class InStatement extends Statement {

  /**
   * List of variables/values/procedures/functions/functors/classes declared in this block.
   */
  protected final ArrayList<Declaration> declarations;

  /**
   * List of statements forming the block body.
   */
  protected final ArrayList<Statement> statements;

  /**
   * The context represented by this block.
   */
  private LocalContext context;

  /**
   * Construct an AST node for a block given its line number, and the list of statements forming the
   * block body.
   *
   * @param line       line in which the block occurs in the source file.
   * @param decls      declarations appearing in the block body
   * @param statements list of statements forming the block body.
   */
  public InStatement(int line, ArrayList<Declaration> decls, ArrayList<Statement> statements) {
    super(line);
    this.declarations = decls;
    this.statements = statements;
  }

  /**
   * Analyzing a block consists of creating a new nested context for that block and analyzing each
   * of its statements within that context.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context context) {
    this.context = new LocalContext(context);

    declarations.forEach(e -> e = (Declaration) e.analyze(this.context));

    statements.forEach(e -> e = (Statement) e.analyze(this.context));

    return this;
  }

  /**
   * Generating code for a block consists of generating code for each of its declarations and
   * statements.
   *
   * @param output the code emitter (basically an abstraction for producing the Oz file).
   */
  @Override
  public void codegen(Emitter output) {
    declarations.forEach(e -> e.codegen(output));
    output.indentLeft();
    output.token(TokenOz.IN);
    output.newLine();
    output.indentRight();
    statements.forEach(e -> e.codegen(output));
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<StatementBlock line=\"%d\">\n", line());
    if (context != null) {
      p.indentRight();
      context.writeToStdOut(p);
      p.indentLeft();
    }
    for (Declaration decl : declarations) {
      p.indentRight();
      decl.writeToStdOut(p);
      p.indentLeft();
    }
    for (Statement statement : statements) {
      p.indentRight();
      statement.writeToStdOut(p);
      p.indentLeft();
    }
    p.printf("</StatementBlock>\n");
  }
}
