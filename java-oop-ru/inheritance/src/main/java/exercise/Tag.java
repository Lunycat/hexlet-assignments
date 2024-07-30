package exercise;

import java.util.StringJoiner;
import java.util.Map;
import static java.lang.String.format;

// BEGIN
public abstract class Tag {

    private final String type;
    private final Map<String, String> value;

    public Tag(String type, Map<String, String> value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("", format("<%s", type), ">");

        value.forEach((k, v) -> sj.add(format(" %s=\"%s\"", k, v)));

        return sj.toString();
    }
}
// END
