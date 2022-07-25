package org.css.classmap.generator;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CssRendererTest {

    private final CssReader cssReader = new CssReader(
            Paths.get(
                    System.getProperty("user.dir"),
                    "src/test/java/org/css/classmap/generator/classes.css").toString());

    @Test
    public void shouldReadFile_andExtractClasses_fromCss() throws IOException {
        Map<String, List<String>> readResult = cssReader.read();
        assertEquals(1, readResult.size());
        String firstKey = (String) readResult.keySet().toArray()[0];
        List<String> classes = readResult.get(firstKey);
        assertEquals(7, classes.size());
    }
}
