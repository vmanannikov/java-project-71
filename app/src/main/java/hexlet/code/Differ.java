package hexlet.code;

import hexlet.code.base.Formatter;
import hexlet.code.enums.Formations;

import java.io.IOException;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String format) throws IOException {

        var fileMap1 = Utils.readFile(filePath1);
        var fileMap2 = Utils.readFile(filePath2);

        var diff = DiffBuilder.getDiff(fileMap1, fileMap2);
        var formation = Formations.valueOf(format.toUpperCase());

        return Formatter.getFormattedString(formation, diff);

    }
}
