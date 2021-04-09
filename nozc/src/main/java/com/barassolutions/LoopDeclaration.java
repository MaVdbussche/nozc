package com.barassolutions;

import com.barassolutions.util.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A loop declaration can be an iterator or a feature declaration.
 * <p>Iterators :
 * <p><code>X in L</code>
 * <p>Iterates over the elements of list L. At each iteration, X is bound to the next element in L.
 * The generator runs out when all elements in L have been consumed.
 * <p><code>X in  E1;E2;E3</code>
 * <p><code>X in (E1;E2;E3)</code>
 * <p>This iterator is intended to have a C-like flavor. E1 is the initial value, E2 is the boolean
 * condition, and E3 is the next value. The iterator runs out when E2 evaluates to false.
 * <p><code>X in E1..E2;E3</code>
 * <p>Iterate over the integers from E1 up to E2 inclusive, by increment of E3. E1, E2 and E3 are
 * evaluated only once prior to starting the loop. Whether the loop is intended to count upward or
 * downward is determined by the sign of E3.
 * <code>X in E1..E2</code>
 * <p>Same as above, but with increment of 1.
 * <p><code>X in E1;E2</code>
 * <p>Shorthand for <code>X in E1;true;E2</code>
 *
 * @see LoopStructure
 */
public class LoopDeclaration extends AST {

  private Variable iterator;

  private Expression initialValue;

  @Nullable
  private Expression continuationCondition;

  @Nullable
  private Expression stepValue;

  private Expression endValue;

  private boolean generatorMode = false;
  private Expression generator;

  /**
   * Construct an AST node for a loop declaration part given its arguments.
   *
   * @param line line in which the if-statement occurs in the source file.
   * @param var  the variable which will iterate.
   * @param init the initial value of the iterator.
   * @param cond the condition to check before each iteration.
   * @param step the increment/decrement (depending on the evaluation of the expression) which will
   *             be applied at the end of each iteration
   * @param end  the upper bound of the iterator (included)
   */
  public LoopDeclaration(int line, Variable var, Expression init, @Nullable Expression cond,
      @Nullable Expression step,
      @Nullable Expression end) {
    super(line);
    this.iterator = var;
    this.initialValue = init;
    this.continuationCondition = cond;
    this.stepValue = step;
    this.endValue = end;
    this.generator = null;
  }

  /**
   * Construct an AST node for a loop declaration part given its arguments.
   *
   * @param line      line in which the if-statement occurs in the source file.
   * @param var       the variable which will iterate.
   * @param generator the list to iterate over.
   */
  public LoopDeclaration(int line, Variable var, Expression generator) {
    super(line);
    this.iterator = var;
    this.generator = generator;
    generatorMode = true;
  }

  /**
   * Analyzing the loop declaration part means analyzing its components and evaluating the
   * expression types.
   *
   * @param loopContext context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context loopContext) {
    loopContext.addVariable(iterator);

    iterator = (Variable) iterator.analyze(loopContext);

    if (!generatorMode) {
      initialValue = initialValue.analyze(loopContext);
      initialValue.type().mustMatchExpected(line(), Type.INT, Type.FLOAT);

      if (continuationCondition != null) {
        continuationCondition = continuationCondition.analyze(loopContext);
        continuationCondition.type().mustMatchExpected(line(), Type.BOOLEAN);
      }

      if (stepValue != null) {
        stepValue = stepValue.analyze(loopContext);
        stepValue.type().mustMatchExpected(line(), initialValue.type());
      }

      if (endValue != null) { //Form E1..E2[;E3]
        endValue = endValue.analyze(loopContext);
        initialValue.type().mustMatchExpected(line(), Type.INT);
        if(stepValue!=null) {
          stepValue.type().mustMatchExpected(line(), Type.INT);
        }
        endValue.type().mustMatchExpected(line(), Type.INT);
      }
    } else {
      generator = generator.analyze(loopContext);
      generator.type().mustMatchExpected(line(),
          Type.LIST); //TODO problem : we never create expressions of type LIST
    }

    return this;
  }

  /**
   * Perform code generation for this AST.
   *
   * @param output the code emitter (basically an abstraction for producing the oz file).
   */
  @Override
  public void codegen(Emitter output) {
    iterator.codegen(output);
    output.space();
    output.token(TokenOz.IN);
    output.space();
    if (generatorMode) {
      generator.codegen(output);
    } else {
      if (endValue == null) { //Form E1;E2;E3 or E1;[true;]E2
        output.token(TokenOz.LPAREN);
        initialValue.codegen(output);
        output.token(TokenOz.SEMI);
        if (continuationCondition != null) {
          continuationCondition.codegen(output);
        } else {
          output.token(TokenOz.TRUE);
        }

        assert stepValue != null;
        output.token(TokenOz.SEMI);
        stepValue.codegen(output);

        output.token(TokenOz.RPAREN);
      } else { //Form E1..E2[;E3]
        initialValue.codegen(output);
        output.token(TokenOz.DOTDOT);
        endValue.codegen(output);
        output.token(TokenOz.SEMI);
        if (stepValue != null) {
          stepValue.codegen(output);
        } else {
          output.literal("1");
        }
      }
    }
  }

  /**
   * Write the information pertaining to this AST to STDOUT.
   *
   * @param p for pretty printing with indentation.
   */
  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<LoopDec>\n");
    p.indentRight();
    if (generatorMode) {
      p.printf("<Generator />\n");
      //TODO
    }
    p.indentLeft();
    p.printf("</LoopDec>\n");
  }
}
