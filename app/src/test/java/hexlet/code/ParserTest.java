package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {
    static String expected;
    @BeforeAll
    static void readFile() throws Exception {
        expected = Utils.readFile("result.txt");
    }

    @Test
    void generateYamlTest() throws Exception {
        var actual = Parser.parse("file1.yml", "file2.yml");
        assertThat(actual).isEqualTo(expected);
    }
}
