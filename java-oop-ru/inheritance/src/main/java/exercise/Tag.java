package exercise;

import java.util.Map;

// BEGIN
abstract class Tag {
    abstract public String toString();

    protected String getKeyValuesString(Map<String, String> mapAttributes) {
        if (mapAttributes.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : mapAttributes.entrySet()) {
                stringBuilder.append(" ");
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append("\"");
                stringBuilder.append(entry.getValue());
                stringBuilder.append("\"");
            }
            return stringBuilder.toString();
        } else {
            return "";
        }
    }
}
// END
