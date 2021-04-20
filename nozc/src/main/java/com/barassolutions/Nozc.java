package com.barassolutions;

import com.barassolutions.util.Logger;
import com.barassolutions.util.Logger.LogLevel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Ansi;
import picocli.CommandLine.Help.Ansi.Style;
import picocli.CommandLine.Help.ColorScheme;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/* Read the Picocli documentation at https://picocli.info/ */
@Command(name = "nozc", //name = "main/java/nozc",
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

  public static boolean errorHasOccurred;

  private static final Ansi usedAnsi = Ansi.AUTO;

  public static LogLevel loglevel;

  @Option(names = {
      "--no-keep"}, negatable = true, description = "Keep the intermediary Oz files in the output folder. True by default")
  boolean deleteOzFiles;
  @Option(names = {
      "--no-compile"}, negatable = true, description = "Continue the program after Oz code generation. The actual Oz code compilation will be executed. True by default")
  boolean dontRunOzc;
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
  @Option(names = {"-v",
      "--verbosity"}, description = "The verbosity you want to see in output.\nAvailable levels : [OFF, FATAL, ERROR, WARN, INFO, DEBUG, ALL] (default: INFO)", arity = "1", defaultValue = "INFO")
  String verbosity;
  @Parameters(description = "The .noz file(s) to compile or translate.", paramLabel = "FILE", arity = "1..*")
  private String[] inputFilesNames;
  @Option(names = {"-o",
      "--out"}, description = "Name of the output file (WITHOUT ANY EXTENSION !). This option will be ignored if you pass more than one input file.")
  //TODO enforce non-extension
  private String outputFileName;
  @Option(names = {"-d",
      "--directory"}, description = "Output directory for compiled and/or translated files (default: .)", arity = "1", defaultValue = ".")
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

    if (!Arrays.asList(args).contains("-V")) {
      cmd.printVersionHelp(out);
    } //Don't print it twice

    int exitCode = cmd.execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    long startTime = System.nanoTime();
    long time;
    switch (verbosity) {
      case "OFF" -> loglevel = LogLevel.OFF;
      case "FATAL" -> loglevel = LogLevel.FATAL;
      case "ERROR" -> loglevel = LogLevel.ERROR;
      case "WARN" -> loglevel = LogLevel.WARN;
      case "INFO" -> loglevel = LogLevel.INFO;
      case "DEBUG" -> loglevel = LogLevel.DEBUG;
      case "TRACE" -> loglevel = LogLevel.TRACE;
      case "ALL" -> loglevel = LogLevel.ALL;
      default -> {
        loglevel = LogLevel.INFO;
        Logger.warn("Invalid verbosity level. Defaulting to INFO");
      }
    }

    Logger.info(
        "Compiling " + inputFilesNames.length + " NewOz file(s) to destination directory \""
            + destDirectory
            .getPath() + "\"");
    File[] ozFiles = new File[inputFilesNames.length];
    File outputFile;

    for (int i = 0; i < inputFilesNames.length; i++) {
      errorHasOccurred = false;
      if (outputFileName == null || inputFilesNames.length > 1) {
        outputFile = new File(destDirectory,
            inputFilesNames[i].substring(inputFilesNames[i].lastIndexOf(File.separatorChar) + 1,
                inputFilesNames[i].lastIndexOf('.')) + ".oz");
        ozFiles[i] = outputFile;
      } else {
        outputFile = new File(destDirectory, outputFileName + ".oz");
        ozFiles[i] = outputFile;
      }
      Logger.info(getSuccessString("Created output file " + outputFile.toString()));

      /* Create the Scanner */
      Logger.info("==========Scanning started==========");
      time = System.nanoTime();
      JavaCCParserTokenManager scanner;
      try {
        scanner = new JavaCCParserTokenManager(new SimpleCharStream(
            new FileInputStream(inputFilesNames[i]),
            1,
            1)
        );
      } catch (FileNotFoundException e) {
        return 1;
      }
      if (scanner == null) {
        Logger.error(getErrorString("Error in scanner initialization"));
        return 1;
      }

      // Tokenize the NewOz input, print the tokens to STDOUT, and then stop the compilation
      if (stopAtTokenizer) {
        Token token;
        PrettyPrinter p = new PrettyPrinter();
        do {
          token = scanner.getNextToken();
          if (token.kind == JavaCCParserConstants.ERROR) {
            Logger.error(getErrorString(
                inputFilesNames[i] + ":" + token.beginLine + ": Unidentified input token: '"
                    + token.image
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
      Logger.info("==========Scanning done in %s==========", getTimeString(time));

      /* Create the Parser */
      Logger.info("==========Parsing input==========");
      time = System.nanoTime();
      InterStatement ast = null;
      JavaCCParser parser;
      try {
        //This is not actually an error : IntelliJ just tries to match this with the class
        // in newoz.jj, instead of the valid constructor in JavaCC.java (for some reason).
        // This does not cause an error at runtime.
        parser = new JavaCCParser(scanner);
        parser.fileName(inputFilesNames[i]);
        ast = parser.interStatement();
        errorHasOccurred |= parser.errorHasOccurred();
      } catch (ParseException e) {
        System.err.println(e.getMessage());
      }
      if (ast == null) {
        Logger.error(getErrorString("Error in parser initialization"));
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
      Logger.info("==========Parsing done in %s==========", getTimeString(time));

      /* Pre-analyze the input */
      Logger.info("==========Pre-analyzing input==========");
      time = System.nanoTime();
      ast.setFileName(inputFilesNames[i]);
      ast.preAnalyze();
      errorHasOccurred |= ast.errorHasOccurred();

      if (stopAtPreAnalysis) {
        ast.writeToStdOut(new PrettyPrinter());
        return 0;
      }
      if (errorHasOccurred) {
        return 1;
      }
      Logger.info("==========Pre-analyzing done in %s==========", getTimeString(time));

      /* Analyze the input */
      Logger.info("==========Analyzing input==========");
      time = System.nanoTime();
      ast.analyze(null);
      errorHasOccurred |= ast.errorHasOccurred();

      if (stopAtAnalysis) {
        ast.writeToStdOut(new PrettyPrinter());
        return 0;
      }
      if (errorHasOccurred) {
        return 1;
      }
      Logger.info("==========Analyzing done in %s==========", getTimeString(time));

      /* Generate Oz code */
      Logger.info("==========Code generation started==========");
      time = System.nanoTime();
      Emitter emitter = new Emitter(outputFile);
      ast.codegen(emitter);
      emitter.close();
      errorHasOccurred |= ast.errorHasOccurred();
      if (errorHasOccurred) {
        return 1;
      }
      Logger.info("==========Code generation done %s==========", getTimeString(time));

      Logger
          .info(getSuccessString("Translation of file %s completed in " + getTimeString(startTime)),
              inputFilesNames[i]);
    }

    // Part of this code has been found on https://stackabuse.com/executing-shell-commands-with-java/
    if (false) {
      //if (!dontRunOzc) {
      Logger.info("We will now run the Mozart Oz compiler.");
      for (File file : ozFiles) {
        String fileName = file.toString();
        Logger.info("Now compiling " + fileName);
        StringBuilder cmd = new StringBuilder();
        cmd.append("ozc -c ");
        cmd.append(fileName);
        cmd.append(" -o ");
        if (outputFileName == null || inputFilesNames.length > 1) {
          cmd.append(file.getName(), 0, file.getName().lastIndexOf("."));
        } else {
          cmd.append(outputFileName);
        }
        cmd.append(".ozf");
        Logger.debug("Executing command \"" + cmd.toString() + "\"");
        Logger.info("==========Below is the output of the Mozart compiler :==========");

        Process process = Runtime.getRuntime().exec(cmd.toString());
        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        while ((line = bfr.readLine()) != null) {
          System.out.println(line);
        }

        Logger.debug("Waiting for Mozart compiler to exit...");
        process.waitFor();
        Logger.info("==========Mozart compiler exited with code %d==========", process.exitValue());
        if (process.exitValue() != 0) {
          return process.exitValue();
        }
      }
    } else {
      Logger.info("Skipping Mozart Oz compilation, as requested.");
    }

    /* Delete the generated Oz files if so required */
    if (deleteOzFiles) {
      for (File ozFile : ozFiles) {
        Logger.info(
            getSuccessString("Deleting file \"" + ozFile.getName() + "\" as requested..."));
        Files.delete(ozFile.toPath());
      }
    }

    // No error occurred
    return 0;
  }

  private static String getErrorString(String s) {
    return usedAnsi.string("@|bold,red " + s + "|@");
  }

  private static String getSuccessString(String s) {
    return usedAnsi.string("@|bold,green " + s + "|@");
  }

  private static String getTimeString(long startTime) {
    long diffNano = System.nanoTime() - startTime;
    Logger.trace("Printing " + diffNano + " nanoseconds");

    long nbNano = diffNano % 1000;
    long diffMicro = (diffNano - nbNano) / 1000;
    long nbMicro = diffMicro % 1000;
    long diffMilli = (diffMicro - nbMicro) / 1000;
    long nbMilli = diffMilli % 1000;
    long diffSec = (diffMilli - nbMilli) / 1000;
    long nbSec = diffSec % 60;
    long diffMin = (diffSec - nbSec) / 60;
    long nbMin = diffMin % 60;

    return String.format("%dm:%ds:%dms:%dµs:%dns",
        nbMin, nbSec, nbMilli, nbMicro, nbNano);
  }

  static class VersionProvider implements IVersionProvider {

    public String[] getVersion() throws Exception {

      InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties");
      Properties props = new Properties();
      props.load(in);
      String version = props.get("version").toString();

      return new String[]{"@|yellow ${COMMAND-FULL-NAME} " + version + "|@",
          "(c) Martin \"Barasingha\" Vandenbussche 2021",
          "Run via Picocli " + CommandLine.VERSION,
          "JVM: ${java.version} (${java.vendor} ${java.vm.name} ${java.vm.version})",
          "OS: ${os.name} ${os.version} ${os.arch}",
          "This software is distributed under the BSD license, available at https://github.com/MaVdbussche/nozc/blob/master/LICENSE"};
    }
  }
}
