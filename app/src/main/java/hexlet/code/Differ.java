package hexlet.code;

import hexlet.code.base.Formatter;
import hexlet.code.enums.Formations;

import java.io.IOException;

import static hexlet.code.Utils.getData;

public class Differ {
    private static final int EXT_INDEX = 1;
    private static final int CONTENT_INDEX = 0;
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String format) throws IOException {

        // читаем содержимое
        var fileContent1 = Utils.readFile(filePath1);
        var fileContent2 = Utils.readFile(filePath2);

        // формируем объекты на основе содержимого
        var fileMap1 = getData(fileContent1.get(CONTENT_INDEX), fileContent1.get(EXT_INDEX));
        var fileMap2 = getData(fileContent2.get(CONTENT_INDEX), fileContent2.get(EXT_INDEX));

        var diff = DiffBuilder.getDiff(fileMap1, fileMap2);
        var formation = Formations.valueOf(format.toUpperCase());

        return Formatter.getFormattedString(formation, diff);

    }
}
