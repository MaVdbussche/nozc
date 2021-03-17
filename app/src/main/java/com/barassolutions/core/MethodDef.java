package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import com.barassolutions.Utils;
import org.jetbrains.annotations.Nullable;

public class MethodDef extends Declaration implements ClassElement {

  private final MethodHead head;

  @Nullable
  private final String aliasName;

  /**
   * The statement/expression constituting the method's body.
   */
  @Nullable
  private final InStatement statement;
  @Nullable
  private final InExpression expression;

  private final boolean isAFunction;

  public MethodDef(int line, MethodHead head, @Nullable Variable name,
      @Nullable InExpression expression, @Nullable InStatement statement) {
    super(line);
    assert (expression != null || statement != null);
    isAFunction = statement == null;

    this.head = head;
    this.statement = statement;
    this.expression = expression;

    if (name != null) {
      this.aliasName = name.name();
    } else {
      aliasName = null;
    }
  }

  @Override
  public AST analyze(Context context) {
    //TODO declare name in the context if it doesn't exist yet

    head.args().forEach(a -> a = a.analyze(context));

    MethodContext methContext = new MethodContext(context);
    // TODO create this method's inner context and add args to it (shadow if necessary)

    if (statement != null) {
      statement = (InStatement) statement
          .analyze(methContext);
    } else if (expression != null) {
      expression = (InExpression) expression.analyze(methContext);
    }
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.METH);
    output.space();
    output.literal(head.isPrivate() ? Utils.ozFriendlyName(head.name()) : head.name());
    output.token(TokenOz.LPAREN);
    head.args().forEach(a -> {
      a.codegen(output);
      output.space();
    });
    if(head.hasMorArgs()) {
      output.token(TokenOz.ELLIPSIS);
      output.space();
    }
    if (isAFunction) {
      output.token(TokenOz.DOLLAR);
    }
    output.token(TokenOz.RPAREN);

    if(aliasName!=null) {

    }

    output.newLine();
    output.indentRight();
    if (statement != null) {
      statement.codegen(output);
    } else if (expression != null) {
      expression.codegen(output);
    }
    output.newLine();
    output.indentLeft();
    output.token(TokenOz.END);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<MethodDeclaration line=\"%d\" name=\"%s\" alias=\"%s\" function?=\"%b\" procedure?=\"%b\">\n",
        line(), head.name(), aliasName, isAFunction, !isAFunction);
    p.indentRight();
    head.args().forEach(a -> {
      a.writeToStdOut(p);
    });

    if (statement != null) {
      statement.writeToStdOut(p);
    } else if (expression != null) {
      expression.writeToStdOut(p);
    }

    p.indentLeft();
    p.printf("</MethodDeclaration>\n");
  }
}
