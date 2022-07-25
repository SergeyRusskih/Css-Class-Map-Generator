package org.css.classmap.generator;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArgsParserTest {

    @Test
    public void shouldParse_requiredArgs_ifProvided() throws ParseException {
        String[] args = { "--folder=foo", "--output=bar" };
        ArgsParser parser = new ArgsParser(args);
        assertEquals("foo", parser.getCssFolderPath());
        assertEquals("bar", parser.getOutputFilePath());
    }

    @Test
    public void shouldThrowException_whileParsing_ifNoCssPathProvided() {
        String[] args = { "--output=bar" };
        assertThrows(ParseException.class, () -> new ArgsParser(args));
    }

    @Test
    public void shouldThrowException_whileParsing_ifOutputFilePathProvided() {
        String[] args = { "--folder=foo" };
        assertThrows(ParseException.class, () -> new ArgsParser(args));
    }
}
