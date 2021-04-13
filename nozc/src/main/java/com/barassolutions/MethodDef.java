package com.barassolutions;

import com.barassolutions.util.Logger;
import com.barassolutions.util.Utils;
import org.jetbrains.annotations.Nullable;

public class MethodDef extends Declaration implements ClassElement {

  private final MethodHead head;

  @Nullable
  private final String aliasName;
  private final boolean isAFunction;
  /**
   * The statement/expression constituting the method's body.
   */
  @Nullable
  private InStatement statement;
  @Nullable
  private InExpression expression;
  /**
   * Only makes sense if this is a function, of course.
   */
  @Nullable
  private Type returnType;

  public MethodDef(int line, MethodHead head, @Nullable Variable name,
      @Nullable InExpression expression, @Nullable InStatement statement) {
    super(line);
    if ((expression == null && statement == null) || (expression != null && statement != null)) {
      interStatement.reportSemanticError(line,
          "Invalid structure for method definition. You should wrote a statement or an expression, but not both.");
    }
    this.head = head;
    this.statement = statement;
    this.expression = expression;

    //This is only correct because of the statements above. Be careful when editing this constructor !
    isAFunction = (expression != null);
    if (isAFunction) {
      this.returnType = Type.ANY;
    }

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
    Logger.debug("Launching methDef analysis for " + name());
    MethodContext methContext = new MethodContext(context);
    ClassContext classContext = context.asClassContext();
    if (classContext == null) {
      interStatement.reportSemanticError(line(),
          "Definition of a method is only allowed in the context of a Class.");
    }

    head.args().forEach(a -> {
      a = a.analyze(context);
      a.patterns().forEach(methContext::addVariable); //TODO Do this for other Def classes ?! Also, make this infinitely recursive for records in records as pattern etc.
    });

    if (classContext != null) {
      classContext.addMethod(this, methContext);
    }

    if (statement != null && !isAFunction) {
      Logger.debug("Analyzing statement in method context");
      statement = (InStatement) statement.analyze(methContext);
    } else if (expression != null && isAFunction) {
      //Temporary assigning a type to allow analysis of potential recursive calls
      methContext.setReturnType(returnType);
      Logger.debug("Temporarily assigned a type to MethodDef");

      expression = (InExpression) expression.analyze(methContext);
      returnType = expression.type();
      methContext.setReturnType(returnType);
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
    output.indentLeft();
    output.newLine();
    output.token(TokenOz.END);
    output.newLine();
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
