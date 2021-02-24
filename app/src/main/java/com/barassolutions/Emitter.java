// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions;

/**
 * This class provides a high level interface for creating representation of Oz files.
 * <p>
 * NOzC uses this interface to produce target Oz code from a NewOz source program.
 * //TODO mimic a CharStream, with open() and close()
 */
public class Emitter {

  /**
   * Name of the file.
   */
  private String name;

  /**
   * Destination directory for the class.
   */
  private String destDir;

  /**
   * Whether an error occurred while creating/writing the file.
   */
  private boolean errorHasOccurred;

  /**
   * Construct an Emitter instance.
   */
  public Emitter() {
    destDir = ".";
  }

  /**
   * Set the destination directory for the class file to the specified value.
   *
   * @param destDir destination directory.
   */
  public void destinationDir(String destDir) {
    this.destDir = destDir;
  }

  /**
   * Has an emitter error occurred up to now?
   *
   * @return true or false.
   */
  public boolean isInErrorState() {
    return errorHasOccurred;
  }

  /**
   * Write a token at the current position in the output file
   *
   * @param token the id of the token to generate.
   * @see TokenOz
   */
  public void token(int token) {
    //TODO
  }

  /**
   * Print a space at the current position in the output file.
   */
  public void space() {
    //TODO
  }

  /**
   * Print a line at the current position in the output file.
   * //TODO remember indentation level etc for readability!
   */
  public void newLine() {

  }
  /**
   * Close the stream.
   */
  public void close() {
    //TODO
  }
}
