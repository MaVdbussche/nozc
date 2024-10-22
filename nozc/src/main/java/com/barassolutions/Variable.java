package com.barassolutions;

import com.barassolutions.util.Logger;
import com.barassolutions.util.Utils;
import java.util.Collections;

public class Variable extends Pattern implements Lhs {

  private final String name;
  private final boolean usedAsPattern;
  private boolean forceSmallLetter;
  /**
   * This means this is an instance of variable access (typically a rhs or a term)
   */
  private final boolean readMode;
  private boolean constant;
  private boolean isAssigned;

  public Variable(int line, String name, boolean isAPattern, boolean readMode) {
    this(line, name, true, isAPattern, readMode);
  }

  public Variable(int line, String name, boolean constant, boolean isAPattern, boolean readMode) {
    super(line);
    this.name = name;
    this.constant = constant;
    this.usedAsPattern = isAPattern;
    this.readMode = readMode;
    if (isAPattern) {
      this.type = Type.ANY;
    }
    this.forceSmallLetter = false;
  }

  //Used for attributes in classes
  public Variable(int line, String name, boolean constant, boolean isAPattern, boolean readMode,
      boolean forceSmallLetter) {
    super(line);
    this.name = name;
    this.constant = constant;
    this.usedAsPattern = isAPattern;
    this.readMode = readMode;
    if (isAPattern) {
      this.type = Type.ANY;
    }
    this.forceSmallLetter = forceSmallLetter;
  }

  Variable(Variable v, boolean readMode) {
    this(v.line(), v.name, v.constant, v.usedAsPattern, readMode, v.forceSmallLetter);
  }

  /**
   * To use in places where we rather want to give faithful representation to the user. In other
   * words, places where we don't want things like first letter capitalization.
   */
  public String name() {
    return name;
  }

  public boolean isConstant() {
    return constant;
  }

  public boolean isAssigned() {
    return isAssigned;
  }

  public boolean readMode() {
    return readMode;
  }

  public boolean usedAsPattern() {
    return usedAsPattern;
  }

  public boolean forceSmallLetter() {
    return forceSmallLetter;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public Iterable<Pattern> patterns() {
    return Collections.singleton(this);
  }

  /**
   * Tries to assign the passed value to this Variable. If it is successful, this method also
   * updates this Variable's <code>Type</code>.
   *
   * @param newValue the new value to assign to this Variable.
   */
  public void tryAssign(Expression newValue) {
    if (!constant || !isAssigned) {
      this.type = newValue.type();
      isAssigned = true;
    } else {
      interStatement
          .reportSemanticError(line(), "Trying to assign value <" + newValue.toString()
              + "> to a Variable of type VAL. You might want to use VAR instead.");
    }
  }

  public void tryPlusAssign(Expression newValue) {
    if (constant) {
      interStatement
          .reportSemanticError(line(), "Trying to assign a value <" + newValue.toString()
              + "> to a Variable of type VAL. You might want to use VAR instead.");
    } else if (!isAssigned) {
      interStatement.reportSemanticError(line(),
          "Trying to compute using a not-yet assigned variable as an operand."); //TODO do this check for all computations
    } else {
      if (type.equals(Type.INT)) {
        newValue.type.mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        isAssigned = true;
      } else if (type.equals(Type.FLOAT)) {
        newValue.type.mustMatchExpected(line(), Type.FLOAT);
        type = Type.FLOAT;
        isAssigned = true;
      } else if (type.equals(Type.STRING)) {
        newValue.type.mustMatchExpected(line(), Type.STRING);
        type = Type.STRING;
        isAssigned = true;
      } else {
        type = Type.ANY;
        interStatement.reportSemanticError(line(),
            "This operation is not allowed for variables of type " + this.type().toString() + ".");
      }
    }
  }

  public void tryMinusAssign(Expression newValue) {
    if (constant) {
      interStatement
          .reportSemanticError(line(), "Trying to assign a value <" + newValue.toString()
              + "> to a Variable of type VAL. You might want to use VAR instead.");
    } else if (!isAssigned) {
      interStatement.reportSemanticError(line(),
          "Trying to compute using a not-yet assigned variable as an operand.");
    } else {
      if (type.equals(Type.INT)) {
        newValue.type.mustMatchExpected(line(), Type.INT);
        type = Type.INT;
        isAssigned = true;
      } else if (type.equals(Type.FLOAT)) {
        newValue.type.mustMatchExpected(line(), Type.FLOAT);
        type = Type.FLOAT;
        isAssigned = true;
      } else {
        type = Type.ANY;
        interStatement.reportSemanticError(line(),
            "This operation is not allowed for variables of type " + this.type().toString() + ".");
      }
    }
  }

  @Override
  public Expression analyze(Context context) {
    if (!usedAsPattern) {
      Variable var = context.variableFor(this.name);
      if (var == null) {
        interStatement.reportSemanticError(line(),
            "Could not find variable for: <name:" + name + ">");
      } else {
        Logger.debug(
            "Retrieved Variable in context <name:" + var.name() + " constant:" + var.isConstant()
                + " readMode:" + var.readMode() + " type:" + var.type() + ">");
        //Copy general data about the variable found in the context, which are required to have correct code output.
        this.constant = var.constant;
        this.type = var.type();
        this.forceSmallLetter = var.forceSmallLetter;
      }
    } else {
      //Nothing to do here (it is added to the inner context in analyze() method of all encapsulating AST nodes, like MethodDef or CaseExpressionClause)
    }

    return this;
  }

  /**
   * Perform code generation for an expression, given the code emitter.
   *
   * @param output the code emitter (basically an abstraction for producing the .oz file).
   */
  @Override
  public void codegen(Emitter output) {
    Logger.debug(
        "Generating code for a Variable <name:" + name + " constant:" + constant + " readMode:"
            + readMode + ">");
    if (!constant && readMode) {
      output.token(TokenOz.COMMERCAT);
    }
    if (forceSmallLetter) {
      output.literal(name);
    } else {
      output.literal(Utils.ozFriendlyName(name));
    }
  }

  /**
   * Write the information pertaining to this AST to STDOUT.
   *
   * @param p for pretty printing with indentation.
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Variable>\n");
    p.indentRight();
    p.println("<name:" + name + " constant:" + constant + ">");
    p.indentLeft();
    p.printf("</Variable>\n");
  }
}
