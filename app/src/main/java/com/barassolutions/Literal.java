package com.barassolutions;

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
    super(line);
    this.type = type;
    this.image = image;
  }

  public Literal(int line, Type type, @NotNull String image, boolean bool) {
    super(line);
    this.type = type;
    this.image = image;
  }

  public String image() {
    return image;
  }

  @Override
  public Expression analyze(Context context) {
    //Nothing to analyze here
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.literal(image);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Literal image=\"%s\">\n", image);
  }
}
