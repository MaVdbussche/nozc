package com.barassolutions;

import com.barassolutions.ClassDescriptor.SubType;
import com.barassolutions.util.Utils;
import java.util.ArrayList;
import java.util.List;

public class ClassDef extends Declaration {

  /**
   * The class' name.
   */
  private final String className;

  /**
   * The descriptors of this class.
   */
  private final List<ClassDescriptor> descriptors;

  /**
   * The methods defined in this class.
   */
  private final List<MethodDef> methods;

  public ClassDef(int line, String name, List<ClassDescriptor> descriptors,
      List<MethodDef> methods) {
    super(line);
    this.className = name;
    Utils.sortDescriptors(descriptors);
    this.descriptors = descriptors;
    this.methods = methods;
  }

  public ClassDef(ClassDefAnonym c) {
    this(c.line(), c.name(), c.descriptors(), c.methods());
  }

  public String name() {
    return className;
  }

  public ArrayList<String> superClassesNames() {
    ArrayList<String> out = new ArrayList<>();
    descriptors.forEach(d -> out.addAll(d.extendedClasses()));
    return out;
  }

  public List<MethodDef> methods() {
    return methods;
  }

  @Override
  public AST analyze(Context context) {
    ClassContext classContext = new ClassContext(context, className);
    context.addClass(this, classContext);

    // Stream are fun ! (maybe not for the next reader...)
    int extensionsFound = (int) descriptors.stream()
        .map(d -> (ClassDescriptor) d.analyze(classContext)) //Analyze each of them
        .filter(d -> d.type() == SubType.EXTENSION)
        .count(); //Find the number of extension "statements"
    if (extensionsFound > 1) {
      interStatement.reportSemanticError(line(),
          "Only one class extension is allowed. To use multiple inheritance, separate the classes names with commas instead.");
    }

    methods.forEach(m -> m = (MethodDef) m.analyze(classContext));

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.token(TokenOz.CLASS);
    output.space();
    output.literal(Utils.ozFriendlyName(className));
    output.newLine();
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
        output.space();
        output.token(TokenOz.COLON);
        output.space();
        descriptor.attributeValue().codegen(output);
        output.newLine();
      } else if (descriptor.type().equals(SubType.PROPERTY)) {
        //TODO not supported in the first release
      } else if (descriptor.type().equals(SubType.FEATURE)) {
        output.token(TokenOz.FEAT);
        //TODO not supported in the first release
      }
    }
    this.methods.forEach(m -> {
      m.codegen(output);
    });
    output.token(TokenOz.END);
    output.newLine();
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
