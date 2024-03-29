package hexlet.code.base;

import hexlet.code.enums.Formations;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.model.DiffItem;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String getFormattedString(Formations format, Map<String, DiffItem> map) throws IOException {
        return switch (format) {
            case STYLISH -> Stylish.formatMap(map);
            case PLAIN -> Plain.formatMap(map);
            case JSON -> Json.formatMap(map);
        };
    }
}
