package org.css.classmap.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CssReader {

    final static Pattern pattern = Pattern.compile("\\.-?[_a-zA-Z]+[_a-zA-Z0-9-]*\\s*", Pattern.CASE_INSENSITIVE);

    private final String folder;

    public CssReader(String folder) {
        this.folder = folder;
    }

    public Map<String, List<String>> read() throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get(this.folder))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(s -> s.toString().endsWith(".css"))
                    .forEach(s -> {
                        try {
                            List<String> classes = new ArrayList<>();
                            Files.lines(s.toAbsolutePath())
                                    .forEach(l -> {
                                        Matcher matcher = pattern.matcher(l);
                                        while (matcher.find()) {
                                            String className = matcher.group().substring(1).trim();
                                            classes.add(className);
                                        }
                                    });
                            result.put(s.toString(), classes);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        return result;
    }
}
