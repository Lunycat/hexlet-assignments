package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private final Map<String, String> memory;

    public InMemoryKV(Map<String, String> memory) {
        this.memory = new HashMap<>(memory);
    }

    @Override
    public void set(String key, String value) {
        memory.put(key, value);
    }

    @Override
    public void unset(String key) {
        memory.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return memory.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(memory);
    }
}
// END
