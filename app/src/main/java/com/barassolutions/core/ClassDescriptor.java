package com.barassolutions.core;

import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class ClassDescriptor implements ClassElement {

  public enum SubType {
    EXTENSION, ATTRIBUTE, PROPERTY
  }

  private final ArrayList<String> extendedClassesNames;

  private final Variable attribute;

  /** Optional value given to the attribute. Will always be null for properties. */
  @Nullable
  private final Expression value;


  public ClassDescriptor(int line, SubType type, ArrayList<String> extensions) {
    assert type.equals(SubType.EXTENSION);
    this.extendedClassesNames = extensions;
    this.attribute = null;
    this.value = null;
  }

  public ClassDescriptor(int line, SubType type, Variable var, @Nullable Expression expression) {
    assert (type.equals(SubType.ATTRIBUTE) || type.equals(SubType.PROPERTY));
    this.extendedClassesNames = null;
    this.attribute = var;
    if (type.equals(SubType.ATTRIBUTE)) {
      this.value = expression;
    } else { //This is a PROPERTY (see assertion above)
      this.value = null;
    }
  }

  public Variable attribute() {
    return this.attribute;
  }

  public Expression attributeValue() {
    return this.value;
  }

  public ArrayList<String> extendedClasses() {
    return this.extendedClassesNames;
  }
}
