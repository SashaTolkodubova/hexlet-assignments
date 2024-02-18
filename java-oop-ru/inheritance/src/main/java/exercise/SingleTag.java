package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {
    private final String tagName;
    private final Map<String, String> mapAttributes;

    public SingleTag(String tagName, Map<String, String> mapAttributes) {
        this.tagName = tagName;
        this.mapAttributes = mapAttributes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<" + tagName);
        stringBuilder.append(getKeyValuesString(mapAttributes));
        stringBuilder.append(">");
        return stringBuilder.toString();
    }
}
// END
