package com.barassolutions;

import com.barassolutions.AST;
import com.barassolutions.Context;
import com.barassolutions.CustomPrinter;
import com.barassolutions.Emitter;
import java.util.ArrayList;

/**
 * The AST node for a this(...) call.
 */

class ThisConstruction extends Expression {

  /** Arguments to the constructor. */
  private ArrayList<Expression> arguments;

  /** Constructor representation of the (method) call. */
  private MessageExpression call;

  /** Types of arguments. */
  private Type[] argTypes;

  /**
   * Construct the AST node for a this(...) constructor given its line number
   * and arguments.
   *
   * @param line
   *            line in which the constructor occurs in the source file.
   * @param arguments
   *            the constructor's arguments.
   */
  protected ThisConstruction(int line, ArrayList<Expression> arguments) {
    super(line);
    this.arguments = arguments;
  }

  /**
   * Analyzing a this statement involves (1) setting the type, (2)
   * analyzing the actual arguments, and (3) finding the appropriate method to call
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  public Expression analyze(Context context) {
    type = Type.VOID;

    // Analyze the arguments, collecting
    // their types (in Class form) as argTypes.
    argTypes = new Type[arguments.size()];
    for (int i = 0; i < arguments.size(); i++) {
      arguments.set(i, (Expression) arguments.get(i).analyze(context));
      argTypes[i] = arguments.get(i).type();
    }

    // Get the constructor this(...) refers to.
    call = ((TypeDecl) context.classContext.definition())
        .thisType().constructorFor(argTypes); //TODO getMethodByName() or something

    if (call == null) {
      AST.compilationBlock.reportSemanticError(line(),
          "No such method: this"
              + Type.argTypesAsString(argTypes));

    }
    return this;
  }

  /**
   * Code generation involves generating the code for loading the actual
   * arguments onto the stack, and then for invoking this constructor.
   *
   * @param output
   *            the code emitter (basically an abstraction for producing the
   *            .oz file).
   */
  public void codegen(Emitter output) {
    //TODO
  }

  /**
   * @inheritDoc
   */

  public void writeOut(CustomPrinter p) {
    p.printf("<ThisConstruction line=\"%d\"/>\n", line());
    p.indentRight();
    if (arguments != null) {
      p.println("<Arguments>");
      for (Expression argument : arguments) {
        p.indentRight();
        p.println("<Argument>");
        p.indentRight();
        argument.writeOut(p);
        p.indentLeft();
        p.println("</Argument>");
        p.indentLeft();
      }
      p.println("</Arguments>");
    }
    p.indentLeft();
    p.println("</ThisConstruction>");
  }

}
