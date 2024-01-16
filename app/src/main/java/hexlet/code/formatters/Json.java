package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.DiffItem;

import java.io.IOException;
import java.util.Map;

public class Json {
    public static String formatMap(Map<String, DiffItem> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }
}
