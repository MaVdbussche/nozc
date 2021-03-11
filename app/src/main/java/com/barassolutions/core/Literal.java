package com.barassolutions.core;

import com.barassolutions.TokenOz;
import org.jetbrains.annotations.NotNull;

public class Literal extends Term {

  @NotNull
  private String image = "<image>";

  public Literal(int line, Type type) {
    super(line);
    this.type = type;
    switch (type) {
      case NIL -> this.image = TokenOz.NIL.image();
      case UNIT -> this.image = TokenOz.UNIT.image();
      case UNDERSCORE -> this.image = TokenOz.UNDERSCORE.image();
    }
  }

  public Literal(int line, Type type, boolean value) {
    super(line);
    this.type = type;
    this.image = String.valueOf(value);
  }

  public Literal(int line, Type type, @NotNull String image) {
    suuper(line);
    this.type = type;
    this.image = image;
  }

  public Literal(int line, Type type, @NotNull String image, boolean bool) {
    super(line);
    this.type = type;
    this.image = image;
  }
}
