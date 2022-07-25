package org.css.classmap.generator;

import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CssClassMapGenerator {

    public static void main(String[] args) throws ParseException, IOException {
        ArgsParser parser = new ArgsParser(args);
        CssReader cssReader = new CssReader(parser.getCssFolderPath());
        ClassMapCreator classMapCreator = new ClassMapCreator(cssReader.read());
        List<String> classMapItems = classMapCreator.createClassMap();

        Path file = Paths.get(parser.getOutputFilePath());
        Files.write(file, classMapItems, StandardOpenOption.CREATE);
    }
}
