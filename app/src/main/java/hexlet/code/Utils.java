package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Utils {
    public static String readFile(String filename) throws IOException {
        Path path = Paths.get(filename).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new NoSuchFileException("File '" + path + "' does not exist!");
        }
        return Files.readString(path);
    }

    public static Map<String, Object> unserialize(String text) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(text, Map.class);
        return data;
    }
}
