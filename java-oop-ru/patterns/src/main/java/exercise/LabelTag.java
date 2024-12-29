package exercise;

// BEGIN
public class LabelTag implements TagInterface {

    private TagInterface tagInterface;
    private String description;

    public LabelTag(String description, TagInterface tagInterface) {
        this.description = description;
        this.tagInterface = tagInterface;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", description, tagInterface.render());
    }
}
// END
