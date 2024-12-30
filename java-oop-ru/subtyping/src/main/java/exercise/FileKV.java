package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private final String path;

    public FileKV(String path, Map<String, String> memory) {
        this.path = path;
        Utils.writeFile(path, Utils.serialize(memory));
    }

    @Override
    public void set(String key, String value) {
        var tempMemory = Utils.unserialize(path);
        tempMemory.put(key, value);
        Utils.writeFile(path, Utils.serialize(tempMemory));
    }

    @Override
    public void unset(String key) {
        var tempMemory = Utils.unserialize(path);
        tempMemory.remove(key);
        Utils.writeFile(path, Utils.serialize(tempMemory));
    }

    @Override
    public String get(String key, String defaultValue) {
        var tempMemory = Utils.unserialize(path);
        return tempMemory.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(path);
    }
}
// END
