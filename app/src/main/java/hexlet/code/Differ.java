package hexlet.code;

import hexlet.code.base.FormatFactory;
import hexlet.code.base.Parser;
import hexlet.code.enums.Extensions;
import hexlet.code.enums.Formations;
import hexlet.code.enums.Operations;
import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YamlParser;
import hexlet.code.storage.DiffItem;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

import static hexlet.code.Utils.getExtension;
import static hexlet.code.Utils.readFile;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String format) throws IOException {

        var fileExt1 = getExtension(filePath1);
        var fileExt2 = getExtension(filePath2);

        if (!fileExt1.equals(fileExt2)) {
            throw new RuntimeException("Files have different extensions");
        }

        // Раз не вызвано исключение можно
        // предположить что файлы с одним расширением
        // поэтому можно брать любой для вычисления парсера
        var ext = Extensions.valueOf(fileExt1.toUpperCase());

        Parser parser = switch (ext) {
            case YML, YAML -> new YamlParser();
            case JSON -> new JsonParser();
        };


        var fileMap1 = parser.parse(readFile(filePath1));
        var fileMap2 = parser.parse(readFile(filePath2));

        Map<String, DiffItem> diff = new TreeMap<>();
        Set<String> keysFromMap = new TreeSet<>();

        keysFromMap.addAll(fileMap1.keySet());
        keysFromMap.addAll(fileMap2.keySet());

        for (String item: keysFromMap) {
            var diffItem1 = new DiffItem(fileMap1.get(item));
            var diffItem2 = new DiffItem(fileMap2.get(item));

            if (!fileMap2.containsKey(item)) {
                diff.put(item, new DiffItem(Operations.DELETED, fileMap1.get(item), null));
            } else if (!fileMap1.containsKey(item)) {
                diff.put(item, new DiffItem(Operations.ADDED, null, fileMap2.get(item)));
            } else if (!diffItem1.equals(diffItem2)) {
                diff.put(item, new DiffItem(Operations.CHANGED, fileMap1.get(item), fileMap2.get(item)));
            } else {
                diff.put(item, new DiffItem(Operations.STAYED, fileMap1.get(item), null));
            }
        }

        var formation = Formations.valueOf(format.toUpperCase());

        String formatter = FormatFactory.getFormat(formation, diff);

        return formatter;
    }
}
