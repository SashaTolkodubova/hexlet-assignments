package exercise;

import java.util.*;
import java.util.Map.Entry;

// BEGIN
class App {
    public static <T> List<Map<T, T>> findWhere(List<Map<T, T>> books, Map<T, T> parameters) {
        List<Map<T, T>> result = new ArrayList<>();

        for (Map<T, T> book : books) {
            boolean flag = true;
            for (Map.Entry<T, T> set : parameters.entrySet()) {
                if (book.containsKey(set.getKey()) && (book.get(set.getKey()).equals(set.getValue()))) {
                } else {
                    flag = false;
                }
            }
            if (flag) {
                result.add(book);
            }
        }

        return result;
    }
}
//END
