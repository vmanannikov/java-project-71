package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static String readFile(String filename) throws IOException {
        Path path = Paths.get(filename).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new NoSuchFileException("File '" + path + "' does not exist!");
        }
        return Files.readString(path);
    }

    public static String getExtension(String filename) {
        var index = filename.lastIndexOf('.');
        return index > 0 ? filename.substring(index + 1) : "";
    }

    public static String getObjectValue(Object o) {
        if (o == null) return "null";
        if (o instanceof String) return "'".concat((String) o).concat("'");
        return o instanceof Integer || o instanceof Boolean ?
                o.toString() : "[complex value]";
    }
}
