package com.barassolutions.core;

import com.barassolutions.Emitter;
import com.barassolutions.PrettyPrinter;
import java.util.ArrayList;

public class ClassDef extends Declaration {

  private String className;

  private ArrayList<ClassDescriptor> descriptors;

  private ArrayList<MethodDef> methods;

  public ClassDef(int line, String name, ArrayList<ClassDescriptor> descriptors, ArrayList<MethodDef> methods) {
    super(line);
    this.className = name;
    this.descriptors = descriptors;
    this.methods = methods;
  }

  @Override
  public AST analyze(Context context) {
    //TODO
  }

  @Override
  public void codegen(Emitter output) {
    //TODO
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    //TODO
  }
}
