package jminusminus;

import com.barassolutions.Context;
import com.barassolutions.CustomPrinter;
import com.barassolutions.Emitter;

/**
 * The AST for a "this" expression. It serves as a target of some field
 * selection or message.
 */

class This extends Expression {

  /**
   * Construct an AST node for a "this" expression given its line number.
   *
   * @param line
   *            line in which the expression occurs in the source file.
   */
  public This(int line) {
    super(line);
  }

  /**
   * Analysis involves simply determining the type in which we are, since that
   * determines the type of this target.
   *
   * @param context
   *            context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  public Expression analyze(Context context) {
    type = ((ClassDeclaration) context.classContext.definition())
        .thisType();
    return this;
  }

  /**
   * Simply generate code to load "this" onto the stack.
   *
   * @param output
   *            the code emitter (basically an abstraction for producing the
   *            .oz file).
   */
  public void codegen(Emitter output) {
    //TODO
  }

  /**
   * inheritDoc
   */
  public void writeOut(CustomPrinter p) {
    p.println("<JThis/>");
  }

}
