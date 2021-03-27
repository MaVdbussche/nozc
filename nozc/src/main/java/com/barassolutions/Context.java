package com.barassolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.jetbrains.annotations.Nullable;

public class Context {

  private final Context parent;

  protected final ArrayList<Pattern> definedVars = new ArrayList<>();
  protected final Map<FunctionDef, MethodContext> definedFunctions = new HashMap<>();
  protected final Map<ProcedureDef, MethodContext> definedProcedures = new HashMap<>();
  protected final Map<FunctorDef, FunctorContext> definedFunctors = new HashMap<>();
  protected final Map<ClassDef, ClassContext> definedClasses = new HashMap<>();

  public Context(Context parent) {
    this.parent = parent;
  }

  public boolean addVariable(Pattern p) {
    boolean notExistsHere;
    if (p instanceof Variable v) {
      notExistsHere = this.ensureNotExistsHere(v.line(), v.name());
      System.out.println("Adding Variable in context <name:"+v.name()+" constant:"+v.isConstant()+" readMode:"+v.readMode()+" usedAsPattern:"+v.usedAsPattern()+">");
    } else if (p instanceof Record r) {
      notExistsHere = this.ensureNotExistsHere(r.line(), r.name());
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

  public boolean addClass(ClassDef f, ClassContext c) {
    boolean notExistsHere = this.ensureNotExistsHere(f.line(), f.name());
    if (notExistsHere) {
      this.definedClasses.put(f, c);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Look for a variable respecting the passed name characteristic, in this or any of the parent
   * contexts.
   *
   * @param name Name of the variable to look for
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
   * @param name Name of the variable to look for
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
    if (this instanceof MethodContext m) {
      return m;
    } else if (parent == null) {
      return null;
    } else {
      return parent.findMethodContext();
    }
  }

  public ClassContext findClassContext() {
    if (this instanceof ClassContext c) {
      return c;
    } else if (parent == null) {
      return null;
    } else {
      return parent.findClassContext();
    }
  }

  public ClassContext findClassContext(String className) {
    if (this instanceof ClassContext c && c.name.equals(className)) {
      return (ClassContext) this;
    } else if (parent == null) {
      return null;
    } else {
      return parent.findClassContext();
    }
  }

  /**
   * Displays an error if the passed name is NOT present in this particular context.
   *
   * @return true if the name IS present in this particular context, false otherwise.
   */
  public boolean ensureExistsHere(int line, String name) {
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
        || definedFunctors.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedClasses.keySet().stream().anyMatch(e -> e.name().equals(name))) {
      return true;
    } else {
      AST.interStatement
          .reportSemanticError(line, "Name \"" + name + "\" does not exist in this context.");
      return false;
    }
  }

  /**
   * Displays an error if the passed name IS present in this particular context.
   *
   * @return true if the name is NOT present in this particular context, false otherwise.
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
        || definedFunctors.keySet().stream().anyMatch(e -> e.name().equals(name))
        || definedClasses.keySet().stream().anyMatch(e -> e.name().equals(name))) {
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
   * Look for a method respecting the passed characteristics, in this specific class's context. The
   * reason we don't search "up" recursively is because the usage of super() requires the user to
   * specify the superclass. This allows analyze() methods to call this method on the exact class
   * right away.
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
  public boolean addProcedure(ProcedureDef f, MethodContext c)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Cannot create a procedure in a Class context.");
  }

  /**
   * Displays an error if the passed name IS present in this particular context.
   *
   * @return true if the name is NOT present in this particular context, false otherwise.
   */
  @Override
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
        || definedMethods.keySet().stream().anyMatch(e -> e.name().equals(name))) {
      AST.interStatement
          .reportSemanticError(line, "Name \"" + name
              + "\" is already defined in this class' context. This definition will be skipped.");
      return false;
    } else {
      return true;
    }
  }
}

class FunctorContext extends Context {

  public FunctorContext(Context parent) {
    super(parent);
  }

  public void addImport(ImportClause i) {
    i.getValues().forEach((s, v) -> {
      this.addVariable(v);
    });
  }
}
