package hexlet.code.base;

import hexlet.code.enums.Extensions;
import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YamlParser;

public class ParserFactory {
    public static Parser getParser(String extension) {
        return switch (Extensions.valueOf(extension)) {
            case JSON -> new JsonParser();
            case YML, YAML -> new YamlParser();
        };
    }
}
