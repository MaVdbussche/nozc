package com.barassolutions;

import java.io.PrintStream;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class that allows pretty (indented) printing to a stream.
 */
public class PrettyPrinter {

  private final PrintStream out;

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
    out = System.out;
    this.indentWidth = indentWidth;
    indent = 0;
  }

  /**
   * Construct a PrettyPrinter given the output Stream and an indentation width of 2.
   *
   * @param out
   *            PrintStream to use for printing.
   */
  public PrettyPrinter(PrintStream out) {
    this(out, 2);
  }

  /**
   * Construct a PrettyPrinter given the output Stream and indentation width.
   *
   * @param out
   *            PrintStream to use for printing.
   * @param indentWidth
   *            number of blank spaces for an indent.
   */
  public PrettyPrinter(PrintStream out, int indentWidth) {
    this.out = out;
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
   * Print an empty line to the output stream.
   */
  public void println() {
    doIndent();
    out.println();
  }

  /**
   * Print the specified string (followed by a newline) to the output stream.
   *
   * @param s
   *            string to print.
   */
  public void println(@NotNull String s) {
    doIndent();
    out.println(s);
  }

  /**
   * Print the specified string to the output stream.
   *
   * @param s
   *            string to print.
   */
  public void print(@NotNull String s) {
    //doIndent();
    out.print(s);
  }

  /**
   * Print args to the output stream according to the specified format.
   *
   * @param format
   *            format specifier.
   * @param args
   *            values to print.
   */
  public void printf(@NotNull String format, Object... args) {
    doIndent();
    out.printf(format, args);
  }

  /**
   * Indent by printing spaces to the output stream.
   */
  private void doIndent() {
    for (int i = 0; i < indent; i++) {
      out.print(" ");
    }
  }

  /**
   * Close files, not STDOUT.
   */
  public void close() {
    if (! out.equals(System.out)) {
        out.close();
    }
  }

}
