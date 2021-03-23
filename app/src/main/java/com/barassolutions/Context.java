package com.barassolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class Context {

  private final Context parent;

  private final ArrayList<Variable> definedVars = new ArrayList<>();
  private final Map<FunctionDef, MethodContext> definedFunctions = new HashMap<>();
  private final Map<ProcedureDef, MethodContext> definedProcedures = new HashMap<>();
  private final Map<FunctorDef, FunctorContext> definedFunctors = new HashMap<>();

  public Context(Context parent) {
    //TODO
    this.parent = parent;
  }

  public void addFunction(FunctionDef f, MethodContext c) {
    this.ensureNotExistsHere(f.line(), f.name());
    //TODO throw error if this name exists in THIS context (shadow it if present in parent)
  }

  public void addProcedure(ProcedureDef p, MethodContext c) {
    //TODO throw error if this name exists in THIS context (shadow it if present in parent)
  }

  public void addFunctor(FunctorDef f, FunctorContext c) {
    //TODO throw error if this name exists in THIS context (shadow it if present in parent)
  }

  public FunctionDef functionFor(String name, int nbArgs) {
    //TODO
    return null;
  }

  public ProcedureDef procedureFor(String name, int nbArgs) {
    //TODO
    return null;
  }

  public ClassContext asClassContext() {
    if (this instanceof ClassContext) {
      return (ClassContext) this;
    } else {
      return null;
    }
  }

  public MethodContext findMethodContext() {
    if (this instanceof MethodContext) {
      return (MethodContext) this; //Waiting for Java 16's pattern-matching ...
    } else if (parent == null) {
      return null;
    } else {
      return parent.findMethodContext();
    }
  }

  public ClassContext findClassContext() {
    if (this instanceof ClassContext) {
      return (ClassContext) this; //Waiting for Java 16's pattern-matching ...
    } else if (parent == null) {
      return null;
    } else {
      return parent.findClassContext();
    }
  }

  public ClassContext findClassContext(String className) {
    if (this instanceof ClassContext && ((ClassContext) this).name
        .equals(className)) { //Waiting for Java 16's pattern-matching ...
      return (ClassContext) this;
    } else if (parent == null) {
      return null;
    } else {
      return parent.findClassContext();
    }
  }

  /**
   * Displays an error if the passed name is not present in this context, or any of its parents'
   * contexts.
   */
  public void ensureExists(int line, String name) {
    if (definedVars.stream().anyMatch(e -> e.name().equals(name))
        || definedFunctions.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedProcedures.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedFunctors.keySet().stream().anyMatch(e -> e.name().equals(name))) {
    } else if (parent == null) {
      AST.interStatement
          .reportSemanticError(line, "Name \"" + name + "\" does not exist in this context.");
    } else {
      parent.ensureExists(line, name);
    }
  } //TODO [MINOR] this condition could be simplified

  /**
   * Displays an error if the passed name is present in this context, or any of its parents'
   * contexts.
   */
  public void ensureNotExists(int line, String name) {
    if (definedVars.stream().noneMatch(e -> e.name().equals(name))
        && definedFunctions.keySet().stream().noneMatch(e -> e.name().equals(name))
        && definedProcedures.keySet().stream().noneMatch(e -> e.name().equals(name))
        && definedFunctors.keySet().stream().noneMatch(e -> e.name().equals(name))) {
      if (parent != null) {
        parent.ensureNotExists(line, name);
      }
    } else {
      AST.interStatement
          .reportSemanticError(line, "Name \"" + name
              + "\" is already defined in this context. This definition will be skipped.");
    }
  }

  /**
   * Displays an error if the passed name is present in this particular context.
   */
  public void ensureNotExistsHere(int line, String name) {
    if (definedVars.stream().anyMatch(e -> e.name().equals(name))
        || definedFunctions.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedProcedures.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedFunctors.keySet().stream().anyMatch(e -> e.name().equals(name))) {
      AST.interStatement
          .reportSemanticError(line, "Name \"" + name
              + "\" is already defined in this context. This definition will be skipped.");
    }
  }

  public void writeToStdOut(PrettyPrinter p) {
    //TODO describe everything defined in this context (something map-like ?)
  }
}

class GlobalContext extends Context {
  //TODO hold a store of all know Oz functions/procs
  //TODO creat constructor with parent as arg (always null here)

  public GlobalContext() {
    super(null);
  }
}

/**
 * This class encapsulate methods, functions & procedure contexts
 */
class MethodContext extends Context {

  private Type returnType = null;

  public MethodContext(Context parent) {
    super(parent);
  }

  public void setReturnType(Type type) {
    this.returnType = type;
  }

  public Type returnType() {
    return returnType;
  }
}

class ClassContext extends Context {
  //TODO creat constructor with parent as arg

  public String name;

  private ArrayList<String> superClasses;
  private Map<Variable, Expression> attributes;
  private final Map<MethodDef, MethodContext> definedMethods = new HashMap<>();

  public ClassContext(Context parent, String className) {
    super(parent);
    this.name = className;
  }

  public void addMethod(MethodDef m, MethodContext c) {
    //TODO
  }

  public void addAttribute(Variable attribute, @Nullable Expression defaultValue) {
    attributes.put(attribute, defaultValue);
  }

  public MethodDef methodFor(String name, int nbArgs) {
    //TODO
    return null;
  }

  @Override
  public void addFunction(FunctionDef f, MethodContext c) throws UnsupportedOperationException {
  }

  @Override
  public void addProcedure(ProcedureDef f, MethodContext c) throws UnsupportedOperationException {
  }

  //TODO rewrite the ensure*** operations to take into account the super classes as well in the available methods
}

class FunctorContext extends Context {

  public FunctorContext(Context parent) {
    super(parent);
  }

  public void addImport(ImportClause i) {
    //TODO
  }
}
