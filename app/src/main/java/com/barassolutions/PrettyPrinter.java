package com.barassolutions;

/**
 * A utility class that allows pretty (indented) printing to STDOUT.
 */
public class PrettyPrinter {

  /** Width of an indentation. */
  private final int indentWidth;

  /** Current indentation (number of blank spaces). */
  private int indent;

  /**
   * Construct a PrettyPrinter with an indentation width of 2.
   */
  public PrettyPrinter() {
    this(2);
  }

  /**
   * Construct a PrettyPrinter given the indentation width.
   *
   * @param indentWidth
   *            number of blank spaces for an indent.
   */
  public PrettyPrinter(int indentWidth) {
    this.indentWidth = indentWidth;
    indent = 0;
  }

  /**
   * Indent right.
   */
  public void indentRight() {
    indent += indentWidth;
  }

  /**
   * Indent left.
   */
  public void indentLeft() {
    if (indent > 0) {
      indent -= indentWidth;
    }
  }

  /**
   * Print an empty line to STDOUT.
   */
  public void println() {
    doIndent();
    System.out.println();
  }

  /**
   * Print the specified string (followed by a newline) to STDOUT.
   *
   * @param s
   *            string to print.
   */
  public void println(String s) {
    doIndent();
    System.out.println(s);
  }

  /**
   * Print the specified string to STDOUT.
   *
   * @param s
   *            string to print.
   */
  public void print(String s) {
    doIndent();
    System.out.print(s);
  }

  /**
   * Print args to STDOUT according to the specified format.
   *
   * @param format
   *            format specifier.
   * @param args
   *            values to print.
   */
  public void printf(String format, Object... args) {
    doIndent();
    System.out.printf(format, args);
  }

  /**
   * Indent by printing spaces to STDOUT.
   */
  private void doIndent() {
    for (int i = 0; i < indent; i++) {
      System.out.print(" ");
    }
  }

}
