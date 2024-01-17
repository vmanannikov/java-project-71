package hexlet.code;

import hexlet.code.base.Parser;
import hexlet.code.enums.Extensions;
import hexlet.code.model.DiffItem;
import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YamlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static List<String> readFile(String filename) throws IOException {
        Path path = Paths.get(filename).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new NoSuchFileException("File '" + path + "' does not exist!");
        }

        var content = Files.readString(path);
        var ext = getExtension(filename);

        return new ArrayList<>(List.of(content, ext));
    }

    public static Map<String, DiffItem> getData(String content, String ext) throws IOException {
        Parser parser = switch (Extensions.valueOf(ext.toUpperCase())) {
            case YML, YAML -> new YamlParser();
            case JSON -> new JsonParser();
        };
        return parser.parse(content);
    }

    public static String getExtension(String filename) {
        var index = filename.lastIndexOf('.');
        return index > 0 ? filename.substring(index + 1) : "";
    }

    public static String getObjectValue(Object o) {
        if (o == null) {
            return "null";
        }
        if (o instanceof String) {
            return "'".concat((String) o).concat("'");
        }
        return o instanceof Integer || o instanceof Boolean
               ? o.toString() : "[complex value]";
    }
}
