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

  private final boolean declareMode;

  /**
   * The context represented by this block.
   */
  private Context context;

  /**
   * Construct an AST node for a block given its line number, and the list of statements forming the
   * block body.
   *
   * @param line       line in which the block occurs in the source file.
   * @param decls      declarations appearing in the block body
   * @param statements list of statements forming the block body.
   */
  public InStatement(int line, ArrayList<Declaration> decls, ArrayList<Statement> statements, boolean declarePresent) {
    super(line);
    this.declarations = decls;
    this.statements = statements;
    this.declareMode = declarePresent;
  }

  public ArrayList<Declaration> decls() {
    return declarations;
  }

  public ArrayList<Statement> statements() {
    return statements;
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
    this.context = new Context(context);

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
    if (declarations.size()>0) {
      if (declareMode) {
        output.token(TokenOz.DECLARE);
      }
      else {
        output.token(TokenOz.LOCAL);
      }
      output.newLine();
      output.indentRight();
      declarations.forEach(e -> {
        e.codegen(output);
        //if (declarations.indexOf(e) != declarations.size() - 1) {
          //output.newLine();
        //}
      });
      output.indentLeft();
      output.token(TokenOz.IN);
      output.newLine();
      output.indentRight();
    }
    if (statements.size() > 0) {
      statements.forEach(e -> {
        e.codegen(output);
        output.newLine();
      });
    } else {
      output.token(TokenOz.SKIP);
    }
    output.indentLeft();
    if ((!declareMode) && declarations.size()>0) {
      output.token(TokenOz.END);
    }
  }

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
