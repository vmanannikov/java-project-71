package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import hexlet.code.base.Parser;
import hexlet.code.storage.DiffItem;

import java.util.Map;

public final class JsonParser implements Parser {
    @Override
    public Map<String, DiffItem> parse(String content) throws JsonProcessingException {
        ObjectMapper mapper = new JsonMapper();
        return mapper.readValue(content, Map.class);
    }
}
