package com.barassolutions;

import com.barassolutions.util.BuiltIns;
import com.barassolutions.util.Logger;
import com.barassolutions.util.Utils;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

/**
 * The AST node for a local variables/values declaration. Inheriting classes declare things like
 * functors, procedures, etc. Local names are declared by analyze(), which also re-writes any
 * initializations as assignment statements, in turn generated by codegen().
 */
public class Declaration extends Statement {

  /**
   * Map holding declared variables and their optional assigned value.
   */
  private Map<Variable, Expression> map;

  /**
   * Whether these variables are constants. Note that this concept is totally new to NewOz, since Oz
   * only supports constant values. In NewOz, one can declare variables, which are a wrapper for
   * Cells.
   */
  private boolean constant;

  public Declaration(int line, @NotNull Map<Variable, Expression> map, boolean constant) {
    super(line);
    this.map = map;
    this.constant = constant;
  }

  protected Declaration(int line) {
    super(line);
  }

  @Override
  public AST analyze(Context context) {
    map.forEach((v, e) -> {
      if(e != null) {
        map.put(v, e.analyze(context));
        v.type = e.type();
      } else {
        v.type = Type.ANY;
      }
      context.addVariable(v);
      Logger.debug("Added Variable to context : <name:"+v.name()+" constant:"+v.isConstant()+" readMode:"+v.readMode()+">");
    });

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (constant) {
      map.forEach((k, v) -> {
        output.literal(Utils.ozFriendlyName(k.name()));
        if (v != null) {
          output.token(TokenOz.ASSIGN);
          v.codegen(output);
        }
        output.newLine();
      });
    } else {
      map.forEach((k, v) -> {
        output.literal(Utils.ozFriendlyName(k.name()));
        output.token(TokenOz.ASSIGN);
        output.token(TokenOz.LCURLY);
        output.literal(BuiltIns.newCell.ozString());
        output.space();
        if (v == null) {
          output.token(TokenOz.UNDERSCORE);
        } else {
          v.codegen(output);
        }
        output.token(TokenOz.RCURLY);
        output.newLine();
      });
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    if (constant) {
      map.forEach((k, v) -> {
        p.printf("<ConstantDeclaration line=\"%d\" name=\"%s\">\n", line(), k.name());
        p.indentRight();
        if (v == null) {
          p.printf("<Value : none>\n");
        } else {
          p.printf("<Value :>\n");
          v.writeToStdOut(p);
        }
        p.indentLeft();
        p.printf("</ConstantDeclaration>\n");
      });
    } else {
      map.forEach((k, v) -> {
        p.printf("<VariableDeclaration line=\"%d\" name=\"%s\">\n", line(), k.name());
        p.indentRight();
        if (v == null) {
          p.printf("<Initial value : none>\n");
        } else {
          p.printf("<Initial value :>\n");
          v.writeToStdOut(p);
        }
        p.indentLeft();
        p.printf("</VariableDeclaration>\n");
      });
    }
  }
}
