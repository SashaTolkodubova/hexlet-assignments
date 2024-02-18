package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
class PairedTag extends Tag {
    private final String tagName;
    private final Map<String, String> mapAttributes;
    private final String tagBody;
    private final List<Tag> childrenTags;

    public PairedTag(String tagName, Map<String, String> mapAttributes, String tagBody, List<Tag> childrenTags) {
        this.tagName = tagName;
        this.mapAttributes = mapAttributes;
        this.tagBody = tagBody;
        this.childrenTags = childrenTags;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(tagName);
        stringBuilder.append(getKeyValuesString(mapAttributes));
        stringBuilder.append(">");
        stringBuilder.append(tagBody);
        if (childrenTags.size() > 0) {
            for (Tag tag : childrenTags) {
                stringBuilder.append(tag.toString());
            }
        }
        stringBuilder.append("</");
        stringBuilder.append(tagName);
        stringBuilder.append(">");
        return stringBuilder.toString();
    }
}
// END
