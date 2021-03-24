package com.barassolutions;

public class Variable extends Pattern implements Lhs {

  private final String name;

  private final boolean constant;

  private boolean isAssigned;

  private final boolean usedAsPattern;

  public Variable(int line, String name, boolean isAPattern) {
    this(line, name, true, isAPattern);
  }

  public Variable(int line, String name, boolean constant, boolean isAPattern) {
    super(line);
    this.name = name;
    this.constant = constant;
    this.usedAsPattern = isAPattern;
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
            "This operation is not allowed for variables of type "+this.type().toString()+".");
      }
    }
  }

  @Override
  public Expression analyze(Context context) {
    if(!usedAsPattern) {
      Variable var = context.variableFor(this.name);
      if(var==null) {
        interStatement.reportSemanticError(line(),
            "Could not find variable for: <name:"+name+">");
      }
    } else {
      //Nothing to do here (it is added to the method context in MethodDef#analyze())
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
    output.literal(Utils.ozFriendlyName(name));
  }

  /**
   * Write the information pertaining to this AST to STDOUT.
   *
   * @param p for pretty printing with indentation.
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    //TODO
  }
}
