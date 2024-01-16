package hexlet.code;

import hexlet.code.enums.Operations;
import hexlet.code.model.DiffItem;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {
    public static Map<String, DiffItem> getDiff(Map<String, DiffItem> map1, Map<String, DiffItem> map2) {

        Map<String, DiffItem> diff = new TreeMap<>();
        Set<String> keysFromMap = new TreeSet<>();

        keysFromMap.addAll(map1.keySet());
        keysFromMap.addAll(map2.keySet());

        for (String item: keysFromMap) {
            var diffItem1 = new DiffItem(map1.get(item));
            var diffItem2 = new DiffItem(map2.get(item));

            if (!map2.containsKey(item)) {
                diff.put(item, new DiffItem(Operations.DELETED, map1.get(item), null));
            } else if (!map1.containsKey(item)) {
                diff.put(item, new DiffItem(Operations.ADDED, null, map2.get(item)));
            } else if (!diffItem1.equals(diffItem2)) {
                diff.put(item, new DiffItem(Operations.CHANGED, map1.get(item), map2.get(item)));
            } else {
                diff.put(item, new DiffItem(Operations.STAYED, map1.get(item), null));
            }
        }
        return diff;
    }
}
