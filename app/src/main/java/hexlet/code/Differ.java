package hexlet.code;

import hexlet.code.enums.Operations;
import hexlet.code.storage.DiffItem;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.Utils.readFile;
import static hexlet.code.Utils.unserialize;


public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {

        var fileMap1 = unserialize(readFile(filePath1));
        var fileMap2 = unserialize(readFile(filePath2));

        Map<String, DiffItem> diff = new TreeMap<>();
        Set<String> keysFromMap = new TreeSet<>();

        keysFromMap.addAll(fileMap1.keySet());
        keysFromMap.addAll(fileMap2.keySet());

        for (String item: keysFromMap) {
            if (!fileMap2.containsKey(item)) {
                diff.put(item, new DiffItem(Operations.DELETED, fileMap1.get(item), null));
            } else if (!fileMap1.containsKey(item)) {
                diff.put(item, new DiffItem(Operations.ADDED, null, fileMap2.get(item)));
            } else if (!fileMap1.get(item).equals(fileMap2.get(item))) {
                diff.put(item, new DiffItem(Operations.CHANGED, fileMap1.get(item), fileMap2.get(item)));
            } else {
                diff.put(item, new DiffItem(Operations.STAYED, fileMap1.get(item), null));
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
