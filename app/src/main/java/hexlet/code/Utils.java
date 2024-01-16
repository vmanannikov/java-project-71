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
import java.util.Map;

public class Utils {
    public static Map<String, DiffItem> readFile(String filename) throws IOException {
        Path path = Paths.get(filename).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new NoSuchFileException("File '" + path + "' does not exist!");
        }

        var content = Files.readString(path);
        var ext = getExtension(filename);

        Parser parser = switch (Extensions.valueOf(ext.toUpperCase())) {
            case YML, YAML -> new YamlParser();
            case JSON -> new JsonParser();
        };

        var fileMap = parser.parse(content);

        return fileMap;
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
