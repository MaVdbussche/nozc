package com.barassolutions;

import com.barassolutions.core.InterStatement;
import java.io.FileNotFoundException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import picocli.CommandLine.IVersionProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

/* Read the Picocli documentation at https://picocli.info/ */
//TODO generate specific exit codes for custom exceptions ? https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
//TODO customize standard help message color scheme ? https://picocli.info/#_configuring_fixed_elements
@Command(name = "main/java/nozc",
    synopsisHeading = "@|bold,underline Usage:|@%n%n",
    descriptionHeading = "%n@|bold,underline Description:|@%n%n",
    description = "Compiles a NewOz program file, or translate it to a valid equivalent Oz program file.",
    parameterListHeading = "%n@|bold,underline Parameters:|@%n",
    optionListHeading = "%n@|bold,underline Options:|@%n",
    exitCodeListHeading = "%n@|bold, underline Exit codes:|@%n",
    exitCodeList = {"0:Successful program execution", "1:something", "24:something bad"},
    mixinStandardHelpOptions = true,
    versionProvider = Nozc.VersionProvider.class,
    sortOptions = false,
    usageHelpAutoWidth = true)
public class Nozc implements Callable<Integer> {

  // see mixinStandardHelpOptions
    /*@Option(names = {"-h", "--help"}, usageHelp = true, description = "display a description of valid command usages")
    private boolean printHelp = false;
    */

  @Parameters(description = "The .noz file(s) to compile or translate.", paramLabel = "FILE", arity = "1..*")
  private File[] inputFiles;

  @Option(names = {"-o",
      "--out"}, description = "name of the output file. When using this option, you can only pass one input file as parameter")
  private File outputFile;

  @Option(names = {"-d",
      "--directory"}, description = "output directory for compiled and/or translated files (default: ${DEFAULT_VALUE})", arity = "1", defaultValue = ".")
  private File destDirectory;

  @Option(names = {
      "--no-keep"}, negatable = true, description = "Keep the intermediary Oz files in the output folder. True by default")
  boolean keepOzFiles;

  @Option(names = {"-t",
      "--tokenize"}, description = "tokenize the NewOz input, print the tokens to STDOUT, and then stop the compilation")
  boolean stopAtTokenizer;

  @Option(names = {"-s",
      "--scan"}, description = "scan/parse the NewOz input, print the AST to STDOUT, and then stop the compilation")
  boolean stopAtParser;

  @Option(names = {"-p",
      "--preAnalyze"}, description = "pre-analyze the NewOz input, print the AST to STDOUT, and then stop the compilation")
  boolean stopAtPreAnalysis;

  @Option(names = {"-a",
      "--analyze"}, description = "analyze the NewOz input, print the AST to STDOUT, and then stop the compilation")
  boolean stopAtAnalysis;

  @Option(names = {"-v", "--verbose"})
  boolean verbose;

  private boolean errorHasOccurred;

  @Override
  public Integer call() throws Exception {
    // This check is done manually because it is too complex for picocli (I think). See "-o" parameter description.
    if (outputFile != null && inputFiles.length != 1) {
      return 3; //TODO catch this custom exit code and display appropriate error/help message https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
    }

    //TODO for each input file : (think about exact loop placement)
    File inputFile = inputFiles[0];
    /* Create the Scanner */
    JavaCCParserTokenManager scanner;
    try {
      scanner = new JavaCCParserTokenManager(new SimpleCharStream(
          new FileInputStream(inputFile),
          1,
          1)
      );
    } catch (FileNotFoundException e) {
      return 2; //TODO catch this custom exit code and display appropriate error/help message https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
    }
    if (scanner == null) {
      System.err.println("Error in scanner initialization"); //TODO use picocli error printing
      return 1; //TODO catch this custom exit code and display appropriate error/help message https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
    }

    // Tokenize the NewOz input, print the tokens to STDOUT, and then stop the compilation
    if (stopAtTokenizer) {
      Token token;
      do {
        token = scanner
            .getNextToken(); //Can't do much about this warning; this class is auto-generated
        if (token.kind == JavaCCParserConstants.ERROR) {
          System.err.printf(
              "%s:%d: Unidentified input token: '%s'\n",
              inputFile, token.beginLine, token.image); //TODO use picocli error printing
          errorHasOccurred = true;
        } else {
          System.out.printf("%d\t : %s = %s\n", token.beginLine,
              JavaCCParserConstants.tokenImage[token.kind],
              token.image); //TODO use PrettyPrinter ?
        }
      } while (token.kind != JavaCCParserConstants.EOF);
      return 0;
    }

    /* Create the Parser */
    InterStatement ast = null;
    JavaCCParser parser;
    try {
      parser = new JavaCCParser(scanner); //TODO create an annotation to ignore this error ?
      parser.fileName(inputFile.getName());
      ast = parser
          .interStatement(); //Can't do much about this warning; that class is auto-generated
      errorHasOccurred |= parser.errorHasOccurred();
    } catch (ParseException e) {
      System.err.println(e.getMessage()); //TODO use picocli error printing
    }
    if (ast == null) {
      System.err.println("Error in parser initialization"); //TODO use picocli error printing
      return 1; //TODO catch this custom exit code and display appropriate error/help message https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
    }

    // Scan/parse the NewOz input, print the AST to STDOUT, and then stop the compilation
    if (stopAtParser) {
        ast.writeToStdOut(new PrettyPrinter());
        return 0;
    }
    if (errorHasOccurred) {
      return 1; //TODO catch this custom exit code and display appropriate error/help message https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
    }

    /* Pre-analyze the input */
    ast.fileName(inputFile.getName());
    ast.preAnalyze();
    errorHasOccurred |= ast.errorHasOccurred();

    if (stopAtPreAnalysis) {
      ast.writeToStdOut(new PrettyPrinter());
      return 0;
    }
    if (errorHasOccurred) {
      return 1; //TODO catch this custom exit code and display appropriate error/help message https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
    }

    /* Analyze the input */
    ast.analyze(null);
    errorHasOccurred |= ast.errorHasOccurred();

    if (stopAtAnalysis) {
      ast.writeToStdOut(new PrettyPrinter());
      return 0;
    }
    if (errorHasOccurred) {
      return 1; //TODO catch this custom exit code and display appropriate error/help message https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
    }

    /* Generate Oz code */
    Emitter emitter = new Emitter();
    emitter.destinationDir(destDirectory.toString());
    ast.codegen(emitter);
    errorHasOccurred |= ast.errorHasOccurred();
    if (errorHasOccurred) {
      return 1; //TODO catch this custom exit code and display appropriate error/help message https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
    }

    /**
    //TODO now launch to Oz compiler (ozc)
    **/

    // No error occurred
    return 0;
  }

  public static void main(String[] args) {
    PrintWriter out = null;
    PrintWriter err = null;
    //Ansi ansi = Ansi.AUTO;

    CommandLine cmd = new CommandLine(new Nozc())
        .setOut(out)
        .setErr(err);
    //.setColorScheme(Help.defaultColorScheme(ansi));

    cmd.printVersionHelp(System.out);
    int exitCode = cmd.execute(args);
    System.exit(exitCode);
  }

  static class VersionProvider implements IVersionProvider {

    public String[] getVersion() throws Exception {
      //TODO get it from Gradle build config ?
      return new String[]{"@|yellow ${COMMAND-FULL-NAME} 0.1|@",
          "(c) COPYRIGHT INFO HERE 2021",
          "Picocli " + CommandLine.VERSION,
          "JVM: ${java.version} (${java.vendor} ${java.vm.name} ${java.vm.version})",
          "OS: ${os.name} ${os.version} ${os.arch}"};
    }
  }
}
