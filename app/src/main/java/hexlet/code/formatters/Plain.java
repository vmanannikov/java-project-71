package hexlet.code.formatters;

import hexlet.code.model.DiffItem;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static hexlet.code.Utils.getObjectValue;
import static hexlet.code.enums.Operations.STAYED;

public class Plain {
    public static String formatMap(Map<String, DiffItem> map) {
        Map<String, DiffItem> plainMap = new TreeMap<>(map);
        return map.entrySet().stream()
            .filter((e) -> !STAYED.equals(e.getValue().getStatus()))
            .map(e -> {
                DiffItem v = e.getValue();
                String k = e.getKey();
                switch (v.getStatus()) {
                    case CHANGED -> {
                        return "Property '" + e.getKey() + "' was updated. From "
                                + getObjectValue(v.getPrevVal())
                                + " to " + getObjectValue(v.getCurVal())
                                + "\n";
                    }
                    case DELETED -> {
                        return "Property '" + k + "' was removed" + "\n";
                    }

                    case ADDED -> {
                        return "Property '" + k + "' was added with value: "
                                + getObjectValue(v.getCurVal()) + "\n";
                    }
                    default -> throw new RuntimeException("Illegal difference key: " + k);
                }

            }).collect(Collectors.joining()).trim();

    }
}
