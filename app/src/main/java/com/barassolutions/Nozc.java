package com.barassolutions;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import picocli.CommandLine.IVersionProvider;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
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
        exitCodeList = {"0:Successful program execution","1:something","24:something bad"},
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
    private List<File> inputFiles;

    @Option(names = {"-o", "--out"}, description = "name of the output file. When using this option, you can only pass one input file as parameter") //TODO manually do this check
    private File outputFile;

    @Option(names = {"-d", "--directory"}, description = "output directory for compiled and/or translated files (default: ${DEFAULT_VALUE})", arity = "1", defaultValue = ".")
    private File destDirectory;

    @Option(names = {"--no-keep"}, negatable = true, description = "Keep the intermediary Oz files in the output folder. True by default")
    boolean keepOzFiles;

    @Option(names = {"-t", "--tokenize"}, description = "tokenize the NewOz input, print the tokens to STDOUT, and then stop the compilation")
    boolean tokenize;

    @Option(names = {"-s", "--scan"}, description = "scan/parse the NewOz input, print the AST to STDOUT, and then stop the compilation")
    boolean scanner;

    @Option(names = {"-p", "--preAnalyze"}, description = "pre-analyze the NewOz input, print the AST to STDOUT, and then stop the compilation")
    boolean preAnalyzer;

    @Option(names = {"-a", "--analyze"}, description = "analyze the NewOz input, print the AST to STDOUT, and then stop the compilation")
    boolean analyzer;


    @Option(names = {"-v", "--verbose"})
    boolean verbose;

    @Override
    public Integer call() throws Exception {
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
