package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void generateTest() throws Exception {
        var actual = Differ.generate("file1.json", "file2.json");
        var expected = "{\n - follow: false\n   host: hexlet.io\n "
                + "- proxy: 123.234.53.22\n - timeout: 50\n + "
                + "timeout: 20\n + verbose: true\n}";
        assertThat(actual).isEqualTo(expected);
    }
}
