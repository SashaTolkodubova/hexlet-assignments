package exercise;

import java.util.*;

// BEGIN
class App {

    public static Map<String, Integer> getWordCount(String sentence) {
        ArrayList<String> wordsArray = new ArrayList<>(List.of(sentence.split(" ")));
        HashMap<String, Integer> result = new HashMap<>();

        if (wordsArray.size() != 1 || !"".equals(wordsArray.get(0))) {
            for (String word : wordsArray) {
                result.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }
        return result;
    }

    public static String toString(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return "{}";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{\n");
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                stringBuilder.append("  ");
                stringBuilder.append(key);
                stringBuilder.append(": ");
                stringBuilder.append(value);
                stringBuilder.append("\n");
            }
            stringBuilder.append("}\n");
            return stringBuilder.toString();
        }
    }
}
//END
