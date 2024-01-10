package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.base.Parser;
import hexlet.code.storage.DiffItem;

import java.util.Map;

public final class YamlParser implements Parser {
    @Override
    public Map<String, DiffItem> parse(String content) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, Map.class);
    }
}
