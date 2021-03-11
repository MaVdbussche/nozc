package com.barassolutions.core;

import org.jetbrains.annotations.Nullable;

public class Method implements ClassElement {

  public Method(int line, MethodHead head, Variable name, @Nullable InExpression expression, @Nullable InStatement statement) {
    super(line);
    assert (expression!=null || statement!=null);


  }
}
