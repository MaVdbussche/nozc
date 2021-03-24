package com.barassolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.jetbrains.annotations.Nullable;

public class Context {

  private final Context parent;

  private final ArrayList<Pattern> definedVars = new ArrayList<>();
  private final Map<FunctionDef, MethodContext> definedFunctions = new HashMap<>();
  private final Map<ProcedureDef, MethodContext> definedProcedures = new HashMap<>();
  private final Map<FunctorDef, FunctorContext> definedFunctors = new HashMap<>();

  public Context(Context parent) {
    this.parent = parent;
  }

  public boolean addVariable(Pattern p) {
    boolean notExistsHere;
    if (p instanceof Variable) { //Waiting for Java 16's pattern-matching ...
      notExistsHere = this.ensureNotExistsHere(((Variable) p).line(), ((Variable) p).name());
    } else if (p instanceof Record) { //Waiting for Java 16's pattern-matching ...
      notExistsHere = this.ensureNotExistsHere(((Record) p).line(), ((Record) p).name());
    } else {
      notExistsHere = true;
    }
    if (notExistsHere) {
      this.definedVars.add(p);
      return true;
    } else {
      return false;
    }
  }

  public boolean addFunction(FunctionDef f, MethodContext c) {
    boolean notExistsHere = this.ensureNotExistsHere(f.line(), f.name());
    if (notExistsHere) {
      this.definedFunctions.put(f, c);
      return true;
    } else {
      return false;
    }
  }

  public boolean addProcedure(ProcedureDef p, MethodContext c) {
    boolean notExistsHere = this.ensureNotExistsHere(p.line(), p.name());
    if (notExistsHere) {
      this.definedProcedures.put(p, c);
      return true;
    } else {
      return false;
    }
  }

  public boolean addFunctor(FunctorDef f, FunctorContext c) {
    boolean notExistsHere = this.ensureNotExistsHere(f.line(), f.name());
    if (notExistsHere) {
      this.definedFunctors.put(f, c);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Look for a variable respecting the passed name characteristic, in this or any of the parent
   * contexts.
   *
   * @param name   Name of the variable to look for
   * @return the Variable if found, or null if no matching variable exists
   */
  public Variable variableFor(String name) {
    AtomicReference<Variable> out = new AtomicReference<>();
    out.set(null);
    this.definedVars.forEach(pattern -> {
      if (pattern instanceof Variable v && v.name().equals(name)) {
        out.set(v);
      }
    });
    if (out.get() == null && parent != null) {
      return parent.variableFor(name);
    } else {
      return out.get();
    }
  }

  /**
   * Look for a variable respecting the passed name characteristic, in this or any of the parent
   * contexts.
   *
   * @param name   Name of the variable to look for
   * @return the Variable if found, or null if no matching variable exists
   */
  public Record recordFor(String name) {
    AtomicReference<Record> out = new AtomicReference<>();
    out.set(null);
    this.definedVars.forEach(pattern -> {
      if (pattern instanceof Record r && r.name().equals(name)) {
        out.set(r);
      }
    });
    if (out.get() == null && parent != null) {
      return parent.recordFor(name);
    } else {
      return out.get();
    }
  }

  /**
   * Look for a function respecting the passed characteristics, in this or any of the parent
   * contexts.
   *
   * @param name   Name of the function to look for
   * @param nbArgs Number of arguments of the function to look for
   * @return the FunctionDef if found, or null if no matching function exists
   */
  public FunctionDef functionFor(String name, int nbArgs) {
    AtomicReference<FunctionDef> out = new AtomicReference<>();
    out.set(null);
    this.definedFunctions.keySet().forEach(functionDef -> {
      if (functionDef.name().equals(name) && functionDef.nbArgs() == nbArgs) {
        out.set(functionDef);
      }
    });
    if (out.get() == null && parent != null) {
      return parent.functionFor(name, nbArgs);
    } else {
      return out.get();
    }
  }

  /**
   * Look for a procedure respecting the passed characteristics, in this or any of the parent
   * contexts.
   *
   * @param name   Name of the procedure to look for
   * @param nbArgs Number of arguments of the procedure to look for
   * @return the ProcedureDef if found, or null if no matching procedure exists
   */
  public ProcedureDef procedureFor(String name, int nbArgs) {
    AtomicReference<ProcedureDef> out = new AtomicReference<>();
    out.set(null);
    this.definedProcedures.keySet().forEach(procedureDef -> {
      if (procedureDef.name().equals(name) && procedureDef.nbArgs() == nbArgs) {
        out.set(procedureDef);
      }
    });
    if (out.get() == null && parent != null) {
      return parent.procedureFor(name, nbArgs);
    } else {
      return out.get();
    }
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
   *
   * @return true if the name is not present in this context or any parent, false otherwise.
   */
  @Deprecated
  public boolean ensureExists(int line, String name) {
    if (definedVars.stream().noneMatch(e -> {
      if (e instanceof Variable v) {
        return v.name().equals(name);
      } else if (e instanceof Record r) {
        return r.name().equals(name);
      } else {
        return false; //Not applicable for name search
      }
    })
        && definedFunctions.keySet().stream().noneMatch(e -> e.name().equals(name))
        && definedProcedures.keySet().stream().noneMatch(e -> e.name().equals(name))
        && definedFunctors.keySet().stream().noneMatch(e -> e.name().equals(name))) {
      if (parent == null) {
        AST.interStatement
            .reportSemanticError(line, "Name \"" + name
                + "\" does not exist in this context, nor any of its parent contexts.");
        return true;
      } else {
        return parent.ensureExists(line, name);
      }
    } else {
      return false;
    }
  }

  /**
   * Displays an error if the passed name is NOT present in this particular context.
   *
   * @return true if the name is not present in this particular context, false otherwise.
   */
  public boolean ensureExistsHere(int line, String name) {
    if (definedVars.stream().noneMatch(e -> {
      if (e instanceof Variable v) {
        return v.name().equals(name);
      } else if (e instanceof Record r) {
        return r.name().equals(name);
      } else {
        return false; //Not applicable for name search
      }
    })
        && definedFunctions.keySet().stream().noneMatch(e -> e.name().equals(name))
        && definedProcedures.keySet().stream().noneMatch(e -> e.name().equals(name))
        && definedFunctors.keySet().stream().noneMatch(e -> e.name().equals(name))) {
      AST.interStatement
          .reportSemanticError(line, "Name \"" + name + "\" does not exist in this context.");
      return true;
    } else {
      return false;
    }
  }

  /**
   * Displays an error if the passed name is present in this context, or any of its parents'
   * contexts.
   *
   * @return true if the name is not present in this context or any parent, false otherwise.
   */
  @Deprecated
  public boolean ensureNotExists(int line, String name) {
    if (definedVars.stream().anyMatch(e -> {
      if (e instanceof Variable v) {
        return v.name().equals(name);
      } else if (e instanceof Record r) {
        return r.name().equals(name);
      } else {
        return false; //Not applicable for name search
      }
    })
        || definedFunctions.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedProcedures.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedFunctors.keySet().stream().anyMatch(e -> e.name().equals(name))) {
      AST.interStatement
          .reportSemanticError(line, "Name \"" + name
              + "\" is already defined in this context. This definition will be skipped.");
      return false;
    } else {
      if (parent != null) {
        return parent.ensureNotExists(line, name);
      } else {
        return true;
      }
    }
  }

  /**
   * Displays an error if the passed name is present in this particular context.
   *
   * @return true if the name is not present in this particular context, false otherwise.
   */
  public boolean ensureNotExistsHere(int line, String name) {
    if (definedVars.stream().anyMatch(e -> {
      if (e instanceof Variable v) {
        return v.name().equals(name);
      } else if (e instanceof Record r) {
        return r.name().equals(name);
      } else {
        return false; //Not applicable for name search
      }
    })
        || definedFunctions.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedProcedures.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedFunctors.keySet().stream().anyMatch(e -> e.name().equals(name))) {
      AST.interStatement
          .reportSemanticError(line, "Name \"" + name
              + "\" is already defined in this context. This definition will be skipped.");
      return false;
    } else {
      return true;
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

  public void addArgument(Pattern p) {
    this.addVariable(p);
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

  private final Map<MethodDef, MethodContext> definedMethods = new HashMap<>();
  public String name;
  public ArrayList<String> superClasses;

  public ClassContext(Context parent) {
    super(parent);
  }

  public ClassContext(Context parent, String className) {
    super(parent);
    this.name = className;
  }

  public void addMethod(MethodDef m, MethodContext c) {
    boolean notExistsHere = this.ensureNotExistsHere(m.line(), m.name());
    if (notExistsHere) {
      this.definedMethods.put(m, c);
    }
  }

  public boolean addAttribute(Variable attribute, @Nullable Expression defaultValue) {
    return addVariable(attribute);
  }

  /**
   * Look for a method respecting the passed characteristics, in this specific class's context.
   *
   * @param name   Name of the method to look for
   * @param nbArgs Number of arguments of the method to look for
   * @return the MethodDef if found, or null if no matching method exists
   */
  @Nullable
  public MethodDef methodFor(String name, int nbArgs) {
    AtomicReference<MethodDef> out = new AtomicReference<>();
    out.set(null);
    this.definedMethods.keySet().forEach(methodDef -> {
      if (methodDef.name().equals(name) && methodDef.nbArgs() == nbArgs) {
        out.set(methodDef);
      }
    });
    return out.get();
  }

  @Override
  public boolean addFunction(FunctionDef f, MethodContext c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot create a function in a Class context.");
  }

  @Override
  public boolean addProcedure(ProcedureDef f, MethodContext c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot create a procedure in a Class context.");
  }

  //TODO rewrite the ensure*** operations to take into account the super classes as well as the available methods
}

class FunctorContext extends Context {

  public FunctorContext(Context parent) {
    super(parent);
  }

  public void addImport(ImportClause i) {
    //TODO
  }
}
