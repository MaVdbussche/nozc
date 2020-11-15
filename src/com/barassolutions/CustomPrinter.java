package com.barassolutions;

/**
 * A utility class that allows pretty (indented) printing to STDOUT.
 */
class CustomPrinter {

  /** Width of an indentation. */
  private final int indentWidth;

  /** Current indentation (number of blank spaces). */
  private int indent;

  /**
   * Construct a CustomPrinter with an indentation width of 2.
   */
  public CustomPrinter() {
    this(2);
  }

  /**
   * Construct a CustomPrinter given the indentation width.
   */
  public CustomPrinter(int indentWidth) {
    this.indentWidth = indentWidth;
    indent = 0;
  }

  public void indentRight() {
    indent += indentWidth;
  }

  public void indentLeft() {
    if (indent > 0) {
      indent -= indentWidth;
    }
  }

  public void println() {
    doIndent();
    System.out.println();
  }

  public void println(String s) {
    doIndent();
    System.out.println(s);
  }

  public void print(String s) {
    doIndent();
    System.out.print(s);
  }

  public void printf(String format, Object... args) {
    doIndent();
    System.out.printf(format, args);
  }

  private void doIndent() {
    for (int i = 0; i < indent; i++) {
      System.out.print(" ");
    }
  }
}
