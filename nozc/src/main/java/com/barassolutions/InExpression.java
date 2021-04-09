package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class InExpression extends Expression {

  /**
   * List of variables/values/procedures/functions/functors/classes declared in this block.
   */
  private final ArrayList<Declaration> declarations;

  /**
   * Optional statements.
   */
  private final ArrayList<Statement> statements;

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
   * @param statements statements present in the block body.
   * @param expression optional expression present in the block body.
   */
  public InExpression(int line, ArrayList<Declaration> decls, ArrayList<Statement> statements,
      @Nullable Expression expression) {
    super(line);
    this.declarations = decls;
    this.statements = statements;
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

    statements.forEach(s -> s = (Statement) s.analyze(this.context));

    if (expression != null) {
      expression = expression.analyze(this.context);
      this.type = expression.type();
    } else if (statements.get(statements.size() - 1) instanceof CallProcedure cp) {
      if (cp.isActuallyAFunction()) {
        this.type = cp.returnType();
      } else {//TODO ugly hack to remove once we merge CallFunction and CallProcedure
        interStatement.reportSemanticError(line(), "Missing expression or return value in block");
      }
    } else {
      interStatement.reportSemanticError(line(), "Missing expression or return value in block");
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
    if (declarations.size() > 0) {
      output.token(TokenOz.LOCAL);
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
      statements.forEach(s -> s.codegen(output));
    }

    if (expression != null) {
      expression.codegen(output);
    }
    output.indentLeft();
    if (declarations.size() > 0) {
      output.token(TokenOz.END);
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
    p.indentRight();
    declarations.forEach(decl -> decl.writeToStdOut(p));
    statements.forEach(s -> s.writeToStdOut(p));
    if (expression != null) {
      expression.writeToStdOut(p);
    }
    p.indentLeft();

    p.printf("</ExpressionBlock>\n");
  }
}
