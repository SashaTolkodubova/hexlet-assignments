package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String label;
    private TagInterface subTag;

    public LabelTag(String label, TagInterface subTag) {
        this.label = label;
        this.subTag = subTag;
    }

    @Override
    public String render() {
        return "<label>" + label + subTag.render() + "</label>";
    }
}
// END
