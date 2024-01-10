package hexlet.code.formatters;

import hexlet.code.storage.DiffItem;
import java.util.Map;

public class Stylish {
    public static String formatMap(Map<String, DiffItem> map) {
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
