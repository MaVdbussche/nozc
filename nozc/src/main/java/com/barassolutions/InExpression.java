package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class InExpression extends Expression {

  /**
   * List of variables/values/procedures/functions/functors/classes declared in this block.
   */
  private final ArrayList<Declaration> declarations;

  /**
   * Optional statement.
   */
  @Nullable
  private Statement statement;

  /**
   * Expression to evaluate.
   */
  @Nullable
  private Expression expression;

  /**
   * The context represented by this block.
   */
  private Context context;

  /**
   * Construct an AST node for a block given its line number, and the list of statements and/or
   * expressions forming the block body.
   *
   * @param line       line in which the block occurs in the source file.
   * @param statement  statement present in the block body.
   * @param expression optional expression present in the block body.
   */
  public InExpression(int line, ArrayList<Declaration> decls, @Nullable Statement statement,
      @Nullable Expression expression) {
    super(line);
    this.declarations = decls;
    this.statement = statement;
    this.expression = expression;
  }

  /**
   * Analyzing a block consists of creating a new nested context for that block and analyzing each
   * of its statements/expressions within that context.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    this.context = new Context(context);

    declarations.forEach(e -> e = (Declaration) e.analyze(this.context));

    if (statement != null) {
      statement = (Statement) statement.analyze(this.context);
    } else if (expression != null) {
      expression = expression.analyze(this.context);
    }
    return this;
  }

  /**
   * Generating code for a block consists of generating code for each of its declarations and
   * statements/expressions.
   *
   * @param output the code emitter (basically an abstraction for producing the Oz file).
   */
  @Override
  public void codegen(Emitter output) {
    declarations.forEach(e -> e.codegen(output));
    if (declarations.size() > 0) {
      output.indentLeft();
      output.token(TokenOz.IN);
      output.newLine();
      output.indentRight();
    }
    if (statement != null) {
      statement.codegen(output);
    } else if (expression != null) {
      expression.codegen(output);
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ExpressionBlock line=\"%d\">\n", line());
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
    if (statement != null) {
      p.indentRight();
      statement.writeToStdOut(p);
      p.indentLeft();
    } else if (expression != null) {
      p.indentRight();
      expression.writeToStdOut(p);
      p.indentLeft();
    }

    p.printf("</ExpressionBlock>\n");
  }
}
