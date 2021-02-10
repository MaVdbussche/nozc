package com.barassolutions.toCleanUp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Environment in which the AST is analyzed. Represents a code scope.
 * <p>
 * Scopes are nested in newOz => env is seen as a stack of Context objects. Each COntext is a
 * mapping {name,definition}
 */
public class Context {

  protected Context surroundingContext;

  protected ClassContext classContext;

  protected CompilationBlockContext compilationBlockContext;

  protected Map<String, Definition> entries;

  protected Context(Context surrounding, ClassContext classContext, CompilationBlockContext compilationBlockContext) {
    this.surroundingContext = surrounding;
    this.compilationBlockContext = compilationBlockContext;
    this.entries = new HashMap<>();
  }

  public void addEntry(int line, String name, Definition def) {
    if (entries.containsKey(name)) {
      AST.compilationBlock.reportSemanticError(line, "redefining name: " + name);
    } else {
      entries.put(name, def);
    }
  }

  /**
   * Look this definition up, recursively in surrounding contexts until it is found (if it exists)
   */
  public Definition lookup(String name) {
    Definition def = entries.get(name);
    return def != null ? def
        : surroundingContext != null ? surroundingContext.lookup(name)
            :null;
  }

  /**
   * Return the definition for a type name in the environment. For now, we
   * look for types only in the CompilationUnitContext.
   *
   * @param name
   *            the name of the type whose definition we're looking for.
   * @return the definition (or null, if not found).
   */
  public Type lookupType(String name) {
    TypeNameDefn defn = (TypeNameDefn) compilationBlockContext.lookup(name);
    return defn == null ? null : defn.type();
  }

  public Context surroundingContext() {
    return this.surroundingContext;
  }

  public CompilationBlockContext compilationBlockContext() {
    return this.compilationBlockContext;
  }

  /**
   * Get the method context we are in, or null if we are not in a method
   */
  public MethodContext methodContext() {
    Context context = this;
    while (context != null && !(context instanceof MethodContext)) {
      context = context.surroundingContext();
    }
    return (MethodContext) context;
  }

  public Set<String> names() {
    return entries.keySet();
  }

  /**
   * Describe this AST in STDOUT
   */
  public void writeOut(CustomPrinter p) {

  }
}

/**
 * The compilation block context is always the outermost context, and is where
 * imported types and locally defined types (classes) are declared.
 */
class CompilationBlockContext extends Context {

  public CompilationBlockContext() {
    super(null, null,null);
    compilationBlockContext = this;
  }

  /**
   * @inheritDoc
   */
  public void writeOut(CustomPrinter p) {
    p.println("<CompilationBlockContext>");
    p.indentRight();
    p.println("<Entries>");
    if (entries != null) {
      p.indentRight();
      for (String key : names()) {
        p.println("<Entry>" + key + "</Entry>");
      }
      p.indentLeft();
    }
    p.println("</Entries>");
    p.indentLeft();
    p.println("</CompilationBlockContext>");
  }
}

/**
 * Represents the context (scope, environment, symbol table) for a type, eg a
 * class, in j--. It also keeps track of its surrounding context(s), and the
 * type whose context it represents.
 */
class ClassContext extends Context {

  /** AST node of the type that this class represents. */
  private final AST definition;

  /**
   * Construct a class context.
   *
   * @param definition
   *            the AST node of the type that this class represents.
   * @param surrounding
   *            the surrounding context(s).
   */

  public ClassContext(AST definition, Context surrounding) {
    super(surrounding, null, surrounding.compilationBlockContext);
    classContext = this;
    this.definition = definition;
  }

  /**
   * Return the AST node of the type defined by this class.
   *
   * @return the AST of the type defined by this class.
   */
  public AST definition() {
    return definition;
  }

}

/**
 * Context in which local variables can be declared
 */
class LocalContext extends Context {

  public LocalContext(Context surrounding) {
    super(surrounding, surrounding.classContext surrounding.compilationBlockContext());
  }

  /**
   * @inheritDoc
   */
  public void writeOut(CustomPrinter p) {
    p.println("<LocalContext>");
    p.indentRight();
    p.println("<Entries>");
    if (entries != null) {
      p.indentRight();
      for (String key : names()) {
        Definition defn = entries.get(key);
        if (defn instanceof LocalVariableDef) {
          p.printf("<Entry name=\"%s\">", key);
        }
      }
      p.indentLeft();
    }
    p.println("</Entries>");
    p.indentLeft();
    p.println("</LocalContext>");
  }
}

/**
 * Method context where formal parameters are declared.
 */
class MethodContext extends LocalContext {

  /**
   * Used to differentiate methods in object classes from "normal" ones
   */
  private final boolean isStatic;

  /**
   * We only ask this question for functions, not procedures ofc.
   */
  private boolean hasReturnStmt = false;

  public MethodContext(Context surrounding, boolean isStatic) {
    super(surrounding);
    this.isStatic = isStatic;
  }

  public boolean isStatic() {
    return this.isStatic;
  }

  public void setHasReturnStmt(boolean b) {
    this.hasReturnStmt = b;
  }

  public boolean hasReturnStmt() {
    return this.hasReturnStmt;
  }

  /**
   * @inheritDoc
   */
  public void writeOut(CustomPrinter p) {
    p.println("<MethodContext>");
    p.indentRight();
    super.writeOut(p);
    p.indentLeft();
    p.println("</MethodContext>");
  }
}
