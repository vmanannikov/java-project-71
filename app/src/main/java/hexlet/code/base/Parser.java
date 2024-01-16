package hexlet.code.base;

import hexlet.code.model.DiffItem;

import java.io.IOException;
import java.util.Map;

public interface Parser {
    Map<String, DiffItem> parse(String content) throws IOException;
}
