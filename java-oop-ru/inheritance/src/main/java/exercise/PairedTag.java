package exercise;

import java.util.Map;
import java.util.List;
import java.util.StringJoiner;
import static java.lang.String.format;

// BEGIN
public class PairedTag extends Tag {

    private final String body;
    private final List<Tag> tags;

    public PairedTag(String type, Map<String, String> value, String body, List<Tag> tags) {
        super(type, value);
        this.body = body;
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("",
                format("%s%s",super.toString(), body), format("</%s>", getType()));

        tags.forEach(e -> sj.add(e.toString()));

        return sj.toString();
    }
}
// END
