package com.barassolutions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Literal extends Pattern {

  @NotNull
  private String image = "<image>";

  private final boolean usedAsPattern;

  public Literal(int line, Type type, boolean isAPattern) {
    super(line);
    this.type = type;
    switch (type) {
      case NIL -> {
        this.image = TokenOz.NIL.image();
        this.type = Type.ANY;
      }
      case UNIT -> this.image = TokenOz.UNIT.image();
      case UNDERSCORE -> this.image = TokenOz.UNDERSCORE.image();
    }
    this.usedAsPattern = isAPattern;
  }

  public Literal(int line, Type type, boolean value, boolean isAPattern) {
    super(line);
    assert type == Type.BOOLEAN;
    this.type = type;
    this.image = String.valueOf(value);
    this.usedAsPattern = isAPattern;
  }

  public Literal(int line, Type type, @NotNull String image, boolean isAPattern) {
    super(line);
    this.type = type;
    if (type == Type.STRING) {
      this.image = image.substring(1, image.length() - 1);
    } else {
      this.image = image;
    }
    this.usedAsPattern = isAPattern;
  }

  public String image() {
    return image;
  }

  @Override
  @Nullable
  public Iterable<Pattern> patterns() {
    return null;
  }

  @Override
  public Expression analyze(Context context) {
    //Nothing to analyze here
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    if (this.type == Type.STRING) {
      output.token(TokenOz.APOSTROPHE);
      output.literal(image);
      output.token(TokenOz.APOSTROPHE);
    } else {
      output.literal(image);
    }
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Literal image=\"%s\">\n", image);
  }
}
