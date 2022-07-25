package org.css.classmap.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassMapCreator {

    private static final String FIRST_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NEXT_CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final Map<String, List<String>> cssClasses;

    public ClassMapCreator(Map<String, List<String>> cssClasses) {
        this.cssClasses = cssClasses;
    }

    public List<String> createClassMap() {
        Map<String, Integer> frequencyMap = createFrequencyMap();
        List<Map.Entry<String, Integer>> sortedEntities = entriesSortedByValues(frequencyMap);
        return renameClasses(sortedEntities);
    }

    private Map<String, Integer> createFrequencyMap() {
        Map<String, Integer> frequencyMap = new HashMap<>();
        this.cssClasses.forEach((key, value) -> {
            value.forEach(l -> frequencyMap.put(l, frequencyMap.getOrDefault(l, 0) + 1));
        });
        return frequencyMap;
    }

    private List<String> renameClasses(List<Map.Entry<String, Integer>> entries) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<String, Integer> e = entries.get(i);
            StringBuilder sb = new StringBuilder();
            int j = i;
            sb.append(FIRST_CHARS.charAt(j % FIRST_CHARS.length()));
            j = j / FIRST_CHARS.length() - 1;
            for (; j >= 0; j = j / NEXT_CHARS.length() - 1) {
                sb.append(NEXT_CHARS.charAt(j % NEXT_CHARS.length()));
            }
            result.add(String.format("%s: %s", e.getKey(), sb));
        }
        return result;
    }

    static <K,V extends Comparable<? super V>> List<Map.Entry<K, V>> entriesSortedByValues(Map<K,V> map) {
        List<Map.Entry<K,V>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return sortedEntries;
    }
}
