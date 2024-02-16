package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage dataBase) {
        Map<String, String> tempMap = Map.copyOf(dataBase.toMap());
        HashMap<String, String> swapedDatabase = new HashMap<>();
        for (String key : tempMap.keySet()) {
            swapedDatabase.put(tempMap.get(key), key);
            dataBase.unset(key);
//            dataBase.set(tempMap.get(key), key);
        }
        for (String key : swapedDatabase.keySet()) {
            dataBase.set(key, swapedDatabase.get(key));
        }

    }
}
// END
