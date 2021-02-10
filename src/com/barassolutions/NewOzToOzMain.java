// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions;

/**
 * Driver class for the nozc (NewOz to Oz) compiler using JavaCC front-end. This is the main entry point
 * for the compiler. The compiler proceeds as follows:
 *
 * <p>(1) It reads arguments that affect its behavior.
 *
 * <p>(2) It builds a scanner.
 *
 * <p>(3) It builds a parser (using the scanner) and parses the input for producing
 * an abstact syntax tree (AST).
 *
 * <p>(4) It sends the preAnalyze() message to that AST, which recursively descends
 * the tree so far as the member headers for declaring types and members in the symbol table
 * (represented as a string of contexts). TODO rewrite this
 *
 * <p>(5) It sends the analyze() message to that AST for declaring local variables,
 * and checking and assigning types to expressions. Analysis also sometimes rewrites some of the
 * abstract syntax trees for clarifying the semantics. Analysis does all of this by recursively
 * descending the AST down to its leaves. TODO rewrite this
 *
 * <p>(6) Finally, it sends a codegen() message to the AST for generating code.
 * Again, codegen() recursively descends the tree, down to its leaves, generating Oz code for
 * producing a .oz or .ozf file.
 */
public class NewOzToOzMain {

  private static boolean errorHasOccurred;

  public static void main(String[] args) {
    System.out.println("Launching to NewOz to Oz compiler...");

    String caller = "java com.barassolutions.NewOzToOzMain";
    String sourceFile = "";
    String debugOption = null;
    String outputDir = "";
    errorHasOccurred = false;
    for (int i=0; i<args.length; i++) {
      //TODO find a nice library to have a clean arguments handling ?
    }
  }

  public static boolean errorHasOccurred() {
    return errorHasOccurred;
  }

  private static void printUsage(String caller) {
    String usage = "Usage: "
        + caller
        + " <options> <source file>\n"
        + "where possible options include:\n"
        + "  -t Only tokenize input and print tokens to STDOUT\n"
        + "  -p Only parse input and print AST to STDOUT\n"
        + "  -pa Only parse and pre-analyze input and print AST to STDOUT\n"
        + "  -a Only parse, pre-analyze, and analyze input and print AST to STDOUT\n"
        + "  -d <dir> Specify where to place output files; default = .";
    System.out.println(usage);
  }
}
