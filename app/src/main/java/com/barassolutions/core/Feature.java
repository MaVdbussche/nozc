package com.barassolutions.core;

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
}
