package hexlet.code.base;

import hexlet.code.storage.DiffItem;

import java.io.IOException;
import java.util.Map;

public interface Parser {
    Map<String, DiffItem> parse(String content) throws IOException;
}
