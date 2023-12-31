package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    static String expected;
    @BeforeAll
    static void readFile() throws Exception {
        expected = Utils.readFile("result.txt");
    }

    @Test
    void generateJsonTest() throws Exception {
        var actual = Differ.generate("file1.json", "file2.json");
        assertThat(actual).isEqualTo(expected);
    }
}
