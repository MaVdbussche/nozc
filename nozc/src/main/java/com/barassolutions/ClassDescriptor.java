package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class ClassDescriptor extends Declaration implements ClassElement {

  public enum SubType {
    EXTENSION, ATTRIBUTE, PROPERTY, FEATURE //TODO add properties & features in future release
  }

  private final ArrayList<String> extendedClassesNames;

  private final Variable attribute;

  /**
   * Optional default value given to the attribute. Will always be null for properties.
   */
  @Nullable
  private final Expression defaultValue;

  private final SubType type;

  public ClassDescriptor(int line, SubType type, ArrayList<String> extensions) {
    super(line);
    assert type.equals(SubType.EXTENSION);
    this.extendedClassesNames = extensions;
    this.attribute = null;
    this.defaultValue = null;
    this.type = type;
  }

  public ClassDescriptor(int line, SubType type, Variable var, @Nullable Expression expression) {
    super(line);
    assert (type.equals(SubType.ATTRIBUTE) || type.equals(SubType.PROPERTY));
    this.extendedClassesNames = new ArrayList<>();
    this.attribute = var;
    if (type.equals(SubType.ATTRIBUTE)) {
      this.defaultValue = expression;
    } else { //This is a PROPERTY (see assertion above)
      this.defaultValue = null;
    }
    this.type = type;
  }

  public Variable attribute() {
    return this.attribute;
  }

  public Expression attributeValue() {
    return this.defaultValue;
  }

  public ArrayList<String> extendedClasses() {
    return this.extendedClassesNames;
  }

  public SubType type() {
    return type;
  }

  @Override
  public AST analyze(Context context) {
    ClassContext classContext = (ClassContext) context;

    if (type.equals(SubType.EXTENSION)) {
      classContext.superClasses.addAll(extendedClassesNames);
    } else if (type.equals(SubType.ATTRIBUTE)) {
      classContext.addAttribute(attribute, defaultValue);
    } else if (type.equals(SubType.PROPERTY)) {
      interStatement.reportSemanticError(line(), "Class properties are not supported in this release.");
      //TODO add properties & features in future release
    } else if (type.equals(SubType.FEATURE)) {
      interStatement.reportSemanticError(line(), "Class features are not supported in this release.");
      //TODO add properties & features in future release
    }

    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (type.equals(SubType.EXTENSION)) {
      output.token(TokenOz.FROM);
      extendedClassesNames.forEach(n -> {
        output.space();
        output.literal(n);
      });
    } else if (type.equals(SubType.ATTRIBUTE)) {
      output.token(TokenOz.ATTR);
      output.space();
      output.literal(attribute.name());
      if (defaultValue != null) {
        output.token(TokenOz.COLON);
        output.space();
        defaultValue.codegen(output);
      }
    } else if (type.equals(SubType.PROPERTY)) {
      //TODO add properties & features in future release
    } else if (type.equals(SubType.FEATURE)) {
      //TODO add properties & features in future release
    }
    output.newLine();
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    switch (type) {
      case EXTENSION -> extendedClassesNames.forEach(n -> p.printf("<Extension superclassName=\"%s\">\n", n));
      case ATTRIBUTE -> {
        p.printf("<AttributeDefinition name=\"%s\">\n", attribute.name());
        if (defaultValue != null) {
          p.printf("<Default value:>\n");
          p.indentRight();
          defaultValue.writeToStdOut(p);
          p.indentLeft();
          p.printf("</Default value>\n");
        }
        p.printf("</AttributeDefinition>\n");
      }
      case PROPERTY, FEATURE -> {
        //TODO add properties & features in future release
      }
    }
  }
}
