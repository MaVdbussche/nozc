package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import com.barassolutions.TokenOz;
import java.beans.Expression;

/**
 * A loop declaration can be an iterator or a feature declaration.
 *
 * @see LoopStructure
 */
public class LoopDeclaration extends AST implements Declaration {

  private Variable iterator;

  private Expression initialValue;

  private Expression continuationCondition;

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
  public LoopDeclaration(int line, Variable var, Expression init, Expression cond, Expression step,
      Expression end) {
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
   * @inheritDoc
   */
  @Override
  public void preAnalyze(Context context, Emitter partial) {
    //TODO ensure newly declared names do not already exist in this context, then add them as normal
    // (if they are, "shadow" them)
  }

  /**
   * Analyzing the loop declaration part means analyzing its components and evaluating the
   * expression types.
   *
   * @param context context in which names are resolved.
   * @return the analyzed (and possibly rewritten) AST subtree.
   */
  @Override
  public AST analyze(Context context) {
    iterator = (Variable) iterator.analyze(context);

    if (!generatorMode) {
      initialValue = (Expression) initialValue.analyze(context);
      initialValue.type().mustMatchExpected(line, new Type[]{Type.INT, Type.FLOAT});

      continuationCondition = (Expression) continuationCondition.analyze(context);
      continuationCondition.type().mustMatchExpected(line, initialValue.type());

      stepValue = (Expression) stepValue.analyze(context);
      stepValue.type().mustMatchExpected(line, initialValue.type());

      endValue = (Expression) endValue.analyze(context);
      endValue.type().mustMatchExpected(line, initialValue.type());
    } else {
      generator = (Expression) generator.analyze(context);
      generator.type().mustMatchExpected(line, Type.LIST);
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
        continuationCondition.codegen(output);
        output.token(TokenOz.SEMI);
        stepValue.codegen(output);
        output.token(TokenOz.RPAREN);
      } else { //Form E1..E2[;E3]
        initialValue.codegen(output);
        output.token(TokenOz.DOTDOT);
        endValue.codegen(output);
        output.token(TokenOz.SEMI);
        stepValue.codegen(output);
      }
    }
    output.space();
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
      p.printf("<Generator >\n");
      //TODO
    }
    p.indentLeft();
    p.printf("</LoopDec>\n");
  }
}
