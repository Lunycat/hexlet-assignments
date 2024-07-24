package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static final Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    public void setAndGetTest() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        assertThat(storage.get("k", "-1")).isEqualTo("-1");
        storage.set("k", "v");
        assertThat(storage.get("k", "-1")).isEqualTo("v");
        storage.unset("k");
        assertThat(storage.get("k", "-1")).isEqualTo("-1");
    }
    // END
}
