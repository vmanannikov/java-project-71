package hexlet.code.formatters;

import hexlet.code.model.DiffItem;

import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Utils.getObjectValue;
import static hexlet.code.enums.Operations.STAYED;


public class Plain {
    public static String formatMap(Map<String, DiffItem> map) {
        Map<String, DiffItem> plainMap = new TreeMap<>(map);

        // Чистим неизменяемые ключи
        map.forEach((k, v) -> {
            if (plainMap.get(k).getStatus().equals(STAYED)) {
                plainMap.remove(k);
            }
        });

        StringBuilder sb = new StringBuilder();

        plainMap.forEach((k, v) -> {
            switch (map.get(k).getStatus()) {
                case CHANGED -> {
                    sb.append("Property '").append(k).append("' was updated. From ")
                            .append(getObjectValue(v.getPrevVal()))
                            .append(" to ").append(getObjectValue(v.getCurVal()))
                            .append("\n");
                }

                case DELETED -> {
                    sb.append("Property '").append(k).append("' was removed").append("\n");
                }

                case ADDED -> {
                    sb.append("Property '").append(k).append("' was added with value: ")
                            .append(getObjectValue(v.getCurVal())).append("\n");
                }
                default -> throw new RuntimeException("Illegal difference key: " + k);
            }
        });

        return sb.toString().trim();
    }
}
