// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * This class provides a high level interface for creating representation of Oz files.
 * <p>
 * NOzC uses this interface to produce target Oz code from a NewOz source program.
 */
public class Emitter {

  /**
   * The underlying PrettyPrinter.
   */
  private final PrettyPrinter out;

  /**
   * Construct an Emitter instance.
   *
   * @param outputFile the file to write to.
   */
  public Emitter(File outputFile) throws FileNotFoundException {
    this.out = new PrettyPrinter(new PrintStream(outputFile));
  }

  /**
   * Write a token at the current position in the output file
   *
   * @param token the id of the token to generate.
   * @see TokenOz
   */
  public void token(TokenOz token) {
    out.print(token.image());
  }

  /**
   * Write a literal at the current position in the output file
   *
   * @param literal the String to generate.
   * @see TokenOz
   */
  public void literal(String literal) {
    out.print(literal);
  }

  /**
   * Print a space at the current position in the output file.
   */
  public void space() {
    out.print(" ");
  }

  /**
   * Print a line at the current position in the output file.
   */
  public void newLine() {
    out.println();
  }

  public void indentLeft() { out.indentLeft(); }

  public void indentRight() { out.indentRight(); }

  /**
   * Close the stream.
   */
  public void close() {
    out.close();
  }
}
