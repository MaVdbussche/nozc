package com.barassolutions;

import com.barassolutions.util.BuiltIns;
import com.barassolutions.util.Logger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The abstract syntax tree (AST) node representing an interactive statement, and so the root of the
 * AST.
 */
public class InterStatement extends AST {

  /**
   * List of statements forming the block body.
   */
  private final ArrayList<Statement> statements;

  /**
   * Block in this declare structure
   */
  private InStatement statement;

  /**
   * The context represented by this block.
   */
  private GlobalContext globalContext;

  /**
   * Whether a semantic error has been found.
   */
  private boolean isInError;

  /**
   * Construct an AST node for an interactive statement given a line number, declaration parts, and
   * a possible child InterStatement.
   *
   * @param line        line in which the compilation unit occurs in the source file.
   * @param inStatement a block of statements.
   */
  public InterStatement(int line, InStatement inStatement) {
    super(line);
    this.statement = inStatement;
    this.statements = null;
    interStatement = this;
  }

  /**
   * Construct an AST node for an InterStatement given its line number.
   *
   * @param line       line in which the statement occurs in the source file.
   * @param statements a list of statements.
   */
  public InterStatement(int line, ArrayList<Statement> statements) {
    super(line);
    this.statement = null;
    this.statements = statements;
    interStatement = this;
  }

  /**
   * Has a semantic error occurred up to now?
   *
   * @return true or false.
   */
  public boolean errorHasOccurred() {
    return isInError;
  }

  /**
   * Report a semantic error.
   *
   * @param line    line in which the error occurred in the source file.
   * @param message message identifying the error.
   */
  public void reportSemanticError(int line, String message, Object... args) {
    var arguments = new ArrayList<>();
    arguments.add(line);
    arguments.addAll(Arrays.asList(args));

    isInError = true;
    Logger.error("%d: " + message, arguments.toArray());
  }

  public void preAnalyze() {
    globalContext = new GlobalContext();

    for (BuiltIns b : BuiltIns.values()) {
      switch (b.type()) {
        case FUNCTION -> {
          globalContext.addFunctionBuiltIn(new FunctionDef(-1, b.nozString(),
                  /*Function arguments*/
                  b.args(),
                  /*Function content is not important for Nozc*/
                  null,
                  /*We don't care if lazy or not*/
                  false),
              new MethodContext(null));
        }
        case PROCEDURE -> {
          globalContext.addProcedureBuiltIn(new ProcedureDef(-1, b.nozString(),
                  /*Procedure arguments*/
                  b.args(),
                  /*Procedure content is not important for Nozc*/
                  null),
              new MethodContext(null));
        }
        case FUNCTOR -> {
          //TODO necessary ?
        }
        case CLASS -> {
          globalContext.addClassBuiltIn(new ClassDef(-1, b.ozString(),
                  /*Class descriptors*/
                  b.descrs(),
                  /*Class methods*/
                  b.meths()),
              new ClassContext(null));
        }
      }
    }
    //TODO also add system calls etc, things directly available without imports

    //Launch recursive preAnalyze
  }

  @Override
  public AST analyze(Context context) {
    if (statements != null) {
      statements.forEach(s -> s = (Statement) s.analyze(this.globalContext));
    } else if (statement != null) {
      statement = (InStatement) statement.analyze(this.globalContext);
    }
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (statement != null) {
      output.token(TokenOz.DECLARE);
      output.newLine();
      output.indentRight();
      statement.codegen(output);
      output.newLine();
      output.indentLeft();
    } else if (statements != null) {
      statements.forEach(s -> s.codegen(output));
    }
  }

  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<InteractiveStatement line=\"%d\">\n", line());

    if (statements != null) {
      statements.forEach(s -> s.writeToStdOut(p));
    } else if (statement != null) {
      statement.writeToStdOut(p);
    }
    p.printf("</InteractiveStatement>\n");
  }
}
