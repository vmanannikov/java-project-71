package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    static String expectedJson;
    static String expectedStylish;
    static String expectedPlain;

    private static Path getPath(String file) {
        return Paths.get("src", "test", "resources", "result", file)
                .toAbsolutePath().normalize();
    }

    private static Path getPath(String file, String ext) {
        return Paths.get("src", "test", "resources", "input", ext, file)
                .toAbsolutePath().normalize();
    }

    private static String readFileResult(String file) throws Exception {
        Path filePath = getPath(file);
        return Files.readString(filePath).trim();
    }

    private static String readFileInput(String file, String ext) throws Exception {
        Path filePath = getPath(file, ext);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    static void readFile() throws Exception {
        expectedJson = readFileResult("JSON.txt");
        expectedStylish = readFileResult("STYLISH.txt");
        expectedPlain = readFileResult("PLAIN.txt");
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvSource(useHeadersInDisplayName = true, value = {
            "ext, format, expected",
            "json, stylish, expectedStylish",
            "json, '', expectedStylish",
            "json, pla  in, expectedPlain",
            "json, json, expectedJson",
            "yml, stylish, expectedStylish",
            "yml, '', expectedStylish",
            "yml, plain, expectedPlain",
            "yml, json, expectedJson"
    })
    void generateTest(String extension, String format, String expectedField) throws Exception {
        var ext = extension.equals("yml") ? "yaml" : extension;
        String path1 = String.valueOf(getPath("file1.".concat(extension), ext.toUpperCase()));
        String path2 = String.valueOf(getPath("file2.".concat(extension), ext.toUpperCase()));
        try {
            String expected = (String) this.getClass().getDeclaredField(expectedField).get(this);
            assertThat(Differ.generate(path1, path2, format.isEmpty() ? "stylish" : format)).isEqualTo(expected);
        } catch (NoSuchFieldException | IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
