package com.barassolutions;

import com.barassolutions.ClassDescriptor.SubType;
import java.util.ArrayList;

public class ClassDef extends Declaration { //TODO make sure this follows new format (see FunctionDef & ProcedureDef & FunctorDef & MethodDef)

  private final String className;

  private final ArrayList<ClassDescriptor> descriptors;

  private final ArrayList<MethodDef> methods;

  public ClassDef(int line, String name, ArrayList<ClassDescriptor> descriptors,
      ArrayList<MethodDef> methods) {
    super(line);
    this.className = name;
    Utils.sortDescriptors(descriptors);
    this.descriptors = descriptors;
    this.methods = methods;
  }

  @Override
  public AST analyze(Context context) {
    ClassContext classContext = new ClassContext(context, className);

    descriptors.forEach(d -> d = (ClassDescriptor) d.analyze(classContext));
    //TODO check we only have 1 EXTENSION descriptor

    methods.forEach(m -> m = (MethodDef) m.analyze(classContext));

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.CLASS);
    output.space();
    output.literal(Utils.ozFriendlyName(className));
    output.space();
    //The following piece of codes relies on the fact that the list is sorted according to
    // the ClassDescriptor.SubType enum.
    // It is far from elegant, but it should work.
    for (ClassDescriptor descriptor : descriptors) {
      if (descriptor.type().equals(SubType.EXTENSION)) {
        output.token(TokenOz.FROM);
        descriptor.extendedClasses().forEach(n -> {
          output.space();
          output.literal(n);
        });
        output.newLine();
      } else if (descriptor.type().equals(SubType.ATTRIBUTE)) {
        output.token(TokenOz.ATTR);
        output.space();
        output.literal(descriptor.attribute().name());
      } else if (descriptor.type().equals(SubType.PROPERTY)) {
        //TODO
      } else if (descriptor.type().equals(SubType.FEATURE)) {
        output.token(TokenOz.FEAT);
        //TODO
      }
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<ClassDeclaration line=\"%d\" name=\"%s\">\n", line(), className);
    p.indentRight();
    descriptors.forEach(a -> {
      a.writeToStdOut(p);
    });
    methods.forEach(a -> {
      a.writeToStdOut(p);
    });
    p.indentLeft();
    p.printf("</ClassDeclaration>\n");
  }
}
