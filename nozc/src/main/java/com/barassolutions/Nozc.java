package com.barassolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Properties;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Help.Ansi;
import picocli.CommandLine.Help.Ansi.Style;
import picocli.CommandLine.Help.ColorScheme;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/* Read the Picocli documentation at https://picocli.info/ */
//TODO generate specific exit codes for custom exceptions ? https://picocli.info/#_exception_exit_codes and https://picocli.info/#_business_logic_exceptions
//TODO customize standard help message color scheme ? https://picocli.info/#_configuring_fixed_elements
@Command(name = "nozc",//name = "main/java/nozc",
    synopsisHeading = "@|bold,underline Usage:|@%n%n",
    descriptionHeading = "%n@|bold,underline Description:|@%n%n",
    description = "Compiles a NewOz program file, or translate it to a valid equivalent Oz program file.",
    parameterListHeading = "%n@|bold,underline Parameters:|@%n",
    optionListHeading = "%n@|bold,underline Options:|@%n",
    exitCodeListHeading = "%n@|bold,underline Exit codes:|@%n",
    exitCodeList = {"0:Successful program execution",
        "1:Exception occurred during program execution", "2:Invalid input (usage)"},
    mixinStandardHelpOptions = true,
    versionProvider = Nozc.VersionProvider.class,
    sortOptions = false,
    usageHelpAutoWidth = true)
public class Nozc implements Callable<Integer> {

  // see mixinStandardHelpOptions
    /*@Option(names = {"-h", "--help"}, usageHelp = true, description = "display a description of valid command usages")
    private boolean printHelp = false;
    */

  public static boolean errorHasOccurred;

  private static Ansi usedAnsi = Ansi.AUTO;

  @Option(names = {
      "--no-keep"}, negatable = true, description = "Keep the intermediary Oz files in the output folder. True by default")
  boolean deleteOzFiles;
  @Option(names = {"-t",
      "--tokenize"}, description = "Tokenize the NewOz input, print the tokens to STDOUT, and then stop the compilation")
  boolean stopAtTokenizer;
  @Option(names = {"-s",
      "--scan"}, description = "Scan/parse the NewOz input, print the AST to STDOUT, and then stop the compilation")
  boolean stopAtParser;
  @Option(names = {"-p",
      "--preAnalyze"}, description = "Pre-analyze the NewOz input, print the AST to STDOUT, and then stop the compilation")
  boolean stopAtPreAnalysis;
  @Option(names = {"-a",
      "--analyze"}, description = "Analyze the NewOz input, print the AST to STDOUT, and then stop the compilation")
  boolean stopAtAnalysis;
  @Option(names = {"-v", "--verbose"})
  boolean verbose;
  @Parameters(description = "The .noz file(s) to compile or translate.", paramLabel = "FILE", arity = "1..*")
  private File[] inputFiles;
  @Option(names = {"-o",
      "--out"}, description = "Name of the output file. This option will be ignored if you pass more than one input file.")
  private File outputFile;
  @Option(names = {"-d",
      "--directory"}, description = "Output directory for compiled and/or translated files (default: ${DEFAULT_VALUE})", arity = "1", defaultValue = ".")
  private File destDirectory;

  public static void main(String[] args) {
    PrintWriter out = new PrintWriter(System.out);
    PrintWriter err = new PrintWriter(System.err);
    ColorScheme colorScheme = new ColorScheme.Builder()
        .commands(Style.bold)
        .options(Style.fg_yellow)
        .parameters(Style.fg_yellow)
        .optionParams(Style.italic)
        .errors(Style.fg_red, Style.bold)
        .stackTraces(Style.italic)
        .build();

    Nozc nozc = new Nozc();

    CommandLine cmd = new CommandLine(nozc)
        .setOut(out)
        .setErr(err)
        .setColorScheme(colorScheme);

    cmd.printVersionHelp(out);
    System.out.println("================================================");
    int exitCode = cmd.execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    System.out.println(
        "Compiling " + inputFiles.length + " NewOz files to destination directory " + destDirectory
            .getPath());
    for (File inputFile : inputFiles) {
      errorHasOccurred = false;
      if (outputFile == null) {
        outputFile = new File(destDirectory,
            inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.')) + ".oz");
      } else {
        outputFile = new File(destDirectory, outputFile.getName());
      }
      System.out.println("Created output file " + outputFile.toString());

      /* Create the Scanner */
      JavaCCParserTokenManager scanner;
      try {
        scanner = new JavaCCParserTokenManager(new SimpleCharStream(
            new FileInputStream(inputFile),
            1,
            1)
        );
      } catch (FileNotFoundException e) {
        return 1;
      }
      if (scanner == null) {
        System.err.println(getErrorString("Error in scanner initialization"));
        return 1;
      }

      // Tokenize the NewOz input, print the tokens to STDOUT, and then stop the compilation
      if (stopAtTokenizer) {
        Token token;
        PrettyPrinter p = new PrettyPrinter();
        do {
          token = scanner.getNextToken();
          if (token.kind == JavaCCParserConstants.ERROR) {
            System.err.println(getErrorString(
                inputFile + ":" + token.beginLine + ": Unidentified input token: '" + token.image
                    + "'"));
            errorHasOccurred = true;
          } else {
            p.printf("%d\t : %s = %s\n", token.beginLine,
                JavaCCParserConstants.tokenImage[token.kind],
                token.image);
          }
        } while (token.kind != JavaCCParserConstants.EOF);
        return 0;
      }

      /* Create the Parser */
      InterStatement ast = null;
      JavaCCParser parser;
      try {
        //This is not actually an error : IntelliJ just tries to match this with the class
        // in newoz.jj, instead of the valid constructor in JavaCC.java.
        // This does not cause an error at runtime.
        parser = new JavaCCParser(scanner);
        parser.fileName(inputFile.getName());
        ast = parser.interStatement();
        errorHasOccurred |= parser.errorHasOccurred();
      } catch (ParseException e) {
        System.err.println(e.getMessage());
      }
      if (ast == null) {
        System.err.println(getErrorString("Error in parser initialization"));
        return 1;
      }

      // Scan/parse the NewOz input, print the AST to STDOUT, and then stop the compilation
      if (stopAtParser) {
        ast.writeToStdOut(new PrettyPrinter());
        return 0;
      }
      if (errorHasOccurred) {
        return 1;
      }

      /* Pre-analyze the input */
      ast.setFileName(inputFile.getName());
      ast.preAnalyze();
      errorHasOccurred |= ast.errorHasOccurred();

      if (stopAtPreAnalysis) {
        ast.writeToStdOut(new PrettyPrinter());
        return 0;
      }
      if (errorHasOccurred) {
        return 1;
      }

      /* Analyze the input */
      ast.analyze(null);
      errorHasOccurred |= ast.errorHasOccurred();

      if (stopAtAnalysis) {
        ast.writeToStdOut(new PrettyPrinter());
        return 0;
      }
      if (errorHasOccurred) {
        return 1;
      }

      /* Generate Oz code */
      Emitter emitter = new Emitter(outputFile);
      ast.codegen(emitter);
      emitter.close();
      errorHasOccurred |= ast.errorHasOccurred();
      if (errorHasOccurred) {
        return 1;
      }

      /**
       //TODO now launch to Oz compiler (ozc)
       * Use -c flag on ozc compiler if isFunctor==true
       **/

      /* Delete the generated Oz files if so required */
      if (deleteOzFiles) {
        System.out.println("Deleting file \"" + outputFile.getName() + "\" as requested...");
        Files.delete(outputFile.toPath());
      }
    }

    // No error occurred
    return 0;
  }

  private static String getErrorString(String s) {
    return usedAnsi.string("@|bold,red " + s + "|@");
  }

  static class VersionProvider implements IVersionProvider {

    public String[] getVersion() throws Exception {

      InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties");
      Properties props = new Properties();
      props.load(in);
      String version = props.get("version").toString();

      return new String[]{"@|yellow ${COMMAND-FULL-NAME} " + version + "|@",
          "(c) Martin \"Barsingha\" Vandenbussche 2021",
          "Run via Picocli " + CommandLine.VERSION,
          "JVM: ${java.version} (${java.vendor} ${java.vm.name} ${java.vm.version})",
          "OS: ${os.name} ${os.version} ${os.arch}",
          "This software is distributed under the BSD license, available at https://github.com/MaVdbussche/nozc/blob/master/LICENSE"};
    }
  }
}
