package com.barassolutions;

import org.jetbrains.annotations.Nullable;

public class MethodDef extends Declaration implements ClassElement {

  private final MethodHead head;

  @Nullable
  private final String aliasName;

  /**
   * The statement/expression constituting the method's body.
   */
  @Nullable
  private InStatement statement;
  @Nullable
  private InExpression expression;

  private final boolean isAFunction;

  /**
   * Only makes sense if this is a function, of course.
   */
  @Nullable
  private Type returnType;

  public MethodDef(int line, MethodHead head, @Nullable Variable name,
      @Nullable InExpression expression, @Nullable InStatement statement) {
    super(line);
    assert (expression != null || statement != null);
    isAFunction = head.isAFunction();

    this.head = head;
    this.statement = statement;
    this.expression = expression;

    if (name != null) {
      this.aliasName = name.name();
    } else {
      aliasName = null;
    }
  }

  public String name() {
    return head.name();
  }

  public int nbArgs() {
    return head.args().size();
  }

  public boolean isAFunction() {
    return isAFunction;
  }

  public Type returnType() {
    return returnType;
  }

  @Override
  public AST analyze(Context context) {
    MethodContext methContext = new MethodContext(context);
    ClassContext classContext = context.asClassContext();
    if (classContext == null) {
      interStatement.reportSemanticError(line(),
          "Definition of a method is only allowed in the context of a Class.");
    }

    head.args().forEach(a -> {
      a = a.analyze(context);
      methContext.addArgument(a);
    });

    if (statement != null && !isAFunction) {
      statement = (InStatement) statement
          .analyze(methContext);
    } else if (expression != null && isAFunction) {
      expression = (InExpression) expression.analyze(methContext);
      returnType = expression.type();
      methContext.setReturnType(returnType);
    }

    if (classContext != null) {
      classContext.addMethod(this, methContext);
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
    if (head.hasMorArgs()) {
      output.token(TokenOz.ELLIPSIS);
      output.space();
    }
    if (isAFunction) {
      output.token(TokenOz.DOLLAR);
    }
    output.token(TokenOz.RPAREN);

    if (aliasName != null) {
      //TODO what is this actually ?
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
    p.printf(
        "<MethodDeclaration line=\"%d\" name=\"%s\" alias=\"%s\" function?=\"%b\" procedure?=\"%b\">\n",
        line(), head.name(), aliasName, isAFunction, !isAFunction);
    p.indentRight();
    head.args().forEach(a -> a.writeToStdOut(p));

    if (statement != null) {
      statement.writeToStdOut(p);
    } else if (expression != null) {
      expression.writeToStdOut(p);
    }

    p.indentLeft();
    p.printf("</MethodDeclaration>\n");
  }
}
