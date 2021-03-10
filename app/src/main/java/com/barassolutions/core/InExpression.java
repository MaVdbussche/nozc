package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class InExpression extends Expression {

  /**
   * List of variables/values/procedures/functions/functors/classes declared in this block.
   */
  private ArrayList<DeclarationPart> declarationParts;

  /** Optional statement. */
  private Statement statement;

  /**
   * Expresion to evaluate.
   */
  @Nullable
  private Expression expression;

  /**
   * The context represented by this block.
   */
  private LocalContext context;

  /**
   * Construct an AST node for a block given its line number, and the list of
   * statements and/or expressions forming the block body.
   *
   * @param line
   *            line in which the block occurs in the source file.
   * @param statement
   *            statement present in the block body.
   * @param expression
   *            optional expression present in the block body.
   */
  public InExpression(int line, ArrayList<DeclarationPart> decls, Statement statement, @Nullable Expression expression) {
    super(line);
    this.declarationParts = decls;
    this.statement = statement;
    this.expression = expression;
  }

  /**
   * Analyzing a block consists of creating a new nested context for that
   * block and analyzing each of its statements/expressions within that context.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public Expression analyze(Context context) {
    this.context = new LocalContext(context);

    declarationParts.forEach(e -> e = (DeclarationPart) e.analyze(this.context));

    statement = (Statement) statement.analyze(this.context);

    if(expression!=null) {
      expression = (Expression) expression.analyze(this.context);
    }
    return this;
  }

  /**
   * Generating code for a block consists of generating code for each of its declarations and
   * statements/expressions.
   *
   * @param output
   *            the code emitter (basically an abstraction for producing the
   *            Oz file).
   */
  @Override
  public void codegen(Emitter output) {
    declarationParts.forEach(e -> e.codegen(output));
    output.indentLeft();
    output.token(TokenOz.IN);
    output.newLine();
    output.indentRight();
    statement.codegen(output);
    if(expression!=null) {
      expression.codegen(output);
    }
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ExpressionBlock line=\"%d\">\n", line());
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
    p.indentRight();
    statement.writeToStdOut(p);
    p.indentLeft();

    if(expression!=null) {
      p.indentRight();
      expression.writeToStdOut(p);
      p.indentLeft();
    }

    p.printf("</ExpressionBlock>\n");
  }
}
