package com.barassolutions;

public class Feature extends Expression {

  private final String image;

  public Feature(int line, int value) {
    super(line);
    this.image = String.valueOf(value);
  }

  public Feature(int line, String image) {
    super(line);
    this.image = image;
  }

  public String image() {
    return image;
  }

  @Override
  public Expression analyze(Context context) {
    //TODO nothing to do I think
    return this;
  }

  @Override
  public void codegen(Emitter output) {
    output.literal(image);
  }

  @Override
  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Feature image=\"%s\">\n", image);
  }
}
