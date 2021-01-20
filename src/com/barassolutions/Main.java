// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Driver class for nozc compiler using JavaCC front-end. This is the main entry
 * point for the compiler. The compiler proceeds as follows:
 *
 * (1) It reads arguments that affect its behavior.
 *
 * (2) It builds a scanner.
 *
 * (3) It builds a parser (using the scanner) and parses the input for producing
 * an abstact syntax tree (AST).
 *
 * (4) It sends the preAnalyze() message to that AST, which recursively descends
 * the tree so far as the member headers for declaring types and members in the
 * symbol table (represented as a string of contexts).
 *
 * (5) It sends the analyze() message to that AST for declaring local variables,
 * and checking and assigning types to expressions. Analysis also sometimes
 * rewrites some of the abstract syntax trees for clarifying the semantics.
 * Analysis does all of this by recursively descending the AST down to its
 * leaves.
 *
 * (6) Finally, it sends a codegen() message to the AST for generating code.
 * Again, codegen() recursively descends the tree, down to its leaves,
 * generating Oz code for producing a .oz or .ozf file.
 */

public class Main {

  /** Whether an error occurred during compilation. */
  private static boolean errorHasOccurred;

  /**
   * Entry point.
   */
  public static void main(String args[]) {
    String caller = "java com.barassolutions.Main";
    String sourceFile = "";
    String debugOption = "";
    String outputDir = ".";
    String registerAllocation = "";
    boolean isFunctor = new boolean[];
    errorHasOccurred = false;
    for (int i = 0; i < args.length; i++) {
      if (args[i].endsWith(".noz")) {
        sourceFile = args[i];
        isFunctor = false;
      } else if (args[i].endsWith(".nozf")) {
        sourceFile = args[i];
        isFunctor = true;
      } else if (args[i].equals("-t") || args[i].equals("-p")
              || args[i].equals("-pa") || args[i].equals("-a")) {
        debugOption = args[i];
      } else if (args[i].endsWith("-d") && (i + 1) < args.length) {
        outputDir = args[++i];
      } else {
        printUsage(caller);
        return;
      }
    }
    if (sourceFile.equals("")) {
      printUsage(caller);
      return;
    }

    JavaCCParserTokenManager javaCCScanner = null;
    try {
      javaCCScanner = new JavaCCParserTokenManager(new SimpleCharStream(
              new FileInputStream(sourceFile), 1, 1));
    } catch (FileNotFoundException e) {
      System.err.println("Error: file " + sourceFile + " not found.");
    }

    if (debugOption.equals("-t")) {
      // Just tokenize input and print the tokens to STDOUT
      Token token;
      do {
        token = javaCCScanner.getNextToken();
        if (token.kind == JavaCCParserConstants.ERROR) {
          System.err.printf(
                  "%s:%d: Unidentified input token: '%s'\n",
                  sourceFile, token.beginLine, token.image);
          errorHasOccurred = true;
        } else {
          System.out.printf("%d\t : %s = %s\n", token.beginLine,
                  JavaCCParserConstants.tokenImage[token.kind],
                  token.image);
        }
      } while (token.kind != JavaCCParserConstants.EOF);
      return;
    }

    // Parse input
    CompilationBlock ast = null;
    JavaCCParser javaCCParser = new JavaCCParser(javaCCScanner);
    javaCCParser.fileName(sourceFile);
    try {
      ast = javaCCParser.compilationBlock(); //TODO have different entry point if functor ? Allows to compile both in the same program !
      errorHasOccurred |= javaCCParser.errorHasOccurred();
    } catch (ParseException e) {
      System.err.println(e.getMessage());
    }
    if (debugOption.equals("-p")) {
      ast.writeOut(new CustomPrinter());
      return;
    }
    if (errorHasOccurred) {
      return;
    }

    // Do pre-analysis
    ast.preAnalyze();
    errorHasOccurred |= AST.compilationBlock.isInErrorState();
    if (debugOption.equals("-pa")) {
      ast.writeOut(new CustomPrinter());
      return;
    }
    if (errorHasOccurred) {
      return;
    }

    // Do analysis
    ast.analyze(null);
    errorHasOccurred |= AST.compilationBlock.isInErrorState();
    if (debugOption.equals("-a")) {
      ast.writeOut(new CustomPrinter());
      return;
    }
    if (errorHasOccurred) {
      return;
    }

    // Generate Oz code
    Emitter emitter = new Emitter();
    emitter.destinationDir(outputDir);
    ast.codegen(emitter);
    errorHasOccurred |= emitter.isInErrorState();
    if (errorHasOccurred) {
      return;
    }
  }

  /**
   * Return true if an error occurred during compilation; false otherwise.
   *
   * @return true or false.
   */
  public static boolean isInErrorState() {
    return errorHasOccurred;
  }

  /**
   * Print command usage to STDOUT.
   *
   * @param caller
   *            denotes how this class is invoked.
   */
  private static void printUsage(String caller) {
    String usage = "Usage: "
            + caller
            + " <options> <source file>\n"
            + "where possible options include:\n"
            + "  -t Only tokenize input and print tokens to STDOUT\n"
            + "  -p Only parse input and print AST to STDOUT\n"
            + "  -pa Only parse and pre-analyze input and print "
            + "AST to STDOUT\n"
            + "  -a Only parse, pre-analyze, and analyze input "
            + "and print AST to STDOUT\n"
            + "  -d <dir> Specify where to place output files; default = .";
    System.out.println(usage);
  }

}
