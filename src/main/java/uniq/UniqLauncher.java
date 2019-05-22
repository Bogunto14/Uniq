package uniq;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class UniqLauncher {

    @Option(name = "-o", metaVar = "outputFile", usage = "Set output file name")
    private String outputFileName;

    @Option(name = "-i", usage = "Ignore case")
    private boolean ignoreCase;

    @Option(name = "-s", metaVar = "num", usage = "Ignore the first N characters of each line")
    private int skipNCharacters;

    @Option(name = "-u", usage = "Output only unique lines")
    private boolean onlyUniq;

    @Option(name = "-c", usage = "Number of changed strings")
    private boolean numberOfString;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    public static void main(String[] args) {
        new UniqLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);


        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar uniq.jar [-i] [-u] [-c] [-s num] [-o ofile] [file]");
            parser.printUsage(System.err);
            return;
        }

        Uniq uniq = new Uniq(ignoreCase, skipNCharacters, onlyUniq, numberOfString);

        try {
            uniq.createUniq(inputFileName, outputFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}