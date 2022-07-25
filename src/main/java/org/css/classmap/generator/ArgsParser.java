package org.css.classmap.generator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgsParser {
    private final static String CSS_FOLDER = "folder";
    private final static String OUTPUT_FILE = "output";

    private final static Option folder = Option.builder()
            .argName(CSS_FOLDER)
            .longOpt(CSS_FOLDER)
            .hasArg()
            .required()
            .desc("folder with the CSS files")
            .build();

    private final static Option output = Option.builder()
            .argName(OUTPUT_FILE)
            .longOpt(OUTPUT_FILE)
            .hasArg()
            .required()
            .desc("output CSS class map file")
            .build();

    private final static Option help = Option.builder()
            .longOpt("help")
            .desc("print this message")
            .build();

    private final CommandLine commandLine;

    public ArgsParser(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(help);
        options.addOption(folder);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        this.commandLine = parser.parse(options, args);
    }

    public String getCssFolderPath() {
        return commandLine.getOptionValue(CSS_FOLDER);
    }

    public String getOutputFilePath() {
        return commandLine.getOptionValue(OUTPUT_FILE);
    }
}
