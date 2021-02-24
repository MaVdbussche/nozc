package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import java.util.ArrayList;

public class InStatement extends Statement {

  /**
   * List of variables/values/procedures/functions/functors/classes declared in this block.
   */
  private ArrayList<DeclarationPart> declarationParts;

  /** List of statements forming the block body. */
  private ArrayList<Statement> statements;

  /**
   * The context represented by this block.
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
  public InStatement(int line, ArrayList<DeclarationPart> decls, ArrayList<Statement> statements) {
    this.line = line;
    this.declarationParts = decls;
    this.statements = statements;
  }

  /**
   * Analyzing a block consists of creating a new nested context for that
   * block and analyzing each of its statements within that context.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context context) {
    this.context = new LocalContext(context);

    declarationParts.forEach(e -> e = (DeclarationPart) e.analyze(this.context));

    statements.forEach(e -> e = (Statement) e.analyze(this.context));

    return this;
  }

  /**
   * Generating code for a block consists of generating code for each of its declarations and
   * statements.
   *
   * @param output
   *            the code emitter (basically an abstraction for producing the
   *            Oz file).
   */
  @Override
  public void codegen(Emitter output) {
    output.newLine();
    output.indentRight();
    //TODO do local..in..end only if there are declarations. Otherwise current scope is enough
    //TODO codegen LOCAL
    declarationParts.forEach(e -> e.codegen(output));
    //TODO codegen IN
    statements.forEach(e -> e.codegen(output));
    //TODO codegen END
    output.newLine();
    output.indentLeft();
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Block line=\"%d\">\n", line());
    if (context != null) {
      p.indentRight();
      context.writeToStdOut(p);
      p.indentLeft();
    }
    for (DeclarationPart decl : declarationParts) {
      p.indentRight();
      decl.writeToStdOut(p);
      p.indentLeft();
    }
    for (Statement statement : statements) {
      p.indentRight();
      statement.writeToStdOut(p);
      p.indentLeft();
    }
    p.printf("</Block>\n");
  }
}
