package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;

public class Variable extends Expression {

  private final String name;

  private final boolean constant;

  public Variable(int line, String name) {
    this(line, name, true);
  }

  public Variable(int line, String name, boolean constant) {
     super(line);
     this.name = name;
     this.constant = constant;
  }

  /**
   * To use in codegen() calls, as Oz variables should start with a capital letter.
   */
  public String ozFriendlyName() {
    return name.substring(0,1).toUpperCase() + name.substring(1);
  }

  /**
   * To use in places where we rather want to give faithful representation to the user.
   */
  public String name() {
    return name;
  }

  @Override
  public Expression analyze(Context context) {
    //TODO resolve name in context, then "shadow" it if necessary
    return null;
  }

  /**
   * Perform code generation for an expression, given the code emitter.
   *
   * @param output the code emitter (basically an abstraction for producing the .oz file).
   */
  @Override
  public void codegen(Emitter output) {

  }

  /**
   * Write the information pertaining to this AST to STDOUT.
   *
   * @param p for pretty printing with indentation.
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {

  }
}
