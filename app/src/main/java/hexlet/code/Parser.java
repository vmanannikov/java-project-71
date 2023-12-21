package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.Operations.*;

public class Parser {

    public static String parse(String filePath1, String filePath2) throws IOException {

        ObjectMapper mapper = new YAMLMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Map<String, DiffItem> diff = new TreeMap<>();

        var dataOne = Utils.readFile(filePath1);
        var dataTwo = Utils.readFile(filePath2);

        Map<String, Object> mapOne = mapper.readValue(dataOne, Map.class);
        Map<String, Object> mapTwo = mapper.readValue(dataTwo, Map.class);

        Set<String> keysFromMap = new TreeSet<>();

        keysFromMap.addAll(mapOne.keySet());
        keysFromMap.addAll(mapTwo.keySet());

        for (String item: keysFromMap) {
            if (!mapTwo.containsKey(item)) {
                diff.put(item, new DiffItem(DELETED, mapOne.get(item), null));
            } else if (!mapOne.containsKey(item)) {
                diff.put(item, new DiffItem(ADDED, null, mapTwo.get(item)));
            } else if (!mapOne.get(item).equals(mapTwo.get(item))) {
                diff.put(item, new DiffItem(CHANGED, mapOne.get(item), mapTwo.get(item)));
            } else {
                diff.put(item, new DiffItem(Operations.STAYED, mapOne.get(item), null));
            }
        }

        return printDiff(diff);
    }

    public static String printDiff(Map<String, DiffItem> map) {
        StringBuilder sb = new StringBuilder();
        var separator = System.lineSeparator();
        sb.append("{").append(separator);

        map.forEach((k, v) -> {
            switch (map.get(k).getStatus()) {
                case ADDED -> sb.append(" ")
                        .append(" + ").append(k).append(": ").append(v.getCurVal()).append(separator);
                case DELETED -> sb.append(" ")
                        .append(" - ").append(k).append(": ").append(v.getPrevVal()).append(separator);
                case CHANGED -> {
                    sb.append(" ").append(" - ").append(k).append(": ").append(v.getPrevVal()).append(separator);
                    sb.append(" ").append(" + ").append(k).append(": ").append(v.getCurVal()).append(separator);
                }
                default -> sb.append(" ").append("   ").append(k).append(": ").append(v.getPrevVal()).append(separator);
            }
        });

        return sb.append("}").toString();
    }
}
